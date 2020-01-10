package com.adventofcode.curtislb.year2019.day15.repair

import java.math.BigInteger

/**
 * A type of space that may appear on the repair droid's exploration grid.
 * A [Char] symbol that is used to represent this [Space].
 */
enum class Space(val symbol: Char) {
    /**
     * A space representing the current location of the repair droid.
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
     * A [Space] representing an empty position, which the repair droid can pass through.
     */
    EMPTY('.'),

    /**
     * A [Space] representing oxygen, which originates from the oxygen system's location.
     */
    OXYGEN('O')
}

/**
 * Converts an [Int] value to its corresponding [Space].
 * @receiver An [Int] representing a valid [Space].
 * @return The [Space] object corresponding to this [Int].
 * @throws IllegalArgumentException If called for an [Int] with no corresponding [Space].
 */
fun Int.toSpace(): Space {
    return when(this) {
        -2 -> Space.DROID
        -1 -> Space.UNKNOWN
        0 -> Space.WALL
        1 -> Space.EMPTY
        2 -> Space.OXYGEN
        else -> throw IllegalArgumentException("Unknown space value: $this")
    }
}

/**
 * Converts a [BigInteger] value to its corresponding [Space].
 * @receiver A [BigInteger] representing a valid [Space].
 * @return The [Space] object corresponding to this [BigInteger].
 * @throws IllegalArgumentException If called for a [BigInteger] with no corresponding [Space].
 */
fun BigInteger.toSpace(): Space = toInt().toSpace()
