package com.adventofcode.curtislb.year2019.day15.repair.space

/**
 * A [Space] representing a wall, which the repair droid cannot pass through.
 */
object WallSpace : Space() {
    override val value: Int = 1
    override val symbol: Char = '#'
}
