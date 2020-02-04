package com.adventofcode.curtislb.common.io

import java.nio.file.Path
import java.nio.file.Paths

/**
 * Produces the path corresponding to the input file for a given puzzle.
 * @param year The integer year corresponding to that of the puzzle.
 * @param day The integer day corresponding to that of the puzzle.
 * @param part The integer part of the puzzle. If `null`, this portion of the path will be omitted.
 * @param fileName The name of the input file, with the path omitted.
 * @return A [Path] object representing the location of the input file on the current file system.
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
