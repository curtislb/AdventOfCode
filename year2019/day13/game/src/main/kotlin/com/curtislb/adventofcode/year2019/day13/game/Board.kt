package com.curtislb.adventofcode.year2019.day13.game

import com.curtislb.adventofcode.common.grid.Point

/**
 * The current state of the game board and associated information.
 */
class Board {
    /**
     * A map from each board position to the tile at that location.
     */
    private val tiles: MutableMap<Point, Tile> = mutableMapOf()

    /**
     * The width of the board, in number of tiles.
     */
    var width: Int = 0
        private set

    /**
     * The height of the board, in number of tiles.
     */
    var height: Int = 0
        private set

    /**
     * Returns the type of the [Tile] at a given [position] on the board.
     *
     * A valid board [position] is one whose x-coordinate is 0 or positive and whose y-coordinate is
     * 0 or negative.
     *
     * @throws IllegalArgumentException If [position] is invalid.
     */
    operator fun get(position: Point): Tile {
        require(position.x >= 0) { "Position must have a non-negative x-coordinate: $position" }
        require(position.y <= 0) { "Position must have a non-positive y-coordinate: $position" }
        return tiles.getOrDefault(position, Tile.EMPTY)
    }

    /**
     * Updates the tile at a given [position] on the board to match the given [type].
     *
     * A valid [position] is one whose x-coordinate is 0 or positive and whose y-coordinate is 0 or
     * negative.
     *
     * @throws IllegalArgumentException If [position] is invalid.
     */
    operator fun set(position: Point, type: Tile) {
        require(position.x >= 0) { "Position must have a non-negative x-coordinate: $position" }
        require(position.y <= 0) { "Position must have a non-positive y-coordinate: $position" }
        tiles[position] = type
        height = maxOf(height, -position.y + 1)
        width = maxOf(width, position.x + 1)
    }

    /**
     * Returns the positions of all tiles of a given [type] on the board.
     */
    fun findAll(type: Tile): List<Point> {
        return if (type == Tile.EMPTY) {
            // Find unset tiles on the board in addition to ones explicitly set to empty.
            mutableListOf<Point>().apply {
                for (x in 0 until width) {
                    for (y in 0 downTo -height + 1) {
                        val point = Point(x, y)
                        if (tiles.getOrDefault(point, Tile.EMPTY) == Tile.EMPTY) {
                            add(point)
                        }
                    }
                }
            }
        } else {
            tiles.filter { (_, tile) -> tile == type }.map { (point, _) -> point }
        }
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        for (row in 0 until height) {
            for (col in 0 until width) {
                stringBuilder.append(
                    tiles.getOrDefault(
                        Point.fromMatrixCoordinates(row, col),
                        Tile.EMPTY
                    ).symbol
                )
            }
            if (row < height - 1) {
                stringBuilder.append('\n')
            }
        }
        return stringBuilder.toString()
    }
}
