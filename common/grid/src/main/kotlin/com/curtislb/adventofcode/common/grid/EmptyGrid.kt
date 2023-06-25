package com.curtislb.adventofcode.common.grid

/**
 * A read-only grid that contains no values.
 */
private object EmptyGrid : Grid<Nothing> {
    override val height: Int = 0

    override val width: Int = 0

    override fun get(rowIndex: Int, colIndex: Int): Nothing {
        throw IndexOutOfBoundsException(
            "Empty grid has no element at row $rowIndex, column $colIndex."
        )
    }

    override fun row(rowIndex: Int): List<Nothing> {
        throw IndexOutOfBoundsException("Empty grid has no row at index $rowIndex.")
    }

    override fun column(colIndex: Int): List<Nothing> {
        throw IndexOutOfBoundsException("Empty grid has no column at index $colIndex.")
    }

    override fun rows(): List<List<Nothing>> = emptyList()

    override fun columns(): List<List<Nothing>> = emptyList()

    override fun equals(other: Any?): Boolean = other is Grid<*> && other.isEmpty()

    override fun hashCode(): Int = 1

    override fun toString(): String = "[]"
}

/**
 * Returns an empty read-only grid.
 */
fun <E> emptyGrid(): Grid<E> = EmptyGrid
