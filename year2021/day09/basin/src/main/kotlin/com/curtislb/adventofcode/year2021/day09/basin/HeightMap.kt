package com.curtislb.adventofcode.year2021.day09.basin

import com.curtislb.adventofcode.common.grid.Grid
import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.forEachPointValue
import com.curtislb.adventofcode.common.grid.mutableGridOf
import com.curtislb.adventofcode.common.math.toDigit

/**
 * A map of heights at various positions on an undersea cave floor.
 *
 * @param mapString A string representing the height of all floor positions. The string must consist
 *  of equal-length lines, each of the form `"${A}${B}${C}..."`, where `A`, `B`, `C`, etc. are
 *  decimal digits representing the heights of the given character positions.
 *
 * @throws IllegalArgumentException If `mapString` does not match the required format.
 */
class HeightMap(mapString: String) {
    /**
     * A grid containing the heights of all floor positions.
     */
    private val heightGrid: Grid<Int> = mutableGridOf<Int>().apply {
        mapString.lines().forEach { line ->
            if (line.isNotBlank()) {
                val heights = line.trim().toCharArray().map { it.toDigit() }
                addShallowRow(heights)
            }
        }
    }

    /**
     * Returns the sizes of all basins in this heightmap.
     *
     * A basin is a contiguous collection of cardinally adjacent positions with heights less than
     * [MAX_HEIGHT]. The size of a basin is the number of distinct positions that are part of it.
     */
    fun findBasinSizes(): List<Int> {
        // Keep track of all previously visited basin points
        val visited = mutableSetOf<Point>()

        // Counts contiguous unvisited basin points reachable from (and including) `point`
        fun unvisitedSizeFrom(point: Point): Int =
            if (point !in visited && isBasin(point)) {
                // Once it's been counted, mark this basin point as visited
                visited.add(point)
                1 + point.cardinalNeighbors().sumOf { unvisitedSizeFrom(it) }
            } else {
                0
            }

        // Scan through the heightmap, greedily searching for basins
        val basinSizes = mutableListOf<Int>()
        heightGrid.forEachPointValue { point, height ->
            if (point !in visited && height < MAX_HEIGHT) {
                basinSizes.add(unvisitedSizeFrom(point))
            }
        }

        return basinSizes
    }

    /**
     * Returns the positions of all low points in this height map.
     *
     * A low point is any floor position with a height that is lower than all cardinally adjacent
     * positions in the heightmap.
     */
    fun findLowPoints(): List<Point> {
        val points = mutableListOf<Point>()
        heightGrid.forEachPointValue { point, height ->
            // Check if all cardinal neighbor positions are lower than this one
            val isLowPoint = point.cardinalNeighbors().all { neighbor ->
                val neighborHeight = heightGrid.getOrNull(neighbor)
                neighborHeight == null || neighborHeight > height
            }

            if (isLowPoint) {
                points.add(point)
            }
        }

        return points
    }

    /**
     * Checks if the given [point] position is a part of a basin in this heightmap.
     *
     * See [findBasinSizes] for the definition of a basin.
     */
    fun isBasin(point: Point): Boolean =
        heightGrid.getOrNull(point)?.let { it < MAX_HEIGHT } == true

    /**
     * Returns the risk level for a given [point] position in this heightmap.
     *
     * @throws IndexOutOfBoundsException If [point] is outside the range of the heightmap.
     */
    fun riskLevel(point: Point): Int = heightGrid[point] + 1

    companion object {
        /**
         * The maximum height of any floor position in a heightmap.
         */
        private const val MAX_HEIGHT = 9
    }
}
