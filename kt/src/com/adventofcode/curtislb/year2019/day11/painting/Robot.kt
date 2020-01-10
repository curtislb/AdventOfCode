package com.adventofcode.curtislb.year2019.day11.painting

import com.adventofcode.curtislb.common.grid.Direction
import com.adventofcode.curtislb.common.grid.Point
import com.adventofcode.curtislb.common.grid.constructGrid
import com.adventofcode.curtislb.common.intcode.Intcode
import java.math.BigInteger

/**
 * A robot that navigates and paints panels in an infinite 2D grid.
 */
class Robot {
    /**
     * The current [Point] position of the [Robot] in the grid.
     */
    var position: Point = Point.ORIGIN
        private set

    /**
     * The current [Direction] in which the [Robot] is facing.
     */
    var direction: Direction = Direction.UP
        private set

    /**
     * A [MutableMap] of all panels on the gird that the [Robot] has painted.
     *
     * See [paintedPanels] for the corresponding public field.
     */
    private val panels: MutableMap<Point, Color> = mutableMapOf()

    /**
     * A [Map] of all panels on the grid that the [Robot] has painted.
     */
    val paintedPanels: Map<Point, Color>
        get() = panels.toMap()

    /**
     * Whether the panel that the [Robot] is currently on has been painted white.
     */
    private val isOnWhitePanel: Boolean
        get() = panels[position] == Color.WHITE

    /**
     * A [List] of lists representing the portion of the grid that the [Robot] has painted.
     */
    val paintedGrid: List<List<Color>>
        get() = constructGrid(panels.keys) { panels.getOrDefault(it, Color.BLACK) }

    /**
     * Moves the [Robot] forward one space from its current [position], in the [direction] it's currently facing.
     */
    fun moveForward() { position = position.move(direction) }

    /**
     * Paints the panel at the current [position] of the [Robot] with the specified [Color].
     * @param color The [Color] that the current panel should be painted.
     */
    fun paint(color: Color) { panels[position] = color }

    /**
     * Turns the [Robot] to face 90 degrees to the left from its current [direction].
     */
    fun turnLeft() {
        direction = when (direction) {
            Direction.UP -> Direction.LEFT
            Direction.RIGHT -> Direction.UP
            Direction.DOWN -> Direction.RIGHT
            Direction.LEFT -> Direction.DOWN
        }
    }

    /**
     * Turns the [Robot] to face 90 degrees to the right from its current [direction].
     */
    fun turnRight() {
        direction = when (direction) {
            Direction.UP -> Direction.RIGHT
            Direction.RIGHT -> Direction.DOWN
            Direction.DOWN -> Direction.LEFT
            Direction.LEFT -> Direction.UP
        }
    }

    /**
     * Causes the [Robot] to follow the instructions given by an [Intcode] program.
     *
     * The program should expect as input a value representing the color (0 for a black, or 1 for white) of the panel on
     * which the [Robot] is currently located. This  will be provided once initially and again each time the [Robot]
     * moves to another position. The program should then output a series of 0 or 1 values. These will be interpreted
     * alternately by the [Robot] as:
     * 1. A color to paint the current panel, with 0 representing black and 1 representing white
     * 2. A direction to turn before moving forward, with 0 representing left and 1 representing right
     *
     * This process will continue until the [Intcode] program halts, at which point this method will return.
     *
     * @param intcode The [Intcode] program that will receive input and provide output to the [Robot].
     * @throws IllegalArgumentException If [intcode] produces an invalid value as output.
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
