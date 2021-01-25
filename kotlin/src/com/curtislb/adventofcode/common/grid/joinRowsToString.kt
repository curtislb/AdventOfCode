package com.curtislb.adventofcode.common.grid

/**
 * Returns a string by transforming each row in this grid with the [transform] function and combining them with the
 * given [separator].
 */
internal fun <T> Grid<T>.joinRowsToString(separator: String = ", ", transform: (row: List<T>) -> String): String {
    return volatileRows().joinToString(separator = separator, transform = transform)
}
