package com.curtislb.adventofcode.common.io

import java.io.File
import java.nio.charset.Charset
import lombok.Generated

/**
 * Reads and applies [action] to each character in this file, using the specified [charset].
 */
@Generated
inline fun File.forEachChar(charset: Charset = Charsets.UTF_8, action: (char: Char) -> Unit) {
    reader(charset).use { charReader ->
        var c = charReader.read()
        while (c != -1) {
            action(c.toChar())
            c = charReader.read()
        }
    }
}
