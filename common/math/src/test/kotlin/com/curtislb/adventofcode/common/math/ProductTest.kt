package com.curtislb.adventofcode.common.math

import java.math.BigInteger
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests [product].
 */
class ProductTest {
    @Test
    fun testProductOfInts() {
        assertEquals(1, emptyList<Int>().product())
        assertEquals(0, listOf(0).product())
        assertEquals(1, listOf(1).product())
        assertEquals(2, listOf(2).product())
        assertEquals(-1, listOf(-1).product())
        assertEquals(2, listOf(1, 2).product())
        assertEquals(-2, listOf(-1, 2).product())
        assertEquals(6, listOf(2, 3).product())
        assertEquals(-6, listOf(2, -3).product())
        assertEquals(30, listOf(2, 3, 5).product())
        assertEquals(-30, listOf(2, -3, 5).product())
        assertEquals(30, listOf(-2, 3, -5).product())
        assertEquals(-7804188, listOf(-21, -3, -93, 36, 37).product())
        assertEquals(31104000, listOf(12, -3, 3, -5, -10, 8, 12, -15, 4, 1).product())
    }

    @Test
    fun testProductOfLongs() {
        assertEquals(1L, emptyList<Long>().product())
        assertEquals(0L, listOf(0L).product())
        assertEquals(1L, listOf(1L).product())
        assertEquals(2L, listOf(2L).product())
        assertEquals(-1L, listOf(-1L).product())
        assertEquals(2L, listOf(1L, 2L).product())
        assertEquals(-2L, listOf(-1L, 2L).product())
        assertEquals(6L, listOf(2L, 3L).product())
        assertEquals(-6L, listOf(2L, -3L).product())
        assertEquals(30L, listOf(2L, 3L, 5L).product())
        assertEquals(-30L, listOf(2L, -3L, 5L).product())
        assertEquals(30L, listOf(-2L, 3L, -5L).product())
        assertEquals(-7804188L, listOf(-21L, -3L, -93L, 36L, 37L).product())
        assertEquals(31104000L, listOf(12L, -3L, 3L, -5L, -10L, 8L, 12L, -15L, 4L, 1L).product())
        assertEquals(
            -2116664524800L,
            listOf(-49L, -31L, 3L, -2L, -32L, 16L, 32L, -35L, -45L, -9L).product()
        )
    }

    @Test
    fun testProductOfBigIntegers() {
        assertEquals(BigInteger("1"), emptyList<BigInteger>().product())
        assertEquals(BigInteger("0"), listOf(BigInteger.ZERO).product())
        assertEquals(BigInteger("1"), listOf(BigInteger.ONE).product())
        assertEquals(BigInteger("2"), listOf(BigInteger.TWO).product())
        assertEquals(BigInteger("-1"), listOf(BigInteger("-1")).product())
        assertEquals(BigInteger("2"), listOf(BigInteger.ONE, BigInteger.TWO).product())
        assertEquals(BigInteger("-2"), listOf(BigInteger("-1"), BigInteger.TWO).product())
        assertEquals(BigInteger("6"), listOf(BigInteger.TWO, BigInteger("3")).product())
        assertEquals(BigInteger("-6"), listOf(BigInteger.TWO, BigInteger("-3")).product())
        assertEquals(
            BigInteger("30"),
            listOf(BigInteger.TWO, BigInteger("3"), BigInteger("5")).product()
        )
        assertEquals(
            BigInteger("-30"),
            listOf(BigInteger.TWO, BigInteger("-3"), BigInteger("5")).product()
        )
        assertEquals(
            BigInteger("30"),
            listOf(BigInteger("-2"), BigInteger("3"), BigInteger("-5")).product()
        )
        assertEquals(
            BigInteger("-7804188"),
            listOf(-21, -3, -93, 36, 37).map { it.toBigInteger() }.product()
        )
        assertEquals(
            BigInteger("31104000"),
            listOf(12, -3, 3, -5, -10, 8, 12, -15, 4, 1).map { it.toBigInteger() }.product()
        )
        assertEquals(
            BigInteger("53937613013501048832"),
            listOf(42, -34, -111, 191, -156, -48, -77, 143, 148, -146)
                .map { it.toBigInteger() }
                .product()
        )
    }
}
