package com.curtislb.adventofcode.year2019.day11.painting

import com.curtislb.adventofcode.common.grid.Direction
import com.curtislb.adventofcode.common.grid.Orientation
import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.constructGrid
import com.curtislb.adventofcode.common.intcode.Intcode
import java.math.BigInteger

/**
 * A robot that navigates and paints panels in an infinite 2D grid.
 */
class Robot {
    /**
     * The current orientation of the robot in the grid.
     */
    var orientation: Orientation = Orientation(Point.ORIGIN, Direction.UP)
        private set

    /**
     * A map from the position of each grid panel the robot has painted to the current color of that panel.
     */
    private val paintedPanels: MutableMap<Point, Color> = mutableMapOf()

    /**
     * Whether the panel that the robot is currently on has been painted white.
     */
    private val isOnWhitePanel: Boolean get() = paintedPanels[orientation.position] == Color.WHITE

    /**
     * The number of panels in the grid that the robot has painted.
     */
    val paintedArea: Int get() = paintedPanels.size

    /**
     * Returns a matrix representing the portion of the grid that the robot has painted.
     */
    fun constructPaintedGrid(): List<List<Color>> {
        return constructGrid(paintedPanels.keys) { paintedPanels.getOrDefault(it, Color.BLACK) }
    }

    /**
     * Moves the robot forward one space from its current position, in the direction it's currently facing.
     */
    fun moveForward() {
        orientation = orientation.moveForward()
    }

    /**
     * Turns the robot 90 degrees to the left from the direction it's currently facing.
     */
    fun turnLeft() {
        orientation = orientation.turnLeft()
    }

    /**
     * Turns the robot 90 degrees to the right from the direction it's currently facing.
     */
    fun turnRight() {
        orientation = orientation.turnRight()
    }

    /**
     * Paints the panel currently underneath the robot with [color] paint.
     */
    fun paint(color: Color) {
        paintedPanels[orientation.position] = color
    }

    /**
     * Causes the robot to follow the instructions given by an [intcode] program.
     *
     * The program should expect as input a value representing the color (0 for a black, or 1 for white) of the panel on
     * which the robot is currently located. This should be provided once initially and again each time the robot moves
     * to another position. The program should then output a series of 0 or 1 values. These will be interpreted
     * alternately by the robot as:
     * 1. A color to paint the current panel, with 0 representing black and 1 representing white.
     * 2. A direction to turn before moving forward, with 0 representing left and 1 representing right.
     *
     * This process will continue until the [intcode] program halts, at which point this method will return.
     *
     * @throws IllegalArgumentException If [intcode] produces an invalid output value.
     */
    fun executeProgram(intcode: Intcode) {
        var didPaint = false
        intcode.onOutput = { output ->
            if (didPaint) {
                // Turn the robot and move it forward one space.
                when (output) {
                    BigInteger.ZERO -> turnLeft()
                    BigInteger.ONE -> turnRight()
                    else -> throw IllegalArgumentException("Invalid program output: $output")
                }
                moveForward()

                // Send new panel color as input to the program.
                intcode.sendInput(if (isOnWhitePanel) BigInteger.ONE else BigInteger.ZERO)
            } else {
                // Have the robot paint the current panel.
                when (output) {
                    BigInteger.ZERO -> paint(Color.BLACK)
                    BigInteger.ONE -> paint(Color.WHITE)
                    else -> throw IllegalArgumentException("Invalid program output: $output")
                }
            }

            // Toggle flag for how program output should be interpreted.
            didPaint = !didPaint
        }

        // Send initial input and run the program until it halts.
        intcode.sendInput(if (isOnWhitePanel) BigInteger.ONE else BigInteger.ZERO)
        while (!intcode.isDone) {
            intcode.run()
        }
    }
}
