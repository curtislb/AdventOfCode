package com.curtislb.adventofcode.year2023.day10.pipes

import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.grid.Grid
import com.curtislb.adventofcode.common.grid.mutableGridOf
import com.curtislb.adventofcode.common.grid.pointOfFirst
import java.io.File

/**
 * A 2D grid containing a maze of connected pipes, including at least one continuous pipe loop that
 * originates from a specified location in the grid.
 *
 * @property grid A grid of [Tile]s, representing sections of pipe and ground that make up the maze.
 *
 * @constructor Creates a new [PipeMaze] instance with the given tile [grid].
 */
class PipeMaze(private val grid: Grid<Tile>) {
    /**
     * Returns a list of connected points that make up a continuous pipe loop, starting from the
     * first [Tile.START] space in the grid.
     *
     * If there is no continuous pipe loop originating from the [Tile.START] space, this function
     * instead returns `null`.
     */
    fun findLoop(): List<Point>? {
        // Find the starting point of the pipe loop
        val startPoint = grid.pointOfFirst { it == Tile.START } ?: return null

        // Try each pipe tile in place of the starting tile
        for (startTile in Tile.entries) {
            if (!startTile.isPipe()) {
                continue
            }

            // Construct the loop by following pipe connections from the starting tile
            val loop = mutableListOf(startPoint)
            var direction = startTile.directions.first()
            var point = startPoint.move(direction)
            while (point != startPoint) {
                // Check if the next tile is connected to the previous one
                val tile = grid.getOrNull(point) ?: break
                val backDirection = direction.reverse()
                if (backDirection !in tile.directions) {
                    break
                }

                // If the tile is valid, add it to the loop
                loop.add(point)
                direction = tile.directions.first { it != backDirection }
                point = point.move(direction)
            }

            // Return the complete loop when the starting point is reached
            if (point == startPoint) {
                return loop
            }
        }

        // Unable to find a complete loop
        return null
    }

    companion object {
        /**
         * Returns a [PipeMaze] with a tile grid read from the given [file].
         *
         * The [file] must consist of a list of equal-length rows, where each character represents
         * the symbol of the tile at that location in the [PipeMaze] grid. For example:
         *
         * ```
         * .....
         * .S-7.
         * .|.|.
         * .L-J.
         * .....
         * ```
         *
         * @throws IllegalArgumentException If [file] has the wrong format.
         */
        fun fromFile(file: File): PipeMaze {
            val grid = mutableGridOf<Tile>()
            file.forEachLine { line ->
                val row = line.map { Tile.fromChar(it) }
                grid.addShallowRow(row)
            }
            return PipeMaze(grid)
        }
    }
}
