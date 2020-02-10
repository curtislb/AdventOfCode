package com.curtislb.adventofcode.common.grid

/**
 * Returns the minimal 2D grid that contains all [points], where the value at each position is given by [valueAt].
 */
fun <T> constructGrid(points: Iterable<Point>, valueAt: (point: Point) -> T): List<List<T>> {
    // Determine the visible bounds of the grid.
    var minX = 0
    var minY = 0
    var maxX = 0
    var maxY = 0
    points.forEach { point ->
        minX = minX.coerceAtMost(point.x)
        minY = minY.coerceAtMost(point.y)
        maxX = maxX.coerceAtLeast(point.x)
        maxY = maxY.coerceAtLeast(point.y)
    }

    // Populate all grid cells in the visible bounds.
    val grid = mutableListOf<List<T>>()
    for (i in 0..(maxY - minY)) {
        val row = mutableListOf<T>()
        for (j in 0..(maxX - minX)) {
            row.add(valueAt(Point(minX + j, maxY - i)))
        }
        grid.add(row)
    }
    return grid
}
