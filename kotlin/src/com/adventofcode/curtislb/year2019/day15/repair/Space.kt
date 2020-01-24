package com.adventofcode.curtislb.year2019.day15.repair

import java.math.BigInteger

/**
 * A type of space that may appear on the repair droid's exploration grid.
 * @param symbol A [Char] symbol that is used to represent this [Space].
 */
enum class Space(val symbol: Char) {
    /**
     * A [Space] representing the current location of the repair droid.
     */
    DROID('D'),

    /**
     * A [Space] that has not yet been identified by the repair droid.
     */
    UNKNOWN(' '),

    /**
     * A [Space] representing a wall, which the repair droid cannot pass through.
     */
    WALL('#'),

    /**
     * A [Space] representing an empty position, which the repair droid can occupy.
     */
    EMPTY('.'),

    /**
     * A [Space] representing oxygen or the oxygen system, both of which the repair droid can pass through.
     */
    OXYGEN('O');

    companion object {
        /**
         * Converts an [Int] value to its corresponding [Space].
         * @param value An [Int] representing a valid [Space].
         * @return The [Space] corresponding to [value].
         * @throws IllegalArgumentException If [value] has no corresponding [Space].
         */
        fun from(value: Int): Space {
            return when(value) {
                -2 -> DROID
                -1 -> UNKNOWN
                0 -> WALL
                1 -> EMPTY
                2 -> OXYGEN
                else -> throw IllegalArgumentException("Unknown space value: $value")
            }
        }

        /**
         * Converts a [BigInteger] value to its corresponding [Space].
         * @param value A [BigInteger] representing a valid [Space].
         * @return The [Space] corresponding to [value].
         * @throws IllegalArgumentException If [value] has no corresponding [Space].
         */
        fun from(value: BigInteger): Space = from(value.toInt())
    }
}
