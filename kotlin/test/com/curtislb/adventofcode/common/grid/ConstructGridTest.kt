package com.curtislb.adventofcode.common.grid

import org.junit.Test
import kotlin.test.assertEquals

class ConstructGridTest {
    @Test
    fun testWithNoPoints() {
        assertEquals(emptyGrid(), constructPointGrid(emptyList()) { it })
    }

    @Test
    fun testWithOnePoint() {
        for (x in -2..2) {
            for (y in -2..2) {
                val point = Point(x, y)
                val expected: Grid<String> = gridOf(listOf(point.toString()))
                assertEquals(expected, constructPointGrid(listOf(point)) { it.toString() })
            }
        }
    }

    @Test
    fun testWithMultiplePoints() {
        val points = listOf(
            Point(-4, -3),
            Point(-4, 0),
            Point(5, -1),
            Point(-4, -3),
            Point(-2, 2),
            Point(1, 0),
            Point(4, -2)
        )
        val expected: Grid<Int> = gridOf(
            listOf(0, 0, 1, 0, 0, 0, 0, 0, 0, 0),
            listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            listOf(1, 0, 0, 0, 0, 1, 0, 0, 0, 0),
            listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1),
            listOf(0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
            listOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        )
        assertEquals(expected, constructPointGrid(points) { if (it in points) 1 else 0 })
    }
}
