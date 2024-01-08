package com.curtislb.adventofcode.year2023.day15.hash

import com.curtislb.adventofcode.common.number.modMultiply

/**
 * An implementation of the HASH algorithm, which converts any string to an integer in the range
 * `0..<modulus`.
 *
 * @property modulus The modulus applied to each intermediate value of the HASH algorithm.
 *
 * @constructor Creates a new instance of [HashAlgorithm] with the given [modulus].
 */
class HashAlgorithm(private val modulus: Int) {
    /**
     * Returns the result of applying the HASH algorithm to the given [string].
     */
    fun convert(string: String): Int = string.fold(0) { acc, char ->
        (acc + char.code).modMultiply(FACTOR, modulus)
    }

    companion object {
        /**
         * A multiplicative factor applied to each intermediate value of the HASH algorithm.
         */
        private const val FACTOR = 17
    }
}
