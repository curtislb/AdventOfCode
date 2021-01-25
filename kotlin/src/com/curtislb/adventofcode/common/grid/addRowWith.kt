package com.curtislb.adventofcode.common.grid

/**
 * Adds a new row to this grid, constructed by applying the [prepare] function to an empty list.
 */
inline fun <T> MutableGrid<T>.addRowWith(prepare: MutableList<T>.() -> Unit) {
    addShallowRow(ArrayList<T>().apply(prepare))
}
