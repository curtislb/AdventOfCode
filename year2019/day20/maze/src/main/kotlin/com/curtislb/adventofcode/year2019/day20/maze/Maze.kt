package com.curtislb.adventofcode.year2019.day20.maze

import com.curtislb.adventofcode.common.collection.getOrNull
import com.curtislb.adventofcode.common.graph.bfsDistance
import com.curtislb.adventofcode.common.grid.Grid
import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.forEachPoint
import com.curtislb.adventofcode.common.grid.mutableGridOf
import com.curtislb.adventofcode.common.io.mapLines
import com.curtislb.adventofcode.year2019.day20.maze.space.EmptySpace
import com.curtislb.adventofcode.year2019.day20.maze.space.LabeledSpace
import com.curtislb.adventofcode.year2019.day20.maze.space.Space
import java.io.File

/**
 * A maze containing labeled spaces that lead to other locations within or to additional recursive
 * copies of the maze.
 *
 * @param file A file containing the layout for the maze. Each line should contain a row of
 *  characters, with each representing a [Space] in the maze, or part of a two-letter label to be
 *  associated with an adjacent non-empty space.
 */
class Maze(file: File) {
    /**
     * A matrix representing the space at each position in the maze.
     */
    private val grid: Grid<Space>

    init {
        val charRows = file.mapLines { it.trimEnd().toMutableList() }
        val spaceLabels = processLabels(charRows)
        grid = createSpaceGrid(charRows, spaceLabels)
    }

    /**
     * A map from each label to the positions of spaces in the maze grid with that label.
     */
    private val labeledSpaces: Map<String, List<Point>> = findLabeledSpaces(grid)

    /**
     * Returns the shortest distance through the maze from the space labeled [entranceLabel] to one
     * labeled [exitLabel].
     *
     * If [isRecursive] is `false`, labeled spaces are treated as portals, which are adjacent to all
     * other spaces in the maze that share the same label.
     *
     * If [isRecursive] is `true`, labeled spaces are treated as entrances to or exits from
     * recursive copies of the maze. Labeled spaces in the interior (not outer edges) of the maze
     * are adjacent to other similarly labeled spaces in a copy of the maze one level deeper, and
     * vice versa. In this case, the function returns the shortest distance between the spaces
     * labeled [entranceLabel] and [exitLabel] within the original, outermost copy of the maze.
     */
    fun findShortestDistance(
        entranceLabel: String,
        exitLabel: String,
        isRecursive: Boolean = false
    ): Long? {
        return bfsDistance(
            source = Location(labeledSpaces[entranceLabel]?.first() ?: return null),
            isGoal = { location ->
                if (location.depth != 0) {
                    return@bfsDistance false
                }
                val space = grid.getOrNull(location.point)
                space is LabeledSpace && space.label == exitLabel
            },
            getNeighbors = { location ->
                sequence {
                    location.point.cardinalNeighbors().forEach { neighbor ->
                        if (grid.getOrNull(neighbor)?.isOccupiable == true) {
                            yield(Location(neighbor, location.depth))
                        }
                    }
                    yieldAll(getPortalLocations(location, isRecursive))
                }
            }
        )
    }

    /**
     * Returns all locations within the maze that are adjacent to [location].
     *
     * The parameter [isRecursive] indicates whether the maze is recursive, and causes the points
     * and depths of adjacent locations to be determined accordingly.
     *
     * @see [findShortestDistance]
     */
    private fun getPortalLocations(location: Location, isRecursive: Boolean): List<Location> {
        return if (isRecursive) {
            getRecursivePortalLocations(location)
        } else {
            getNonRecursivePortalLocations(location)
        }
    }

    /**
     * Returns all locations within a recursive maze that are adjacent to [location].
     *
     * @see [findShortestDistance]
     * @see [getPortalLocations]
     */
    private fun getRecursivePortalLocations(location: Location): List<Location> {
        val space = grid.getOrNull(location.point)
        val isOuterPortal = isOuterPoint(location.point)
        return when {
            space !is LabeledSpace || (isOuterPortal && location.depth == 0) -> emptyList()

            else -> {
                val newPoints =
                    labeledSpaces[space.label]?.filter { it != location.point } ?: emptyList()
                val newDepth = if (isOuterPortal) location.depth - 1 else location.depth + 1
                newPoints.map { Location(it, newDepth) }
            }
        }
    }

