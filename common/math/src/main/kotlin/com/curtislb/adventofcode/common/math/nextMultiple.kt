package com.curtislb.adventofcode.common.math

import java.math.BigInteger

/**
 * Returns the least positive integer greater than [n] that is a multiple of this one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun BigInteger.nextMultipleAbove(n: BigInteger): BigInteger {
    require(this > BigInteger.ZERO) { "Number must be positive: $this" }
    require(n >= BigInteger.ZERO) { "Argument must be non-negative: $n" }
    return n + (this - (n % this))
}

/**
 * Returns the least positive integer greater than [n] that is a multiple of this one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun Long.nextMultipleAbove(n: Long): Long {
    require(this > 0L) { "Number must be positive: $this" }
    require(n >= 0L) { "Argument must be non-negative: $n" }
    return n + (this - (n % this))
}

/**
 * Returns the least positive integer greater than [n] that is a multiple of this one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun Int.nextMultipleAbove(n: Int): Int = toLong().nextMultipleAbove(n.toLong()).toInt()

/**
 * Returns the least non-negative integer greater than or equal to [n] that is a multiple of this
 * one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun BigInteger.nextMultipleAtLeast(n: BigInteger): BigInteger {
    require(this > BigInteger.ZERO) { "Number must be positive: $this" }
    require(n >= BigInteger.ZERO) { "Number n must be non-negative: $n" }
    return if (n % this == BigInteger.ZERO) n else nextMultipleAbove(n)
}

/**
 * Returns the least non-negative integer greater than or equal to [n] that is a multiple of this
 * one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun Long.nextMultipleAtLeast(n: Long): Long {
    require(this > 0L) { "Number must be positive: $this" }
    require(n >= 0L) { "Number n must be non-negative: $n" }
    return if (n % this == 0L) n else nextMultipleAbove(n)
}

/**
 * Returns the least non-negative integer greater than or equal to [n] that is a multiple of this
 * one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun Int.nextMultipleAtLeast(n: Int): Int = toLong().nextMultipleAtLeast(n.toLong()).toInt()
