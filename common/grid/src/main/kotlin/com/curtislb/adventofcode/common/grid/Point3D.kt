package com.curtislb.adventofcode.common.grid

import kotlin.math.abs

/**
 * A point with integer [x], [y], and [z] coordinates on a 3D grid.
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
     * Returns a copy of this point with all coordinates negated.
     */
    operator fun unaryMinus(): Point3D = if (this == ORIGIN) ORIGIN else Point3D(-x, -y, -z)

    /**
     * Returns the Manhattan distance between this point and [other].
     *
     * The Manhattan distance is the length (in grid units) of the shortest possible path between
     * this point and [other], while moving only along grid lines.
     */
    fun manhattanDistance(other: Point3D): Int =
        abs(x - other.x) + abs(y - other.y) + abs(z - other.z)

    override fun toString() = "($x, $y, $z)"

    companion object {
        /**
         * A point representing the 3D origin `(0, 0, 0)`.
         */
        val ORIGIN = Point3D(0, 0, 0)

        /**
         * A regex used to match a 3D point string.
         */
        private val POINT_REGEX = Regex("""\s*\(?\s*(-?\d+)\s*,\s*(-?\d+),\s*(-?\d+)\s*\)?\s*""")

        /**
         * Returns a [Point3D] from a [string] of the form `"$x,$y,$z"` or `"($x,$y,$z)"`.
         *
         * @throws IllegalArgumentException If [string] is not formatted correctly.
         */
        fun fromString(string: String): Point3D {
            val matchResult = POINT_REGEX.matchEntire(string)
            require(matchResult != null) { "Malformed point string: $string" }

            val (xString, yString, zString) = matchResult.destructured
            return Point3D(xString.toInt(), yString.toInt(), zString.toInt())
        }
    }
}
