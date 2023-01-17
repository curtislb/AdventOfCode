package com.curtislb.adventofcode.common.range

import java.math.BigInteger

/**
 * The number of distinct integer values in this range.
 */
val ClosedRange<Int>.size: Int
    get() = if (isEmpty()) 0 else endInclusive - start + 1

/**
 * The number of distinct integer values in this range.
 */
val ClosedRange<Long>.size: Long
    get() = if (isEmpty()) 0 else endInclusive - start + 1L

/**
 * Returns if all the values in [other] are also in this range.
 */
operator fun IntRange.contains(other: IntRange): Boolean = other.all { it in this }

/**
 * Returns if all the values in [other] are also in this range.
 */
operator fun LongRange.contains(other: LongRange): Boolean = other.all { it in this }

/**
 * Returns a range containing all values that are contained by both this range and [other].
 */
fun <T : Comparable<T>> ClosedRange<T>.overlapWith(other: ClosedRange<T>): ClosedRange<T> = when {
    isEmpty() || (start >= other.start && endInclusive <= other.endInclusive) -> this
    other.isEmpty() || (other.start >= start && other.endInclusive <= endInclusive) -> other
    other.endInclusive < start || start >= other.start -> ClosedRange(start, other.endInclusive)
    else -> ClosedRange(other.start, endInclusive)
}

/**
 * Returns a [ClosedRange] with the given [start] and [endInclusive] values.
 */
private fun <T : Comparable<T>> ClosedRange(start: T, endInclusive: T): ClosedRange<T> =
    object : ClosedRange<T> {
        override val start: T = start
        override val endInclusive: T = endInclusive
    }

/**
 * Returns a [IntRange] corresponding to this [ClosedRange].
 */
fun ClosedRange<Int>.toIterableRange(): IntRange = start..endInclusive

/**
 * Returns a [LongRange] corresponding to this [ClosedRange].
 */
fun ClosedRange<Long>.toIterableRange(): LongRange = start..endInclusive

/**
 * Returns a [BigIntegerRange] corresponding to this [ClosedRange].
 */
fun ClosedRange<BigInteger>.toIterableRange(): BigIntegerRange = start..endInclusive
