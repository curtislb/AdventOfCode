package com.curtislb.adventofcode.common.heap

/**
 * An entry in a heap, consisting of a [value] and its associated [key].
 */
data class HeapEntry<out T>(val value: T, val key: Long)
