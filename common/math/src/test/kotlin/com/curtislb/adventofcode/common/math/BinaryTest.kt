package com.curtislb.adventofcode.common.math

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests common binary-related functions and utilities.
 */
class BinaryTest {
    @Test
    fun testNonNegativeIntWithOneBit() {
        assertEquals(1, 0.withOneBit(0))
        assertEquals(1, 1.withOneBit(0))
        assertEquals(3, 2.withOneBit(0))
        assertEquals(3, 3.withOneBit(0))
        assertEquals(5, 4.withOneBit(0))
        assertEquals(5, 5.withOneBit(0))
        assertEquals(7, 6.withOneBit(0))
        assertEquals(7, 7.withOneBit(0))
        assertEquals(2, 0.withOneBit(1))
        assertEquals(3, 1.withOneBit(1))
        assertEquals(2, 2.withOneBit(1))
        assertEquals(3, 3.withOneBit(1))
        assertEquals(6, 4.withOneBit(1))
        assertEquals(7, 5.withOneBit(1))
        assertEquals(6, 6.withOneBit(1))
        assertEquals(7, 7.withOneBit(1))
        assertEquals(4, 0.withOneBit(2))
        assertEquals(5, 1.withOneBit(2))
        assertEquals(6, 2.withOneBit(2))
        assertEquals(7, 3.withOneBit(2))
        assertEquals(4, 4.withOneBit(2))
        assertEquals(5, 5.withOneBit(2))
        assertEquals(6, 6.withOneBit(2))
        assertEquals(7, 7.withOneBit(2))
        assertEquals(Int.MAX_VALUE, Int.MAX_VALUE.withOneBit(30))
        assertEquals(-1, Int.MAX_VALUE.withOneBit(31))
    }

    @Test
    fun testNegativeIntWithOneBit() {
        assertEquals(-1, (-1).withOneBit(0))
        assertEquals(-1, (-2).withOneBit(0))
        assertEquals(-3, (-3).withOneBit(0))
        assertEquals(-3, (-4).withOneBit(0))
        assertEquals(-5, (-5).withOneBit(0))
        assertEquals(-5, (-6).withOneBit(0))
        assertEquals(-7, (-7).withOneBit(0))
        assertEquals(-1, (-1).withOneBit(1))
        assertEquals(-2, (-2).withOneBit(1))
        assertEquals(-1, (-3).withOneBit(1))
        assertEquals(-2, (-4).withOneBit(1))
        assertEquals(-5, (-5).withOneBit(1))
        assertEquals(-6, (-6).withOneBit(1))
        assertEquals(-5, (-7).withOneBit(1))
        assertEquals(-1, (-1).withOneBit(2))
        assertEquals(-2, (-2).withOneBit(2))
        assertEquals(-3, (-3).withOneBit(2))
        assertEquals(-4, (-4).withOneBit(2))
        assertEquals(-1, (-5).withOneBit(2))
        assertEquals(-2, (-6).withOneBit(2))
        assertEquals(-3, (-7).withOneBit(2))
        assertEquals(Int.MIN_VALUE, Int.MIN_VALUE.withOneBit(31))
    }

    @Test
    fun testInvalidIntWithOneBit() {
        assertThrows<IllegalArgumentException> { 1.withOneBit(-1) }
        assertThrows<IllegalArgumentException> { 1.withOneBit(-2) }
        assertThrows<IllegalArgumentException> { 1.withOneBit(32) }
        assertThrows<IllegalArgumentException> { 1.withOneBit(33) }
        assertThrows<IllegalArgumentException> { 2.withOneBit(-1) }
        assertThrows<IllegalArgumentException> { 2.withOneBit(-2) }
        assertThrows<IllegalArgumentException> { 2.withOneBit(32) }
        assertThrows<IllegalArgumentException> { 2.withOneBit(33) }
    }

