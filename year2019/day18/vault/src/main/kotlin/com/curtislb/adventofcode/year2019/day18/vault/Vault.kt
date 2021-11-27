package com.curtislb.adventofcode.year2019.day18.vault

import com.curtislb.adventofcode.common.grid.Grid
import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.addRowWith
import com.curtislb.adventofcode.common.grid.forEachPointValue
import com.curtislb.adventofcode.common.grid.mutableGridOf
import com.curtislb.adventofcode.year2019.day18.vault.space.EntranceSpace
import com.curtislb.adventofcode.year2019.day18.vault.space.KeySpace
import com.curtislb.adventofcode.year2019.day18.vault.space.Space
import java.io.File

/**
 * A vault consisting of a grid of tunnels, which contain doors that may be unlocked by keys.
 *
 * @param file A file containing the grid layout for this vault. Each line should contain a row of
 *  characters, with each representing a [Space] in the vault.
 */
class Vault(file: File) {
    /**
     * A matrix representing the space at each position in the vault.
     */
    private val grid: Grid<Space> = mutableGridOf<Space>().apply {
        file.forEachLine { line ->
            addRowWith { line.trim().forEach { char -> add(Space.from(char)) } }
        }
    }

    /**
     * All positions in this vault that contain an [EntranceSpace].
     */
    val entranceLocations: Set<Point> by lazy {
        mutableSetOf<Point>().apply {
            grid.forEachPointValue { point, space ->
                if (space == EntranceSpace) {
                    add(point)
                }
            }
        }
    }

    /**
     * A map from each key symbol to the position where the corresponding [KeySpace] is located.
     */
    val keyLocations: Map<Char, Point> by lazy {
        mutableMapOf<Char, Point>().apply {
            grid.forEachPointValue { point, space ->
                if (space is KeySpace) {
                    this[space.symbol] = point
                }
            }
        }
    }

    /**
     * Returns the space at [position] in the vault, or `null` if [position] is not in the vault.
     */
    operator fun get(position: Point): Space? = grid.getOrNull(position)

    override fun toString(): String {
        return grid.joinRowsToString(separator = "\n") { row ->
            row.joinToString(separator = "") { it.symbol.toString() }
        }
    }
}
