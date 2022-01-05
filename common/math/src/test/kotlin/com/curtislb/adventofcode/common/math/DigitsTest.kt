package com.curtislb.adventofcode.common.math

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests common functions and utilities related to numerical digits.
 */
class DigitsTest {
    @Test
    fun testCountDigitsOfInt() {
        assertEquals(1, 0.countDigits())
        assertEquals(1, 0.countDigits(radix = 2))
        assertEquals(1, 1.countDigits())
        assertEquals(1, 1.countDigits(radix = 2))
        assertEquals(1, 2.countDigits())
        assertEquals(2, 2.countDigits(radix = 2))
        assertEquals(1, 9.countDigits())
        assertEquals(4, 9.countDigits(radix = 2))
        assertEquals(2, 9.countDigits(radix = 8))
        assertEquals(1, 9.countDigits(radix = 16))
        assertEquals(2, 10.countDigits())
        assertEquals(4, 10.countDigits(radix = 2))
        assertEquals(2, 10.countDigits(radix = 8))
        assertEquals(1, 10.countDigits(radix = 16))
        assertEquals(2, 10.countDigits())
        assertEquals(2, 15.countDigits())
        assertEquals(4, 15.countDigits(radix = 2))
        assertEquals(2, 15.countDigits(radix = 8))
        assertEquals(1, 15.countDigits(radix = 16))
        assertEquals(2, 16.countDigits())
        assertEquals(5, 16.countDigits(radix = 2))
        assertEquals(2, 16.countDigits(radix = 8))
        assertEquals(2, 16.countDigits(radix = 16))
        assertEquals(2, 99.countDigits())
        assertEquals(7, 99.countDigits(radix = 2))
        assertEquals(3, 99.countDigits(radix = 8))
        assertEquals(2, 99.countDigits(radix = 16))
        assertEquals(1, 99.countDigits(radix = 100))
        assertEquals(3, 100.countDigits())
        assertEquals(7, 100.countDigits(radix = 2))
        assertEquals(3, 100.countDigits(radix = 8))
        assertEquals(2, 100.countDigits(radix = 16))
        assertEquals(2, 100.countDigits(radix = 100))
        assertEquals(11, 81053026850.countDigits())
        assertEquals(37, 81053026850.countDigits(radix = 2))
        assertEquals(10, 81053026850.countDigits(radix = 16))
    }

    @Test
    fun testCountDigitsOfInvalidInt() {
        assertThrows<IllegalArgumentException> { (-1).countDigits() }
        assertThrows<IllegalArgumentException> { (-2).countDigits() }
        assertThrows<IllegalArgumentException> { (-3).countDigits() }
        assertThrows<IllegalArgumentException> { (-1).countDigits(radix = 2) }
        assertThrows<IllegalArgumentException> { (-2).countDigits(radix = 8) }
        assertThrows<IllegalArgumentException> { (-3).countDigits(radix = 16) }
    }

    @Test
    fun testCountDigitsOfIntWithInvalidRadix() {
        assertThrows<IllegalArgumentException> { 2.countDigits(radix = -1) }
        assertThrows<IllegalArgumentException> { 2.countDigits(radix = 0) }
        assertThrows<IllegalArgumentException> { 2.countDigits(radix = 1) }
        assertThrows<IllegalArgumentException> { 10.countDigits(radix = -1) }
        assertThrows<IllegalArgumentException> { 10.countDigits(radix = 0) }
        assertThrows<IllegalArgumentException> { 10.countDigits(radix = 1) }
    }

    @Test
    fun testCountDigitsOfLong() {
        assertEquals(1, 0L.countDigits())
        assertEquals(1, 0L.countDigits(radix = 2))
        assertEquals(1, 1L.countDigits())
        assertEquals(1, 1L.countDigits(radix = 2))
        assertEquals(1, 2L.countDigits())
        assertEquals(2, 2L.countDigits(radix = 2))
        assertEquals(1, 9L.countDigits())
        assertEquals(4, 9L.countDigits(radix = 2))
        assertEquals(2, 9L.countDigits(radix = 8))
        assertEquals(1, 9L.countDigits(radix = 16))
        assertEquals(2, 10L.countDigits())
        assertEquals(4, 10L.countDigits(radix = 2))
        assertEquals(2, 10L.countDigits(radix = 8))
        assertEquals(1, 10L.countDigits(radix = 16))
        assertEquals(2, 10L.countDigits())
        assertEquals(2, 15L.countDigits())
        assertEquals(4, 15L.countDigits(radix = 2))
        assertEquals(2, 15L.countDigits(radix = 8))
        assertEquals(1, 15L.countDigits(radix = 16))
        assertEquals(2, 16L.countDigits())
        assertEquals(5, 16L.countDigits(radix = 2))
        assertEquals(2, 16L.countDigits(radix = 8))
        assertEquals(2, 16L.countDigits(radix = 16))
        assertEquals(2, 99L.countDigits())
        assertEquals(7, 99L.countDigits(radix = 2))
        assertEquals(3, 99L.countDigits(radix = 8))
        assertEquals(2, 99L.countDigits(radix = 16))
        assertEquals(1, 99L.countDigits(radix = 100))
        assertEquals(3, 100L.countDigits())
        assertEquals(7, 100L.countDigits(radix = 2))
        assertEquals(3, 100L.countDigits(radix = 8))
        assertEquals(2, 100L.countDigits(radix = 16))
        assertEquals(2, 100L.countDigits(radix = 100))
        assertEquals(11, 81053026850L.countDigits())
        assertEquals(37, 81053026850L.countDigits(radix = 2))
        assertEquals(10, 81053026850L.countDigits(radix = 16))
        assertEquals(18, 408614916977095463L.countDigits())
        assertEquals(59, 408614916977095463L.countDigits(radix = 2))
        assertEquals(15, 408614916977095463L.countDigits(radix = 16))
    }

