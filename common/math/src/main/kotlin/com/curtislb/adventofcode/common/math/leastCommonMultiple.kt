package com.curtislb.adventofcode.common.math

import com.curtislb.adventofcode.common.collection.mapToMap
import kotlin.math.max

/**
 * Returns the smallest whole number that can be evenly divided by both [m] and [n].
 *
 * @throws IllegalArgumentException If either [m] or [n] is negative or 0.
 */
fun leastCommonMultiple(m: Int, n: Int): Int {
    require(m > 0) { "First argument must be positive: $m" }
    require(n > 0) { "Second argument must be positive: $n" }
    return m / greatestCommonDivisor(m, n) * n
}

/**
 * Returns the smallest whole number that can be evenly divided by both [m] and [n].
 *
 * @throws IllegalArgumentException If either [m] or [n] is negative or 0.
 */
fun leastCommonMultiple(m: Long, n: Long): Long {
    require(m > 0L) { "First argument must be positive: $m" }
    require(n > 0L) { "Second argument must be positive: $n" }
    return m / greatestCommonDivisor(m, n) * n
}

/**
 * Returns the smallest whole number that can be evenly divided by [m], [n], and all of [nums].
 *
 * @throws IllegalArgumentException If [m], [n], or any value in [nums] is negative or 0.
 */
fun leastCommonMultiple(m: Long, n: Long, vararg nums: Long): Long {
    require(m > 0L) { "First argument must be positive: $m" }
    require(n > 0L) { "Second argument must be positive: $n" }
    val factorization = nums.fold(
        takeLargestPowers(m.primeFactorization(), n.primeFactorization())
    ) { partial, num ->
        require(num > 0L) { "Each vararg value must be positive: $num" }
        takeLargestPowers(partial, num.primeFactorization())
    }
    return factorization.entries.fold(1L) { result, (factor, power) -> result * factor.pow(power) }
}

/**
 * Returns a map from each factor in [factorization1] or [factorization2] (or both) to the larger of
 * its powers from the two factorizations.
 */
private fun takeLargestPowers(
    factorization1: Map<Long, Int>,
    factorization2: Map<Long, Int>
): Map<Long, Int> =
    (factorization1.keys + factorization2.keys).mapToMap { factor ->
        factor to max(factorization1[factor] ?: 0, factorization2[factor] ?: 0)
    }
