package com.curtislb.adventofcode.common.graph

import com.curtislb.adventofcode.common.collection.removeLast

/**
 * Conducts a depth-first graph search and returns a map from each node in [goals] that is reachable from [start] to a
 * list of all paths from [start] to that node, with the neighbors of each node given by [getNeighbors].
 */
fun <T> dfsPaths(start: T, goals: Set<T>, getNeighbors: (node: T) -> Sequence<T>): Map<T, List<List<T>>> {
    val paths = mutableMapOf<T, MutableList<List<T>>>()
    dfsPathsInternal(start, goals, getNeighbors, paths)
    return paths
}

/**
 * Recursive helper function for [dfsPaths].
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