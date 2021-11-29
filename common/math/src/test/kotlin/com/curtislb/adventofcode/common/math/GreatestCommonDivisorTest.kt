package com.curtislb.adventofcode.common.math

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [greatestCommonDivisor].
 */
class GreatestCommonDivisorTest {
    @Test
    fun testGreatestCommonDivisorOfInts() {
        assertEquals(1, greatestCommonDivisor(1, 1))
        assertEquals(1, greatestCommonDivisor(1, 2))
        assertEquals(2, greatestCommonDivisor(2, 2))
        assertEquals(1, greatestCommonDivisor(2, 3))
        assertEquals(2, greatestCommonDivisor(2, 4))
        assertEquals(4, greatestCommonDivisor(20, 16))
        assertEquals(6, greatestCommonDivisor(54, 24))
        assertEquals(9, greatestCommonDivisor(45, 54))
        assertEquals(15, greatestCommonDivisor(30, 105))
        assertEquals(3581, greatestCommonDivisor(452713601, 662853843))
    }

    @Test
    fun testGreatestCommonDivisorOfInvalidInts() {
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(0, 0) }
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(0, 1) }
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(1, 0) }
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(0, 2) }
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(2, 0) }
    }

    @Test
    fun testGreatestCommonDivisorOfLongs() {
        assertEquals(1L, greatestCommonDivisor(1L, 1L))
        assertEquals(1L, greatestCommonDivisor(1L, 2L))
        assertEquals(2L, greatestCommonDivisor(2L, 2L))
        assertEquals(1L, greatestCommonDivisor(2L, 3L))
        assertEquals(2L, greatestCommonDivisor(2L, 4L))
        assertEquals(4L, greatestCommonDivisor(20L, 16L))
        assertEquals(6L, greatestCommonDivisor(54L, 24L))
        assertEquals(9L, greatestCommonDivisor(45L, 54L))
        assertEquals(15L, greatestCommonDivisor(30L, 105L))
        assertEquals(3581L, greatestCommonDivisor(452713601L, 662853843L))
    }

    @Test
    fun testGreatestCommonDivisorOfInvalidLongs() {
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(0L, 0L) }
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(0L, 1L) }
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(1L, 0L) }
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(0L, 2L) }
        assertThrows<IllegalArgumentException> { greatestCommonDivisor(2L, 0L) }
    }
}
