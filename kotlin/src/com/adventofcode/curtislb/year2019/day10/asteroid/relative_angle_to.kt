package com.adventofcode.curtislb.year2019.day10.asteroid

import com.adventofcode.curtislb.common.grid.Point
import kotlin.math.PI
import kotlin.math.atan2

/**
 * Finds the relative angle of the segment formed by this and another [Point].
 * @param other A [Point] that forms a segment with this one.
 * @return The angle in radians (from 0 to `2 * PI`, exclusive) of the segment formed by this [Point] and [other],
 *  measured clockwise from the negative y-axis.
 */
fun Point.relativeAngleTo(other: Point) = PI - atan2((other.x - x).toDouble(), (other.y - y).toDouble())
