package com.curtislb.adventofcode.common.range

import java.math.BigInteger

/**
 * Returns the number of distinct integer values in the range.
 */
fun IntRange.size(): Int = if (isEmpty()) 0 else last - first + 1

/**
 * Returns the number of distinct integer values in the range.
 */
fun LongRange.size(): Long = if (isEmpty()) 0L else last - first + 1L

/**
 * Returns the number of distinct integer values in the range.
 */
fun BigIntegerRange.size(): BigInteger =
    if (isEmpty()) BigInteger.ZERO else last - first + BigInteger.ONE
