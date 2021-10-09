package com.curtislb.adventofcode.common.collection

import lombok.Generated

/**
 * TODO
 */
@Generated
inline fun <T, R : Comparable<R>> Iterable<T>.argmaxByOrNull(transform: (element: T) -> R): Int? {
    return withIndex().maxByOrNull { transform(it.value) }?.index
}
