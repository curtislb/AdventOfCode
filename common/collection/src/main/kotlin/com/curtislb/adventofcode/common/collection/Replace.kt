package com.curtislb.adventofcode.common.collection

/**
 * Returns a copy of this list with the value at the given [index] replaced by the result of
 * applying [getNewValue] to the previous value.
 */
inline fun <T> List<T>.replace(index: Int, getNewValue: (oldValue: T) -> T): List<T> =
    List(size) { if (it == index) getNewValue(this[it]) else this[it] }
