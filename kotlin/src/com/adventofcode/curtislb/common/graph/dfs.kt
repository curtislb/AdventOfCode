package com.adventofcode.curtislb.common.graph

import com.adventofcode.curtislb.common.collection.removeLast

/**
 * Conducts a depth-first graph search to determine all paths from a starting node to a set of goal nodes.
 * @param start The starting node from which to conduct the search.
 * @param goals A [Set] of nodes for which all paths from [start] should be found.
 * @param getNeighbors A function that maps each node to a [Sequence] of the nodes that neighbor it.
 * @return A [Map] from each node in [goals] that is reachable from [start] to a [List] of all paths from [start] to
 *  that node (without backtracking).
 */
fun <T> dfsPaths(start: T, goals: Set<T>, getNeighbors: (node: T) -> Sequence<T>): Map<T, List<List<T>>> {
    val paths = mutableMapOf<T, MutableList<List<T>>>()
    dfsPathsInternal(start, goals, getNeighbors, paths)
    return paths
}

/**
 * Recursive helper function for [dfsPaths].
 * @param node The current node from which to continue searching.
 * @param goals A [Set] of nodes for which all paths from [node] should be found.
 * @param getNeighbors A function that maps each node to a [Sequence] of the nodes that neighbor it.
 * @param paths A [MutableMap] of all currently recorded paths from an original starting node to nodes in [goals].
 * @param visited A [MutableSet] of nodes that have already been visited along the current path.
 * @param pathPrefix An ordered [List] of nodes that make up the current path to [node].
 */
private fun <T> dfsPathsInternal(
    node: T,
    goals: Set<T>,
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
        if (neighbor in goals) {
            paths.getOrPut(neighbor) { mutableListOf() }.add(pathPrefix.toList())
        }

        // Continue searching recursively from neighbor.
        dfsPathsInternal(neighbor, goals, getNeighbors, paths, visited, pathPrefix)
        pathPrefix.removeLast()
    }
    visited.remove(node)
}
