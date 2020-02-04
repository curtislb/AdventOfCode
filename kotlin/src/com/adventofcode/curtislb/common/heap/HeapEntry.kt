package com.adventofcode.curtislb.common.heap

/**
 * An entry in a heap, consisting of a value and its key within the heap.
 * @param value An arbitrary value that is stored in the heap with an associated [key].
 * @param key A [Long] key associated with [value] that is used for ordering within the heap.
 */
data class HeapEntry<out T>(val value: T, val key: Long)
