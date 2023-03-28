package com.curtislb.adventofcode.common.geometry

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests the [PointMask] class.
 */
class PointMaskTest {
    @Test
    fun width_whenEmpty() {
        val points = emptySet<Point>()
        val mask = PointMask(points)
        assertThat(mask.width).isEqualTo(0)
    }

    @Test
    fun width_withOnePoint() {
        val points = setOf(Point(-2, 28))
        val mask = PointMask(points)
        assertThat(mask.width).isEqualTo(1)
    }

    @Test
    fun width_withMultiplePoints() {
        val points = setOf(
            Point(77, 48),
            Point(-31, -84),
            Point(-70, 98),
            Point(31, 59),
            Point(-32, 48)
        )
        val mask = PointMask(points)
        assertThat(mask.width).isEqualTo(148)
    }

    @Test
    fun height_whenEmpty() {
        val points = emptySet<Point>()
        val mask = PointMask(points)
        assertThat(mask.height).isEqualTo(0)
    }

    @Test
    fun height_withOnePoint() {
        val points = setOf(Point(20, -22))
        val mask = PointMask(points)
        assertThat(mask.height).isEqualTo(1)
    }

    @Test
    fun height_withMultiplePoints() {
        val points = setOf(
            Point(58, 40),
            Point(95, -26),
            Point(-74, 38),
            Point(24, 10),
            Point(3, -77)
        )
        val mask = PointMask(points)
        assertThat(mask.height).isEqualTo(118)
    }

    @Test
    fun contains_whenEmpty() {
        val points = emptySet<Point>()
        val mask = PointMask(points)
        assertThat(Point.ORIGIN in mask).isFalse
    }

    @Test
    fun contains_withSinglePoint_absentPoint() {
        val points = setOf(Point(79, -64))
        val mask = PointMask(points)
        assertThat(Point(-64, 79) in mask).isFalse
    }

    @Test
    fun contains_withSinglePoint_presentPoint() {
        val points = setOf(Point(-67, -95))
        val mask = PointMask(points)
        assertThat(Point(-67, -95) in mask).isTrue
    }

    @Test
    fun contains_withMultiplePoints_absentPoint() {
        val points = setOf(
            Point(76, -1),
            Point(-67, -80),
            Point(60, 18),
            Point(88, 40),
            Point(44, 8)
        )
        val mask = PointMask(points)
        assertThat(Point(44, 18) in mask).isFalse
    }

    @Test
    fun contains_withMultiplePoints_presentPoint() {
        val points = setOf(
            Point(-73, -98),
            Point(-96, 9),
            Point(-65, 49),
            Point(88, 76),
            Point(72, 75)
        )
        val mask = PointMask(points)
        assertThat(Point(-65, 49) in mask).isTrue
    }

    @Test
    fun mapSelected_whenEmpty() {
        val points = emptySet<Point>()
        val mask = PointMask(points)
        assertThat(mask.mapSelected { it }).isEmpty()
    }

    @Test
    fun mapSelected_withSinglePoint_toNull() {
        val points = setOf(Point(-19, 5))
        val mask = PointMask(points)
        assertThat(mask.mapSelected<Any> { null }).isEmpty()
    }

    @Test
    fun mapSelected_withSinglePoint_toNonNull() {
        val points = setOf(Point(-19, 5))
        val mask = PointMask(points)
        assertThat(mask.mapSelected { it.x * it.y })
            .containsExactlyInAnyOrderEntriesOf(mapOf(Point(-19, 5) to -95))
    }

    @Test
    fun mapSelected_withMultiplePoints_allToNull() {
        val points = setOf(
            Point(-31, 86),
            Point(92, -8),
            Point(-19, 19),
            Point(80, -14),
            Point(32, -47)
        )
        val mask = PointMask(points)
        assertThat(mask.mapSelected<Any> { null }).isEmpty()
    }

    @Test
    fun mapSelected_withMultiplePoints_allToNonNull() {
        val points = setOf(
            Point(41, 2),
            Point(67, 86),
            Point(-49, 4),
            Point(-22, 80),
            Point(-17, -81)
        )
        val mask = PointMask(points)
        assertThat(mask.mapSelected { Point(-it.y, it.x) }).containsExactlyInAnyOrderEntriesOf(
            mapOf(
                Point(41, 2) to Point(-2, 41),
                Point(67, 86) to Point(-86, 67),
                Point(-49, 4) to Point(-4, -49),
                Point(-22, 80) to Point(-80, -22),
                Point(-17, -81) to Point(81, -17)
            )
        )
    }

    @Test
    fun mapSelected_withMultiplePoints_someToNull() {
        val points = setOf(
            Point(-82, -89),
            Point(-10, 21),
            Point(36, -97),
            Point(-50, 38),
            Point(96, -4)
        )
        val mask = PointMask(points)
        assertThat(mask.mapSelected { if (it.y < 0) null else Point(it.y, it.x) })
            .containsExactlyInAnyOrderEntriesOf(
                mapOf(
                    Point(-10, 21) to Point(21, -10),
                    Point(-50, 38) to Point(38, -50),
                )
            )
    }

    @Test
    fun translate_whenEmpty_defaultDistance() {
        val points = emptySet<Point>()
        val mask = PointMask(points)

        val translated = mask.translate(Direction.UP_LEFT)
        assertThat(translated.points).isEmpty()
    }

    @Test
    fun translate_whenEmpty_nonDefaultDistance() {
        val points = emptySet<Point>()
        val mask = PointMask(points)

        val translated = mask.translate(Direction.RIGHT, distance = -5)
        assertThat(translated.points).isEmpty()
    }

    @Test
    fun translate_withSinglePoint_defaultDistance() {
        val points = setOf(Point(-27, 57))
        val mask = PointMask(points)

        val translated = mask.translate(Direction.DOWN)
        assertThat(translated.points).containsExactly(Point(-27, 56))
    }

    @Test
    fun translate_withSinglePoint_nonDefaultDistance() {
        val points = setOf(Point(44, 74))
        val mask = PointMask(points)

        val translated = mask.translate(Direction.DOWN_RIGHT, distance = 20)
        assertThat(translated.points).containsExactly(Point(64, 54))
    }

    @Test
    fun translate_withMultiplePoints_defaultDistance() {
        val points = setOf(
            Point(-47, 22),
            Point(-33, -7),
            Point(-11, -30),
            Point(63, -13),
            Point(84, 54)
        )
        val mask = PointMask(points)

        val translated = mask.translate(Direction.DOWN_LEFT)
        assertThat(translated.points).containsExactlyInAnyOrder(
            Point(-48, 21),
            Point(-34, -8),
            Point(-12, -31),
            Point(62, -14),
            Point(83, 53)
        )
    }

    @Test
    fun translate_withMultiplePoints_nonDefaultDistance() {
        val points = setOf(
            Point(24, 24),
            Point(46, 86),
            Point(-92, 16),
            Point(27, -10),
            Point(-63, -88)
        )
        val mask = PointMask(points)

        val translated = mask.translate(Direction.UP, distance = -42)
        assertThat(translated.points).containsExactlyInAnyOrder(
            Point(24, -18),
            Point(46, 44),
            Point(-92, -26),
            Point(27, -52),
            Point(-63, -130)
        )
    }
}
