package com.curtislb.adventofcode.common.collection

/**
 * Produces values from one or more value sequences in the order they were provided.
 *
 * @param E The type of values produced by the sequencer.
 *
 * @constructor Creates a new empty instance of [ValueSequencer].
 */
class ValueSequencer<E> {
    /**
     * Iterators for all currently queued value sequences.
     */
    private val iterators: ArrayDeque<Iterator<E>> = ArrayDeque()

    /**
     * Returns `true` if the sequencer has no queued values.
     */
    fun isEmpty(): Boolean {
        dropEmptyIterators()
        return iterators.isEmpty()
    }

    /**
     * Queues a sequence of [values] to be produced after all previous values.
     */
    fun offer(values: Sequence<E>) {
        iterators.addLast(values.iterator())
    }

    /**
     * Returns and removes the next queued value from the sequencer.
     *
     * @throws NoSuchElementException If there is no queued value to return.
     */
    fun poll(): E {
        dropEmptyIterators()
        return iterators.first().next()
    }

    /**
     * Resets the state of the sequencer by removing all queued values.
     */
    fun clear() {
        iterators.clear()
    }

    /**
     * Removes all empty value sequences from the front of [iterators].
     */
    private fun dropEmptyIterators() {
        while (iterators.firstOrNull()?.hasNext() == false) {
            iterators.removeFirst()
        }
    }
}
