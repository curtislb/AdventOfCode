package com.adventofcode.curtislb.year2019.day13.game.tile

/**
 * A [Tile] representing a paddle, which is indestructible and can be moved horizontally by the player.
 */
object PaddleTile : Tile() {
    override val value: Int = 3
    override val symbol: String = "\u25ac"
}
