package com.curtislb.adventofcode.year2020.day14.bitmask

import com.curtislb.adventofcode.common.math.withOneBit
import com.curtislb.adventofcode.common.math.withZeroBit

/**
 * A bitmask program that uses the current bitmask to modify the values stored to memory addresses.
 *
 * When [writeMemory] is called, the current bitmask is applied to the given value according to the following rules for
 * each bit:
 * - [Bit.ZERO] - Overwrites the corresponding value bit with 0.
 * - [Bit.ONE] - Overwrites the corresponding value bit with 1.
 * - [Bit.FLOATING] - Leaves the corresponding value bit unchanged.
 *
 * @param programString A string representation of the program, consisting of one statement per line.
 */
class BitmaskProgramV1(programString: String) : BitmaskProgram(programString) {
    override fun writeMemory(addressString: String, valueString: String) {
        val address = addressString.toLong()
        val value = applyBitmask(valueString.toLong())
        this[address] = value
    }

    /**
     * Returns the value that results from applying the current bitmask to the given [value].
     */
    private fun applyBitmask(value: Long): Long {
        var maskedValue = value
        maskBits.forEach { (bit, indices) ->
            when (bit) {
                Bit.ZERO -> indices.forEach { maskedValue = maskedValue.withZeroBit(it) }
                Bit.ONE ->  indices.forEach { maskedValue = maskedValue.withOneBit(it) }
                else -> Unit
            }
        }
        return maskedValue
    }
}
