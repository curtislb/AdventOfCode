package com.curtislb.adventofcode.common.collection

/**
 * Returns the value at the given [rowIndex] and [colIndex] in this 2D list, or `null` if there is
 * no such value.
 */
fun <T> List<List<T>>.getOrNull(rowIndex: Int, colIndex: Int): T? =
    getOrNull(rowIndex)?.getOrNull(colIndex)
