package com.adventofcode.curtislb.common.grid

/**
 * A valid direction on a two-dimensional grid.
 */
enum class Direction {
    UP, RIGHT, DOWN, LEFT;

    /**
     * Gives the [Direction] that is the reverse of this one.
     * @receiver The [Direction] from which to turn.
     * @return The [Direction] that results from turning 180 degrees from this [Direction].
     */
    fun reverse(): Direction {
        return when (this) {
            UP -> DOWN
            RIGHT -> LEFT
            DOWN -> UP
            LEFT -> RIGHT
        }
    }

    /**
     * Gives the [Direction] that is to the right of this one.
     * @receiver The [Direction] from which to turn.
     * @return The [Direction] that results from turning 90 degrees clockwise from this [Direction].
     */
    fun turnRight(): Direction {
        return when (this) {
            UP -> RIGHT
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
        }
    }

    /**
     * Gives the [Direction] that is to the left of this one.
     * @receiver The [Direction] from which to turn.
     * @return The [Direction] that results from turning 90 degrees counterclockwise from this [Direction].
     */
    fun turnLeft(): Direction {
        return when (this) {
            UP -> LEFT
            RIGHT -> UP
            DOWN -> RIGHT
            LEFT -> DOWN
        }
    }

    companion object {
        /**
         * Converts a [Char] to its corresponding [Direction].
         * @param char The [Char] to be converted. Must be one of 'U', 'L', 'D', or 'R' (case-insensitive).
         * @return The [Direction] corresponding to [char].
         * @throws IllegalArgumentException If [char] has no corresponding [Direction].
         */
        fun from(char: Char): Direction {
            return when (char) {
                'U', 'u' -> UP
                'R', 'r' -> RIGHT
                'D', 'd' -> DOWN
                'L', 'l' -> LEFT
                else -> throw IllegalArgumentException("Invalid direction character: $char")
            }
        }
    }
}
