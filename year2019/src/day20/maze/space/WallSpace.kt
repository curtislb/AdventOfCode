package com.curtislb.adventofcode.year2019.day20.maze.space

/**
 * A space representing a wall within the maze, which cannot be passed through.
 */
object WallSpace : Space {
    override val symbol: Char = '#'
    override val isOccupiable: Boolean = false
}
