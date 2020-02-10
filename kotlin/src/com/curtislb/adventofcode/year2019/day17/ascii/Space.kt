package com.curtislb.adventofcode.year2019.day17.ascii

import java.math.BigInteger

/**
 * A type of space that may appear on the scaffold grid, as reported by the [ASCII].
 */
enum class Space(val symbol: Char) {
    /**
     * A space representing an empty position, with no robot or scaffold.
     */
    EMPTY('.'),

    /**
     * A space representing a part of the ship's scaffold.
     */
    SCAFFOLD('#'),

    /**
     * A space representing the vacuum robot, which is on the scaffold and facing up.
     */
    ROBOT_UP('^'),

    /**
     * A space representing the vacuum robot, which is on the scaffold and facing right.
     */
    ROBOT_RIGHT('>'),

    /**
     * A space representing the vacuum robot, which is on the scaffold and facing down.
     */
    ROBOT_DOWN('v'),

    /**
     * A space representing the vacuum robot, which is on the scaffold and facing left.
     */
    ROBOT_LEFT('<'),

    /**
     * A space representing the vacuum robot, which is tumbling uncontrollably through space.
     */
    ROBOT_FELL('X');

    companion object {
        /**
         * Returns the space corresponding to [char].
         *
         * @throws IllegalArgumentException If [char] has no corresponding space.
         */
        fun from(char: Char): Space {
            values().forEach { space ->
                if (space.symbol == char) {
                    return space
                }
            }
            throw IllegalArgumentException("Unknown space char: $char")
        }

        /**
         * Returns the space corresponding to [value].
         *
         * @throws IllegalArgumentException If [value] has no corresponding space.
         */
        fun from(value: BigInteger): Space = from(value.toInt().toChar())
    }
}
