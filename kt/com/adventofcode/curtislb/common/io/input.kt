package com.adventofcode.curtislb.common.io

import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Produces the path corresponding to the input file for a given puzzle.
 * @param day The integer day corresponding to that of the puzzle.
 * @param fileName The name of the input file, with the path omitted.
 * @return A [Path] object representing the location of the input file on the current file system.
 */
fun pathToInput(day: Int, fileName: String): Path {
    return Paths.get("input", "day${day.toString().padStart(2, '0')}", fileName)
}

/**
 * Reads the text of a file and interprets it as a single contiguous integer range.
 * @receiver The [File] whose contents will be read.
 * @param separator The [String] separating the start and end of the range.
 * @return A [Pair] consisting of the minimum and maximum (inclusive) range values.
 */
fun File.readIntRange(separator: String = "-"): Pair<Int, Int> {
    val (minValue, maxValue) = readText().trim().split(separator).map { it.trim().toInt() }
    return Pair(minValue, maxValue)
}
