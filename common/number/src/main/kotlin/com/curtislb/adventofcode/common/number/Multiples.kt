package com.curtislb.adventofcode.common.number

import com.curtislb.adventofcode.common.collection.mapToMap
import java.math.BigInteger

/**
 * Returns the largest positive integer that evenly divides both [m] and [n].
 *
 * @throws IllegalArgumentException If either [m] or [n] is negative or 0.
 */
fun greatestCommonDivisor(m: Int, n: Int): Int {
    require(m > 0) { "First argument must be positive: $m" }
    require(n > 0) { "Second argument must be positive: $n" }
    var a = m
    var b = n
    while (b != 0) {
        a = b.also { b = a % it }
    }
    return a
}

/**
 * Returns the largest positive integer that evenly divides both [m] and [n].
 *
 * @throws IllegalArgumentException If either [m] or [n] is negative or 0.
 */
fun greatestCommonDivisor(m: Long, n: Long): Long {
    require(m > 0L) { "First argument must be positive: $m" }
    require(n > 0L) { "Second argument must be positive: $n" }
    var a = m
    var b = n
    while (b != 0L) {
        a = b.also { b = a % it }
    }
    return a
}

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

    val initialPowers = takeLargestPowers(primeFactorization(m), primeFactorization(n))
    val factorization = nums.fold(initialPowers) { acc, num ->
        require(num > 0L) { "Each vararg value must be positive: $num" }
        takeLargestPowers(acc, primeFactorization(num))
    }

    return factorization.entries.fold(1L) { acc, (factor, power) -> acc * factor.pow(power) }
}

/**
 * Returns a map from each factor in [factorization1] and/or [factorization2] to the larger of its
 * powers from the two factorizations.
 */
private fun takeLargestPowers(
    factorization1: Map<Long, Int>,
    factorization2: Map<Long, Int>
): Map<Long, Int> {
    val factors = factorization1.keys + factorization2.keys
    return factors.mapToMap { factor ->
        factor to maxOf(factorization1[factor] ?: 0, factorization2[factor] ?: 0)
    }
}

/**
 * Returns the least positive integer greater than [n] that is a multiple of this one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun Int.nextMultipleAbove(n: Int): Int {
    require(this > 0) { "Number must be positive: $this" }
    require(n >= 0) { "Argument must be non-negative: $n" }
    return this - (n % this) + n
}

/**
 * Returns the least positive integer greater than [n] that is a multiple of this one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun Long.nextMultipleAbove(n: Long): Long {
    require(this > 0L) { "Number must be positive: $this" }
    require(n >= 0L) { "Argument must be non-negative: $n" }
    return this - (n % this) + n
}

/**
 * Returns the least positive integer greater than [n] that is a multiple of this one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun BigInteger.nextMultipleAbove(n: BigInteger): BigInteger {
    require(this > BigInteger.ZERO) { "Number must be positive: $this" }
    require(n >= BigInteger.ZERO) { "Argument must be non-negative: $n" }
    return this - (n % this) + n
}

/**
 * Returns the least integer greater than or equal to [n] that is a multiple of this one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun Int.nextMultipleAtLeast(n: Int): Int {
    require(this > 0) { "Number must be positive: $this" }
    require(n >= 0) { "Argument must be non-negative: $n" }
    return if (n % this == 0) n else nextMultipleAbove(n)
}

/**
 * Returns the least integer greater than or equal to [n] that is a multiple of this one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun Long.nextMultipleAtLeast(n: Long): Long {
    require(this > 0L) { "Number must be positive: $this" }
    require(n >= 0L) { "Argument must be non-negative: $n" }
    return if (n % this == 0L) n else nextMultipleAbove(n)
}

/**
 * Returns the least integer greater than or equal to [n] that is a multiple of this one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun BigInteger.nextMultipleAtLeast(n: BigInteger): BigInteger {
    require(this > BigInteger.ZERO) { "Number must be positive: $this" }
    require(n >= BigInteger.ZERO) { "Argument must be non-negative: $n" }
    return if (n % this == BigInteger.ZERO) n else nextMultipleAbove(n)
}

/**
 * Returns the greatest integer less than or equal to [n] that is a multiple of this one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun Int.prevMultipleAtMost(n: Int): Int {
    require(this > 0) { "Number must be positive: $this" }
    require(n >= 0) { "Argument must be non-negative: $n" }
    return n - (n % this)
}

/**
 * Returns the greatest integer less than or equal to [n] that is a multiple of this one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun Long.prevMultipleAtMost(n: Long): Long {
    require(this > 0L) { "Number must be positive: $this" }
    require(n >= 0L) { "Argument must be non-negative: $n" }
    return n - (n % this)
}

/**
 * Returns the greatest integer less than or equal to [n] that is a multiple of this one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun BigInteger.prevMultipleAtMost(n: BigInteger): BigInteger {
    require(this > BigInteger.ZERO) { "Number must be positive: $this" }
    require(n >= BigInteger.ZERO) { "Argument must be non-negative: $n" }
    return n - (n % this)
}

/**
 * Returns the greatest integer less than [n] that is a multiple of this one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun Int.prevMultipleBelow(n: Int): Int {
    require(this > 0) { "Number must be positive: $this" }
    require(n >= 0) { "Argument must be non-negative: $n" }
    return if (n % this == 0) n - this else prevMultipleAtMost(n)
}

/**
 * Returns the greatest integer less than [n] that is a multiple of this one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun Long.prevMultipleBelow(n: Long): Long {
    require(this > 0L) { "Number must be positive: $this" }
    require(n >= 0L) { "Argument must be non-negative: $n" }
    return if (n % this == 0L) n - this else prevMultipleAtMost(n)
}

/**
 * Returns the greatest integer less than [n] that is a multiple of this one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun BigInteger.prevMultipleBelow(n: BigInteger): BigInteger {
    require(this > BigInteger.ZERO) { "Number must be positive: $this" }
    require(n >= BigInteger.ZERO) { "Argument must be non-negative: $n" }
    return if (n % this == BigInteger.ZERO) n - this else prevMultipleAtMost(n)
}
