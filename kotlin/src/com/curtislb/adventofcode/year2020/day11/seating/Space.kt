package com.curtislb.adventofcode.year2020.day11.seating

/**
 * A type of space that may appear on the seat layout grid.
 *
 * @param symbol The character used to represent this space.
 */
enum class Space(val symbol: Char) {
    /**
     * A space representing a section of floor with no seat.
     */
    FLOOR('.'),

    /**
     * A space representing a seat that is currently empty.
     */
    EMPTY('L'),

    /**
     * A space representing a seat that is currently occupied.
     */
    OCCUPIED('#');

    companion object {
        /**
         * Returns the space corresponding to [symbol].
         *
         * @throws IllegalArgumentException If [symbol] has no corresponding space.
         */
        fun from(symbol: Char): Space {
            values().forEach { space ->
                if (space.symbol == symbol) {
                    return space
                }
            }
            throw IllegalArgumentException("Unknown space symbol: $symbol")
        }
    }
}
