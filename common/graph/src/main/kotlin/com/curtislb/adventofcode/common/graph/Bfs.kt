package com.curtislb.adventofcode.common.graph

import java.util.ArrayDeque

/**
 * An implementation of the breadth-first graph search algorithm.
 */
abstract class Bfs<T> {
    /**
     * Returns all nodes that are adjacent to and reachable from [node].
     */
    protected abstract fun getNeighbors(node: T): Iterable<T>

    /**
     * Returns the shortest distance from [source] to any node for which [isGoal] returns `true`.
     *
     * If no goal node is reachable from [source], this function instead returns `null`.
     */
    fun findShortestDistance(source: T, isGoal: (node: T) -> Boolean): Long? {
        var result: Long? = null
        forEachNode(source) { node, distance ->
            if (isGoal(node)) {
                result = distance
                true // Done searching
            } else {
                false // Not done searching
            }
        }
        return result
    }

    /**
     * Applies the [process] function to each node reachable from the given [source] node in
     * breadth-first order, along with the shortest path distance from [source] to that node.
     *
     * This function continues searching until all nodes reachable from [source] have been processed
     * or until [process] returns `true`, indicating that the search should be terminated early.
     */
    fun forEachNode(source: T, process: (node: T, distance: Long) -> Boolean) {
        val searchQueue = ArrayDeque<Pair<T, Long>>().apply { offer(Pair(source, 0L)) }
        val visited = mutableSetOf<T>()
        while (searchQueue.isNotEmpty()) {
            val (node, distance) = searchQueue.poll()

            // Ignore this node if it was previously visited
            if (node in visited) {
                continue
            }

            // Process this node, terminating the search if necessary
            if (process(node, distance)) {
                return
            }

            // Enqueue all unvisited neighboring nodes with distances
            visited.add(node)
            for (neighbor in getNeighbors(node)) {
                if (neighbor !in visited) {
                    searchQueue.offer(Pair(neighbor, distance + 1L))
                }
            }
        }
    }
}
