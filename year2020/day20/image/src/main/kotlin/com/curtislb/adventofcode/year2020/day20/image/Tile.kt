package com.curtislb.adventofcode.year2020.day20.image

import com.curtislb.adventofcode.common.geometry.Direction
import com.curtislb.adventofcode.common.grid.Grid
import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.grid.horizontalReflection
import com.curtislb.adventofcode.common.grid.joinRowsToString
import com.curtislb.adventofcode.common.grid.leftRotation
import com.curtislb.adventofcode.common.grid.toGrid

/**
 * TODO
 */
class Tile(val id: Int, private var dataGrid: Grid<Boolean>) {
    init {
        require(dataGrid.height == dataGrid.width) {
            "Data grid must be square: height = ${dataGrid.height}, width = ${dataGrid.width}"
        }
    }

    /**
     * TODO
     */
    val sideLength = dataGrid.height - 2

    /**
     * TODO
     */
    operator fun get(point: Point): Boolean = this[point.matrixRow, point.matrixCol]

    /**
     * TODO
     */
    operator fun get(rowIndex: Int, colIndex: Int): Boolean {
        if (rowIndex !in 0..sideLength || colIndex !in 0..sideLength) {
            throw IndexOutOfBoundsException(
                "Row ($rowIndex) and column ($colIndex) indices must be in range 0..$sideLength."
            )
        }
        return dataGrid[rowIndex + 1, colIndex + 1]
    }

    /**
     * TODO
     */
    fun border(direction: Direction): List<Boolean> = when (direction) {
        Direction.UP -> dataGrid.firstRow()
        Direction.RIGHT -> dataGrid.lastColumn()
        Direction.DOWN -> dataGrid.lastRow()
        Direction.LEFT -> dataGrid.firstColumn()
        else -> throw IllegalArgumentException("No border for direction: $direction")
    }

    /**
     * TODO
     */
    fun rotateLeft(): Tile = Tile(id, dataGrid.leftRotation())

    /**
     * TODO
     */
    fun flipHorizontal(): Tile = Tile(id, dataGrid.horizontalReflection())

    override fun toString(): String = dataGrid.joinRowsToString(separator = "\n") { row ->
        row.joinToString(separator = "") { isFilled -> if (isFilled) "#" else "." }
    }

    companion object {
        fun from(string: String): Tile {
            val lines = string.trim().lines()
            val tileID = lines[0].filter { it.isDigit() }.toInt()
            val rowStrings = lines.subList(1, lines.size)
            return Tile(
                tileID,
                rowStrings.map { rowStr -> rowStr.trim().map { char -> char == '#' } }.toGrid()
            )
        }
    }
}
