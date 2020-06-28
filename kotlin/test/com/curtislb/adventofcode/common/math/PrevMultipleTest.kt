package com.curtislb.adventofcode.common.math

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests [prevMultipleAtMost] and [prevMultipleBelow].
 */
class PrevMultipleTest {
    @Test fun testPrevMultipleBelowLong() {
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

    @Test fun testPrevMultipleBelowInt() {
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

    @Test fun testPrevMultipleAtMostLong() {
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

    @Test fun testPrevMultipleAtMostInt() {
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
}
