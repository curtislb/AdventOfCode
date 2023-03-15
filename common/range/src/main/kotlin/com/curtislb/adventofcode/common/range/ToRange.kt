package com.curtislb.adventofcode.common.range

import java.math.BigInteger

/**
 * Returns an [IntRange] corresponding to this [ClosedRange].
 */
fun ClosedRange<Int>.toIntRange(): IntRange = start..endInclusive

/**
 * Returns a [LongRange] corresponding to this [ClosedRange].
 */
fun ClosedRange<Long>.toLongRange(): LongRange = start..endInclusive

/**
 * Returns a [BigIntegerRange] corresponding to this [ClosedRange].
 */
fun ClosedRange<BigInteger>.toBigIntegerRange(): BigIntegerRange =
    BigIntegerRange(start, endInclusive)
