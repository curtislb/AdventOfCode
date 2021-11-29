package com.curtislb.adventofcode.common.io

import java.io.File
import java.nio.charset.Charset

/**
 * Returns a list containing the results of applying the given [transform] function to each line in
 * this file, using the specified [charset].
 */
fun <T> File.mapLines(charset: Charset = Charsets.UTF_8, transform: (line: String) -> T): List<T> =
    mutableListOf<T>().apply {
        this@mapLines.forEachLine(charset) { add(transform(it)) }
    }
