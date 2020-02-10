package com.curtislb.adventofcode.common.io

import java.io.File

/**
 * Returns the contents of this file as an integer range with start and end (inclusive) values delimited by [separator].
 */
fun File.readIntRange(separator: String = "-"): IntRange {
    val (minValue, maxValue) = readText().trim().split(separator).map { it.trim().toInt() }
    return minValue..maxValue
}
