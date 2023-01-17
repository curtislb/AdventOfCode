package com.curtislb.adventofcode.common.number

import java.math.BigInteger
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests common functions and utilities related to mathematical operations.
 */
class OperationsTest {
    @Test
    fun testModMultiplyWithLongs() {
        for (m in -11L..11L) {
            for (n in -11L..11L) {
                for (modulus in (-11L..11L)) {
                    if (modulus == 0L) {
                        assertThrows<IllegalArgumentException> { m.modMultiply(n, modulus) }
                    } else {
                        assertEquals((m * n).mod(modulus), m.modMultiply(n, modulus))
                    }
                }
            }
        }
    }

    @Test
    fun testModMultiplyWithBigIntegers() {
        for (m in -11..11) {
            for (n in -11..11) {
                for (modulus in (-2..11)) {
                    if (modulus <= 0) {
                        assertThrows<IllegalArgumentException> {
                            m.toBigInteger().modMultiply(n.toBigInteger(), modulus.toBigInteger())
                        }
                    } else {
                        assertEquals(
                            (m * n).mod(modulus).toBigInteger(),
                            m.toBigInteger().modMultiply(n.toBigInteger(), modulus.toBigInteger())
                        )
                    }
                }
            }
        }
    }

    @Test
    fun testPowOfInt() {
        assertEquals(1, 0.pow(0))
        assertEquals(0, 0.pow(1))
        assertEquals(0, 0.pow(2))
        assertEquals(0, 0.pow(3))
        assertEquals(1, 1.pow(0))
        assertEquals(1, 1.pow(1))
        assertEquals(1, 1.pow(2))
        assertEquals(1, 1.pow(3))
        assertEquals(1, 2.pow(0))
        assertEquals(2, 2.pow(1))
        assertEquals(4, 2.pow(2))
        assertEquals(8, 2.pow(3))
        assertEquals(1, 3.pow(0))
        assertEquals(3, 3.pow(1))
        assertEquals(9, 3.pow(2))
        assertEquals(27, 3.pow(3))
        assertEquals(1, (-1).pow(0))
        assertEquals(-1, (-1).pow(1))
        assertEquals(1, (-1).pow(2))
        assertEquals(-1, (-1).pow(3))
        assertEquals(1, (-2).pow(0))
        assertEquals(-2, (-2).pow(1))
        assertEquals(4, (-2).pow(2))
        assertEquals(-8, (-2).pow(3))
        assertEquals(1977326743, 7.pow(11))
    }

    @Test
    fun testPowOfIntWithInvalidExponent() {
        assertThrows<IllegalArgumentException> { 0.pow(-1) }
        assertThrows<IllegalArgumentException> { 0.pow(-2) }
        assertThrows<IllegalArgumentException> { 1.pow(-1) }
        assertThrows<IllegalArgumentException> { 1.pow(-2) }
        assertThrows<IllegalArgumentException> { 2.pow(-1) }
        assertThrows<IllegalArgumentException> { 2.pow(-2) }
    }

    @Test
    fun testPowOfLong() {
        assertEquals(1L, 0L.pow(0))
        assertEquals(0L, 0L.pow(1))
        assertEquals(0L, 0L.pow(2))
        assertEquals(0L, 0L.pow(3))
        assertEquals(1L, 1L.pow(0))
        assertEquals(1L, 1L.pow(1))
        assertEquals(1L, 1L.pow(2))
        assertEquals(1L, 1L.pow(3))
        assertEquals(1L, 2L.pow(0))
        assertEquals(2L, 2L.pow(1))
        assertEquals(4L, 2L.pow(2))
        assertEquals(8L, 2L.pow(3))
        assertEquals(1L, 3L.pow(0))
        assertEquals(3L, 3L.pow(1))
        assertEquals(9L, 3L.pow(2))
        assertEquals(27L, 3L.pow(3))
        assertEquals(1L, (-1L).pow(0))
        assertEquals(-1L, (-1L).pow(1))
        assertEquals(1L, (-1L).pow(2))
        assertEquals(-1L, (-1L).pow(3))
        assertEquals(1L, (-2L).pow(0))
        assertEquals(-2L, (-2L).pow(1))
        assertEquals(4L, (-2L).pow(2))
        assertEquals(-8L, (-2L).pow(3))
        assertEquals(1977326743L, 7L.pow(11))
        assertEquals(819628286980801L, 31L.pow(10))
    }

    @Test
    fun testPowOfLongWithInvalidExponent() {
        assertThrows<IllegalArgumentException> { 0L.pow(-1) }
        assertThrows<IllegalArgumentException> { 0L.pow(-2) }
        assertThrows<IllegalArgumentException> { 1L.pow(-1) }
        assertThrows<IllegalArgumentException> { 1L.pow(-2) }
        assertThrows<IllegalArgumentException> { 2L.pow(-1) }
        assertThrows<IllegalArgumentException> { 2L.pow(-2) }
    }

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
