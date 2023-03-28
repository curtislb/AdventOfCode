package com.curtislb.adventofcode.common.geometry

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests the [Point3D] class.
 */
class Point3DTest {
    @Test
    fun plus_bothOrigin() {
        val point = Point3D.ORIGIN
        val other = Point3D.ORIGIN
        assertThat(point + other).isEqualTo(Point3D.ORIGIN)
    }

    @Test
    fun plus_leftOrigin() {
        val point = Point3D.ORIGIN
        val other = Point3D(-71, 52, -26)
        assertThat(point + other).isEqualTo(other)
    }

    @Test
    fun plus_rightOrigin() {
        val point = Point3D(-23, -40, 80)
        val other = Point3D.ORIGIN
        assertThat(point + other).isEqualTo(point)
    }

    @Test
    fun plus_bothNotOrigin() {
        val point = Point3D(-42, -30, -41)
        val other = Point3D(-93, 29, 49)
        assertThat(point + other).isEqualTo(Point3D(-135, -1, 8))
    }

    @Test
    fun minus_bothOrigin() {
        val point = Point3D.ORIGIN
        val other = Point3D.ORIGIN
        assertThat(point - other).isEqualTo(Point3D.ORIGIN)
    }

    @Test
    fun minus_leftOrigin() {
        val point = Point3D.ORIGIN
        val other = Point3D(-90, -91, 53)
        assertThat(point - other).isEqualTo(Point3D(90, 91, -53))
    }

    @Test
    fun minus_rightOrigin() {
        val point = Point3D(-3, 74, -97)
        val other = Point3D.ORIGIN
        assertThat(point - other).isEqualTo(point)
    }

    @Test
    fun minus_bothNotOrigin() {
        val point = Point3D(76, 61, 45)
        val other = Point3D(64, 65, -91)
        assertThat(point - other).isEqualTo(Point3D(12, -4, 136))
    }

    @Test
    fun unaryMinus_whenOrigin() {
        val point = Point3D.ORIGIN
        assertThat(-point).isEqualTo(Point3D.ORIGIN)
    }

    @Test
    fun unaryMinus_whenNotOrigin() {
        val point = Point3D(-19, -33, 69)
        assertThat(-point).isEqualTo(Point3D(19, 33, -69))
    }

    @Test
    fun manhattanDistance_bothOrigin() {
        val point = Point3D.ORIGIN
        val other = Point3D.ORIGIN
        assertThat(point manhattanDistance other).isEqualTo(0)
    }

    @Test
    fun manhattanDistance_leftOrigin() {
        val point = Point3D.ORIGIN
        val other = Point3D(20, 96, -63)
        assertThat(point manhattanDistance other).isEqualTo(179)
    }

    @Test
    fun manhattanDistance_rightOrigin() {
        val point = Point3D(29, -6, 16)
        val other = Point3D.ORIGIN
        assertThat(point manhattanDistance other).isEqualTo(51)
    }

    @Test
    fun manhattanDistance_bothNotOrigin() {
        val point = Point3D(79, -44, 73)
        val other = Point3D(71, 85, -14)
        assertThat(point manhattanDistance other).isEqualTo(224)
    }

    @Test
    fun toString_whenOrigin() {
        val point = Point3D.ORIGIN
        assertThat(point.toString()).isEqualTo("(0, 0, 0)")
    }

    @Test
    fun toString_whenNotOrigin() {
        val point = Point3D(-65, 98, 32)
        assertThat(point.toString()).isEqualTo("(-65, 98, 32)")
    }

    @Test
    fun fromString_origin_noParens_noSpace() {
        val string = "0,0,0"
        val point = Point3D.fromString(string)
        assertThat(point).isEqualTo(Point3D.ORIGIN)
    }

    @Test
    fun fromString_origin_noParens_withSpace() {
        val string = "0, 0, 0"
        val point = Point3D.fromString(string)
        assertThat(point).isEqualTo(Point3D.ORIGIN)
    }

    @Test
    fun fromString_origin_withParens_noSpace() {
        val string = "(0,0,0)"
        val point = Point3D.fromString(string)
        assertThat(point).isEqualTo(Point3D.ORIGIN)
    }

    @Test
    fun fromString_origin_withParens_withSpace() {
        val string = "(0, 0, 0)"
        val point = Point3D.fromString(string)
        assertThat(point).isEqualTo(Point3D.ORIGIN)
    }

    @Test
    fun fromString_xyZero_noParens_noSpace() {
        val string = "0,0,84"
        val point = Point3D.fromString(string)
        assertThat(point).isEqualTo(Point3D(0, 0, 84))
    }

    @Test
    fun fromString_xyZero_noParens_withSpace() {
        val string = "0, 0, -31"
        val point = Point3D.fromString(string)
        assertThat(point).isEqualTo(Point3D(0, 0, -31))
    }

    @Test
    fun fromString_xyZero_withParens_noSpace() {
        val string = "(0,0,-83)"
        val point = Point3D.fromString(string)
        assertThat(point).isEqualTo(Point3D(0, 0, -83))
    }

    @Test
    fun fromString_xyZero_withParens_withSpace() {
        val string = "(0, 0, 92)"
        val point = Point3D.fromString(string)
        assertThat(point).isEqualTo(Point3D(0, 0, 92))
    }


    @Test
    fun fromString_xZero_noParens_noSpace() {
        val string = "0,32,-76"
        val point = Point3D.fromString(string)
        assertThat(point).isEqualTo(Point3D(0, 32, -76))
    }

    @Test
    fun fromString_xZero_noParens_withSpace() {
        val string = "0, -17, 64"
        val point = Point3D.fromString(string)
        assertThat(point).isEqualTo(Point3D(0, -17, 64))
    }

    @Test
    fun fromString_xZero_withParens_noSpace() {
        val string = "(0,14,46)"
        val point = Point3D.fromString(string)
        assertThat(point).isEqualTo(Point3D(0, 14, 46))
    }

    @Test
    fun fromString_xZero_withParens_withSpace() {
        val string = "(0, 72, -85)"
        val point = Point3D.fromString(string)
        assertThat(point).isEqualTo(Point3D(0, 72, -85))
    }

    @Test
    fun fromString_xyzNonzero_noParens_noSpace() {
        val string = "50,14,-95"
        val point = Point3D.fromString(string)
        assertThat(point).isEqualTo(Point3D(50, 14, -95))
    }

    @Test
    fun fromString_xyzNonzero_noParens_withSpace() {
        val string = "-84, 68, -35"
        val point = Point3D.fromString(string)
        assertThat(point).isEqualTo(Point3D(-84, 68, -35))
    }

    @Test
    fun fromString_xyzNonzero_withParens_noSpace() {
        val string = "(55,88,30)"
        val point = Point3D.fromString(string)
        assertThat(point).isEqualTo(Point3D(55, 88, 30))
    }

    @Test
    fun fromString_xyzNonzero_withParens_withSpace() {
        val string = "(-65, -77, -1)"
        val point = Point3D.fromString(string)
        assertThat(point).isEqualTo(Point3D(-65, -77, -1))
    }

    @Test
    fun fromString_wrongFormat() {
        val string = " (-92, -62, -43)"
        assertThrows<IllegalArgumentException> { Point3D.fromString(string) }
    }
}
