package com.curtislb.adventofcode.common.grid

import com.curtislb.adventofcode.common.range.sizeInclusive

/**
 * Returns the minimal 2D grid that contains all [points], where the value at each position is given
 * by [valueAt].
 */
fun <T> constructPointGrid(points: Iterable<Point>, valueAt: (point: Point) -> T): Grid<T> {
    // Determine the visible bounds of the grid.
    val (xRange, yRange) = points.coordinateRanges()

    // Return an empty grid if given no points.
    if (xRange.isEmpty() || yRange.isEmpty()) {
        return emptyGrid()
    }

    // Populate all grid cells in the visible bounds.
    val height = yRange.sizeInclusive
    val width = xRange.sizeInclusive
    val minX = xRange.start
    val maxY = yRange.endInclusive
    return Grid(height, width) { rowIndex, colIndex ->
        valueAt(Point(minX + colIndex, maxY - rowIndex))
    }
}
