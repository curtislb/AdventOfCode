package com.curtislb.adventofcode.common.geometry

import com.curtislb.adventofcode.common.collection.mapToSet
import com.curtislb.adventofcode.common.range.size

/**
 * A collection of points that can be used to select values.
 *
 * @param points All points included in the mask.
 */
data class PointMask(val points: Set<Point>) {
    /**
     * Width of the minimum grid that contains all points in this mask.
     */
    val width: Int

    /**
     * Height of the minimum grid that contains all points in this mask.
     */
    val height: Int

    init {
        val ranges = CoordinateRanges.ofPoints(points)
        width = ranges.x.size()
        height = ranges.y.size()
    }

    /**
     * Checks if the given [point] is included in this point mask.
     */
    operator fun contains(point: Point) = point in points

    /**
     * Returns a map from each point in the mask to the result of applying the [transform] function
     * to that point.
     *
     * Points for which the [transform] function returns `null` are excluded from the resulting map.
     */
    fun <T> mapSelected(transform: (point: Point) -> T?): Map<Point, T> {
        val valueMap = mutableMapOf<Point, T>()
        for (point in points) {
            val value = transform(point)
            if (value != null) {
                valueMap[point] = value
            }
        }
        return valueMap
    }

    /**
     * Returns a copy of this mask with all points moved [distance] units in the given [direction].
     */
    fun translate(direction: Direction, distance: Int = 1): PointMask {
        val newPoints = points.mapToSet { it.move(direction, distance) }
        return PointMask(newPoints)
    }
}
