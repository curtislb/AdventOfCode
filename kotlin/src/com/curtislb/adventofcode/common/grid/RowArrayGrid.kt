package com.curtislb.adventofcode.common.grid

/**
 * A mutable rectangular grid of values, implemented as an [ArrayList] of [ArrayList] rows.
 *
 * @param rowList A list of the rows that make up this grid.
 */
class RowArrayGrid<T>(private val rowList: ArrayList<ArrayList<T>> = ArrayList()) : MutableGrid<T> {
    override val height: Int get() = rowList.size

    override val width: Int get() = rowList.firstOrNull()?.size ?: 0

    override fun get(rowIndex: Int, colIndex: Int): T = rowList[rowIndex][colIndex]

    override fun row(rowIndex: Int): List<T> = rowList[rowIndex].toList()

    override fun column(colIndex: Int): List<T> = rowList.map { it[colIndex] }

    override fun volatileRow(rowIndex: Int): List<T> = rowList[rowIndex]

    override fun volatileRows(): List<List<T>> = rowList

    override fun set(rowIndex: Int, colIndex: Int, value: T) {
        rowList[rowIndex][colIndex] = value
    }

    override fun addRow(row: List<T>) {
        require(isEmpty() || row.size == width) { "Row size (${row.size}) must match grid width ($width)." }
        rowList.add(ArrayList(row))
    }

    override fun addColumn(column: List<T>) {
        require(isEmpty() || column.size == height) { "Column size (${column.size}) must match grid height ($height)." }
        val isFirstColumn = isEmpty()
        column.forEachIndexed { index, value ->
            if (isFirstColumn) {
                rowList.add(arrayListOf(value))
            } else {
                rowList[index].add(value)
            }
        }
    }

    override fun addShallowRow(row: List<T>) {
        require(isEmpty() || row.size == width) { "Row size (${row.size}) must match grid width ($width)." }
        rowList.add(if (row is ArrayList<T>) row else ArrayList(row))
    }

    override fun removeLastRow() {
        if (isEmpty()) {
            throw NoSuchElementException("Can't remove a row from an empty grid.")
        }
        rowList.removeLast()
    }

    override fun removeLastColumn() {
        if (isEmpty()) {
            throw NoSuchElementException("Can't remove a column from an empty grid.")
        }
        rowList.forEach { it.removeLast() }
    }

    override fun equals(other: Any?): Boolean = other is RowArrayGrid<*> && rowList == other.rowList

    override fun hashCode(): Int = rowList.hashCode()

    override fun toString(): String = if (isEmpty()) "[]" else """
        [
         ${rowList.joinToString(separator = ",\n         ")}
        ]
    """.trimIndent()
}

/**
 * Returns a new array grid with the given [height] and [width], with each element set by the given [init] function.
 */
@Suppress("FunctionName")
inline fun <T> RowArrayGrid(height: Int, width: Int, init: (rowIndex: Int, colIndex: Int) -> T): RowArrayGrid<T> {
    require(height >= 0) { "Height must be non-negative: $height" }
    require(width >= 0) { "Width must be non-negative: $width" }
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
fun <T> RowArrayGrid(grid: Grid<T>) : RowArrayGrid<T> {
    return RowArrayGrid(grid.height, grid.width) { rowIndex, colIndex -> grid[rowIndex, colIndex] }
}

/**
 * Returns a new [RowArrayGrid], constructed from the given [rows].
 */
@Suppress("FunctionName")
fun <T> RowArrayGrid(rows: List<List<T>>) : RowArrayGrid<T> {
    require(rows.all { it.size == rows.first().size }) { "All rows must have the same size." }
    return RowArrayGrid(height = rows.size, width = rows.firstOrNull()?.size ?: 0) { rowIndex, colIndex ->
        rows[rowIndex][colIndex]
    }
}

/**
 * Returns a new [RowArrayGrid], constructed from the given [rows].
 */
@Suppress("FunctionName")
fun <T> RowArrayGrid(vararg rows: List<T>) : RowArrayGrid<T> {
    require(rows.all { it.size == rows.first().size }) { "All rows must have the same size." }
    return RowArrayGrid(height = rows.size, width = rows.firstOrNull()?.size ?: 0) { rowIndex, colIndex ->
        rows[rowIndex][colIndex]
    }
}
