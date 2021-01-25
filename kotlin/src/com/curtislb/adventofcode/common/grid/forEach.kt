package com.curtislb.adventofcode.common.grid

/**
 * Performs the given [action] on each element and its row and column indices in this grid.
 */
inline fun <T> Grid<T>.forEachIndexed(action: (rowIndex: Int, colIndex: Int, value: T) -> Unit) {
    for (rowIndex in rowIndices) {
        for (colIndex in columnIndices) {
            action(rowIndex, colIndex, this[rowIndex, colIndex])
        }
    }
}

/**
 * Performs the given [action] on each element and its point position in this grid.
 */
inline fun <T> Grid<T>.forEachPoint(action: (point: Point, value: T) -> Unit) {
    forEachIndexed { rowIndex, colIndex, value -> action(Point.fromMatrixCoordinates(rowIndex, colIndex), value) }
}
