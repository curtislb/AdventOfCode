package com.curtislb.adventofcode.common.geometry

import kotlin.math.max
import kotlin.math.min

/**
 * Returns the x and y coordinate ranges, respectively, of this iterable collection of points.
 */
fun Iterable<Point>.coordinateRanges(): Pair<IntRange, IntRange> {
    var minX = Int.MAX_VALUE
    var maxX = Int.MIN_VALUE
    var minY = Int.MAX_VALUE
    var maxY = Int.MIN_VALUE

    for (point in this) {
        minX = min(minX, point.x)
        maxX = max(maxX, point.x)
        minY = min(minY, point.y)
        maxY = max(maxY, point.y)
    }

    return Pair(minX..maxX, minY..maxY)
}
