package com.curtislb.adventofcode.year2019.day17.ascii

import java.math.BigInteger

/**
 * A type of space that may appear on the scaffold grid, as reported by the [ASCII].
 *
 * @param symbol The character that is used to represent this space.
 * @param isSafe Whether the vacuum robot can safely occupy this space.
 */
enum class Space(val symbol: Char, val isSafe: Boolean) {
    /**
     * A space representing an empty position, with no robot or scaffold.
     */
    EMPTY(symbol = '.', isSafe = false),

    /**
     * A space representing a part of the ship's scaffold.
     */
    SCAFFOLD(symbol = '#', isSafe = true),

    /**
     * A space representing the vacuum robot, which is on the scaffold and facing up.
     */
    ROBOT_UP(symbol = '^', isSafe = true),

    /**
     * A space representing the vacuum robot, which is on the scaffold and facing right.
     */
    ROBOT_RIGHT(symbol = '>', isSafe = true),

    /**
     * A space representing the vacuum robot, which is on the scaffold and facing down.
     */
    ROBOT_DOWN(symbol = 'v', isSafe = true),

    /**
     * A space representing the vacuum robot, which is on the scaffold and facing left.
     */
    ROBOT_LEFT(symbol = '<', isSafe = true),

    /**
     * A space representing the vacuum robot, which is tumbling uncontrollably through space.
     */
    ROBOT_FELL(symbol = 'X', isSafe = false);

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
            throw IllegalArgumentException("Unknown space char: $symbol")
        }

        /**
         * Returns the space corresponding to [value].
         *
         * @throws IllegalArgumentException If [value] has no corresponding space.
         */
        fun from(value: BigInteger): Space = from(value.toInt().toChar())
    }
}
