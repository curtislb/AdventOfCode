package com.curtislb.adventofcode.common.math

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests constants provided by [com.curtislb.adventofcode.common.math].
 */
class ConstantsTest {
    @Test
    fun testDecimalDigits() {
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), DECIMAL_DIGITS)
    }
}
