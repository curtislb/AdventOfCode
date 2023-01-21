package com.curtislb.adventofcode.common.heap

/**
 * An entry in a heap, consisting of an [element] and its associated [key].
 */
data class HeapEntry<out T>(val element: T, val key: Long)