    @Test
    fun testCountDigitsOfInvalidLong() {
        assertThrows<IllegalArgumentException> { (-1L).countDigits() }
        assertThrows<IllegalArgumentException> { (-2L).countDigits() }
        assertThrows<IllegalArgumentException> { (-3L).countDigits() }
        assertThrows<IllegalArgumentException> { (-1L).countDigits(radix = 2) }
        assertThrows<IllegalArgumentException> { (-2L).countDigits(radix = 8) }
        assertThrows<IllegalArgumentException> { (-3L).countDigits(radix = 16) }
    }

    @Test
    fun testCountDigitsOfLongWithInvalidRadix() {
        assertThrows<IllegalArgumentException> { 2L.countDigits(radix = -1) }
        assertThrows<IllegalArgumentException> { 2L.countDigits(radix = 0) }
        assertThrows<IllegalArgumentException> { 2L.countDigits(radix = 1) }
        assertThrows<IllegalArgumentException> { 10L.countDigits(radix = -1) }
        assertThrows<IllegalArgumentException> { 10L.countDigits(radix = 0) }
        assertThrows<IllegalArgumentException> { 10L.countDigits(radix = 1) }
    }

    @Test
    fun testBigEndianDigit() {
        assertEquals(0, 0.bigEndianDigit(0))
        assertEquals(0, 0.bigEndianDigit(0, radix = 2))
        assertEquals(0, 0.bigEndianDigit(0, radix = 16))
        assertEquals(1, 1.bigEndianDigit(0))
        assertEquals(1, 1.bigEndianDigit(0, radix = 8))
        assertEquals(1, 1.bigEndianDigit(0, radix = 256))
        assertEquals(7, 7.bigEndianDigit(0))
        assertEquals(2, 7.bigEndianDigit(0, radix = 3))
        assertEquals(1, 7.bigEndianDigit(1, radix = 3))
        assertEquals(8, 859.bigEndianDigit(0))
        assertEquals(5, 859.bigEndianDigit(1))
        assertEquals(9, 859.bigEndianDigit(2))
        assertEquals(3, 859.bigEndianDigit(0, radix = 16))
        assertEquals(5, 859.bigEndianDigit(1, radix = 16))
        assertEquals(11, 859.bigEndianDigit(2, radix = 16))
        assertEquals(3, 859.bigEndianDigit(0, radix = 256))
        assertEquals(91, 859.bigEndianDigit(1, radix = 256))
    }

    @Test
    fun testBigEndianDigitOfInvalidInt() {
        assertThrows<IllegalArgumentException> { (-1).bigEndianDigit(0) }
        assertThrows<IllegalArgumentException> { (-2).bigEndianDigit(0) }
        assertThrows<IllegalArgumentException> { (-3).bigEndianDigit(0) }
        assertThrows<IllegalArgumentException> { (-1).bigEndianDigit(0, radix = 2) }
        assertThrows<IllegalArgumentException> { (-2).bigEndianDigit(0, radix = 8) }
        assertThrows<IllegalArgumentException> { (-3).bigEndianDigit(0, radix = 16) }
    }

    @Test
    fun testBigEndianDigitWithInvalidPosition() {
        assertThrows<IllegalArgumentException> { 2.bigEndianDigit(-1) }
        assertThrows<IllegalArgumentException> { 2.bigEndianDigit(1) }
        assertThrows<IllegalArgumentException> { 2.bigEndianDigit(2) }
        assertThrows<IllegalArgumentException> { 10.bigEndianDigit(-1) }
        assertThrows<IllegalArgumentException> { 10.bigEndianDigit(2) }
        assertThrows<IllegalArgumentException> { 10.bigEndianDigit(3) }
        assertThrows<IllegalArgumentException> { 10.bigEndianDigit(-1) }
        assertThrows<IllegalArgumentException> { 10.bigEndianDigit(2, radix = 16) }
    }

