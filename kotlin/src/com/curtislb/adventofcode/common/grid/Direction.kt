package com.curtislb.adventofcode.common.grid

/**
 * A valid direction in a two-dimensional grid.
 */
enum class Direction {
    UP, RIGHT, DOWN, LEFT;

    /**
     * Returns the direction given by turning 180 degrees from this one.
     */
    fun reverse(): Direction = when (this) {
        UP -> DOWN
        RIGHT -> LEFT
        DOWN -> UP
        LEFT -> RIGHT
    }

    /**
     * Returns the direction given by turning 90 degrees counterclockwise from this one.
     */
    fun turnLeft(): Direction = when (this) {
        UP -> LEFT
        RIGHT -> UP
        DOWN -> RIGHT
        LEFT -> DOWN
    }

    /**
     * Returns the direction given by turning 90 degrees clockwise from this one.
     */
    fun turnRight(): Direction = when (this) {
        UP -> RIGHT
        RIGHT -> DOWN
        DOWN -> LEFT
        LEFT -> UP
    }

    companion object {
        /**
         * Returns the direction corresponding to [char].
         *
         * @throws IllegalArgumentException If [char] has no corresponding direction.
         */
        fun from(char: Char): Direction = when (char) {
            'U', 'u' -> UP
            'R', 'r' -> RIGHT
            'D', 'd' -> DOWN
            'L', 'l' -> LEFT
            else -> throw IllegalArgumentException("Invalid direction character: $char")
        }
    }
}
