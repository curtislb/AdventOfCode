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
    BALL('O');

    companion object {
        /**
         * Converts an [Int] value to its corresponding [Tile].
         * @param value An [Int] representing a valid [Tile].
         * @return The [Tile] corresponding to [value].
         * @throws IllegalArgumentException If [value] has no corresponding [Tile].
         */
        fun from(value: Int): Tile {
            return when(value) {
                0 -> EMPTY
                1 -> WALL
                2 -> BLOCK
                3 -> PADDLE
                4 -> BALL
                else -> throw IllegalArgumentException("Unknown tile value: $value")
            }
        }

        /**
         * Converts a [BigInteger] value to its corresponding [Tile].
         * @param value A [BigInteger] representing a valid [Tile].
         * @return The [Tile] corresponding to [value].
         * @throws IllegalArgumentException If [value] has no corresponding [Tile].
         */
        fun from(value: BigInteger): Tile = from(value.toInt())
    }
}
