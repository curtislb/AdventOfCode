package com.curtislb.adventofcode.common.collection

/**
 * Removes the last element from this mutable list.
 */
fun <T> MutableList<out T>.removeLast() { removeAt(lastIndex) }
