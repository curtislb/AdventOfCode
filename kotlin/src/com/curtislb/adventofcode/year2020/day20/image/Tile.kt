package com.curtislb.adventofcode.year2020.day20.image

import com.curtislb.adventofcode.common.grid.Direction
import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.flippedHorizontal
import com.curtislb.adventofcode.common.grid.rotatedLeft

/**
 * TODO
 */
class Tile(val id: Int, private var dataGrid: List<List<Boolean>>) {
    /**
     * TODO
     */
    val sideLength = dataGrid.size - 2

    /**
     * TODO
     */
    operator fun get(point: Point): Boolean {
        val (rowIndex, colIndex) = point.toMatrixCoordinates()
        return this[rowIndex, colIndex]
    }

    /**
     * TODO
     */
    operator fun get(rowIndex: Int, colIndex: Int): Boolean {
        if (rowIndex !in 0..sideLength || colIndex !in 0..sideLength) {
            throw IndexOutOfBoundsException(
                "Row ($rowIndex) and column ($colIndex) indices must be in the range 0..$sideLength."
            )
        }
        return dataGrid[rowIndex + 1][colIndex + 1]
    }

    /**
     * TODO
     */
    fun border(direction: Direction): List<Boolean> = when (direction) {
        Direction.UP -> dataGrid.first()
        Direction.RIGHT -> dataGrid.map { it.last() }
        Direction.DOWN -> dataGrid.last()
        Direction.LEFT -> dataGrid.map { it.first() }
    }

    /**
     * TODO
     */
    fun rotateLeft() = Tile(id, dataGrid.rotatedLeft())

    /**
     * TODO
     */
    fun flipHorizontal(): Tile = Tile(id, dataGrid.flippedHorizontal())

    override fun toString(): String = dataGrid.joinToString(separator = "\n") { row ->
        row.joinToString(separator = "") { isFilled -> if (isFilled) "#" else "." }
    }

    companion object {
        fun from(string: String): Tile {
            val lines = string.trim().lines()
            val tileID = lines[0].filter { it.isDigit() }.toInt()
            val rowStrings = lines.subList(1, lines.size)
            return Tile(tileID, rowStrings.map { rowString -> rowString.trim().map { char -> char == '#' } })
        }
    }
}
