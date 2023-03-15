package com.curtislb.adventofcode.common.range

/**
 * Returns `true` if all values in [other] are also in this range.
 */
operator fun IntRange.contains(other: IntRange): Boolean =
    other.isEmpty() || (other.first >= first && other.last <= last)

/**
 * Returns `true` if all values in [other] are also in this range.
 */
operator fun LongRange.contains(other: LongRange): Boolean =
    other.isEmpty() || (other.first >= first && other.last <= last)

/**
 * Returns `true` if all values in [other] are also in this range.
 */
operator fun BigIntegerRange.contains(other: BigIntegerRange): Boolean =
    other.isEmpty() || (other.first >= first && other.last <= last)
