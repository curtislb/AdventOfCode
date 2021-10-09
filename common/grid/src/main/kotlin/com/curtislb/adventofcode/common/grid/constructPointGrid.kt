package com.curtislb.adventofcode.common.grid

import com.curtislb.adventofcode.common.range.rangeSize
import lombok.Generated

/**
 * Returns the minimal 2D grid that contains all [points], where the value at each position is given by [valueAt].
 */
@Generated
inline fun <T> constructPointGrid(points: Iterable<Point>, valueAt: (point: Point) -> T): Grid<T> {
    // Determine the visible bounds of the grid.
    val (xRange, yRange) = points.coordinateRanges()

    // Return an empty grid if given no points.
    if (xRange.isEmpty() || yRange.isEmpty()) {
        return emptyGrid()
    }

    // Populate all grid cells in the visible bounds.
    val height = yRange.rangeSize
    val width = xRange.rangeSize
    val minX = xRange.start
    val maxY = yRange.endInclusive
    return Grid(height, width) { rowIndex, colIndex -> valueAt(Point(minX + colIndex, maxY - rowIndex)) }
}
