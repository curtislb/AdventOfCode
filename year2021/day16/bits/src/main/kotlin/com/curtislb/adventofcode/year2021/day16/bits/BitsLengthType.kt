package com.curtislb.adventofcode.year2021.day16.bits

/**
 * An interpretation mode for a length value within a BITS packet.
 *
 * @param id An integer ID that uniquely defines this length type.
 * @param bitCount The number of bits used to represent the length value for this length type.
 */
enum class BitsLengthType(val id: Int, val bitCount: Int) {
    /**
     * A length type indicating that the next [bitCount] bits represent the total length in bits of
     * the sub-packets contained by this packet.
     */
    BITS(id = 0, bitCount = 15),

    /**
     * A length type indicating that the next [bitCount] bits represent the number of sub-packets
     * immediately contained by this packet.
     */
    SUB_PACKETS(id = 1, bitCount = 11);

    companion object {
        /**
         * Returns the [BitsLengthType] corresponding to the given [id].
         *
         * @throws IllegalArgumentException If [id] has no corresponding [BitsLengthType].
         */
        fun fromID(id: Int): BitsLengthType = values().find { it.id == id }
            ?: throw IllegalArgumentException("No length type for ID: $id")
    }
}
