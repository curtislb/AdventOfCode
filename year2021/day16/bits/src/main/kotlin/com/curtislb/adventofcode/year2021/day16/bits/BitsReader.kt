package com.curtislb.adventofcode.year2021.day16.bits

import com.curtislb.adventofcode.common.math.setBit
import com.curtislb.adventofcode.common.math.testBit

/**
 * Interpreter that reads and evaluates a Buoyancy Interchange Transmission System (BITS) packet.
 *
 * A BITS packet contains a series of binary (or hexadecimal) values that are meant to be read in
 * groups as unsigned big-endian integer values. A packet has the following standard format:
 * - The first 3 bits represent the packet version.
 * - The following 3 bits represent the [BitsPacketType] of the packet.
 * - If the packet type is [BitsPacketType.LITERAL], the remaining bits are separated into 5-bit
 *   groups. The first bit of each group is 0 for the last group, or 1 otherwise. The remaining
 *   4 bits of each group are big-endian binary digits of the unsigned integer value of the packet.
 * - If the packet is of any other type, the next bit represents the [BitsLengthType], the next
 *   [BitsLengthType.bitCount] bits after that represent the length of all sub-packets, and the
 *   remaining bits represent the sub-packets themselves.
 *
 * @param packetString A hexadecimal string representing a BITS packet.
 */
class BitsReader(private val packetString: String) {
    /**
     * The current offset from the start of the BITS packet, indicating the next bit to read.
     */
    var offset: Int = 0
        private set

    /**
     * The total number of bits in the binary representation of the BITS packet.
     */
    val packetBitLength: Int = packetString.length * 4

    /**
     * The number of unread bits remaining in the BITS packet.
     */
    private val remainingBitCount: Int
        get() = packetBitLength - offset

    /**
     * Returns the result of evaluating the BITS packet starting at the current [offset], and
     * advances [offset] to the next bit after the current packet.
     *
     * The evaluation rules for a BITS packet are as follows:
     * - A [BitsPacketType.LITERAL] packet evaluates to the literal value it represents.
     * - Any other [BitsPacketType] packet evaluates to the result of applying its corresponding
     *   operation (via [BitsPacketType.applyOperation]) to the result(s) of recursively evaluating
     *   its direct sub-packet(s).
     */
    fun evaluatePacket(): Long {
        skipBits(BIT_COUNT_VERSION)
        val packetType = readPacketType()
        return if (packetType == BitsPacketType.LITERAL) {
            readLiteral()
        } else {
            val values = mutableListOf<Long>()
            readAndProcessSubPackets(readLengthType()) { values.add(evaluatePacket()) }
            packetType.applyOperation(values)
        }
    }

    /**
     * Returns the sum of the version of the BITS packet starting at the current [offset] and the
     * version(s) of its sub-packet(s), and advances [offset] to the next bit after the current
     * packet.
     */
    fun sumPacketVersions(): Int {
        val version = readBits(BIT_COUNT_VERSION)
        return if (readPacketType() == BitsPacketType.LITERAL) {
            skipLiteral()
            version
        } else {
            var subVersionSum = 0
            readAndProcessSubPackets(readLengthType()) { subVersionSum += sumPacketVersions() }
            version + subVersionSum
        }
    }

    /**
     * Restores this BITS reader to its original state, immediately after initialization.
     */
    fun reset() {
        offset = 0
    }

    /**
     * Returns the unsigned integer represented by the next [bitCount] bits starting at [offset],
     * and advances [offset] by [bitCount] bits.
     */
    private fun readBits(bitCount: Int): Int {
        require(bitCount >= 0) { "Bit count must be non-negative: $bitCount" }
        check(bitCount <= remainingBitCount) {
            "Bit count must not exceed remaining: $bitCount > $remainingBitCount."
        }

        // Return immediately if reading no bits
        if (bitCount == 0) {
            return 0
        }

        var result = 0
        var bitsToRead = bitCount
        while (bitsToRead > 0) {
            // Read necessary bits from the current hex digit
            val hexDigit = packetString[offset / BITS_PER_HEX_DIGIT].digitToInt(radix = 16)
            val startIndex = offset % BITS_PER_HEX_DIGIT
            val bitsToReadFromDigit = (BITS_PER_HEX_DIGIT - startIndex).coerceAtMost(bitsToRead)
            for (bitIndex in startIndex until (startIndex + bitsToReadFromDigit)) {
                result = result shl 1
                val testBitIndex = (BITS_PER_HEX_DIGIT - 1) - bitIndex
                if (hexDigit.testBit(testBitIndex)) {
                    result = result.setBit(0)
                }
            }

            // Advance to the next digit and decrement the number of read bits
            bitsToRead -= bitsToReadFromDigit
            offset += bitsToReadFromDigit
        }

        return result
    }

