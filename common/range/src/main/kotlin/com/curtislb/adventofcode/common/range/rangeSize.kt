package com.curtislb.adventofcode.common.range

import java.math.BigInteger

/**
 * Returns the number of distinct [Int] values in this range.
 */
val ClosedRange<Int>.sizeInclusive: Int get() = if (isEmpty()) 0 else endInclusive - start + 1

/**
 * Returns the number of distinct [Long] values in this range.
 */
val ClosedRange<Long>.sizeInclusive: Long get() = if (isEmpty()) 0 else endInclusive - start + 1L

/**
 * Returns the number of distinct [BigInteger] values in this range.
 */
val ClosedRange<BigInteger>.sizeInclusive: BigInteger
    get() = if (isEmpty()) BigInteger.ZERO else endInclusive - start + BigInteger.ONE
