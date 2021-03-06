package com.curtislb.adventofcode.common.math

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests [pow].
 */
class PowTest {
    @Test
    fun testPowOfLong() {
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
    fun testPowOfInt() {
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
}
