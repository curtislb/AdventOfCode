package com.adventofcode.curtislb.common.intcode.io

import java.util.Deque
import java.util.LinkedList

/**
 * TODO
 */
class InputSequencer {
    private var sequences: Deque<Iterator<Int>> = LinkedList()

    /**
     * TODO
     */
    fun queue(inputSequence: Sequence<Int>) {
        sequences.addLast(inputSequence.iterator())
    }

    /**
     * TODO
     */
    fun clear() { sequences = LinkedList() }

    /**
     * TODO
     */
    fun hasNext(): Boolean {
        dropEmptySequences()
        return sequences.isNotEmpty()
    }

    /**
     * TODO
     */
    fun next(): Int {
        dropEmptySequences()
        if (sequences.isEmpty()) {
            throw IllegalStateException("Can't get next value. All queued values have been exhausted")
        }
        return sequences.first.next()
    }

    /**
     * TODO
     */
    private fun dropEmptySequences() {
        while (sequences.isNotEmpty() && !sequences.first.hasNext()) {
            sequences.removeFirst()
        }
    }
}
