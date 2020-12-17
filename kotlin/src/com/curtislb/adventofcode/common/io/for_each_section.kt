package com.curtislb.adventofcode.common.io

import java.io.File
import java.nio.charset.Charset

/**
 * Reads and applies [action] to each sequence of non-empty lines in this file, using the specified [charset].
 */
inline fun File.forEachSection(charset: Charset = Charsets.UTF_8, crossinline action: (lines: List<String>) -> Unit) {
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

    // Apply action to the last section if there is no final blank line.
    if (lines.getOrNull(0)?.isNotBlank() == true) {
        action(lines)
    }
}
