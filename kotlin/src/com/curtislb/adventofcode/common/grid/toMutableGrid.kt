package com.curtislb.adventofcode.common.grid

/**
 * Returns a new mutable copy of this read-only grid.
 */
fun <T> Grid<T>.toMutableGrid(): MutableGrid<T> = RowArrayGrid(this)

/**
 * Returns a new mutable grid constructed from this list of rows.
 */
fun <T> List<List<T>>.toMutableGrid(): MutableGrid<T> = RowArrayGrid(this)
