package com.adventofcode.curtislb.year2019.day13.game

import java.math.BigInteger

/**
 * A type of space on the game board, which can have various properties according to the rules of the game.
 * @param symbol A [Char] symbol that is used to represent this [Tile].
 */
enum class Tile(val symbol: Char) {
    /**
     * A [Tile] representing an empty space on the board.
     */
    EMPTY('.'),

    /**
     * A [Tile] representing a wall, which is indestructible and can't be moved.
     */
    WALL('#'),

    /**
     * A [Tile] representing a block, which can be broken by a ball.
     */
    BLOCK('X'),

    /**
     * A [Tile] representing a paddle, which is indestructible and can be moved horizontally by the player.
     */
    PADDLE('='),

    /**
     * A [Tile] representing a ball, which moves diagonally and bounces off objects.
     */
    BALL('O')
}

/**
 * Converts an [Int] value to its corresponding [Tile].
 * @receiver An [Int] representing a valid [Tile].
 * @return The [Tile] corresponding to this [Int].
 * @throws IllegalArgumentException If called for an [Int] with no corresponding [Tile].
 */
fun Int.toTile(): Tile {
    return when(this) {
        0 -> Tile.EMPTY
        1 -> Tile.WALL
        2 -> Tile.BLOCK
        3 -> Tile.PADDLE
        4 -> Tile.BALL
        else -> throw IllegalArgumentException("Unknown tile value: $this")
    }
}

/**
 * Converts a [BigInteger] value to its corresponding [Tile].
 * @receiver A [BigInteger] representing a valid [Tile].
 * @return The [Tile] object corresponding to this [BigInteger].
 * @throws IllegalArgumentException If called for a [BigInteger] with no corresponding [Tile].
 */
fun BigInteger.toTile(): Tile = toInt().toTile()
