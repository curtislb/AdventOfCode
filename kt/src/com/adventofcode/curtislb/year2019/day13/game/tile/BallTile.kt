package com.adventofcode.curtislb.year2019.day13.game.tile

/**
 * A [Tile] representing a ball, which moves diagonally and bounces off objects.
 */
object BallTile : Tile() {
    override val value: Int = 4
    override val symbol: String = "\u2022"
}
