package com.curtislb.adventofcode.year2020.day09.encryption

import com.curtislb.adventofcode.common.collection.FifoCache
import com.curtislb.adventofcode.common.comparison.minMaxOrNull
import com.curtislb.adventofcode.common.search.findNonNegativeSubarraySum
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
        // Search for a value (after the preamble) that meets the criteria
        val prevValues = FifoCache<Long>(preambleSize)
        for (value in values) {
            if (prevValues.isFull() && prevValues.findPairSum(value) == null) {
                return@lazy value
            }
            prevValues.add(value)
        }

        // Failed to find an invalid value
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
        // Search for a subset that meets the criteria
        firstInvalidNumber?.let { targetSum ->
            values.findNonNegativeSubarraySum(targetSum, minSize = 2)
                ?.minMaxOrNull()
                ?.let { return it.min + it.max }
        }

        // Failed to find an encryption weakness
        return null
    }
}
