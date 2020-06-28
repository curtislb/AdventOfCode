package com.curtislb.adventofcode.common.math

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests [primeFactorization].
 */
class PrimeFactorizationTest {
    @Test fun testPrimeFactorization() {
        assertEquals(2L.primeFactorization(), mapOf(Pair(2L, 1)))
        assertEquals(3L.primeFactorization(), mapOf(Pair(3L, 1)))
        assertEquals(4L.primeFactorization(), mapOf(Pair(2L, 2)))
        assertEquals(5L.primeFactorization(), mapOf(Pair(5L, 1)))
        assertEquals(6L.primeFactorization(), mapOf(Pair(2L, 1), Pair(3L, 1)))
        assertEquals(12L.primeFactorization(), mapOf(Pair(2L, 2), Pair(3L, 1)))
        assertEquals(2903L.primeFactorization(), mapOf(Pair(2903L, 1)))
        assertEquals(61740L.primeFactorization(), mapOf(Pair(2L, 2), Pair(3L, 2), Pair(5L, 1), Pair(7L, 3)))
        assertEquals(4928693L.primeFactorization(), mapOf(Pair(7L, 1), Pair(11L, 3), Pair(23L, 2)))
        assertEquals(169165232L.primeFactorization(), mapOf(Pair(2L, 4), Pair(17L, 1), Pair(313L, 1), Pair(1987L, 1)))
        assertEquals(
            74435892358158L.primeFactorization(),
            mapOf(Pair(2L, 1), Pair(3L, 3), Pair(29L, 1), Pair(3049L, 2), Pair(5113L, 1))
        )
    }
}
