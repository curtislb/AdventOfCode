package com.curtislb.adventofcode.common.grid

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

/**
 * Tests [getCellOrNull].
 */
class GetCellOrNullTest {
    @Test fun testWithEmptyGrid() {
        assertNull(emptyList<Nothing>().getCellOrNull(0, 0))
    }

    @Test fun testWithInvalidIndices() {
        assertNull(GRID.getCellOrNull(-2, 3))
        assertNull(GRID.getCellOrNull(-2, 5))
        assertNull(GRID.getCellOrNull(-1, 0))
        assertNull(GRID.getCellOrNull(1, 5))
        assertNull(GRID.getCellOrNull(3, -1))
        assertNull(GRID.getCellOrNull(3, 6))
        assertNull(GRID.getCellOrNull(4, 2))
        assertNull(GRID.getCellOrNull(4, -2))
        assertNull(GRID.getCellOrNull(5, -1))
        assertNull(GRID.getCellOrNull(5, 1))
    }

    @Test fun testWithValidIndices() {
        assertEquals(-17, GRID.getCellOrNull(0, 0))
        assertEquals(-8, GRID.getCellOrNull(0, 1))
        assertEquals(2, GRID.getCellOrNull(0, 2))
        assertEquals(14, GRID.getCellOrNull(1, 4))
        assertEquals(8, GRID.getCellOrNull(2, 2))
        assertEquals(-11, GRID.getCellOrNull(2, 3))
        assertEquals(12, GRID.getCellOrNull(3, 1))
        assertEquals(-19, GRID.getCellOrNull(3, 2))
    }

    @Test fun testWithInvalidPoints() {
        assertNull(GRID.getCellOrNull(Point(-2, -1)))
        assertNull(GRID.getCellOrNull(Point(-2, -4)))
        assertNull(GRID.getCellOrNull(Point(-1, -2)))
        assertNull(GRID.getCellOrNull(Point(-1, 2)))
        assertNull(GRID.getCellOrNull(Point(0, 2)))
        assertNull(GRID.getCellOrNull(Point(1, -5)))
        assertNull(GRID.getCellOrNull(Point(3, -4)))
        assertNull(GRID.getCellOrNull(Point(4, 1)))
        assertNull(GRID.getCellOrNull(Point(5, 0)))
        assertNull(GRID.getCellOrNull(Point(5, 1)))
        assertNull(GRID.getCellOrNull(Point(6, -3)))
        assertNull(GRID.getCellOrNull(Point(6, -5)))

    }

    @Test fun testWithValidPoints() {
        assertEquals(-18, GRID.getCellOrNull(Point(0, -1)))
        assertEquals(11, GRID.getCellOrNull(Point(0, -3)))
        assertEquals(9, GRID.getCellOrNull(Point(1, -2)))
        assertEquals(-7, GRID.getCellOrNull(Point(2, -1)))
        assertEquals(-15, GRID.getCellOrNull(Point(3, 0)))
        assertEquals(3, GRID.getCellOrNull(Point(3, -1)))
        assertEquals(10, GRID.getCellOrNull(Point(4, 0)))
        assertEquals(16, GRID.getCellOrNull(Point(4, -2)))
    }

    companion object {
        /**
         * A sample 4x5 grid of integer values.
         */
        private val GRID = listOf(
            listOf(-17, -8, 2, -15, 10),
            listOf(-18, 7, -7, 3, 14),
            listOf(15, 9, 8, -11, 16),
            listOf(11, 12, -19, -13, 19)
        )
    }
}
