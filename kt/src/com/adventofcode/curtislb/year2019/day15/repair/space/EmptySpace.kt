package com.adventofcode.curtislb.year2019.day15.repair.space

/**
 * A [Space] representing an empty space, which the repair droid can pass through.
 */
object EmptySpace : Space() {
    override val value: Int = 0
    override val symbol: Char = '.'
}
