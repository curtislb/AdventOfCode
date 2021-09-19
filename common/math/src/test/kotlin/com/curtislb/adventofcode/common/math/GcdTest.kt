package com.curtislb.adventofcode.common.math

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests [greatestCommonDivisor].
 */
class GcdTest {
    @Test
    fun testGreatestCommonDivisorWithLongs() {
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
    fun testGreatestCommonDivisorWithInts() {
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
}
