package com.curtislb.adventofcode.common.grid

import com.curtislb.adventofcode.common.geometry.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests the [gridOfPoints] function.
 */
class GridOfPointsTest {
    @Test
    fun gridOfPoints_emptyList() {
        val points = emptyList<Point>()
        val grid = gridOfPoints(points) { it }
        assertThat(grid).isEqualTo(emptyGrid<Point>())
    }

    @Test
    fun gridOfPoints_singlePoint() {
        val points = listOf(Point(59, -15))
        val grid = gridOfPoints(points) { it.x + it.y }
        assertThat(grid).isEqualTo(gridOf(listOf(44)))
    }

    @Test
    fun gridOfPoints_multiplePoints() {
        val points = listOf(
            Point(-4, -3),
            Point(-4, 0),
            Point(5, -1),
            Point(-4, -3),
            Point(-2, 2),
            Point(1, 0),
            Point(4, -2)
        )
        val grid = gridOfPoints(points) { if (it in points) 1 else 0 }
        assertThat(grid).isEqualTo(
            gridOf(
                listOf(0, 0, 1, 0, 0, 0, 0, 0, 0, 0),
                listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                listOf(1, 0, 0, 0, 0, 1, 0, 0, 0, 0),
                listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1),
                listOf(0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
                listOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 0)
            )
        )
    }
}
