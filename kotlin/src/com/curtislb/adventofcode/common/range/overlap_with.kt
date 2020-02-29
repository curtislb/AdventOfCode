package com.curtislb.adventofcode.common.range

/**
 * Returns a range containing the values that are in both this range and [other].
 */
fun <T : Comparable<T>> ClosedRange<T>.overlapWith(other: ClosedRange<T>): ClosedRange<T> = when {
    isEmpty() || (start >= other.start && endInclusive <= other.endInclusive) -> this
    other.isEmpty() || (other.start >= start && other.endInclusive <= endInclusive) -> other
    other.endInclusive < start || start >= other.start -> createClosedRange(start, other.endInclusive)
    else -> createClosedRange(other.start, endInclusive)
}

/**
 * Returns a [ClosedRange] object with the given [start] and [endInclusive] values.
 */
private fun <T : Comparable<T>> createClosedRange(start: T, endInclusive: T): ClosedRange<T> {
    return object : ClosedRange<T> {
        override val start: T = start
        override val endInclusive: T = endInclusive
    }
}
