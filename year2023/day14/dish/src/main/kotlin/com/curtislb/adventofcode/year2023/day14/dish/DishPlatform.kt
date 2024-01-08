package com.curtislb.adventofcode.year2023.day14.dish

import com.curtislb.adventofcode.common.grid.Grid
import com.curtislb.adventofcode.common.grid.MutableGrid
import com.curtislb.adventofcode.common.grid.mutableGridOf
import com.curtislb.adventofcode.common.grid.toMutableGrid
import java.io.File

/**
 * A platform that contains rocks arranged in a 2D grid, which can be tilted to reposition the rocks
 * and thereby change the focus of a parabolic reflector dish.
 *
 * @param initialGrid A grid representing the initial position of each [Rock] on the platform.
 *
 * @constructor Creates a new instance of [DishPlatform] with the given `initialGrid` of rocks.
 */
class DishPlatform(initialGrid: Grid<Rock>) {
    /**
     * A grid representing the current position of each rock on the platform.
     */
    private var grid: MutableGrid<Rock> = initialGrid.toMutableGrid()

    /**
     * Returns the current total load on the platform's north support beams.
     *
     * The total load is given by adding together the loads contributed by all [Rock.ROUND] rocks on
     * the platform. The load for each rock is equal to the height of the grid minus the index of
     * the row in which the rock is located (starting from index 0 for the top row).
     */
    fun findNorthBeamLoad(): Int = grid.shallowRows().withIndex().sumOf { (rowIndex, row) ->
        (grid.height - rowIndex) * row.count { it == Rock.ROUND }
    }

    /**
     * Updates the grid to reflect the final positions of all rocks on the platform after performing
     * a "spin cycle" for the given number of [iterations].
     *
     * A spin cycle iteration consists of tilting the platform north, west, south, and east, in that
     * order. For more information, see: [tiltNorth], [tiltWest], [tiltSouth], and [tiltEast].
     */
    fun runSpinCycle(iterations: Long) {
        // Keep track of the grid state after each iteration
        val seenList = mutableListOf<MutableGrid<Rock>>()
        val seenMap = mutableMapOf<MutableGrid<Rock>, Int>()

        // Perform iterations until a cycle is found
        var cycleCount = 0L
        var seenIndex: Int?
        do {
            // Add previous state to seen list and index map
            seenList.add(grid.toMutableGrid())
            seenMap[seenList.last()] = seenList.lastIndex

            // Perform a single iteration
            tiltNorth()
            tiltWest()
            tiltSouth()
            tiltEast()

            // Stop if the target iteration is reached
            cycleCount++
            if (cycleCount == iterations) {
                return
            }

            // Check if the new grid state has been seen (forming a cycle)
            seenIndex = seenMap[grid]
        } while (seenIndex == null)

        // Find the point in the cycle that corresponds to the target iteration
        val remainder = (iterations - cycleCount) % (seenList.size - seenIndex).toLong()
        grid = seenList[seenIndex + remainder.toInt()]
    }

    /**
     * Tilts the platform to the north, causing all [Rock.ROUND] rocks to move as far up as possible
     * in the grid, without falling off or passing through any other rocks.
     */
    fun tiltNorth() {
        for (colIndex in grid.columnIndices) {
            var rockRowIndex = -1
            for (rowIndex in grid.rowIndices) {
                when (grid[rowIndex, colIndex]) {
                    Rock.ROUND -> {
                        rockRowIndex++
                        grid[rowIndex, colIndex] = Rock.NONE
                        grid[rockRowIndex, colIndex] = Rock.ROUND
                    }
                    Rock.CUBE -> rockRowIndex = rowIndex
                    Rock.NONE -> {}
                }
            }
        }
    }

    /**
     * Tilts the platform to the west, causing all [Rock.ROUND] rocks to move as far left as
     * possible in the grid, without falling off or passing through any other rocks.
     */
    fun tiltWest() {
        for (rowIndex in grid.rowIndices) {
            var rockColIndex = -1
            for (colIndex in grid.columnIndices) {
                when (grid[rowIndex, colIndex]) {
                    Rock.ROUND -> {
                        rockColIndex++
                        grid[rowIndex, colIndex] = Rock.NONE
                        grid[rowIndex, rockColIndex] = Rock.ROUND
                    }
                    Rock.CUBE -> rockColIndex = colIndex
                    Rock.NONE -> {}
                }
            }
        }
    }

    /**
     * Tilts the platform to the south, causing all [Rock.ROUND] rocks to move as far down as
     * possible in the grid, without falling off or passing through any other rocks.
     */
    fun tiltSouth() {
        for (colIndex in grid.columnIndices) {
            var rockRowIndex = grid.height
            for (rowIndex in grid.rowIndices.reversed()) {
                when (grid[rowIndex, colIndex]) {
                    Rock.ROUND -> {
                        rockRowIndex--
                        grid[rowIndex, colIndex] = Rock.NONE
                        grid[rockRowIndex, colIndex] = Rock.ROUND
                    }
                    Rock.CUBE -> rockRowIndex = rowIndex
                    Rock.NONE -> {}
                }
            }
        }
    }

    /**
     * Tilts the platform to the east, causing all [Rock.ROUND] rocks to move as far right as
     * possible in the grid, without falling off or passing through any other rocks.
     */
    fun tiltEast() {
        for (rowIndex in grid.rowIndices) {
            var rockColIndex = grid.width
            for (colIndex in grid.columnIndices.reversed()) {
                when (grid[rowIndex, colIndex]) {
                    Rock.ROUND -> {
                        rockColIndex--
                        grid[rowIndex, colIndex] = Rock.NONE
                        grid[rowIndex, rockColIndex] = Rock.ROUND
                    }
                    Rock.CUBE -> rockColIndex = colIndex
                    Rock.NONE -> {}
                }
            }
        }
    }

    companion object {
        /**
         * Returns a [DishPlatform] with an initial grid of rocks read from the given [file].
         *
         * The [file] must contain lines of equal length, with each character representing a [Rock]
         * located at the corresponding grid position.
         *
         * @throws IllegalArgumentException If [file] is not formatted correctly.
         */
        fun fromFile(file: File): DishPlatform {
            val grid = mutableGridOf<Rock>()
            file.forEachLine { line ->
                val row = line.map { Rock.fromChar(it) }
                grid.addShallowRow(row)
            }
            return DishPlatform(grid)
        }
    }
}
