package com.curtislb.adventofcode.common.intcode.mode

import com.curtislb.adventofcode.common.intcode.Intcode
import java.math.BigInteger

/**
 * A parameter mode recognized by an [Intcode] program, which indicates how a given parameter's value should be
 * interpreted while processing an operation.
 */
interface Mode {
    /**
     * Returns the value in [intcode] for [parameter], as interpreted by this mode.
     */
    fun getValue(intcode: Intcode, parameter: BigInteger): BigInteger

    /**
     * Sets a new [value] for [parameter] in [intcode], as interpreted by this mode.
     */
    fun setValue(intcode: Intcode, parameter: BigInteger, value: BigInteger)

    companion object {
        /**
         * Returns the parameter mode corresponding to [value].
         *
         * @throws IllegalArgumentException If [value] has no corresponding mode.
         */
        fun from(value: Int): Mode {
            return when (value) {
                0 -> PositionMode
                1 -> ImmediateMode
                2 -> RelativeMode
                else -> throw IllegalArgumentException("Unknown parameter mode: $value")
            }
        }

        /**
         * Returns the parameter mode corresponding to [value].
         *
         * @throws IllegalArgumentException If [value] has no corresponding mode.
         */
        fun from(value: BigInteger): Mode = from(value.toInt())
    }
}
