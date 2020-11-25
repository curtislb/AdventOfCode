package com.curtislb.adventofcode.common.collection

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests [deepCopy].
 */
class DeepCopyTest {
    @Test
    fun testWithNoItems() {
        assertEquals(emptyList(), emptyList<Nothing>().deepCopy { it })
    }

    @Test
    fun testWithImmutableItems() {
        assertEquals(listOf(1, 2, 3, 4), listOf(1, 2, 3, 4).deepCopy { it })
    }

    @Test
    fun testWithMutableItems() {
        val list = listOf(mutableListOf("a", "b", "c"), mutableListOf("x", "y", "z"))
        val copy = list.deepCopy { it.toMutableList() }
        list[0].add("d")
        list[1].removeAt(2)
        assertEquals(listOf(mutableListOf("a", "b", "c"), mutableListOf("x", "y", "z")), copy)
    }
}
