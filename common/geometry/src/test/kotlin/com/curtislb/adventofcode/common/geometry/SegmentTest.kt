package com.curtislb.adventofcode.common.geometry

import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test

/**
 * Tests [Segment].
 */
class SegmentTest {
    @Test
    fun testStartAndEndWithZeroLength() {
        assertEquals(Point(7, -12), Segment.from(Point(7, -12), Direction.UP, distance = 0).start)
        assertEquals(Point(7, -12), Segment.from(Point(7, -12), Direction.UP, distance = 0).end)
        assertEquals(Point(-3, 5), Segment.from(Point(-3, 5), Direction.RIGHT, distance = 0).start)
        assertEquals(Point(-3, 5), Segment.from(Point(-3, 5), Direction.RIGHT, distance = 0).end)
        assertEquals(Point(-7, -3), Segment.from(Point(-7, -3), Direction.DOWN, distance = 0).start)
        assertEquals(Point(-7, -3), Segment.from(Point(-7, -3), Direction.DOWN, distance = 0).end)
        assertEquals(Point(10, 8), Segment.from(Point(10, 8), Direction.LEFT, distance = 0).start)
        assertEquals(Point(10, 8), Segment.from(Point(10, 8), Direction.LEFT, distance = 0).end)
    }

    @Test
    fun testStartAndEndWithNonzeroLength() {
        assertEquals(Point(-15, 36), Segment.from(Point(-15, 18), Direction.UP, distance = 18).end)
        assertEquals(Point(-1, -6), Segment.from(Point(-7, -6), Direction.RIGHT, distance = 6).end)
        assertEquals(Point(16, -7), Segment.from(Point(16, 0), Direction.DOWN, distance = 7).start)
        assertEquals(Point(5, 10), Segment.from(Point(13, 10), Direction.LEFT, distance = 8).start)
    }

    @Test
    fun testIsHorizontal() {
        assertFalse(Segment.from(Point(-18, 18), Direction.UP, distance = 0).isHorizontal)
        assertFalse(Segment.from(Point(10, -1), Direction.UP, distance = 12).isHorizontal)
        assertFalse(Segment.from(Point(19, -13), Direction.UP, distance = 15).isHorizontal)
        assertFalse(Segment.from(Point(-5, 10), Direction.RIGHT, distance = 0).isHorizontal)
        assertTrue(Segment.from(Point(0, -20), Direction.RIGHT, distance = 8).isHorizontal)
        assertTrue(Segment.from(Point(12, -6), Direction.RIGHT, distance = 4).isHorizontal)
        assertFalse(Segment.from(Point(8, -13), Direction.DOWN, distance = 0).isHorizontal)
        assertFalse(Segment.from(Point(17, -6), Direction.DOWN, distance = 15).isHorizontal)
        assertFalse(Segment.from(Point(-8, 8), Direction.DOWN, distance = 12).isHorizontal)
        assertFalse(Segment.from(Point(-9, 15), Direction.LEFT, distance = 0).isHorizontal)
        assertTrue(Segment.from(Point(-9, 0), Direction.LEFT, distance = 6).isHorizontal)
        assertTrue(Segment.from(Point(10, -5), Direction.LEFT, distance = 13).isHorizontal)
    }

    @Test
    fun testIsPerpendicular() {
        assertFalse(
            Segment.from(Point(7, 5), Direction.UP, distance = 9).isPerpendicular(
                Segment.from(Point(20, -12), Direction.UP, distance = 2)
            )
        )
        assertTrue(
            Segment.from(Point(1, -13), Direction.UP, distance = 9).isPerpendicular(
                Segment.from(Point(-14, 8), Direction.RIGHT, distance = 4)
            )
        )
        assertFalse(
            Segment.from(Point(11, -20), Direction.UP, distance = 4).isPerpendicular(
                Segment.from(Point(-9, -2), Direction.DOWN, distance = 10)
            )
        )
        assertFalse(
            Segment.from(Point(18, -4), Direction.UP, distance = 9).isPerpendicular(
                Segment.from(Point(20, -20), Direction.LEFT, distance = 0)
            )
        )
        assertFalse(
            Segment.from(Point(-20, 0), Direction.RIGHT, distance = 0).isPerpendicular(
                Segment.from(Point(-11, -18), Direction.UP, distance = 7)
            )
        )
        assertFalse(
            Segment.from(Point(-11, -7), Direction.RIGHT, distance = 8).isPerpendicular(
                Segment.from(Point(8, -11), Direction.RIGHT, distance = 9)
            )
        )
        assertTrue(
            Segment.from(Point(8, 11), Direction.RIGHT, distance = 8).isPerpendicular(
                Segment.from(Point(-13, 6), Direction.DOWN, distance = 5)
            )
        )
        assertFalse(
            Segment.from(Point(10, -1), Direction.RIGHT, distance = 6).isPerpendicular(
                Segment.from(Point(19, -13), Direction.LEFT, distance = 3)
            )
        )
        assertFalse(
            Segment.from(Point(0, -20), Direction.DOWN, distance = 1).isPerpendicular(
                Segment.from(Point(12, -6), Direction.UP, distance = 2)
            )
        )
        assertTrue(
            Segment.from(Point(17, -6), Direction.DOWN, distance = 7).isPerpendicular(
                Segment.from(Point(-8, 8), Direction.RIGHT, distance = 8)
            )
        )
        assertFalse(
            Segment.from(Point(-9, 0), Direction.DOWN, distance = 1).isPerpendicular(
                Segment.from(Point(10, -5), Direction.DOWN, distance = 0)
            )
        )
        assertFalse(
            Segment.from(Point(-12, 14), Direction.DOWN, distance = 0).isPerpendicular(
                Segment.from(Point(-8, -1), Direction.LEFT, distance = 1)
            )
        )
        assertTrue(
            Segment.from(Point(-2, -13), Direction.LEFT, distance = 8).isPerpendicular(
                Segment.from(Point(17, 11), Direction.UP, distance = 6)
            )
        )
        assertFalse(
            Segment.from(Point(9, -19), Direction.LEFT, distance = 0).isPerpendicular(
                Segment.from(Point(7, 15), Direction.RIGHT, distance = 10)
            )
        )
        assertFalse(
            Segment.from(Point(-11, -1), Direction.LEFT, distance = 7).isPerpendicular(
                Segment.from(Point(12, 17), Direction.DOWN, distance = 0)
            )
        )
        assertFalse(
            Segment.from(Point(-8, 14), Direction.LEFT, distance = 7).isPerpendicular(
                Segment.from(Point(3, -12), Direction.LEFT, distance = 4)
            )
        )
    }

