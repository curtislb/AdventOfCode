package com.curtislb.adventofcode.common.grid

import lombok.Generated

/**
 * A read-only rectangular grid of values.
 */
interface Grid<out E> {
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
     * The index of the last row in this grid, or -1 if this grid is empty.
     */
    val lastRowIndex: Int get() = height - 1

    /**
     * The index of the last column in this grid, or -1 if this grid is empty.
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
    fun points(): Sequence<Point> =
        sequence {
            for (x in columnIndices) {
                for (negativeY in rowIndices) {
                    yield(Point(x, -negativeY))
                }
            }
        }

    /**
     * Checks if the given [point] corresponds to an element in this grid.
     */
    operator fun contains(point: Point): Boolean =
        point.x in columnIndices && -point.y in rowIndices

    /**
     * Returns the element at the given [rowIndex] and [colIndex] in this grid.
     *
     * @throws IndexOutOfBoundsException If [rowIndex] is not in [rowIndices] or [colIndex] is not
     *  in [columnIndices].
     */
    operator fun get(rowIndex: Int, colIndex: Int): E

    /**
     * Returns the element at the given [point] position in this grid.
     *
     * @throws IndexOutOfBoundsException If [point] is outside the grid range.
     */
    operator fun get(point: Point): E {
        val (rowIndex, colIndex) = point.toMatrixCoordinates()
        return this[rowIndex, colIndex]
    }

    /**
     * Returns the element at the given [rowIndex] and [colIndex] in this grid, or `null` if there
     * is no such element.
     */
    fun getOrNull(rowIndex: Int, colIndex: Int): E? =
        if (rowIndex in rowIndices && colIndex in columnIndices) {
            this[rowIndex, colIndex]
        } else {
            null
        }

    /**
     * Returns the element at the given [point] position in this grid, or `null` if there is no such
     * element.
     */
    fun getOrNull(point: Point): E? = if (point in this) this[point] else null

    /**
     * Returns a read-only copy of the row corresponding to the given [rowIndex] in this grid.
     *
     * @throws IndexOutOfBoundsException If [rowIndex] is not in [rowIndices].
     */
    fun row(rowIndex: Int): List<E>

    /**
     * Returns a read-only copy of the column corresponding to the given [colIndex] in this grid.
     *
     * @throws IndexOutOfBoundsException If [colIndex] is not in [columnIndices].
     */
    fun column(colIndex: Int): List<E>

    /**
     * Returns a read-only copy of the first row in this grid.
     *
     * @throws NoSuchElementException If this grid is empty.
     */
    fun firstRow(): List<E> =
        if (isEmpty()) {
            throw NoSuchElementException("Empty grid has no first row.")
        } else {
            row(0)
        }

    /**
     * Returns a read-only copy of the first column in this grid.
     *
     * @throws NoSuchElementException If this grid is empty.
     */
    fun firstColumn(): List<E> =
        if (isEmpty()) {
            throw NoSuchElementException("Empty grid has no first column.")
        } else {
            column(0)
        }

    /**
     * Returns a read-only copy of the last row in this grid.
     *
     * @throws NoSuchElementException If this grid is empty.
     */
    fun lastRow(): List<E> =
        if (isEmpty()) {
            throw NoSuchElementException("Empty grid has no last row.")
        } else {
            row(lastRowIndex)
        }

    /**
     * Returns a read-only copy of the last column of this grid.
     *
     * @throws NoSuchElementException If this grid is empty.
     */
    fun lastColumn(): List<E> =
        if (isEmpty()) {
            throw NoSuchElementException("Empty grid has no last column.")
        } else {
            column(lastColumnIndex)
        }

    /**
     * Returns a read-only copy of the row corresponding to the given [rowIndex] in this grid, or
     * `null` if there is no such row.
     */
    fun rowOrNull(rowIndex: Int): List<E>? = if (rowIndex in rowIndices) row(rowIndex) else null

    /**
     * Returns a read-only copy of the column corresponding to the given [colIndex] in this grid, or
     * `null` if there is no such column.
     */
    fun columnOrNull(colIndex: Int): List<E>? =
        if (colIndex in columnIndices) column(colIndex) else null

    /**
     * Returns a read-only copy of each of the rows in this grid.
     */
    fun rows(): List<List<E>> = if (isEmpty()) emptyList() else List(height, ::row)

    /**
     * Returns a read-only copy of each of the columns in this grid
     */
    fun columns(): List<List<E>> = if (isEmpty()) emptyList() else List(width, ::column)

