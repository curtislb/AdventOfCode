package com.curtislb.adventofcode.common.collection

import com.curtislb.adventofcode.common.testing.assertContainsExactly
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests [permutations].
 */
class PermutationsTest {
    @Test
    fun testWithNoItems() {
        assertEquals(emptySet(), emptyList<Nothing>().permutations().toSet())
    }

    @Test
    fun testWithOneItem() {
        assertEquals(setOf(listOf('#')), listOf('#').permutations().toSet())
    }

    @Test
    fun testWithTwoItems() {
        val expected = listOf(listOf("foo", "bar"), listOf("bar", "foo"))
        for (list in expected) {
            val actual = list.permutations().toList()
            assertContainsExactly(expected, actual)
        }
    }

    @Test
    fun testWithThreeItems() {
        val expected = listOf(
            listOf(1, 2, 3),
            listOf(1, 3, 2),
            listOf(2, 1, 3),
            listOf(2, 3, 1),
            listOf(3, 1, 2),
            listOf(3, 2, 1)
        )
        for (list in expected) {
            val actual = list.permutations().toList()
            assertContainsExactly(expected, actual)
        }
    }
}
