package com.curtislb.adventofcode.common.grid

/**
 * A read-only grid that contains no values.
 */
internal object EmptyGrid : Grid<Nothing> {
    override val height: Int = 0

    override val width: Int = 0

    override fun get(rowIndex: Int, colIndex: Int): Nothing {
        throw IndexOutOfBoundsException("Empty grid doesn't contain an element at row $rowIndex, column $colIndex.")
    }

    override fun row(rowIndex: Int): List<Nothing> {
        throw IndexOutOfBoundsException("Empty grid doesn't contain a row at index $rowIndex.")
    }

    override fun column(colIndex: Int): List<Nothing> {
        throw IndexOutOfBoundsException("Empty grid doesn't contain a column at index $colIndex.")
    }

    override fun rows(): List<List<Nothing>> = emptyList()

    override fun columns(): List<List<Nothing>> = emptyList()
}

/**
 * Returns an empty read-only grid.
 */
fun <T> emptyGrid(): Grid<T> = EmptyGrid
