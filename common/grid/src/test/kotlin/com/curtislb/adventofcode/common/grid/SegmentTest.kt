package com.curtislb.adventofcode.common.grid

import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [Segment].
 */
class SegmentTest {
    @Test
    fun testConstructWithNegativeLength() {
        assertThrows<IllegalArgumentException> {
            Segment(
                Point(8, -12),
                Direction.RIGHT,
                length = -2
            )
        }
    }

    @Test
    fun testEndWithZeroLength() {
        assertEquals(Point(7, -12), Segment(Point(7, -12), Direction.UP, length = 0).end)
        assertEquals(Point(-3, 10), Segment(Point(-3, 10), Direction.RIGHT, length = 0).end)
        assertEquals(Point(-7, -3), Segment(Point(-7, -3), Direction.DOWN, length = 0).end)
        assertEquals(Point(-10, -8), Segment(Point(-10, -8), Direction.LEFT, length = 0).end)
    }

    @Test
    fun testEndWithNonzeroLength() {
        assertEquals(Point(-15, 36), Segment(Point(-15, 18), Direction.UP, length = 18).end)
        assertEquals(Point(-1, -6), Segment(Point(-7, -6), Direction.RIGHT, length = 6).end)
        assertEquals(Point(16, -17), Segment(Point(16, 0), Direction.DOWN, length = 17).end)
        assertEquals(Point(5, 10), Segment(Point(13, 10), Direction.LEFT, length = 8).end)
    }

    @Test
    fun testIsHorizontal() {
        assertFalse(Segment(Point(-18, 18), Direction.UP, length = 0).isHorizontal)
        assertFalse(Segment(Point(10, -1), Direction.UP, length = 12).isHorizontal)
        assertFalse(Segment(Point(19, -13), Direction.UP, length = 15).isHorizontal)
        assertTrue(Segment(Point(-5, 10), Direction.RIGHT, length = 0).isHorizontal)
        assertTrue(Segment(Point(0, -20), Direction.RIGHT, length = 8).isHorizontal)
        assertTrue(Segment(Point(12, -6), Direction.RIGHT, length = 4).isHorizontal)
        assertFalse(Segment(Point(8, -13), Direction.DOWN, length = 0).isHorizontal)
        assertFalse(Segment(Point(17, -6), Direction.DOWN, length = 15).isHorizontal)
        assertFalse(Segment(Point(-8, 8), Direction.DOWN, length = 12).isHorizontal)
        assertTrue(Segment(Point(-9, 15), Direction.LEFT, length = 0).isHorizontal)
        assertTrue(Segment(Point(-9, 0), Direction.LEFT, length = 6).isHorizontal)
        assertTrue(Segment(Point(10, -5), Direction.LEFT, length = 13).isHorizontal)
    }

    @Test
    fun testIsPerpendicular() {
        assertFalse(
            Segment(Point(7, 5), Direction.UP, length = 9).isPerpendicular(
                Segment(Point(20, -12), Direction.UP, length = 2)
            )
        )
        assertTrue(
            Segment(Point(1, -13), Direction.UP, length = 9).isPerpendicular(
                Segment(Point(-14, 8), Direction.RIGHT, length = 4)
            )
        )
        assertFalse(
            Segment(Point(11, -20), Direction.UP, length = 4).isPerpendicular(
                Segment(Point(-9, -2), Direction.DOWN, length = 10)
            )
        )
        assertTrue(
            Segment(Point(18, -4), Direction.UP, length = 9).isPerpendicular(
                Segment(Point(20, -20), Direction.LEFT, length = 0)
            )
        )
        assertTrue(
            Segment(Point(-20, 0), Direction.RIGHT, length = 0).isPerpendicular(
                Segment(Point(-11, -18), Direction.UP, length = 7)
            )
        )
        assertFalse(
            Segment(Point(-11, -7), Direction.RIGHT, length = 8).isPerpendicular(
                Segment(Point(8, -11), Direction.RIGHT, length = 9)
            )
        )
        assertTrue(
            Segment(Point(8, 11), Direction.RIGHT, length = 8).isPerpendicular(
                Segment(Point(-13, 6), Direction.DOWN, length = 5)
            )
        )
        assertFalse(
            Segment(Point(10, -1), Direction.RIGHT, length = 6).isPerpendicular(
                Segment(Point(19, -13), Direction.LEFT, length = 3)
            )
        )
        assertFalse(
            Segment(Point(0, -20), Direction.DOWN, length = 1).isPerpendicular(
                Segment(Point(12, -6), Direction.UP, length = 2)
            )
        )
        assertTrue(
            Segment(Point(17, -6), Direction.DOWN, length = 7).isPerpendicular(
                Segment(Point(-8, 8), Direction.RIGHT, length = 8)
            )
        )
        assertFalse(
            Segment(Point(-9, 0), Direction.DOWN, length = 1).isPerpendicular(
                Segment(Point(10, -5), Direction.DOWN, length = 0)
            )
        )
        assertTrue(
            Segment(Point(-12, 14), Direction.DOWN, length = 0).isPerpendicular(
                Segment(Point(-8, -1), Direction.LEFT, length = 1)
            )
        )
        assertTrue(
            Segment(Point(-2, -13), Direction.LEFT, length = 8).isPerpendicular(
                Segment(Point(17, 11), Direction.UP, length = 6)
            )
        )
        assertFalse(
            Segment(Point(9, -19), Direction.LEFT, length = 0).isPerpendicular(
                Segment(Point(7, 15), Direction.RIGHT, length = 10)
            )
        )
        assertTrue(
            Segment(Point(-11, -1), Direction.LEFT, length = 7).isPerpendicular(
                Segment(Point(12, 17), Direction.DOWN, length = 0)
            )
        )
        assertFalse(
            Segment(Point(-8, 14), Direction.LEFT, length = 7).isPerpendicular(
                Segment(Point(3, -12), Direction.LEFT, length = 4)
            )
        )
    }

