package com.curtislb.adventofcode.year2020.day11.seating

import com.curtislb.adventofcode.common.grid.Grid
import com.curtislb.adventofcode.common.grid.MutableGrid
import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.addRowWith
import com.curtislb.adventofcode.common.grid.joinRowsToString
import com.curtislb.adventofcode.common.grid.mutableGridOf
import com.curtislb.adventofcode.common.grid.sumRowsBy
import com.curtislb.adventofcode.common.grid.toMutableGrid
import com.curtislb.adventofcode.common.simulation.runGameOfLife

/**
 * A fixed-size grid containing seats that may become occupied or empty over time.
 *
 * @param initialLayout A string representation of the initial seat layout. Each line should contain
 *  a row of characters, with each representing part of the floor (`'.'`) an empty seat (`'L'`), or
 *  an occupied seat (`'#'`).
 * @param maxNeighbors The maximum number of neighboring seats that can be occupied without causing
 *  a currently occupied seat to become empty.
 * @param getNeighborSeats Returns the positions of all neighboring seats for a given position in
 *  the grid.
 */
class SeatLayout(
    initialLayout: String,
    private val maxNeighbors: Int = 0,
    private val getNeighborSeats: (grid: Grid<Space>, position: Point) -> Sequence<Point>
) {
    /**
     * A grid of spaces representing the current seat layout.
     */
    private var spaceGrid: Grid<Space> = mutableGridOf<Space>().apply {
        initialLayout.trim().lines().forEach { line ->
            addRowWith { line.forEach { char -> add(Space.from(char)) } }
        }
    }

    /**
     * Returns the number of occupied seats in the current layout.
     */
    fun countOccupied(): Int =
        spaceGrid.sumRowsBy { row -> row.count { space -> space == Space.OCCUPIED } }

    /**
     * Updates the current layout by applying update rules to all spaces simultaneously until the
     * layout no longer changes.
     *
     * The rules for updating each space are as follows:
     *
     * - An empty seat becomes occupied if no neighboring seat (see [getNeighborSeats]) is occupied.
     * - An occupied seat becomes empty if [maxNeighbors] or more neighboring seats are occupied.
     * - In all other cases, the space does not change.
     */
    fun updateUntilStable() {
        spaceGrid = runGameOfLife(
            spaceGrid.toMutableGrid(),
            shouldTerminate = { prevGrid, grid -> prevGrid == grid },
            copyState = MutableGrid<Space>::toMutableGrid,
            getValue = MutableGrid<Space>::get,
            setValue = { grid, point, space -> grid.apply { this[point] = space } },
            getUpdatableKeys = { grid -> grid.points().filter { grid[it] != Space.FLOOR } },
            getNeighborKeys = getNeighborSeats,
            applyUpdateRules = ::applyRules
        )
    }

    /**
     * Returns the resulting [Space] after applying the rules from [updateUntilStable] to the given
     * [space] with given [neighbors].
     */
    private fun applyRules(space: Space, neighbors: Sequence<Space>): Space = when (space) {
        Space.FLOOR -> space
        Space.EMPTY -> if (neighbors.none { it == Space.OCCUPIED }) Space.OCCUPIED else space
        Space.OCCUPIED -> {
            val occupiedCount = neighbors.count { it == Space.OCCUPIED }
            if (occupiedCount > maxNeighbors) Space.EMPTY else space
        }
    }

    override fun toString(): String = spaceGrid.joinRowsToString(separator = "\n") { row ->
        row.joinToString(separator = "") { space -> space.symbol.toString() }
    }
}
