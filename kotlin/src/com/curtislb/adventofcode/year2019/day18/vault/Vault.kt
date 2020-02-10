package com.curtislb.adventofcode.year2019.day18.vault

import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.year2019.day18.vault.space.EntranceSpace
import com.curtislb.adventofcode.year2019.day18.vault.space.KeySpace
import com.curtislb.adventofcode.year2019.day18.vault.space.Space
import java.io.File

/**
 * A vault consisting of a grid of tunnels, which contain doors that may be unlocked by corresponding keys.
 *
 * @param file A file containing the grid layout for this vault. Each line should contain a row of characters, with each
 *  representing a [Space] in the vault.
 */
class Vault(file: File) {
    /**
     * A matrix representing the space at each position in the grid.
     */
    private val grid: List<List<Space>>

    init {
        val gridBuilder = mutableListOf<List<Space>>()
        file.forEachLine { line -> gridBuilder.add(line.trim().map { char -> Space.from(char) }) }
        grid = gridBuilder
    }

    /**
     * All positions in this vault that contain an [EntranceSpace].
     */
    val entranceLocations: Set<Point> by lazy {
        val entrancesBuilder = mutableSetOf<Point>()
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == EntranceSpace) {
                    entrancesBuilder.add(Point.fromMatrixCoordinates(i, j))
                }
            }
        }
        entrancesBuilder
    }

    /**
     * A map from each key symbol to the position where the corresponding [KeySpace] is located.
     */
    val keyLocations: Map<Char, Point> by lazy {
        val keyLocationsBuilder = mutableMapOf<Char, Point>()
        grid.forEachIndexed { i, row ->
            row.forEachIndexed { j, space ->
                if (space is KeySpace) {
                    keyLocationsBuilder[space.symbol] = Point.fromMatrixCoordinates(i, j)
                }
            }
        }
        keyLocationsBuilder
    }

    /**
     * Returns the space located at [position] in the vault, or `null` if [position] is not in the vault.
     */
    operator fun get(position: Point): Space? {
        val (rowIndex, colIndex) = position.toMatrixCoordinates()
        return if (rowIndex in grid.indices && colIndex in grid[rowIndex].indices) grid[rowIndex][colIndex] else null
    }

    override fun toString(): String {
        return grid.joinToString(separator = "\n") { row -> row.joinToString(separator = "") { it.symbol.toString() } }
    }
}
