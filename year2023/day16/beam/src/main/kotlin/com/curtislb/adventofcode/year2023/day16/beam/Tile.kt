package com.curtislb.adventofcode.year2023.day16.beam

/**
 * A type of tile that may appear in a [BeamContraption] grid.
 *
 * @property symbol A character that uniquely identifies the type of tile.
 */
enum class Tile(val symbol: Char) {
    /**
     * An empty tile, which a beam can pass through without changing direction.
     */
    EMPTY('.'),

    /**
     * A mirror that reflects a rightward beam upward, a leftward beam downward, and vice versa.
     */
    MIRROR_NE('/'),

    /**
     * A mirror that reflects a rightward beam downward, a leftward beam upward, and vice versa.
     */
    MIRROR_SE('\\'),

    /**
     * A splitter that splits a horizontal beam into upward and downward beams.
     */
    SPLIT_NS('|'),

    /**
     * A splitter that splits a vertical beam into leftward and rightward beams.
     */
    SPLIT_EW('-');

    override fun toString(): String = symbol.toString()

    companion object {
        /**
         * Returns the [Tile] that corresponds to the given [char].
         *
         * @throws IllegalArgumentException If [char] has no corresponding [Tile].
         */
        fun fromChar(char: Char): Tile {
            return entries.firstOrNull { it.symbol == char }
                ?: throw IllegalArgumentException("Invalid tile char: $char")
        }
    }
}
