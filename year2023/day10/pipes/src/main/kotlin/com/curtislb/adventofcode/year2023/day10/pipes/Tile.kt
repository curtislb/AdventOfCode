package com.curtislb.adventofcode.year2023.day10.pipes

import com.curtislb.adventofcode.common.geometry.Direction

/**
 * A type of space that may appear in a [PipeMaze] grid.
 *
 * @property symbol A character that uniquely identifies the tile.
 * @property directions Directions of all adjacent pipe tiles that may be connected to this one.
 */
enum class Tile(val symbol: Char, val directions: List<Direction>) {
    /**
     * A vertical pipe connecting north and south.
     */
    PIPE_NS(symbol = '|', directions = listOf(Direction.UP, Direction.DOWN)),

    /**
     * A horizontal pipe connecting east and west.
     */
    PIPE_EW(symbol = '-', directions = listOf(Direction.RIGHT, Direction.LEFT)),

    /**
     * A 90-degree bend connecting north and east.
     */
    PIPE_NE(symbol = 'L', directions = listOf(Direction.UP, Direction.RIGHT)),

    /**
     * A 90-degree bend connecting north and west.
     */
    PIPE_NW(symbol = 'J', directions = listOf(Direction.UP, Direction.LEFT)),

    /**
     * A 90-degree bend connecting south and west.
     */
    PIPE_SW(symbol = '7', directions = listOf(Direction.DOWN, Direction.LEFT)),

    /**
     * A 90-degree bend connecting south and east.
     */
    PIPE_SE(symbol = 'F', directions = listOf(Direction.DOWN, Direction.RIGHT)),

    /**
     * A ground tile, which contains no pipe.
     */
    GROUND(symbol = '.', directions = emptyList()),

    /**
     * The starting position of the main loop within a [PipeMaze].
     */
    START(symbol = 'S', directions = emptyList());

    /**
     * Returns `true` if the tile represents a specific type of pipe which connects adjacent tiles.
     */
    fun isPipe(): Boolean = when (this) {
        PIPE_NS, PIPE_EW, PIPE_NE, PIPE_NW, PIPE_SW, PIPE_SE -> true
        GROUND, START -> false
    }

    override fun toString(): String = symbol.toString()

    companion object {
        /**
         * Returns a [Tile] corresponding to the given symbol [char].
         *
         * @throws IllegalArgumentException If [char] has no corresponding [Tile].
         */
        fun fromChar(char: Char): Tile =
            entries.firstOrNull { it.symbol == char }
                ?: throw IllegalArgumentException("Invalid symbol character: $char")
    }
}
