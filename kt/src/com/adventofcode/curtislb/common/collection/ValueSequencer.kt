package com.adventofcode.curtislb.common.collection

import java.util.Deque
import java.util.LinkedList

/**
 * Produces values from one or more sequences in the order they were provided.
 */
class ValueSequencer<T> {
    private var sequences: Deque<Iterator<T>> = LinkedList()

    /**
     * Queues an additional sequence of values.
     * @param valueSequence A [Sequence] of values to be provided in order after all previous values.
     */
    fun queue(valueSequence: Sequence<T>) {
        sequences.addLast(valueSequence.iterator())
    }

    /**
     * Resets the state by removing all pending values.
     */
    fun clear() { sequences = LinkedList() }

    /**
     * Determines if there is at least one value to be provided.
     * @return `true` if there is at least one non-empty [Sequence] of values remaining, or `false` otherwise.
     */
    fun hasNext(): Boolean {
        dropEmptySequences()
        return sequences.isNotEmpty()
    }

    /**
     * Produces the next value in sequence, if one is available.
     * @return The next pending value, in the order provided.
     * @throws IllegalStateException If there is no next value to return.
     */
    fun next(): T {
        dropEmptySequences()
        if (sequences.isEmpty()) {
            throw IllegalStateException("Can't get next value. All queued values have been exhausted.")
        }
        return sequences.first.next()
    }

    /**
     * Removes all empty value sequences from the front of [sequences].
     */
    private fun dropEmptySequences() {
        while (sequences.isNotEmpty() && !sequences.first.hasNext()) {
            sequences.removeFirst()
        }
    }
}
