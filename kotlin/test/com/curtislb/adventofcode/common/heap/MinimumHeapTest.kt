package com.curtislb.adventofcode.common.heap

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * Tests [MinimumHeap].
 */
class MinimumHeapTest {
    @Test fun testWhenEmpty() {
        val heap = MinimumHeap<String>()
        assertEquals(0, heap.size)
        assertTrue(heap.isEmpty())
        assertNull(heap["foo"])
    }

    @Test(expected = NoSuchElementException::class)
    fun testPopMinimumWhenEmpty() {
        val heap = MinimumHeap<String>()
        heap.popMinimum()
    }

    @Test(expected = IllegalArgumentException::class)
    fun testDecreaseKeyWithUnknownValue() {
        val heap = MinimumHeap<String>()
        heap.add("foo", 2L)
        heap.decreaseKey("bar", 1L)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testDecreaseKeyToSameKey() {
        val heap = MinimumHeap<String>()
        heap.add("foo", 2L)
        heap.decreaseKey("foo", 2L)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testDecreaseKeyToGreaterKey() {
        val heap = MinimumHeap<String>()
        heap.add("foo", 2L)
        heap.decreaseKey("foo", 3L)
    }

    @Test fun testAddDecreaseAndPop() {
        val heap = MinimumHeap<String>()
        heap.add("lorem", 5L)
        assertEquals(1, heap.size)
        assertFalse(heap.isEmpty())
        assertEquals(5L, heap["lorem"])

        heap.add("ipsum", 22L)
        heap.add("dolor", -93L)
        assertEquals(3, heap.size)
        assertFalse(heap.isEmpty())
        assertEquals(5L, heap["lorem"])
        assertEquals(22L, heap["ipsum"])
        assertEquals(-93L, heap["dolor"])

        assertEquals(HeapEntry("dolor", -93L), heap.popMinimum())
        assertEquals(2, heap.size)
        assertFalse(heap.isEmpty())
        assertEquals(5L, heap["lorem"])
        assertEquals(22L, heap["ipsum"])
        assertNull(heap["dolor"])

        heap.decreaseKey("lorem", -24L)
        heap.add("dolor", 5L)
        heap.decreaseKey("ipsum", 20L)
        heap.decreaseKey("ipsum", 17L)
        assertEquals(3, heap.size)
        assertFalse(heap.isEmpty())
        assertEquals(-24L, heap["lorem"])
        assertEquals(17L, heap["ipsum"])
        assertEquals(5L, heap["dolor"])

        assertEquals(HeapEntry("lorem", -24L), heap.popMinimum())
        assertEquals(HeapEntry("dolor", 5L), heap.popMinimum())
        assertEquals(HeapEntry("ipsum", 17L), heap.popMinimum())
        assertEquals(0, heap.size)
        assertTrue(heap.isEmpty())
        assertNull(heap["lorem"])
        assertNull(heap["ipsum"])
        assertNull(heap["dolor"])
    }
}
