package com.curtislb.adventofcode.year2020.day14.bitmask

import com.curtislb.adventofcode.common.math.setBit
import com.curtislb.adventofcode.common.math.clearBit

/**
 * A bitmask program that uses the current bitmask to modify the values stored to memory addresses.
 *
 * When [writeMemory] is called, the current bitmask is applied to the given value according to the
 * following rules for each bit:
 *
 * - [Bit.ZERO]: Overwrites the corresponding value bit with 0.
 * - [Bit.ONE]: Overwrites the corresponding value bit with 1.
 * - [Bit.FLOATING]: Leaves the corresponding value bit unchanged.
 *
 * @param programString A string representation of the program, containing one statement per line.
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
                Bit.ZERO -> indices.forEach { maskedValue = maskedValue.clearBit(it) }
                Bit.ONE -> indices.forEach { maskedValue = maskedValue.setBit(it) }
                else -> Unit
            }
        }
        return maskedValue
    }
}
