package com.curtislb.adventofcode.common.io

import java.io.File
import java.nio.charset.Charset
import lombok.Generated

/**
 * Returns a list containing the results of applying the given [transform] function to each line in
 * this file, using the specified [charset].
 */
@Generated
inline fun <T> File.mapLines(
    charset: Charset = Charsets.UTF_8,
    crossinline transform: (line: String) -> T
): List<T> {
    return mutableListOf<T>().apply { this@mapLines.forEachLine(charset) { add(transform(it)) } }
}
