package com.curtislb.adventofcode.common.math

import java.math.BigInteger
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [prevMultipleAtMost] and [prevMultipleBelow].
 */
class PrevMultipleTest {
    @Test
    fun testPrevMultipleAtMostInt() {
        assertEquals(0, 1.prevMultipleAtMost(0))
        assertEquals(1, 1.prevMultipleAtMost(1))
        assertEquals(2, 1.prevMultipleAtMost(2))
        assertEquals(3, 1.prevMultipleAtMost(3))
        assertEquals(0, 2.prevMultipleAtMost(1))
        assertEquals(2, 2.prevMultipleAtMost(2))
        assertEquals(2, 2.prevMultipleAtMost(3))
        assertEquals(4, 2.prevMultipleAtMost(4))
        assertEquals(0, 3.prevMultipleAtMost(2))
        assertEquals(3, 3.prevMultipleAtMost(3))
        assertEquals(3, 3.prevMultipleAtMost(4))
        assertEquals(3, 3.prevMultipleAtMost(5))
        assertEquals(6, 3.prevMultipleAtMost(6))
        assertEquals(6, 3.prevMultipleAtMost(7))
        assertEquals(357, 7.prevMultipleAtMost(363))
        assertEquals(364, 7.prevMultipleAtMost(364))
        assertEquals(364, 7.prevMultipleAtMost(365))
        assertEquals(336, 42.prevMultipleAtMost(365))
        assertEquals(803, 73.prevMultipleAtMost(820))
        assertEquals(5016, 264.prevMultipleAtMost(5133))
        assertEquals(911768, 6376.prevMultipleAtMost(913259))
        assertEquals(585806194, 9448487.prevMultipleAtMost(589545477))
    }

    @Test
    fun testPrevMultipleAtMostWithInvalidInts() {
        assertThrows<IllegalArgumentException> { 0.prevMultipleAtMost(0) }
        assertThrows<IllegalArgumentException> { 0.prevMultipleAtMost(1) }
        assertThrows<IllegalArgumentException> { 0.prevMultipleAtMost(2) }
        assertThrows<IllegalArgumentException> { (-1).prevMultipleAtMost(0) }
        assertThrows<IllegalArgumentException> { (-1).prevMultipleAtMost(1) }
        assertThrows<IllegalArgumentException> { (-1).prevMultipleAtMost(2) }
        assertThrows<IllegalArgumentException> { 1.prevMultipleAtMost(-1) }
        assertThrows<IllegalArgumentException> { 2.prevMultipleAtMost(-1) }
        assertThrows<IllegalArgumentException> { 3.prevMultipleAtMost(-1) }
        assertThrows<IllegalArgumentException> { 1.prevMultipleAtMost(-2) }
        assertThrows<IllegalArgumentException> { 2.prevMultipleAtMost(-2) }
        assertThrows<IllegalArgumentException> { 3.prevMultipleAtMost(-2) }
    }

    @Test
    fun testPrevMultipleAtMostLong() {
        assertEquals(0L, 1L.prevMultipleAtMost(0L))
        assertEquals(1L, 1L.prevMultipleAtMost(1L))
        assertEquals(2L, 1L.prevMultipleAtMost(2L))
        assertEquals(3L, 1L.prevMultipleAtMost(3L))
        assertEquals(0L, 2L.prevMultipleAtMost(1L))
        assertEquals(2L, 2L.prevMultipleAtMost(2L))
        assertEquals(2L, 2L.prevMultipleAtMost(3L))
        assertEquals(4L, 2L.prevMultipleAtMost(4L))
        assertEquals(0L, 3L.prevMultipleAtMost(2L))
        assertEquals(3L, 3L.prevMultipleAtMost(3L))
        assertEquals(3L, 3L.prevMultipleAtMost(4L))
        assertEquals(3L, 3L.prevMultipleAtMost(5L))
        assertEquals(6L, 3L.prevMultipleAtMost(6L))
        assertEquals(6L, 3L.prevMultipleAtMost(7L))
        assertEquals(357L, 7L.prevMultipleAtMost(363L))
        assertEquals(364L, 7L.prevMultipleAtMost(364L))
        assertEquals(364L, 7L.prevMultipleAtMost(365L))
        assertEquals(336L, 42L.prevMultipleAtMost(365L))
        assertEquals(803L, 73L.prevMultipleAtMost(820L))
        assertEquals(5016L, 264L.prevMultipleAtMost(5133L))
        assertEquals(911768L, 6376L.prevMultipleAtMost(913259L))
        assertEquals(585806194L, 9448487L.prevMultipleAtMost(589545477L))
    }

