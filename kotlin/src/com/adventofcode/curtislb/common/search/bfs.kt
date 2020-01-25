package com.adventofcode.curtislb.common.search

import java.util.LinkedList
import java.util.Queue

/**
 * Applies a function to all nodes reachable via a breadth-first graph search.
 * @param start The starting node from which to conduct the search.
 * @param getNeighbors A function that maps each node to an [Iterable] of the nodes that neighbor it.
 * @param process A function to be applied to each node, along with its distance from [start]. Returns a [Boolean]
 *  indicating whether the search should terminate after processing this node.
 */
fun <T> bfsApply(start: T, getNeighbors: (node: T) -> Iterable<T>, process: (node: T, distance: Long) -> Boolean) {
    val visited = mutableSetOf<T>()
    val frontier: Queue<Pair<T, Long>> = LinkedList()
    frontier.offer(Pair(start, 0L))
    while (frontier.isNotEmpty()) {
        // Process the next node, terminating the search if necessary.
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
 * Conducts a breadth-first graph search to determine the shortest distance from a starting node to a goal node.
 * @param start The starting node from which to conduct the search.
 * @param getNeighbors A function that maps each node to an [Iterable] of the nodes that neighbor it.
 * @param isGoal A function that returns `true` if (and only if) a given node is the goal node.
 * @return The shortest distance from [start] to the nearest node for which [isGoal] is `true`, or `null` if no such
 *  node can be reached via breadth-first search from [start].
 */
fun <T> bfsDistance(start: T, getNeighbors: (node: T) -> Iterable<T>, isGoal: (node: T) -> Boolean): Long? {
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
