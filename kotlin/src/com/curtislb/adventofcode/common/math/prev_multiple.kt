package com.curtislb.adventofcode.common.math

/**
 * Returns the greatest non-negative integer less than or equal to [n] that is a multiple of this one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun Long.prevMultipleAtMost(n: Long): Long {
    require(this > 0L) { "Number must be positive: $this" }
    require(n >= 0L) { "Argument must be non-negative: $n" }
    return n - (n % this)
}

/**
 * Returns the greatest non-negative integer less than or equal to [n] that is a multiple of this one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun Int.prevMultipleAtMost(n: Int): Int = toLong().prevMultipleAtMost(n.toLong()).toInt()

/**
 * Returns the greatest non-negative integer less than [n] that is a multiple of this one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun Long.prevMultipleBelow(n: Long): Long {
    require(this > 0L) { "Number must be positive: $this" }
    require(n >= 0L) { "Number n must be non-negative: $n" }
    return if (n % this == 0L) n - this else prevMultipleAtMost(n)
}

/**
 * Returns the greatest non-negative integer less than [n] that is a multiple of this one.
 *
 * @throws IllegalArgumentException If this number is negative or 0, or if [n] is negative.
 */
fun Int.prevMultipleBelow(n: Int): Int = toLong().prevMultipleBelow(n.toLong()).toInt()
