package com.curtislb.adventofcode.common.grid

/**
 * Creates and appends a new row to the grid by calling the specified function [block] with an empty
 * list as its receiver.
 *
 * @throws IllegalArgumentException If the new row is empty or doesn't match the size of rows in the
 *  grid.
 */
inline fun <T> MutableGrid<T>.addRow(block: MutableList<T>.() -> Unit) {
    addShallowRow(ArrayList<T>().apply(block))
}

/**
 * Replaces each element in the grid with the result of the [update] function applied to that value.
 */
inline fun <T> MutableGrid<T>.updateAll(update: (value: T) -> T) =
    forEachIndexed { rowIndex, colIndex, value -> this[rowIndex, colIndex] = update(value) }
