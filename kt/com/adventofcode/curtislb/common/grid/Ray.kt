package com.adventofcode.curtislb.common.grid

import com.adventofcode.curtislb.common.math.Fraction

/**
 * A unique representation of a ray with a given source [Point] on a 2D grid.
 * @param source The [Point] of origin of the ray.
 * @param slope A [Fraction] representing the slope of the ray, or `null` if this slope is infinite.
 * @param directionParity A [Boolean] flag indicating the direction of the ray. If [slope] is non-null, `true` indicates
 *  that the x-values of points on the ray approach positive infinity, as opposed to negative infinity (for which
 *  [directionParity] is `false`). If [slope] is `null`, `true` indicates that the y-values of points on the ray
 *  approach positive infinity, as opposed to negative infinity (for which [directionParity] is `false`).
 */
data class Ray(val source: Point, val slope: Fraction?, val directionParity: Boolean) {
    /**
     * Uniquely represents a ray with a given source [Point] on a 2D grid.
     * @param source The [Point] of origin of the ray.
     * @param member A second [Point] that is a member of the ray. Must be distinct from [source].
     * @throws IllegalArgumentException If [source] and [member] represent the same [Point].
     */
    constructor(source: Point, member: Point) : this(
        source,
        when {
            member == source -> throw IllegalArgumentException("Source and member points must be distinct.")
            member.x == source.x -> null
            else -> Fraction(member.y - source.y, member.x - source.x)
        },
        if (source.x != member.x) member.x > source.x else member.y > source.y
    )
}
