package com.curtislb.adventofcode.common.math

import java.math.BigInteger
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [nextMultipleAbove] and [nextMultipleAtLeast].
 */
class NextMultipleTest {
    @Test
    fun testNextMultipleAboveInt() {
        assertEquals(1, 1.nextMultipleAbove(0))
        assertEquals(2, 1.nextMultipleAbove(1))
        assertEquals(3, 1.nextMultipleAbove(2))
        assertEquals(2, 2.nextMultipleAbove(1))
        assertEquals(4, 2.nextMultipleAbove(2))
        assertEquals(4, 2.nextMultipleAbove(3))
        assertEquals(3, 3.nextMultipleAbove(2))
        assertEquals(371, 7.nextMultipleAbove(365))
        assertEquals(365, 365.nextMultipleAbove(42))
        assertEquals(876, 73.nextMultipleAbove(820))
        assertEquals(5280, 264.nextMultipleAbove(5133))
        assertEquals(918144, 6376.nextMultipleAbove(913259))
        assertEquals(595254681, 9448487.nextMultipleAbove(589545477))
    }

    @Test
    fun testNextMultipleAboveWithInvalidInts() {
        assertThrows<IllegalArgumentException> { 0.nextMultipleAbove(0) }
        assertThrows<IllegalArgumentException> { 0.nextMultipleAbove(1) }
        assertThrows<IllegalArgumentException> { 0.nextMultipleAbove(2) }
        assertThrows<IllegalArgumentException> { (-1).nextMultipleAbove(0) }
        assertThrows<IllegalArgumentException> { (-1).nextMultipleAbove(1) }
        assertThrows<IllegalArgumentException> { (-1).nextMultipleAbove(2) }
        assertThrows<IllegalArgumentException> { 1.nextMultipleAbove(-1) }
        assertThrows<IllegalArgumentException> { 2.nextMultipleAbove(-1) }
        assertThrows<IllegalArgumentException> { 1.nextMultipleAbove(-2) }
        assertThrows<IllegalArgumentException> { 2.nextMultipleAbove(-2) }
    }

    @Test
    fun testNextMultipleAboveLong() {
        assertEquals(1L, 1L.nextMultipleAbove(0L))
        assertEquals(2L, 1L.nextMultipleAbove(1L))
        assertEquals(3L, 1L.nextMultipleAbove(2L))
        assertEquals(2L, 2L.nextMultipleAbove(1L))
        assertEquals(4L, 2L.nextMultipleAbove(2L))
        assertEquals(4L, 2L.nextMultipleAbove(3L))
        assertEquals(3L, 3L.nextMultipleAbove(2L))
        assertEquals(371L, 7L.nextMultipleAbove(365L))
        assertEquals(365L, 365L.nextMultipleAbove(42L))
        assertEquals(876L, 73L.nextMultipleAbove(820L))
        assertEquals(5280L, 264L.nextMultipleAbove(5133L))
        assertEquals(918144L, 6376L.nextMultipleAbove(913259L))
        assertEquals(595254681L, 9448487L.nextMultipleAbove(589545477L))
    }

    @Test
    fun testNextMultipleAboveWithInvalidLongs() {
        assertThrows<IllegalArgumentException> { 0L.nextMultipleAbove(0L) }
        assertThrows<IllegalArgumentException> { 0L.nextMultipleAbove(1L) }
        assertThrows<IllegalArgumentException> { 0L.nextMultipleAbove(2L) }
        assertThrows<IllegalArgumentException> { (-1L).nextMultipleAbove(0L) }
        assertThrows<IllegalArgumentException> { (-1L).nextMultipleAbove(1L) }
        assertThrows<IllegalArgumentException> { (-1L).nextMultipleAbove(2L) }
        assertThrows<IllegalArgumentException> { 1L.nextMultipleAbove(-1L) }
        assertThrows<IllegalArgumentException> { 2L.nextMultipleAbove(-1L) }
        assertThrows<IllegalArgumentException> { 1L.nextMultipleAbove(-2L) }
        assertThrows<IllegalArgumentException> { 2L.nextMultipleAbove(-2L) }
    }

