package com.adventofcode.curtislb.common.collection

/**
 * TODO
 */
fun <T> List<T>.uniquePairs(): Sequence<Pair<T, T>> = sequence {
    val list = this@uniquePairs
    for (i in list.indices) {
        for (j in (i + 1)..list.lastIndex) {
            yield(Pair(list[i], list[j]))
        }
    }
}
