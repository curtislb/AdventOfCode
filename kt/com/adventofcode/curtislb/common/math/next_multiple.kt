package com.adventofcode.curtislb.common.math

/**
 * Finds the first multiple of this number above a given threshold.
 * @param n A [Long] value that the desired multiple must be above.
 * @return The least [Long] value greater than [n] that is a multiple of this [Long].
 */
fun Long.nextMultipleAbove(n: Long) = n + (this - (n % this))

/**
 * Finds the first multiple of this number above a given threshold.
 * @param n An [Int] value that the desired multiple must be above.
 * @return The least [Int] value greater than [n] that is a multiple of this [Int].
 */
fun Int.nextMultipleAbove(n: Int) = toLong().nextMultipleAbove(n.toLong()).toInt()

/**
 * Finds the first multiple of this number equal to or above a given value.
 * @param n A [Long] value that the desired multiple must be equal to or greater than.
 * @return The least [Long] value greater than or equal to [n] that is a multiple of this [Long].
 */
fun Long.nextMultipleAtLeast(n: Long) = if (n % this == 0L) n else nextMultipleAbove(n)

/**
 * Finds the first multiple of this number equal to or above a given value.
 * @param n An [Int] value that the desired multiple must be equal to or greater than.
 * @return The least [Int] value greater than or equal to [n] that is a multiple of this [Int].
 */
fun Int.nextMultipleAtLeast(n: Int) = toLong().nextMultipleAtLeast(n.toLong()).toInt()
