package com.curtislb.adventofcode.common.io

import java.io.File
import java.nio.charset.Charset

/**
 * Reads and applies [action] to each character in this file, using the specified [charset].
 */
inline fun File.forEachChar(charset: Charset = Charsets.UTF_8, action: (char: Char) -> Unit) {
    val charReader = reader(charset)
    var c = charReader.read()
    while (c != -1) {
        action(c.toChar())
        c = charReader.read()
    }
}
