package com.curtislb.adventofcode.common.io

import java.io.File
import java.nio.charset.Charset

/**
 * Reads and applies [process] to each line in this file, using the specified [charset].
 *
 * Unlike [File.forEachLine], this function is inlined, meaning that [process] can terminate the function early with a
 * non-local return. [process] can also terminate this function early by producing `true` after processing a given line.
 */
inline fun File.forEachLineInterruptible(charset: Charset = Charsets.UTF_8, process: (line: String) -> Boolean) {
    useLines(charset) { lines ->
        lines.forEach { line ->
            if (process(line)) {
                return
            }
        }
    }
}