    @Test
    fun testPrevMultipleAtMostWithInvalidLongs() {
        assertThrows<IllegalArgumentException> { 0L.prevMultipleAtMost(0L) }
        assertThrows<IllegalArgumentException> { 0L.prevMultipleAtMost(1L) }
        assertThrows<IllegalArgumentException> { 0L.prevMultipleAtMost(2L) }
        assertThrows<IllegalArgumentException> { (-1L).prevMultipleAtMost(0L) }
        assertThrows<IllegalArgumentException> { (-1L).prevMultipleAtMost(1L) }
        assertThrows<IllegalArgumentException> { (-1L).prevMultipleAtMost(2L) }
        assertThrows<IllegalArgumentException> { 1L.prevMultipleAtMost(-1L) }
        assertThrows<IllegalArgumentException> { 2L.prevMultipleAtMost(-1L) }
        assertThrows<IllegalArgumentException> { 3L.prevMultipleAtMost(-1L) }
        assertThrows<IllegalArgumentException> { 1L.prevMultipleAtMost(-2L) }
        assertThrows<IllegalArgumentException> { 2L.prevMultipleAtMost(-2L) }
        assertThrows<IllegalArgumentException> { 3L.prevMultipleAtMost(-2L) }
    }

    @Test
    fun testPrevMultipleAtMostBigInteger() {
        assertEquals(BigInteger.ZERO, BigInteger.ONE.prevMultipleAtMost(BigInteger.ZERO))
        assertEquals(BigInteger.ONE, BigInteger.ONE.prevMultipleAtMost(BigInteger.ONE))
        assertEquals(BigInteger.TWO, BigInteger.ONE.prevMultipleAtMost(BigInteger.TWO))
        assertEquals(BigInteger("3"), BigInteger.ONE.prevMultipleAtMost(BigInteger("3")))
        assertEquals(BigInteger.ZERO, BigInteger.TWO.prevMultipleAtMost(BigInteger.ONE))
        assertEquals(BigInteger.TWO, BigInteger.TWO.prevMultipleAtMost(BigInteger.TWO))
        assertEquals(BigInteger.TWO, BigInteger.TWO.prevMultipleAtMost(BigInteger("3")))
        assertEquals(BigInteger("4"), BigInteger.TWO.prevMultipleAtMost(BigInteger("4")))
        assertEquals(BigInteger.ZERO, BigInteger("3").prevMultipleAtMost(BigInteger.TWO))
        assertEquals(BigInteger("3"), BigInteger("3").prevMultipleAtMost(BigInteger("3")))
        assertEquals(BigInteger("3"), BigInteger("3").prevMultipleAtMost(BigInteger("4")))
        assertEquals(BigInteger("3"), BigInteger("3").prevMultipleAtMost(BigInteger("5")))
        assertEquals(BigInteger("6"), BigInteger("3").prevMultipleAtMost(BigInteger("6")))
        assertEquals(BigInteger("6"), BigInteger("3").prevMultipleAtMost(BigInteger("7")))
        assertEquals(BigInteger("357"), BigInteger("7").prevMultipleAtMost(BigInteger("363")))
        assertEquals(BigInteger("364"), BigInteger("7").prevMultipleAtMost(BigInteger("364")))
        assertEquals(BigInteger("364"), BigInteger("7").prevMultipleAtMost(BigInteger("365")))
        assertEquals(BigInteger("336"), BigInteger("42").prevMultipleAtMost(BigInteger("365")))
        assertEquals(BigInteger("803"), BigInteger("73").prevMultipleAtMost(BigInteger("820")))
        assertEquals(BigInteger("5016"), BigInteger("264").prevMultipleAtMost(BigInteger("5133")))
        assertEquals(
            BigInteger("911768"),
            BigInteger("6376").prevMultipleAtMost(BigInteger("913259"))
        )
        assertEquals(
            BigInteger("585806194"),
            BigInteger("9448487").prevMultipleAtMost(BigInteger("589545477"))
        )
    }

    @Test
    fun testPrevMultipleAtMostWithInvalidBigIntegers() {
        assertThrows<IllegalArgumentException> {
            BigInteger.ZERO.prevMultipleAtMost(BigInteger.ZERO)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.ZERO.prevMultipleAtMost(BigInteger.ONE)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.ZERO.prevMultipleAtMost(BigInteger.TWO)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger("-1").prevMultipleAtMost(BigInteger.ZERO)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger("-1").prevMultipleAtMost(BigInteger.ONE)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger("-1").prevMultipleAtMost(BigInteger.TWO)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.ONE.prevMultipleAtMost(BigInteger("-1"))
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.TWO.prevMultipleAtMost(BigInteger("-1"))
        }
        assertThrows<IllegalArgumentException> {
            BigInteger("3").prevMultipleAtMost(BigInteger("-1"))
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.ONE.prevMultipleAtMost(BigInteger("-2"))
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.TWO.prevMultipleAtMost(BigInteger("-2"))
        }
        assertThrows<IllegalArgumentException> {
            BigInteger("3").prevMultipleAtMost(BigInteger("-2"))
        }
    }

