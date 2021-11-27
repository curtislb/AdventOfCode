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

    /**
     * Returns the minimum number of 90-degree clockwise turns from this direction to face [other].
     */
    fun countRightTurns(other: Direction) = when (this) {
        UP -> when (other) {
            UP -> 0
            RIGHT -> 1
            DOWN -> 2
            LEFT -> 3
        }

        RIGHT -> when (other) {
            UP -> 3
            RIGHT -> 0
            DOWN -> 1
            LEFT -> 2
        }

        DOWN -> when (other) {
            UP -> 2
            RIGHT -> 3
            DOWN -> 0
            LEFT -> 1
        }

        LEFT -> when (other) {
            UP -> 1
            RIGHT -> 2
            DOWN -> 3
            LEFT -> 0
        }
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
