package com.curtislb.adventofcode.common.number

import java.math.BigInteger
import kotlin.math.floor
import kotlin.math.sqrt

/**
 * Returns the [n]th triangular number, defined as the sum of integers 0..[n].
 *
 * If [n] < 0, this function instead returns `triangleNumber(abs(n) - 1)`.
 */
fun triangleNumber(n: Int): Int = if (n % 2 == 0) n / 2 * (n + 1) else (n + 1) / 2 * n

/**
 * Returns the [n]th triangular number, defined as the sum of integers 0..[n].
 *
 * If [n] < 0, this function instead returns `triangleNumber(abs(n) - 1)`.
 */
fun triangleNumber(n: Long): Long = if (n % 2L == 0L) n / 2L * (n + 1L) else (n + 1L) / 2L * n

/**
 * Returns the [n]th triangular number, defined as the sum of integers 0..[n].
 *
 * If [n] < 0, this function instead returns `triangleNumber(abs(n) - 1)`.
 */
fun triangleNumber(n: BigInteger): BigInteger = n * (n + BigInteger.ONE) / BigInteger.TWO

/**
 * If [n] is a triangular number, returns the unique non-negative integer value `m` such that
 * `triangleNumber(m) == n`.
 *
 * If [n] is *not* a triangular number, the behavior of this function is undefined.
 */
fun triangleNumberInverse(n: Int): Int = floor(sqrt(n * 2.0)).toInt()

/**
 * If [n] is a triangular number, returns the unique non-negative integer value `m` such that
 * `triangleNumber(m) == n`.
 *
 * If [n] is *not* a triangular number, the behavior of this function is undefined.
 */
fun triangleNumberInverse(n: Long): Long = floor(sqrt(n * 2.0)).toLong()

/**
 * If [n] is a triangular number, returns the unique non-negative integer value `m` such that
 * `triangleNumber(m) == n`.
 *
 * If [n] is *not* a triangular number, the behavior of this function is undefined.
 */
fun triangleNumberInverse(n: BigInteger): BigInteger = (n * BigInteger.TWO).sqrt()
