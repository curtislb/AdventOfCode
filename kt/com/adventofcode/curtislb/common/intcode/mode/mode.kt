package com.adventofcode.curtislb.common.intcode.mode

import com.adventofcode.curtislb.common.intcode.Intcode
import java.math.BigInteger

/**
 * A parameter mode recognized by an [Intcode] program.
 *
 * This mode indicates how a given parameter's value should be interpreted while processing an operation.
 */
interface Mode {
    /**
     * Gets the value represented by a parameter in this [Mode].
     * @param intcode The currently running [Intcode] program.
     * @param parameter An operation parameter specifying which value to get.
     * @return The value in [intcode] of [parameter], as interpreted by this [Mode].
     */
    fun getValue(intcode: Intcode, parameter: BigInteger): BigInteger

    /**
     * Sets the value represented by a parameter in this [Mode].
     * @param intcode The currently running [Intcode] program.
     * @param parameter An operation parameter specifying which value to set.
     * @param value The new value to be set in [intcode] for [parameter], as interpreted by this [Mode].
     */
    fun setValue(intcode: Intcode, parameter: BigInteger, value: BigInteger)
}

/**
 * Converts an [Int] representing an [Intcode] parameter mode to its corresponding [Mode].
 * @receiver An [Int] representing a valid parameter mode.
 * @return The [Mode] object corresponding to this [Int].
 * @throws IllegalArgumentException If called for an [Int] with no corresponding [Mode].
 */
fun Int.toMode(): Mode {
    return when (this) {
        0 -> PositionMode
        1 -> ImmediateMode
        2 -> RelativeMode
        else -> throw IllegalArgumentException("Unknown parameter mode: $this.")
    }
}

/**
 * Converts a [BigInteger] representing an [Intcode] parameter mode to its corresponding [Mode].
 * @receiver A [BigInteger] representing a valid parameter mode.
 * @return The [Mode] object corresponding to this [BigInteger].
 * @throws IllegalArgumentException If called for a [BigInteger] with no corresponding [Mode].
 */
fun BigInteger.toMode(): Mode = this.toInt().toMode()
