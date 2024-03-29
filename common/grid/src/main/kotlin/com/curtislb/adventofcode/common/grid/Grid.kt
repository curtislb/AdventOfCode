package com.curtislb.adventofcode.common.grid

import com.curtislb.adventofcode.common.geometry.Point

/**
 * A read-only rectangular grid of values.
 */
interface Grid<out E> {
    /**
     * The number of rows in the grid.
     */
    val height: Int

    /**
     * The number of columns in the grid.
     */
    val width: Int

    /**
     * The number of elements contained in the grid.
     */
    val size: Int get() = height * width

    /**
     * The index of the last row in the grid, or -1 if the grid is empty.
     */
    val lastRowIndex: Int get() = height - 1

    /**
     * The index of the last column in the grid, or -1 if the grid is empty.
     */
    val lastColumnIndex: Int get() = width - 1

    /**
     * The range of valid row indices for the grid.
     */
    val rowIndices: IntRange get() = 0 until height

    /**
     * The range of valid column indices for the grid.
     */
    val columnIndices: IntRange get() = 0 until width

    /**
     * Checks if the grid is empty.
     */
    fun isEmpty(): Boolean = height == 0

    /**
     * Returns a sequence of all point positions in the grid.
     */
    fun points(): Sequence<Point> = sequence {
        for (negativeY in rowIndices) {
            for (x in columnIndices) {
                yield(Point(x, -negativeY))
            }
        }
    }

    /**
     * Returns a sequence of all element values in the grid.
     */
    fun values(): Sequence<E> = sequence {
        for (rowIndex in rowIndices) {
            for (colIndex in columnIndices) {
                yield(this@Grid[rowIndex, colIndex])
            }
        }
    }

    /**
     * Checks if the given [point] corresponds to an element in the grid.
     */
    operator fun contains(point: Point): Boolean =
        point.matrixRow in rowIndices && point.matrixCol in columnIndices

    /**
     * Returns the element at the given [rowIndex] and [colIndex] in the grid.
     *
     * @throws IndexOutOfBoundsException If [rowIndex] is not in [rowIndices] or [colIndex] is not
     *  in [columnIndices].
     */
    operator fun get(rowIndex: Int, colIndex: Int): E

    /**
     * Returns the element at the given [point] position in the grid.
     *
     * @throws IndexOutOfBoundsException If [point] is outside the grid range.
     */
    operator fun get(point: Point): E = this[point.matrixRow, point.matrixCol]

    /**
     * Returns the element at the given [rowIndex] and [colIndex] in the grid, or `null` if there
     * is no such element.
     */
    fun getOrNull(rowIndex: Int, colIndex: Int): E? =
        if (rowIndex in rowIndices && colIndex in columnIndices) {
            this[rowIndex, colIndex]
        } else {
            null
        }

    /**
     * Returns the element at the given [point] position in the grid, or `null` if there is no such
     * element.
     */
    fun getOrNull(point: Point): E? = if (point in this) this[point] else null

    /**
     * Returns a read-only copy of the row corresponding to the given [rowIndex] in the grid.
     *
     * @throws IndexOutOfBoundsException If [rowIndex] is not in [rowIndices].
     */
    fun row(rowIndex: Int): List<E>

    /**
     * Returns a read-only copy of the column corresponding to the given [colIndex] in the grid.
     *
     * @throws IndexOutOfBoundsException If [colIndex] is not in [columnIndices].
     */
    fun column(colIndex: Int): List<E>

    /**
     * Returns a read-only copy of the first row in the grid.
     *
     * @throws NoSuchElementException If the grid is empty.
     */
    fun firstRow(): List<E> {
        if (isEmpty()) {
            throw NoSuchElementException("Empty grid has no first row.")
        }
        return row(0)
    }

    /**
     * Returns a read-only copy of the first column in the grid.
     *
     * @throws NoSuchElementException If the grid is empty.
     */
    fun firstColumn(): List<E> {
        if (isEmpty()) {
            throw NoSuchElementException("Empty grid has no first column.")
        }
        return column(0)
    }