    @Test
    fun testNextMultipleAboveBigInteger() {
        assertEquals(BigInteger.ONE, BigInteger.ONE.nextMultipleAbove(BigInteger.ZERO))
        assertEquals(BigInteger.TWO, BigInteger.ONE.nextMultipleAbove(BigInteger.ONE))
        assertEquals(BigInteger("3"), BigInteger.ONE.nextMultipleAbove(BigInteger.TWO))
        assertEquals(BigInteger.TWO, BigInteger.TWO.nextMultipleAbove(BigInteger.ONE))
        assertEquals(BigInteger("4"), BigInteger.TWO.nextMultipleAbove(BigInteger.TWO))
        assertEquals(BigInteger("4"), BigInteger.TWO.nextMultipleAbove(BigInteger("3")))
        assertEquals(BigInteger("3"), BigInteger("3").nextMultipleAbove(BigInteger.TWO))
        assertEquals(BigInteger("371"), BigInteger("7").nextMultipleAbove(BigInteger("365")))
        assertEquals(BigInteger("365"), BigInteger("365").nextMultipleAbove(BigInteger("42")))
        assertEquals(BigInteger("876"), BigInteger("73").nextMultipleAbove(BigInteger("820")))
        assertEquals(BigInteger("5280"), BigInteger("264").nextMultipleAbove(BigInteger("5133")))
        assertEquals(
            BigInteger("918144"),
            BigInteger("6376").nextMultipleAbove(BigInteger("913259"))
        )
        assertEquals(
            BigInteger("595254681"),
            BigInteger("9448487").nextMultipleAbove(BigInteger("589545477"))
        )
    }

    @Test
    fun testNextMultipleAboveWithInvalidBigIntegers() {
        assertThrows<IllegalArgumentException> {
            BigInteger.ZERO.nextMultipleAbove(BigInteger.ZERO)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.ZERO.nextMultipleAbove(BigInteger.ONE)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.ZERO.nextMultipleAbove(BigInteger.TWO)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger("-1").nextMultipleAbove(BigInteger.ZERO)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger("-1").nextMultipleAbove(BigInteger.ONE)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger("-1").nextMultipleAbove(BigInteger.TWO)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.ONE.nextMultipleAbove(BigInteger("-1"))
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.TWO.nextMultipleAbove(BigInteger("-1"))
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.ONE.nextMultipleAbove(BigInteger("-2"))
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.TWO.nextMultipleAbove(BigInteger("-2"))
        }
    }

    @Test
    fun testNextMultipleAtLeastInt() {
        assertEquals(0, 1.nextMultipleAtLeast(0))
        assertEquals(1, 1.nextMultipleAtLeast(1))
        assertEquals(2, 1.nextMultipleAtLeast(2))
        assertEquals(2, 2.nextMultipleAtLeast(1))
        assertEquals(2, 2.nextMultipleAtLeast(2))
        assertEquals(4, 2.nextMultipleAtLeast(3))
        assertEquals(3, 3.nextMultipleAtLeast(2))
        assertEquals(371, 7.nextMultipleAtLeast(365))
        assertEquals(365, 365.nextMultipleAtLeast(42))
        assertEquals(876, 73.nextMultipleAtLeast(820))
        assertEquals(5280, 264.nextMultipleAtLeast(5133))
        assertEquals(918144, 6376.nextMultipleAtLeast(913259))
        assertEquals(595254681, 9448487.nextMultipleAtLeast(589545477))
    }

    @Test
    fun testNextMultipleAtLeastWithInvalidInts() {
        assertThrows<IllegalArgumentException> { 0.nextMultipleAtLeast(0) }
        assertThrows<IllegalArgumentException> { 0.nextMultipleAtLeast(1) }
        assertThrows<IllegalArgumentException> { 0.nextMultipleAtLeast(2) }
        assertThrows<IllegalArgumentException> { (-1).nextMultipleAtLeast(0) }
        assertThrows<IllegalArgumentException> { (-1).nextMultipleAtLeast(1) }
        assertThrows<IllegalArgumentException> { (-1).nextMultipleAtLeast(2) }
        assertThrows<IllegalArgumentException> { 1.nextMultipleAtLeast(-1) }
        assertThrows<IllegalArgumentException> { 2.nextMultipleAtLeast(-1) }
        assertThrows<IllegalArgumentException> { 1.nextMultipleAtLeast(-2) }
        assertThrows<IllegalArgumentException> { 2.nextMultipleAtLeast(-2) }
    }

