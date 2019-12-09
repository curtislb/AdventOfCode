package com.adventofcode.curtislb.common.io

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