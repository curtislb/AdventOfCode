package com.curtislb.adventofcode.common.grid

/**
 * TODO
 */
class PointMask(val includedPoints: Set<Point>) {
    /**
     * TODO
     */
    val width: Int

    /**
     * TODO
     */
    val height: Int

    init {
        var minX = Int.MAX_VALUE
        var minY = Int.MAX_VALUE
        var maxX = Int.MIN_VALUE
        var maxY = Int.MIN_VALUE

        includedPoints.forEach { point ->
            minX = minX.coerceAtMost(point.x)
            minY = minY.coerceAtMost(point.y)
            maxX = maxX.coerceAtLeast(point.x)
            maxY = maxY.coerceAtLeast(point.y)
        }

        width = if (includedPoints.isEmpty()) 0 else maxX - minX + 1
        height = if (includedPoints.isEmpty()) 0 else maxY - minY + 1
    }

    /**
     * TODO
     */
    fun <T> applyMask(grid: List<List<T>>): Map<Point, T> {
        return mutableMapOf<Point, T>().apply {
            includedPoints.forEach { point ->
                val value = grid.getCellOrNull(point)
                if (value != null) {
                    this[point] = value
                }
            }
        }
    }

    /**
     * TODO
     */
    fun translated(direction: Direction, distance: Int = 1): PointMask {
        return PointMask(includedPoints.map { it.move(direction, distance) }.toSet())
    }
}