    @Test
    fun testIntersectionWithParallelSegment() {
        assertNull(
            Segment.from(Point(15, 6), Direction.UP, distance = 13).intersectionWith(
                Segment.from(Point(-9, -3), Direction.UP, distance = 2)
            )
        )
        assertNull(
            Segment.from(Point(4, -9), Direction.UP, distance = 6).intersectionWith(
                Segment.from(Point(1, -15), Direction.DOWN, distance = 2)
            )
        )
        assertNull(
            Segment.from(Point(-12, 1), Direction.DOWN, distance = 29).intersectionWith(
                Segment.from(Point(-17, 13), Direction.DOWN, distance = 10)
            )
        )
        assertNull(
            Segment.from(Point(20, 17), Direction.DOWN, distance = 3).intersectionWith(
                Segment.from(Point(20, -8), Direction.UP, distance = 30)
            )
        )
        assertNull(
            Segment.from(Point(-18, 6), Direction.DOWN, distance = 16).intersectionWith(
                Segment.from(Point(-18, -8), Direction.DOWN, distance = 14)
            )
        )
        assertNull(
            Segment.from(Point(-10, 20), Direction.LEFT, distance = 18).intersectionWith(
                Segment.from(Point(-12, 18), Direction.LEFT, distance = 18)
            )
        )
        assertNull(
            Segment.from(Point(-10, -20), Direction.LEFT, distance = 4).intersectionWith(
                Segment.from(Point(-7, 8), Direction.RIGHT, distance = 27)
            )
        )
        assertNull(
            Segment.from(Point(-4, 0), Direction.RIGHT, distance = 27).intersectionWith(
                Segment.from(Point(-1, 13), Direction.RIGHT, distance = 21)
            )
        )
        assertNull(
            Segment.from(Point(15, -1), Direction.LEFT, distance = 8).intersectionWith(
                Segment.from(Point(8, -1), Direction.LEFT, distance = 12)
            )
        )
        assertNull(
            Segment.from(Point(7, 6), Direction.RIGHT, distance = 29).intersectionWith(
                Segment.from(Point(17, 6), Direction.LEFT, distance = 23)
            )
        )
    }

    @Test
    fun testIntersectionWithDisjointPerpendicularSegment() {
        assertNull(
            Segment.from(Point(-12, -12), Direction.UP, distance = 10).intersectionWith(
                Segment.from(Point(8, -1), Direction.RIGHT, distance = 18)
            )
        )
        assertNull(
            Segment.from(Point(3, -9), Direction.UP, distance = 13).intersectionWith(
                Segment.from(Point(18, 4), Direction.LEFT, distance = 2)
            )
        )
        assertNull(
            Segment.from(Point(-5, -13), Direction.DOWN, distance = 2).intersectionWith(
                Segment.from(Point(7, 2), Direction.RIGHT, distance = 12)
            )
        )
        assertNull(
            Segment.from(Point(9, 12), Direction.DOWN, distance = 6).intersectionWith(
                Segment.from(Point(-9, 9), Direction.LEFT, distance = 3)
            )
        )
        assertNull(
            Segment.from(Point(19, -9), Direction.RIGHT, distance = 10).intersectionWith(
                Segment.from(Point(-7, -15), Direction.UP, distance = 17)
            )
        )
        assertNull(
            Segment.from(Point(13, 9), Direction.RIGHT, distance = 7).intersectionWith(
                Segment.from(Point(2, -18), Direction.DOWN, distance = 12)
            )
        )
        assertNull(
            Segment.from(Point(5, 9), Direction.LEFT, distance = 13).intersectionWith(
                Segment.from(Point(-19, 20), Direction.UP, distance = 7)
            )
        )
        assertNull(
            Segment.from(Point(-15, -13), Direction.LEFT, distance = 8).intersectionWith(
                Segment.from(Point(-13, -6), Direction.DOWN, distance = 1)
            )
        )
    }

