package com.curtislb.adventofcode.common.graph

/**
 * An implementation of the depth-first graph search algorithm.
 */
abstract class Dfs<T> {
    /**
     * Returns all nodes that are adjacent to and reachable from [node].
     */
    protected abstract fun getNeighbors(node: T): Sequence<T>

    /**
     * Returns a map from each node for which [isGoal] returns `true` that is reachable via
     * depth-first traversal from [source] to a list of all paths from [source] to that node.
     *
     * Each path is represented by an ordered list of all nodes (excluding [source]) along the path
     * from [source] to the goal node for that path.
     */
    fun findAllPaths(source: T, isGoal: (node: T) -> Boolean): Map<T, List<List<T>>> {
        val paths = mutableMapOf<T, MutableList<List<T>>>()

        // If source is a goal node, add the zero-length path to it
        if (isGoal(source)) {
            paths[source] = mutableListOf(emptyList())
        }

        findAllPathsInternal(
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
    private fun findAllPathsInternal(
        node: T,
        paths: MutableMap<T, MutableList<List<T>>>,
        visited: MutableSet<T>,
        pathPrefix: MutableList<T>,
        isGoal: (node: T) -> Boolean
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
            findAllPathsInternal(
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
