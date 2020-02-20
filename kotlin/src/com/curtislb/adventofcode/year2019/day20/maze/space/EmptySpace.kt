package com.curtislb.adventofcode.year2019.day20.maze.space

/**
 * An empty space in or around the maze, which cannot be occupied.
 */
object EmptySpace : Space {
    override val symbol: Char = ' '
    override val isOccupiable: Boolean = false
}
