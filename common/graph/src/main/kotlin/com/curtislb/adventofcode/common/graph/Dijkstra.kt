package com.curtislb.adventofcode.common.graph

import com.curtislb.adventofcode.common.heap.MinimumHeap

/**
 * Applies Dijkstra's shortest path algorithm from [source] and returns the shortest weighted
 * distance to any node for which [isGoal] is `true`, with the edges from each node given by
 * [getEdges].
 *
 * If there is no node reachable from [source] for which [isGoal] is `true`, this function instead
 * returns `null`.
 */
fun <T> dijkstraShortestDistance(
    source: T,
    isGoal: (node: T) -> Boolean,
    getEdges: (node: T) -> Sequence<DirectedEdge<T>>
): Long? {
    val visited = mutableSetOf<T>()
    val nodeHeap = MinimumHeap<T>().apply { add(source, 0L) }
    while (!nodeHeap.isEmpty()) {
        // Pop node with the shortest distance, and return if we've found the goal.
        val (node, distance) = nodeHeap.popMinimum()
        if (isGoal(node)) {
            return distance
        }

        // Update distances to all neighboring nodes.
        visited.add(node)
        for ((neighbor, edgeWeight) in getEdges(node)) {
            if (neighbor !in visited) {
                val oldDistance = nodeHeap[neighbor]
                val newDistance = distance + edgeWeight
                if (oldDistance == null) {
                    nodeHeap.add(neighbor, newDistance)
                } else if (newDistance < oldDistance) {
                    nodeHeap.decreaseKey(neighbor, newDistance)
                }
            }
        }
    }

    // No goal node was found during the search.
    return null
}
