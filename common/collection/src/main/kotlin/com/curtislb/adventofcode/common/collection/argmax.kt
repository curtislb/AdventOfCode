package com.curtislb.adventofcode.common.collection

import lombok.Generated

/**
 * Returns the index of the element for which the [transform] function produces the greatest value.
 *
 * If the iterable is empty, this method instead returns `null`.
 */
@Generated
inline fun <T, R : Comparable<R>> Iterable<T>.argmaxByOrNull(transform: (element: T) -> R): Int? =
    withIndex().maxByOrNull { transform(it.value) }?.index