    @Test
    fun testBigEndianDigitWithInvalidRadix() {
        assertThrows<IllegalArgumentException> { 2.bigEndianDigit(0, radix = -1) }
        assertThrows<IllegalArgumentException> { 2.bigEndianDigit(0, radix = 0) }
        assertThrows<IllegalArgumentException> { 2.bigEndianDigit(0, radix = 1) }
        assertThrows<IllegalArgumentException> { 10.bigEndianDigit(0, radix = -1) }
        assertThrows<IllegalArgumentException> { 10.bigEndianDigit(0, radix = 0) }
        assertThrows<IllegalArgumentException> { 10.bigEndianDigit(0, radix = 1) }
    }

    @Test
    fun testLittleEndianDigit() {
        assertEquals(0, 0.littleEndianDigit(0))
        assertEquals(0, 0.littleEndianDigit(1))
        assertEquals(0, 0.littleEndianDigit(0, radix = 2))
        assertEquals(0, 0.littleEndianDigit(1, radix = 16))
        assertEquals(1, 1.littleEndianDigit(0))
        assertEquals(0, 1.littleEndianDigit(1))
        assertEquals(1, 1.littleEndianDigit(0, radix = 8))
        assertEquals(0, 1.littleEndianDigit(1, radix = 256))
        assertEquals(7, 7.littleEndianDigit(0))
        assertEquals(0, 7.littleEndianDigit(1))
        assertEquals(0, 7.littleEndianDigit(2))
        assertEquals(1, 7.littleEndianDigit(0, radix = 3))
        assertEquals(2, 7.littleEndianDigit(1, radix = 3))
        assertEquals(0, 7.littleEndianDigit(2, radix = 3))
        assertEquals(9, 859.littleEndianDigit(0))
        assertEquals(5, 859.littleEndianDigit(1))
        assertEquals(8, 859.littleEndianDigit(2))
        assertEquals(0, 859.littleEndianDigit(3))
        assertEquals(11, 859.littleEndianDigit(0, radix = 16))
        assertEquals(5, 859.littleEndianDigit(1, radix = 16))
        assertEquals(3, 859.littleEndianDigit(2, radix = 16))
        assertEquals(0, 859.littleEndianDigit(3, radix = 16))
        assertEquals(91, 859.littleEndianDigit(0, radix = 256))
        assertEquals(3, 859.littleEndianDigit(1, radix = 256))
        assertEquals(0, 859.littleEndianDigit(2, radix = 256))
    }

    @Test
    fun testLittleEndianDigitOfInvalidInt() {
        assertThrows<IllegalArgumentException> { (-1).littleEndianDigit(0) }
        assertThrows<IllegalArgumentException> { (-2).littleEndianDigit(0) }
        assertThrows<IllegalArgumentException> { (-3).littleEndianDigit(0) }
        assertThrows<IllegalArgumentException> { (-1).littleEndianDigit(0, radix = 2) }
        assertThrows<IllegalArgumentException> { (-2).littleEndianDigit(0, radix = 8) }
        assertThrows<IllegalArgumentException> { (-3).littleEndianDigit(0, radix = 16) }
    }

    @Test
    fun testLittleEndianDigitWithInvalidPosition() {
        assertThrows<IllegalArgumentException> { 2.littleEndianDigit(-1) }
        assertThrows<IllegalArgumentException> { 2.littleEndianDigit(-2) }
        assertThrows<IllegalArgumentException> { 2.littleEndianDigit(-1, radix = 2) }
        assertThrows<IllegalArgumentException> { 2.littleEndianDigit(-2, radix = 8) }
        assertThrows<IllegalArgumentException> { 10.littleEndianDigit(-1) }
        assertThrows<IllegalArgumentException> { 10.littleEndianDigit(-2) }
        assertThrows<IllegalArgumentException> { 10.littleEndianDigit(-1, radix = 2) }
        assertThrows<IllegalArgumentException> { 10.littleEndianDigit(-2, radix = 8) }
    }

    @Test
    fun testLittleEndianDigitWithInvalidRadix() {
        assertThrows<IllegalArgumentException> { 2.littleEndianDigit(0, radix = -1) }
        assertThrows<IllegalArgumentException> { 2.littleEndianDigit(0, radix = 0) }
        assertThrows<IllegalArgumentException> { 2.littleEndianDigit(0, radix = 1) }
        assertThrows<IllegalArgumentException> { 10.littleEndianDigit(0, radix = -1) }
        assertThrows<IllegalArgumentException> { 10.littleEndianDigit(0, radix = 0) }
        assertThrows<IllegalArgumentException> { 10.littleEndianDigit(0, radix = 1) }
    }
}
