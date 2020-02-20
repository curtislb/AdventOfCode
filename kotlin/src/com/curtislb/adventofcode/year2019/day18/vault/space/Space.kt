package com.curtislb.adventofcode.year2019.day18.vault.space

/**
 * A type of space that may appear on the grid of tunnels within the vault.
 */
interface Space {
    /**
     * The character that is used to represent this space.
     */
    val symbol: Char

    /**
     * Whether this space may be occupied by a searcher.
     */
    val isOccupiable: Boolean

    companion object {
        /**
         * Returns the space corresponding to [symbol].
         *
         * @throws IllegalArgumentException If [symbol] has no corresponding space.
         */
        fun from(symbol: Char): Space {
            return when {
                symbol == OpenSpace.symbol -> OpenSpace
                symbol == WallSpace.symbol -> WallSpace
                symbol == EntranceSpace.symbol -> EntranceSpace
                symbol.isLowerCase() -> KeySpace(symbol)
                symbol.isUpperCase() -> DoorSpace(symbol)
                else -> throw IllegalArgumentException("Invalid space character: $symbol")
            }
        }
    }
}
