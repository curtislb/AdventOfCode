package com.adventofcode.curtislb.common.io

import java.io.File

/**
 * Reads a file and interprets its contents as a single contiguous integer range.
 * @receiver The [File] whose contents will be read.
 * @param separator The [String] separating the start and end of the range.
 * @return An [IntRange] representing the contents of the file.
 */
fun File.readIntRange(separator: String = "-"): IntRange {
    val (minValue, maxValue) = readText().trim().split(separator).map { it.trim().toInt() }
    return minValue..maxValue
}
