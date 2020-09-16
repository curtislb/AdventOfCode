package com.curtislb.adventofcode.common.grid

/**
 * Returns the minimal 2D grid that contains all [points], where the value at each position is given by [valueAt].
 */
inline fun <T> constructGrid(points: Iterable<Point>, valueAt: (point: Point) -> T): List<List<T>> {
    // Determine the visible bounds of the grid.
    var minX: Int? = null
    var minY: Int? = null
    var maxX: Int? = null
    var maxY: Int? = null
    points.forEach { point ->
        minX = minX?.coerceAtMost(point.x) ?: point.x
        minY = minY?.coerceAtMost(point.y) ?: point.y
        maxX = maxX?.coerceAtLeast(point.x) ?: point.x
        maxY = maxY?.coerceAtLeast(point.y) ?: point.y
    }

    // Return an empty list if given no points.
    val gridMinX = minX
    val gridMinY = minY
    val gridMaxX = maxX
    val gridMaxY = maxY
    if (gridMinX == null || gridMinY == null || gridMaxX == null || gridMaxY == null) {
        return emptyList()
    }

    // Populate all grid cells in the visible bounds.
    val grid = mutableListOf<List<T>>()
    for (i in 0..(gridMaxY - gridMinY)) {
        val row = mutableListOf<T>()
        for (j in 0..(gridMaxX - gridMinX)) {
            row.add(valueAt(Point(gridMinX + j, gridMaxY - i)))
        }
        grid.add(row)
    }
    return grid
}
