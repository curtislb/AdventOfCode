package com.curtislb.adventofcode.common.geometry

import com.curtislb.adventofcode.common.collection.getCyclic

/**
 * A valid direction in a two-dimensional grid.
 *
 * @param clockwiseIndex The index of this direction in clockwise order, starting with [UP].
 */
enum class Direction(private val clockwiseIndex: Int) {
    /**
     * The direction of positive y.
     */
    UP(0),

    /**
     * The direction of positive x and y.
     */
    UP_RIGHT(1),

    /**
     * The direction of positive x.
     */
    RIGHT(2),

    /**
     * The direction of positive x and negative y.
     */
    DOWN_RIGHT(3),

    /**
     * The direction of negative y.
     */
    DOWN(4),

    /**
     * The direction of negative x and y.
     */
    DOWN_LEFT(5),

    /**
     * The direction of negative x.
     */
    LEFT(6),

    /**
     * The direction of negative x and positive y.
     */
    UP_LEFT(7);

    /**
     * Returns the direction given by turning 180 degrees from this one.
     */
    fun reverse(): Direction =
        clockwiseOrder.getCyclic(clockwiseIndex + RotationAngle.DEGREES_180.turnCount)

    /**
     * Returns the direction given by turning a given [angle] counterclockwise from this one.
     */
    fun turnLeft(angle: RotationAngle = RotationAngle.DEGREES_90): Direction =
        clockwiseOrder.getCyclic(clockwiseIndex - angle.turnCount)

    /**
     * Returns the direction given by turning a given [angle] clockwise from this one.
     */
    fun turnRight(angle: RotationAngle = RotationAngle.DEGREES_90): Direction =
        clockwiseOrder.getCyclic(clockwiseIndex + angle.turnCount)

    companion object {
        /**
         * A list of all directions in clockwise order, starting with [UP].
         */
        private val clockwiseOrder: List<Direction> = values().sortedBy { it.clockwiseIndex }

        /**
         * Returns an array of all cardinal directions.
         */
        fun cardinalValues(): Array<Direction> = arrayOf(UP, RIGHT, DOWN, LEFT)

        /**
         * Returns the cardinal direction corresponding to [char].
         *
         * @throws IllegalArgumentException If [char] has no corresponding direction.
         */
        fun fromChar(char: Char): Direction = when (char) {
            'U', 'u' -> UP
            'R', 'r' -> RIGHT
            'D', 'd' -> DOWN
            'L', 'l' -> LEFT
            else -> throw IllegalArgumentException("Invalid direction character: $char")
        }
    }
}
