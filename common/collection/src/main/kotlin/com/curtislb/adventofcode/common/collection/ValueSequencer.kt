package com.curtislb.adventofcode.common.collection

import java.util.ArrayDeque
import java.util.Deque

/**
 * Produces values from one or more sequences in the order they were provided.
 */
class ValueSequencer<E> {
    /**
     * All currently queued sequences of values.
     */
    private var sequences: Deque<Iterator<E>> = ArrayDeque()

    /**
     * Queues an additional [valueSequence] after all previous values.
     */
    fun queue(valueSequence: Sequence<E>) {
        sequences.addLast(valueSequence.iterator())
    }

    /**
     * Resets the state of this sequencer by removing all queued values.
     */
    fun clear() {
        sequences = ArrayDeque()
    }

    /**
     * Returns `true` if this sequencer has at least one queued value, or `false` otherwise.
     */
    fun hasNext(): Boolean {
        dropEmptySequences()
        return sequences.isNotEmpty()
    }

    /**
     * Returns and removes the next queued value from this sequencer.
     *
     * @throws NoSuchElementException If there is no queued value to return.
     */
    fun next(): E {
        dropEmptySequences()
        if (sequences.isEmpty()) {
            throw NoSuchElementException("Can't get next value. All queued values are exhausted.")
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
