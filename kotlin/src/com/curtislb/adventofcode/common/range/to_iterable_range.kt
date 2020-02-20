package com.curtislb.adventofcode.common.range

import java.math.BigInteger

/**
 * Returns the [IntRange] that is equivalent to this [ClosedRange].
 */
fun ClosedRange<Int>.toIterableRange(): IntRange = start..endInclusive

/**
 * Returns the [LongRange] that is equivalent to this [ClosedRange].
 */
fun ClosedRange<Long>.toIterableRange(): LongRange = start..endInclusive

/**
 * Returns the [BigIntegerRange] that is equivalent to this [ClosedRange].
 */
fun ClosedRange<BigInteger>.toIterableRange(): BigIntegerRange = start..endInclusive
