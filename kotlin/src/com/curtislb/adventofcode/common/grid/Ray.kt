package com.curtislb.adventofcode.common.grid

import com.curtislb.adventofcode.common.math.Fraction

/**
 * A unique representation of a ray with a given origin and direction in a 2D grid.
 *
 * @param source The origin point of the ray.
 * @param slope The slope of the ray, or `null` if its slope is infinite.
 * @param directionParity A flag indicating the direction of the ray. If [slope] is non-null, `true` indicates
 *  that the x-values of points on the ray approach positive infinity, whereas `false` indicates that they approach
 *  negative infinity. If [slope] is `null`, `true` indicates that the y-values of points on the ray approach positive
 *  infinity, whereas `false` indicates that they approach negative infinity.
 */
data class Ray(val source: Point, val slope: Fraction?, val directionParity: Boolean) {
    /**
     * A unique representation of a ray with a given origin and direction in a 2D grid.
     *
     * @param source The origin point of the ray.
     * @param member A second point that is a member of the ray. Must be distinct from [source].
     *
     * @throws IllegalArgumentException If [source] and [member] are the same point.
     */
    constructor(source: Point, member: Point) : this(
        source,
        when {
            member == source -> throw IllegalArgumentException("Points source and member must be distinct.")
            member.x == source.x -> null
            else -> Fraction(member.y - source.y, member.x - source.x)
        },
        if (source.x != member.x) member.x > source.x else member.y > source.y
    )
}
