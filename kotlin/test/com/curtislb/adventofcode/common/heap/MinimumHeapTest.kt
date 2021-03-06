package com.curtislb.adventofcode.common.heap

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * Tests [MinimumHeap].
 */
class MinimumHeapTest {
    private lateinit var heap: MinimumHeap<String>

    @Before
    fun setUp() {
        heap = MinimumHeap()
    }

    @Test
    fun testWhenEmpty() {
        assertEquals(0, heap.size)
        assertTrue(heap.isEmpty())
        assertNull(heap["foo"])
    }

    @Test
    fun testAddDecreaseAndPop() {
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

    @Test(expected = IllegalArgumentException::class)
    fun testAddExistingValue() {
        heap.add("foo", 2L)
        heap.add("foo", 3L)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testDecreaseKeyWithUnknownValue() {
        heap.add("foo", 2L)
        heap.decreaseKey("bar", 1L)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testDecreaseKeyToSameKey() {
        heap.add("foo", 2L)
        heap.decreaseKey("foo", 2L)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testDecreaseKeyToGreaterKey() {
        heap.add("foo", 2L)
        heap.decreaseKey("foo", 3L)
    }

    @Test(expected = NoSuchElementException::class)
    fun testPopMinimumWhenEmpty() {
        heap.popMinimum()
    }
}