    @Test
    fun testIntersectionWithParallelSegment() {
        assertNull(
            Segment(Point(15, 6), Direction.UP, length = 13).intersection(
                Segment(Point(-9, -3), Direction.UP, length = 2)
            )
        )
        assertNull(
            Segment(Point(4, -9), Direction.UP, length = 6).intersection(
                Segment(Point(1, -15), Direction.DOWN, length = 2)
            )
        )
        assertNull(
            Segment(Point(-12, 1), Direction.DOWN, length = 29).intersection(
                Segment(Point(-17, 13), Direction.DOWN, length = 10)
            )
        )
        assertNull(
            Segment(Point(9, -17), Direction.UP, length = 25).intersection(
                Segment(Point(9, 7), Direction.UP, length = 0)
            )
        )
        assertNull(
            Segment(Point(20, 17), Direction.DOWN, length = 3).intersection(
                Segment(Point(20, -8), Direction.UP, length = 30)
            )
        )
        assertNull(
            Segment(Point(-18, 6), Direction.DOWN, length = 16).intersection(
                Segment(Point(-18, -8), Direction.DOWN, length = 14)
            )
        )
        assertNull(
            Segment(Point(-10, 20), Direction.LEFT, length = 18).intersection(
                Segment(Point(-12, 18), Direction.LEFT, length = 18)
            )
        )
        assertNull(
            Segment(Point(-10, -20), Direction.LEFT, length = 4).intersection(
                Segment(Point(-7, 8), Direction.RIGHT, length = 27)
            )
        )
        assertNull(
            Segment(Point(-4, 0), Direction.RIGHT, length = 27).intersection(
                Segment(Point(-1, 13), Direction.RIGHT, length = 21)
            )
        )
        assertNull(
            Segment(Point(15, -1), Direction.LEFT, length = 8).intersection(
                Segment(Point(8, -1), Direction.LEFT, length = 12)
            )
        )
        assertNull(
            Segment(Point(7, 6), Direction.RIGHT, length = 29).intersection(
                Segment(Point(17, 6), Direction.LEFT, length = 23)
            )
        )
        assertNull(
            Segment(Point(-6, 14), Direction.RIGHT, length = 0).intersection(
                Segment(Point(-17, 14), Direction.RIGHT, length = 16)
            )
        )
    }

