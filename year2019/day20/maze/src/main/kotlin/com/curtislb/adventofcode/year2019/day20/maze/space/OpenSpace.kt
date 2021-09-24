package com.curtislb.adventofcode.year2019.day20.maze.space

/**
 * An open space within the maze, which can be occupied.
 */
object OpenSpace : Space {
    override val symbol: Char = '.'
    override val isOccupiable: Boolean = true
}
