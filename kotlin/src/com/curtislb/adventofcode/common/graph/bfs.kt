package com.curtislb.adventofcode.common.graph

import java.util.ArrayDeque
import java.util.Queue

/**
 * Applies the function [process] to all nodes reachable via a breadth-first graph search from [start], with the
 * neighbors of each node given by [getNeighbors].
 *
 * Continues searching until all nodes reachable from [start] have been processed or until [process] returns `true`,
 * indicating that the search should be terminated early.
 */
fun <T> bfsApply(start: T, getNeighbors: (node: T) -> Sequence<T>, process: (node: T, distance: Long) -> Boolean) {
    val visited = mutableSetOf<T>()
    val frontier: Queue<Pair<T, Long>> = ArrayDeque()
    frontier.offer(Pair(start, 0L))
    while (frontier.isNotEmpty()) {
        // Dequeue and process the next node, terminating the search if necessary.
        val (node, distance) = frontier.poll()
        if (process(node, distance)) {
            return
        }

        // Enqueue all unvisited neighboring nodes with distances.
        visited.add(node)
        getNeighbors(node).forEach { neighbor ->
            if (neighbor !in visited) {
                frontier.offer(Pair(neighbor, distance + 1L))
            }
        }
    }
}

/**
 * Conducts a breadth-first graph search and returns the shortest distance from [start] to any node for which [isGoal]
 * returns `true`, with the neighbors of each node given by [getNeighbors].
 *
 * If there is no node reachable from [start] for which [isGoal] is `true`, this function instead returns `null`.
 */
fun <T> bfsDistance(start: T, isGoal: (node: T) -> Boolean, getNeighbors: (node: T) -> Sequence<T>): Long? {
    var result: Long? = null
    bfsApply(start, getNeighbors) { node, distance ->
        if (isGoal(node)) {
            result = distance
            true // Done searching.
        } else {
            false // Not done searching.
        }
    }
    return result
}
