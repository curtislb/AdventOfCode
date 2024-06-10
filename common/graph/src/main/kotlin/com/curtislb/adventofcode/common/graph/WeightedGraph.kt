package com.curtislb.adventofcode.common.graph

import com.curtislb.adventofcode.common.heap.MinimumHeap

/**
 * A graph consisting of unique nodes of type [V], connected by directed edges with integer weights.
 */
abstract class WeightedGraph<V> {
    /**
     * An edge from one node to another in a weighted graph.
     *
     * @property node The destination node.
     * @property weight The cost incurred by traversing this edge to the given [node].
     */
    data class Edge<out T>(val node: T, val weight: Long)

    /**
     * Returns all weighted edges from [node] to adjacent nodes in the graph.
     */
    protected abstract fun getEdges(node: V): Iterable<Edge<V>>

    /**
     * Returns the minimal path cost from [source] to any node in the graph for which [isGoal]
     * returns `true`, using the A* path search algorithm with the given [heuristic] function.
     *
     * If there is no path from [source] to any goal node, this function instead returns `null`.
     *
     * The [heuristic] function should return an estimate of the minimal path cost from a node to
     * any goal node. It *must not* return a negative value or a value that is greater than the
     * minimal path cost. Otherwise, the behavior of this function is undefined.
     */
    fun aStarDistance(
        source: V,
        heuristic: (node: V) -> Long,
        isGoal: (node: V) -> Boolean
    ): Long? {
        val nodeHeap = MinimumHeap<V>().apply { addOrDecreaseKey(source, heuristic(source)) }
        val distanceMap = mutableMapOf(source to 0L)
        while (!nodeHeap.isEmpty()) {
            // Check the next node with the lowest f-score
            val (node, _) = nodeHeap.popMinimum()
            val distance = distanceMap[node]!!
            if (isGoal(node)) {
                return distance
            }

            // Update the f-score of each neighboring node
            for (edge in getEdges(node)) {
                val oldDistance = distanceMap[edge.node]
                val newDistance = distance + edge.weight
                if (oldDistance == null || oldDistance > newDistance) {
                    nodeHeap.addOrDecreaseKey(edge.node, newDistance + heuristic(edge.node))
                    distanceMap[edge.node] = newDistance
                }
            }
        }

        // Search ended without finding a goal node
        return null
    }

    /**
     * Returns the minimal path cost from [source] to any node in the graph for which [isGoal]
     * returns `true`, using Dijkstra's shortest path algorithm.
     *
     * If there is no path from [source] to any goal node, this function instead returns `null`.
     */
    fun dijkstraDistance(source: V, isGoal: (node: V) -> Boolean): Long? {
        val nodeHeap = MinimumHeap<V>().apply { addOrDecreaseKey(source, 0L) }
        val visited = mutableSetOf<V>()
        while (!nodeHeap.isEmpty()) {
            // Check the next node with the shortest distance
            val (node, distance) = nodeHeap.popMinimum()
            if (isGoal(node)) {
                return distance
            }

            visited.add(node)

            // Process edges from this node and update distances to neighbors
            for ((neighbor, edgeWeight) in getEdges(node)) {
                // Don't re-add a previously visited node
                if (neighbor in visited) {
                    continue
                }

                // Update the shortest known distance if needed
                val oldDistance = nodeHeap[neighbor]
                val newDistance = distance + edgeWeight
                if (oldDistance == null || oldDistance > newDistance) {
                    nodeHeap.addOrDecreaseKey(neighbor, newDistance)
                }
            }
        }

        // Search ended without finding a goal node
        return null
    }
}
