package com.curtislb.adventofcode.year2019.day13.game

import com.curtislb.adventofcode.common.grid.Point
import java.math.BigInteger

/**
 * The current state of the game board and associated information.
 */
class Board {
    /**
     * A map from each position on the grid to the tile at that location.
     */
    private val tiles: MutableMap<Point, Tile> = mutableMapOf()

    /**
     * The player's current score.
     */
    var score: BigInteger = BigInteger.ZERO
        private set

    /**
     * The height of the board, in number of tiles.
     */
    var height: Int = 0
        private set

    /**
     * The width of the board, in number of tiles.
     */
    var width: Int = 0
        private set

    /**
     * Returns the positions of all tiles of a given [type] on the board.
     */
    fun findAll(type: Tile): List<Point> = tiles.filter { (_, tile) -> tile == type }.map { (point, _) -> point }

    /**
     * Returns the type of the tile at [position] on the board.
     */
    operator fun get(position: Point): Tile = tiles.getOrDefault(position, Tile.EMPTY)

    /**
     * Updates the type of the tile at a given [position] on the board.
     */
    operator fun set(position: Point, value: BigInteger) {
        if (position.x < 0) {
            score = value
        } else {
            tiles[position] = Tile.from(value)
            height = height.coerceAtLeast(-position.y + 1)
            width = width.coerceAtLeast(position.x + 1)
        }
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder("Score: $score\n")
        for (row in 0 until height) {
            for (col in 0 until width) {
                stringBuilder.append(tiles.getOrDefault(Point.fromMatrixCoordinates(row, col), Tile.EMPTY).symbol)
            }
            stringBuilder.append('\n')
        }
        return stringBuilder.toString()
    }
}
