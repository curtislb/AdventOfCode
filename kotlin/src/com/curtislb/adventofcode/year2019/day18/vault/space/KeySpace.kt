package com.curtislb.adventofcode.year2019.day18.vault.space

/**
 * A space representing a key in the vault, which opens any corresponding door when collected.
 *
 * @throws IllegalArgumentException If [symbol] is not a lowercase letter.
 */
class KeySpace(override val symbol: Char) : Space {
    init {
        require(symbol.isLowerCase()) { "Symbol must be a lowercase letter: $symbol" }
    }

    override val isOccupiable: Boolean = true

    /**
     * The symbol for the door that corresponds to this key.
     */
    val doorSymbol: Char get() = symbol.toUpperCase()

    override fun equals(other: Any?): Boolean = other is KeySpace && other.symbol == symbol

    override fun hashCode(): Int = symbol.hashCode()
}
