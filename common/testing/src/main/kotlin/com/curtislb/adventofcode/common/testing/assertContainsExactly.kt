package com.curtislb.adventofcode.common.testing

import kotlin.test.assertEquals
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.MatcherAssert.assertThat

/**
 * Checks that [actual] contains exactly the same items as [expected], in any order.
 */
fun <T> assertContainsExactly(expected: Collection<T>, actual: Collection<T>) {
    val message = """
        Expected items: $expected
        Actual items: $actual
    """.trimIndent()

    assertEquals(expected.size, actual.size, message)
    for (expectedItem in expected) {
        assertThat(message, actual, hasItem(expectedItem))
    }
}
