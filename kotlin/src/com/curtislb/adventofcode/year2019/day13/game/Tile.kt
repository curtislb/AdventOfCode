package com.curtislb.adventofcode.year2019.day13.game

import java.math.BigInteger

/**
 * A type of space that may appear on the game board.
 *
 * @param symbol The character used to represent this tile.
 */
enum class Tile(val symbol: Char) {
    /**
     * A tile representing an empty space on the board.
     */
    EMPTY('.'),

    /**
     * A tile representing a wall, which is indestructible and can't be moved.
     */
    WALL('#'),

    /**
     * A tile representing a block, which can be broken by a ball.
     */
    BLOCK('X'),

    /**
     * A tile representing a paddle, which is indestructible and can be moved horizontally by the player.
     */
    PADDLE('='),

    /**
     * A tile representing a ball, which moves diagonally and bounces off objects.
     */
    BALL('O');

    companion object {
        /**
         * Returns the tile corresponding to [value].
         *
         * @throws IllegalArgumentException If [value] has no corresponding tile.
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
         * Returns the tile corresponding to [value].
         *
         * @throws IllegalArgumentException If [value] has no corresponding tile.
         */
        fun from(value: BigInteger): Tile = from(value.toInt())
    }
}
