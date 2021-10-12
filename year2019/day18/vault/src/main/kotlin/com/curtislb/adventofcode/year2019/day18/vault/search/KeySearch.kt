package com.curtislb.adventofcode.year2019.day18.vault.search

import com.curtislb.adventofcode.common.collection.mapToMap
import com.curtislb.adventofcode.common.graph.DirectedEdge
import com.curtislb.adventofcode.common.graph.dfsPaths
import com.curtislb.adventofcode.common.graph.dijkstraShortestDistance
import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.year2019.day18.vault.Vault
import com.curtislb.adventofcode.year2019.day18.vault.space.EntranceSpace
import com.curtislb.adventofcode.year2019.day18.vault.space.KeySpace

/**
 * A search for the shortest path through [vault] that collects all keys.
 */
class KeySearch(private val vault: Vault) {
    /**
     * The fewest steps that a group of searchers (starting from each [EntranceSpace]) can travel
     * in order to collect all keys, or `null` if not all keys can be collected from the searchers'
     * starting positions.
     */
    val searchDistance: Long? by lazy {
        val searchEdges = findSearchEdges()
        val allKeys = KeyCollection.from(vault.keyLocations.keys)
        dijkstraShortestDistance(
            source = SearchState(vault.entranceLocations, KeyCollection()),
            isGoal = { (_, heldKeys) -> heldKeys == allKeys },
            getEdges = { (positions, heldKeys) ->
                sequence {
                    positions.forEachIndexed { index, position ->
                        searchEdges[position]?.forEach { (neighbor, edges) ->
                            val key = vault[neighbor]?.symbol
                            if (key != null && key !in heldKeys) {
                                val newPositions =
                                    positions.toMutableList().apply { set(index, neighbor) }
                                val newKeys = heldKeys.withKey(key)
                                val newState = SearchState(newPositions, newKeys)
                                edges.forEach { searchEdge ->
                                    if (searchEdge.isTraversable(heldKeys)) {
                                        yield(DirectedEdge(newState, searchEdge.distance.toLong()))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        )
    }

    /**
     * Returns a map from each possible starting position ([EntranceSpace] or [KeySpace]) to a map
     * from each reachable key position to the [SearchEdge] representation of the path to that key.
     */
    private fun findSearchEdges(): Map<Point, Map<Point, List<SearchEdge>>> {
        val keyPositions = vault.keyLocations.values.toSet()
        val startPositions: Set<Point> =
            vault.entranceLocations.toMutableSet().apply { addAll(keyPositions) }
        return startPositions.mapToMap { startPosition ->
            // Use DFS to find all paths to keys from startPosition.
            val pathsFromStart = dfsPaths(
                startPosition,
                isGoal = { it in keyPositions },
                getNeighbors = { point ->
                    sequence {
                        for (neighbor in point.cardinalNeighbors()) {
                            val space = vault[neighbor]
                            if (space != null && space.isOccupiable) {
                                yield(neighbor)
                            }
                        }
                    }
                }
            )

            // Create a SearchEdge for each path and store it in the map.
            val edgesFromStart = pathsFromStart.mapToMap { (endPosition, paths) ->
                endPosition to paths.map { path ->
                    SearchEdge.from(path.map { point -> vault[point]!! })
                }
            }
            startPosition to edgesFromStart
        }
    }
}
