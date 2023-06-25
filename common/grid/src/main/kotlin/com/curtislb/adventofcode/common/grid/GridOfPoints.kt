package com.curtislb.adventofcode.common.grid

import com.curtislb.adventofcode.common.geometry.CoordinateRanges
import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.range.size

/**
 * Returns the minimal 2D grid that contains all [points], where the value at each position is given
 * by [valueAt].
 */
fun <T> gridOfPoints(points: Iterable<Point>, valueAt: (point: Point) -> T): Grid<T> {
    // Determine the visible bounds of the grid
    val coordRanges = CoordinateRanges.ofPoints(points)

    // Return an empty grid if given no points
    if (coordRanges.isEmpty()) {
        return emptyGrid()
    }

    // Populate all grid cells in the visible bounds
    return Grid(coordRanges.y.size(), coordRanges.x.size()) { rowIndex, colIndex ->
        val x = coordRanges.x.first + colIndex
        val y = coordRanges.y.last - rowIndex
        valueAt(Point(x, y))
    }
}
