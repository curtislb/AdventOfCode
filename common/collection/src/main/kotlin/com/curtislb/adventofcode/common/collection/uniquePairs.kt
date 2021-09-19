package com.curtislb.adventofcode.common.collection

/**
 * Returns all unique pairs of elements in this list.
 */
fun <T> List<T>.uniquePairs(): Sequence<Pair<T, T>> = sequence {
    val list = this@uniquePairs
    for (i in list.indices) {
        for (j in (i + 1)..list.lastIndex) {
            yield(Pair(list[i], list[j]))
        }
    }
}
