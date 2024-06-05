package com.curtislb.adventofcode.common.collection

/**
 * Returns the indices of the list, sorted by the result of the [selector] function applied to the
 * corresponding list element.
 */
inline fun <T, R : Comparable<R>> List<T>.sortIndicesBy(crossinline selector: (T) -> R): List<Int> {
    val indexList = indices.toMutableList()
    indexList.sortBy { selector(this[it]) }
    return indexList
}
