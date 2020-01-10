package com.adventofcode.curtislb.common.grid

/**
 * Enum representing valid directions on a 2D grid.
 */
enum class Direction { UP, RIGHT, DOWN, LEFT }

/**
 * Converts a [Char] to a corresponding [Direction].
 * @receiver The [Char] to be converted. Must be one of `{'U', 'R', 'D', 'L'}` (case-insensitive).
 * @return The [Direction] corresponding to this [Char].
 * @throws IllegalArgumentException If called for any [Char] not specified above.
 */
fun Char.toDirection(): Direction {
    return when (this) {
        'U', 'u' -> Direction.UP
        'R', 'r' -> Direction.RIGHT
        'D', 'd' -> Direction.DOWN
        'L', 'l' -> Direction.LEFT
        else -> throw IllegalArgumentException("Invalid direction character: $this")
    }
}

/**
 * TODO
 */
fun Direction.reverse(): Direction {
    return when (this) {
        Direction.UP -> Direction.DOWN
        Direction.RIGHT -> Direction.LEFT
        Direction.DOWN -> Direction.UP
        Direction.LEFT -> Direction.RIGHT
    }
}
