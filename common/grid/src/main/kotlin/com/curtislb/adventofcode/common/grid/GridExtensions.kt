package com.curtislb.adventofcode.common.grid

import com.curtislb.adventofcode.common.geometry.Point

/**
 * Returns the number of elements in the grid for which the [predicate] function returns `true`.
 */
inline fun <T> Grid<T>.count(predicate: (value: T) -> Boolean): Int {
    var count = 0
    for (rowIndex in rowIndices) {
        for (colIndex in columnIndices) {
            if (predicate(this[rowIndex, colIndex])) {
                count++
            }
        }
    }
    return count
}

/**
 * Performs the given [action] on each element and its row and column indices in this grid, in
 * row-major order.
 */
inline fun <T> Grid<T>.forEachIndexed(action: (rowIndex: Int, colIndex: Int, value: T) -> Unit) {
    for (rowIndex in rowIndices) {
        for (colIndex in columnIndices) {
            action(rowIndex, colIndex, this[rowIndex, colIndex])
        }
    }
}

/**
 * Performs the given [action] on each element and its point position in this grid, in row-major
 * order.
 */
inline fun <T> Grid<T>.forEachPointValue(action: (point: Point, value: T) -> Unit) {
    forEachIndexed { rowIndex, colIndex, value ->
        action(Point.fromMatrixCoordinates(rowIndex, colIndex), value)
    }
}

/**
 * Returns a grid with elements given by applying the [transform] function to each element and its
 * row and column indices in the original grid.
 */
inline fun <T, R> Grid<T>.mapIndexed(
    transform: (rowIndex: Int, colIndex: Int, value: T) -> R
): Grid<R> {
    return Grid(height, width) { rowIndex, colIndex ->
        transform(rowIndex, colIndex, this[rowIndex, colIndex])
    }
}

/**
 * Returns a grid with elements given by applying the [transform] function to each element and its
 * point position in the original grid.
 */
inline fun <T, R> Grid<T>.mapPointValues(transform: (point: Point, value: T) -> R): Grid<R> =
    mapIndexed { rowIndex, colIndex, value ->
        transform(Point.fromMatrixCoordinates(rowIndex, colIndex), value)
    }

/**
 * Returns a grid with elements given by applying the [transform] function to each element of the
 * row with the given [rowIndex] in the original grid.
 *
 * @throws IndexOutOfBoundsException If the grid has no row with the given [rowIndex].
 */
inline fun <T, R> Grid<T>.mapRow(rowIndex: Int, transform: (value: T) -> R): List<R> =
    shallowRow(rowIndex).map(transform)

/**
 * Returns the point location of the first grid element (in row-major order) for which the
 * [predicate] function returns `true`, or `null` if no element matches the predicate.
 */
inline fun <T> Grid<T>.pointOfFirst(predicate: (value: T) -> Boolean): Point? {
    forEachIndexed { rowIndex, colIndex, value ->
        if (predicate(value)) {
            return Point.fromMatrixCoordinates(rowIndex, colIndex)
        }
    }
    return null
}

/**
 * Returns the sum of the values given by applying the [transform] function to each row in the grid.
 */
inline fun <T> Grid<T>.sumRowsBy(transform: (row: List<T>) -> Int): Int =
    shallowRows().sumOf(transform)

/**
 * Returns a string by transforming each row in the grid with the [transform] function and
 * combining them with the given [separator].
 */
fun <T> Grid<T>.joinRowsToString(
    separator: CharSequence = ", ",
    transform: ((row: List<T>) -> CharSequence)? = null
): String {
    return shallowRows().joinToString(separator = separator, transform = transform)
}

/**
 * Returns a read-only copy of the grid that has been reflected horizontally.
 */
fun <T> Grid<T>.horizontalReflection(): Grid<T> =
    Grid(height, width) { rowIndex, colIndex -> get(rowIndex, lastColumnIndex - colIndex) }

/**
 * Returns a read-only copy of the grid that has been rotated counterclockwise by 90 degrees.
 */
fun <T> Grid<T>.leftRotation(): Grid<T> =
    Grid(width, height) { rowIndex, colIndex -> get(colIndex, lastColumnIndex - rowIndex) }
