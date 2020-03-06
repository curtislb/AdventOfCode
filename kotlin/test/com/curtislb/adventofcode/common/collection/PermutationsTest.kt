package com.curtislb.adventofcode.common.collection

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder
import org.junit.Test
import kotlin.test.assertEquals

class PermutationsTest {
    @Test fun testWithNoItems() {
        assertEquals(emptySet(), emptyList<Any>().permutations().toSet())
    }

    @Test fun testWithOneItem() {
        assertEquals(setOf(listOf('#')), listOf('#').permutations().toSet())
    }

    @Test fun testWithTwoItems() {
        val expected = arrayOf(listOf("foo", "bar"), listOf("bar", "foo"))
        for (list in expected) {
            val actual = list.permutations().toList()
            assertEquals(expected.size, actual.size)
            assertThat(actual, containsInAnyOrder(*expected))
        }
    }

    @Test fun testWithThreeItems() {
        val expected = arrayOf(
            listOf(1, 2, 3),
            listOf(1, 3, 2),
            listOf(2, 1, 3),
            listOf(2, 3, 1),
            listOf(3, 1, 2),
            listOf(3, 2, 1)
        )
        for (list in expected) {
            val actual = list.permutations().toList()
            assertEquals(expected.size, actual.size)
            assertThat(actual, containsInAnyOrder(*expected))
        }
    }
}
