package com.curtislb.adventofcode.common.math

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [primeFactorization].
 */
class PrimeFactorizationTest {
    @Test
    fun testPrimeFactorizationOfLong() {
        assertEquals(emptyMap(), 1L.primeFactorization())
        assertEquals(mapOf(2L to 1), 2L.primeFactorization())
        assertEquals(mapOf(3L to 1), 3L.primeFactorization())
        assertEquals(mapOf(2L to 2), 4L.primeFactorization())
        assertEquals(mapOf(5L to 1), 5L.primeFactorization())
        assertEquals(mapOf(2L to 1, 3L to 1), 6L.primeFactorization())
        assertEquals(mapOf(2L to 2, 3L to 1), 12L.primeFactorization())
        assertEquals(mapOf(2903L to 1), 2903L.primeFactorization())
        assertEquals(mapOf(2L to 2, 3L to 2, 5L to 1, 7L to 3), 61740L.primeFactorization())
        assertEquals(mapOf(7L to 1, 11L to 3, 23L to 2), 4928693L.primeFactorization())
        assertEquals(
            mapOf(2L to 4, 17L to 1, 313L to 1, 1987L to 1),
            169165232L.primeFactorization()
        )
        assertEquals(
            mapOf(2L to 1, 3L to 3, 29L to 1, 3049L to 2, 5113L to 1),
            74435892358158L.primeFactorization()
        )
    }

    @Test
    fun testPrimeFactorizationOfInvalidLong() {
        assertThrows<IllegalArgumentException> { 0L.primeFactorization() }
        assertThrows<IllegalArgumentException> { (-1L).primeFactorization() }
        assertThrows<IllegalArgumentException> { (-2L).primeFactorization() }
        assertThrows<IllegalArgumentException> { (-3L).primeFactorization() }
    }
}
