package com.curtislb.adventofcode.common.grid

import java.util.Objects

/**
 * A mutable rectangular grid of values, implemented as an [ArrayList] of [ArrayList] rows.
 *
 * @param E The type of element contained in the grid.
 * @param initialCapacity The initial capacity for the [ArrayList] of rows that make up the grid. If
 *  this value is `null`, the default initial capacity will be used.
 *
 * @constructor Creates a new instance of [RowArrayGrid], with an optional `initialCapacity` for the
 *  [ArrayList] of rows.
 *
 * @throws IllegalArgumentException If any row is empty, or if not all rows have the same size.
 */
class RowArrayGrid<E>(initialCapacity: Int? = null) : MutableGrid<E> {
    /**
     * All rows currently in the grid.
     */
    private val rowList: ArrayList<ArrayList<E>> =
        if (initialCapacity == null) ArrayList() else ArrayList(initialCapacity)

    override val height: Int get() = rowList.size

    override val width: Int get() = if (rowList.isEmpty()) 0 else rowList[0].size

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
        checkRow(row)
        rowList.add(ArrayList(row))
    }

    override fun addColumn(column: List<E>) {
        require(column.isNotEmpty()) { "Can't add empty column to grid." }
        if (rowList.isEmpty()) {
            for (value in column) {
                rowList.add(arrayListOf(value))
            }
        } else {
            require(column.size == height) {
                "Column size must match grid height: ${column.size} != $height"
            }
            column.forEachIndexed { index, value -> rowList[index].add(value) }
        }
    }

    override fun addShallowRow(row: List<E>) {
        checkRow(row)
        rowList.add(if (row is ArrayList<E>) row else ArrayList(row))
    }

    override fun removeLastRow() {
        if (rowList.isEmpty()) {
            throw NoSuchElementException("Can't remove last row from an empty grid.")
        }
        rowList.removeLast()
    }

    override fun removeLastColumn() {
        when (width) {
            0 -> throw NoSuchElementException("Can't remove last column from an empty grid.")
            1 -> rowList.clear()
            else -> {
                for (row in rowList) {
                    row.removeLast()
                }
            }
        }
    }

    override fun toString(): String =
        rowList.joinToString(prefix = "[", separator = ",\n ", postfix = "]")

    override fun equals(other: Any?): Boolean = other is Grid<*> && rowList == other.shallowRows()

    override fun hashCode(): Int = if (isEmpty()) emptyList<E>().hashCode() else rowList.hashCode()

    /**
     * Checks if the given [row] can be added to the grid.
     *
     * @throws IllegalArgumentException If [row] is empty or doesn't match the size of rows in the
     *  grid.
     */
    private fun checkRow(row: List<*>) {
        require(row.isNotEmpty()) { "Can't add empty row to grid." }
        require(isEmpty() || row.size == width) {
            "Row size must match grid width: ${row.size} != $width"
        }
    }
}

/**
 * Returns a new [RowArrayGrid] with the given [height] and [width], where each element is
 * initialized by calling the specified [init] function.
 *
 * @throws IllegalArgumentException If [height] or [width] is negative, or if exactly one of
 *  [height] and [width] is zero.
 */
inline fun <T> RowArrayGrid(
    height: Int,
    width: Int,
    init: (rowIndex: Int, colIndex: Int) -> T
): RowArrayGrid<T> {
    require(height >= 0) { "Height must be non-negative: $height" }
    require(width >= 0) { "Width must be non-negative: $width" }
    require((height != 0 && width != 0) || height == width) {
        "Height and width must both be positive or both be zero: $height != $width"
    }

    // Initialize and add elements to the grid one row at a time
    val grid = RowArrayGrid<T>(height)
    for (rowIndex in 0 until height) {
        val row = ArrayList<T>(width)
        for (colIndex in 0 until width) {
            val value = init(rowIndex, colIndex)
            row.add(value)
        }
        grid.addShallowRow(row)
    }

    return grid
}

/**
 * Returns a new [RowArrayGrid] that contains the same elements as the given [grid].
 */
fun <T> RowArrayGrid(grid: Grid<T>): RowArrayGrid<T> =
    RowArrayGrid(grid.height, grid.width) { rowIndex, colIndex -> grid[rowIndex, colIndex] }

/**
 * Returns a new [RowArrayGrid] that contains the same elements as the given list of [rows].
 *
 * @throws IllegalArgumentException If any row is empty, or if not all [rows] have the same size.
 */
fun <T> RowArrayGrid(rows: List<List<T>>): RowArrayGrid<T> {
    if (rows.isEmpty()) {
        // Use default initial capacity
        return RowArrayGrid()
    }

    checkRows(rows)
    return RowArrayGrid(height = rows.size, width = rows[0].size) { rowIndex, colIndex ->
        rows[rowIndex][colIndex]
    }
}

/**
 * Returns a new [RowArrayGrid] that contains the same elements as the given [rows].
 *
 * @throws IllegalArgumentException If any row is empty, or if not all [rows] have the same size.
 */
fun <T> rowArrayGridOf(vararg rows: List<T>): RowArrayGrid<T> {
    if (rows.isEmpty()) {
        // Use default initial capacity
        return RowArrayGrid()
    }

    checkRows(rows.asIterable())
    return RowArrayGrid(height = rows.size, width = rows[0].size) { rowIndex, colIndex ->
        rows[rowIndex][colIndex]
    }
}

/**
 * Checks if the given [rows] can be used to construct a [RowArrayGrid].
 *
 * @throws IllegalArgumentException If any row is empty, or if not all [rows] have the same size.
 */
private fun <T> checkRows(rows: Iterable<List<T>>) {
    val rowIterator = rows.iterator()
    if (rowIterator.hasNext()) {
        // Check that the first row is non-empty
        val firstRowSize = rowIterator.next().size
        require(firstRowSize != 0) { "Row at index 0 is empty." }

        // Check that subsequent rows have the same size
        var index = 0
        while (rowIterator.hasNext()) {
            index++
            val currentRowSize = rowIterator.next().size
            require(currentRowSize == firstRowSize) {
                "Row at index $index has the wrong size: $currentRowSize != $firstRowSize"
            }
        }
    }
}
