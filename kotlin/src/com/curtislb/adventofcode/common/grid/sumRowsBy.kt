package com.curtislb.adventofcode.common.grid

/**
 * Returns the sum of the values given by applying the [transform] function to each row in this grid.
 */
inline fun <T> Grid<T>.sumRowsBy(transform: (row: List<T>) -> Int): Int = volatileRows().sumBy(transform)
