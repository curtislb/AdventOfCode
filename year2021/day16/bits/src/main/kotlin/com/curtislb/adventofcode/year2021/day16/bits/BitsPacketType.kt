package com.curtislb.adventofcode.year2021.day16.bits

import com.curtislb.adventofcode.common.number.product

/**
 * A type of packet included as part of a BITS transmission.
 *
 * @param id An integer ID that uniquely defines this packet type.
 */
enum class BitsPacketType(val id: Int) {
    /**
     * A packet whose value is the sum of the values of its sub-packets.
     */
    SUM(id = 0),

    /**
     * A packet whose value is result of multiplying together the values of its sub-packets.
     */
    PRODUCT(id = 1),

    /**
     * A packet whose value is the minimum of the values of its sub-packets.
     */
    MINIMUM(id = 2),

    /**
     * A packet whose value is the maximum of the values of its sub-packets.
     */
    MAXIMUM(id = 3),

    /**
     * A packet that represents a literal value and contains no sub-packets.
     */
    LITERAL(id = 4),

    /**
     * A packet whose value is 1 if the value of the first sub-packet is greater than the value of
     * the second sub-packet; otherwise, its value is 0.
     */
    GREATER_THAN(id = 5),

    /**
     * A packet whose value is 1 if the value of the first sub-packet is less than the value of the
     * second sub-packet; otherwise, its value is 0.
     */
    LESS_THAN(id = 6),

    /**
     * A packet whose value is 1 if the value of the first sub-packet is equal to the value of the
     * second sub-packet; otherwise, its value is 0.
     */
    EQUAL_TO(id = 7);

    /**
     * Returns the result of applying the operation represented by this packet type to a list of
     * sub-packet [values].
     *
     * @throws UnsupportedOperationException If this packet type has no associated operation.
     */
    fun applyOperation(values: List<Long>): Long = when (this) {
        SUM -> values.sum()
        PRODUCT -> values.product()
        MINIMUM -> values.minOrNull() ?: 0L
        MAXIMUM -> values.maxOrNull() ?: 0L
        LITERAL -> throw UnsupportedOperationException("Not supported for packet type: $this")
        GREATER_THAN -> {
            checkBinaryOperatorValues(values)
            if (values[0] > values[1]) 1L else 0L
        }
        LESS_THAN -> {
            checkBinaryOperatorValues(values)
            if (values[0] < values[1]) 1L else 0L
        }
        EQUAL_TO -> {
            checkBinaryOperatorValues(values)
            if (values[0] == values[1]) 1L else 0L
        }
    }

    /**
     * Checks that a given list of sub-packet [values] is valid for a binary operator packet type.
     *
     * @throws IllegalArgumentException If a binary operator can't be applied to [values].
     */
    private fun checkBinaryOperatorValues(values: List<Long>) {
        require(values.size == 2) { "$this operation requires exactly 2 values: $values" }
    }

    companion object {
        /**
         * Returns the [BitsPacketType] corresponding to the given [id].
         *
         * @throws IllegalArgumentException If [id] has no corresponding [BitsPacketType].
         */
        fun fromID(id: Int): BitsPacketType = values().find { it.id == id }
            ?: throw IllegalArgumentException("No packet type for ID: $id")
    }
}
