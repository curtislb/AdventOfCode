package com.curtislb.adventofcode.year2021.day16.bits

import com.curtislb.adventofcode.common.math.digitsToInt
import com.curtislb.adventofcode.common.math.product
import com.curtislb.adventofcode.common.math.testBit

/**
 * TODO
 */
class BitsReader(private val packetString: String) {
    private val packetBitLength: Int = packetString.length * 4

    private var offset: Int = 0

    val remainingBitCount: Int get() = packetBitLength - offset

    fun nextBits(bitCount: Int): Int {
        require(bitCount >= 0) { "Bit count must be non-negative: $bitCount" }
        check(bitCount <= remainingBitCount) {
            "Bit count ($bitCount) can't be larger than remaining bit count ($remainingBitCount)."
        }

        if (bitCount == 0) {
            return 0
        }

        var result = 0
        var bitsToRead = bitCount
        while (bitsToRead > 0) {
            val hexDigit = packetString[offset / 4].digitToInt(radix = 16)
            val startIndex = offset % 4
            val bitsToReadFromDigit = (4 - startIndex).coerceAtMost(bitsToRead)
            for (bitIndex in startIndex until (startIndex + bitsToReadFromDigit)) {
                result = result shl 1
                if (hexDigit.testBit(3 - bitIndex)) {
                    result++
                }
            }

            bitsToRead -= bitsToReadFromDigit
            offset += bitsToReadFromDigit
        }

        return result
    }

    fun skipBits(bitCount: Int) {
        offset = (offset + bitCount).coerceAtMost(packetBitLength)
    }

    fun reset() {
        offset = 0
    }

    fun sumPacketVersions(): Int {
        val version = nextBits(3)
        val packetType = nextBits(3)
        return if (packetType == 4) {
            var isLastGroup = false
            while (!isLastGroup) {
                isLastGroup = nextBits(1) == 0
                skipBits(4)
            }
            version
        } else {
            var subVersionSum = 0
            when (val lengthType = nextBits(1)) {
                0 -> {
                    val subPacketsBitCount = nextBits(15)
                    val endOffset = offset + subPacketsBitCount
                    while (offset < endOffset) {
                        subVersionSum += sumPacketVersions()
                    }
                }

                1 -> {
                    val subPacketsCount = nextBits(11)
                    repeat(subPacketsCount) {
                        subVersionSum += sumPacketVersions()
                    }
                }

                else -> throw IllegalStateException("Unrecognized length type ID: $lengthType")
            }
            version + subVersionSum
        }
    }

    fun evaluatePacket(): Long {
        skipBits(3)
        val packetType = nextBits(3)
        return if (packetType == 4) {
            buildString {
                var isLastGroup = false
                while (!isLastGroup) {
                    isLastGroup = nextBits(1) == 0
                    append(nextBits(4).toString(radix = 2).padStart(4, '0'))
                }
            }.toLong(radix = 2)
        } else {
            val subPacketValues = mutableListOf<Long>()
            when (val lengthType = nextBits(1)) {
                0 -> {
                    val subPacketsBitCount = nextBits(15)
                    val endOffset = offset + subPacketsBitCount
                    while (offset < endOffset) {
                        subPacketValues.add(evaluatePacket())
                    }
                }

                1 -> {
                    val subPacketsCount = nextBits(11)
                    repeat(subPacketsCount) {
                        subPacketValues.add(evaluatePacket())
                    }
                }

                else -> throw IllegalStateException("Unrecognized length type ID: $lengthType")
            }
            val operator = packetType.toOperator()
            operator(subPacketValues)
        }
    }

    companion object {
        private val SUM: (List<Long>) -> Long = { it.sum() }
        private val PRODUCT: (List<Long>) -> Long = { it.product() }
        private val MINIMUM: (List<Long>) -> Long = { it.minOrNull() ?: 0L }
        private val MAXIMUM: (List<Long>) -> Long = { it.maxOrNull() ?: 0L }
        private val GREATER_THAN: (List<Long>) -> Long = { arguments ->
            val (valueA, valueB) = arguments
            if (valueA > valueB) 1L else 0L
        }
        private val LESS_THAN: (List<Long>) -> Long = { arguments ->
            val (valueA, valueB) = arguments
            if (valueA < valueB) 1L else 0L
        }
        private val EQUAL_TO: (List<Long>) -> Long = { arguments ->
            val (valueA, valueB) = arguments
            if (valueA == valueB) 1L else 0L
        }

        private fun Int.toOperator(): (arguments: List<Long>) -> Long = when (this) {
            0 -> SUM
            1 -> PRODUCT
            2 -> MINIMUM
            3 -> MAXIMUM
            5 -> GREATER_THAN
            6 -> LESS_THAN
            7 -> EQUAL_TO
            else -> throw IllegalArgumentException("No operator for packet type ID: $this")
        }
    }
}
