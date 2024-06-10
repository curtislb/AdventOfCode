package com.curtislb.adventofcode.common.geometry

import com.curtislb.adventofcode.common.collection.getCyclic

/**
 * A valid direction in a two-dimensional grid.
 *
 * @property clockwiseIndex The index of the direction in clockwise order, starting with [UP].
 */
enum class Direction(private val clockwiseIndex: Int) {
    /**
     * The direction of positive y.
     */
    UP(clockwiseIndex = 0),

    /**
     * The direction of positive x and y.
     */
    UP_RIGHT(clockwiseIndex = 1),

    /**
     * The direction of positive x.
     */
    RIGHT(clockwiseIndex = 2),

    /**
     * The direction of positive x and negative y.
     */
    DOWN_RIGHT(clockwiseIndex = 3),

    /**
     * The direction of negative y.
     */
    DOWN(clockwiseIndex = 4),

    /**
     * The direction of negative x and y.
     */
    DOWN_LEFT(clockwiseIndex = 5),

    /**
     * The direction of negative x.
     */
    LEFT(clockwiseIndex = 6),

    /**
     * The direction of negative x and positive y.
     */
    UP_LEFT(clockwiseIndex = 7);

    /**
     * Returns `true` if the direction is horizontal.
     */
    fun isHorizontal(): Boolean = this == RIGHT || this == LEFT

    /**
     * Returns `true` if the direction is vertical.
     */
    fun isVertical(): Boolean = this == UP || this == DOWN

    /**
     * Returns `true` if the direction is diagonal.
     */
    fun isDiagonal(): Boolean = when (this) {
        UP, RIGHT, DOWN, LEFT -> false
        UP_RIGHT, DOWN_RIGHT, DOWN_LEFT, UP_LEFT -> true
    }

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
        private val clockwiseOrder: List<Direction> = entries.sortedBy { it.clockwiseIndex }

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
            'U', 'u', '^' -> UP
            'R', 'r', '>' -> RIGHT
            'D', 'd', 'v' -> DOWN
            'L', 'l', '<' -> LEFT
            else -> throw IllegalArgumentException("No direction for char: $char")
        }
    }
}
