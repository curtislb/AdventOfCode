package com.curtislb.adventofcode.common.collection

/**
 * Returns the index of the element for which the [transform] function produces the greatest value.
 *
 * If the iterable is empty, this function instead returns `null`.
 */
inline fun <T, R : Comparable<R>> Iterable<T>.argmaxByOrNull(transform: (element: T) -> R): Int? =
    withIndex().maxByOrNull { transform(it.value) }?.index