    @Test
    fun testNextMultipleAtLeastLong() {
        assertEquals(0L, 1L.nextMultipleAtLeast(0L))
        assertEquals(1L, 1L.nextMultipleAtLeast(1L))
        assertEquals(2L, 1L.nextMultipleAtLeast(2L))
        assertEquals(2L, 2L.nextMultipleAtLeast(1L))
        assertEquals(2L, 2L.nextMultipleAtLeast(2L))
        assertEquals(4L, 2L.nextMultipleAtLeast(3L))
        assertEquals(3L, 3L.nextMultipleAtLeast(2L))
        assertEquals(371L, 7L.nextMultipleAtLeast(365L))
        assertEquals(365L, 365L.nextMultipleAtLeast(42L))
        assertEquals(876L, 73L.nextMultipleAtLeast(820L))
        assertEquals(5280L, 264L.nextMultipleAtLeast(5133L))
        assertEquals(918144L, 6376L.nextMultipleAtLeast(913259L))
        assertEquals(595254681L, 9448487L.nextMultipleAtLeast(589545477L))
    }

    @Test
    fun testNextMultipleAtLeastWithInvalidLongs() {
        assertThrows<IllegalArgumentException> { 0L.nextMultipleAtLeast(0L) }
        assertThrows<IllegalArgumentException> { 0L.nextMultipleAtLeast(1L) }
        assertThrows<IllegalArgumentException> { 0L.nextMultipleAtLeast(2L) }
        assertThrows<IllegalArgumentException> { (-1L).nextMultipleAtLeast(0L) }
        assertThrows<IllegalArgumentException> { (-1L).nextMultipleAtLeast(1L) }
        assertThrows<IllegalArgumentException> { (-1L).nextMultipleAtLeast(2L) }
        assertThrows<IllegalArgumentException> { 1L.nextMultipleAtLeast(-1L) }
        assertThrows<IllegalArgumentException> { 2L.nextMultipleAtLeast(-1L) }
        assertThrows<IllegalArgumentException> { 1L.nextMultipleAtLeast(-2L) }
        assertThrows<IllegalArgumentException> { 2L.nextMultipleAtLeast(-2L) }
    }

    @Test
    fun testNextMultipleAtLeastBigInteger() {
        assertEquals(BigInteger.ZERO, BigInteger.ONE.nextMultipleAtLeast(BigInteger.ZERO))
        assertEquals(BigInteger.ONE, BigInteger.ONE.nextMultipleAtLeast(BigInteger.ONE))
        assertEquals(BigInteger.TWO, BigInteger.ONE.nextMultipleAtLeast(BigInteger.TWO))
        assertEquals(BigInteger.TWO, BigInteger.TWO.nextMultipleAtLeast(BigInteger.ONE))
        assertEquals(BigInteger.TWO, BigInteger.TWO.nextMultipleAtLeast(BigInteger.TWO))
        assertEquals(BigInteger("4"), BigInteger.TWO.nextMultipleAtLeast(BigInteger("3")))
        assertEquals(BigInteger("3"), BigInteger("3").nextMultipleAtLeast(BigInteger.TWO))
        assertEquals(BigInteger("371"), BigInteger("7").nextMultipleAtLeast(BigInteger("365")))
        assertEquals(BigInteger("365"), BigInteger("365").nextMultipleAtLeast(BigInteger("42")))
        assertEquals(BigInteger("876"), BigInteger("73").nextMultipleAtLeast(BigInteger("820")))
        assertEquals(BigInteger("5280"), BigInteger("264").nextMultipleAtLeast(BigInteger("5133")))
        assertEquals(
            BigInteger("918144"),
            BigInteger("6376").nextMultipleAtLeast(BigInteger("913259"))
        )
        assertEquals(
            BigInteger("595254681"),
            BigInteger("9448487").nextMultipleAtLeast(BigInteger("589545477"))
        )
    }

    @Test
    fun testNextMultipleAtLeastWithInvalidBigIntegers() {
        assertThrows<IllegalArgumentException> {
            BigInteger.ZERO.nextMultipleAtLeast(BigInteger.ZERO)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.ZERO.nextMultipleAtLeast(BigInteger.ONE)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.ZERO.nextMultipleAtLeast(BigInteger.TWO)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger("-1").nextMultipleAtLeast(BigInteger.ZERO)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger("-1").nextMultipleAtLeast(BigInteger.ONE)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger("-1").nextMultipleAtLeast(BigInteger.TWO)
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.ONE.nextMultipleAtLeast(BigInteger("-1"))
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.TWO.nextMultipleAtLeast(BigInteger("-1"))
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.ONE.nextMultipleAtLeast(BigInteger("-2"))
        }
        assertThrows<IllegalArgumentException> {
            BigInteger.TWO.nextMultipleAtLeast(BigInteger("-2"))
        }
    }
}
