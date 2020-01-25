package com.adventofcode.curtislb.year2019.day18.vault

import com.adventofcode.curtislb.common.grid.Point
import com.adventofcode.curtislb.year2019.day18.vault.space.EntranceSpace
import com.adventofcode.curtislb.year2019.day18.vault.space.KeySpace
import com.adventofcode.curtislb.year2019.day18.vault.space.Space
import java.io.File

/**
 * TODO
 */
class Vault(file: File) {
    /**
     * TODO
     */
    private val grid: List<List<Space>>

    init {
        val gridBuilder = mutableListOf<List<Space>>()
        file.forEachLine { line -> gridBuilder.add(line.trim().map { char -> Space.from(char) }) }
        grid = gridBuilder
    }

    /**
     * TODO
     */
    val entrance: Point? by lazy {
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == EntranceSpace) {
                    return@lazy Point.fromMatrixCoordinates(i, j)
                }
            }
        }
        null
    }

    /**
     * TODO
     */
    val keyCount: Int by lazy { grid.sumBy { row -> row.sumBy { space -> if (space is KeySpace) 1 else 0 } } }

    /**
     * TODO
     */
    operator fun get(point: Point): Space {
        val (rowIndex, colIndex) = point.toMatrixCoordinates()
        return grid[rowIndex][colIndex]
    }

    override fun toString(): String {
        return grid.joinToString(separator = "\n") { row -> row.joinToString(separator = "") { it.symbol.toString() } }
    }
}
