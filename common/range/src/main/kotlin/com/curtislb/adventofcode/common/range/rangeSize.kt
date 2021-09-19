package com.curtislb.adventofcode.common.range

import java.math.BigInteger

/**
 * Returns the number of distinct [Int] values in this range.
 */
val ClosedRange<Int>.rangeSize: Int get() = endInclusive - start + 1

/**
 * Returns the number of distinct [Long] values in this range.
 */
val ClosedRange<Long>.rangeSize: Long get() = endInclusive - start + 1L

/**
 * Returns the number of distinct [BigInteger] values in this range.
 */
val ClosedRange<BigInteger>.rangeSize: BigInteger get() = endInclusive - start + BigInteger.ONE
