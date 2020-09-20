package com.curtislb.adventofcode.common.io

import java.io.File

/**
 * Returns a list containing the results of applying the given [transform] function to each line in this file.
 */
inline fun <T> File.mapLines(crossinline transform: (line: String) -> T): List<T> {
    return mutableListOf<T>().apply { this@mapLines.forEachLine { add(transform(it)) } }
}
