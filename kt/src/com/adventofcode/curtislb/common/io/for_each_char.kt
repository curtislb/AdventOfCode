package com.adventofcode.curtislb.common.io

import java.io.File
import java.nio.charset.Charset

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
