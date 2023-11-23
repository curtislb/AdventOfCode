package com.curtislb.adventofcode.common.parse

import com.curtislb.adventofcode.common.range.BigIntegerRange

/**
 * Regex that matches a decimal integer range with inclusive start and end values.
 */
private val INTEGER_RANGE_REGEX = Regex("""\s*([+\-]?\d+)(?:-|\.\.)([+\-]?\d+)\s*""")

/**
 * Parses the string as an [IntRange] and returns the result.
 *
 * @throws IllegalArgumentException If the string is not a valid representation of an integer range.
 */
fun String.toIntRange(): IntRange {
    val matches = INTEGER_RANGE_REGEX.matchEntire(this)?.groupValues
    require(matches != null) { "Invalid range format: $this" }
    return matches[1].toInt()..matches[2].toInt()
}

/**
 * Parses the string as a [LongRange] and returns the result.
 *
 * @throws IllegalArgumentException If the string is not a valid representation of an integer range.
 */
fun String.toLongRange(): LongRange {
    val matches = INTEGER_RANGE_REGEX.matchEntire(this)?.groupValues
    require(matches != null) { "Invalid range format: $this" }
    return matches[1].toLong()..matches[2].toLong()
}

/**
 * Parses the string as a [BigIntegerRange] and returns the result.
 *
 * @throws IllegalArgumentException If the string is not a valid representation of an integer range.
 */
fun String.toBigIntegerRange(): BigIntegerRange {
    val matches = INTEGER_RANGE_REGEX.matchEntire(this)?.groupValues
    require(matches != null) { "Invalid range format: $this" }
    return BigIntegerRange(matches[1].toBigInteger(), matches[2].toBigInteger())
}
