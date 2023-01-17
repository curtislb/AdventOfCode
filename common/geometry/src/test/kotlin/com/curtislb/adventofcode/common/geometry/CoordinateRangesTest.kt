package com.curtislb.adventofcode.common.geometry

import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test

/**
 * Tests [coordinateRanges].
 */
class CoordinateRangesTest {
    @Test
    fun testWithNoPoints() {
        val (xRange, yRange) = emptyList<Point>().coordinateRanges()
        assertTrue(xRange.isEmpty())
        assertTrue(yRange.isEmpty())
    }

    @Test
    fun testWithOnePoint() {
        val (xRange, yRange) = listOf(Point(70, -41)).coordinateRanges()
        assertEquals(70..70, xRange)
        assertEquals(-41..-41, yRange)
    }

    @Test
    fun testWithMultiplePoints() {
        val (xRange, yRange) = listOf(
            Point(-25, 71),
            Point(90, -25),
            Point(17, 72),
            Point(22, -98),
            Point(-69, 86),
            Point(-92, -77),
            Point(86, -64),
            Point(0, -37)
        ).coordinateRanges()
        assertEquals(-92..90, xRange)
        assertEquals(-98..86, yRange)
    }
}
