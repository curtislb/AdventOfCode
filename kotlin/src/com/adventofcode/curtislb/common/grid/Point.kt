package com.adventofcode.curtislb.common.grid

import com.adventofcode.curtislb.common.math.pow
import kotlin.math.abs

/**
 * A point on a 2D grid with positive and negative integer coordinates.
 * @param x The x-coordinate of the point.
 * @param y The y-coordinate of the point.
 */
data class Point(val x: Int, val y: Int) {
    /**
     * A [List] of all grid points that are horizontally or vertically adjacent to this [Point].
     */
    val neighbors: List<Point>
        get() = listOf(Point(x, y - 1), Point(x + 1, y), Point(x, y + 1), Point(x - 1, y))

    /**
     * Finds the [Point] that results from moving a given distance in a given direction.
     * @receiver The [Point] from which to begin moving.
     * @param direction The [Direction] in which to move.
     * @param distance The number of grid units to move.
     * @return The [Point] reached by moving [distance] units in [direction] from this [Point].
     */
    fun move(direction: Direction, distance: Int = 1): Point {
        return when (direction) {
            Direction.UP -> Point(x, y + distance)
            Direction.RIGHT -> Point(x + distance, y)
            Direction.DOWN -> Point(x, y - distance)
            Direction.LEFT -> Point(x - distance, y)
        }
    }

    /**
     * Finds the Manhattan distance between this and another [Point].
     *
     * The Manhattan distance represents the shortest possible path (in grid units) between two points, while moving
     * only up, down, left, or right.
     *
     * @param other The [Point] to which the distance will be determined.
     * @return The Manhattan distance between this [Point] and [other].
     */
    fun manhattanDistanceTo(other: Point) = abs(x - other.x) + abs(y - other.y)

    /**
     * Finds the squared distance between this and another [Point].
     * @param other The [Point] to which the squared distance will be determined.
     * @return The squared value of the Euclidean distance between this [Point] and [other].
     */
    fun squaredDistanceTo(other: Point): Int = (x - other.x).pow(2) + (y - other.y).pow(2)

    /**
     * Converts this [Point] to corresponding row and column indices in a 2D matrix.
     * @return A [Pair] `(rowIndex, columnIndex)`, representing the row and column matrix indices for this [Point].
     */
    fun toMatrixCoordinates(): Pair<Int, Int> = Pair(-y, x)

    override fun toString() = "($x, $y)"

    companion object {
        /**
         * A [Point] representing the 2D origin (0, 0).
         */
        val ORIGIN: Point = Point(0, 0)

        /**
         * Creates a [Point] from a given pair of matrix coordinates.
         * @param rowIndex The row index of a position in matrix coordinates.
         * @param colIndex The column index of a position in matrix coordinates.
         * @return The [Point] corresponding to the matrix indices [rowIndex] and [colIndex].
         */
        fun fromMatrixCoordinates(rowIndex: Int, colIndex: Int): Point = Point(colIndex, -rowIndex)
    }
}
