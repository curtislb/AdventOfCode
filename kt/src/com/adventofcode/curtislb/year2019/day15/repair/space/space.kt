package com.adventofcode.curtislb.year2019.day15.repair.space

import java.math.BigInteger

/**
 * A type of space on the ship, as reported by the repair droid.
 */
abstract class Space {
    /**
     * An [Int] value that uniquely identifies this [Space].
     */
    abstract val value: Int

    /**
     * A [Char] symbol which is used to represent this [Space].
     */
    abstract val symbol: Char

    override fun toString() = symbol.toString()
}

/**
 * Converts an [Int] value to its corresponding [Space].
 * @receiver An [Int] representing a valid [Space].
 * @return The [Space] object corresponding to this [Int].
 * @throws IllegalArgumentException If called for an [Int] with no corresponding [Space].
 */
fun Int.toSpace(): Space {
    return when(this) {
        -1 -> DroidSpace
        0 -> EmptySpace
        1 -> WallSpace
        2 -> SystemSpace
        else -> throw IllegalArgumentException("Unknown space value: $this.")
    }
}

/**
 * Converts a [BigInteger] value to its corresponding [Space].
 * @receiver A [BigInteger] representing a valid [Space].
 * @return The [Space] object corresponding to this [BigInteger].
 * @throws IllegalArgumentException If called for a [BigInteger] with no corresponding [Space].
 */
fun BigInteger.toSpace(): Space = toInt().toSpace()
