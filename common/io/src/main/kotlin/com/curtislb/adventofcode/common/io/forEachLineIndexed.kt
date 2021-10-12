package com.curtislb.adventofcode.common.io

import java.io.File
import java.nio.charset.Charset
import lombok.Generated

/**
 * Reads and applies [action] to each line in this file, along with a sequential index, using the
 * specified [charset].
 */
@Generated
inline fun File.forEachLineIndexed(
    charset: Charset = Charsets.UTF_8,
    crossinline action: (index: Int, line: String) -> Unit
) {
    var index = 0
    forEachLine(charset) {
        action(index, it)
        index++
    }
}
