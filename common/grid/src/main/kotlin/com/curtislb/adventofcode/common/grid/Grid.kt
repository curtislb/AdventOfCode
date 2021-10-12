package com.curtislb.adventofcode.common.grid

import lombok.Generated

/**
 * A read-only rectangular grid of values.
 */
interface Grid<out T> {
    /**
     * The number of rows in this grid.
     */
    val height: Int

    /**
     * The number of columns in this grid.
     */
    val width: Int

    /**
     * The number of elements contained in this grid.
     */
    val size: Int get() = height * width

    /**
     * The index of the last row in this grid.
     */
    val lastRowIndex: Int get() = height - 1

    /**
     * The index of the last column in this grid.
     */
    val lastColumnIndex: Int get() = width - 1

    /**
     * The range of valid row indices for this grid.
     */
    val rowIndices: IntRange get() = 0 until height

    /**
     * The range of valid column indices for this grid.
     */
    val columnIndices: IntRange get() = 0 until width

    /**
     * Checks if this grid is empty.
     */
    fun isEmpty(): Boolean = height == 0

    /**
     * Returns a sequence of all point positions in this grid.
     */
    fun points(): Sequence<Point> = sequence {
        for (x in columnIndices) {
            for (negativeY in rowIndices) {
                yield(Point(x, -negativeY))
            }
        }
    }

    /**
     * Checks if the given [point] corresponds to an element in this grid.
     */
    operator fun contains(point: Point): Boolean {
        return point.x in columnIndices && -point.y in rowIndices
    }

    /**
     * Returns the element at the given [rowIndex] and [colIndex] in this grid.
     */
    operator fun get(rowIndex: Int, colIndex: Int): T

    /**
     * Returns the element at the given [point] position in this grid.
     */
    operator fun get(point: Point): T {
        val (rowIndex, colIndex) = point.toMatrixCoordinates()
        return this[rowIndex, colIndex]
    }

    /**
     * Returns the element at the given [rowIndex] and [colIndex] in this grid, or `null` if there
     * is no such element.
     */
    fun getOrNull(rowIndex: Int, colIndex: Int): T? {
        return if (rowIndex in rowIndices && colIndex in columnIndices) {
            this[rowIndex, colIndex]
        } else {
            null
        }
    }

    /**
     * Returns the element at the given [point] position in this grid, or `null` if there is no such
     * element.
     */
    fun getOrNull(point: Point): T? = if (point in this) this[point] else null

    /**
     * Returns a read-only copy of the row corresponding to the given [rowIndex] in this grid.
     */
    fun row(rowIndex: Int): List<T>

    /**
     * Returns a read-only copy of the column corresponding to the given [colIndex] in this grid.
     */
    fun column(colIndex: Int): List<T>

    /**
     * Returns a read-only copy of the first row in this grid.
     */
    fun firstRow(): List<T> = row(0)

    /**
     * Returns a read-only copy of the first column in this grid.
     */
    fun firstColumn(): List<T> = column(0)

    /**
     * Returns a read-only copy of the last row in this grid.
     */
    fun lastRow(): List<T> = row(lastRowIndex)

    /**
     * Returns a read-only copy of the last column of this grid.
     */
    fun lastColumn(): List<T> = column(lastColumnIndex)

    /**
     * Returns a read-only copy of the row corresponding to the given [rowIndex] in this grid, or
     * `null` if there is no such row.
     */
    fun rowOrNull(rowIndex: Int): List<T>? = if (rowIndex in rowIndices) row(rowIndex) else null

    /**
     * Returns a read-only copy of the column corresponding to the given [colIndex] in this grid, or
     * `null` if there is no such column.
     */
    fun columnOrNull(colIndex: Int): List<T>? =
        if (colIndex in columnIndices) column(colIndex) else null

    /**
     * Returns a read-only copy of each of the rows in this grid.
     */
    fun rows(): List<List<T>> = List(height, ::row)

    /**
     * Returns a read-only copy of each of the columns in this grid
     */
    fun columns(): List<List<T>> = List(width, ::column)

    /**
     * Returns the row at the given [rowIndex] in this grid. The contents of this row may change
     * over time.
     *
     * Implementors may override this function to provide a more efficient version of [row]
     * without needing to guarantee immutability for the returned list.
     */
    fun volatileRow(rowIndex: Int): List<T> = row(rowIndex)

    /**
     * Returns the column at the given [colIndex] in this grid. The contents of this column may
     * change over time.
     *
     * Implementors may override this function to provide a more efficient version of [column]
     * without needing to guarantee immutability for the returned list.
     */
    fun volatileColumn(colIndex: Int): List<T> = column(colIndex)

    /**
     * Returns a list containing each of the rows in this grid. The contents of this list may change
     * over time.
     *
     * Implementors may override this function to provide a more efficient version of [rows] without
     * needing to guarantee immutability for the returned list.
     */
    fun volatileRows(): List<List<T>> = rows()

    /**
     * Returns a list containing each of the columns in this grid. The contents of this list may
     * change over time.
     *
     * Implementors may override this function to provide a more efficient version of [columns]
     * without needing to guarantee immutability for the returned list.
     */
    fun volatileColumns(): List<List<T>> = columns()

    /**
     * Returns a read-only copy of this grid that has been flipped horizontally.
     */
    fun flippedHorizontal(): Grid<T> =
        transformed(height, width) { Point(lastColumnIndex - it.x, it.y) }

    /**
     * Returns a read-only copy of this grid that has been rotated counterclockwise by 90 degrees.
     */
    fun rotatedLeft(): Grid<T> = transformed(width, height) { Point(-it.y, it.x - lastColumnIndex) }

    /**
     * Returns a new grid with the given [newHeight] and [newWidth]. The element at each point in
     * the current grid is copied to the point in the new grid given by the [mapPoint] function.
     *
     * Any points in the new grid that are not explicitly mapped to by the [mapPoint] function will
     * get a copy of the element in the top-left corner of the current grid.
     *
     * @throws IllegalArgumentException If the current grid is empty and either [newHeight] or
     *  [newWidth] is nonzero.
     */
    @Generated
    private inline fun transformed(
        newHeight: Int,
        newWidth: Int,
        mapPoint: (point: Point) -> Point
    ): Grid<T> {
        if (isEmpty()) {
            require(newHeight == 0 && newWidth == 0) {
                "Empty grid can't be transformed into a non-empty grid."
            }
            return this
        }

        val tempValue = this[0, 0]
        val newGrid = MutableGrid(newHeight, newWidth) { _, _ -> tempValue }
        forEachPoint { point, value -> newGrid[mapPoint(point)] = value }
        return newGrid
    }
}

/**
 * Returns a read-only grid with the given [height] and [width], with each element set by the given
 * [init] function.
 */
@Generated
@Suppress("FunctionName")
inline fun <T> Grid(height: Int, width: Int, init: (rowIndex: Int, colIndex: Int) -> T): Grid<T> {
    return RowArrayGrid(height, width, init)
}

/**
 * Returns a read-only grid constructed from the given [rows].
 */
fun <T> gridOf(vararg rows: List<T>): Grid<T> = RowArrayGrid(*rows)
