package com.curtislb.adventofcode.year2019.day20.maze.space

/**
 * A type of space that may appear on the maze grid.
 */
interface Space {
    /**
     * The character that is used to represent this space.
     */
    val symbol: Char

    /**
     * Whether this space can be occupied.
     */
    val isOccupiable: Boolean

    companion object {
        /**
         * Returns the space corresponding to [symbol].
         *
         * @throws IllegalArgumentException If [symbol] has no corresponding space.
         */
        fun from(symbol: Char): Space = when (symbol) {
            EmptySpace.symbol -> EmptySpace
            OpenSpace.symbol -> OpenSpace
            WallSpace.symbol -> WallSpace
            else -> throw IllegalArgumentException("Unknown space char: $symbol")
        }
    }
}
