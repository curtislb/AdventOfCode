package com.curtislb.adventofcode.common.geometry

import com.curtislb.adventofcode.common.number.Fraction

/**
 * A unique representation of a ray with a given origin and direction in a 2D grid.
 *
 * @param source The origin point of the ray.
 * @param slope The slope of the ray, or `null` if its slope is infinite.
 * @param directionParity A flag indicating the direction of the ray. If [slope] is non-null, `true`
 *  indicates that the x values of points on the ray approach positive infinity. If [slope] is
 *  `null`, `true` indicates that the y values of points on the ray approach positive infinity.
 */
data class Ray(val source: Point, val slope: Fraction?, val directionParity: Boolean) {
    /**
     * A unique representation of a ray with a given origin and direction in a 2D grid.
     *
     * @param source The origin point of the ray.
     * @param member A second point on the ray. Must be distinct from [source].
     *
     * @throws IllegalArgumentException If [source] and [member] are the same point.
     */
    constructor(source: Point, member: Point) : this(
        source = source,
        slope = when {
            member == source -> {
                throw IllegalArgumentException("Source and member points must be distinct.")
            }
            member.x == source.x -> null
            else -> Fraction(member.y - source.y, member.x - source.x)
        },
        directionParity = if (source.x != member.x) member.x > source.x else member.y > source.y
    )

    /**
     * Returns an infinite sequence of all grid points that fall on this ray, sorted by their
     * distances from (and including) the [source] point.
     */
    fun points(): Sequence<Point> = sequence {
        // Calculate the change in x and y values between subsequent points.
        val directionSign = if (directionParity) 1 else -1
        val deltaX = directionSign * (slope?.denominator?.toInt() ?: 0)
        val deltaY = directionSign * (slope?.numerator?.toInt() ?: 1)

        // Yield each point in order, starting with source.
        var x = source.x
        var y = source.y
        while (true) {
            yield(Point(x, y))
            x += deltaX
            y += deltaY
        }
    }
}
