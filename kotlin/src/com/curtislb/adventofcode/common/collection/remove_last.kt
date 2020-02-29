package com.curtislb.adventofcode.common.collection

/**
 * Removes the last element from this mutable list.
 */
fun MutableList<*>.removeLast() { removeAt(lastIndex) }
