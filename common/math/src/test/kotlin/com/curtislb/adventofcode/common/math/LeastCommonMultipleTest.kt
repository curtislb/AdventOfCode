package com.curtislb.adventofcode.common.math

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [leastCommonMultiple].
 */
class LeastCommonMultipleTest {
    @Test
    fun testLeastCommonMultipleOfTwoInts() {
        assertEquals(1, leastCommonMultiple(1, 1))
        assertEquals(2, leastCommonMultiple(1, 2))
        assertEquals(2, leastCommonMultiple(2, 1))
        assertEquals(6, leastCommonMultiple(2, 3))
        assertEquals(6, leastCommonMultiple(3, 2))
        assertEquals(4, leastCommonMultiple(2, 4))
        assertEquals(12, leastCommonMultiple(3, 4))
        assertEquals(12, leastCommonMultiple(6, 4))
        assertEquals(42, leastCommonMultiple(6, 21))
        assertEquals(36, leastCommonMultiple(18, 12))
        assertEquals(105, leastCommonMultiple(15, 35))
    }

    @Test
    fun testLeastCommonMultipleOfTwoInvalidInts() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0, 0) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1, 0) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0, -1) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1, -1) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1, 0) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0, 1) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1, -1) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1, 1) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(2, 0) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0, 2) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(2, -1) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1, 2) }
    }

    @Test
    fun testLeastCommonMultipleOfTwoLongs() {
        assertEquals(1L, leastCommonMultiple(1L, 1L))
        assertEquals(2L, leastCommonMultiple(1L, 2L))
        assertEquals(2L, leastCommonMultiple(2L, 1L))
        assertEquals(6L, leastCommonMultiple(2L, 3L))
        assertEquals(6L, leastCommonMultiple(3L, 2L))
        assertEquals(4L, leastCommonMultiple(2L, 4L))
        assertEquals(12L, leastCommonMultiple(3L, 4L))
        assertEquals(12L, leastCommonMultiple(6L, 4L))
        assertEquals(42L, leastCommonMultiple(6L, 21))
        assertEquals(36L, leastCommonMultiple(18L, 12L))
        assertEquals(105L, leastCommonMultiple(15L, 35L))
        assertEquals(20608307442L, leastCommonMultiple(55250154L, 21071889L))
    }

    @Test
    fun testLeastCommonMultipleOfTwoInvalidLongs() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0L, 0L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1L, 0L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0L, -1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1L, -1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 0L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0L, 1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, -1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1L, 1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(2L, 0L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0L, 2L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(2L, -1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1L, 2L) }
    }

    @Test
    fun testLeastCommonMultipleOfSeveralLongs() {
        assertEquals(1L, leastCommonMultiple(1L, 1L, 1L))
        assertEquals(2L, leastCommonMultiple(2L, 1L, 1L))
        assertEquals(64L, leastCommonMultiple(2L, 64L, 8L))
        assertEquals(504L, leastCommonMultiple(8L, 9L, 7L))
        assertEquals(420L, leastCommonMultiple(4L, 7L, 2L, 5L, 3L))
        assertEquals(4871660667720L, leastCommonMultiple(540330L, 424130L, 465962L, 357896L))
    }

    @Test
    fun testLeastCommonMultipleOfSeveralInvalidLongs() {
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0L, 1L, 2L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 0L, 2L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 2L, 0L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0L, 0L, 1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 0L, 0L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(0L, 0L, 0L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 2L, 3L, 0L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 2L, 3L, 4L, 0L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 2L, 3L, 0L, 4L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1L, 1L, 2L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, -1L, 2L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 2L, -1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1L, -1L, 1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, -1L, -1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(-1L, -1L, -1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 2L, 3L, -1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 2L, 3L, 4L, -1L) }
        assertThrows<IllegalArgumentException> { leastCommonMultiple(1L, 2L, 3L, -1L, 4L) }
    }
}
