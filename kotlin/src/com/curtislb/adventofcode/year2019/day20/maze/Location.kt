package com.curtislb.adventofcode.year2019.day20.maze

import com.curtislb.adventofcode.common.grid.Point

/**
 * A unique representation of a location within a recursive maze.
 *
 * @param point A position in the maze relative to the current layer.
 * @param depth The depth of the current recursive layer within the maze.
 */
data class Location(val point: Point, val depth: Int = 0)
