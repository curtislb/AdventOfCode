package com.curtislb.adventofcode.common.grid

import kotlin.test.assertEquals
import kotlin.test.assertNull
import org.junit.jupiter.api.Test

/**
 * Tests [getOrNull].
 */
class GetCellOrNullTest {
    @Test
    fun testWithEmptyGrid() {
        assertNull(emptyGrid<Any>().getOrNull(0, 0))
    }

    @Test
    fun testWithInvalidIndices() {
        assertNull(GRID.getOrNull(-2, 3))
        assertNull(GRID.getOrNull(-2, 5))
        assertNull(GRID.getOrNull(-1, 0))
        assertNull(GRID.getOrNull(1, 5))
        assertNull(GRID.getOrNull(3, -1))
        assertNull(GRID.getOrNull(3, 6))
        assertNull(GRID.getOrNull(4, 2))
        assertNull(GRID.getOrNull(4, -2))
        assertNull(GRID.getOrNull(5, -1))
        assertNull(GRID.getOrNull(5, 1))
    }

    @Test
    fun testWithValidIndices() {
        assertEquals(-17, GRID.getOrNull(0, 0))
        assertEquals(-8, GRID.getOrNull(0, 1))
        assertEquals(2, GRID.getOrNull(0, 2))
        assertEquals(14, GRID.getOrNull(1, 4))
        assertEquals(8, GRID.getOrNull(2, 2))
        assertEquals(-11, GRID.getOrNull(2, 3))
        assertEquals(12, GRID.getOrNull(3, 1))
        assertEquals(-19, GRID.getOrNull(3, 2))
    }

    @Test
    fun testWithInvalidPoints() {
        assertNull(GRID.getOrNull(Point(-2, -1)))
        assertNull(GRID.getOrNull(Point(-2, -4)))
        assertNull(GRID.getOrNull(Point(-1, -2)))
        assertNull(GRID.getOrNull(Point(-1, 2)))
        assertNull(GRID.getOrNull(Point(0, 2)))
        assertNull(GRID.getOrNull(Point(1, -5)))
        assertNull(GRID.getOrNull(Point(3, -4)))
        assertNull(GRID.getOrNull(Point(4, 1)))
        assertNull(GRID.getOrNull(Point(5, 0)))
        assertNull(GRID.getOrNull(Point(5, 1)))
        assertNull(GRID.getOrNull(Point(6, -3)))
        assertNull(GRID.getOrNull(Point(6, -5)))

    }

    @Test
    fun testWithValidPoints() {
        assertEquals(-18, GRID.getOrNull(Point(0, -1)))
        assertEquals(11, GRID.getOrNull(Point(0, -3)))
        assertEquals(9, GRID.getOrNull(Point(1, -2)))
        assertEquals(-7, GRID.getOrNull(Point(2, -1)))
        assertEquals(-15, GRID.getOrNull(Point(3, 0)))
        assertEquals(3, GRID.getOrNull(Point(3, -1)))
        assertEquals(10, GRID.getOrNull(Point(4, 0)))
        assertEquals(16, GRID.getOrNull(Point(4, -2)))
    }

    companion object {
        /**
         * A sample 4x5 grid of integer values.
         */
        private val GRID = gridOf(
            listOf(-17, -8, 2, -15, 10),
            listOf(-18, 7, -7, 3, 14),
            listOf(15, 9, 8, -11, 16),
            listOf(11, 12, -19, -13, 19)
        )
    }
}
