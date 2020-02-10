package com.curtislb.adventofcode.common.math

/**
 * Returns the least integer value greater than [n] that is a multiple of this one.
 */
fun Long.nextMultipleAbove(n: Long) = n + (this - (n % this))

/**
 * Returns the least integer value greater than [n] that is a multiple of this one.
 */
fun Int.nextMultipleAbove(n: Int) = toLong().nextMultipleAbove(n.toLong()).toInt()

/**
 * Returns the least integer value greater than or equal to [n] that is a multiple of this one.
 */
fun Long.nextMultipleAtLeast(n: Long) = if (n % this == 0L) n else nextMultipleAbove(n)

/**
 * Returns the least integer value greater than or equal to [n] that is a multiple of this one.
 */
fun Int.nextMultipleAtLeast(n: Int) = toLong().nextMultipleAtLeast(n.toLong()).toInt()
