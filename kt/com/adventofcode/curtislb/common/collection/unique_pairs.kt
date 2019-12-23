package com.adventofcode.curtislb.common.collection

/**
 * Produces all unique pairs of elements in a list.
 * @receiver The [List] whose elements will be paired.
 * @return A finite [Sequence] yielding all unique pairs of elements.
 */
fun <T> List<T>.uniquePairs(): Sequence<Pair<T, T>> = sequence {
    val list = this@uniquePairs
    for (i in list.indices) {
        for (j in (i + 1)..list.lastIndex) {
            yield(Pair(list[i], list[j]))
        }
    }
}