    /**
     * Returns a read-only copy of the last row in the grid.
     *
     * @throws NoSuchElementException If the grid is empty.
     */
    fun lastRow(): List<E> {
        if (isEmpty()) {
            throw NoSuchElementException("Empty grid has no last row.")
        }
        return row(lastRowIndex)
    }

    /**
     * Returns a read-only copy of the last column of the grid.
     *
     * @throws NoSuchElementException If the grid is empty.
     */
    fun lastColumn(): List<E> {
        if (isEmpty()) {
            throw NoSuchElementException("Empty grid has no last column.")
        }
        return column(lastColumnIndex)
    }

    /**
     * Returns a read-only copy of the row corresponding to the given [rowIndex] in the grid, or
     * `null` if there is no such row.
     */
    fun rowOrNull(rowIndex: Int): List<E>? = if (rowIndex in rowIndices) row(rowIndex) else null

    /**
     * Returns a read-only copy of the column corresponding to the given [colIndex] in the grid, or
     * `null` if there is no such column.
     */
    fun columnOrNull(colIndex: Int): List<E>? =
        if (colIndex in columnIndices) column(colIndex) else null

    /**
     * Returns a read-only copy of each of the rows in the grid.
     */
    fun rows(): List<List<E>> = if (isEmpty()) emptyList() else List(height, ::row)

    /**
     * Returns a read-only copy of each of the columns in the grid
     */
    fun columns(): List<List<E>> = if (isEmpty()) emptyList() else List(width, ::column)

    /**
     * Returns the row at the given [rowIndex] in the grid. The contents of this row may change over
     * time.
     *
     * Implementors may override this function to provide a more efficient version of [row]
     * without needing to guarantee immutability for the returned list.
     *
     * @throws IndexOutOfBoundsException If [rowIndex] is not in [rowIndices].
     */
    fun shallowRow(rowIndex: Int): List<E> = row(rowIndex)

    /**
     * Returns the column at the given [colIndex] in the grid. The contents of this column may
     * change over time.
     *
     * Implementors may override this function to provide a more efficient version of [column]
     * without needing to guarantee immutability for the returned list.
     *
     * @throws IndexOutOfBoundsException If [colIndex] is not in [columnIndices].
     */
    fun shallowColumn(colIndex: Int): List<E> = column(colIndex)

    /**
     * Returns a list containing each of the rows in the grid. The contents of this list may change
     * over time.
     *
     * Implementors may override this function to provide a more efficient version of [rows] without
     * needing to guarantee immutability for the returned list.
     */
    fun shallowRows(): List<List<E>> = rows()

    /**
     * Returns a list containing each of the columns in the grid. The contents of this list may
     * change over time.
     *
     * Implementors may override this function to provide a more efficient version of [columns]
     * without needing to guarantee immutability for the returned list.
     */
    fun shallowColumns(): List<List<E>> = columns()

    /**
     * Checks if the grid and [other] are equivalent.
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
inline fun <T> Grid(height: Int, width: Int, init: (rowIndex: Int, colIndex: Int) -> T): Grid<T> =
    if (height == 0 && width == 0) emptyGrid() else RowArrayGrid(height, width, init)

/**
 * Returns a read-only grid constructed from the given [rows].
 *
 * @throws IllegalArgumentException If not all [rows] have the same size.
 */
fun <T> gridOf(vararg rows: List<T>): Grid<T> =
    if (rows.isEmpty()) emptyGrid() else rowArrayGridOf(*rows)

/**
 * Returns a read-only grid constructed from this list of rows.
 *
 * @throws IllegalArgumentException If not all rows have the same size.
 */
fun <T> List<List<T>>.toGrid(): Grid<T> = if (isEmpty()) emptyGrid() else RowArrayGrid(this)

/**
 * Returns a read-only copy of this mutable grid.
 */
fun <T> MutableGrid<T>.toGrid(): Grid<T> = if (isEmpty()) emptyGrid() else RowArrayGrid(this)
