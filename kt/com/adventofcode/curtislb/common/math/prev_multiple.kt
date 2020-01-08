package com.adventofcode.curtislb.common.math

/**
 * Finds the first multiple of this number equal to or below a given value.
 * @param n A [Long] value that the desired multiple must be equal to or less than.
 * @return The greatest [Long] value less than or equal to [n] that is a multiple of this [Long].
 */
fun Long.prevMultipleAtMost(n: Long) = n - (n % this)

/**
 * Finds the first multiple of this number equal to or below a given value.
 * @param n An [Int] value that the desired multiple must be equal to or less than.
 * @return The greatest [Int] value less than or equal to [n] that is a multiple of this [Int].
 */
fun Int.prevMultipleAtMost(n: Int) = toLong().prevMultipleAtMost(n.toLong()).toInt()

/**
 * Finds the first multiple of this number below a given threshold.
 * @param n A [Long] value that the desired multiple must be below.
 * @return The greatest [Long] value less than [n] that is a multiple of this [Long].
 */
fun Long.prevMultipleBelow(n: Long) = if (n % this == 0L) n - this else prevMultipleAtMost(n)

/**
 * Finds the first multiple of this number below a given threshold.
 * @param n An [Int] value that the desired multiple must be below.
 * @return The greatest [Int] value less than [n] that is a multiple of this [Int].
 */
fun Int.prevMultipleBelow(n: Int) = toLong().prevMultipleBelow(n.toLong()).toInt()
