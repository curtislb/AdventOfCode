package com.curtislb.adventofcode.common.collection

import kotlin.test.assertEquals
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests [uniquePairs].
 */
class UniquePairsTest {
    @Test
    fun testWithTwoItems() {
        val actual = listOf("foo", "bar").uniquePairs().toList()
        assertEquals(1, actual.size)
        assertHasPair("foo", "bar", actual)
    }

    @Test
    fun testWithThreeItems() {
        val actual = listOf("lorem", "ipsum", "dolor").uniquePairs().toList()
        assertEquals(3, actual.size)
        assertHasPair("lorem", "ipsum", actual)
        assertHasPair("ipsum", "dolor", actual)
        assertHasPair("lorem", "dolor", actual)
    }

    @Test
    fun testWithFourItems() {
        val actual = listOf(1, 2, 3, 4).uniquePairs().toList()
        assertEquals(6, actual.size)
        assertHasPair(1, 2, actual)
        assertHasPair(1, 3, actual)
        assertHasPair(1, 4, actual)
        assertHasPair(2, 3, actual)
        assertHasPair(2, 4, actual)
        assertHasPair(3, 4, actual)
    }

    companion object {
        /**
         * Checks that the pair [item1], [item2] (in either order) is in an [actual] collection.
         */
        private fun <T> assertHasPair(item1: T, item2: T, actual: Collection<Pair<T, T>>) {
            assertThat(actual, anyOf(hasItem(Pair(item1, item2)), hasItem(Pair(item2, item1))))
        }
    }
}