    @Test
    fun testNonNegativeLongWithOneBit() {
        assertEquals(1L, 0L.withOneBit(0))
        assertEquals(1L, 1L.withOneBit(0))
        assertEquals(3L, 2L.withOneBit(0))
        assertEquals(3L, 3L.withOneBit(0))
        assertEquals(5L, 4L.withOneBit(0))
        assertEquals(5L, 5L.withOneBit(0))
        assertEquals(7L, 6L.withOneBit(0))
        assertEquals(7L, 7L.withOneBit(0))
        assertEquals(2L, 0L.withOneBit(1))
        assertEquals(3L, 1L.withOneBit(1))
        assertEquals(2L, 2L.withOneBit(1))
        assertEquals(3L, 3L.withOneBit(1))
        assertEquals(6L, 4L.withOneBit(1))
        assertEquals(7L, 5L.withOneBit(1))
        assertEquals(6L, 6L.withOneBit(1))
        assertEquals(7L, 7L.withOneBit(1))
        assertEquals(4L, 0L.withOneBit(2))
        assertEquals(5L, 1L.withOneBit(2))
        assertEquals(6L, 2L.withOneBit(2))
        assertEquals(7L, 3L.withOneBit(2))
        assertEquals(4L, 4L.withOneBit(2))
        assertEquals(5L, 5L.withOneBit(2))
        assertEquals(6L, 6L.withOneBit(2))
        assertEquals(7L, 7L.withOneBit(2))
        assertEquals(Long.MAX_VALUE, Long.MAX_VALUE.withOneBit(62))
        assertEquals(-1L, Long.MAX_VALUE.withOneBit(63))
    }

    @Test
    fun testNegativeLongWithOneBit() {
        assertEquals(-1L, (-1L).withOneBit(0))
        assertEquals(-1L, (-2L).withOneBit(0))
        assertEquals(-3L, (-3L).withOneBit(0))
        assertEquals(-3L, (-4L).withOneBit(0))
        assertEquals(-5L, (-5L).withOneBit(0))
        assertEquals(-5L, (-6L).withOneBit(0))
        assertEquals(-7L, (-7L).withOneBit(0))
        assertEquals(-1L, (-1L).withOneBit(1))
        assertEquals(-2L, (-2L).withOneBit(1))
        assertEquals(-1L, (-3L).withOneBit(1))
        assertEquals(-2L, (-4L).withOneBit(1))
        assertEquals(-5L, (-5L).withOneBit(1))
        assertEquals(-6L, (-6L).withOneBit(1))
        assertEquals(-5L, (-7L).withOneBit(1))
        assertEquals(-1L, (-1L).withOneBit(2))
        assertEquals(-2L, (-2L).withOneBit(2))
        assertEquals(-3L, (-3L).withOneBit(2))
        assertEquals(-4L, (-4L).withOneBit(2))
        assertEquals(-1L, (-5L).withOneBit(2))
        assertEquals(-2L, (-6L).withOneBit(2))
        assertEquals(-3L, (-7L).withOneBit(2))
        assertEquals(Long.MIN_VALUE, Long.MIN_VALUE.withOneBit(63))
    }

    @Test
    fun testInvalidLongWithOneBit() {
        assertThrows<IllegalArgumentException> { 1L.withOneBit(-1) }
        assertThrows<IllegalArgumentException> { 1L.withOneBit(-2) }
        assertThrows<IllegalArgumentException> { 1L.withOneBit(64) }
        assertThrows<IllegalArgumentException> { 1L.withOneBit(65) }
        assertThrows<IllegalArgumentException> { 2L.withOneBit(-1) }
        assertThrows<IllegalArgumentException> { 2L.withOneBit(-2) }
        assertThrows<IllegalArgumentException> { 2L.withOneBit(64) }
        assertThrows<IllegalArgumentException> { 2L.withOneBit(65) }
    }

