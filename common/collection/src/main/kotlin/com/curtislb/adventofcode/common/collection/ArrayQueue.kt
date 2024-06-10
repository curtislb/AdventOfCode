package com.curtislb.adventofcode.common.collection

/**
 * Resizable-array implementation of the queue data structure.
 *
 * @param E The type of elements contained in the queue.
 *
 * @constructor Creates a new empty instance of [ArrayQueue].
 */
class ArrayQueue<E> : Collection<E> {
    /**
     * A deque of elements currently in the queue.
     */
    private val deque: ArrayDeque<E> = ArrayDeque()

    override val size: Int
        get() = deque.size

    override fun isEmpty(): Boolean = deque.isEmpty()

    override fun contains(element: E): Boolean = element in deque

    override fun containsAll(elements: Collection<E>): Boolean = deque.containsAll(elements)

    override fun iterator(): Iterator<E> = deque.iterator()

    /**
     * Adds the specified [element] to the queue.
     */
    fun offer(element: E) {
        deque.addLast(element)
    }

    /**
     * Removes and returns the least-recently-added element in the queue.
     *
     * @throws NoSuchElementException If the queue is empty.
     */
    fun poll(): E = deque.removeFirst()
}

/**
 * Returns a new instance of [ArrayQueue] with the given [elements] in FIFO order.
 */
fun <T> arrayQueueOf(vararg elements: T): ArrayQueue<T> = ArrayQueue<T>().apply {
    for (element in elements) {
        offer(element)
    }
}
