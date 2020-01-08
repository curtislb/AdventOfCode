package com.adventofcode.curtislb.year2019.day11.painting

import com.adventofcode.curtislb.common.grid.Direction
import com.adventofcode.curtislb.common.grid.Point
import com.adventofcode.curtislb.common.intcode.Intcode
import java.math.BigInteger

/**
 * A robot which navigates and paints panels in an infinite 2D grid.
 */
class Robot {
    /**
     * The current [Point] position of the [Robot] in the grid.
     */
    var position: Point = Point(0, 0)
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
    private val panels: MutableMap<Point, Boolean> = mutableMapOf()

    /**
     * A [Map] of all panels on the grid that the [Robot] has painted.
     *
     * Each [Point] key represents a painted panel on the grid, and each value represents the color that the panel has
     * been painted, with `true` representing white and `false` representing black.
     */
    val paintedPanels: Map<Point, Boolean>
        get() = panels.toMap()

    /**
     * Whether the panel that the [Robot] is currently on has been painted white.
     */
    private val isOnWhitePanel: Boolean
        get() = panels[position] == true

    /**
     * A 2D [Array] representing the portion of the grid that the [Robot] has painted.
     *
     * The value at each position in `paintedGrid` represents the color of the panel at the corresponding relative
     * position in the grid, with `true` representing white and `false` representing black.
     */
    val paintedGrid: Array<BooleanArray>
        get() {
            var minX = 0
            var minY = 0
            var maxX = 0
            var maxY = 0
            panels.forEach { (point, _) ->
                minX = minX.coerceAtMost(point.x)
                minY = minY.coerceAtMost(point.y)
                maxX = maxX.coerceAtLeast(point.x)
                maxY = maxY.coerceAtLeast(point.y)
            }

            val grid = Array(maxY - minY + 1) { BooleanArray(maxX - minX + 1) }
            panels.forEach { (point, white) -> grid[maxY - point.y][point.x - minX] = white }
            return grid
        }

    /**
     * Moves the [Robot] forward one space from its current [position], in the [direction] it's currently facing.
     */
    fun moveForward() { position = position.move(direction, 1) }

    /**
     * Paints the panel at the current [position] of the [Robot] with the specified color.
     * @param white Whether the panel should be painted white. If `false`, the panel will be painted black instead.
     */
    fun paint(white: Boolean) { panels[position] = white }

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
                // Turn the robot and move it forward one space
                when (output) {
                    BigInteger.ZERO -> turnLeft()
                    BigInteger.ONE -> turnRight()
                    else -> throw IllegalArgumentException("Invalid program output: $output.")
                }
                moveForward()

                // Send new panel color as input to the program
                intcode.sendInput(if (isOnWhitePanel) BigInteger.ONE else BigInteger.ZERO)
            } else {
                // Have the robot paint the current panel
                when (output) {
                    BigInteger.ZERO -> paint(white = false)
                    BigInteger.ONE -> paint(white = true)
                    else -> throw IllegalArgumentException("Invalid program output: $output.")
                }
            }

            // Toggle flag for how program output should be interpreted
            didPaint = !didPaint
        }

        // Send initial input and run the program until it halts
        intcode.sendInput(if (isOnWhitePanel) BigInteger.ONE else BigInteger.ZERO)
        while (!intcode.isDone) {
            intcode.run()
        }
    }
}
