package com.curtislb.adventofcode.common.geometry

import com.curtislb.adventofcode.common.collection.getCyclic
import kotlin.math.abs

/**
 * Returns the area of the polygon with specified [vertices] (in consecutive order).
 */
fun polygonArea(vertices: List<Point>): Long {
    // Calculate area using the shoelace formula
    val shoelaceSum = vertices.withIndex().sumOf { (index, vertex) ->
        val xDiff = vertices.getCyclic(index - 1).x - vertices.getCyclic(index + 1).x
        vertex.y.toLong() * xDiff.toLong()
    }
    return abs(shoelaceSum / 2L)
}
