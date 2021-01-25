package com.curtislb.adventofcode.common.grid

/**
 * Returns a grid containing the results of applying the given [transform] function to each element and its row and
 * column indices in the original grid.
 */
inline fun <T, R> Grid<T>.mapIndexed(transform: (rowIndex: Int, colIndex: Int, value: T) -> R): Grid<R> {
    return Grid(height, width) { rowIndex, colIndex ->
        transform(rowIndex, colIndex, this[rowIndex, colIndex])
    }
}

/**
 * Returns a grid containing the results of applying the given [transform] function to each element and its point
 * position in the original grid.
 */
inline fun <T, R> Grid<T>.mapPoints(transform: (point: Point, value: T) -> R): Grid<R> {
    return mapIndexed { rowIndex, colIndex, value ->
        transform(Point.fromMatrixCoordinates(rowIndex, colIndex), value)
    }
}
