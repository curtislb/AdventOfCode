package com.adventofcode.curtislb.year2019.day13.game.tile

/**
 * A [Tile] representing an empty space on the board.
 */
object EmptyTile : Tile() {
    override val value: Int = 0
    override val symbol: Char = '.'
}
