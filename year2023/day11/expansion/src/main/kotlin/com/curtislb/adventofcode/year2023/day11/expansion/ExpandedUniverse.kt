package com.curtislb.adventofcode.year2023.day11.expansion

import com.curtislb.adventofcode.common.collection.mapToSet
import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.io.forEachLineIndexed
import com.curtislb.adventofcode.common.iteration.uniquePairs
import java.io.File
import kotlin.math.abs

/**
 * A representation of a universe containing one or more galaxies, in which the empty space between
 * galaxies has increased by a given expansion factor.
 *
 * @property galaxies A list of point positions of all galaxies in the universe, before accounting
 *  for expansion.
 * @param expansionFactor The factor by which the space between galaxies should increase to account
 *  for expansion.
 */
class ExpandedUniverse(private val galaxies: List<Point>, expansionFactor: Long) {
    /**
     * A map from each row index in [galaxies] to the distance between that row and the row at index
     * 0, after accounting for expansion.
     */
    private val rowDistanceMap: Map<Int, Long> =
        expandedDistanceMap(galaxies.mapToSet { it.matrixRow }, expansionFactor)

    /**
     * A map from each column index in [galaxies] to the distance between that column and the column
     * at index 0, after accounting for expansion.
     */
    private val colDistanceMap: Map<Int, Long> =
        expandedDistanceMap(galaxies.mapToSet { it.matrixCol }, expansionFactor)

    /**
     * Returns the sum of distances between all unique pairs of galaxies in the universe, after
     * accounting for expansion.
     */
    fun sumGalaxyDistances(): Long = galaxies.uniquePairs().sumOf { (pointA, pointB) ->
        val rowDistanceA = rowDistanceMap[pointA.matrixRow] ?: error("Not in row map: $pointA")
        val rowDistanceB = rowDistanceMap[pointB.matrixRow] ?: error("Not in row map: $pointB")
        val colDistanceA = colDistanceMap[pointA.matrixCol] ?: error("Not in column map: $pointA")
        val colDistanceB = colDistanceMap[pointB.matrixCol] ?: error("Not in column map: $pointB")
        abs(rowDistanceA - rowDistanceB) + abs(colDistanceA - colDistanceB)
    }

    companion object {
        /**
         * Returns an [ExpandedUniverse] with the specified [expansionFactor] and galaxy positions
         * read from the given input [file], representing an unexpanded image of the universe.
         *
         * The [file] should contain a 2D grid of *only* the following characters:
         * - `#`: A galaxy contained in the universe
         * - `.`: An empty (unexpanded) space in the universe image
         */
        fun fromFile(file: File, expansionFactor: Long): ExpandedUniverse {
            val galaxies = mutableListOf<Point>()
            file.forEachLineIndexed { rowIndex, line ->
                line.forEachIndexed { colIndex, char ->
                    if (char == '#') {
                        galaxies.add(Point.fromMatrixCoordinates(rowIndex, colIndex))
                    }
                }
            }
            return ExpandedUniverse(galaxies, expansionFactor)
        }

        /**
         * Returns a map from each index in [galaxyIndices] to the distance between that index and
         * index 0, assuming all excluded indices expand in size by the given [expansionFactor].
         */
        private fun expandedDistanceMap(
            galaxyIndices: Set<Int>,
            expansionFactor: Long
        ): Map<Int, Long> {
            // Calculate distance for each galaxy index in order
            val distanceMap = mutableMapOf(0 to 0L)
            var prevIndex = 0
            for (index in galaxyIndices.sorted()) {
                // Skip index 0, since it's fixed at a distance of 0
                if (index == 0) {
                    continue
                }

                // Add expanded distance (plus 1 for this index) to the previous distance
                val prevDistance = distanceMap[prevIndex] ?: error("Index not in map: $prevIndex")
                val expandedDistance = (index - prevIndex - 1) * expansionFactor
                distanceMap[index] = prevDistance + expandedDistance + 1L

                prevIndex = index
            }

            return distanceMap
        }
    }
}
