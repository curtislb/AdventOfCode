package com.curtislb.adventofcode.common.graph

import com.curtislb.adventofcode.common.heap.MinimumHeap

/**
 * An implementation of the A* graph search algorithm.
 */
abstract class AStar<T> {
    /**
     * Returns all weighted edges from [node] to adjacent nodes in the graph.
     */
    protected abstract fun getEdges(node: T): Iterable<DirectedEdge<T>>

    /**
     * Returns the minimal path cost from [source] to any node in the graph for which [isGoal]
     * returns `true`, using the given admissible [heuristic] function.
     *
     * If there is no path from [source] to a goal node via edges given by the [getEdges] function,
     * this function instead returns `null`.
     *
     * The [heuristic] function should return an estimate of the minimal path cost from a node to
     * any goal node. It must *not* return a negative value or a value that is greater than the
     * minimal path cost from a node. If it does, the behavior of this function becomes undefined.
     */
    fun findShortestDistance(
        source: T,
        heuristic: (node: T) -> Long,
        isGoal: (node: T) -> Boolean
    ): Long? {
        // Add the source node to the search set
        val distanceMap = mutableMapOf(source to 0L)
        val nodeHeap = MinimumHeap<T>().apply { addOrDecreaseKey(source, heuristic(source)) }
        while (!nodeHeap.isEmpty()) {
            // Check the next node with the lowest f-score
            val (node, _) = nodeHeap.popMinimum()
            if (isGoal(node)) {
                return distanceMap[node]
            }

            // Check each directed edge from this node
            for (edge in getEdges(node)) {
                // If this is the new shortest path to a node, update the node's f-score
                val oldDistance = distanceMap[edge.node]
                val newDistance = distanceMap[node]?.let { it + edge.weight } ?: Long.MAX_VALUE
                if (oldDistance == null || oldDistance > newDistance) {
                    distanceMap[edge.node] = newDistance
                    nodeHeap.addOrDecreaseKey(edge.node, newDistance + heuristic(edge.node))
                }
            }
        }

        // Finished searching without finding a goal node
        return null
    }
}
