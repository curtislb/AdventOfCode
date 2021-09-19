package com.curtislb.adventofcode.common.io

import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the path to the input file [fileName] for the puzzle on a given [day] and [year] of Advent of Code.
 *
 * Some puzzles have a different input directory for each part, in which case, the correct path can be specified by
 * providing a non-null value for [part].
 */
fun pathToInput(year: Int, day: Int, part: Int? = null, fileName: String = "input.txt"): Path {
    val yearDir = "year$year"
    val dayDir = "day${day.toString().padStart(2, '0')}"
    return if (part == null) {
        Paths.get("input", yearDir, dayDir, fileName)
    } else {
        Paths.get("input", yearDir, dayDir, "part$part", fileName)
    }
}
