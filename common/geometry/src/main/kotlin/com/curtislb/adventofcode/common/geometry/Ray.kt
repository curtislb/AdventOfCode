package com.curtislb.adventofcode.common.geometry

import com.curtislb.adventofcode.common.number.Fraction

/**
 * A unique representation of a ray with a given origin and direction in a 2D grid.
 *
 * @property source The origin point of the ray.
 * @property slope The slope of the ray, or `null` if its slope is infinite.
 * @property isPositive A boolean flag indicating the direction of the ray. If [slope] is finite,
 *  this is `true` if the x-coordinates of points on the ray approach positive infinity. If [slope]
 *  is `null`, this is `true` if the y-coordinates of points on the ray approach positive infinity.
 *
 * @constructor Creates a new instance of [Ray] with the given [source] point, [slope], and
 *  direction indicated by [isPositive].
 */
data class Ray(val source: Point, val slope: Fraction?, val isPositive: Boolean) {
    /**
     * Returns a sequence of all grid points that fall on this ray, including the [source] point,
     * sorted by their distance from [source].
     */
    fun points(): Sequence<Point> = sequence {
        // Calculate the change in x and y between subsequent points
        val sign = if (isPositive) 1 else -1
        val deltaX = sign * (slope?.denominator?.toInt() ?: 0)
        val deltaY = sign * (slope?.numerator?.toInt() ?: 1)

        // Determine last coordinate values before integer overflow
        val lastX = when {
            deltaX > 0 -> Int.MAX_VALUE - (deltaX - 1)
            deltaX < 0 -> Int.MIN_VALUE - (deltaX + 1)
            else -> 0
        }
        val lastY = when {
            deltaY > 0 -> Int.MAX_VALUE - (deltaY - 1)
            deltaY < 0 -> Int.MIN_VALUE - (deltaY + 1)
            else -> 0
        }

        // Produce each point in order, starting with source
        var x = source.x
        var y = source.y
        while (true) {
            yield(Point(x, y))

            // Stop producing points if either coordinate would overflow
            if (
                deltaX > 0 && x >= lastX ||
                deltaX < 0 && x <= lastX ||
                deltaY > 0 && y >= lastY ||
                deltaY < 0 && y <= lastY
            ) {
                break
            }

            x += deltaX
            y += deltaY
        }
    }

    companion object {
        /**
         * Returns the unique [Ray] defined by the given [source] point and distinct [member] point.
         *
         * @throws IllegalArgumentException If [source] and [member] are the same point.
         */
        fun fromPoints(source: Point, member: Point): Ray {
            require(source != member) {
                "Source and member points must be distinct: $source == $member"
            }

            val slope: Fraction?
            val isDirectionPositive: Boolean
            if (source.x == member.x) {
                // Ray is vertical (infinite slope)
                slope = null
                isDirectionPositive = source.y < member.y
            } else {
                // Ray is not vertical (finite slope)
                slope = Fraction.valueOf(member.y - source.y, member.x - source.x)
                isDirectionPositive = source.x < member.x
            }

            return Ray(source, slope, isDirectionPositive)
        }
    }
}
