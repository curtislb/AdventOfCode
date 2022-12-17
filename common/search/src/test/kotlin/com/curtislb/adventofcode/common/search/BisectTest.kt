package com.curtislb.adventofcode.common.search

import kotlin.test.assertEquals
import kotlin.test.assertNull
import org.junit.jupiter.api.Test

/**
 * Tests [bisect].
 */
class BisectTest {
    @Test
    fun testBisect() {
        assertEquals(1L, bisect { it > 0 })
        assertEquals(1L, bisect(knownTrue = 1L) { it > 0L })
        assertEquals(1L, bisect(knownTrue = 2L) { it > 0L })
        assertEquals(1L, bisect(knownTrue = 100L) { it > 0L })
        assertEquals(1L, bisect(knownFalse = 0L, knownTrue = 1L) { it > 0L })
        assertEquals(1L, bisect(knownFalse = 0L, knownTrue = 2L) { it > 0L })
        assertEquals(1L, bisect(knownFalse = 0L, knownTrue = 100L) { it > 0L })
        assertEquals(6L, bisect { it > 5L })
        assertEquals(6L, bisect(knownFalse = 2L) { it > 5L })
        assertEquals(6L, bisect(knownFalse = 3L, knownTrue = 7L) { it > 5L })
        assertEquals(21L, bisect(knownFalse = 6L) { it < 4L || it > 20L })
        assertEquals(21L, bisect(knownFalse = 4L, knownTrue = 34L) { it < 4L || it > 20L })
        assertEquals(78557L, bisect(knownFalse = 12345, knownTrue = 82000) { it >= 78557 })

        assertNull(bisect(knownFalse = 1L, knownTrue = 1L) { it > 0 })
        assertNull(bisect(knownFalse = Long.MAX_VALUE) { false })
        assertNull(bisect { false })
    }
}