    @Test
    fun testNonNegativeIntWithZeroBit() {
        assertEquals(0, 0.withZeroBit(0))
        assertEquals(0, 1.withZeroBit(0))
        assertEquals(2, 2.withZeroBit(0))
        assertEquals(2, 3.withZeroBit(0))
        assertEquals(4, 4.withZeroBit(0))
        assertEquals(4, 5.withZeroBit(0))
        assertEquals(6, 6.withZeroBit(0))
        assertEquals(6, 7.withZeroBit(0))
        assertEquals(0, 0.withZeroBit(1))
        assertEquals(1, 1.withZeroBit(1))
        assertEquals(0, 2.withZeroBit(1))
        assertEquals(1, 3.withZeroBit(1))
        assertEquals(4, 4.withZeroBit(1))
        assertEquals(5, 5.withZeroBit(1))
        assertEquals(4, 6.withZeroBit(1))
        assertEquals(5, 7.withZeroBit(1))
        assertEquals(0, 0.withZeroBit(2))
        assertEquals(1, 1.withZeroBit(2))
        assertEquals(2, 2.withZeroBit(2))
        assertEquals(3, 3.withZeroBit(2))
        assertEquals(0, 4.withZeroBit(2))
        assertEquals(1, 5.withZeroBit(2))
        assertEquals(2, 6.withZeroBit(2))
        assertEquals(3, 7.withZeroBit(2))
        assertEquals(Int.MAX_VALUE / 2, Int.MAX_VALUE.withZeroBit(30))
        assertEquals(Int.MAX_VALUE, Int.MAX_VALUE.withZeroBit(31))
    }

    @Test
    fun testNegativeIntWithZeroBit() {
        assertEquals(-2, (-1).withZeroBit(0))
        assertEquals(-2, (-2).withZeroBit(0))
        assertEquals(-4, (-3).withZeroBit(0))
        assertEquals(-4, (-4).withZeroBit(0))
        assertEquals(-6, (-5).withZeroBit(0))
        assertEquals(-6, (-6).withZeroBit(0))
        assertEquals(-8, (-7).withZeroBit(0))
        assertEquals(-3, (-1).withZeroBit(1))
        assertEquals(-4, (-2).withZeroBit(1))
        assertEquals(-3, (-3).withZeroBit(1))
        assertEquals(-4, (-4).withZeroBit(1))
        assertEquals(-7, (-5).withZeroBit(1))
        assertEquals(-8, (-6).withZeroBit(1))
        assertEquals(-7, (-7).withZeroBit(1))
        assertEquals(-5, (-1).withZeroBit(2))
        assertEquals(-6, (-2).withZeroBit(2))
        assertEquals(-7, (-3).withZeroBit(2))
        assertEquals(-8, (-4).withZeroBit(2))
        assertEquals(-5, (-5).withZeroBit(2))
        assertEquals(-6, (-6).withZeroBit(2))
        assertEquals(-7, (-7).withZeroBit(2))
        assertEquals(Int.MIN_VALUE, Int.MIN_VALUE.withZeroBit(30))
        assertEquals(0, Int.MIN_VALUE.withZeroBit(31))
    }

    @Test
    fun testInvalidIntWithZeroBit() {
        assertThrows<IllegalArgumentException> { 1.withZeroBit(-1) }
        assertThrows<IllegalArgumentException> { 1.withZeroBit(-2) }
        assertThrows<IllegalArgumentException> { 1.withZeroBit(32) }
        assertThrows<IllegalArgumentException> { 1.withZeroBit(33) }
        assertThrows<IllegalArgumentException> { 2.withZeroBit(-1) }
        assertThrows<IllegalArgumentException> { 2.withZeroBit(-2) }
        assertThrows<IllegalArgumentException> { 2.withZeroBit(32) }
        assertThrows<IllegalArgumentException> { 2.withZeroBit(33) }
    }

