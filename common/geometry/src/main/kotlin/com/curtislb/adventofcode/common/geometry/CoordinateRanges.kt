package com.curtislb.adventofcode.common.geometry

/**
 * A pair of integer ranges corresponding to the x- and y-coordinates of a rectangular region in a
 * 2D grid.
 *
 * @property x The range of x-coordinate values for the region.
 * @property y The range of y-coordinate values for the region.
 */
data class CoordinateRanges(val x: IntRange, val y: IntRange) {
    /**
     * Returns `true` if at least one of the coordinate ranges is empty.
     */
    fun isEmpty(): Boolean = x.isEmpty() || y.isEmpty()

    companion object {
        /**
         * Returns the smallest x- and y-coordinate ranges that contain the given [points].
         */
        fun ofPoints(points: Iterable<Point>): CoordinateRanges {
            var minX = Int.MAX_VALUE
            var maxX = Int.MIN_VALUE
            var minY = Int.MAX_VALUE
            var maxY = Int.MIN_VALUE

            // Iterate over points, keeping track of min/max coordinates
            for (point in points) {
                minX = minOf(minX, point.x)
                maxX = maxOf(maxX, point.x)
                minY = minOf(minY, point.y)
                maxY = maxOf(maxY, point.y)
            }

            return CoordinateRanges(minX..maxX, minY..maxY)
        }
    }
}
