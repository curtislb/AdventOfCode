package com.curtislb.adventofcode.common.io

import java.io.File
import java.nio.charset.Charset

/**
 * Reads and applies [process] to each line in this file, using the specified [charset].
 *
 * Since this function is inlined (unlike [File.forEachLine]), [process] may terminate this function early via a
 * non-local return. Alternatively, [process] can terminate this function by producing `true` after processing a given
 * line.
 */
inline fun File.forEachLineTerminable(charset: Charset = Charsets.UTF_8, process: (line: String) -> Boolean) {
    useLines(charset) { lines ->
        lines.forEach { line ->
            if (process(line)) {
                return
            }
        }
    }
}
