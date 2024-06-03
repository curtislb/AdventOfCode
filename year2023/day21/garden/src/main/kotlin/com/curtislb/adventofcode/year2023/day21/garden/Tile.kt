package com.curtislb.adventofcode.year2023.day21.garden

/**
 * A type of tile that may appear in a [GardenPlotGrid].
 *
 * @property symbol A character that uniquely identifies the tile type.
 */
enum class Tile(val symbol: Char) {
    /**
     * A tile representing the garden plot from which the Elf will begin walking.
     */
    START('S'),

    /**
     * A tile representing a garden plot, onto which the Elf can step.
     */
    PLOT('.'),

    /**
     * A tile representing a rock, onto which the Elf can *not* step.
     */
    ROCK('#');

    companion object {
        /**
         * Returns the [Tile] that corresponds to the given [char].
         *
         * @throws IllegalArgumentException If [char] has no corresponding tile.
         */
        fun fromChar(char: Char): Tile {
            return entries.firstOrNull { it.symbol == char }
                ?: throw IllegalArgumentException("Invalid tile char: $char")
        }
    }
}
