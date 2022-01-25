package com.curtislb.adventofcode.common.grid

import java.util.Objects
import lombok.Generated

/**
 * A mutable rectangular grid of values, implemented as an [ArrayList] of [ArrayList] rows.
 *
 * @param rowList A list of the rows that make up this grid.
 *
 * @throws IllegalArgumentException If any row in [rowList] is empty, or if not all rows in
 *  [rowList] have the same size.
 */
class RowArrayGrid<E>(private val rowList: ArrayList<ArrayList<E>> = ArrayList()) : MutableGrid<E> {
    init {
        require(rowList.none { it.isEmpty() }) { "Can't initialize grid with empty rows." }
        require(rowList.isEmpty() || rowList.all { it.size == rowList.first().size }) {
            "All rows must have the same size."
        }
    }

    override val height: Int get() = rowList.size

    override val width: Int get() = rowList.firstOrNull()?.size ?: 0

    override fun get(rowIndex: Int, colIndex: Int): E = rowList[rowIndex][colIndex]

    override fun row(rowIndex: Int): List<E> = rowList[rowIndex].toList()

    override fun column(colIndex: Int): List<E> {
        Objects.checkIndex(colIndex, width)
        return rowList.map { it[colIndex] }
    }

    override fun shallowRow(rowIndex: Int): List<E> = rowList[rowIndex]

    override fun shallowRows(): List<List<E>> = rowList

    override fun set(rowIndex: Int, colIndex: Int, value: E) {
        rowList[rowIndex][colIndex] = value
    }

    override fun addRow(row: List<E>) {
        require(row.isNotEmpty()) { "Can't add empty row to grid." }
        require(isEmpty() || row.size == width) {
            "Row size (${row.size}) must match grid width ($width)."
        }

        rowList.add(ArrayList(row))
    }

    override fun addColumn(column: List<E>) {
        require(column.isNotEmpty()) { "Can't add empty column to grid." }
        require(isEmpty() || column.size == height) {
            "Column size (${column.size}) must match grid height ($height)."
        }

        val isFirstColumn = isEmpty()
        column.forEachIndexed { index, value ->
            if (isFirstColumn) {
                rowList.add(arrayListOf(value))
            } else {
                rowList[index].add(value)
            }
        }
    }

    override fun addShallowRow(row: List<E>) {
        require(row.isNotEmpty()) { "Can't add empty row to grid." }
        require(isEmpty() || row.size == width) {
            "Row size (${row.size}) must match grid width ($width)."
        }

        rowList.add(if (row is ArrayList<E>) row else ArrayList(row))
    }

    override fun removeLastRow() {
        if (isEmpty()) {
            throw NoSuchElementException("Can't remove last row from an empty grid.")
        }
        rowList.removeLast()
    }

    override fun removeLastColumn() {
        if (isEmpty()) {
            throw NoSuchElementException("Can't remove last column from an empty grid.")
        }
        for (row in rowList) {
            row.removeLast()
        }
    }

    override fun equals(other: Any?): Boolean = other is Grid<*> && rowList == other.shallowRows()

    override fun hashCode(): Int = if (isEmpty()) emptyList<E>().hashCode() else rowList.hashCode()

    override fun toString(): String =
        if (isEmpty()) "[]" else "[${rowList.joinToString(separator = ",\n ")}]"
}

/**
 * Returns a new array grid with the given [height] and [width], with each element set by the given
 * [init] function.
 *
 * @throws IllegalArgumentException If [height] or [width] is negative, or if only one of [height]
 *  and [width] is zero.
 */
@Generated
@Suppress("FunctionName")
inline fun <T> RowArrayGrid(
    height: Int,
    width: Int,
    init: (rowIndex: Int, colIndex: Int) -> T
): RowArrayGrid<T> {
    require(height >= 0) { "Height must be non-negative: $height" }
    require(width >= 0) { "Width must be non-negative: $width" }
    require((height != 0 && width != 0) || height == width) {
        "Height ($height) and width ($width) must both be positive or zero"
    }

    val rows = ArrayList<ArrayList<T>>(height).apply {
        for (rowIndex in 0 until height) {
            val row = ArrayList<T>(width).apply {
                for (colIndex in 0 until width) {
                    add(init(rowIndex, colIndex))
                }
            }
            add(row)
        }
    }
    return RowArrayGrid(rows)
}

/**
 * Returns a new [RowArrayGrid], constructed from the given [grid].
 */
@Suppress("FunctionName")
fun <T> RowArrayGrid(grid: Grid<T>): RowArrayGrid<T> =
    RowArrayGrid(grid.height, grid.width) { rowIndex, colIndex -> grid[rowIndex, colIndex] }

/**
 * Returns a new [RowArrayGrid], constructed from the given [rows].
 *
 * @throws IllegalArgumentException If any row in [rows] is empty, or if not all rows have the same
 *  size.
 */
@Suppress("FunctionName")
fun <T> RowArrayGrid(rows: List<List<T>>): RowArrayGrid<T> {
    require(rows.none { it.isEmpty() }) { "Can't initialize grid with empty rows." }
    require(rows.all { it.size == rows.first().size }) { "All rows must have the same size." }
    return RowArrayGrid(rows.size, rows.firstOrNull()?.size ?: 0) { rowIndex, colIndex ->
        rows[rowIndex][colIndex]
    }
}

/**
 * Returns a new [RowArrayGrid], constructed from the given [rows].
 *
 * @throws IllegalArgumentException If any row in [rows] is empty, or if not all rows have the same
 *  size.
 */
@Suppress("FunctionName")
fun <T> RowArrayGrid(vararg rows: List<T>): RowArrayGrid<T> {
    require(rows.none { it.isEmpty() }) { "Can't initialize grid with empty rows." }
    require(rows.all { it.size == rows.first().size }) { "All rows must have the same size." }
    return RowArrayGrid(rows.size, rows.firstOrNull()?.size ?: 0) { rowIndex, colIndex ->
        rows[rowIndex][colIndex]
    }
}
