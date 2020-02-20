package com.curtislb.adventofcode.common.io

import java.io.File

/**
 * Returns a list containing the results of applying the given [transform] function to each line in this file.
 */
fun <T> File.mapLines(transform: (line: String) -> T): List<T> {
    val listBuilder = mutableListOf<T>()
    forEachLine { listBuilder.add(transform(it)) }
    return listBuilder
}
