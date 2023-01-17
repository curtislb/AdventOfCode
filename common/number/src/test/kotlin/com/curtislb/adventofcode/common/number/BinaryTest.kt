package com.curtislb.adventofcode.common.number

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests common functions and utilities related to binary numbers.
 */
class BinaryTest {
    @Test
    fun testNonNegativeIntWithOneBit() {
        assertEquals(1, 0.setBit(0))
        assertEquals(1, 1.setBit(0))
        assertEquals(3, 2.setBit(0))
        assertEquals(3, 3.setBit(0))
        assertEquals(5, 4.setBit(0))
        assertEquals(5, 5.setBit(0))
        assertEquals(7, 6.setBit(0))
        assertEquals(7, 7.setBit(0))
        assertEquals(2, 0.setBit(1))
        assertEquals(3, 1.setBit(1))
        assertEquals(2, 2.setBit(1))
        assertEquals(3, 3.setBit(1))
        assertEquals(6, 4.setBit(1))
        assertEquals(7, 5.setBit(1))
        assertEquals(6, 6.setBit(1))
        assertEquals(7, 7.setBit(1))
        assertEquals(4, 0.setBit(2))
        assertEquals(5, 1.setBit(2))
        assertEquals(6, 2.setBit(2))
        assertEquals(7, 3.setBit(2))
        assertEquals(4, 4.setBit(2))
        assertEquals(5, 5.setBit(2))
        assertEquals(6, 6.setBit(2))
        assertEquals(7, 7.setBit(2))
        assertEquals(Int.MAX_VALUE, Int.MAX_VALUE.setBit(30))
        assertEquals(-1, Int.MAX_VALUE.setBit(31))
    }

    @Test
    fun testNegativeIntWithOneBit() {
        assertEquals(-1, (-1).setBit(0))
        assertEquals(-1, (-2).setBit(0))
        assertEquals(-3, (-3).setBit(0))
        assertEquals(-3, (-4).setBit(0))
        assertEquals(-5, (-5).setBit(0))
        assertEquals(-5, (-6).setBit(0))
        assertEquals(-7, (-7).setBit(0))
        assertEquals(-1, (-1).setBit(1))
        assertEquals(-2, (-2).setBit(1))
        assertEquals(-1, (-3).setBit(1))
        assertEquals(-2, (-4).setBit(1))
        assertEquals(-5, (-5).setBit(1))
        assertEquals(-6, (-6).setBit(1))
        assertEquals(-5, (-7).setBit(1))
        assertEquals(-1, (-1).setBit(2))
        assertEquals(-2, (-2).setBit(2))
        assertEquals(-3, (-3).setBit(2))
        assertEquals(-4, (-4).setBit(2))
        assertEquals(-1, (-5).setBit(2))
        assertEquals(-2, (-6).setBit(2))
        assertEquals(-3, (-7).setBit(2))
        assertEquals(Int.MIN_VALUE, Int.MIN_VALUE.setBit(31))
    }

    @Test
    fun testInvalidIntWithOneBit() {
        assertThrows<IllegalArgumentException> { 1.setBit(-1) }
        assertThrows<IllegalArgumentException> { 1.setBit(-2) }
        assertThrows<IllegalArgumentException> { 1.setBit(32) }
        assertThrows<IllegalArgumentException> { 1.setBit(33) }
        assertThrows<IllegalArgumentException> { 2.setBit(-1) }
        assertThrows<IllegalArgumentException> { 2.setBit(-2) }
        assertThrows<IllegalArgumentException> { 2.setBit(32) }
        assertThrows<IllegalArgumentException> { 2.setBit(33) }
    }

    @Test
    fun testNonNegativeLongWithOneBit() {
        assertEquals(1L, 0L.setBit(0))
        assertEquals(1L, 1L.setBit(0))
        assertEquals(3L, 2L.setBit(0))
        assertEquals(3L, 3L.setBit(0))
        assertEquals(5L, 4L.setBit(0))
        assertEquals(5L, 5L.setBit(0))
        assertEquals(7L, 6L.setBit(0))
        assertEquals(7L, 7L.setBit(0))
        assertEquals(2L, 0L.setBit(1))
        assertEquals(3L, 1L.setBit(1))
        assertEquals(2L, 2L.setBit(1))
        assertEquals(3L, 3L.setBit(1))
        assertEquals(6L, 4L.setBit(1))
        assertEquals(7L, 5L.setBit(1))
        assertEquals(6L, 6L.setBit(1))
        assertEquals(7L, 7L.setBit(1))
        assertEquals(4L, 0L.setBit(2))
        assertEquals(5L, 1L.setBit(2))
        assertEquals(6L, 2L.setBit(2))
        assertEquals(7L, 3L.setBit(2))
        assertEquals(4L, 4L.setBit(2))
        assertEquals(5L, 5L.setBit(2))
        assertEquals(6L, 6L.setBit(2))
        assertEquals(7L, 7L.setBit(2))
        assertEquals(Long.MAX_VALUE, Long.MAX_VALUE.setBit(62))
        assertEquals(-1L, Long.MAX_VALUE.setBit(63))
    }

