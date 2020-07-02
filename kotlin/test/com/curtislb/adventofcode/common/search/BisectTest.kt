package com.curtislb.adventofcode.common.search

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

/**
 * Tests [bisectIndex].
 */
class BisectTest {
    @Test fun testBisectIndex() {
        assertEquals(1L, bisectIndex { it > 0 })
        assertEquals(1L, bisectIndex(knownFalse = 0L, knownTrue = 1L) { it > 0L })
        assertEquals(1L, bisectIndex(knownFalse = 0L, knownTrue = 2L) { it > 0L })
        assertEquals(1L, bisectIndex(knownFalse = 0L, knownTrue = 100L) { it > 0L })
        assertEquals(6L, bisectIndex { it > 5L })
        assertEquals(6L, bisectIndex(knownFalse = 2L) { it > 5L })
        assertEquals(6L, bisectIndex(knownFalse = 3L, knownTrue = 7L) { it > 5L })
        assertEquals(21L, bisectIndex(knownFalse = 6L) { it < 4L || it > 20L })
        assertEquals(21L, bisectIndex(knownFalse = 4L, knownTrue = 34L) { it < 4L || it > 20L })
        assertEquals(78557L, bisectIndex(knownFalse = 12345, knownTrue = 82000) { it >= 78557 })

        assertNull(bisectIndex(knownFalse = 1L, knownTrue = 1L) { it > 0 })
        assertNull(bisectIndex(knownFalse = Int.MAX_VALUE.toLong()) { false })
        assertNull(bisectIndex { false })
    }
}
