package com.curtislb.adventofcode.common.math

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

/**
 * Tests [Fraction].
 */
class FractionTest {
    @Test(expected = IllegalArgumentException::class)
    fun testConstructWithZeroDenominator() {
        Fraction(2, 0)
    }

    @Test fun testConstructFromInts() {
        var fraction = Fraction(0)
        assertEquals(0L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(1)
        assertEquals(1L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(38)
        assertEquals(38L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(0, 2)
        assertEquals(0L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(1, 2)
        assertEquals(1L, fraction.numerator)
        assertEquals(2L, fraction.denominator)

        fraction = Fraction(660, 154)
        assertEquals(30L, fraction.numerator)
        assertEquals(7L, fraction.denominator)

        fraction = Fraction(4, 6)
        assertEquals(2L, fraction.numerator)
        assertEquals(3L, fraction.denominator)

        fraction = Fraction(-4, 6)
        assertEquals(-2L, fraction.numerator)
        assertEquals(3L, fraction.denominator)

        fraction = Fraction(4, -6)
        assertEquals(-2L, fraction.numerator)
        assertEquals(3L, fraction.denominator)

        fraction = Fraction(-4, -6)
        assertEquals(2L, fraction.numerator)
        assertEquals(3L, fraction.denominator)
    }

    @Test fun testConstructFromLongs() {
        var fraction = Fraction(0L)
        assertEquals(0L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(1L)
        assertEquals(1L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(38)
        assertEquals(38L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(0L, 2L)
        assertEquals(0L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(1L, 2L)
        assertEquals(1L, fraction.numerator)
        assertEquals(2L, fraction.denominator)

        fraction = Fraction(660L, 154L)
        assertEquals(30L, fraction.numerator)
        assertEquals(7L, fraction.denominator)

        fraction = Fraction(4L, 6L)
        assertEquals(2L, fraction.numerator)
        assertEquals(3L, fraction.denominator)

        fraction = Fraction(-4L, 6L)
        assertEquals(-2L, fraction.numerator)
        assertEquals(3L, fraction.denominator)

        fraction = Fraction(4L, -6L)
        assertEquals(-2L, fraction.numerator)
        assertEquals(3L, fraction.denominator)

        fraction = Fraction(-4L, -6L)
        assertEquals(2L, fraction.numerator)
        assertEquals(3L, fraction.denominator)

        fraction = Fraction(30171142802624L, 36952357521510L)
        assertEquals(15085571401312L, fraction.numerator)
        assertEquals(18476178760755L, fraction.denominator)
    }

    @Test fun testPlus() {
        var fraction = Fraction(0) + Fraction(0)
        assertEquals(0L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(1) + Fraction(0)
        assertEquals(1L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(1) + Fraction(1)
        assertEquals(2L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(1) + Fraction(-1)
        assertEquals(0L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(2) + Fraction(1)
        assertEquals(3L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(1, 2) + Fraction(1, 3)
        assertEquals(5L, fraction.numerator)
        assertEquals(6L, fraction.denominator)

        fraction = Fraction(1, 2) + Fraction(-1, 3)
        assertEquals(1L, fraction.numerator)
        assertEquals(6L, fraction.denominator)

        fraction = Fraction(-1, 2) + Fraction(1, 3)
        assertEquals(-1L, fraction.numerator)
        assertEquals(6L, fraction.denominator)

        fraction = Fraction(-1, 2) + Fraction(-1, 3)
        assertEquals(-5L, fraction.numerator)
        assertEquals(6L, fraction.denominator)

        fraction = Fraction(7, 11) + Fraction(25, 9)
        assertEquals(338L, fraction.numerator)
        assertEquals(99L, fraction.denominator)

        fraction = Fraction(7, 11) + Fraction(-25, 9)
        assertEquals(-212L, fraction.numerator)
        assertEquals(99L, fraction.denominator)
    }

    @Test fun testMinus() {
        var fraction = Fraction(0) - Fraction(0)
        assertEquals(0L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(1) - Fraction(0)
        assertEquals(1L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(1) - Fraction(1)
        assertEquals(0L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(1) - Fraction(-1)
        assertEquals(2L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(2) - Fraction(1)
        assertEquals(1L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(1, 2) - Fraction(1, 3)
        assertEquals(1L, fraction.numerator)
        assertEquals(6L, fraction.denominator)

        fraction = Fraction(1, 2) - Fraction(-1, 3)
        assertEquals(5L, fraction.numerator)
        assertEquals(6L, fraction.denominator)

        fraction = Fraction(-1, 2) - Fraction(1, 3)
        assertEquals(-5L, fraction.numerator)
        assertEquals(6L, fraction.denominator)

        fraction = Fraction(-1, 2) - Fraction(-1, 3)
        assertEquals(-1L, fraction.numerator)
        assertEquals(6L, fraction.denominator)

        fraction = Fraction(7, 11) - Fraction(25, 9)
        assertEquals(-212L, fraction.numerator)
        assertEquals(99L, fraction.denominator)

        fraction = Fraction(7, 11) - Fraction(-25, 9)
        assertEquals(338L, fraction.numerator)
        assertEquals(99L, fraction.denominator)
    }

    @Test fun testUnaryMinus() {
        var fraction = -Fraction(0)
        assertEquals(0L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = -Fraction(1)
        assertEquals(-1L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = -Fraction(-1)
        assertEquals(1L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = -Fraction(1, 2)
        assertEquals(-1L, fraction.numerator)
        assertEquals(2L, fraction.denominator)

        fraction = -Fraction(-1, 2)
        assertEquals(1L, fraction.numerator)
        assertEquals(2L, fraction.denominator)

        fraction = -Fraction(4, 6)
        assertEquals(-2L, fraction.numerator)
        assertEquals(3L, fraction.denominator)
    }

    @Test fun testTimes() {
        var fraction = Fraction(0) * Fraction(0)
        assertEquals(0L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(1) * Fraction(0)
        assertEquals(0L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(1) * Fraction(1)
        assertEquals(1L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(1) * Fraction(-1)
        assertEquals(-1L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(-1) * Fraction(1)
        assertEquals(-1L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(-1) * Fraction(-1)
        assertEquals(1L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(1, 2) * Fraction(1, 3)
        assertEquals(1L, fraction.numerator)
        assertEquals(6L, fraction.denominator)

        fraction = Fraction(2, 3) * Fraction(7, 5)
        assertEquals(14L, fraction.numerator)
        assertEquals(15L, fraction.denominator)

        fraction = Fraction(-17, 11) * Fraction(21, 40)
        assertEquals(-357L, fraction.numerator)
        assertEquals(440L, fraction.denominator)
    }

    @Test fun testDiv() {
        var fraction = Fraction(0) / Fraction(1)
        assertEquals(0L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(1) / Fraction(1)
        assertEquals(1L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(1) / Fraction(-1)
        assertEquals(-1L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(-1) / Fraction(1)
        assertEquals(-1L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(-1) / Fraction(-1)
        assertEquals(1L, fraction.numerator)
        assertEquals(1L, fraction.denominator)

        fraction = Fraction(1, 2) / Fraction(1, 3)
        assertEquals(3L, fraction.numerator)
        assertEquals(2L, fraction.denominator)

        fraction = Fraction(2, 3) / Fraction(7, 5)
        assertEquals(10L, fraction.numerator)
        assertEquals(21L, fraction.denominator)

        fraction = Fraction(-17, 11) / Fraction(21, 40)
        assertEquals(-680L, fraction.numerator)
        assertEquals(231L, fraction.denominator)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testDivByZero() {
        Fraction(1, 2) / Fraction(0)
    }

    @Test fun testCeil() {
        assertEquals(0L, Fraction(0).ceil())
        assertEquals(1L, Fraction(1).ceil())
        assertEquals(2L, Fraction(2).ceil())
        assertEquals(-1L, Fraction(-1).ceil())
        assertEquals(-2L, Fraction(-2).ceil())
        assertEquals(1L, Fraction(1, 2).ceil())
        assertEquals(1L, Fraction(4, 5).ceil())
        assertEquals(1L, Fraction(1, 1000).ceil())
        assertEquals(0L, Fraction(-1, 2).ceil())
        assertEquals(0L, Fraction(-4, 5).ceil())
        assertEquals(0L, Fraction(-1, 1000).ceil())
        assertEquals(2L, Fraction(6, 5).ceil())
        assertEquals(0L, Fraction(-3, 13).ceil())
        assertEquals(2L, Fraction(3, 2).ceil())
        assertEquals(3L, Fraction(11, 5).ceil())
        assertEquals(-2L, Fraction(-19, 9).ceil())
        assertEquals(-6L, Fraction(-13, 2).ceil())
        assertEquals(7L, Fraction(13, 2).ceil())
        assertEquals(334L, Fraction(1000, 3).ceil())
        assertEquals(-333L, Fraction(-1000, 3).ceil())
    }

    @Test fun testFloor() {
        assertEquals(0L, Fraction(0).floor())
        assertEquals(1L, Fraction(1).floor())
        assertEquals(2L, Fraction(2).floor())
        assertEquals(-1L, Fraction(-1).floor())
        assertEquals(-2L, Fraction(-2).floor())
        assertEquals(0L, Fraction(1, 2).floor())
        assertEquals(0L, Fraction(4, 5).floor())
        assertEquals(0L, Fraction(1, 1000).floor())
        assertEquals(-1L, Fraction(-1, 2).floor())
        assertEquals(-1L, Fraction(-4, 5).floor())
        assertEquals(-1L, Fraction(-1, 1000).floor())
        assertEquals(1L, Fraction(6, 5).floor())
        assertEquals(-1L, Fraction(-3, 13).floor())
        assertEquals(1L, Fraction(3, 2).floor())
        assertEquals(2L, Fraction(11, 5).floor())
        assertEquals(-3L, Fraction(-19, 9).floor())
        assertEquals(-7L, Fraction(-13, 2).floor())
        assertEquals(6L, Fraction(13, 2).floor())
        assertEquals(333L, Fraction(1000, 3).floor())
        assertEquals(-334L, Fraction(-1000, 3).floor())
    }

    @Test fun testEquals() {
        assertEquals(Fraction(0), Fraction(0))
        assertEquals(Fraction(-1), Fraction(-1))
        assertEquals(Fraction(1, 2), Fraction(1, 2))
        assertEquals(Fraction(1, 2), Fraction(2, 4))
        assertEquals(Fraction(2, 4), Fraction(1, 2))
        assertEquals(Fraction(1, 2), Fraction(123, 246))
        assertEquals(Fraction(2, 4), Fraction(123, 246))
        assertEquals(Fraction(-3, 5), Fraction(3, -5))
        assertEquals(Fraction(3, 5), Fraction(-3, -5))

        assertNotEquals(Fraction(0), Fraction(1))
        assertNotEquals(Fraction(1), Fraction(-1))
        assertNotEquals(Fraction(1, 2), Fraction(2, 1))
        assertNotEquals(Fraction(1, 2), Fraction(2, 3))
        assertNotEquals(Fraction(2, 4), Fraction(6, 8))
        assertNotEquals(Fraction(1, 2), Fraction(123, 456))
        assertNotEquals(Fraction(3, 5), Fraction(3, -5))
        assertNotEquals(Fraction(-3, 5), Fraction(-3, -5))
    }

    @Test fun testHashCode() {
        val fractions = listOf(
            Fraction(11, 8),
            Fraction(-17, -13),
            Fraction(20, -3),
            Fraction(11, 18),
            Fraction(-7, 7),
            Fraction(32, -20),
            Fraction(-17, -1),
            Fraction(13, 10),
            Fraction(10, 18),
            Fraction(-4, 11),
            Fraction(2, -12)
        )
        val hashMap = HashMap<Fraction, Int>()
        fractions.forEachIndexed { index, fraction -> hashMap[fraction] = index }
        fractions.forEachIndexed { index, fraction -> assertEquals(index, hashMap[fraction]) }
    }

    @Test fun testToString() {
        assertEquals("0/1", Fraction(0).toString())
        assertEquals("1/1", Fraction(1).toString())
        assertEquals("-1/1", Fraction(-1).toString())
        assertEquals("2/1", Fraction(2).toString())
        assertEquals("1/2", Fraction(1, 2).toString())
        assertEquals("-1/2", Fraction(-1, 2).toString())
        assertEquals("2/3", Fraction(2, 3).toString())
        assertEquals("2/3", Fraction(4, 6).toString())
        assertEquals("5/4", Fraction(5, 4).toString())
    }
}