    /**
     * Advances [offset] by [bitCount] bits.
     */
    private fun skipBits(bitCount: Int) {
        offset = (offset + bitCount).coerceAtMost(packetBitLength)
    }

    /**
     * Returns the packet type represented by bits starting at the current bit [offset], and
     * advances [offset] to the next bit after the current packet type.
     */
    private fun readPacketType(): BitsPacketType =
        BitsPacketType.fromID(readBits(BIT_COUNT_PACKET_TYPE))

    /**
     * Returns the length type represented by bits starting at the current bit [offset], and
     * advances [offset] to the next bit after the current length type.
     */
    private fun readLengthType(): BitsLengthType =
        BitsLengthType.fromID(readBits(BIT_COUNT_LENGTH_TYPE))

    /**
     * Returns the literal value (within a [BitsPacketType.LITERAL] packet) represented by the bits
     * starting at the current bit [offset], and advances [offset] to the next bit after the current
     * literal value.
     */
    private fun readLiteral(): Long {
        val binaryLiteral = buildString {
            var isLastGroup = false
            while (!isLastGroup) {
                isLastGroup = readBits(1) == 0
                append(readBinaryLiteralGroup())
            }
        }
        return binaryLiteral.toLong(radix = 2)
    }

    /**
     * Advances the current bit [offset] to the next bit after the literal value (within a
     * [BitsPacketType.LITERAL] packet) represented by the bits starting at [offset].
     */
    private fun skipLiteral() {
        var isLastGroup = false
        while (!isLastGroup) {
            isLastGroup = readBits(1) == 0
            skipBits(BIT_COUNT_LITERAL_GROUP)
        }
    }

    /**
     * Returns the binary string representation of the 4-bit literal group and leading bit (within a
     * [BitsPacketType.LITERAL] packet) starting at the current bit [offset], and advances [offset]
     * to the next bit after the current literal group.
     */
    private fun readBinaryLiteralGroup(): String {
        val groupValue = readBits(BIT_COUNT_LITERAL_GROUP)
        return groupValue.toString(radix = 2).padStart(BIT_COUNT_LITERAL_GROUP, '0')
    }

    /**
     * Calls the [processSubPacket] function for each sub-packet within the BITS packet starting
     * at the current bit [offset], which has the given [lengthType], and advances [offset] to the
     * next bit after the current packet.
     *
     * The [processSubPacket] function is called with [offset] set to the first bit of each
     * sub-packet, and it must advance [offset] to the next bit after the current packet.
     */
    private fun readAndProcessSubPackets(lengthType: BitsLengthType, processSubPacket: () -> Unit) {
        when (lengthType) {
            BitsLengthType.BITS -> {
                val subPacketsBitCount = readBits(lengthType.bitCount)
                val endOffset = offset + subPacketsBitCount
                while (offset < endOffset) {
                    processSubPacket()
                }
            }

            BitsLengthType.SUB_PACKETS -> {
                val subPacketCount = readBits(lengthType.bitCount)
                repeat(subPacketCount) {
                    processSubPacket()
                }
            }
        }
    }

    companion object {
        /**
         * The number of bits in the binary representation of a [BitsLengthType] ID.
         */
        private const val BIT_COUNT_LENGTH_TYPE = 1

        /**
         * The number of bits in a single literal value group in a [BitsPacketType.LITERAL] packet.
         */
        private const val BIT_COUNT_LITERAL_GROUP = 4

        /**
         * The number of bits in the binary representation of a [BitsPacketType] ID.
         */
        private const val BIT_COUNT_PACKET_TYPE = 3

        /**
         * The number of bits in the binary representation of a BITS packet version.
         */
        private const val BIT_COUNT_VERSION = 3

        /**
         * The number of bits represented by a single hexadecimal digit.
         */
        private const val BITS_PER_HEX_DIGIT = 4
    }
}
