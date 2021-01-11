package com.curtislb.adventofcode.common.grid

/**
 * TODO
 */
fun <T> List<List<T>>.flippedHorizontal(): List<List<T>> {
    if (isEmpty() || first().isEmpty()) {
        return emptyList()
    }

    val rowCount = size
    val colCount = first().size
    val tempValue = first().first()
    val transformedGrid = List(rowCount) { MutableList(colCount) { tempValue } }
    forEachIndexed { rowIndex, row ->
        row.forEachIndexed { colIndex, value ->
            transformedGrid[rowIndex][colCount - 1 - colIndex] = value
        }
    }
    return transformedGrid
}
