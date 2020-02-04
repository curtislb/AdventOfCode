package com.adventofcode.curtislb.year2019.day18.vault.space

/**
 * A [Space] representing a key in the vault, which a searcher may use to open a corresponding door.
 */
class KeySpace(symbol: Char) : Space(symbol) {
    init {
        if (!symbol.isLowerCase()) {
            throw IllegalArgumentException("Key symbol '$symbol' should be a lowercase letter.")
        }
    }

    /**
     * The [Char] symbol of the door that corresponds to this key.
     */
    val doorSymbol: Char
        get() = symbol.toUpperCase()

    override fun equals(other: Any?): Boolean = other is KeySpace && other.symbol == symbol

    override fun hashCode(): Int = symbol.hashCode()
}