    @Test
    fun testPrevMultipleBelowInt() {
        assertEquals(-1, 1.prevMultipleBelow(0))
        assertEquals(0, 1.prevMultipleBelow(1))
        assertEquals(1, 1.prevMultipleBelow(2))
        assertEquals(2, 1.prevMultipleBelow(3))
        assertEquals(0, 2.prevMultipleBelow(1))
        assertEquals(0, 2.prevMultipleBelow(2))
        assertEquals(2, 2.prevMultipleBelow(3))
        assertEquals(2, 2.prevMultipleBelow(4))
        assertEquals(0, 3.prevMultipleBelow(2))
        assertEquals(0, 3.prevMultipleBelow(3))
        assertEquals(3, 3.prevMultipleBelow(4))
        assertEquals(3, 3.prevMultipleBelow(5))
        assertEquals(3, 3.prevMultipleBelow(6))
        assertEquals(6, 3.prevMultipleBelow(7))
        assertEquals(357, 7.prevMultipleBelow(363))
        assertEquals(357, 7.prevMultipleBelow(364))
        assertEquals(364, 7.prevMultipleBelow(365))
        assertEquals(336, 42.prevMultipleBelow(365))
        assertEquals(803, 73.prevMultipleBelow(820))
        assertEquals(5016, 264.prevMultipleBelow(5133))
        assertEquals(911768, 6376.prevMultipleBelow(913259))
        assertEquals(585806194, 9448487.prevMultipleBelow(589545477))
    }

    @Test
    fun testPrevMultipleBelowWithInvalidInts() {
        assertThrows<IllegalArgumentException> { 0.prevMultipleBelow(0) }
        assertThrows<IllegalArgumentException> { 0.prevMultipleBelow(1) }
        assertThrows<IllegalArgumentException> { 0.prevMultipleBelow(2) }
        assertThrows<IllegalArgumentException> { (-1).prevMultipleBelow(0) }
        assertThrows<IllegalArgumentException> { (-1).prevMultipleBelow(1) }
        assertThrows<IllegalArgumentException> { (-1).prevMultipleBelow(2) }
        assertThrows<IllegalArgumentException> { 1.prevMultipleBelow(-1) }
        assertThrows<IllegalArgumentException> { 2.prevMultipleBelow(-1) }
        assertThrows<IllegalArgumentException> { 3.prevMultipleBelow(-1) }
        assertThrows<IllegalArgumentException> { 1.prevMultipleBelow(-2) }
        assertThrows<IllegalArgumentException> { 2.prevMultipleBelow(-2) }
        assertThrows<IllegalArgumentException> { 3.prevMultipleBelow(-2) }
    }

    @Test
    fun testPrevMultipleBelowLong() {
        assertEquals(-1L, 1L.prevMultipleBelow(0L))
        assertEquals(0L, 1L.prevMultipleBelow(1L))
        assertEquals(1L, 1L.prevMultipleBelow(2L))
        assertEquals(2L, 1L.prevMultipleBelow(3L))
        assertEquals(0L, 2L.prevMultipleBelow(1L))
        assertEquals(0L, 2L.prevMultipleBelow(2L))
        assertEquals(2L, 2L.prevMultipleBelow(3L))
        assertEquals(2L, 2L.prevMultipleBelow(4L))
        assertEquals(0L, 3L.prevMultipleBelow(2L))
        assertEquals(0L, 3L.prevMultipleBelow(3L))
        assertEquals(3L, 3L.prevMultipleBelow(4L))
        assertEquals(3L, 3L.prevMultipleBelow(5L))
        assertEquals(3L, 3L.prevMultipleBelow(6L))
        assertEquals(6L, 3L.prevMultipleBelow(7L))
        assertEquals(357L, 7L.prevMultipleBelow(363L))
        assertEquals(357L, 7L.prevMultipleBelow(364L))
        assertEquals(364L, 7L.prevMultipleBelow(365L))
        assertEquals(336L, 42L.prevMultipleBelow(365L))
        assertEquals(803L, 73L.prevMultipleBelow(820L))
        assertEquals(5016L, 264L.prevMultipleBelow(5133L))
        assertEquals(911768L, 6376L.prevMultipleBelow(913259L))
        assertEquals(585806194L, 9448487L.prevMultipleBelow(589545477L))
    }

