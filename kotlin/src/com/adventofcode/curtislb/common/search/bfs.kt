package com.adventofcode.curtislb.common.search

import java.util.LinkedList
import java.util.Queue

/**
 * Applies a function to all nodes reachable via a breadth-first graph search.
 * @param start The starting node from which to conduct the search.
 * @param getNeighbors A function that maps each node to an [Iterable] of the nodes that neighbor it.
 * @param action A function to be applied to each visited node, along with its distance from [start].
 */
fun <T> bfsApply(start: T, getNeighbors: (point: T) -> Iterable<T>, action: (node: T, distance: Long) -> Unit) {
    val visited = mutableSetOf<T>()
    val frontier: Queue<Pair<T, Long>> = LinkedList()
    frontier.offer(Pair(start, 0L))
    while (frontier.isNotEmpty()) {
        val (node, distance) = frontier.poll()
        action(node, distance)
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
fun <T> bfsDistance(start: T, getNeighbors: (point: T) -> Iterable<T>, isGoal: (point: T) -> Boolean): Long? {
    var result: Long? = null
    bfsApply(start, getNeighbors) { node, distance ->
        if (isGoal(node)) {
            result = distance
            return@bfsApply
        }
    }
    return result
}
