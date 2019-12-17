package com.adventofcode.curtislb.common.io

import java.io.File
import java.nio.charset.Charset
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Produces the path corresponding to the input file for a given puzzle.
 * @param year The integer year corresponding to that of the puzzle.
 * @param day The integer day corresponding to that of the puzzle.
 * @param fileName The name of the input file, with the path omitted.
 * @return A [Path] object representing the location of the input file on the current file system.
 */
fun pathToInput(year: Int, day: Int, fileName: String): Path {
    return Paths.get("input", "year${year}", "day${day.toString().padStart(2, '0')}", fileName)
}

/**
 * Reads the text of a file and interprets it as a single contiguous integer range.
 * @receiver The [File] whose contents will be read.
 * @param separator The [String] separating the start and end of the range.
 * @return An [IntRange] representing the contents of the file.
 */
fun File.readIntRange(separator: String = "-"): IntRange {
    val (minValue, maxValue) = readText().trim().split(separator).map { it.trim().toInt() }
    return minValue..maxValue
}

/**
 * Reads this file character by character using the specified [charset], calling [action] for each [Char].
 * @param charset The character set to use. Defaults to UTF-8.
 * @param action An action to be performed on each [Char] read from this file.
 */
fun File.forEachChar(charset: Charset = Charsets.UTF_8, action: (char: Char) -> Unit) {
    val reader = this.reader(charset)
    var c = reader.read()
    while (c != -1) {
        action(c.toChar())
        c = reader.read()
    }
}
