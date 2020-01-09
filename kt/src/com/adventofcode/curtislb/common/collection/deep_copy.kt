package com.adventofcode.curtislb.common.collection

/**
 * Creates a deep copy of a list, given a per-element copy function.
 * @receiver The [List] of elements to be copied.
 * @param copy A function that produces a deep copy of a list element.
 * @return A [List] containing deep copies of the elements in this list in the same order.
 */
fun <T> List<T>.deepCopy(copy: (T) -> T): List<T> {
    return mutableListOf<T>().apply { addAll(this@deepCopy.map(copy)) }
}
