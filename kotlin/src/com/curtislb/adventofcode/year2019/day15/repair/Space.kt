package com.curtislb.adventofcode.year2019.day15.repair

import java.math.BigInteger

/**
 * A type of space that may appear on the repair droid's exploration grid.
 *
 * @param symbol The character that is used to represent this space.
 */
enum class Space(val symbol: Char) {
    /**
     * A space representing the current location of the repair droid.
     */
    DROID('D'),

    /**
     * A space that has not yet been identified by the repair droid.
     */
    UNKNOWN(' '),

    /**
     * A space representing a wall, which the repair droid cannot pass through.
     */
    WALL('#'),

    /**
     * A space representing an empty position, which the repair droid can occupy.
     */
    EMPTY('.'),

    /**
     * A space representing the oxygen system, which the repair droid can pass through.
     */
    OXYGEN('O');

    companion object {
        /**
         * Returns the space corresponding to [value].
         *
         * @throws IllegalArgumentException If [value] has no corresponding space.
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
         * Returns the space corresponding to [value].
         *
         * @throws IllegalArgumentException If [value] has no corresponding space.
         */
        fun from(value: BigInteger): Space = from(value.toInt())
    }
}
