package com.curtislb.adventofcode.common.grid

/**
 * Returns the value at position ([rowIndex], [colIndex]) in this matrix, or `null` if the position is invalid for this
 * matrix.
 */
fun <T> List<List<T>>.getCellOrNull(rowIndex: Int, colIndex: Int): T? {
    return if (rowIndex in indices && colIndex in this[rowIndex].indices) this[rowIndex][colIndex] else null
}

/**
 * Returns the value at the position corresponding to [point] in this matrix, or `null` if the position is invalid for
 * this matrix.
 */
fun <T> List<List<T>>.getCellOrNull(point: Point): T? {
    val (rowIndex, colIndex) = point.toMatrixCoordinates()
    return getCellOrNull(rowIndex, colIndex)
}
