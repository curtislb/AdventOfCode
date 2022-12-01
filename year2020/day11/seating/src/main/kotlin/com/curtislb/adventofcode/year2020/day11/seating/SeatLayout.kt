package com.curtislb.adventofcode.year2020.day11.seating

import com.curtislb.adventofcode.common.grid.Grid
import com.curtislb.adventofcode.common.grid.MutableGrid
import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.addRowWith
import com.curtislb.adventofcode.common.grid.mutableGridOf
import com.curtislb.adventofcode.common.grid.sumRowsBy
import com.curtislb.adventofcode.common.grid.toMutableGrid
import com.curtislb.adventofcode.common.simulation.GameOfLife

/**
 * A fixed-size grid containing seats that may become occupied or empty over time.
 *
 * @param initialLayout A string representation of the initial seat layout. Each line should contain
 *  a row of characters, with each representing part of the floor (`.`) an empty seat (`L`), or an
 *  occupied seat (`#`).
 * @param maxNeighbors The maximum number of neighboring seats that can be occupied without causing
 *  a currently occupied seat to become empty.
 * @param getNeighboringSeats Returns the positions of all neighboring seats for a given position in
 *  the grid.
 */
class SeatLayout(
    initialLayout: String,
    private val maxNeighbors: Int = 0,
    private val getNeighboringSeats: (grid: Grid<Space>, position: Point) -> Sequence<Point>
) {
    /**
     * A grid of spaces representing the current seat layout.
     */
    private var spaceGrid = mutableGridOf<Space>().apply {
        for (line in initialLayout.trim().lines()) {
            addRowWith {
                for (char in line.trim()) {
                    add(Space.from(char))
                }
            }
        }
    }

    /**
     * TODO
     */
    private val seatingProcess = object : GameOfLife<MutableGrid<Space>, Point, Space>() {
        override fun getValue(state: MutableGrid<Space>, key: Point): Space = state[key]

        override fun setValue(
            state: MutableGrid<Space>,
            key: Point,
            value: Space
        ): MutableGrid<Space> {
            state[key] = value
            return state
        }

        override fun getUpdatableKeys(state: MutableGrid<Space>): Sequence<Point> =
            state.points().filter { state[it] != Space.FLOOR }

        override fun getNeighboringKeys(
            state: MutableGrid<Space>,
            key: Point
        ): Sequence<Point> {
            return getNeighboringSeats(state, key)
        }

        override fun applyUpdateRules(value: Space, neighbors: Sequence<Space>): Space =
            when (value) {
                Space.FLOOR -> value
                Space.EMPTY ->
                    if (neighbors.none { it == Space.OCCUPIED }) Space.OCCUPIED else value
                Space.OCCUPIED -> {
                    val occupiedCount = neighbors.count { it == Space.OCCUPIED }
                    if (occupiedCount > maxNeighbors) Space.EMPTY else value
                }
            }

        override fun copyState(state: MutableGrid<Space>): MutableGrid<Space> =
            state.toMutableGrid()

        override fun shouldTerminate(
            previousState: MutableGrid<Space>,
            state: MutableGrid<Space>
        ): Boolean {
            return previousState == state
        }
    }

    /**
     * Returns the number of occupied seats in the current layout.
     */
    fun countOccupied(): Int =
        spaceGrid.sumRowsBy { row ->
            row.count { it == Space.OCCUPIED }
        }

    /**
     * Updates the current layout by applying update rules to all spaces simultaneously until the
     * layout no longer changes.
     *
     * The rules for updating each space are as follows:
     *
     * - An empty seat becomes occupied if no neighboring seat (see [getNeighboringSeats]) is
     *   occupied.
     * - An occupied seat becomes empty if [maxNeighbors] or more neighboring seats are occupied.
     * - In all other cases, the space does not change.
     */
    fun updateUntilStable() {
        spaceGrid = seatingProcess.runSimulation(spaceGrid)
    }

    override fun toString(): String = spaceGrid.joinRowsToString(separator = "\n") { row ->
        row.joinToString(separator = "") { space -> space.symbol.toString() }
    }
}
