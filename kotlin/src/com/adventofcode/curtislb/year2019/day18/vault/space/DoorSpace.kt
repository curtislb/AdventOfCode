package com.adventofcode.curtislb.year2019.day18.vault.space

/**
 * TODO
 */
class DoorSpace(symbol: Char) : Space(symbol) {
    init {
        if (!symbol.isUpperCase()) {
            throw IllegalArgumentException("Door symbol '$symbol' should be an uppercase letter.")
        }
    }
}
