package com.curtislb.adventofcode.common.collection

import lombok.Generated

/**
 * Returns a deep copy of this list, given a per-element deep [copy] function.
 */
@Generated
inline fun <T> List<T>.deepCopy(copy: (element: T) -> T): List<T> {
    return mutableListOf<T>().apply { addAll(this@deepCopy.map(copy)) }
}
