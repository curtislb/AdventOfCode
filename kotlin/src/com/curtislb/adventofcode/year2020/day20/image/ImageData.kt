package com.curtislb.adventofcode.year2020.day20.image

import com.curtislb.adventofcode.common.collection.mapToMap
import com.curtislb.adventofcode.common.grid.Direction
import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.constructGrid
import com.curtislb.adventofcode.common.grid.getCellOrNull
import com.curtislb.adventofcode.common.io.forEachSection
import java.io.File
import kotlin.math.roundToInt
import kotlin.math.sqrt

/**
 * TODO
 */
class ImageData(file: File) {
    /**
     * TODO
     */
    private val tileArrangement: List<List<Tile>>

    init {
        val tileMap = mutableMapOf<Int, Tile>().apply {
            file.forEachSection { lines ->
                val tile = Tile.from(lines.joinToString(separator = "\n"))
                this[tile.id] = tile
            }
        }

        val adjacencyMap = mutableMapOf<Int, MutableMap<Direction, Int>>().apply {
            tileMap.values.forEach { tile ->
                for (tileDirection in Direction.values()) {
                    var isMatchFound = false
                    for (otherTile in tileMap.values) {
                        if (isMatchFound) {
                            break
                        }

                        if (tile.id == otherTile.id) {
                            continue
                        }

                        for (otherDirection in Direction.values()) {
                            val tileBorder = tile.border(tileDirection)
                            val otherBorder = otherTile.border(otherDirection)
                            if (tileBorder == otherBorder || tileBorder == otherBorder.reversed()) {
                                getOrPut(tile.id) { mutableMapOf() }[tileDirection] = otherTile.id
                                getOrPut(otherTile.id) { mutableMapOf() }[otherDirection] = tile.id
                                isMatchFound = true
                                break
                            }
                        }
                    }
                }
            }
        }

        val tilesPerSide = sqrt(tileMap.size.toDouble()).roundToInt()
        val arrangedTiles = List(tilesPerSide) { MutableList(tilesPerSide) { Tile(0, emptyList()) } }
        val arrangedTileIDs = mutableSetOf<Int>()
        for (startColumn in 0 until tilesPerSide) {
            val startRows = if (startColumn < tilesPerSide - 1) 0..0 else tileMap.keys.indices
            for (startRow in startRows) {
                var rowIndex = startRow
                var colIndex = startColumn
                while (rowIndex in 0 until tilesPerSide && colIndex in 0 until tilesPerSide) {
                    val tileAbove = arrangedTiles.getCellOrNull(rowIndex - 1, colIndex)
                    val tileLeft = arrangedTiles.getCellOrNull(rowIndex, colIndex - 1)

                    val maxAdjacentTileCount = 4 - listOf(tileAbove, tileLeft).count { it == null }
                    val matchingEntries = adjacencyMap.entries.filter { (tileID, adjacentTiles) ->
                        tileID !in arrangedTileIDs &&
                            adjacentTiles.size <= maxAdjacentTileCount &&
                            tileAbove?.let { it.id in adjacentTiles.values } != false &&
                            tileLeft?.let { it.id in adjacentTiles.values } != false
                    }

                    var isMatchFound = false
                    for ((tileID, adjacentTiles) in matchingEntries) {
                        val tile = tileMap[tileID]?.orient(tileAbove, tileLeft, adjacentTiles)
                        if (tile != null) {
                            arrangedTiles[rowIndex][colIndex] = tile
                            arrangedTileIDs.add(tileID)
                            isMatchFound = true
                            break
                        }
                    }

                    if (!isMatchFound) {
                        throw IllegalArgumentException("No suitable tile for row $rowIndex, column $colIndex.")
                    }

                    rowIndex++
                    colIndex--
                }
            }
        }

        tileArrangement = arrangedTiles
    }

    /**
     * TODO
     */
    private val tileSideLength = tileArrangement.first().first().sideLength

    /**
     * TODO
     */
    val sideLength: Int = tileArrangement.size * tileSideLength

    /**
     * TODO
     */
    val cornerTiles: List<Tile> get() = listOf(
        tileArrangement.first().first(),
        tileArrangement.first().last(),
        tileArrangement.last().first(),
        tileArrangement.last().last()
    )

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
        val tileRowIndex = rowIndex / tileSideLength
        val tileColIndex = colIndex / tileSideLength
        val innerRowIndex = rowIndex % tileSideLength
        val innerColIndex = colIndex % tileSideLength
        return tileArrangement[tileRowIndex][tileColIndex][innerRowIndex, innerColIndex]
    }

    /**
     * TODO
     */
    fun constructImageGrid(): List<List<Boolean>> {
        return constructGrid(listOf(Point.ORIGIN, Point(sideLength - 1, 1 - sideLength))) { this[it] }
    }

    companion object {
        /**
         * TODO
         */
        private fun Tile.orient(tileAbove: Tile?, tileLeft: Tile?, adjacentTiles: Map<Direction, Int>): Tile? {
            var orientedTile = this
            var orientedAdjacentTiles = adjacentTiles
            val borderAbove = tileAbove?.border(Direction.DOWN)
            val borderLeft = tileLeft?.border(Direction.RIGHT)
            for (rotationCount in 0..3) {
                if (orientedAdjacentTiles[Direction.UP] == tileAbove?.id &&
                    orientedAdjacentTiles[Direction.LEFT] == tileLeft?.id &&
                    (borderAbove == null || borderAbove == orientedTile.border(Direction.UP)) &&
                    (borderLeft == null || borderLeft == orientedTile.border(Direction.LEFT))
                ) {
                    return orientedTile
                }

                orientedTile = orientedTile.rotateLeft()
                orientedAdjacentTiles = orientedAdjacentTiles.mapToMap { (direction, tileID) ->
                    direction.turnLeft() to tileID
                }
            }

            orientedTile = orientedTile.flipHorizontal()
            orientedAdjacentTiles = orientedAdjacentTiles.mapToMap { (direction, tileID) ->
                val newDirection = when (direction) {
                    Direction.RIGHT, Direction.LEFT -> direction.reverse()
                    else -> direction
                }
                newDirection to tileID
            }

            for (rotationCount in 0..3) {
                if (orientedAdjacentTiles[Direction.UP] == tileAbove?.id &&
                    orientedAdjacentTiles[Direction.LEFT] == tileLeft?.id &&
                    (borderAbove == null || borderAbove == orientedTile.border(Direction.UP)) &&
                    (borderLeft == null || borderLeft == orientedTile.border(Direction.LEFT))
                ) {
                    return orientedTile
                }

                orientedTile = orientedTile.rotateLeft()
                orientedAdjacentTiles = orientedAdjacentTiles.mapToMap { (direction, tileID) ->
                    direction.turnLeft() to tileID
                }
            }

            return null
        }
    }
}
