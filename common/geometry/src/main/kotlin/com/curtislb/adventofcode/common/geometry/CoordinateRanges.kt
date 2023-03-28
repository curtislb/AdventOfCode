package com.curtislb.adventofcode.common.geometry

/**
 * A pair of integer ranges corresponding to the x- and y-coordinates of a rectangular region in a
 * 2D grid.
 *
 * @property x The range of x-coordinate values for the region.
 * @property y The range of y-coordinate values for the region.
 */
data class CoordinateRanges(val x: IntRange, val y: IntRange) {
    companion object {
        /**
         * Returns the smallest x- and y-coordinate ranges that contain all the given [points].
         */
        fun ofPoints(points: Iterable<Point>): CoordinateRanges {
            var minX = Int.MAX_VALUE
            var maxX = Int.MIN_VALUE
            var minY = Int.MAX_VALUE
            var maxY = Int.MIN_VALUE

            // Iterate over points, keeping track of min/max coordinates
            for (point in points) {
                minX = point.x.coerceAtMost(minX)
                maxX = point.x.coerceAtLeast(maxX)
                minY = point.y.coerceAtMost(minY)
                maxY = point.y.coerceAtLeast(maxY)
            }

            return CoordinateRanges(minX..maxX, minY..maxY)
        }
    }
}
