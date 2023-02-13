package com.curtislb.adventofcode.year2019.day18.vault.search

import com.curtislb.adventofcode.common.collection.mapToMap
import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.graph.Dfs
import com.curtislb.adventofcode.common.graph.Dijkstra
import com.curtislb.adventofcode.common.graph.DirectedEdge
import com.curtislb.adventofcode.year2019.day18.vault.Vault
import com.curtislb.adventofcode.year2019.day18.vault.space.EntranceSpace
import com.curtislb.adventofcode.year2019.day18.vault.space.KeySpace

/**
 * A search for the shortest path through [vault] that collects all keys.
 */
class KeySearch(private val vault: Vault) {
    /**
     * Depth-first search config for finding all paths between important positions in the vault.
     */
    private val pathsSearch = object : Dfs<Point>() {
        override fun getNeighbors(node: Point): Sequence<Point> = sequence {
            for (neighbor in node.cardinalNeighbors()) {
                val space = vault[neighbor]
                if (space != null && space.isOccupiable) {
                    yield(neighbor)
                }
            }
        }
    }

    /**
     * The minimum search distance while collecting all keys.
     *
     * See [findMinimumSearchDistance] for more details.
     */
    private val searchDistance: Long? by lazy {
        val search = DistanceSearch(searchEdges = findSearchEdges())
        val source = SearchState(vault.entranceLocations, KeyCollection())
        val allKeys = KeyCollection.from(vault.keyLocations.keys)
        search.findShortestDistance(source) { (_, heldKeys) -> heldKeys == allKeys }
    }

    /**
     * Returns the minimum number of steps a group of searchers (starting from each [EntranceSpace])
     * can travel while collecting all keys, or `null` if not all keys can be collected from the
     * searchers' starting positions.
     */
    fun findMinimumSearchDistance(): Long? = searchDistance

    /**
     * Returns a map from each possible starting position ([EntranceSpace] or [KeySpace]) to a map
     * from each reachable key position to the [SearchEdge] representation of the path to that key.
     */
    private fun findSearchEdges(): Map<Point, Map<Point, List<SearchEdge>>> {
        val keyPositions = vault.keyLocations.values.toSet()
        val startPositions = vault.entranceLocations.toMutableSet().apply { addAll(keyPositions) }
        return startPositions.mapToMap { startPosition ->
            // Use DFS to find all paths to keys from startPosition
            val pathsFromStart = pathsSearch.findAllPaths(startPosition) { it in keyPositions }

            // Create a SearchEdge for each path and store it in the map
            val edgesFromStart = pathsFromStart.mapToMap { (endPosition, paths) ->
                endPosition to paths.map { path ->
                    SearchEdge.from(path.map { point -> vault[point]!! })
                }
            }
            startPosition to edgesFromStart
        }
    }

    /**
     * Dijkstra's graph search config for finding the shortest path that collects all vault keys.
     */
    inner class DistanceSearch(
        private val searchEdges: Map<Point, Map<Point, List<SearchEdge>>>
    ) : Dijkstra<SearchState>() {
        override fun getEdges(node: SearchState): Iterable<DirectedEdge<SearchState>> =
            mutableListOf<DirectedEdge<SearchState>>().apply {
                node.positions.forEachIndexed { index, position ->
                    searchEdges[position]?.forEach { (neighbor, searchEdges) ->
                        val key = vault[neighbor]?.symbol
                        if (key != null && key !in node.heldKeys) {
                            val newPositions = node.positions.toMutableList().apply {
                                set(index, neighbor)
                            }
                            val newKeys = node.heldKeys.withKey(key)
                            val newState = SearchState(newPositions, newKeys)
                            for (searchEdge in searchEdges) {
                                if (searchEdge.isTraversable(node.heldKeys)) {
                                    add(DirectedEdge(newState, searchEdge.distance.toLong()))
                                }
                            }
                        }
                    }
                }
            }
    }
}
