package com.curtislb.adventofcode.common.grid

/**
 * A mutable rectangular grid of values.
 */
interface MutableGrid<E> : Grid<E> {
    /**
     * Updates the element at the given [rowIndex] and [colIndex] in this grid to the given [value].
     *
     * @throws IndexOutOfBoundsException If [rowIndex] is not in [rowIndices] or [colIndex] is not
     *  in [columnIndices].
     */
    operator fun set(rowIndex: Int, colIndex: Int, value: E)

    /**
     * Updates the element at the given [point] position in this grid to the given [value].
     *
     * @throws IndexOutOfBoundsException If [point] is outside the bounds of the grid.
     */
    operator fun set(point: Point, value: E) {
        val (rowIndex, colIndex) = point.toMatrixCoordinates()
        this[rowIndex, colIndex] = value
    }

    /**
     * Appends a copy of the given [row] to the bottom of this grid.
     *
     * @throws IllegalArgumentException If [row] is empty or has a size different from the rows
     *  currently in the grid.
     */
    fun addRow(row: List<E>)

    /**
     * Appends a copy of the given [column] to the rightmost side of this grid.
     *
     * @throws IllegalArgumentException If [column] is empty or has a size different from the
     *  columns currently in the grid.
     */
    fun addColumn(column: List<E>)

    /**
     * Appends the given [row] to the bottom of this grid. Changes to this row may affect the
     * original list, and vice versa.
     *
     * Implementors may override this function to provide a more efficient version of [addRow]
     * without needing to create a defensive copy of the given list.
     *
     * @throws IllegalArgumentException If [row] is empty or has a size different from the rows
     *  currently in the grid.
     */
    fun addShallowRow(row: List<E>) = addRow(row)

    /**
     * Appends the given [column] to the rightmost side of this grid. Changes to this column may
     * affect the original list, and vice versa.
     *
     * Implementors may override this function to provide a more efficient version of [addColumn]
     * without needing to create a defensive copy of the given list.
     *
     * @throws IllegalArgumentException If [column] is empty or has a size different from the
     *  columns currently in the grid.
     */
    fun addShallowColumn(column: List<E>) = addColumn(column)

    /**
     * Removes the bottom row from this grid.
     *
     * @throws NoSuchElementException If the grid is empty.
     */
    fun removeLastRow()

    /**
     * Removes the rightmost column from this grid.
     *
     * @throws NoSuchElementException If the grid is empty.
     */
    fun removeLastColumn()
}

/**
 * Returns a new mutable grid with the given [height] and [width], with each element set by the
 * given [init] function.
 *
 * @throws IllegalArgumentException If [height] or [width] is negative, or if only one of [height]
 *  and [width] is zero.
 */
@Suppress("FunctionName")
inline fun <E> MutableGrid(
    height: Int,
    width: Int,
    init: (rowIndex: Int, colIndex: Int) -> E
): MutableGrid<E> = RowArrayGrid(height, width, init)

/**
 * Returns a new empty mutable grid.
 */
fun <E> mutableGridOf(): MutableGrid<E> = RowArrayGrid()

/**
 * Returns a new mutable grid constructed from the given [rows].
 *
 * @throws IllegalArgumentException If not all [rows] have equal size.
 */
fun <E> mutableGridOf(vararg rows: List<E>): MutableGrid<E> = RowArrayGrid(*rows)

/**
 * Returns a new mutable grid constructed from this list of rows.
 *
 * @throws IllegalArgumentException If not all rows have equal size.
 */
fun <E> List<List<E>>.toMutableGrid(): MutableGrid<E> = RowArrayGrid(this)

/**
 * Returns a new mutable copy of this read-only grid.
 */
fun <E> Grid<E>.toMutableGrid(): MutableGrid<E> = RowArrayGrid(this)
