package com.curtislb.adventofcode.common.grid

import lombok.Generated

/**
 * Returns the sum of the values given by applying the [transform] function to each row in this grid.
 */
@Generated
inline fun <T> Grid<T>.sumRowsBy(transform: (row: List<T>) -> Int): Int = volatileRows().sumOf(transform)
