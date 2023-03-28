package com.curtislb.adventofcode.common.geometry

import kotlin.math.abs

/**
 * A point with integer x-, y-, and z-coordinates on a 3D grid.
 *
 * @property x The x-coordinate of the point.
 * @property y The y-coordinate of the point.
 * @property z The z-coordinate of the point.
 *
 * @constructor Creates a new instance of [Point] with the given [x]-, [y]-, and [z]-coordinates.
 */
data class Point3D(val x: Int, val y: Int, val z: Int) {
    /**
     * Returns the point given by adding each coordinate of [other] to this point.
     */
    operator fun plus(other: Point3D): Point3D = when {
        this == ORIGIN -> other
        other == ORIGIN -> this
        else -> Point3D(x + other.x, y + other.y, z + other.z)
    }

    /**
     * Returns the point given by subtracting each coordinate of [other] from this point.
     */
    operator fun minus(other: Point3D): Point3D =
        if (other == ORIGIN) this else Point3D(x - other.x, y - other.y, z - other.z)

    /**
     * Returns a copy of the point with all coordinate values negated.
     */
    operator fun unaryMinus(): Point3D = if (this == ORIGIN) ORIGIN else Point3D(-x, -y, -z)

    /**
     * Returns the Manhattan distance between this point and [other].
     *
     * The Manhattan distance is the length (in grid units) of the shortest possible path between
     * this point and [other], while moving only along grid lines.
     */
    infix fun manhattanDistance(other: Point3D): Int =
        abs(x - other.x) + abs(y - other.y) + abs(z - other.z)

    override fun toString() = "($x, $y, $z)"

    companion object {
        /**
         * A point representing the 3D origin `(0, 0, 0)`.
         */
        val ORIGIN: Point3D = Point3D(0, 0, 0)

        /**
         * A regex used to match a 3D point string.
         */
        private val POINT_REGEX: Regex = Regex("""\(?(-?\d+),\s?(-?\d+),\s?(-?\d+)\)?""")

        /**
         * Returns a [Point] with x-, and y-, z-coordinates read from the given [string].
         *
         * The [string] must have one of the following formats:
         *
         * - `"$x,$y,$z"`
         * - `"$x, $y, $z"`
         * - `"($x,$y,$z)"`
         * - `"($x, $y, $z)"`
         *
         * @throws IllegalArgumentException If [string] is not formatted correctly.
         */
        fun fromString(string: String): Point3D {
            val matchResult = POINT_REGEX.matchEntire(string)
            require(matchResult != null) { "Malformed point string: $string" }

            val (xString, yString, zString) = matchResult.destructured
            val x = xString.toInt()
            val y = yString.toInt()
            val z = zString.toInt()

            return if (x == 0 && y == 0 && z == 0) ORIGIN else Point3D(x, y, z)
        }
    }
}
