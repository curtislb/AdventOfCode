package com.curtislb.adventofcode.common.grid

import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.geometry.coordinateRanges
import com.curtislb.adventofcode.common.range.size

/**
 * Returns the minimal 2D grid that contains all [points], where the value at each position is given
 * by [valueAt].
 */
fun <T> createPointGrid(points: Iterable<Point>, valueAt: (point: Point) -> T): Grid<T> {
    // Determine the visible bounds of the grid
    val (xRange, yRange) = points.coordinateRanges()

    // Return an empty grid if given no points
    if (xRange.isEmpty() || yRange.isEmpty()) {
        return emptyGrid()
    }

    // Populate all grid cells in the visible bounds
    return Grid(yRange.size, xRange.size) { rowIndex, colIndex ->
        valueAt(Point(xRange.first + colIndex, yRange.last - rowIndex))
    }
}
