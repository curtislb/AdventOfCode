package com.adventofcode.curtislb.year2019.day13.game.tile

/**
 * A [Tile] representing a wall, which is indestructible and can't be moved.
 */
object WallTile : Tile() {
    override val value: Int = 1
    override val symbol: String = "\u2588"
}
