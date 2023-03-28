package com.curtislb.adventofcode.common.geometry

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2

/**
 * A point with integer x- and y-coordinates on a 2D grid.
 *
 * @property x The x-coordinate of the point.
 * @property y The y-coordinate of the point.
 *
 * @constructor Creates a new instance of [Point] with the given [x]- and [y]-coordinates.
 */
data class Point(val x: Int, val y: Int) {
    /**
     * The matrix row index corresponding to the point.
     */
    val matrixRow: Int
        get() = -y

    /**
     * The matrix column index corresponding to the point.
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
     * Returns a copy of the point with all coordinate values negated.
     */
    operator fun unaryMinus(): Point = if (this == ORIGIN) ORIGIN else Point(-x, -y)

    /**
     * Returns the Manhattan distance between this point and [other].
     *
     * The Manhattan distance is the length (in grid units) of the shortest possible path between
     * this point and [other], while moving only up, down, left, or right.
     */
    infix fun manhattanDistance(other: Point): Int = abs(x - other.x) + abs(y - other.y)

    /**
     * Returns the squared Euclidean distance between this point and [other].
     */
    infix fun squaredDistance(other: Point): Long {
        val xDiff = (x - other.x).toLong()
        val yDiff = (y - other.y).toLong()
        return (xDiff * xDiff) + (yDiff * yDiff)
    }

    /**
     * Returns all points on the grid that are horizontally, vertically, or diagonally adjacent to
     * this point.
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
     * Returns all points on the grid that are horizontally or vertically adjacent to this point.
     */
    fun cardinalNeighbors(): List<Point> =
        listOf(copy(x = x - 1), copy(x = x + 1), copy(y = y - 1), copy(y = y + 1))

    /**
     * Returns all points on the grid that are diagonally adjacent to this point.
     */
    fun diagonalNeighbors(): List<Point> =
        listOf(Point(x - 1, y - 1), Point(x - 1, y + 1), Point(x + 1, y - 1), Point(x + 1, y + 1))

    /**
     * Returns the point given by moving [distance] grid spaces in the given [direction] from this
     * point.
     *
     * If [distance] is negative, this function instead returns the point given by moving
     * -[distance] grid spaces opposite the given [direction] from this point.
     *
     * Note that [distance] is *not* the same as the Euclidean distance, as diagonally adjacent
     * points are considered to have a [distance] of 1.
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
     * Returns the point given by rotating this point 90 degrees clockwise about a [center] point.
     */
    fun rotateClockwise(center: Point = ORIGIN): Point =
        Point(y - center.y + center.x, center.x - x + center.y)

    /**
     * Returns the point given by rotating this point 90 degrees counterclockwise about a [center]
     * point.
     */
    fun rotateCounterclockwise(center: Point = ORIGIN): Point =
        Point(center.y - y + center.x, x - center.x + center.y)

    /**
     * Returns the point given by rotating this point 180 degrees about a [center] point.
     */
    fun rotate180Degrees(center: Point = ORIGIN): Point =
        Point(center.x - x + center.x, center.y - y + center.y)

    /**
     * Returns the angle (in radians) of the line segment formed by this point and [other], measured
     * clockwise from the positive y-axis with this point as the origin.
     *
     * @throws IllegalArgumentException If this point and [other] are the same point.
     */
    fun clockwiseAngleFromYTo(other: Point): Double {
        require(this != other) { "This point and other must be distinct: $this == $other" }

        // Get translated coordinates of other with this as the origin
        val translatedX = other.x - x
        val translatedY = other.y - y

        // Calculate (possibly negative) angle from the positive y-axis
        val theta = atan2(translatedX.toDouble(), translatedY.toDouble())

        // If angle is negative, return the equivalent positive angle
        return if (theta >= 0) theta else 2.0 * PI + theta
    }

    override fun toString() = "($x, $y)"

    companion object {
        /**
         * A point representing the 2D origin `(0, 0)`.
         */
        val ORIGIN: Point = Point(0, 0)

        /**
         * A regex used to match a 2D point string.
         */
        private val POINT_REGEX: Regex = Regex("""\(?(-?\d+),\s?(-?\d+)\)?""")

        /**
         * Returns the [Point] corresponding to the given ([rowIndex], [colIndex]) matrix
         * coordinates.
         */
        fun fromMatrixCoordinates(rowIndex: Int, colIndex: Int): Point =
            if (rowIndex == 0 && colIndex == 0) ORIGIN else Point(colIndex, -rowIndex)

        /**
         * Returns a [Point] with x- and y-coordinates read from the given [string].
         *
         * The [string] must have one of the following formats:
         *
         * - `"$x,$y"`
         * - `"$x, $y"`
         * - `"($x,$y)"`
         * - `"($x, $y)"`
         *
         * If [invertY] is `true`, the y-coordinate of the resulting [Point] will be negated.
         *
         * @throws IllegalArgumentException If [string] is formatted incorrectly.
         */
        fun fromString(string: String, invertY: Boolean = false): Point {
            val matchResult = POINT_REGEX.matchEntire(string)
            require(matchResult != null) { "Malformed point string: $string" }

            val (xString, yString) = matchResult.destructured
            val x = xString.toInt()
            val y = if (invertY) -yString.toInt() else yString.toInt()

            return if (x == 0 && y == 0) ORIGIN else Point(x, y)
        }
    }
}
