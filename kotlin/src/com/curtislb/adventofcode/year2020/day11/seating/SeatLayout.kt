package com.curtislb.adventofcode.year2020.day11.seating

import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.getCellOrNull
import java.io.File

/**
 * TODO
 */
class SeatLayout(file: File) {
    /**
     * TODO
     */
    private var seatGrid: List<List<Space>> = mutableListOf<List<Space>>().apply {
        file.forEachLine { line ->
            add(mutableListOf<Space>().apply { line.forEach { char -> add(Space.from(char)) } })
        }
    }

    /**
     * TODO
     */
    fun countOccupied(): Int = seatGrid.sumBy { row -> row.sumBy { space -> if (space == Space.OCCUPIED) 1 else 0 } }

    /**
     * TODO
     */
    fun stabilize(useUpdatedRules: Boolean = false) {
        var nextGrid = if (useUpdatedRules) applyUpdatedRules(seatGrid) else applyRules(seatGrid)
        while (seatGrid != nextGrid) {
            seatGrid = nextGrid
            nextGrid = if (useUpdatedRules) applyUpdatedRules(seatGrid) else applyRules(seatGrid)
        }
    }

    companion object {
        fun applyRules(seatGrid: List<List<Space>>): List<List<Space>> {
            val newGrid = mutableListOf<List<Space>>()
            seatGrid.forEachIndexed { rowIndex, row ->
                val newRow = mutableListOf<Space>()
                row.forEachIndexed { colIndex, space ->
                    val newSpace = when (space) {
                        Space.EMPTY -> if (isIsolated(seatGrid, rowIndex, colIndex)) Space.OCCUPIED else space
                        Space.OCCUPIED -> if (isCrowded(seatGrid, rowIndex, colIndex)) Space.EMPTY else space
                        else -> space
                    }
                    newRow.add(newSpace)
                }
                newGrid.add(newRow)
            }
            return newGrid
        }

        private fun isIsolated(seatGrid: List<List<Space>>, rowIndex: Int, colIndex: Int): Boolean {
            val point = Point.fromMatrixCoordinates(rowIndex, colIndex)
            return point.allNeighbors.none { seatGrid.getCellOrNull(it) == Space.OCCUPIED }
        }

        private fun isCrowded(seatGrid: List<List<Space>>, rowIndex: Int, colIndex: Int): Boolean {
            val point = Point.fromMatrixCoordinates(rowIndex, colIndex)
            val occupiedCount = point.allNeighbors.count { seatGrid.getCellOrNull(it) == Space.OCCUPIED }
            return occupiedCount >= 4
        }

        fun applyUpdatedRules(seatGrid: List<List<Space>>): List<List<Space>> {
            val newGrid = mutableListOf<List<Space>>()
            seatGrid.forEachIndexed { rowIndex, row ->
                val newRow = mutableListOf<Space>()
                row.forEachIndexed { colIndex, space ->
                    val newSpace = when (space) {
                        Space.EMPTY -> if (isVisuallyIsolated(seatGrid, rowIndex, colIndex)) Space.OCCUPIED else space
                        Space.OCCUPIED -> if (isVisuallyCrowded(seatGrid, rowIndex, colIndex)) Space.EMPTY else space
                        else -> space
                    }
                    newRow.add(newSpace)
                }
                newGrid.add(newRow)
            }
            return newGrid
        }

        private val DELTAS = listOf(
            Pair(-1, -1),
            Pair(-1, 0),
            Pair(-1, 1),
            Pair(0, -1),
            Pair(0, 1),
            Pair(1, -1),
            Pair(1, 0),
            Pair(1, 1)
        )

        private fun isVisuallyIsolated(seatGrid: List<List<Space>>, rowIndex: Int, colIndex: Int): Boolean {
            return DELTAS.none { delta -> isVisibleOccupiedSeat(seatGrid, rowIndex, colIndex, delta) }
        }

        private fun isVisuallyCrowded(seatGrid: List<List<Space>>, rowIndex: Int, colIndex: Int): Boolean {
            return DELTAS.count { delta -> isVisibleOccupiedSeat(seatGrid, rowIndex, colIndex, delta) } >= 5
        }

        private fun isVisibleOccupiedSeat(
            seatGrid: List<List<Space>>,
            rowIndex: Int,
            colIndex: Int,
            delta: Pair<Int, Int>
        ): Boolean {
            var visibleRow = rowIndex + delta.first
            var visibleCol = colIndex + delta.second
            while (visibleRow in seatGrid.indices && visibleCol in seatGrid[0].indices) {
                val visibleSeat = seatGrid[visibleRow][visibleCol]
                if (visibleSeat == Space.EMPTY) {
                    return false
                } else if (visibleSeat == Space.OCCUPIED) {
                    return true
                }
                visibleRow += delta.first
                visibleCol += delta.second
            }
            return false
        }
    }
}
