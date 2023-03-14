package com.curtislb.adventofcode.common.collection

/**
 * Returns a copy of this list with the value at the given [index] replaced by the result of
 * applying the [update] function to the previous value.
 *
 * If the given [index] is out of bounds, this function instead returns an unaltered copy of the
 * list.
 */
inline fun <T> List<T>.replaceAt(index: Int, update: (oldValue: T) -> T): List<T> =
    List(size) { if (it == index) update(this[it]) else this[it] }
