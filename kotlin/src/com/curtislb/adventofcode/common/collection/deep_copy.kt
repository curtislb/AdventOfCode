package com.curtislb.adventofcode.common.collection

/**
 * Returns a deep copy of this list, given a per-element deep [copy] function.
 */
inline fun <T> List<T>.deepCopy(copy: (element: T) -> T): List<T> {
    return mutableListOf<T>().apply { addAll(this@deepCopy.map(copy)) }
}
