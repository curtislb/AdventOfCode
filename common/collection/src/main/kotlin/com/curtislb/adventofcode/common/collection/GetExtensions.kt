package com.curtislb.adventofcode.common.collection

/**
 * Returns the element at the given [index], modulo the size of this list.
 *
 * @throws NoSuchElementException If the list is empty.
 */
fun <T> List<T>.getCyclic(index: Int): T {
    if (isEmpty()) {
        throw NoSuchElementException("Can't get an element from an empty list")
    }
    return this[index.mod(size)]
}

/**
 * Returns the value at the given [rowIndex] and [colIndex] in this 2D list, or `null` if there is
 * no such value.
 */
fun <T> List<List<T>>.getOrNull(rowIndex: Int, colIndex: Int): T? =
    getOrNull(rowIndex)?.getOrNull(colIndex)
