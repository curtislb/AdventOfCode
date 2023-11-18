package com.curtislb.adventofcode.year2020.day14.bitmask

/**
 * Bit values that may be present in a [BitmaskProgram].
 *
 * @param symbol The character used to represent this bit.
 */
enum class Bit(val symbol: Char) {
    /**
     * A bit value represented by `0`. Used to overwrite value bits in [BitmaskProgramV1] or to
     * leave address bits unchanged in [BitmaskProgramV2].
     */
    ZERO('0'),

    /**
     * A bit value represented by `1`. Used to overwrite value bits in [BitmaskProgramV1] or
     * to overwrite address bits in [BitmaskProgramV2].
     */
    ONE('1'),

    /**
     * A bit value represented by `X`. Used to leave value bits unchanged in [BitmaskProgramV1] or
     * to produce addresses with both [ZERO] and [ONE] bits in [BitmaskProgramV2].
     */
    FLOATING('X');

    companion object {
        /**
         * Returns the [Bit] corresponding to [symbol].
         *
         * @throws IllegalArgumentException If [symbol] has no corresponding bit.
         */
        fun from(symbol: Char): Bit {
            return entries.find { it.symbol == symbol }
                ?: throw IllegalArgumentException("Unknown bit symbol: $symbol")
        }
    }
}
