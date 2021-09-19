package com.curtislb.adventofcode.common.collection

/**
 * TODO
 */
inline fun <T, R : Comparable<R>> Iterable<T>.argmaxByOrNull(transform: (element: T) -> R): Int? {
    return withIndex().maxByOrNull { transform(it.value) }?.index
}
