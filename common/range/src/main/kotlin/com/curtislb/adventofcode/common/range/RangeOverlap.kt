package com.curtislb.adventofcode.common.range

/**
 * Returns a range containing all values that are in both this range and [other].
 */
infix fun IntRange.overlapWith(other: IntRange): IntRange = when {
    this in other -> this
    other in this -> other
    other.last < first || first >= other.first -> first..(other.last)
    else -> (other.first)..last
}

/**
 * Returns a range containing all values that are in both this range and [other].
 */
infix fun LongRange.overlapWith(other: LongRange): LongRange = when {
    this in other -> this
    other in this -> other
    other.last < first || first >= other.first -> first..(other.last)
    else -> (other.first)..last
}

/**
 * Returns a range containing all values that are in both this range and [other].
 */
infix fun BigIntegerRange.overlapWith(other: BigIntegerRange): BigIntegerRange = when {
    this in other -> this
    other in this -> other
    other.last < first || first >= other.first -> BigIntegerRange(first, other.last)
    else -> BigIntegerRange(other.first, last)
}
