package com.curtislb.adventofcode.common.collection

/**
 * Returns the element at the given [index], modulo the size of this list.
 */
fun <T> List<T>.getCyclic(index: Int) = this[index.mod(size)]
