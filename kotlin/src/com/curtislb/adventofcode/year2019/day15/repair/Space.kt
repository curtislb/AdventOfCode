package com.curtislb.adventofcode.year2019.day15.repair

import java.math.BigInteger

/**
 * A type of space that may appear on the repair droid's exploration grid.
 *
 * @param symbol The character that is used to represent this space.
 * @param isOccupiable Whether the repair droid can occupy this space, or `null` if unknown.
 */
enum class Space(val symbol: Char, val isOccupiable: Boolean?) {
    /**
     * A space representing the current location of the repair droid.
     */
    DROID(symbol = 'D', isOccupiable = true),

    /**
     * A space that has not yet been identified by the repair droid.
     */
    UNKNOWN(symbol = ' ', isOccupiable = null),

    /**
     * A space representing a wall, which the repair droid cannot pass through.
     */
    WALL(symbol = '#', isOccupiable = false),

    /**
     * A space representing an open position, which the repair droid can occupy.
     */
    OPEN(symbol = '.', isOccupiable = true),

    /**
     * A space representing the oxygen system, which the repair droid can pass through.
     */
    OXYGEN(symbol = 'O', isOccupiable = true);

    companion object {
        /**
         * Returns the space corresponding to [value].
         *
         * @throws IllegalArgumentException If [value] has no corresponding space.
         */
        fun from(value: Int): Space = when(value) {
            -2 -> DROID
            -1 -> UNKNOWN
            0 -> WALL
            1 -> OPEN
            2 -> OXYGEN
            else -> throw IllegalArgumentException("Unknown space value: $value")
        }

        /**
         * Returns the space corresponding to [value].
         *
         * @throws IllegalArgumentException If [value] has no corresponding space.
         */
        fun from(value: BigInteger): Space = from(value.toInt())
    }
}
