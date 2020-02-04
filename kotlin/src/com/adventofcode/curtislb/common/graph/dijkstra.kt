package com.adventofcode.curtislb.common.graph

import com.adventofcode.curtislb.common.heap.MinimumHeap

/**
 * Uses Dijkstra's shortest path algorithm to determine the minimum distance from a starting node to a goal node.
 * @param start The starting node from which to conduct the search.
 * @param isGoal A function that returns `true` if (and only if) a given node is a goal node.
 * @param getEdges A function that maps each node to a [Sequence] of [DirectedEdge]s originating from that node.
 * @return The shortest weighted distance from [start] to the nearest node for which [isGoal] is `true`, or `null` if no
 *  such node can be reached from [start].
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
