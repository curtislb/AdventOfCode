package com.curtislb.adventofcode.common.geometry

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests the [CoordinateRanges] class.
 */
class CoordinateRangesTest {
    @Test
    fun isEmpty_whenBothEmpty() {
        val ranges = CoordinateRanges(x = IntRange.EMPTY, y = IntRange.EMPTY)
        assertThat(ranges.isEmpty()).isTrue
    }

    @Test
    fun isEmpty_whenXEmpty() {
        val ranges = CoordinateRanges(x = IntRange.EMPTY, y = -20..56)
        assertThat(ranges.isEmpty()).isTrue
    }

    @Test
    fun isEmpty_whenYEmpty() {
        val ranges = CoordinateRanges(x = 6..17, y = IntRange.EMPTY)
        assertThat(ranges.isEmpty()).isTrue
    }

    @Test
    fun isEmpty_whenBothNonEmpty() {
        val ranges = CoordinateRanges(x = 6..17, y = -20..56)
        assertThat(ranges.isEmpty()).isFalse
    }

    @Test
    fun ofPoints_noPoints() {
        val points = emptyList<Point>()
        val ranges = CoordinateRanges.ofPoints(points)
        assertThat(ranges).isEqualTo(CoordinateRanges(x = IntRange.EMPTY, y = IntRange.EMPTY))
    }

    @Test
    fun ofPoints_singlePoint() {
        val points = listOf(Point(70, -41))
        val ranges = CoordinateRanges.ofPoints(points)
        assertThat(ranges).isEqualTo(CoordinateRanges(x = 70..70, y = -41..-41))
    }

    @Test
    fun ofPoints_multiplePoints() {
        val points = listOf(
            Point(-25, 71),
            Point(90, -25),
            Point(17, 72),
            Point(22, -98),
            Point(-69, 86),
            Point(-92, -77),
            Point(86, -64),
            Point(0, -37)
        )
        val ranges = CoordinateRanges.ofPoints(points)
        assertThat(ranges).isEqualTo(CoordinateRanges(x = -92..90, y = -98..86))
    }
}
