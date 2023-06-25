package com.curtislb.adventofcode.common.io

import java.io.File
import java.nio.charset.Charset

/**
 * Reads and applies [action] to each character in the file, using the specified [charset].
 */
fun File.forEachChar(charset: Charset = Charsets.UTF_8, action: (char: Char) -> Unit) {
    reader(charset).use { charReader ->
        var c = charReader.read()
        while (c != -1) {
            action(c.toChar())
            c = charReader.read()
        }
    }
}

/**
 * Reads and applies [action] to each line in the file, along with a sequential index, using the
 * specified [charset].
 */
fun File.forEachLineIndexed(
    charset: Charset = Charsets.UTF_8,
    action: (index: Int, line: String) -> Unit
) {
    var index = 0
    forEachLine(charset) { line ->
        action(index, line)
        index++
    }
}

/**
 * Reads and applies [action] to each sequence of non-empty lines in the file, using the specified
 * [charset].
 */
fun File.forEachSection(charset: Charset = Charsets.UTF_8, action: (lines: List<String>) -> Unit) {
    var lines = mutableListOf<String>()
    forEachLine(charset) { line ->
        if (line.isBlank()) {
            if (lines.isNotEmpty()) {
                action(lines)
                lines = mutableListOf()
            }
        } else {
            lines.add(line)
        }
    }

    // Process the last section if there's no trailing blank line
    if (lines.getOrNull(0)?.isNotBlank() == true) {
        action(lines)
    }
}

/**
 * Returns a list containing the results of applying the given [transform] function to each line in
 * this file, using the specified [charset].
 */
fun <T> File.mapLines(charset: Charset = Charsets.UTF_8, transform: (line: String) -> T): List<T> {
    val list = mutableListOf<T>()
    forEachLine(charset) { list.add(transform(it)) }
    return list
}

/**
 * Returns an ordered list of all sections in the file in order, using the specified [charset].
 *
 * Each section consists of a list of contiguous, non-empty lines from the original file.
 */
fun File.readSections(charset: Charset = Charsets.UTF_8): List<List<String>> {
    val sections = mutableListOf<List<String>>()
    forEachSection(charset) { sections.add(it) }
    return sections
}