    /**
     * Returns all locations within a non-recursive maze that are adjacent to [location].
     *
     * @see [findShortestDistance]
     * @see [getPortalLocations]
     */
    private fun getNonRecursivePortalLocations(location: Location): List<Location> {
        return when (val space = grid.getOrNull(location.point)) {
            is LabeledSpace -> {
                val newPoints =
                    labeledSpaces[space.label]?.filter { it != location.point } ?: emptyList()
                newPoints.map { Location(it) }
            }

            else -> emptyList()
        }
    }

    /**
     * Returns `true` if a given [point] in the maze is on an outer edge, or `false` otherwise.
     */
    private fun isOuterPoint(point: Point): Boolean {
        val (rowIndex, colIndex) = point.toMatrixCoordinates()
        return rowIndex == 0 || rowIndex == grid.lastRowIndex ||
            colIndex == 0 || colIndex == grid.lastColumnIndex
    }

    companion object {
        /**
         * Processes the given [charRows] (representing a maze) by removing all label characters,
         * and returns a map from each labeled point in the grid to its associated label.
         */
        private fun processLabels(charRows: List<MutableList<Char>>): Map<Point, String> {
            val spaceLabels = mutableMapOf<Point, String>()

            // Search the grid for any characters that may be part of a label.
            charRows.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { colIndex, char ->
                    if (char.isLetter()) {
                        var label: String? = null
                        var position: Point? = null
                        if (charRows.getOrNull(rowIndex + 1, colIndex)?.isLetter() == true) {
                            // Read this vertical label from top to bottom.
                            label =
                                "${charRows[rowIndex][colIndex]}${charRows[rowIndex + 1][colIndex]}"

                            // Check above and below the label for a non-empty space.
                            val charAbove = charRows.getOrNull(rowIndex - 1, colIndex)
                            position = if (charAbove != null && charAbove != EmptySpace.symbol) {
                                Point.fromMatrixCoordinates(rowIndex - 1, colIndex)
                            } else {
                                Point.fromMatrixCoordinates(rowIndex + 2, colIndex)
                            }

                            // Remove the label characters from the grid.
                            charRows[rowIndex][colIndex] = EmptySpace.symbol
                            charRows[rowIndex + 1][colIndex] = EmptySpace.symbol
                        } else if (charRows.getOrNull(rowIndex, colIndex + 1)?.isLetter() == true) {
                            // Read this horizontal label from left to right.
                            label =
                                "${charRows[rowIndex][colIndex]}${charRows[rowIndex][colIndex + 1]}"

                            // Check left and right of the label for a non-empty space
                            val charLeft = charRows.getOrNull(rowIndex, colIndex - 1)
                            position = if (charLeft != null && charLeft != EmptySpace.symbol) {
                                Point.fromMatrixCoordinates(rowIndex, colIndex - 1)
                            } else {
                                Point.fromMatrixCoordinates(rowIndex, colIndex + 2)
                            }

                            // Remove the label characters from the grid.
                            charRows[rowIndex][colIndex] = EmptySpace.symbol
                            charRows[rowIndex][colIndex + 1] = EmptySpace.symbol
                        }

                        // Add a mapping for this label.
                        if (label != null && position != null) {
                            spaceLabels[position] = label
                        }
                    }
                }
            }

            return spaceLabels
        }

        /**
         * Returns a grid of spaces within a maze from the given [charRows] containing space symbols
         * and the map [spaceLabels], which maps points in [charRows] to their associated labels.
         *
         * The resulting grid is made as compact as possible, with empty spaces on the exterior of
         * the maze excluded.
         */
        private fun createSpaceGrid(
            charRows: List<List<Char>>,
            spaceLabels: Map<Point, String>
        ): Grid<Space> {
            val fullGrid = charRows.mapIndexed { rowIndex, row ->
                row.mapIndexed { colIndex, char ->
                    val space = Space.from(char)
                    val label = spaceLabels[Point.fromMatrixCoordinates(rowIndex, colIndex)]
                    if (label != null) LabeledSpace(space, label) else space
                }
            }

            return mutableGridOf<Space>().apply {
                for (rowIndex in 2..(fullGrid.lastIndex - 2)) {
                    val fullRow = fullGrid[rowIndex]
                    val compactRow =
                        fullRow.subList(2, fullRow.size).dropLastWhile { it == EmptySpace }
                    addRow(compactRow)
                }
            }
        }

        /**
         * Returns a map from each unique label in [grid] to the positions of all corresponding
         * labeled spaces.
         */
        private fun findLabeledSpaces(grid: Grid<Space>): Map<String, List<Point>> {
            return mutableMapOf<String, MutableList<Point>>().apply {
                grid.forEachPoint { point, space ->
                    if (space is LabeledSpace) {
                        getOrPut(space.label) { mutableListOf() }.add(point)
                    }
                }
            }
        }
    }
}
