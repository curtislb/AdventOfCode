package com.curtislb.adventofcode.common.grid

import lombok.Generated

/**
 * Adds a new row to this grid, constructed by applying the [initRow] function to an empty list.
 *
 * @throws IllegalArgumentException If this grid is non-empty and the new row is of the wrong size.
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
 * Returns the results of applying the given [transform] function to each element of the row with
 * the given [rowIndex] in the grid.
 */
@Generated
inline fun <E, R> Grid<E>.mapRow(rowIndex: Int, transform: (value: E) -> R): List<R> =
    shallowRow(rowIndex).map(transform)

/**
 * Returns the sum of the values given by applying the [transform] function to each row in the grid.
 */
@Generated
inline fun <E> Grid<E>.sumRowsBy(transform: (row: List<E>) -> Int): Int =
    shallowRows().sumOf(transform)

/**
 * Replaces each element in the grid with the result of the [update] function applied to that value.
 */
@Generated
inline fun <E> MutableGrid<E>.replaceAll(update: (value: E) -> E) =
    forEachIndexed { rowIndex, colIndex, value -> this[rowIndex, colIndex] = update(value) }
