package com.curtislb.adventofcode.common.grid

import lombok.Generated

/**
 * A mutable rectangular grid of values.
 */
interface MutableGrid<T> : Grid<T> {
    /**
     * Updates the element at the given [rowIndex] and [colIndex] in this grid to the given [value].
     */
    operator fun set(rowIndex: Int, colIndex: Int, value: T)

    /**
     * Updates the element at the given [point] position in this grid to the given [value].
     */
    operator fun set(point: Point, value: T) {
        val (rowIndex, colIndex) = point.toMatrixCoordinates()
        this[rowIndex, colIndex] = value
    }

    /**
     * Appends a copy of the given [row] to the bottom of this grid.
     */
    fun addRow(row: List<T>)

    /**
     * Appends a copy of the given [column] to the rightmost side of this grid.
     */
    fun addColumn(column: List<T>)

    /**
     * Appends the given [row] to the bottom of this grid. Changes to this row may affect the
     * original list, and vice versa.
     *
     * Implementors may override this function to provide a more efficient version of [addRow]
     * without needing to create a defensive copy of the given list.
     */
    fun addShallowRow(row: List<T>) = addRow(row)

    /**
     * Appends the given [column] to the rightmost side of this grid. Changes to this column may
     * affect the original list, and vice versa.
     *
     * Implementors may override this function to provide a more efficient version of [addColumn]
     * without needing to create a defensive copy of the given list.
     */
    fun addShallowColumn(column: List<T>) = addColumn(column)

    /**
     * Removes the bottom row from this grid.
     */
    fun removeLastRow()

    /**
     * Removes the rightmost column from this grid.
     */
    fun removeLastColumn()
}

/**
 * Returns a new mutable grid with the given [height] and [width], with each element set by the
 * given [init] function.
 */
@Generated
@Suppress("FunctionName")
inline fun <T> MutableGrid(
    height: Int,
    width: Int,
    init: (rowIndex: Int, colIndex: Int) -> T
): MutableGrid<T> {
    return RowArrayGrid(height, width, init)
}

/**
 * Returns a new empty mutable grid.
 */
fun <T> mutableGridOf(): MutableGrid<T> = RowArrayGrid()

/**
 * Returns a new mutable grid constructed from the given [rows].
 */
fun <T> mutableGridOf(vararg rows: List<T>): MutableGrid<T> = RowArrayGrid(*rows)
