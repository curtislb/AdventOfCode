package com.curtislb.adventofcode.common.comparison

import java.util.Comparator
import java.util.PriorityQueue

/**
 * Returns a list of size [count], containing the largest values in this iterable.
 *
 * If the number of values in this iterable is less than [count], this function instead returns a
 * list of all the values.
 */
fun <T : Comparable<T>> Iterable<T>.takeLargest(count: Int): List<T> {
    require(count >= 0) { "Count must be non-negative: $count" }

    val values = toList()

    // Fewer values than count; return them all
    if (values.size <= count) {
        return values
    }

    // Heapify values using reverse comparator (max-first) order
    val maxPQ = PriorityQueue(
        object : PriorityQueue<T>(Comparator.reverseOrder()) {
            override fun toArray(): Array<Any> = values.toTypedArray()
        }
    )

    return List(count) { maxPQ.poll() }
}
