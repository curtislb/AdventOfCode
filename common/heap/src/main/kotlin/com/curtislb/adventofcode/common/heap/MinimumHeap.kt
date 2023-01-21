package com.curtislb.adventofcode.common.heap

import java.util.PriorityQueue

/**
 * A minimum-order heap, which stores elements with associated keys and produces them in increasing
 * order by key.
 */
class MinimumHeap<E> {
    /**
     * A map from each value in the heap to its current key.
     */
    private val priorityKeys: MutableMap<E, Long> = mutableMapOf()

    /**
     * A priority queue used internally to store and produce elements.
     */
    private val priorityQueue: PriorityQueue<HeapEntry<E>> = PriorityQueue { entry, other ->
        entry.key.compareTo(other.key)
    }

    /**
     * The number of elements currently in the heap.
     */
    val size: Int
        get() = priorityKeys.size

    /**
     * Returns `true` if the given [element] is in the heap.
     */
    operator fun contains(element: E) = element in priorityKeys

    /**
     * Returns the key in the heap currently associated with [element].
     */
    operator fun get(element: E): Long? = priorityKeys[element]

    /**
     * Returns `true` if the heap currently contains no elements.
     */
    fun isEmpty(): Boolean = priorityKeys.isEmpty()

    /**
     * Adds a new [element] to the heap with the associated [key], or decreases its key value in the
     * heap to the given key if it's already present.
     *
     * @throws IllegalArgumentException If [element] is in the heap with an equal or lower [key].
     */
    fun addOrDecreaseKey(element: E, key: Long) {
        val oldKey = priorityKeys[element]
        require(oldKey == null || oldKey > key) {
            "Element $element is in the heap with an equal or lower key: $oldKey <= $key"
        }
        priorityKeys[element] = key
        priorityQueue.add(HeapEntry(element, key))
    }

    /**
     * Returns and removes the minimum-key entry from the heap.
     *
     * @throws NoSuchElementException If the heap is currently empty.
     */
    fun popMinimum(): HeapEntry<E> {
        if (priorityKeys.isEmpty()) {
            throw NoSuchElementException("Failed to pop minimum. Heap is empty.")
        }

        // Pop outdated entries from the priority queue until a valid one is found
        var entry: HeapEntry<E>
        var latestKey: Long?
        do {
            entry = priorityQueue.poll()
            latestKey = priorityKeys[entry.element]
        } while (latestKey == null || entry.key > latestKey)

        priorityKeys.remove(entry.element)
        return entry
    }
}
