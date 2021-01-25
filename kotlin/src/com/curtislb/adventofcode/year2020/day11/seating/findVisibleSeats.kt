package com.curtislb.adventofcode.year2020.day11.seating

import com.curtislb.adventofcode.common.grid.Grid
import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.Ray

/**
 * Returns the positions of all seats that are visible from a given [position] in a [grid] of spaces.
 *
 * A seat is visible from a point if it is the closest (empty or occupied) seat to that point in one of the eight
 * cardinal or diagonal directions.
 */
fun findVisibleSeats(grid: Grid<Space>, position: Point): Sequence<Point> = sequence {
    // Look for seats in each of eight possible directions.
    position.allNeighbors().forEach { neighbor ->
        // Look for a seat at each point along a directional ray.
        val ray = Ray(position, neighbor)
        for (point in ray.points().drop(1)) {
            when (grid.getOrNull(point)) {
                // Stop and yield the first visible seat.
                Space.EMPTY, Space.OCCUPIED -> {
                    yield(point)
                    break
                }

                // Floor spaces don't block visible seats.
                Space.FLOOR -> Unit

                // Reached the edge of the grid without finding a seat.
                null -> break
            }
        }
    }
}
