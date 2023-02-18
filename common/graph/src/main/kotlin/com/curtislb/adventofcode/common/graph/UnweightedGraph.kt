package com.curtislb.adventofcode.common.graph

/**
 * A graph consisting of unique nodes of type [V], connected by directed edges.
 */
abstract class UnweightedGraph<V> {
    /**
     * Returns all nodes in the graph to which there is a directed edge from the given [node].
     */
    protected abstract fun getNeighbors(node: V): Iterable<V>

    /**
     * Performs a breadth-first search from [source] and applies the [process] function to each node
     * that is reached in order, along with the shortest distance from [source] to that node.
     *
     * This function continues searching until all nodes reachable from [source] have been processed
     * or until [process] returns `true`, indicating that the search should be terminated early.
     */
    fun bfsApply(source: V, process: (node: V, distance: Long) -> Boolean) {
        val searchQueue = ArrayDeque<Pair<V, Long>>().apply { addLast(Pair(source, 0L)) }
        val visited = mutableSetOf<V>()
        while (searchQueue.isNotEmpty()) {
            val (node, distance) = searchQueue.removeFirst()

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
                    searchQueue.addLast(Pair(neighbor, distance + 1L))
                }
            }
        }
    }

    /**
     * Performs a breadth-first search from [source] and returns the shortest distance to any
     * reachable node for which [isGoal] returns `true`.
     *
     * If no goal node is reachable from [source], this function instead returns `null`.
     */
    fun bfsDistance(source: V, isGoal: (node: V) -> Boolean): Long? {
        var result: Long? = null
        bfsApply(source) { node, distance ->
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
     * Performs a depth-first search from [source] and returns a map from each reachable node for
     * which [isGoal] returns `true` to a list of all paths from [source] to that node.
     *
     * Each path can pass through a node at most once and is represented by an ordered list of all
     * nodes along the path from [source] (exclusive) to the goal node (inclusive).
     */
    fun dfsPaths(source: V, isGoal: (node: V) -> Boolean): Map<V, List<List<V>>> {
        val paths = mutableMapOf<V, MutableList<List<V>>>()

        // If source is a goal node, add the zero-length path to it
        if (isGoal(source)) {
            paths[source] = mutableListOf(emptyList())
        }

        dfsPathsRecursive(
            node = source,
            paths = paths,
            visited = mutableSetOf(),
            pathPrefix = mutableListOf(),
            isGoal = isGoal
        )
        return paths
    }

    /**
     * Populates the [paths] map with all paths from [node] to any goal node, prepending the given
     * [pathPrefix] and ignoring any nodes that have already been [visited] along the current path.
     */
    private fun dfsPathsRecursive(
        node: V,
        paths: MutableMap<V, MutableList<List<V>>>,
        visited: MutableSet<V>,
        pathPrefix: MutableList<V>,
        isGoal: (node: V) -> Boolean
    ) {
        visited.add(node)
        for (neighbor in getNeighbors(node)) {
            // Ignore visited nodes to prevent unnecessary backtracking
            if (neighbor in visited) {
                continue
            }

            // If neighbor is a goal node, record the current path to it
            pathPrefix.add(neighbor)
            if (isGoal(neighbor)) {
                paths.getOrPut(neighbor) { mutableListOf() }.add(pathPrefix.toList())
            }

            // Continue searching recursively from neighbor
            dfsPathsRecursive(
                node = neighbor,
                paths = paths,
                visited = visited,
                pathPrefix = pathPrefix,
                isGoal = isGoal
            )

            pathPrefix.removeLast()
        }

        visited.remove(node)
    }
}
