package com.curtislb.adventofcode.common.number

import java.math.BigInteger
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests common functions and utilities related to multiples and divisors.
 */
class MultiplesTest {
    @Test
    fun testGreatestCommonDivisorOfInts() {
        assertEquals(1, greatestCommonDivisor(1, 1))
        assertEquals(1, greatestCommonDivisor(1, 2))
        assertEquals(2, greatestCommonDivisor(2, 2))
        assertEquals(1, greatestCommonDivisor(2, 3))
        assertEquals(2, greatestCommonDivisor(2, 4))
        assertEquals(4, greatestCommonDivisor(20, 16))
        assertEquals(6, greatestCommonDivisor(54, 24))
        assertEquals(9, greatestCommonDivisor(45, 54))
        assertEquals(15, greatestCommonDivisor(30, 105))
        assertEquals(3581, greatestCommonDivisor(452713601, 662853843))
    }

    @Test
    fun testGreatestCommonDivisorOfInvalidInts() {
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(0, 0) }
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(0, 1) }
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(1, 0) }
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(0, 2) }
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(2, 0) }
    }

    @Test
    fun testGreatestCommonDivisorOfLongs() {
        assertEquals(1L, greatestCommonDivisor(1L, 1L))
        assertEquals(1L, greatestCommonDivisor(1L, 2L))
        assertEquals(2L, greatestCommonDivisor(2L, 2L))
        assertEquals(1L, greatestCommonDivisor(2L, 3L))
        assertEquals(2L, greatestCommonDivisor(2L, 4L))
        assertEquals(4L, greatestCommonDivisor(20L, 16L))
        assertEquals(6L, greatestCommonDivisor(54L, 24L))
        assertEquals(9L, greatestCommonDivisor(45L, 54L))
        assertEquals(15L, greatestCommonDivisor(30L, 105L))
        assertEquals(3581L, greatestCommonDivisor(452713601L, 662853843L))
    }

    @Test
    fun testGreatestCommonDivisorOfInvalidLongs() {
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(0L, 0L) }
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(0L, 1L) }
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(1L, 0L) }
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(0L, 2L) }
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(2L, 0L) }
    }

    @Test
    fun testLeastCommonMultipleOfTwoInts() {
        assertEquals(1, leastCommonMultiple(1, 1))
        assertEquals(2, leastCommonMultiple(1, 2))
        assertEquals(2, leastCommonMultiple(2, 1))
        assertEquals(6, leastCommonMultiple(2, 3))
        assertEquals(6, leastCommonMultiple(3, 2))
        assertEquals(4, leastCommonMultiple(2, 4))
        assertEquals(12, leastCommonMultiple(3, 4))
        assertEquals(12, leastCommonMultiple(6, 4))
        assertEquals(42, leastCommonMultiple(6, 21))
        assertEquals(36, leastCommonMultiple(18, 12))
        assertEquals(105, leastCommonMultiple(15, 35))
    }

    @Test
    fun testLeastCommonMultipleOfTwoInvalidInts() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0, 0) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1, 0) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0, -1) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1, -1) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1, 0) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0, 1) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1, -1) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1, 1) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(2, 0) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0, 2) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(2, -1) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1, 2) }
    }

    @Test
    fun testLeastCommonMultipleOfTwoLongs() {
        assertEquals(1L, leastCommonMultiple(1L, 1L))
        assertEquals(2L, leastCommonMultiple(1L, 2L))
        assertEquals(2L, leastCommonMultiple(2L, 1L))
        assertEquals(6L, leastCommonMultiple(2L, 3L))
        assertEquals(6L, leastCommonMultiple(3L, 2L))
        assertEquals(4L, leastCommonMultiple(2L, 4L))
        assertEquals(12L, leastCommonMultiple(3L, 4L))
        assertEquals(12L, leastCommonMultiple(6L, 4L))
        assertEquals(42L, leastCommonMultiple(6L, 21))
        assertEquals(36L, leastCommonMultiple(18L, 12L))
        assertEquals(105L, leastCommonMultiple(15L, 35L))
        assertEquals(20608307442L, leastCommonMultiple(55250154L, 21071889L))
    }

    @Test
    fun testLeastCommonMultipleOfTwoInvalidLongs() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0L, 0L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1L, 0L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0L, -1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1L, -1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 0L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0L, 1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, -1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1L, 1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(2L, 0L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0L, 2L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(2L, -1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1L, 2L) }
    }

    @Test
    fun testLeastCommonMultipleOfSeveralLongs() {
        assertEquals(1L, leastCommonMultiple(1L, 1L, 1L))
        assertEquals(2L, leastCommonMultiple(2L, 1L, 1L))
        assertEquals(64L, leastCommonMultiple(2L, 64L, 8L))
        assertEquals(504L, leastCommonMultiple(8L, 9L, 7L))
        assertEquals(420L, leastCommonMultiple(4L, 7L, 2L, 5L, 3L))
        assertEquals(4871660667720L, leastCommonMultiple(540330L, 424130L, 465962L, 357896L))
    }

    @Test
    fun testLeastCommonMultipleOfSeveralInvalidLongs() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0L, 1L, 2L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 0L, 2L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 2L, 0L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0L, 0L, 1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 0L, 0L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0L, 0L, 0L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 2L, 3L, 0L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 2L, 3L, 4L, 0L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 2L, 3L, 0L, 4L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1L, 1L, 2L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, -1L, 2L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 2L, -1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1L, -1L, 1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, -1L, -1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1L, -1L, -1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 2L, 3L, -1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 2L, 3L, 4L, -1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 2L, 3L, -1L, 4L) }
    }

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
