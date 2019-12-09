package com.adventofcode.curtislb.common.io

import java.nio.file.Path
import java.nio.file.Paths

/**
 * TODO
 */
fun pathToInput(day: Int, fileName: String): Path {
    return Paths.get("input", "day${day.toString().padStart(2, '0')}", fileName)
}