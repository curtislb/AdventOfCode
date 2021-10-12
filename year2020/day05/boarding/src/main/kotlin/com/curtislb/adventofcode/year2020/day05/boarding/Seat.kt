package com.curtislb.adventofcode.year2020.day05.boarding

import com.curtislb.adventofcode.common.math.pow

/**
 * An airplane seat with an associated ID and position in the plane.
 *
 * @param id An integer ID that uniquely defines this seat.
 */
data class Seat(val id: Int) {
    /**
     * The row index of this seat in the airplane.
     */
    val rowIndex: Int get() = id / NUM_COLUMNS

    /**
     * The column index of this seat in the airplane.
     */
    val colIndex: Int get() = id % NUM_COLUMNS

    /**
     * An airplane seat with an associated ID and position in the plane.
     *
     * @param rowIndex The row index of this seat in the airplane.
     * @param colIndex The column index of this seat in the airplane.
     */
    constructor(rowIndex: Int, colIndex: Int) : this(rowIndex * NUM_COLUMNS + colIndex)

    companion object {
        /**
         * The number of binary partitions needed to represent a seating row.
         */
        private const val NUM_ROW_PARTITIONS = 7

        /**
         * The number of binary partitions needed to represent a seating column.
         */
        private const val NUM_COL_PARTITIONS = 3

        /**
         * The number of seating columns in an airplane.
         */
        private val NUM_COLUMNS = 2.pow(NUM_COL_PARTITIONS)

        /**
         * Returns the seat corresponding to the given [ticket] string.
         */
        fun from(ticket: String): Seat {
            // Get the row index from the beginning of the ticket.
            val rowIndex = ticket.substring(0 until NUM_ROW_PARTITIONS).map { char ->
                when (char) {
                    'F' -> false
                    'B' -> true
                    else -> throw IllegalArgumentException(
                        "Invalid row partition character: $char"
                    )
                }
            }.toPartitionIndex()

            // Get the column index from the end of the ticket.
            val colIndex = ticket.substring(NUM_ROW_PARTITIONS).map { char ->
                when (char) {
                    'L' -> false
                    'R' -> true
                    else -> throw IllegalArgumentException(
                        "Invalid column partition character: $char"
                    )
                }
            }.toPartitionIndex()

            return Seat(rowIndex, colIndex)
        }

        /**
         * Returns the index corresponding to a list of boolean partition values.
         *
         * Initially, the range `0..(2^size)` represents all possible partition indices. From here,
         * each boolean value indicates whether to keep the lower (`false`) or upper (`true`) half
         * of possible indices after partitioning, until only one index remains.
         */
        private fun List<Boolean>.toPartitionIndex(): Int {
            var minIndexInclusive = 0
            var maxIndexExclusive = 2.pow(size)
            forEach { isRightPartition ->
                val midIndex = minIndexInclusive + (maxIndexExclusive - minIndexInclusive) / 2
                if (isRightPartition) {
                    minIndexInclusive = midIndex
                } else {
                    maxIndexExclusive = midIndex
                }
            }
            return minIndexInclusive
        }
    }
}
