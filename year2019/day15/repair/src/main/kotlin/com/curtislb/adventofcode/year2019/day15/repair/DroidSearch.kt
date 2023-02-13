package com.curtislb.adventofcode.year2019.day15.repair

import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.graph.Bfs

/**
 * Breadth-first search config for a [droid], navigating an explored grid.
 */
class DroidSearch(private val droid: Droid) : Bfs<Point>() {
    /**
     * Returns all spaces that the [Droid] can move into from its current position [node].
     */
    override fun getNeighbors(node: Point): Iterable<Point> = droid.adjacentOccupiableSpaces(node)
}
