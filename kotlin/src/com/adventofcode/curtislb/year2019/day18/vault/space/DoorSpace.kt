package com.adventofcode.curtislb.year2019.day18.vault.space

/**
 * A [Space] representing a key in the vault, which a searcher may pass through if the corresponding key is collected.
 */
class DoorSpace(symbol: Char) : Space(symbol) {
    init {
        if (!symbol.isUpperCase()) {
            throw IllegalArgumentException("Door symbol '$symbol' should be an uppercase letter.")
        }
    }

    /**
     * The [Char] symbol of the key that corresponds to this door.
     */
    val keySymbol: Char
        get() = symbol.toLowerCase()

    override fun equals(other: Any?): Boolean = other is DoorSpace && other.symbol == symbol

    override fun hashCode(): Int = symbol.hashCode()
}
