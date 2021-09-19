package com.curtislb.adventofcode.year2019.day01.fuel

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests [calculateFuel].
 */
class CalculateFuelTest {
    @Test
    fun testCalculateFuel() {
        assertEquals(0, calculateFuel(0))
        assertEquals(0, calculateFuel(1))
        assertEquals(0, calculateFuel(2))
        assertEquals(0, calculateFuel(6))
        assertEquals(1, calculateFuel(9))
        assertEquals(1, calculateFuel(10))
        assertEquals(1, calculateFuel(11))
        assertEquals(2, calculateFuel(12))
        assertEquals(2, calculateFuel(14))
        assertEquals(654, calculateFuel(1969))
        assertEquals(33583, calculateFuel(100756))
    }
}
