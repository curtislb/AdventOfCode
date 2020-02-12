package com.curtislb.adventofcode.common.graph

import com.curtislb.adventofcode.common.heap.MinimumHeap

/**
 * Applies Dijkstra's shortest path algorithm and returns the shortest weighted distance from [start] to any node for
 * which [isGoal] returns `true`, with the edges from each node given by [getEdges].
 *
 * If there is no node reachable from [start] for which [isGoal] is `true`, this function instead returns `null`.
 */
fun <T> dijkstraShortestDistance(
    start: T,
    isGoal: (node: T) -> Boolean,
    getEdges: (node: T) -> Sequence<DirectedEdge<T>>
): Long? {
    val visited = mutableSetOf<T>()
    val nodeHeap = MinimumHeap<T>()
    nodeHeap.add(start, 0L)
    while (!nodeHeap.isEmpty()) {
        // Pop node with the shortest distance, and return if we've found the goal.
        val (node, distance) = nodeHeap.popMinimum()
        if (isGoal(node)) {
            return distance
        }

        // Update distances to all neighboring nodes.
        visited.add(node)
        getEdges(node).forEach { (neighbor, edgeWeight) ->
            if (neighbor !in visited) {
                val oldDistance = nodeHeap[neighbor]
                val newDistance = distance + edgeWeight
                when {
                    oldDistance == null -> nodeHeap.add(neighbor, newDistance)
                    newDistance < oldDistance -> nodeHeap.decreaseKey(neighbor, newDistance)
                }
            }
        }
    }

    // No goal node was found during the search.
    return null
}