package com.curtislb.adventofcode.common.math

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [countDigits].
 */
class CountDigitsTest {
    @Test
    fun testCountDigitsOfInt() {
        assertEquals(1, 0.countDigits())
        assertEquals(1, 0.countDigits(base = 2))
        assertEquals(1, 1.countDigits())
        assertEquals(1, 1.countDigits(base = 2))
        assertEquals(1, 2.countDigits())
        assertEquals(2, 2.countDigits(base = 2))
        assertEquals(1, 9.countDigits())
        assertEquals(4, 9.countDigits(base = 2))
        assertEquals(2, 9.countDigits(base = 8))
        assertEquals(1, 9.countDigits(base = 16))
        assertEquals(2, 10.countDigits())
        assertEquals(4, 10.countDigits(base = 2))
        assertEquals(2, 10.countDigits(base = 8))
        assertEquals(1, 10.countDigits(base = 16))
        assertEquals(2, 10.countDigits())
        assertEquals(2, 15.countDigits())
        assertEquals(4, 15.countDigits(base = 2))
        assertEquals(2, 15.countDigits(base = 8))
        assertEquals(1, 15.countDigits(base = 16))
        assertEquals(2, 16.countDigits())
        assertEquals(5, 16.countDigits(base = 2))
        assertEquals(2, 16.countDigits(base = 8))
        assertEquals(2, 16.countDigits(base = 16))
        assertEquals(2, 99.countDigits())
        assertEquals(7, 99.countDigits(base = 2))
        assertEquals(3, 99.countDigits(base = 8))
        assertEquals(2, 99.countDigits(base = 16))
        assertEquals(1, 99.countDigits(base = 100))
        assertEquals(3, 100.countDigits())
        assertEquals(7, 100.countDigits(base = 2))
        assertEquals(3, 100.countDigits(base = 8))
        assertEquals(2, 100.countDigits(base = 16))
        assertEquals(2, 100.countDigits(base = 100))
        assertEquals(11, 81053026850.countDigits())
        assertEquals(37, 81053026850.countDigits(base = 2))
        assertEquals(10, 81053026850.countDigits(base = 16))
    }

    @Test
    fun testCountDigitsOfInvalidInt() {
        assertThrows<IllegalArgumentException> { (-1).countDigits() }
        assertThrows<IllegalArgumentException> { (-2).countDigits() }
        assertThrows<IllegalArgumentException> { (-3).countDigits() }
        assertThrows<IllegalArgumentException> { (-1).countDigits(base = 2) }
        assertThrows<IllegalArgumentException> { (-2).countDigits(base = 8) }
        assertThrows<IllegalArgumentException> { (-3).countDigits(base = 16) }
    }

    @Test
    fun testCountDigitsOfIntWithInvalidBase() {
        assertThrows<IllegalArgumentException> { 2.countDigits(base = -1) }
        assertThrows<IllegalArgumentException> { 2.countDigits(base = 0) }
        assertThrows<IllegalArgumentException> { 2.countDigits(base = 1) }
        assertThrows<IllegalArgumentException> { 10.countDigits(base = -1) }
        assertThrows<IllegalArgumentException> { 10.countDigits(base = 0) }
        assertThrows<IllegalArgumentException> { 10.countDigits(base = 1) }
    }

    @Test
    fun testCountDigitsOfLong() {
        assertEquals(1, 0L.countDigits())
        assertEquals(1, 0L.countDigits(base = 2))
        assertEquals(1, 1L.countDigits())
        assertEquals(1, 1L.countDigits(base = 2))
        assertEquals(1, 2L.countDigits())
        assertEquals(2, 2L.countDigits(base = 2))
        assertEquals(1, 9L.countDigits())
        assertEquals(4, 9L.countDigits(base = 2))
        assertEquals(2, 9L.countDigits(base = 8))
        assertEquals(1, 9L.countDigits(base = 16))
        assertEquals(2, 10L.countDigits())
        assertEquals(4, 10L.countDigits(base = 2))
        assertEquals(2, 10L.countDigits(base = 8))
        assertEquals(1, 10L.countDigits(base = 16))
        assertEquals(2, 10L.countDigits())
        assertEquals(2, 15L.countDigits())
        assertEquals(4, 15L.countDigits(base = 2))
        assertEquals(2, 15L.countDigits(base = 8))
        assertEquals(1, 15L.countDigits(base = 16))
        assertEquals(2, 16L.countDigits())
        assertEquals(5, 16L.countDigits(base = 2))
        assertEquals(2, 16L.countDigits(base = 8))
        assertEquals(2, 16L.countDigits(base = 16))
        assertEquals(2, 99L.countDigits())
        assertEquals(7, 99L.countDigits(base = 2))
        assertEquals(3, 99L.countDigits(base = 8))
        assertEquals(2, 99L.countDigits(base = 16))
        assertEquals(1, 99L.countDigits(base = 100))
        assertEquals(3, 100L.countDigits())
        assertEquals(7, 100L.countDigits(base = 2))
        assertEquals(3, 100L.countDigits(base = 8))
        assertEquals(2, 100L.countDigits(base = 16))
        assertEquals(2, 100L.countDigits(base = 100))
        assertEquals(11, 81053026850L.countDigits())
        assertEquals(37, 81053026850L.countDigits(base = 2))
        assertEquals(10, 81053026850L.countDigits(base = 16))
        assertEquals(18, 408614916977095463L.countDigits())
        assertEquals(59, 408614916977095463L.countDigits(base = 2))
        assertEquals(15, 408614916977095463L.countDigits(base = 16))
    }

    @Test
    fun testCountDigitsOfInvalidLong() {
        assertThrows<IllegalArgumentException> { (-1L).countDigits() }
        assertThrows<IllegalArgumentException> { (-2L).countDigits() }
        assertThrows<IllegalArgumentException> { (-3L).countDigits() }
        assertThrows<IllegalArgumentException> { (-1L).countDigits(base = 2) }
        assertThrows<IllegalArgumentException> { (-2L).countDigits(base = 8) }
        assertThrows<IllegalArgumentException> { (-3L).countDigits(base = 16) }
    }

    @Test
    fun testCountDigitsOfLongWithInvalidBase() {
        assertThrows<IllegalArgumentException> { 2L.countDigits(base = -1) }
        assertThrows<IllegalArgumentException> { 2L.countDigits(base = 0) }
        assertThrows<IllegalArgumentException> { 2L.countDigits(base = 1) }
        assertThrows<IllegalArgumentException> { 10L.countDigits(base = -1) }
        assertThrows<IllegalArgumentException> { 10L.countDigits(base = 0) }
        assertThrows<IllegalArgumentException> { 10L.countDigits(base = 1) }
    }
}
