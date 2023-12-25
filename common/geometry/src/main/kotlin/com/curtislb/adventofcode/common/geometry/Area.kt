package com.curtislb.adventofcode.common.geometry

import com.curtislb.adventofcode.common.collection.getCyclic
import kotlin.math.abs

/**
 * Returns the area of the polygon with consecutive vertices located at the given [points].
 */
fun polygonArea(points: List<Point>): Long {
    // Calculate area using the shoelace formula
    val shoelaceSum = points.withIndex().sumOf { (index, point) ->
        val xDiff = points.getCyclic(index - 1).x - points.getCyclic(index + 1).x
        point.y.toLong() * xDiff.toLong()
    }
    return abs(shoelaceSum / 2L)
}
