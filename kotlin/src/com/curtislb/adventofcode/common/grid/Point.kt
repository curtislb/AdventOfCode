package com.curtislb.adventofcode.common.grid

import com.curtislb.adventofcode.common.math.pow
import java.lang.IllegalArgumentException
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2

/**
 * A point with integer [x] and [y] coordinates on a 2D grid.
 */
data class Point(val x: Int, val y: Int) {
    /**
     * All points on the grid that are horizontally or vertically adjacent to this one.
     */
    val neighbors: List<Point> by lazy { listOf(Point(x - 1, y), Point(x, y - 1), Point(x + 1, y), Point(x, y + 1)) }

    /**
     * Returns the point given by moving [distance] grid units in [direction] from this one.
     *
     * If [distance] is negative, this is equivalent to moving `-distance` units opposite from [direction].
     */
    fun move(direction: Direction, distance: Int = 1): Point = when (direction) {
        Direction.UP -> Point(x, y + distance)
        Direction.RIGHT -> Point(x + distance, y)
        Direction.DOWN -> Point(x, y - distance)
        Direction.LEFT -> Point(x - distance, y)
    }

    /**
     * Returns the Manhattan distance between this point and [other].
     *
     * This is the length (in grid units) of the shortest possible path between this point and [other], while moving
     * only up, down, left, or right.
     */
    fun manhattanDistance(other: Point) = abs(x - other.x) + abs(y - other.y)

    /**
     * Returns the squared distance between this point and [other].
     */
    fun squaredDistance(other: Point): Int = (x - other.x).pow(2) + (y - other.y).pow(2)

    /**
     * Returns the angle (in radians) of the line segment formed by this point and [other], measured clockwise from the
     * positive y-axis, with this point as the origin.
     *
     * @throws IllegalArgumentException If this and [other] are the same point.
     */
    fun angleClockwiseFromPositiveY(other: Point): Double {
        if (this == other) {
            throw IllegalArgumentException("This point and other must be distinct.")
        }
        val theta = atan2((other.x - x).toDouble(), (other.y - y).toDouble())
        return if (theta >= 0) theta else 2.0 * PI + theta
    }

    /**
     * Returns the pair of `(row, column)` matrix coordinates corresponding to this point.
     */
    fun toMatrixCoordinates(): Pair<Int, Int> = Pair(-y, x)

    override fun toString() = "($x, $y)"

    companion object {
        /**
         * A point representing the 2D origin (0, 0).
         */
        val ORIGIN: Point = Point(0, 0)

        /**
         * Returns the point corresponding to the given ([rowIndex], [colIndex]) matrix coordinates.
         */
        fun fromMatrixCoordinates(rowIndex: Int, colIndex: Int): Point = Point(colIndex, -rowIndex)
    }
}
