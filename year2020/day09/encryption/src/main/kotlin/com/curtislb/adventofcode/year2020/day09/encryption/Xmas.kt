package com.curtislb.adventofcode.year2020.day09.encryption

import com.curtislb.adventofcode.common.collection.FifoCache
import com.curtislb.adventofcode.common.search.findContiguousSubsetSum
import com.curtislb.adventofcode.common.search.findPairSum

/**
 * Data that has been encrypted by the eXchange-Masking Addition System (XMAS).
 *
 * @param values A list of values representing the encrypted data.
 * @param preambleSize The size of the preamble at the start of [values].
 */
class Xmas(private val values: List<Long>, private val preambleSize: Int) {
    /**
     * The first value after the preamble that is not the sum of [preambleSize] previous numbers.
     */
    val firstInvalidNumber: Long? by lazy {
        // Search for a value (after the preamble) that meets the criteria.
        val prevValues = FifoCache<Long>(preambleSize)
        values.forEach { value ->
            if (prevValues.isFull() && prevValues.findPairSum(value) == null) {
                return@lazy value
            }
            prevValues.add(value)
        }

        // Failed to find an invalid value.
        null
    }

    /**
     * Returns the encrypted data value at the given [index].
     */
    operator fun get(index: Int) = values[index]

    /**
     * Returns the encryption weakness for the encrypted data.
     *
     * Let `S` be the first contiguous subset of at least two numbers in [values] whose sum is
     * [firstInvalidNumber]. The encryption weakness is the sum of the minimum and maximum value of
     * `S`, or `null` if `S` does not exist.
     */
    fun findEncryptionWeakness(): Long? {
        // Search for a contiguous subset that meets the criteria.
        firstInvalidNumber?.let { targetSum ->
            val subset = values.findContiguousSubsetSum(targetSum, minSize = 2)
            if (subset != null) {
                val minValue = subset.minOrNull()
                val maxValue = subset.maxOrNull()
                if (minValue != null && maxValue != null) {
                    return minValue + maxValue
                }
            }
        }

        // Failed to find an encryption weakness.
        return null
    }
}
