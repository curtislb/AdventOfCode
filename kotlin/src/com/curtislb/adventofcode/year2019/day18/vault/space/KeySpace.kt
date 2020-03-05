package com.curtislb.adventofcode.year2019.day18.vault.space

/**
 * A space representing a key in the vault, which opens any corresponding door when collected.
 */
class KeySpace(override val symbol: Char) : Space {
    override val isOccupiable: Boolean = true

    init {
        if (!symbol.isLowerCase()) {
            throw IllegalArgumentException("Key symbol '$symbol' should be a lowercase letter.")
        }
    }

    /**
     * The symbol for the door that corresponds to this key.
     */
    val doorSymbol: Char get() = symbol.toUpperCase()

    override fun equals(other: Any?): Boolean = other is KeySpace && other.symbol == symbol

    override fun hashCode(): Int = symbol.hashCode()
}