    /**
     * Returns the row at the given [rowIndex] in this grid. The contents of this row may change
     * over time.
     *
     * Implementors may override this function to provide a more efficient version of [row]
     * without needing to guarantee immutability for the returned list.
     *
     * @throws IndexOutOfBoundsException If [rowIndex] is not in [rowIndices].
     */
    fun volatileRow(rowIndex: Int): List<E> = row(rowIndex)

    /**
     * Returns the column at the given [colIndex] in this grid. The contents of this column may
     * change over time.
     *
     * Implementors may override this function to provide a more efficient version of [column]
     * without needing to guarantee immutability for the returned list.
     *
     * @throws IndexOutOfBoundsException If [colIndex] is not in [columnIndices].
     */
    fun volatileColumn(colIndex: Int): List<E> = column(colIndex)

    /**
     * Returns a list containing each of the rows in this grid. The contents of this list may change
     * over time.
     *
     * Implementors may override this function to provide a more efficient version of [rows] without
     * needing to guarantee immutability for the returned list.
     */
    fun volatileRows(): List<List<E>> = rows()

    /**
     * Returns a list containing each of the columns in this grid. The contents of this list may
     * change over time.
     *
     * Implementors may override this function to provide a more efficient version of [columns]
     * without needing to guarantee immutability for the returned list.
     */
    fun volatileColumns(): List<List<E>> = columns()

    /**
     * Returns a string by transforming each row in this grid with the [transform] function and
     * combining them with the given [separator].
     */
    fun joinRowsToString(separator: String = ", ", transform: (row: List<E>) -> String): String =
        volatileRows().joinToString(separator = separator, transform = transform)

    /**
     * Returns a read-only copy of this grid that has been flipped horizontally.
     */
    fun flippedHorizontal(): Grid<E> =
        transformed(height, width) { Point(lastColumnIndex - it.x, it.y) }

    /**
     * Returns a read-only copy of this grid that has been rotated counterclockwise by 90 degrees.
     */
    fun rotatedLeft(): Grid<E> = transformed(width, height) { Point(-it.y, it.x - lastColumnIndex) }

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
    ): Grid<E> {
        if (isEmpty()) {
            require(newHeight == 0 && newWidth == 0) {
                "Empty grid can't be transformed into a non-empty grid."
            }
            return this
        }

        val tempValue = this[0, 0]
        val newGrid = MutableGrid(newHeight, newWidth) { _, _ -> tempValue }
        forEachPointValue { point, value -> newGrid[mapPoint(point)] = value }
        return newGrid
    }

    /**
     * Checks if this grid and [other] are equivalent.
     *
     * In addition to fulfilling the general contract, implementors must ensure that a grid is equal
     * to [EmptyGrid] if and only if the grid is empty (see [isEmpty]).
     */
    override fun equals(other: Any?): Boolean

    /**
     * Returns a hash code value for the grid.
     *
     * In addition to fulfilling the general contract, implementors must ensure that the hash code
     * for a grid is equal to the hash code for [EmptyGrid] if the grid is empty (see [isEmpty]).
     */
    override fun hashCode(): Int
}

/**
 * Returns a read-only grid with the given [height] and [width], with each element set by the given
 * [init] function.
 *
 * @throws IllegalArgumentException If [height] or [width] is negative, or if only one of [height]
 *  and [width] is zero.
 */
@Generated
@Suppress("FunctionName")
inline fun <E> Grid(height: Int, width: Int, init: (rowIndex: Int, colIndex: Int) -> E): Grid<E> =
    if (height == 0 && width == 0) emptyGrid() else RowArrayGrid(height, width, init)

/**
 * Returns a read-only grid constructed from the given [rows].
 *
 * @throws IllegalArgumentException If not all [rows] have equal size.
 */
fun <E> gridOf(vararg rows: List<E>): Grid<E> =
    if (rows.isEmpty()) emptyGrid() else RowArrayGrid(*rows)

/**
 * Returns a read-only grid constructed from this list of rows.
 *
 * @throws IllegalArgumentException If not all rows have equal size.
 */
fun <E> List<List<E>>.toGrid(): Grid<E> = if (isEmpty()) emptyGrid() else RowArrayGrid(this)

/**
 * Returns a read-only copy of this mutable grid.
 */
fun <E> MutableGrid<E>.toGrid(): Grid<E> = if (isEmpty()) emptyGrid() else RowArrayGrid(this)
