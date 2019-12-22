package com.adventofcode.curtislb.common.grid

import com.adventofcode.curtislb.common.math.Fraction

/**
 * TODO
 */
data class Ray(val source: Point, val slope: Fraction?, val directionParity: Boolean) {
    /**
     * TODO
     */
    constructor(source: Point, member: Point) : this(
        source,
        when {
            member == source -> throw IllegalArgumentException("Source and member points must be distinct")
            member.x == source.x -> null
            else -> Fraction(member.y - source.y, member.x - source.x)
        },
        if (source.x != member.x) member.x > source.x else member.y > source.y
    )
}
