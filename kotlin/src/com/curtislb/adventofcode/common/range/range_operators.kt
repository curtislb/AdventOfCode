package com.curtislb.adventofcode.common.range

import java.math.BigInteger

/**
 * Returns a [BigIntegerRange] containing the values from this up to and including [other].
 */
operator fun BigInteger.rangeTo(other: BigInteger): BigIntegerRange = BigIntegerRange(this, other)

/**
 * Returns a [BigIntegerRange] containing the values from this up until (but not including) [other].
 */
infix fun BigInteger.until(other: BigInteger): BigIntegerRange = BigIntegerRange(this, other - BigInteger.ONE)
