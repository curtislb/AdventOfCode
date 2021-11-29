package com.curtislb.adventofcode.common.graph

/**
 * Conducts a depth-first graph search from [source] and returns a map from each reachable node for
 * which [isGoal] is `true` to a list of all paths to that node, with the neighbors of each node
 * given by [getNeighbors].
 */
fun <T> dfsPaths(
    source: T,
    isGoal: (node: T) -> Boolean,
    getNeighbors: (node: T) -> Sequence<T>
): Map<T, List<List<T>>> {
    val paths = mutableMapOf<T, MutableList<List<T>>>()

    // If source is a goal node, add the zero-length path to it.
    if (isGoal(source)) {
        paths[source] = mutableListOf(emptyList())
    }

    dfsPathsInternal(source, isGoal, getNeighbors, paths)
    return paths
}

/**
 * Recursive helper function for [dfsPaths].
 */
private fun <T> dfsPathsInternal(
    node: T,
    isGoal: (node: T) -> Boolean,
    getNeighbors: (node: T) -> Sequence<T>,
    paths: MutableMap<T, MutableList<List<T>>>,
    visited: MutableSet<T> = mutableSetOf(),
    pathPrefix: MutableList<T> = mutableListOf()
) {
    visited.add(node)
    for (neighbor in getNeighbors(node)) {
        // Ignore visited nodes to prevent backtracking.
        if (neighbor in visited) {
            continue
        }

        // If neighbor is a goal node, record the current path to it.
        pathPrefix.add(neighbor)
        if (isGoal(neighbor)) {
            paths.getOrPut(neighbor) { mutableListOf() }.add(pathPrefix.toList())
        }

        // Continue searching recursively from neighbor.
        dfsPathsInternal(neighbor, isGoal, getNeighbors, paths, visited, pathPrefix)
        pathPrefix.removeLast()
    }
    visited.remove(node)
}
