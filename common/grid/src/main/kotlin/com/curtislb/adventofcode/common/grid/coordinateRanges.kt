package com.curtislb.adventofcode.common.grid

/**
 * Returns the x- and y-coordinate ranges, respectively, of this iterable collection of points.
 */
fun Iterable<Point>.coordinateRanges(): Pair<ClosedRange<Int>, ClosedRange<Int>> {
    var minX = Int.MAX_VALUE
    var maxX = Int.MIN_VALUE
    var minY = Int.MAX_VALUE
    var maxY = Int.MIN_VALUE

    forEach { point ->
        minX = minOf(minX, point.x)
        maxX = maxOf(maxX, point.x)
        minY = minOf(minY, point.y)
        maxY = maxOf(maxY, point.y)
    }

    return Pair(minX..maxX, minY..maxY)
}
