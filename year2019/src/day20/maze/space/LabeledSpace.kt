package com.curtislb.adventofcode.year2019.day20.maze.space

/**
 * A [space] with an associated string [label].
 */
data class LabeledSpace(val space: Space, val label: String) : Space {
    override val symbol: Char = space.symbol
    override val isOccupiable: Boolean = space.isOccupiable
}
