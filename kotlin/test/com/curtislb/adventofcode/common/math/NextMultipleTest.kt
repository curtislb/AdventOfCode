package com.curtislb.adventofcode.common.math

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests [nextMultipleAbove] and [nextMultipleAtLeast].
 */
class NextMultipleTest {
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
}
