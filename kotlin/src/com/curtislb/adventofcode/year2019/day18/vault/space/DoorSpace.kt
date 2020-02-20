package com.curtislb.adventofcode.year2019.day18.vault.space

/**
 * A space representing a key in the vault, which may be passed through once the corresponding key has been collected.
 */
class DoorSpace(override val symbol: Char) : Space {
    override val isOccupiable: Boolean = true

    init {
        if (!symbol.isUpperCase()) {
            throw IllegalArgumentException("Door symbol '$symbol' should be an uppercase letter.")
        }
    }

    /**
     * The symbol for the key that corresponds to this door.
     */
    val keySymbol: Char
        get() = symbol.toLowerCase()

    override fun equals(other: Any?): Boolean = other is DoorSpace && other.symbol == symbol

    override fun hashCode(): Int = symbol.hashCode()
}
