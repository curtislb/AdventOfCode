package com.curtislb.adventofcode.common.geometry

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2

/**
 * A point with integer [x] and [y] coordinates on a 2D grid.
 */
data class Point(val x: Int, val y: Int) {
    /**
     * The matrix row index corresponding to this point.
     */
    val matrixRow: Int
        get() = -y

    /**
     * The matrix column index corresponding to this point.
     */
    val matrixCol: Int
        get() = x

    /**
     * Returns the point given by adding each coordinate of [other] to this point.
     */
    operator fun plus(other: Point): Point = when {
        this == ORIGIN -> other
        other == ORIGIN -> this
        else -> Point(x + other.x, y + other.y)
    }

    /**
     * Returns the point given by subtracting each coordinate of [other] from this point.
     */
    operator fun minus(other: Point): Point =
        if (other == ORIGIN) this else Point(x - other.x, y - other.y)

    /**
     * Returns all points on the grid that are horizontally, vertically, or diagonally adjacent to
     * this one.
     */
    fun allNeighbors(): List<Point> =
        listOf(
            copy(x = x - 1),
            copy(x = x + 1),
            copy(y = y - 1),
            copy(y = y + 1),
            Point(x - 1, y - 1),
            Point(x - 1, y + 1),
            Point(x + 1, y - 1),
            Point(x + 1, y + 1)
        )

    /**
     * Returns all points on the grid that are horizontally or vertically adjacent to this one.
     */
    fun cardinalNeighbors(): List<Point> =
        listOf(copy(x = x - 1), copy(x = x + 1), copy(y = y - 1), copy(y = y + 1))

    /**
     * Returns all points on the grid that are diagonally adjacent to this one.
     */
    fun diagonalNeighbors(): List<Point> =
        listOf(Point(x - 1, y - 1), Point(x - 1, y + 1), Point(x + 1, y - 1), Point(x + 1, y + 1))

    /**
     * Returns the point that is [distance] grid units in a given [direction] from this one.
     *
     * If [distance] is negative, this is equivalent to `abs(distance)` units opposite [direction].
     */
    fun move(direction: Direction, distance: Int = 1): Point =
        if (distance == 0) {
            this
        } else {
            when (direction) {
                Direction.UP -> copy(y = y + distance)
                Direction.RIGHT -> copy(x = x + distance)
                Direction.DOWN -> copy(y = y - distance)
                Direction.LEFT -> copy(x = x - distance)
                Direction.UP_RIGHT -> Point(x + distance, y + distance)
                Direction.DOWN_RIGHT -> Point(x + distance, y - distance)
                Direction.DOWN_LEFT -> Point(x - distance, y - distance)
                Direction.UP_LEFT -> Point(x - distance, y + distance)
            }
        }

    /**
     * Returns the point produced by rotating this one 90 degrees clockwise about a [center] point.
     */
    fun rotateClockwise(center: Point = ORIGIN): Point =
        Point(y - center.y + center.x, center.x - x + center.y)

    /**
     * Returns the point produced by rotating this one 90 degrees counterclockwise about a [center]
     * point.
     */
    fun rotateCounterclockwise(center: Point = ORIGIN): Point =
        Point(center.y - y + center.x, x - center.x + center.y)

    /**
     * Returns the point produced by rotating this one 180 degrees about a [center] point.
     */
    fun rotate180Degrees(center: Point = ORIGIN): Point =
        Point(center.x - x + center.x, center.y - y + center.y)

    /**
     * Returns the Manhattan distance between this point and [other].
     *
     * The Manhattan distance is the length (in grid units) of the shortest possible path between
     * this point and [other], while moving only up, down, left, or right.
     */
    fun manhattanDistance(other: Point): Int = abs(x - other.x) + abs(y - other.y)

    /**
     * Returns the squared distance between this point and [other].
     */
    fun squaredDistance(other: Point): Long {
        val xDiff = (x - other.x).toLong()
        val yDiff = (y - other.y).toLong()
        return (xDiff * xDiff) + (yDiff * yDiff)
    }

    /**
     * Returns the angle (in radians) of the line segment formed by this point and [other], measured
     * clockwise from the positive y-axis, with this point as the origin.
     *
     * @throws IllegalArgumentException If this point and [other] are the same point.
     */
    fun angleClockwiseFromPositiveY(other: Point): Double {
        require(this != other) { "This point and other must be distinct." }
        val theta = atan2((other.x - x).toDouble(), (other.y - y).toDouble())
        return if (theta >= 0) theta else 2.0 * PI + theta
    }

    override fun toString() = "($x, $y)"

    companion object {
        /**
         * A point representing the 2D origin `(0, 0)`.
         */
        val ORIGIN = Point(0, 0)

        /**
         * A regex used to match a 2D point string.
         */
        private val POINT_REGEX = Regex("""\s*\(?\s*(-?\d+)\s*,\s*(-?\d+)\s*\)?\s*""")

        /**
         * Returns the [Point] corresponding to the given ([rowIndex], [colIndex]) matrix
         * coordinates.
         */
        fun fromMatrixCoordinates(rowIndex: Int, colIndex: Int): Point = Point(colIndex, -rowIndex)

        /**
         * Returns a [Point] from a [string] of the form `"$x,$y"` or `"($x,$y)"`.
         *
         * If [invertY] is `true`, the resulting [Point] will have a y-coordinate of `-y`.
         *
         * @throws IllegalArgumentException If [string] is not of the required form.
         */
        fun fromString(string: String, invertY: Boolean = false): Point {
            val matchResult = POINT_REGEX.matchEntire(string)
            require(matchResult != null) { "Malformed point string: $string" }

            val (xString, yString) = matchResult.destructured
            val x = xString.toInt()
            val y = if (invertY) -yString.toInt() else yString.toInt()
            return Point(x, y)
        }
    }
}