package com.curtislb.adventofcode.year2020.day14.bitmask

/**
 * TODO
 */
enum class Bit(val symbol: Char) {
    /**
     * TODO
     */
    ZERO('0'),

    /**
     * TODO
     */
    ONE('1'),

    /**
     * TODO
     */
    FLOATING('X');

    companion object {
        /**
         * TODO
         */
        fun from(symbol: Char): Bit {
            values().forEach { bit ->
                if (bit.symbol == symbol) {
                    return bit
                }
            }
            throw IllegalArgumentException("Invalid bit symbol: $symbol")
        }
    }
}
