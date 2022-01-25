package com.curtislb.adventofcode.common.collection

import java.util.Comparator
import java.util.PriorityQueue

/**
 * Returns the given [count] of the largest values in this iterable.
 */
fun <T : Comparable<T>> Iterable<T>.takeLargest(count: Int): List<T> {
    val priorityQueue = PriorityQueue<T>(Comparator.reverseOrder())
    for (item in this) {
        priorityQueue.offer(item)
    }
    return List(count) { priorityQueue.poll() }
}
