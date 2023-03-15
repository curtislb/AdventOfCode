package com.curtislb.adventofcode.common.parse

import com.curtislb.adventofcode.common.range.BigIntegerRange

/**
 * TODO
 */
private val INTEGER_RANGE_REGEX = Regex("""([+\-]?\d+)\D+?([+\-]?\d+)""")

/**
 * TODO
 */
fun String.toIntRange(): IntRange {
    val matches = INTEGER_RANGE_REGEX.find(this)?.groupValues
    require(matches != null && matches.size == 3) { "Invalid range format: $this" }
    return matches[1].toInt()..matches[2].toInt()
}

/**
 * TODO
 */
fun String.toLongRange(): LongRange {
    val matches = INTEGER_RANGE_REGEX.find(this)?.groupValues
    require(matches != null && matches.size == 3) { "Invalid range format: $this" }
    return matches[1].toLong()..matches[2].toLong()
}

/**
 * TODO
 */
fun String.toBigIntegerRange(): BigIntegerRange {
    val matches = INTEGER_RANGE_REGEX.find(this)?.groupValues
    require(matches != null && matches.size == 3) { "Invalid range format: $this" }
    return BigIntegerRange(matches[1].toBigInteger(), matches[2].toBigInteger())
}
