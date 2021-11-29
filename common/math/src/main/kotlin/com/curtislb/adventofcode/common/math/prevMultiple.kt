package com.curtislb.adventofcode.common.math

import java.math.BigInteger

/**
 * Returns the greatest non-negative integer less than or equal to [n] that is a multiple of this
 * one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun Int.prevMultipleAtMost(n: Int): Int {
    require(this > 0) { "Number must be positive: $this" }
    require(n >= 0) { "Argument must be non-negative: $n" }
    return n - (n % this)
}

/**
 * Returns the greatest non-negative integer less than or equal to [n] that is a multiple of this
 * one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun Long.prevMultipleAtMost(n: Long): Long {
    require(this > 0L) { "Number must be positive: $this" }
    require(n >= 0L) { "Argument must be non-negative: $n" }
    return n - (n % this)
}

/**
 * Returns the greatest non-negative integer less than or equal to [n] that is a multiple of this
 * one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun BigInteger.prevMultipleAtMost(n: BigInteger): BigInteger {
    require(this > BigInteger.ZERO) { "Number must be positive: $this" }
    require(n >= BigInteger.ZERO) { "Argument must be non-negative: $n" }
    return n - (n % this)
}

/**
 * Returns the greatest non-negative integer less than [n] that is a multiple of this one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun Int.prevMultipleBelow(n: Int): Int {
    require(this > 0) { "Number must be positive: $this" }
    require(n >= 0) { "Argument must be non-negative: $n" }
    return if (n % this == 0) n - this else prevMultipleAtMost(n)
}

/**
 * Returns the greatest non-negative integer less than [n] that is a multiple of this one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun Long.prevMultipleBelow(n: Long): Long {
    require(this > 0L) { "Number must be positive: $this" }
    require(n >= 0L) { "Argument must be non-negative: $n" }
    return if (n % this == 0L) n - this else prevMultipleAtMost(n)
}

/**
 * Returns the greatest non-negative integer less than [n] that is a multiple of this one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun BigInteger.prevMultipleBelow(n: BigInteger): BigInteger {
    require(this > BigInteger.ZERO) { "Number must be positive: $this" }
    require(n >= BigInteger.ZERO) { "Argument must be non-negative: $n" }
    return if (n % this == BigInteger.ZERO) n - this else prevMultipleAtMost(n)
}