    @Test
    fun testNegativeLongWithOneBit() {
        assertEquals(-1L, (-1L).setBit(0))
        assertEquals(-1L, (-2L).setBit(0))
        assertEquals(-3L, (-3L).setBit(0))
        assertEquals(-3L, (-4L).setBit(0))
        assertEquals(-5L, (-5L).setBit(0))
        assertEquals(-5L, (-6L).setBit(0))
        assertEquals(-7L, (-7L).setBit(0))
        assertEquals(-1L, (-1L).setBit(1))
        assertEquals(-2L, (-2L).setBit(1))
        assertEquals(-1L, (-3L).setBit(1))
        assertEquals(-2L, (-4L).setBit(1))
        assertEquals(-5L, (-5L).setBit(1))
        assertEquals(-6L, (-6L).setBit(1))
        assertEquals(-5L, (-7L).setBit(1))
        assertEquals(-1L, (-1L).setBit(2))
        assertEquals(-2L, (-2L).setBit(2))
        assertEquals(-3L, (-3L).setBit(2))
        assertEquals(-4L, (-4L).setBit(2))
        assertEquals(-1L, (-5L).setBit(2))
        assertEquals(-2L, (-6L).setBit(2))
        assertEquals(-3L, (-7L).setBit(2))
        assertEquals(Long.MIN_VALUE, Long.MIN_VALUE.setBit(63))
    }

    @Test
    fun testInvalidLongWithOneBit() {
        assertThrows<IllegalArgumentException> { 1L.setBit(-1) }
        assertThrows<IllegalArgumentException> { 1L.setBit(-2) }
        assertThrows<IllegalArgumentException> { 1L.setBit(64) }
        assertThrows<IllegalArgumentException> { 1L.setBit(65) }
        assertThrows<IllegalArgumentException> { 2L.setBit(-1) }
        assertThrows<IllegalArgumentException> { 2L.setBit(-2) }
        assertThrows<IllegalArgumentException> { 2L.setBit(64) }
        assertThrows<IllegalArgumentException> { 2L.setBit(65) }
    }

    @Test
    fun testNonNegativeIntWithZeroBit() {
        assertEquals(0, 0.clearBit(0))
        assertEquals(0, 1.clearBit(0))
        assertEquals(2, 2.clearBit(0))
        assertEquals(2, 3.clearBit(0))
        assertEquals(4, 4.clearBit(0))
        assertEquals(4, 5.clearBit(0))
        assertEquals(6, 6.clearBit(0))
        assertEquals(6, 7.clearBit(0))
        assertEquals(0, 0.clearBit(1))
        assertEquals(1, 1.clearBit(1))
        assertEquals(0, 2.clearBit(1))
        assertEquals(1, 3.clearBit(1))
        assertEquals(4, 4.clearBit(1))
        assertEquals(5, 5.clearBit(1))
        assertEquals(4, 6.clearBit(1))
        assertEquals(5, 7.clearBit(1))
        assertEquals(0, 0.clearBit(2))
        assertEquals(1, 1.clearBit(2))
        assertEquals(2, 2.clearBit(2))
        assertEquals(3, 3.clearBit(2))
        assertEquals(0, 4.clearBit(2))
        assertEquals(1, 5.clearBit(2))
        assertEquals(2, 6.clearBit(2))
        assertEquals(3, 7.clearBit(2))
        assertEquals(Int.MAX_VALUE / 2, Int.MAX_VALUE.clearBit(30))
        assertEquals(Int.MAX_VALUE, Int.MAX_VALUE.clearBit(31))
    }

    @Test
    fun testNegativeIntWithZeroBit() {
        assertEquals(-2, (-1).clearBit(0))
        assertEquals(-2, (-2).clearBit(0))
        assertEquals(-4, (-3).clearBit(0))
        assertEquals(-4, (-4).clearBit(0))
        assertEquals(-6, (-5).clearBit(0))
        assertEquals(-6, (-6).clearBit(0))
        assertEquals(-8, (-7).clearBit(0))
        assertEquals(-3, (-1).clearBit(1))
        assertEquals(-4, (-2).clearBit(1))
        assertEquals(-3, (-3).clearBit(1))
        assertEquals(-4, (-4).clearBit(1))
        assertEquals(-7, (-5).clearBit(1))
        assertEquals(-8, (-6).clearBit(1))
        assertEquals(-7, (-7).clearBit(1))
        assertEquals(-5, (-1).clearBit(2))
        assertEquals(-6, (-2).clearBit(2))
        assertEquals(-7, (-3).clearBit(2))
        assertEquals(-8, (-4).clearBit(2))
        assertEquals(-5, (-5).clearBit(2))
        assertEquals(-6, (-6).clearBit(2))
        assertEquals(-7, (-7).clearBit(2))
        assertEquals(Int.MIN_VALUE, Int.MIN_VALUE.clearBit(30))
        assertEquals(0, Int.MIN_VALUE.clearBit(31))
    }

