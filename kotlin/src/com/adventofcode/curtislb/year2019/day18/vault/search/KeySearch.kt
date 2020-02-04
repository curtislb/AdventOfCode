package com.adventofcode.curtislb.year2019.day18.vault.search

import com.adventofcode.curtislb.common.collection.mapToMap
import com.adventofcode.curtislb.common.graph.DirectedEdge
import com.adventofcode.curtislb.common.graph.dfsPaths
import com.adventofcode.curtislb.common.graph.dijkstraShortestDistance
import com.adventofcode.curtislb.common.grid.Point
import com.adventofcode.curtislb.year2019.day18.vault.Vault
import com.adventofcode.curtislb.year2019.day18.vault.space.DoorSpace
import com.adventofcode.curtislb.year2019.day18.vault.space.EntranceSpace
import com.adventofcode.curtislb.year2019.day18.vault.space.KeySpace
import com.adventofcode.curtislb.year2019.day18.vault.space.OpenSpace
import com.adventofcode.curtislb.year2019.day18.vault.space.WallSpace

/**
 * A search for the shortest distance that a searcher(s) can travel through a [Vault] in order to collect all keys.
 * @param vault The [Vault] to be searched. Searchers begin at each [EntranceSpace] in the vault.
 */
class KeySearch(private val vault: Vault) {
    /**
     * The fewest collective steps that the searchers can travel in order to collect all keys in the [Vault], or `null`
     * if the searchers can't collect all keys from their starting positions.
     */
    val searchDistance: Long? by lazy {
        val searchEdges = findSearchEdges()
        val allKeys = KeyCollection.from(vault.keyLocations.keys)
        dijkstraShortestDistance(
            start = SearchState(vault.entranceLocations, KeyCollection()),
            isGoal = { (_, heldKeys) -> heldKeys == allKeys },
            getEdges = { (positions, heldKeys) ->
                sequence {
                    positions.forEachIndexed { index, position ->
                        searchEdges[position]?.forEach { (neighbor, edges) ->
                            val key = vault[neighbor]?.symbol
                            if (key != null && key !in heldKeys) {
                                val newPositions = positions.toMutableList().apply { set(index, neighbor) }
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
     * Finds the relevant [SearchEdge] information for all paths between points of interest in the [Vault].
     * @return A [Map] from each possible starting position ([EntranceSpace] or [KeySpace] location) to a [Map] from
     *  each reachable key position to the [SearchEdge] representation of the path to that key.
     */
    private fun findSearchEdges(): Map<Point, Map<Point, List<SearchEdge>>> {
        val keyPositions = vault.keyLocations.values.toSet()
        val startPositions = vault.entranceLocations.toMutableSet().apply { addAll(keyPositions) } as Set<Point>
        return startPositions.mapToMap { startPosition ->
            // Use DFS to find all paths to keys from startPosition.
            val pathsFromStart = dfsPaths(startPosition, keyPositions) { point ->
                sequence {
                    for (neighbor in point.neighbors) {
                        when (val space = vault[neighbor]) {
                            OpenSpace, EntranceSpace, is KeySpace, is DoorSpace -> yield(neighbor)
                            WallSpace, null -> {}
                            else -> throw IllegalArgumentException("Encountered unknown space: ${space.symbol}")
                        }
                    }
                }
            }

            // Create a SearchEdge for each path and store it in the map.
            val edgesFromStart = pathsFromStart.mapToMap { (endPosition, paths) ->
                val edges = paths.map { path -> SearchEdge.from(path.map { point -> vault[point]!! }) }
                Pair(endPosition, edges)
            }
            Pair(startPosition, edgesFromStart)
        }
    }
}
