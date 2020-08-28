package com.curtislb.adventofcode.common.math

import org.junit.Test
import kotlin.test.assertTrue

/**
 * Tests constants provided by [com.curtislb.adventofcode.common.math].
 */
class ConstantsTest {
    @Test fun testDecimalDigits() {
        for (digit in 0..9) {
            assertTrue(DECIMAL_DIGITS.contains(digit))
        }
    }
}
