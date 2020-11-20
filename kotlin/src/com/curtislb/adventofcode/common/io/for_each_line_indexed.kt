package com.curtislb.adventofcode.common.io

import java.io.File

/**
 * Reads and applies [action] to each line in this file, along with a sequential index.
 */
inline fun File.forEachLineIndexed(crossinline action: (index: Int, line: String) -> Unit) {
    var index = 0
    forEachLine {
        action(index, it)
        index++
    }
}