    @Test
    fun testIntersectionWithDisjointPerpendicularSegment() {
        assertNull(
            Segment(Point(-12, -12), Direction.UP, length = 10).intersection(
                Segment(Point(8, -1), Direction.RIGHT, length = 18)
            )
        )
        assertNull(
            Segment(Point(3, -9), Direction.UP, length = 13).intersection(
                Segment(Point(18, 4), Direction.LEFT, length = 2)
            )
        )
        assertNull(
            Segment(Point(-5, -13), Direction.DOWN, length = 2).intersection(
                Segment(Point(7, 2), Direction.RIGHT, length = 12)
            )
        )
        assertNull(
            Segment(Point(9, 12), Direction.DOWN, length = 6).intersection(
                Segment(Point(-9, 9), Direction.LEFT, length = 3)
            )
        )
        assertNull(
            Segment(Point(19, -9), Direction.RIGHT, length = 10).intersection(
                Segment(Point(-7, -15), Direction.UP, length = 17)
            )
        )
        assertNull(
            Segment(Point(13, 9), Direction.RIGHT, length = 7).intersection(
                Segment(Point(2, -18), Direction.DOWN, length = 12)
            )
        )
        assertNull(
            Segment(Point(5, 9), Direction.LEFT, length = 13).intersection(
                Segment(Point(-19, 20), Direction.UP, length = 7)
            )
        )
        assertNull(
            Segment(Point(-15, -13), Direction.LEFT, length = 8).intersection(
                Segment(Point(-13, -6), Direction.DOWN, length = 1)
            )
        )
    }

    @Test
    fun testIntersectionWithPerpendicularSegmentAtEndpoint() {
        assertEquals(
            Point(6, -14),
            Segment(Point(6, -14), Direction.UP, length = 13).intersection(
                Segment(Point(6, -14), Direction.RIGHT, length = 19)
            )
        )
        assertEquals(
            Point(-4, 9),
            Segment(Point(-4, 9), Direction.DOWN, length = 1).intersection(
                Segment(Point(5, 9), Direction.LEFT, length = 9)
            )
        )
        assertEquals(
            Point(-1, -7),
            Segment(Point(-18, -7), Direction.RIGHT, length = 17).intersection(
                Segment(Point(-1, -7), Direction.DOWN, length = 1)
            )
        )
        assertEquals(
            Point(13, -5),
            Segment(Point(13, -16), Direction.UP, length = 11).intersection(
                Segment(Point(23, -5), Direction.LEFT, length = 10)
            )
        )
        assertEquals(
            Point(19, 15),
            Segment(Point(19, 15), Direction.LEFT, length = 18).intersection(
                Segment(Point(19, 17), Direction.DOWN, length = 12)
            )
        )
        assertEquals(
            Point(-15, 14),
            Segment(Point(-15, 4), Direction.UP, length = 10).intersection(
                Segment(Point(-20, 14), Direction.RIGHT, length = 9)
            )
        )
        assertEquals(
            Point(-13, -11),
            Segment(Point(-3, -11), Direction.LEFT, length = 12).intersection(
                Segment(Point(-13, -11), Direction.UP, length = 10)
            )
        )
        assertEquals(
            Point(17, -14),
            Segment(Point(14, -14), Direction.RIGHT, length = 5).intersection(
                Segment(Point(17, -7), Direction.DOWN, length = 7)
            )
        )
    }

    @Test
    fun testIntersectionWithPerpendicularSegmentAtNonEndpoint() {
        assertEquals(
            Point(10, 3),
            Segment(Point(10, -13), Direction.UP, length = 20).intersection(
                Segment(Point(-2, 3), Direction.RIGHT, length = 15)
            )
        )
        assertEquals(
            Point(-13, 11),
            Segment(Point(-13, 6), Direction.UP, length = 16).intersection(
                Segment(Point(1, 11), Direction.LEFT, length = 18)
            )
        )
        assertEquals(
            Point(-4, 4),
            Segment(Point(-4, 12), Direction.DOWN, length = 13).intersection(
                Segment(Point(-6, 4), Direction.RIGHT, length = 4)
            )
        )
        assertEquals(
            Point(-17, -9),
            Segment(Point(-17, 2), Direction.DOWN, length = 12).intersection(
                Segment(Point(-10, -9), Direction.LEFT, length = 11)
            )
        )
        assertEquals(
            Point(9, -1),
            Segment(Point(5, -1), Direction.RIGHT, length = 7).intersection(
                Segment(Point(9, -2), Direction.UP, length = 3)
            )
        )
        assertEquals(
            Point(8, 10),
            Segment(Point(-4, 10), Direction.RIGHT, length = 14).intersection(
                Segment(Point(8, 16), Direction.DOWN, length = 15)
            )
        )
        assertEquals(
            Point(-20, -17),
            Segment(Point(-11, -17), Direction.LEFT, length = 12).intersection(
                Segment(Point(-20, -19), Direction.UP, length = 5)
            )
        )
        assertEquals(
            Point(-3, -4),
            Segment(Point(0, -4), Direction.LEFT, length = 11).intersection(
                Segment(Point(-3, -2), Direction.DOWN, length = 16)
            )
        )
    }
}
