package com.adventofcode.curtislb.common.math

import kotlin.math.max

/**
 * Finds the least common multiple (LCM) of two whole numbers.
 * @param m A positive [Long] value.
 * @param n A second positive [Long] value.
 * @return The smallest whole number that can be evenly divided by both [m] and [n].
 */
fun leastCommonMultiple(m: Long, n: Long): Long {
    assert(m > 0L)
    assert(n > 0L)
    return m / greatestCommonDivisor(m, n) * n
}

/**
 * Finds the least common multiple (LCM) of two whole numbers.
 * @param m A positive [Int] value.
 * @param n A second positive [Int] value.
 * @return The smallest whole number that can be evenly divided by both [m] and [n].
 */
fun leastCommonMultiple(m: Int, n: Int): Int = leastCommonMultiple(m.toLong(), n.toLong()).toInt()

/**
 * Finds the least common multiple (LCM) of three or more whole numbers.
 * @param m A positive [Long] value.
 * @param n A second positive [Long] value.
 * @param nums One or more additional positive [Long] values.
 * @return The smallest whole number that can be evenly divided by [m], [n], and all numbers in [nums].
 */
fun leastCommonMultiple(m: Long, n: Long, vararg nums: Long): Long {
    assert(m > 0L)
    assert(n > 0L)
    val factorization = nums.fold(takeLargestPowers(m.primeFactorization(), n.primeFactorization())) { partial, num ->
        takeLargestPowers(partial, num.primeFactorization())
    }
    return factorization.entries.fold(1L) { product, (factor, power) -> product * factor.pow(power) }
}

/**
 * Combines two factorizations by taking the largest power for each factor.
 * @param factorization1 A [Map] from factors to their corresponding powers in the factorization of a number.
 * @param factorization2 A second [Map] representing the factorization of a number.
 * @return A [Map] from each factor in [factorization1] or [factorization2] (or both) to the larger of its powers from
 *  the original two factorizations.
 */
private fun takeLargestPowers(factorization1: Map<Long, Int>, factorization2: Map<Long, Int>): Map<Long, Int> {
    val largestPowers = mutableMapOf<Long, Int>()
    (factorization1.keys + factorization2.keys).forEach { factor ->
        largestPowers[factor] = max(factorization1[factor] ?: 0, factorization2[factor] ?: 0)
    }
    return largestPowers
}
