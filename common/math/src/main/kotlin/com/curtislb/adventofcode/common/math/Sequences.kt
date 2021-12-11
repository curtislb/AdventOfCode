package com.curtislb.adventofcode.common.math

import java.math.BigInteger

/**
 * Returns the [n]th triangular number, defined as the sum of integers 1..[n].
 */
fun triangularNumber(n: Int): Int = n * (n + 1) / 2

/**
 * Returns the [n]th triangular number, defined as the sum of integers 1..[n].
 */
fun triangularNumber(n: Long): Long = n * (n + 1L) / 2L

/**
 * Returns the [n]th triangular number, defined as the sum of integers 1..[n].
 */
fun triangularNumber(n: BigInteger): BigInteger = n * (n + BigInteger.ONE) / BigInteger.TWO
