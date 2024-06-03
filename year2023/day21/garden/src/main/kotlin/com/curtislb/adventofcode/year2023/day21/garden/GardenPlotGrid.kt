package com.curtislb.adventofcode.year2023.day21.garden

import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.grid.Grid
import com.curtislb.adventofcode.common.grid.mutableGridOf
import com.curtislb.adventofcode.common.grid.pointOfFirst
import com.curtislb.adventofcode.common.number.floorToMultipleOf
import com.curtislb.adventofcode.common.number.pow
import java.io.File

/**
 * An infinitely repeating 2D grid of tiles representing garden plots or impassable rocks.
 *
 * @property baseGrid The base grid of tiles that is repeated infinitely in all directions.
 *
 * @constructor Creates a new instance of [GardenPlotGrid] with the given [baseGrid].
 */
class GardenPlotGrid(private val baseGrid: Grid<Tile>) {
    /**
     * The start point in the base grid from which to begin walking.
     */
    private val startPoint: Point = baseGrid.pointOfFirst { it == Tile.START }
        ?: throw IllegalArgumentException("Base grid must have a start tile")

    /**
     * Returns the number of garden plots that can be reached by walking exactly the specified
     * number of [steps] from the given [start] point, while staying *within the base grid*.
     */
    fun countReachableInBaseGrid(steps: Int, start: Point = startPoint): Int {
        // Keep track of the current step and its parity
        var canReach = steps % 2 == 0
        var currStep = 0

        // Use BFS to find and count all reachable plots from start
        var frontier = listOf(start)
        val seen = mutableSetOf<Point>()
        var reachableCount = 0
        while (currStep < steps && frontier.isNotEmpty()) {
            // Update the current step count and parity
            canReach = !canReach
            currStep++

            // Process all tiles in the current search frontier and build a new one
            val newFrontier = mutableListOf<Point>()
            for (point in frontier) {
                for (neighbor in point.cardinalNeighbors()) {
                    // Check if the neighboring tile is a new plot
                    val tile = baseGrid.getOrNull(neighbor)
                    if (tile == null || tile == Tile.ROCK || neighbor in seen) {
                        continue
                    }

                    // Count the tile as reachable if it has the right step parity
                    if (canReach) {
                        reachableCount++
                    }

                    // Add the tile to the search frontier and mark it as seen
                    newFrontier.add(neighbor)
                    seen.add(neighbor)
                }
            }
            frontier = newFrontier
        }

        return reachableCount
    }

    /**
     * Returns the number of garden plots that can be reached by walking exactly the specified
     * number of [steps] from the predetermined start point in the full, infinitely repeating grid.
     *
     * This function makes the following assumptions about the base grid and start point:
     *
     * - The base grid has an odd-number integer width.
     * - The base grid is square (has the same width and height).
     * - The start point is the exact midpoint of the base grid.
     *
     * @throws IllegalArgumentException If the base grid or start point fails to satisfy any of the
     *  above assumptions.
     */
    fun countReachableInRepeatedGrid(steps: Long): Long {
        // Validate necessary assumptions for the base grid and start point
        require(baseGrid.width % 2 == 1) { "Base grid width must be odd: ${baseGrid.width}" }
        require(baseGrid.width == baseGrid.height) {
            "Base grid width and height must be equal: ${baseGrid.width} != ${baseGrid.height}"
        }
        require(
            startPoint.matrixRow == baseGrid.height / 2 &&
            startPoint.matrixCol == baseGrid.width / 2
        ) {
            "Start point must be in the middle of the grid: $startPoint"
        }

        // Calculate some useful intermediary values
        val gridWidth = baseGrid.width
        val maxGridWidths = (steps / gridWidth) - 1L
        val lastGridIndex = gridWidth - 1
        val startCoord = startPoint.matrixCol

        // Count reachable plots in all *fully traversable* copies of the base grid
        val oddFullGrids = (maxGridWidths.floorToMultipleOf(2L) + 1L).pow(2)
        val evenFullGrids = (maxGridWidths + 1L).floorToMultipleOf(2L).pow(2)
        val oddFullGridPlots = oddFullGrids * countReachableInBaseGrid(steps = gridWidth * 2 + 1)
        val evenFullGridPlots = evenFullGrids * countReachableInBaseGrid(steps = gridWidth * 2)
        val allFullGridPlots = oddFullGridPlots + evenFullGridPlots

        // Count reachable plots in all four "corner" copies of the base grid
        val cornerGridSteps = gridWidth - 1
        val topCornerGridStart = Point.fromMatrixCoordinates(lastGridIndex, startCoord)
        val rightCornerGridStart = Point.fromMatrixCoordinates(startCoord, 0)
        val bottomCornerGridStart = Point.fromMatrixCoordinates(0, startCoord)
        val leftCornerGridStart = Point.fromMatrixCoordinates(startCoord, lastGridIndex)
        val allCornerPlots =
            countReachableInBaseGrid(cornerGridSteps, topCornerGridStart) +
            countReachableInBaseGrid(cornerGridSteps, rightCornerGridStart) +
            countReachableInBaseGrid(cornerGridSteps, bottomCornerGridStart) +
            countReachableInBaseGrid(cornerGridSteps, leftCornerGridStart)

        // Count reachable plots in all "small" bordering, non-corner copies of the base grid
        val smallGridSteps = gridWidth / 2 - 1
        val topRightGridStart = Point.fromMatrixCoordinates(lastGridIndex, 0)
        val bottomRightGridStart = Point.ORIGIN
        val bottomLeftGridStart = Point.fromMatrixCoordinates(0, lastGridIndex)
        val topLeftGridStart = Point.fromMatrixCoordinates(lastGridIndex, lastGridIndex)
        val allSmallGridPlots = (maxGridWidths + 1) * (
            countReachableInBaseGrid(smallGridSteps, topRightGridStart) +
            countReachableInBaseGrid(smallGridSteps, bottomRightGridStart) +
            countReachableInBaseGrid(smallGridSteps, bottomLeftGridStart) +
            countReachableInBaseGrid(smallGridSteps, topLeftGridStart)
        )

        // Count reachable plots in all "large" bordering, non-corner copies of the base grid
        val largeGridSteps = gridWidth * 3 / 2 - 1
        val allLargeGridPlots = maxGridWidths * (
            countReachableInBaseGrid(largeGridSteps, topRightGridStart) +
            countReachableInBaseGrid(largeGridSteps, bottomRightGridStart) +
            countReachableInBaseGrid(largeGridSteps, bottomLeftGridStart) +
            countReachableInBaseGrid(largeGridSteps, topLeftGridStart)
        )

        // Count reachable plots across *all* reachable copies of the base grid
        return allFullGridPlots + allCornerPlots + allSmallGridPlots + allLargeGridPlots
    }

    companion object {
        /**
         * Returns a [GardenPlotGrid] with a base grid of [Tile]s read from the specified [file].
         *
         * Each line of the [file] must consist of valid [Tile] characters, representing a single
         * row in the resulting base grid.
         *
         * @throws IllegalArgumentException If [file] is not formatted correctly.
         */
        fun fromFile(file: File): GardenPlotGrid {
            val baseGrid = mutableGridOf<Tile>()
            file.forEachLine { line ->
                val row = line.map { Tile.fromChar(it) }
                baseGrid.addShallowRow(row)
            }
            return GardenPlotGrid(baseGrid)
        }
    }
}
