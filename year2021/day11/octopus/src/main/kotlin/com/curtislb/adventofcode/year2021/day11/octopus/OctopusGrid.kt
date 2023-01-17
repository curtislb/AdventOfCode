package com.curtislb.adventofcode.year2021.day11.octopus

import com.curtislb.adventofcode.common.grid.Grid
import com.curtislb.adventofcode.common.grid.MutableGrid
import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.grid.addRowWith
import com.curtislb.adventofcode.common.grid.forEachPointValue
import com.curtislb.adventofcode.common.grid.mutableGridOf
import com.curtislb.adventofcode.common.grid.replaceAll
import com.curtislb.adventofcode.common.grid.toGrid

/**
 * A grid of dumbo octopuses, with energy levels ranging from 0 to 9 (inclusive).
 *
 * @param gridString A string representing the initial state of the octopus grid. All lines must be
 *  of equal length and consist of only decimal digits, each of which represents the energy level of
 *  the octopus at that grid position.
 *
 * @throws IllegalArgumentException If not all lines in `gridString` are of equal length.
 */
class OctopusGrid(gridString: String) {
    /**
     * A grid containing the current energy levels of all octopuses.
     */
    private val energyGrid: MutableGrid<Int> = mutableGridOf<Int>().apply {
        for (line in gridString.trim().lines()) {
            addRowWith {
                for (char in line.trim()) {
                    add(char.digitToInt())
                }
            }
        }
    }

    /**
     * The number of rows in the grid.
     */
    val height: Int get() = energyGrid.height

    /**
     * The number of columns in the grid.
     */
    val width: Int get() = energyGrid.width

    /**
     * The total number of flashes that have occurred so far.
     *
     * See [update] for more information about when a flash occurs.
     */
    var totalFlashes: Int = 0
        private set

    /**
     * The total number of steps that have been simulated so far.
     */
    var totalSteps: Int = 0
        private set

    /**
     * Returns the current energy level of the octopus at the given [rowIndex] and [colIndex].
     *
     * @throws IndexOutOfBoundsException If [rowIndex] or [colIndex] is outside the grid bounds.
     */
    operator fun get(rowIndex: Int, colIndex: Int): Int = energyGrid[rowIndex, colIndex]

    /**
     * Returns the current energy level of the octopus at the given [point] position.
     *
     * @throws IndexOutOfBoundsException If [point] is outside the grid bounds.
     */
    operator fun get(point: Point): Int = energyGrid[point]

    /**
     * Simulates updating energy levels for [stepCount] steps, starting from the current state.
     *
     * During each step, the following update rules are applied in order:
     *
     * 1. The energy level of each octopus increases by 1.
     * 2. Any octopus with an energy level of at least [FLASH_THRESHOLD] "flashes", increasing the
     *    energy level of all cardinally and diagonally adjacent octopuses by 1. This step repeats
     *    until every octopus has flashed, or no remaining octopus has enough energy to flash.
     * 3. Any octopus that flashed during this step has its energy level set to 0.
     */
    fun update(stepCount: Int = 1) {
        totalSteps += stepCount
        repeat(stepCount) {
            // Rule 1: Increment the energy level of each octopus
            energyGrid.replaceAll { it + 1 }

            // Rule 2: Handle energy increases due to flashing octopuses
            var isUpdated: Boolean
            val flashedPoints = mutableSetOf<Point>()
            do {
                isUpdated = false

                // Find high-energy octopuses that haven't flashed yet
                energyGrid.forEachPointValue { point, energy ->
                    if (point !in flashedPoints && energy >= FLASH_THRESHOLD) {
                        isUpdated = true
                        flashedPoints.add(point)

                        // Increment the energy of all grid neighbors
                        point.allNeighbors().filter { it in energyGrid }.forEach { neighbor ->
                            energyGrid[neighbor]++
                        }
                    }
                }
            } while (isUpdated)

            // Rule 3: Reset the energy of (and count) each flashing octopus
            for (point in flashedPoints) {
                energyGrid[point] = 0
            }
            totalFlashes += flashedPoints.size
        }
    }

    /**
     * Simulates updating energy levels of octopuses in the grid, starting from the current state,
     * until a stopping [condition] is met.
     *
     * See [update] for more information about how energy levels are updated.
     */
    fun updateUntil(condition: Grid<Int>.() -> Boolean) {
        while (!energyGrid.condition()) {
            update()
        }
    }

    /**
     * Returns the current energy levels of all octopuses as an immutable grid.
     */
    fun toGrid(): Grid<Int> = energyGrid.toGrid()

    companion object {
        /**
         * The minimum energy level at which an octopus will flash.
         */
        private const val FLASH_THRESHOLD = 10
    }
}
