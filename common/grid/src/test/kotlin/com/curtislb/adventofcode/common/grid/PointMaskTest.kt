package com.curtislb.adventofcode.common.grid

import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test

/**
 * Tests [PointMask].
 */
class PointMaskTest {
    @Test
    fun testWhenEmpty() {
        val mask = PointMask(setOf())
        assertEquals(0, mask.width)
        assertEquals(0, mask.height)
        assertFalse(Point.ORIGIN in mask)
        assertEquals(emptyMap(), mask.applyToGrid(emptyGrid<Int>()))
        assertEquals(emptyMap(), mask.applyToGrid(gridOf(listOf(3))))
        assertEquals(mask, mask.translated(Direction.DOWN, 0))
        assertEquals(mask, mask.translated(Direction.LEFT, 2))
    }

    @Test
    fun testWithOnePoint() {
        val mask = PointMask(setOf(Point.ORIGIN))
        assertEquals(1, mask.width)
        assertEquals(1, mask.height)
        assertTrue(Point.ORIGIN in mask)
        assertFalse(Point(1, 0) in mask)
        assertFalse(Point(0, -1) in mask)
        assertEquals(emptyMap(), mask.applyToGrid(emptyGrid<Int>()))
        assertEquals(mapOf(Point.ORIGIN to 3), mask.applyToGrid(gridOf(listOf(3))))
        assertEquals(mapOf(Point.ORIGIN to 6), mask.applyToGrid(gridOf(listOf(6, 7), listOf(8, 9))))
        assertEquals(mask, mask.translated(Direction.DOWN, 0))
        assertEquals(PointMask(setOf(Point(-2, 0))), mask.translated(Direction.LEFT, 2))
    }

    @Test
    fun testWithMultiplePoints() {
        val mask = PointMask(setOf(
            Point(4, -2),
            Point(5, -2),
            Point(3, -5),
            Point(2, -4),
            Point(0, 1),
            Point(4, -3),
            Point(3, -2),
            Point(2, -1),
            Point(0, -3)
        ))

        assertEquals(6, mask.width)
        assertEquals(7, mask.height)

        assertFalse(Point.ORIGIN in mask)
        assertTrue(Point(0, 1) in mask)
        assertTrue(Point(2, -1) in mask)

        assertEquals(emptyMap(), mask.applyToGrid(emptyGrid<Int>()))
        assertEquals(
            mapOf(
                Point(0, -3) to -53,
                Point(2, -1) to 78,
                Point(2, -4) to -83,
                Point(3, -2) to 41,
                Point(4, -2) to 73,
                Point(4, -3) to 45,
            ),
            mask.applyToGrid(gridOf(
                listOf( 85, -59,  15, -85,  72),
                listOf( 13,  44,  78, -45, -22),
                listOf(-81,   1, -82,  41,  73),
                listOf(-53,  13,  66, -71,  45),
                listOf(-70, -93, -83, -30,  48)
            ))
        )

        assertEquals(mask, mask.translated(Direction.DOWN, 0))
        assertEquals(
            PointMask(setOf(
                Point(2, -3),
                Point(3, -3),
                Point(1, -6),
                Point(0, -5),
                Point(-2, 0),
                Point(2, -4),
                Point(1, -3),
                Point(0, -2),
                Point(-2, -4)
            )),
            mask.translated(Direction.LEFT, 2).translated(Direction.DOWN, 1)
        )
    }
}