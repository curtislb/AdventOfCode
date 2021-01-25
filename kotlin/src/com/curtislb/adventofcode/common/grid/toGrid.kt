package com.curtislb.adventofcode.common.grid

/**
 * Returns a read-only copy of this mutable grid.
 */
fun <T> MutableGrid<T>.toGrid(): Grid<T> = RowArrayGrid(this)

/**
 * Returns a read-only grid constructed from this list of rows.
 */
fun <T> List<List<T>>.toGrid(): Grid<T> = RowArrayGrid(this)
