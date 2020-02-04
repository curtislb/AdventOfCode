package com.adventofcode.curtislb.common.heap

import java.util.PriorityQueue

/**
 * A minimum-order heap, which stores values with associated keys and produces them in increasing order by key.
 */
class MinimumHeap<T> {
    /**
     * A [MutableMap] from each value in the heap to its current key.
     */
    private val priorityKeys: MutableMap<T, Long> = mutableMapOf()

    /**
     * A [PriorityQueue] used internally to store and produce values.
     */
    private val priorityQueue: PriorityQueue<HeapEntry<T>> = PriorityQueue(Comparator { entry, other ->
        entry.key.compareTo(other.key)
    })

    /**
     * The number of elements currently in the heap.
     */
    val size: Int
        get() = priorityKeys.size

    /**
     * Checks if the heap is currently empty.
     * @return `true` if there are no values currently in the heap, or `false` otherwise.
     */
    fun isEmpty(): Boolean = priorityKeys.isEmpty()

    /**
     * Gets the current key associated with a value in the heap.
     * @param value A value that may be stored in the heap with an associated key.
     * @return The [Long] key currently associated with [value] in the heap, or `null` if [value] is not in the heap.
     */
    operator fun get(value: T): Long? = priorityKeys[value]

    /**
     * Adds a new value to the heap with an associated key.
     * @param value The new value to be added to the heap.
     * @param key The [Long] key to be associated with [value] in the heap.
     * @throws IllegalArgumentException If [value] is already in the heap.
     */
    fun add(value: T, key: Long) {
        if (value in priorityKeys) {
            throw IllegalArgumentException("Failed to add. Value is already in heap: $value")
        }
        priorityKeys[value] = key
        priorityQueue.add(HeapEntry(value, key))
    }

    /**
     * Decreases the key associated with a value in the heap.
     * @param value The value in the heap whose key should be decreased.
     * @param newKey The new key to be associated with [value] in the heap.
     * @throws IllegalArgumentException If [value] is not in the heap, or has an associated key that is less than or
     *  equal to [newKey].
     */
    fun decreaseKey(value: T, newKey: Long) {
        val oldKey = priorityKeys[value]
        if (oldKey == null || oldKey <= newKey) {
            throw IllegalArgumentException("Failed to decrease key. Value is not in heap, or in heap with lower key.")
        }
        priorityKeys[value] = newKey
        priorityQueue.add(HeapEntry(value, newKey))
    }

    /**
     * Removes the entry with minimum key from the heap and returns it.
     * @return A [HeapEntry] representing the value with minimum key in the heap.
     * @throws IllegalArgumentException If the heap is currently empty.
     */
    fun popMinimum(): HeapEntry<T> {
        if (priorityKeys.isEmpty()) {
            throw IllegalStateException("Failed to pop minimum. Heap is empty.")
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
