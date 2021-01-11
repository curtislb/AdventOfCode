package com.curtislb.adventofcode.common.grid

/**
 * TODO
 */
fun <T> List<List<T>>.rotatedLeft(): List<List<T>> {
    if (isEmpty() || first().isEmpty()) {
        return emptyList()
    }

    val rowCount = size
    val colCount = first().size
    val tempValue = first().first()
    val transformedGrid = List(colCount) { MutableList(rowCount) { tempValue } }
    forEachIndexed { rowIndex, row ->
        row.forEachIndexed { colIndex, value ->
            transformedGrid[colCount - 1 - colIndex][rowIndex] = value
        }
    }
    return transformedGrid
}
