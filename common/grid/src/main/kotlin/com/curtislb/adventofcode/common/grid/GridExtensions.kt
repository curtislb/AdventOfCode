package com.curtislb.adventofcode.common.grid

import lombok.Generated

/**
 * Adds a new row to this grid, constructed by applying the [initRow] function to an empty list.
 */
@Generated
inline fun <E> MutableGrid<E>.addRowWith(initRow: MutableList<E>.() -> Unit) {
    addShallowRow(ArrayList<E>().apply(initRow))
}

/**
 * Performs the given [action] on each element and its row and column indices in this grid.
 */
@Generated
inline fun <E> Grid<E>.forEachIndexed(action: (rowIndex: Int, colIndex: Int, value: E) -> Unit) {
    for (rowIndex in rowIndices) {
        for (colIndex in columnIndices) {
            action(rowIndex, colIndex, this[rowIndex, colIndex])
        }
    }
}

/**
 * Performs the given [action] on each element and its point position in this grid.
 */
@Generated
inline fun <E> Grid<E>.forEachPointValue(action: (point: Point, value: E) -> Unit) {
    forEachIndexed { rowIndex, colIndex, value ->
        action(Point.fromMatrixCoordinates(rowIndex, colIndex), value)
    }
}

/**
 * Returns a grid containing the results of applying the given [transform] function to each element
 * and its row and column indices in the original grid.
 */
@Generated
inline fun <E, R> Grid<E>.mapIndexed(
    transform: (rowIndex: Int, colIndex: Int, value: E) -> R
): Grid<R> =
    Grid(height, width) { rowIndex, colIndex ->
        transform(rowIndex, colIndex, this[rowIndex, colIndex])
    }

/**
 * Returns a grid containing the results of applying the given [transform] function to each element
 * and its point position in the original grid.
 */
@Generated
inline fun <E, R> Grid<E>.mapPointValues(transform: (point: Point, value: E) -> R): Grid<R> =
    mapIndexed { rowIndex, colIndex, value ->
        transform(Point.fromMatrixCoordinates(rowIndex, colIndex), value)
    }

/**
 * Returns the sum of the values given by applying the [transform] function to each row in the grid.
 */
@Generated
inline fun <E> Grid<E>.sumRowsBy(transform: (row: List<E>) -> Int): Int =
    volatileRows().sumOf(transform)