    @Test
    fun testIntersectionWithPerpendicularSegmentAtEndpoint() {
        assertEquals(
            Point(6, -14),
            Segment.from(Point(6, -14), Direction.UP, distance = 13).intersectionWith(
                Segment.from(Point(6, -14), Direction.RIGHT, distance = 19)
            )
        )
        assertEquals(
            Point(-4, 9),
            Segment.from(Point(-4, 9), Direction.DOWN, distance = 1).intersectionWith(
                Segment.from(Point(5, 9), Direction.LEFT, distance = 9)
            )
        )
        assertEquals(
            Point(-1, -7),
            Segment.from(Point(-18, -7), Direction.RIGHT, distance = 17).intersectionWith(
                Segment.from(Point(-1, -7), Direction.DOWN, distance = 1)
            )
        )
        assertEquals(
            Point(13, -5),
            Segment.from(Point(13, -16), Direction.UP, distance = 11).intersectionWith(
                Segment.from(Point(23, -5), Direction.LEFT, distance = 10)
            )
        )
        assertEquals(
            Point(19, 15),
            Segment.from(Point(19, 15), Direction.LEFT, distance = 18).intersectionWith(
                Segment.from(Point(19, 17), Direction.DOWN, distance = 12)
            )
        )
        assertEquals(
            Point(-15, 14),
            Segment.from(Point(-15, 4), Direction.UP, distance = 10).intersectionWith(
                Segment.from(Point(-20, 14), Direction.RIGHT, distance = 9)
            )
        )
        assertEquals(
            Point(-13, -11),
            Segment.from(Point(-3, -11), Direction.LEFT, distance = 12).intersectionWith(
                Segment.from(Point(-13, -11), Direction.UP, distance = 10)
            )
        )
        assertEquals(
            Point(17, -14),
            Segment.from(Point(14, -14), Direction.RIGHT, distance = 5).intersectionWith(
                Segment.from(Point(17, -7), Direction.DOWN, distance = 7)
            )
        )
        assertEquals(
            Point(9, 7),
            Segment.from(Point(9, -17), Direction.UP, distance = 25).intersectionWith(
                Segment.from(Point(9, 7), Direction.UP, distance = 0)
            )
        )
    }

    @Test
    fun testIntersectionWithPerpendicularSegmentAtNonEndpoint() {
        assertEquals(
            Point(10, 3),
            Segment.from(Point(10, -13), Direction.UP, distance = 20).intersectionWith(
                Segment.from(Point(-2, 3), Direction.RIGHT, distance = 15)
            )
        )
        assertEquals(
            Point(-13, 11),
            Segment.from(Point(-13, 6), Direction.UP, distance = 16).intersectionWith(
                Segment.from(Point(1, 11), Direction.LEFT, distance = 18)
            )
        )
        assertEquals(
            Point(-4, 4),
            Segment.from(Point(-4, 12), Direction.DOWN, distance = 13).intersectionWith(
                Segment.from(Point(-6, 4), Direction.RIGHT, distance = 4)
            )
        )
        assertEquals(
            Point(-17, -9),
            Segment.from(Point(-17, 2), Direction.DOWN, distance = 12).intersectionWith(
                Segment.from(Point(-10, -9), Direction.LEFT, distance = 11)
            )
        )
        assertEquals(
            Point(9, -1),
            Segment.from(Point(5, -1), Direction.RIGHT, distance = 7).intersectionWith(
                Segment.from(Point(9, -2), Direction.UP, distance = 3)
            )
        )
        assertEquals(
            Point(8, 10),
            Segment.from(Point(-4, 10), Direction.RIGHT, distance = 14).intersectionWith(
                Segment.from(Point(8, 16), Direction.DOWN, distance = 15)
            )
        )
        assertEquals(
            Point(-20, -17),
            Segment.from(Point(-11, -17), Direction.LEFT, distance = 12).intersectionWith(
                Segment.from(Point(-20, -19), Direction.UP, distance = 5)
            )
        )
        assertEquals(
            Point(-3, -4),
            Segment.from(Point(0, -4), Direction.LEFT, distance = 11).intersectionWith(
                Segment.from(Point(-3, -2), Direction.DOWN, distance = 16)
            )
        )
        assertEquals(
            Point(-6, 14),
            Segment.from(Point(-6, 14), Direction.RIGHT, distance = 0).intersectionWith(
                Segment.from(Point(-17, 14), Direction.RIGHT, distance = 16)
            )
        )
    }
}
