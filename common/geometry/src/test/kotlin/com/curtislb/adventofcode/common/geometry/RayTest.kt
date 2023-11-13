package com.curtislb.adventofcode.common.geometry

import com.curtislb.adventofcode.common.number.Fraction
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests the [Ray] class.
 */
class RayTest {
    @Test
    fun points_zeroSlope_positiveDirection() {
        val ray = Ray(source = Point(-49, -89), slope = Fraction.ZERO, isPositive = true)
        assertThat(ray.points().asIterable())
            .startsWith(Point(-49, -89), Point(-48, -89), Point(-47, -89))
    }

    @Test
    fun points_zeroSlope_negativeDirection() {
        val ray = Ray(source = Point(6, 52), slope = Fraction.ZERO, isPositive = false)
        assertThat(ray.points().asIterable()).startsWith(Point(6, 52), Point(5, 52), Point(4, 52))
    }

    @Test
    fun points_positiveSlope_positiveDirection() {
        val ray = Ray(source = Point(-17, 1), slope = Fraction.valueOf(21, 22), isPositive = true)
        assertThat(ray.points().asIterable()).startsWith(Point(-17, 1), Point(5, 22), Point(27, 43))
    }

    @Test
    fun points_positiveSlope_negativeDirection() {
        val ray = Ray(source = Point(3, -23), slope = Fraction.valueOf(3, 26), isPositive = false)
        assertThat(ray.points().asIterable())
            .startsWith(Point(3, -23), Point(-23, -26), Point(-49, -29))
    }

    @Test
    fun points_negativeSlope_positiveDirection() {
        val ray = Ray(source = Point(-49, -30), slope = Fraction.valueOf(-2), isPositive = true)
        assertThat(ray.points().asIterable())
            .startsWith(Point(-49, -30), Point(-48, -32), Point(-47, -34))
    }

    @Test
    fun points_negativeSlope_negativeDirection() {
        val ray = Ray(
            source = Point(41, -66),
            slope = Fraction.valueOf(-4, 3),
            isPositive = false
        )
        assertThat(ray.points().asIterable())
            .startsWith(Point(41, -66), Point(38, -62), Point(35, -58))
    }

    @Test
    fun points_nullSlope_positiveDirection() {
        val ray = Ray(source = Point(-28, -70), slope = null, isPositive = true)
        assertThat(ray.points().asIterable())
            .startsWith(Point(-28, -70), Point(-28, -69), Point(-28, -68))
    }

    @Test
    fun points_nullSlope_negativeDirection() {
        val ray = Ray(source = Point(58, -40), slope = null, isPositive = false)
        assertThat(ray.points().asIterable())
            .startsWith(Point(58, -40), Point(58, -41), Point(58, -42))
    }

    @Test
    fun points_xReachesMaxValue() {
        val source = Point(Int.MAX_VALUE - 1, -2)
        val ray = Ray(source, slope = Fraction.ZERO, isPositive = true)
        assertThat(ray.points().asIterable()).containsExactly(source, Point(Int.MAX_VALUE, -2))
    }

    @Test
    fun points_xOvershootsMaxValue() {
        val source = Point(Int.MAX_VALUE - 1, 85)
        val ray = Ray(source, slope = Fraction.valueOf(1, 2), isPositive = true)
        assertThat(ray.points().asIterable()).containsExactly(source)
    }

    @Test
    fun points_xReachesMinValue() {
        val source = Point(Int.MIN_VALUE + 1, 48)
        val ray = Ray(source, slope = Fraction.ZERO, isPositive = false)
        assertThat(ray.points().asIterable()).containsExactly(source, Point(Int.MIN_VALUE, 48))
    }

    @Test
    fun points_xOvershootsMinValue() {
        val source = Point(Int.MIN_VALUE + 1, -65)
        val ray = Ray(source, slope = Fraction.valueOf(1, 2), isPositive = false)
        assertThat(ray.points().asIterable()).containsExactly(source)
    }

    @Test
    fun points_yReachesMaxValue() {
        val source = Point(-3, Int.MAX_VALUE - 1)
        val ray = Ray(source, slope = null, isPositive = true)
        assertThat(ray.points().asIterable()).containsExactly(source, Point(-3, Int.MAX_VALUE))
    }

    @Test
    fun points_yOvershootsMaxValue() {
        val source = Point(3, Int.MAX_VALUE - 1)
        val ray = Ray(source, slope = Fraction.valueOf(2), isPositive = true)
        assertThat(ray.points().asIterable()).containsExactly(source)
    }

    @Test
    fun points_yReachesMinValue() {
        val source = Point(-92, Int.MIN_VALUE + 1)
        val ray = Ray(source, slope = null, isPositive = false)
        assertThat(ray.points().asIterable()).containsExactly(source, Point(-92, Int.MIN_VALUE))
    }

    @Test
    fun points_yOvershootsMinValue() {
        val source = Point(39, Int.MIN_VALUE + 1)
        val ray = Ray(source, slope = Fraction.valueOf(2), isPositive = false)
        assertThat(ray.points().asIterable()).containsExactly(source)
    }

    @Test
    fun fromPoints_bothSamePoint() {
        val source = Point(85, -10)
        val member = Point(85, -10)
        assertThrows<IllegalArgumentException> { Ray.fromPoints(source, member) }
    }

    @Test
    fun fromPoints_verticalSlope_positiveDirection() {
        val source = Point(24, -80)
        val member = Point(24, -7)
        assertThat(Ray.fromPoints(source, member))
            .isEqualTo(Ray(source, slope = null, isPositive = true))
    }

    @Test
    fun fromPoints_verticalSlope_negativeDirection() {
        val source = Point(97, 55)
        val member = Point(97, 49)
        assertThat(Ray.fromPoints(source, member))
            .isEqualTo(Ray(source, slope = null, isPositive = false))
    }

    @Test
    fun fromPoints_nonVerticalSlope_positiveDirection() {
        val source = Point(50, 32)
        val member = Point(86, 82)
        assertThat(Ray.fromPoints(source, member))
            .isEqualTo(Ray(source, slope = Fraction.valueOf(25, 18), isPositive = true))
    }

    @Test
    fun fromPoints_nonVerticalSlope_negativeDirection() {
        val source = Point(90, -57)
        val member = Point(18, 1)
        assertThat(Ray.fromPoints(source, member))
            .isEqualTo(Ray(source, slope = Fraction.valueOf(-29, 36), isPositive = false))
    }
}
