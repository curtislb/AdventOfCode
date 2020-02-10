package com.curtislb.adventofcode.common.math

import com.curtislb.adventofcode.common.collection.mapToMap
import kotlin.math.max

/**
 * Returns the smallest whole number that can be evenly divided by both [m] and [n].
 */
fun leastCommonMultiple(m: Long, n: Long): Long {
    assert(m > 0L)
    assert(n > 0L)
    return m / greatestCommonDivisor(m, n) * n
}

/**
 * Returns the smallest whole number that can be evenly divided by both [m] and [n].
 */
fun leastCommonMultiple(m: Int, n: Int): Int = leastCommonMultiple(m.toLong(), n.toLong()).toInt()

/**
 * Returns the smallest whole number that can be evenly divided by [m], [n], and all numbers in [nums].
 */
fun leastCommonMultiple(m: Long, n: Long, vararg nums: Long): Long {
    assert(m > 0L)
    assert(n > 0L)
    val factorization = nums.fold(takeLargestPowers(m.primeFactorization(), n.primeFactorization())) { partial, num ->
        assert(num > 0L)
        takeLargestPowers(partial, num.primeFactorization())
    }
    return factorization.entries.fold(1L) { product, (factor, power) -> product * factor.pow(power) }
}

/**
 * Returns a map from each factor in [factorization1] or [factorization2] (or both) to the larger of its powers from
 * the two factorizations.
 */
private fun takeLargestPowers(factorization1: Map<Long, Int>, factorization2: Map<Long, Int>): Map<Long, Int> {
    return (factorization1.keys + factorization2.keys).mapToMap { factor ->
        Pair(factor, max(factorization1[factor] ?: 0, factorization2[factor] ?: 0))
    }
}
