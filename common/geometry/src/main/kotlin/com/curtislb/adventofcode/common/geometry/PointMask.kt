package com.curtislb.adventofcode.common.geometry

import com.curtislb.adventofcode.common.range.size

/**
 * A collection of points that can be used to select values.
 *
 * @param points All points included in the mask.
 */
class PointMask(val points: Set<Point>) {
    /**
     * Width of the minimum grid that contains all points in this mask.
     */
    val width: Int

    /**
     * Height of the minimum grid that contains all points in this mask.
     */
    val height: Int

    init {
        val (xRange, yRange) = points.coordinateRanges()
        width = xRange.size
        height = yRange.size
    }

    override fun equals(other: Any?): Boolean = other is PointMask && points == other.points

    override fun hashCode(): Int = points.hashCode()

    /**
     * Checks if the given [point] is included in this point mask.
     */
    operator fun contains(point: Point) = point in points

    /**
     * Returns a map from points in the mask to the value returned by [getValue] for each point (if
     * non-null).
     */
    fun <T> maskValues(getValue: (point: Point) -> T?): Map<Point, T> {
        val valueMap = mutableMapOf<Point, T>()
        for (point in points) {
            val value = getValue(point)
            if (value != null) {
                valueMap[point] = value
            }
        }
        return valueMap
    }

    /**
     * Returns a copy of this mask with all points moved [distance] units in the given [direction].
     */
    fun translated(direction: Direction, distance: Int = 1): PointMask =
        PointMask(points.map { it.move(direction, distance) }.toSet())
}
