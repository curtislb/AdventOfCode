package com.curtislb.adventofcode.common.testing

import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.MatcherAssert.assertThat
import kotlin.test.assertEquals

/**
 * Checks that [actual] contains exactly the same items as [expected], in any order.
 */
fun <T> assertContainsExactly(expected: Collection<T>, actual: Collection<T>) {
    assertEquals(expected.size, actual.size)
    for (expectedItem in expected) {
        assertThat(actual, hasItem(expectedItem))
    }
}
