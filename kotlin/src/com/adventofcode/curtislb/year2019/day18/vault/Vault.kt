package com.adventofcode.curtislb.year2019.day18.vault

import com.adventofcode.curtislb.common.grid.Point
import com.adventofcode.curtislb.year2019.day18.vault.space.EntranceSpace
import com.adventofcode.curtislb.year2019.day18.vault.space.KeySpace
import com.adventofcode.curtislb.year2019.day18.vault.space.Space
import java.io.File

/**
 * A vault consisting of a grid of tunnels, which contain doors that may be unlocked by corresponding keys.
 * @param file A [File] from which the spaces that make up the grid of tunnels for this [Vault] should be read.
 */
class Vault(file: File) {
    /**
     * A [List] of lists representing each [Space] in the grid of tunnels for this [Vault].
     */
    private val grid: List<List<Space>>

    init {
        val gridBuilder = mutableListOf<List<Space>>()
        file.forEachLine { line -> gridBuilder.add(line.trim().map { char -> Space.from(char) }) }
        grid = gridBuilder
    }

    /**
     * A [Set] of all [Point] positions in this [Vault] where an [EntranceSpace] is located.
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
     * A [Map] from each key [Char] to the [Point] in this [Vault] where the corresponding [KeySpace] is located.
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
     * Gets the [Space] located at a given [Point] in this [Vault].
     * @param point The [Point] position of the desired [Space].
     * @return The [Space] located at position [point] in the grid, or `null` if [point] is outside the grid bounds.
     */
    operator fun get(point: Point): Space? {
        val (rowIndex, colIndex) = point.toMatrixCoordinates()
        return if (rowIndex in grid.indices && colIndex in grid[rowIndex].indices) grid[rowIndex][colIndex] else null
    }

    override fun toString(): String {
        return grid.joinToString(separator = "\n") { row -> row.joinToString(separator = "") { it.symbol.toString() } }
    }
}
