package com.curtislb.adventofcode.year2020.day14.bitmask

import com.curtislb.adventofcode.common.collection.forEachNested
import com.curtislb.adventofcode.common.math.setBit
import com.curtislb.adventofcode.common.math.clearBit

/**
 * A bitmask program that uses the current bitmask to modify the memory address(es) in which values
 * are stored.
 *
 * When [writeMemory] is called, the current bitmask is applied to the given address according to
 * the following rules for each bit:
 *
 * - [Bit.ZERO]: Leaves the corresponding address bit unchanged.
 * - [Bit.ONE]: Overwrites the corresponding address bit with 1.
 * - [Bit.FLOATING]: Produces multiple addresses, with the corresponding address bit set to 0 and 1.
 *
 * @param programString A string representation of the program, containing one statement per line.
 */
class BitmaskProgramV2(programString: String) : BitmaskProgram(programString) {
    override fun writeMemory(addressString: String, valueString: String) {
        val addresses = applyBitmask(addressString.toLong())
        val value = valueString.toLong()
        for (address in addresses) {
            this[address] = value
        }
    }

    /**
     * Returns a list of memory addresses from applying the current bitmask to the given [address].
     */
    private fun applyBitmask(address: Long): List<Long> {
        var baseAddress = address
        for ((bit, bitIndices) in maskBits) {
            if (bit == Bit.ONE) {
                for (bitIndex in bitIndices) {
                    baseAddress = baseAddress.setBit(bitIndex)
                }
            }
        }

        val maskedAddresses = mutableListOf<Long>()
        val floatingIndices = maskBits.getOrElse(Bit.FLOATING) { emptyList() }
        listOf(false, true).forEachNested(floatingIndices.size) { indexedBitFlags ->
            var maskedAddress = baseAddress
            indexedBitFlags.forEachIndexed { index, (_, isOneBit) ->
                val floatingIndex = floatingIndices[index]
                maskedAddress = if (isOneBit) {
                    maskedAddress.setBit(floatingIndex)
                } else {
                    maskedAddress.clearBit(floatingIndex)
                }
            }
            maskedAddresses.add(maskedAddress)
            false // Don't stop iterating
        }

        return maskedAddresses
    }
}
