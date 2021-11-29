package com.curtislb.adventofcode.common.io

import java.io.File
import java.nio.charset.Charset

/**
 * Reads and applies [action] to each line in this file, along with a sequential index, using the
 * specified [charset].
 */
fun File.forEachLineIndexed(
    charset: Charset = Charsets.UTF_8,
    action: (index: Int, line: String) -> Unit
) {
    var index = 0
    forEachLine(charset) {
        action(index, it)
        index++
    }
}
