package com.curtislb.adventofcode.common.math

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [bigEndianDigit] and [littleEndianDigit].
 */
class EndianDigitTest {
    @Test
    fun testBigEndianDigitWithInvalidPosition() {
        assertThrows<IllegalArgumentException> { 2.bigEndianDigit(1) }
    }

    @Test
    fun testBigEndianDigit() {
        assertEquals(0, 0.bigEndianDigit(0))
        assertEquals(0, 0.bigEndianDigit(0, base = 2))
        assertEquals(0, 0.bigEndianDigit(0, base = 16))
        assertEquals(1, 1.bigEndianDigit(0))
        assertEquals(1, 1.bigEndianDigit(0, base = 8))
        assertEquals(1, 1.bigEndianDigit(0, base = 256))
        assertEquals(7, 7.bigEndianDigit(0))
        assertEquals(2, 7.bigEndianDigit(0, base = 3))
        assertEquals(1, 7.bigEndianDigit(1, base = 3))
        assertEquals(8, 859.bigEndianDigit(0))
        assertEquals(5, 859.bigEndianDigit(1))
        assertEquals(9, 859.bigEndianDigit(2))
        assertEquals(3, 859.bigEndianDigit(0, base = 16))
        assertEquals(5, 859.bigEndianDigit(1, base = 16))
        assertEquals(11, 859.bigEndianDigit(2, base = 16))
        assertEquals(3, 859.bigEndianDigit(0, base = 256))
        assertEquals(91, 859.bigEndianDigit(1, base = 256))
    }

    @Test
    fun testLittleEndianDigit() {
        assertEquals(0, 0.littleEndianDigit(0))
        assertEquals(0, 0.littleEndianDigit(1))
        assertEquals(0, 0.littleEndianDigit(0, base = 2))
        assertEquals(0, 0.littleEndianDigit(1, base = 16))
        assertEquals(1, 1.littleEndianDigit(0))
        assertEquals(0, 1.littleEndianDigit(1))
        assertEquals(1, 1.littleEndianDigit(0, base = 8))
        assertEquals(0, 1.littleEndianDigit(1, base = 256))
        assertEquals(7, 7.littleEndianDigit(0))
        assertEquals(0, 7.littleEndianDigit(1))
        assertEquals(0, 7.littleEndianDigit(2))
        assertEquals(1, 7.littleEndianDigit(0, base = 3))
        assertEquals(2, 7.littleEndianDigit(1, base = 3))
        assertEquals(0, 7.littleEndianDigit(2, base = 3))
        assertEquals(9, 859.littleEndianDigit(0))
        assertEquals(5, 859.littleEndianDigit(1))
        assertEquals(8, 859.littleEndianDigit(2))
        assertEquals(0, 859.littleEndianDigit(3))
        assertEquals(11, 859.littleEndianDigit(0, base = 16))
        assertEquals(5, 859.littleEndianDigit(1, base = 16))
        assertEquals(3, 859.littleEndianDigit(2, base = 16))
        assertEquals(0, 859.littleEndianDigit(3, base = 16))
        assertEquals(91, 859.littleEndianDigit(0, base = 256))
        assertEquals(3, 859.littleEndianDigit(1, base = 256))
        assertEquals(0, 859.littleEndianDigit(2, base = 256))
    }
}