    @Test
    fun testInvalidIntWithZeroBit() {
        assertThrows<IllegalArgumentException> { 1.clearBit(-1) }
        assertThrows<IllegalArgumentException> { 1.clearBit(-2) }
        assertThrows<IllegalArgumentException> { 1.clearBit(32) }
        assertThrows<IllegalArgumentException> { 1.clearBit(33) }
        assertThrows<IllegalArgumentException> { 2.clearBit(-1) }
        assertThrows<IllegalArgumentException> { 2.clearBit(-2) }
        assertThrows<IllegalArgumentException> { 2.clearBit(32) }
        assertThrows<IllegalArgumentException> { 2.clearBit(33) }
    }

    @Test
    fun testNonNegativeLongWithZeroBit() {
        assertEquals(0L, 0L.clearBit(0))
        assertEquals(0L, 1L.clearBit(0))
        assertEquals(2L, 2L.clearBit(0))
        assertEquals(2L, 3L.clearBit(0))
        assertEquals(4L, 4L.clearBit(0))
        assertEquals(4L, 5L.clearBit(0))
        assertEquals(6L, 6L.clearBit(0))
        assertEquals(6L, 7L.clearBit(0))
        assertEquals(0L, 0L.clearBit(1))
        assertEquals(1L, 1L.clearBit(1))
        assertEquals(0L, 2L.clearBit(1))
        assertEquals(1L, 3L.clearBit(1))
        assertEquals(4L, 4L.clearBit(1))
        assertEquals(5L, 5L.clearBit(1))
        assertEquals(4L, 6L.clearBit(1))
        assertEquals(5L, 7L.clearBit(1))
        assertEquals(0L, 0L.clearBit(2))
        assertEquals(1L, 1L.clearBit(2))
        assertEquals(2L, 2L.clearBit(2))
        assertEquals(3L, 3L.clearBit(2))
        assertEquals(0L, 4L.clearBit(2))
        assertEquals(1L, 5L.clearBit(2))
        assertEquals(2L, 6L.clearBit(2))
        assertEquals(3L, 7L.clearBit(2))
        assertEquals(Long.MAX_VALUE / 2L, Long.MAX_VALUE.clearBit(62))
        assertEquals(Long.MAX_VALUE, Long.MAX_VALUE.clearBit(63))
    }

    @Test
    fun testNegativeLongWithZeroBit() {
        assertEquals(-2, (-1L).clearBit(0))
        assertEquals(-2, (-2L).clearBit(0))
        assertEquals(-4, (-3L).clearBit(0))
        assertEquals(-4, (-4L).clearBit(0))
        assertEquals(-6, (-5L).clearBit(0))
        assertEquals(-6, (-6L).clearBit(0))
        assertEquals(-8, (-7L).clearBit(0))
        assertEquals(-3, (-1L).clearBit(1))
        assertEquals(-4, (-2L).clearBit(1))
        assertEquals(-3, (-3L).clearBit(1))
        assertEquals(-4, (-4L).clearBit(1))
        assertEquals(-7, (-5L).clearBit(1))
        assertEquals(-8, (-6L).clearBit(1))
        assertEquals(-7, (-7L).clearBit(1))
        assertEquals(-5, (-1L).clearBit(2))
        assertEquals(-6, (-2L).clearBit(2))
        assertEquals(-7, (-3L).clearBit(2))
        assertEquals(-8, (-4L).clearBit(2))
        assertEquals(-5, (-5L).clearBit(2))
        assertEquals(-6, (-6L).clearBit(2))
        assertEquals(-7, (-7L).clearBit(2))
        assertEquals(Long.MIN_VALUE, Long.MIN_VALUE.clearBit(62))
        assertEquals(0L, Long.MIN_VALUE.clearBit(63))
    }

    @Test
    fun testInvalidLongWithZeroBit() {
        assertThrows<IllegalArgumentException> { 1L.clearBit(-1) }
        assertThrows<IllegalArgumentException> { 1L.clearBit(-2) }
        assertThrows<IllegalArgumentException> { 1L.clearBit(64) }
        assertThrows<IllegalArgumentException> { 1L.clearBit(65) }
        assertThrows<IllegalArgumentException> { 2L.clearBit(-1) }
        assertThrows<IllegalArgumentException> { 2L.clearBit(-2) }
        assertThrows<IllegalArgumentException> { 2L.clearBit(64) }
        assertThrows<IllegalArgumentException> { 2L.clearBit(65) }
    }
}
