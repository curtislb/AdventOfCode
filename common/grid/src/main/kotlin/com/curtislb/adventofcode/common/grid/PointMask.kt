package com.curtislb.adventofcode.common.grid

import com.curtislb.adventofcode.common.range.sizeInclusive

/**
 * A collection of points that can be used to select values from a grid.
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
        width = xRange.sizeInclusive
        height = yRange.sizeInclusive
    }

    /**
     * Checks if the given [point] is included in this point mask.
     */
    operator fun contains(point: Point) = point in points

    /**
     * Returns a map from points in the mask to the values at those points in the given [grid].
     */
    fun <T> applyToGrid(grid: Grid<T>): Map<Point, T> =
        mutableMapOf<Point, T>().apply {
            points.forEach { point ->
                grid.getOrNull(point)?.let { this[point] = it }
            }
        }

    /**
     * Returns a copy of this mask with all points moved [distance] units in the given [direction].
     */
    fun translated(direction: Direction, distance: Int = 1): PointMask =
        PointMask(points.map { it.move(direction, distance) }.toSet())

    override fun equals(other: Any?): Boolean = other is PointMask && points == other.points

    override fun hashCode(): Int = points.hashCode()
}
