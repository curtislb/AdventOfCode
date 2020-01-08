package com.adventofcode.curtislb.year2019.day13.game.board

import com.adventofcode.curtislb.common.grid.Point
import com.adventofcode.curtislb.year2019.day13.game.tile.EmptyTile
import com.adventofcode.curtislb.year2019.day13.game.tile.Tile
import com.adventofcode.curtislb.year2019.day13.game.tile.toTile
import java.math.BigInteger

/**
 * The current state of the game board and associated information.
 */
class Board {
    /**
     * A [Map] from each [Point] on the grid to the [Tile] at that position.
     *
     * All positions are measured from the top-left corner of the [Board], with x-coordinates increasing to the right
     * and y-coordinate values increasing downward.
     */
    private val tiles: MutableMap<Point, Tile> = mutableMapOf()

    /**
     * The player's current score, to be updated as the game progresses.
     */
    var score: BigInteger = BigInteger.ZERO
        private set

    /**
     * The height of the [Board], in number of tiles.
     */
    var height: Int = 0
        private set

    /**
     * The width of the [Board], in number of tiles.
     */
    var width: Int = 0
        private set

    /**
     * Finds all tiles of a given type on the [Board].
     * @param type The type of [Tile] to locate on the [Board].
     * @return A [List] of all [Point] positions on the [Board] that contain a [Tile] matching [type].
     */
    fun findAll(type: Tile): List<Point> = tiles.filter { (_, tile) -> tile == type }.map { (point, _) -> point }

    /**
     * Gets the type of [Tile] currently at a given position on the board.
     * @param positionX The x-coordinate position of a [Point] on the board.
     * @param positionY The y-coordinate position of a [Point] on the board.
     * @return The type of the tile at the [Point] `(positionX, positionY)` on the [Board].
     */
    operator fun get(positionX: Int, positionY: Int): Tile = tiles.getOrDefault(Point(positionX, positionY), EmptyTile)

    /**
     * Updates the type of [Tile] at a given position on the board.
     * @param positionX The x-coordinate position of a [Point] on the board.
     * @param positionY The y-coordinate position of a [Point] on the board.
     * @param value A value representing the new [Tile] at [Point] `(positionX, positionY)` on the [Board].
     */
    operator fun set(positionX: Int, positionY: Int, value: BigInteger) {
        if (positionX < 0) {
            score = value
        } else {
            tiles[Point(positionX, positionY)] = value.toTile()
            height = height.coerceAtLeast(positionY + 1)
            width = width.coerceAtLeast(positionX + 1)
        }
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder("Score: $score\n")
        for (row in 0 until height) {
            for (col in 0 until width) {
                stringBuilder.append(tiles.getOrDefault(Point(x = col, y = row), EmptyTile))
            }
            stringBuilder.append('\n')
        }
        return stringBuilder.toString()
    }
}