    @Test
    fun testPrevMultipleBelowWithInvalidLongs() {
        assertThrows<IllegalArgumentException> { 0L.prevMultipleBelow(0L) }
        assertThrows<IllegalArgumentException> { 0L.prevMultipleBelow(1L) }
        assertThrows<IllegalArgumentException> { 0L.prevMultipleBelow(2L) }
        assertThrows<IllegalArgumentException> { (-1L).prevMultipleBelow(0L) }
        assertThrows<IllegalArgumentException> { (-1L).prevMultipleBelow(1L) }
        assertThrows<IllegalArgumentException> { (-1L).prevMultipleBelow(2L) }
        assertThrows<IllegalArgumentException> { 1L.prevMultipleBelow(-1L) }
        assertThrows<IllegalArgumentException> { 2L.prevMultipleBelow(-1L) }
        assertThrows<IllegalArgumentException> { 3L.prevMultipleBelow(-1L) }
        assertThrows<IllegalArgumentException> { 1L.prevMultipleBelow(-2L) }
        assertThrows<IllegalArgumentException> { 2L.prevMultipleBelow(-2L) }
        assertThrows<IllegalArgumentException> { 3L.prevMultipleBelow(-2L) }
    }

    @Test
    fun testPrevMultipleBelowBigInteger() {
        assertEquals(BigInteger("-1"), BigInteger.ONE.prevMultipleBelow(BigInteger.ZERO))
        assertEquals(BigInteger.ZERO, BigInteger.ONE.prevMultipleBelow(BigInteger.ONE))
        assertEquals(BigInteger.ONE, BigInteger.ONE.prevMultipleBelow(BigInteger.TWO))
        assertEquals(BigInteger.TWO, BigInteger.ONE.prevMultipleBelow(BigInteger("3")))
        assertEquals(BigInteger.ZERO, BigInteger.TWO.prevMultipleBelow(BigInteger.ONE))
        assertEquals(BigInteger.ZERO, BigInteger.TWO.prevMultipleBelow(BigInteger.TWO))
        assertEquals(BigInteger.TWO, BigInteger.TWO.prevMultipleBelow(BigInteger("3")))
        assertEquals(BigInteger.TWO, BigInteger.TWO.prevMultipleBelow(BigInteger("4")))
        assertEquals(BigInteger.ZERO, BigInteger("3").prevMultipleBelow(BigInteger.TWO))
        assertEquals(BigInteger.ZERO, BigInteger("3").prevMultipleBelow(BigInteger("3")))
        assertEquals(BigInteger("3"), BigInteger("3").prevMultipleBelow(BigInteger("4")))
        assertEquals(BigInteger("3"), BigInteger("3").prevMultipleBelow(BigInteger("5")))
        assertEquals(BigInteger("3"), BigInteger("3").prevMultipleBelow(BigInteger("6")))
        assertEquals(BigInteger("6"), BigInteger("3").prevMultipleBelow(BigInteger("7")))
        assertEquals(BigInteger("357"), BigInteger("7").prevMultipleBelow(BigInteger("363")))
        assertEquals(BigInteger("357"), BigInteger("7").prevMultipleBelow(BigInteger("364")))
        assertEquals(BigInteger("364"), BigInteger("7").prevMultipleBelow(BigInteger("365")))
        assertEquals(BigInteger("336"), BigInteger("42").prevMultipleBelow(BigInteger("365")))
        assertEquals(BigInteger("803"), BigInteger("73").prevMultipleBelow(BigInteger("820")))
        assertEquals(BigInteger("5016"), BigInteger("264").prevMultipleBelow(BigInteger("5133")))
        assertEquals(
            BigInteger("911768"),
            BigInteger("6376").prevMultipleBelow(BigInteger("913259"))
        )
        assertEquals(
            BigInteger("585806194"),
            BigInteger("9448487").prevMultipleBelow(BigInteger("589545477"))
        )
    }

    @Test
    fun testPrevMultipleBelowWithInvalidBigIntegers() {
        assertThrows<IllegalArgumentException> {
            BigInteger.ZERO.prevMultipleBelow(BigInteger.ZERO)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.ZERO.prevMultipleBelow(BigInteger.ONE)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.ZERO.prevMultipleBelow(BigInteger.TWO)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger("-1").prevMultipleBelow(BigInteger.ZERO)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger("-1").prevMultipleBelow(BigInteger.ONE)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger("-1").prevMultipleBelow(BigInteger.TWO)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.ONE.prevMultipleBelow(BigInteger("-1"))
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.TWO.prevMultipleBelow(BigInteger("-1"))
        }
        assertThrows<IllegalArgumentException> {
            BigInteger("3").prevMultipleBelow(BigInteger("-1"))
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.ONE.prevMultipleBelow(BigInteger("-2"))
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.TWO.prevMultipleBelow(BigInteger("-2"))
        }
        assertThrows<IllegalArgumentException> {
            BigInteger("3").prevMultipleBelow(BigInteger("-2"))
        }
    }
}
