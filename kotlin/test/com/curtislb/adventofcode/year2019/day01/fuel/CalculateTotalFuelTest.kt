package com.curtislb.adventofcode.year2019.day01.fuel

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests [calculateTotalFuel].
 */
class CalculateTotalFuelTest {
    @Test
    fun testCalculateTotalFuel() {
        assertEquals(0, calculateTotalFuel(0))
        assertEquals(0, calculateTotalFuel(1))
        assertEquals(0, calculateTotalFuel(2))
        assertEquals(0, calculateTotalFuel(6))
        assertEquals(1, calculateTotalFuel(9))
        assertEquals(1, calculateTotalFuel(10))
        assertEquals(1, calculateTotalFuel(11))
        assertEquals(2, calculateTotalFuel(12))
        assertEquals(2, calculateTotalFuel(14))
        assertEquals(8, calculateTotalFuel(32))
        assertEquals(10, calculateTotalFuel(33))
        assertEquals(40, calculateTotalFuel(104))
        assertEquals(43, calculateTotalFuel(105))
        assertEquals(966, calculateTotalFuel(1969))
        assertEquals(50346, calculateTotalFuel(100756))
    }
}
