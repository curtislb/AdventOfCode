package com.curtislb.adventofcode.common.graph

import com.curtislb.adventofcode.common.heap.MinimumHeap

/**
 * An implementation of Dijkstra's shortest path graph search algorithm.
 */
abstract class Dijkstra<T> {
    /**
     * Returns all weighted edges from [node] to adjacent nodes in the graph.
     */
    protected abstract fun getEdges(node: T): Iterable<DirectedEdge<T>>

    /**
     * Returns the minimal path cost from [source] to any node in the graph for which [isGoal]
     * returns `true`.
     *
     * If there is no node reachable from [source] for which [isGoal] is `true`, this function
     * instead returns `null`.
     */
    fun findShortestDistance(source: T, isGoal: (node: T) -> Boolean): Long? {
        val visited = mutableSetOf<T>()
        val nodeHeap = MinimumHeap<T>().apply { addOrDecreaseKey(source, 0L) }
        while (!nodeHeap.isEmpty()) {
            // Pop node with the shortest distance, and return if we've found the goal
            val (node, distance) = nodeHeap.popMinimum()
            if (isGoal(node)) {
                return distance
            }

            // Update distances to all neighboring nodes
            visited.add(node)
            for (edge in getEdges(node)) {
                if (edge.node !in visited) {
                    val oldDistance = nodeHeap[edge.node]
                    val newDistance = distance + edge.weight
                    if (oldDistance == null || oldDistance > newDistance) {
                        nodeHeap.addOrDecreaseKey(edge.node, distance + edge.weight)
                    }
                }
            }
        }

        // No goal node was found during the search
        return null
    }
}