    @Test
    fun testNonNegativeLongWithZeroBit() {
        assertEquals(0L, 0L.withZeroBit(0))
        assertEquals(0L, 1L.withZeroBit(0))
        assertEquals(2L, 2L.withZeroBit(0))
        assertEquals(2L, 3L.withZeroBit(0))
        assertEquals(4L, 4L.withZeroBit(0))
        assertEquals(4L, 5L.withZeroBit(0))
        assertEquals(6L, 6L.withZeroBit(0))
        assertEquals(6L, 7L.withZeroBit(0))
        assertEquals(0L, 0L.withZeroBit(1))
        assertEquals(1L, 1L.withZeroBit(1))
        assertEquals(0L, 2L.withZeroBit(1))
        assertEquals(1L, 3L.withZeroBit(1))
        assertEquals(4L, 4L.withZeroBit(1))
        assertEquals(5L, 5L.withZeroBit(1))
        assertEquals(4L, 6L.withZeroBit(1))
        assertEquals(5L, 7L.withZeroBit(1))
        assertEquals(0L, 0L.withZeroBit(2))
        assertEquals(1L, 1L.withZeroBit(2))
        assertEquals(2L, 2L.withZeroBit(2))
        assertEquals(3L, 3L.withZeroBit(2))
        assertEquals(0L, 4L.withZeroBit(2))
        assertEquals(1L, 5L.withZeroBit(2))
        assertEquals(2L, 6L.withZeroBit(2))
        assertEquals(3L, 7L.withZeroBit(2))
        assertEquals(Long.MAX_VALUE / 2L, Long.MAX_VALUE.withZeroBit(62))
        assertEquals(Long.MAX_VALUE, Long.MAX_VALUE.withZeroBit(63))
    }

    @Test
    fun testNegativeLongWithZeroBit() {
        assertEquals(-2, (-1L).withZeroBit(0))
        assertEquals(-2, (-2L).withZeroBit(0))
        assertEquals(-4, (-3L).withZeroBit(0))
        assertEquals(-4, (-4L).withZeroBit(0))
        assertEquals(-6, (-5L).withZeroBit(0))
        assertEquals(-6, (-6L).withZeroBit(0))
        assertEquals(-8, (-7L).withZeroBit(0))
        assertEquals(-3, (-1L).withZeroBit(1))
        assertEquals(-4, (-2L).withZeroBit(1))
        assertEquals(-3, (-3L).withZeroBit(1))
        assertEquals(-4, (-4L).withZeroBit(1))
        assertEquals(-7, (-5L).withZeroBit(1))
        assertEquals(-8, (-6L).withZeroBit(1))
        assertEquals(-7, (-7L).withZeroBit(1))
        assertEquals(-5, (-1L).withZeroBit(2))
        assertEquals(-6, (-2L).withZeroBit(2))
        assertEquals(-7, (-3L).withZeroBit(2))
        assertEquals(-8, (-4L).withZeroBit(2))
        assertEquals(-5, (-5L).withZeroBit(2))
        assertEquals(-6, (-6L).withZeroBit(2))
        assertEquals(-7, (-7L).withZeroBit(2))
        assertEquals(Long.MIN_VALUE, Long.MIN_VALUE.withZeroBit(62))
        assertEquals(0L, Long.MIN_VALUE.withZeroBit(63))
    }

    @Test
    fun testInvalidLongWithZeroBit() {
        assertThrows<IllegalArgumentException> { 1L.withZeroBit(-1) }
        assertThrows<IllegalArgumentException> { 1L.withZeroBit(-2) }
        assertThrows<IllegalArgumentException> { 1L.withZeroBit(64) }
        assertThrows<IllegalArgumentException> { 1L.withZeroBit(65) }
        assertThrows<IllegalArgumentException> { 2L.withZeroBit(-1) }
        assertThrows<IllegalArgumentException> { 2L.withZeroBit(-2) }
        assertThrows<IllegalArgumentException> { 2L.withZeroBit(64) }
        assertThrows<IllegalArgumentException> { 2L.withZeroBit(65) }
    }
}
