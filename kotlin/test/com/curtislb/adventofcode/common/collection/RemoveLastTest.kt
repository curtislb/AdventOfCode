package com.curtislb.adventofcode.common.collection

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RemoveLastTest {
    @Test fun testWithOneItem() {
        val list = mutableListOf("foo")
        list.removeLast()
        assertTrue(list.isEmpty())
    }

    @Test fun testWithMultipleItems() {
        val list = mutableListOf("lorem", "ipsum", "dolor", "sit")
        list.removeLast()
        assertEquals(mutableListOf("lorem", "ipsum", "dolor"), list)
        list.removeLast()
        assertEquals(mutableListOf("lorem", "ipsum"), list)
        list.removeLast()
        assertEquals(mutableListOf("lorem"), list)
        list.removeLast()
        assertTrue(list.isEmpty())
    }
}
