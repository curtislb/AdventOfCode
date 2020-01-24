package com.adventofcode.curtislb.common.collection

/**
 * Removes the last element from a mutable list.
 */
fun <T> MutableList<T>.removeLast() { removeAt(lastIndex) }
