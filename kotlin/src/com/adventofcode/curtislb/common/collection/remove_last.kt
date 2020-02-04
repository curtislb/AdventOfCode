package com.adventofcode.curtislb.common.collection

/**
 * Removes the last element from a mutable list.
 */
fun <T> MutableList<out T>.removeLast() { removeAt(lastIndex) }
