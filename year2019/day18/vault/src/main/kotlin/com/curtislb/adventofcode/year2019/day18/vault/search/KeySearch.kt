package com.curtislb.adventofcode.year2019.day18.vault.search

import com.curtislb.adventofcode.common.collection.mapToMap
import com.curtislb.adventofcode.common.collection.replace
import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.graph.UnweightedGraph
import com.curtislb.adventofcode.common.graph.WeightedGraph
import com.curtislb.adventofcode.year2019.day18.vault.Vault
import com.curtislb.adventofcode.year2019.day18.vault.space.EntranceSpace
import com.curtislb.adventofcode.year2019.day18.vault.space.KeySpace

/**
 * A search for the shortest path through [vault] that collects all keys.
 */
class KeySearch(private val vault: Vault) {
    /**
     * A graph with edges from each space in the [vault] to adjacent occupiable spaces.
     */
    private val spaceGraph = object : UnweightedGraph<Point>() {
        override fun getNeighbors(node: Point): Iterable<Point> =
            node.cardinalNeighbors().filter { vault[it]?.isOccupiable == true }
    }

    /**
     * A graph with edges from each possible [SearchState] to new [SearchState]s that result from
     * moving one searcher to the space of a non-held key, weighted by the number of spaces moved.
     */
    inner class StateGraph(
        private val searchEdges: Map<Point, Map<Point, List<SearchEdge>>>
    ) : WeightedGraph<SearchState>() {
        override fun getEdges(node: SearchState): Iterable<Edge<SearchState>> =
            mutableListOf<Edge<SearchState>>().apply {
                node.positions.forEachIndexed { index, position ->
                    searchEdges[position]?.forEach { (neighbor, searchEdges) ->
                        val key = vault[neighbor]?.symbol
                        if (key != null && key !in node.heldKeys) {
                            val newPositions = node.positions.replace(index) { neighbor }
                            val newKeys = node.heldKeys.withKey(key)
                            val newState = SearchState(newPositions, newKeys)
                            for (searchEdge in searchEdges) {
                                if (searchEdge.isTraversable(node.heldKeys)) {
                                    add(Edge(newState, searchEdge.distance.toLong()))
                                }
                            }
                        }
                    }
                }
            }
    }

    /**
     * The minimum search distance while collecting all keys.
     *
     * See [minimumSearchDistance] for more details.
     */
    private val searchDistance: Long? by lazy {
        val searchEdges = findSearchEdges()
        val stateGraph = StateGraph(searchEdges)
        val source = SearchState(vault.entranceLocations, KeyCollection())
        val allKeys = KeyCollection.from(vault.keyLocations.keys)
        stateGraph.dijkstraDistance(source) { (_, heldKeys) -> heldKeys == allKeys }
    }

    /**
     * Returns the minimum number of steps a group of searchers (starting from each [EntranceSpace])
     * can travel while collecting all keys, or `null` if not all keys can be collected from the
     * searchers' starting positions.
     */
    fun minimumSearchDistance(): Long? = searchDistance

    /**
     * Returns a map from each possible starting position ([EntranceSpace] or [KeySpace]) to a map
     * from each reachable key position to the [SearchEdge] representation of the path to that key.
     */
    private fun findSearchEdges(): Map<Point, Map<Point, List<SearchEdge>>> {
        val keyPositions = vault.keyLocations.values.toSet()
        val startPositions = vault.entranceLocations.toMutableList().apply { addAll(keyPositions) }
        return startPositions.mapToMap { startPosition ->
            // Use DFS to find all paths to keys from startPosition
            val pathsFromStart = spaceGraph.dfsPaths(startPosition) { it in keyPositions }

            // Create a SearchEdge for each path and store it in the map
            val edgesFromStart = pathsFromStart.mapToMap { (endPosition, paths) ->
                endPosition to paths.map { pathPositions ->
                    SearchEdge.fromPath(pathPositions.map { vault[it]!! })
                }
            }
            startPosition to edgesFromStart
        }
    }
}
