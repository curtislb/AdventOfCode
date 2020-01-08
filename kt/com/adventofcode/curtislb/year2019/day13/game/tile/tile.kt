package com.adventofcode.curtislb.year2019.day13.game.tile

import java.math.BigInteger

/**
 * A type of space on the game board, which can have various properties according to the rules of the game.
 */
abstract class Tile {
    /**
     * An [Int] value that uniquely identifies this [Tile].
     */
    abstract val value: Int

    /**
     * A [String] symbol which is used to represent this [Tile] on the game board.
     */
    abstract val symbol: String

    override fun toString() = symbol
}

/**
 * Converts an [Int] value to its corresponding [Tile].
 * @receiver An [Int] representing a valid [Tile].
 * @return The [Tile] object corresponding to this [Int].
 * @throws IllegalArgumentException If called for an [Int] with no corresponding [Tile].
 */
fun Int.toTile(): Tile {
    return when(this) {
        0 -> EmptyTile
        1 -> WallTile
        2 -> BlockTile
        3 -> PaddleTile
        4 -> BallTile
        else -> throw IllegalArgumentException("Unknown tile value: $this.")
    }
}

/**
 * Converts a [BigInteger] value to its corresponding [Tile].
 * @receiver A [BigInteger] representing a valid [Tile].
 * @return The [Tile] object corresponding to this [BigInteger].
 * @throws IllegalArgumentException If called for a [BigInteger] with no corresponding [Tile].
 */
fun BigInteger.toTile(): Tile = this.toInt().toTile()
