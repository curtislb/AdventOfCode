package com.curtislb.adventofcode.common.collection

/**
 * A mutable collection that grows to a maximum [capacity], removing excess elements in FIFO order.
 *
 * @param capacity The maximum number of elements that this collection can contain.
 *
 * @throws IllegalArgumentException If [capacity] is negative.
 */
class FifoCache<T>(val capacity: Int) : Collection<T> {
    init {
        require(capacity >= 0) { "Capacity must be non-negative: $capacity" }
    }

    /**
     * An internal deque used to store the cached elements.
     */
    private val deque: ArrayDeque<T> = ArrayDeque(capacity)

    override val size: Int get() = deque.size

    override fun contains(element: T): Boolean = element in deque

    override fun containsAll(elements: Collection<T>): Boolean = deque.containsAll(elements)

    override fun isEmpty(): Boolean = deque.isEmpty()

    override fun iterator(): Iterator<T> = deque.iterator()

    /**
     * Returns the element at a given [index] in this collection.
     */
    operator fun get(index: Int): T = deque[index]

    /**
     * TODO
     */
    fun isFull(): Boolean = deque.size == capacity

    /**
     * Appends a new [element] to this collection, removing the least recently added element if necessary.
     */
    fun add(element: T) {
        if (isFull()) {
            deque.removeFirst()
        }
        deque.addLast(element)
    }
}
