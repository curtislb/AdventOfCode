package com.adventofcode.curtislb.common.grid

/**
 * Enum representing valid directions on a 2D grid.
 */
enum class Direction { UP, RIGHT, DOWN, LEFT }

/**
 * Converts a [Char] to its corresponding [Direction].
 * @receiver The [Char] to be converted. Must be one of {'U', 'R', 'D', 'L'}.
 * @return The [Direction] corresponding to this [Char].
 * @throws IllegalArgumentException If called for any [Char] not specified above.
 */
fun Char.toDirection(): Direction {
    return when (this) {
        'U' -> Direction.UP
        'R' -> Direction.RIGHT
        'D' -> Direction.DOWN
        'L' -> Direction.LEFT
        else -> throw IllegalArgumentException("Invalid direction character: $this")
    }
}
