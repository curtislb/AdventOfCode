package com.curtislb.adventofcode.common.collection

/**
 * Returns a deep copy of this list, given a deep per-element [copy] function.
 */
fun <T> List<T>.deepCopy(copy: (element: T) -> T): List<T> {
    return mutableListOf<T>().apply { addAll(this@deepCopy.map(copy)) }
}
