package com.curtislb.adventofcode.common.testing

import kotlin.math.abs
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Checks that [actual] is non-null and within [precision] of [expected].
 */
fun assertAlmostEquals(expected: Double, actual: Double?, precision: Double = 0.000001) {
    val message = "Expected: $expected (+/- $precision)\nActual: $actual"
    assertNotNull(actual, message)
    assertTrue(abs(expected - actual) < precision, message)
}
