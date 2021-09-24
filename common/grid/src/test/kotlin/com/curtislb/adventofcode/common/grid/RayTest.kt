package com.curtislb.adventofcode.common.grid

import com.curtislb.adventofcode.common.math.Fraction
import kotlin.test.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Test

/**
 * Tests [Ray].
 */
class RayTest {
    @Test
    fun testConstructWithIdenticalPoints() {
        val point = Point(20, -18)
        assertThrows<IllegalArgumentException> { Ray(point, point) }
    }

    @Test
    fun testConstructWithDistinctPoints() {
        assertEquals(
            Ray(source = Point(16, -6), slope = null, directionParity = true),
            Ray(source = Point(16, -6), member = Point(16, 10))
        )
        assertEquals(
            Ray(source = Point(-14, 18), slope = null, directionParity = false),
            Ray(source = Point(-14, 18), member = Point(-14, 1))
        )
        assertEquals(
            Ray(source = Point(9, 11), slope = Fraction(0), directionParity = true),
            Ray(source = Point(9, 11), member = Point(10, 11))
        )
        assertEquals(
            Ray(source = Point(-8, -20), slope = Fraction(0), directionParity = false),
            Ray(source = Point(-8, -20), member = Point(-16, -20))
        )
        assertEquals(
            Ray(source = Point(-17, -8), slope = Fraction(8, 25), directionParity = true),
            Ray(source = Point(-17, -8), member = Point(8, 0))
        )
        assertEquals(
            Ray(source = Point(-16, -10), slope = Fraction(17, 9), directionParity = true),
            Ray(source = Point(-16, -10), member = Point(-7, 7))
        )
        assertEquals(
            Ray(source = Point(-13, 14), slope = Fraction(-31, 6), directionParity = true),
            Ray(source = Point(-13, 14), member = Point(-7, -17))
        )
        assertEquals(
            Ray(source = Point(-7, -7), slope = Fraction(-1, 3), directionParity = true),
            Ray(source = Point(-7, -7), member = Point(5, -11))
        )
        assertEquals(
            Ray(source = Point(-3, -19), slope = Fraction(-19, 13), directionParity = false),
            Ray(source = Point(-3, -19), member = Point(-16, 0))
        )
        assertEquals(
            Ray(source = Point(12, -13), slope = Fraction(-26, 15), directionParity = false),
            Ray(source = Point(12, -13), member = Point(-3, 13))
        )
        assertEquals(
            Ray(source = Point(6, 10), slope = Fraction(21), directionParity = false),
            Ray(source = Point(6, 10), member = Point(5, -11))
        )
        assertEquals(
            Ray(source = Point(7, 15), slope = Fraction(11, 26), directionParity = false),
            Ray(source = Point(7, 15), member = Point(-19, 4))
        )
    }
}
