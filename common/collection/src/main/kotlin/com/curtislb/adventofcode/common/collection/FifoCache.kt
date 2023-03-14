package com.curtislb.adventofcode.common.collection

/**
 * A mutable collection that grows to a maximum [capacity], removing excess elements in FIFO order.
 *
 * @param E The type of elements contained in the cache.
 * @property capacity The maximum number of elements the cache can contain.
 *
 * @constructor Creates a new empty instance of [FifoCache] with the given [capacity].
 *
 * @throws IllegalArgumentException If [capacity] is negative or 0.
 */
class FifoCache<E>(val capacity: Int) : Collection<E> {
    init {
        require(capacity > 0) { "Capacity must be positive: $capacity" }
    }

    /**
     * An internal deque used to store the cached elements.
     */
    private val deque: ArrayDeque<E> = ArrayDeque(capacity)

    override val size: Int get() = deque.size

    override fun isEmpty(): Boolean = deque.isEmpty()

    override fun contains(element: E): Boolean = element in deque

    override fun containsAll(elements: Collection<E>): Boolean = deque.containsAll(elements)

    override fun iterator(): Iterator<E> = deque.iterator()

    /**
     * Returns the element at the given [index] in the cache.
     */
    operator fun get(index: Int): E = deque[index]

    /**
     * Returns `true` if the cache contains the maximum allowed number of elements.
     */
    fun isFull(): Boolean = deque.size == capacity

    /**
     * Appends a new [element] to the cache, removing the least recently added element if needed.
     */
    fun add(element: E) {
        if (isFull()) {
            deque.removeFirst()
        }
        deque.addLast(element)
    }
}
