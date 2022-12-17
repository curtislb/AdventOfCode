package com.curtislb.adventofcode.common.math

import java.math.BigInteger
import kotlin.math.floor
import kotlin.math.sqrt

/**
 * Returns the [n]th triangular number, defined as the sum of integers 0..[n].
 */
fun triangleNumber(n: Int): Int = n * (n + 1) / 2

/**
 * Returns the [n]th triangular number, defined as the sum of integers 0..[n].
 */
fun triangleNumber(n: Long): Long = n * (n + 1L) / 2L

/**
 * Returns the [n]th triangular number, defined as the sum of integers 0..[n].
 */
fun triangleNumber(n: BigInteger): BigInteger = n * (n + BigInteger.ONE) / BigInteger.TWO

/**
 * If [n] is a triangular number, returns the value `m` such that `triangleNumber(m) == n`.
 */
fun triangleNumberInverse(n: Int): Int = floor(sqrt(n * 2.0)).toInt()

/**
 * If [n] is a triangular number, returns the value `m` such that `triangleNumber(m) == n`.
 */
fun triangleNumberInverse(n: Long): Long = floor(sqrt(n * 2.0)).toLong()

/**
 * If [n] is a triangular number, returns the value `m` such that `triangleNumber(m) == n`.
 */
fun triangleNumberInverse(n: BigInteger): BigInteger = (n * BigInteger.TWO).sqrt()
