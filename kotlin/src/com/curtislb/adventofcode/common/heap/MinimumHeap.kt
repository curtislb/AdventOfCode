package com.curtislb.adventofcode.common.heap

import java.util.PriorityQueue

/**
 * A minimum-order heap, which stores values with associated keys and produces them in increasing order by key.
 */
class MinimumHeap<T> {
    /**
     * A map from each value in the heap to its current key.
     */
    private val priorityKeys: MutableMap<T, Long> = mutableMapOf()

    /**
     * A priority queue used internally to store and produce values.
     */
    private val priorityQueue: PriorityQueue<HeapEntry<T>> = PriorityQueue { entry, other ->
        entry.key.compareTo(other.key)
    }

    /**
     * The number of elements currently in the heap.
     */
    val size: Int get() = priorityKeys.size

    /**
     * Returns `true` if the heap is currently empty, or `false` otherwise.
     */
    fun isEmpty(): Boolean = priorityKeys.isEmpty()

    /**
     * Returns the key currently associated with [value] in the heap.
     */
    operator fun get(value: T): Long? = priorityKeys[value]

    /**
     * Adds a new [value] to the heap with the associated [key].
     *
     * @throws IllegalArgumentException If [value] is already in the heap.
     */
    fun add(value: T, key: Long) {
        require(value !in priorityKeys) { "Value is already in the heap: $value" }
        priorityKeys[value] = key
        priorityQueue.add(HeapEntry(value, key))
    }

    /**
     * Decreases the key associated with [value] in the heap to [newKey].
     *
     * @throws IllegalArgumentException If [value] is not in the heap, or if its associated key is at most [newKey].
     */
    fun decreaseKey(value: T, newKey: Long) {
        val oldKey = priorityKeys[value]
        require(oldKey != null) { "Value is not in the heap: $value" }
        require(oldKey > newKey) { "Value ($value) is in the heap with an equal or lower key: $oldKey <= $newKey" }
        priorityKeys[value] = newKey
        priorityQueue.add(HeapEntry(value, newKey))
    }

    /**
     * Returns and removes the entry with minimum key from the heap.
     *
     * @throws NoSuchElementException If the heap is currently empty.
     */
    fun popMinimum(): HeapEntry<T> {
        if (priorityKeys.isEmpty()) {
            throw NoSuchElementException("Failed to pop minimum. Heap is empty.")
        }

        // Pop outdated entries from the priority queue until a valid one is found.
        var entry: HeapEntry<T>
        var latestKey: Long?
        do {
            entry = priorityQueue.poll()
            latestKey = priorityKeys[entry.value]
        } while (latestKey == null || entry.key > latestKey)

        priorityKeys.remove(entry.value)
        return entry
    }
}
