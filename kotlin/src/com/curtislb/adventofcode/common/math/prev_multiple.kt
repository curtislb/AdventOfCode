package com.curtislb.adventofcode.common.math

/**
 * Returns the greatest integer value less than or equal to [n] that is a multiple of this one.
 */
fun Long.prevMultipleAtMost(n: Long) = n - (n % this)

/**
 * Returns the greatest integer value less than or equal to [n] that is a multiple of this one.
 */
fun Int.prevMultipleAtMost(n: Int) = toLong().prevMultipleAtMost(n.toLong()).toInt()

/**
 * Returns the greatest integer value less than [n] that is a multiple of this one.
 */
fun Long.prevMultipleBelow(n: Long) = if (n % this == 0L) n - this else prevMultipleAtMost(n)

/**
 * Returns the greatest integer value less than [n] that is a multiple of this one.
 */
fun Int.prevMultipleBelow(n: Int) = toLong().prevMultipleBelow(n.toLong()).toInt()
