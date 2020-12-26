package com.curtislb.adventofcode.common.collection

/**
 * TODO
 */
inline fun <T, R : Comparable<R>> Collection<T>.argmaxByOrNull(process: (element: T) -> R): Int? {
    return withIndex().maxByOrNull { process(it.value) }?.index
}
