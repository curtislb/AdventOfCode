package com.curtislb.adventofcode.common.iteration

/**
 * Returns a [Sequence] of all unique pairs of distinct elements in this list.
 *
 * If the list is empty or contains only one element, this function returns an empty sequence.
 */
fun <T> List<T>.uniquePairs(): Sequence<Pair<T, T>> = sequence {
    for (i in indices) {
        for (j in (i + 1)..lastIndex) {
            yield(Pair(get(i), get(j)))
        }
    }
}
