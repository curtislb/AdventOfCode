package com.adventofcode.curtislb.year2019.day18.vault.space

/**
 * TODO
 */
class KeySpace(symbol: Char) : Space(symbol) {
    init {
        if (!symbol.isLowerCase()) {
            throw IllegalArgumentException("Key symbol '$symbol' should be a lowercase letter.")
        }
    }
}
