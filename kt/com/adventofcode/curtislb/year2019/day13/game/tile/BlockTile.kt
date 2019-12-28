package com.adventofcode.curtislb.year2019.day13.game.tile

/**
 * A [Tile] representing a block, which can be broken by a ball.
 */
object BlockTile : Tile() {
    override val value: Int = 2
    override val symbol: String = "\u25a0"
}
