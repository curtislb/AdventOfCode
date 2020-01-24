package com.adventofcode.curtislb.year2019.day17.ascii

import java.math.BigInteger

/**
 * A type of space that may appear on the scaffold grid, as reported by the [ASCII].
 * @param symbol An ASCII [Char] symbol that is used to represent this [Space].
 */
enum class Space(val symbol: Char) {
    /**
     * A [Space] representing an empty position, with no robot or scaffold.
     */
    EMPTY('.'),

    /**
     * A [Space] representing a part of the ship's scaffold.
     */
    SCAFFOLD('#'),

    /**
     * A [Space] representing the vacuum robot, which is on the scaffold and facing up.
     */
    ROBOT_UP('^'),

    /**
     * A [Space] representing the vacuum robot, which is on the scaffold and facing right.
     */
    ROBOT_RIGHT('>'),

    /**
     * A [Space] representing the vacuum robot, which is on the scaffold and facing down.
     */
    ROBOT_DOWN('v'),

    /**
     * A [Space] representing the vacuum robot, which is on the scaffold and facing left.
     */
    ROBOT_LEFT('<'),

    /**
     * A [Space] representing the vacuum robot, which is tumbling uncontrollably through space.
     */
    ROBOT_FELL('X');

    companion object {
        /**
         * Converts a [Char] to its corresponding [Space].
         * @param char An ASCII [Char] representing a valid [Space].
         * @return The [Space] corresponding to [char].
         * @throws IllegalArgumentException If [char] has no corresponding [Space].
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
         * Converts a [BigInteger] value to its corresponding [Space].
         * @param value A [BigInteger] representing a valid [Space].
         * @return The [Space] corresponding to [value].
         * @throws IllegalArgumentException If [value] has no corresponding [Space].
         */
        fun from(value: BigInteger): Space = from(value.toInt().toChar())
    }
}
