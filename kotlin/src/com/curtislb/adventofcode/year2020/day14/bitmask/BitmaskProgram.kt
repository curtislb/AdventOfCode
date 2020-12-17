package com.curtislb.adventofcode.year2020.day14.bitmask

import com.curtislb.adventofcode.common.collection.forEachNested

/**
 * TODO
 */
class BitmaskProgram(private val programString: String, private val isVersion2: Boolean = false) {
    /**
     * TODO
     */
    private var maskBits: Map<Bit, List<Int>> = emptyMap()

    /**
     * TODO
     */
    private val memory: MutableMap<Long, Long> = mutableMapOf()

    /**
     * TODO
     */
    val nonzeroMemoryEntries: Set<Map.Entry<Long, Long>> get() = memory.entries

    /**
     * TODO
     */
    private var isDone: Boolean = false

    /**
     * TODO
     */
    operator fun get(address: Long): Long = memory.getOrDefault(address, 0L)

    /**
     * TODO
     */
    operator fun set(address: Long, value: Long) {
        if (value == 0L) {
            memory.remove(address)
        } else {
            memory[address] = value
        }
    }

    /**
     * TODO
     */
    fun run() {
        if (!isDone) {
            programString.trim().lines().forEach { line ->
                val (lvalue, expression) = line.split('=').map { it.trim() }
                assign(lvalue, expression)
            }
            isDone = true
        }
    }

    /**
     * TODO
     */
    private fun assign(lvalue: String, expression: String) {
        when {
            lvalue == "mask" -> assignMask(expression)
            lvalue.startsWith("mem") -> assignMemory(lvalue, expression)
            else -> throw IllegalArgumentException("Invalid assignment: $lvalue = $expression")
        }
    }

    /**
     * TODO
     */
    private fun assignMask(expression: String) {
        val newMaskBits = mutableMapOf<Bit, MutableList<Int>>()
        expression.forEachIndexed { index, char ->
            newMaskBits.getOrPut(Bit.from(char)) { mutableListOf() }.add(expression.lastIndex - index)
        }
        maskBits = newMaskBits
    }

    /**
     * TODO
     */
    private fun assignMemory(lvalue: String, expression: String) {
        if (isVersion2) {
            assignMemoryV2(lvalue, expression)
        } else {
            assignMemoryV1(lvalue, expression)
        }
    }

    /**
     * TODO
     */
    private fun assignMemoryV1(lvalue: String, expression: String) {
        val address = extractAddress(lvalue)
        val value = applyValueMask(expression.toLong())
        this[address] = value
    }

    /**
     * TODO
     */
    private fun assignMemoryV2(lvalue: String, expression: String) {
        val addresses = applyAddressMask(extractAddress(lvalue))
        val value = expression.toLong()
        addresses.forEach { this[it] = value }
    }

    /**
     * TODO
     */
    private fun applyValueMask(value: Long): Long {
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

    /**
     * TODO
     */
    private fun applyAddressMask(address: Long): List<Long> {
        var initialAddress = address
        maskBits.forEach { (bit, indices) ->
            if (bit == Bit.ONE) {
                indices.forEach { initialAddress = initialAddress.withOneBit(it) }
            }
        }

        val maskedAddresses = mutableListOf<Long>()
        val floatingIndices = maskBits[Bit.FLOATING] ?: emptyList()
        listOf(false, true).forEachNested(floatingIndices.size) { indexedBitFlags ->
            var maskedAddress = initialAddress
            indexedBitFlags.forEachIndexed { index, (_, isOneBit) ->
                val floatingIndex = floatingIndices[index]
                maskedAddress = if (isOneBit) {
                    maskedAddress.withOneBit(floatingIndex)
                } else {
                    maskedAddress.withZeroBit(floatingIndex)
                }
            }
            maskedAddresses.add(maskedAddress)
            false // Don't stop iterating.
        }
        return maskedAddresses
    }

    companion object {
        /**
         * TODO
         */
        private fun extractAddress(lvalue: String): Long = lvalue.filter { it.isDigit() }.toLong()

        /**
         * TODO
         */
        private fun Long.withZeroBit(bitIndex: Int): Long = this and (1L shl bitIndex).inv()

        /**
         * TODO
         */
        private fun Long.withOneBit(bitIndex: Int): Long = this or (1L shl bitIndex)
    }
}
