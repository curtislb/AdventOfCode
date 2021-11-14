package com.curtislb.adventofcode.common.collection

import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [FifoCache].
 */
class FifoCacheTest {
    @Test
    fun testConstructWithInvalidCapacity() {
        assertThrows<IllegalArgumentException> { FifoCache<Any>(capacity = 0) }
        assertThrows<IllegalArgumentException> { FifoCache<Any>(capacity = -1) }
        assertThrows<IllegalArgumentException> { FifoCache<Any>(capacity = -364) }
    }

    @Test
    fun testWhenEmpty() {
        val cache = FifoCache<Any>(capacity = 1)
        assertEquals(1, cache.capacity)
        assertEquals(0, cache.size)
        assertFalse("foo" in cache)
        assertTrue(cache.containsAll(emptyList()))
        assertFalse(cache.containsAll(setOf("bar", "baz")))
        assertTrue(cache.isEmpty())
        assertEquals(0, cache.count())
        assertThrows<IndexOutOfBoundsException> { cache[0] }
        assertFalse(cache.isFull())
    }

    @Test
    fun testAddingItems() {
        val cache = FifoCache<String>(capacity = 2).apply { add("foo") }
        assertEquals(2, cache.capacity)
        assertEquals(1, cache.size)
        assertTrue("foo" in cache)
        assertFalse("bar" in cache)
        assertTrue(cache.containsAll(listOf("foo")))
        assertFalse(cache.containsAll(setOf("foo", "bar")))
        assertFalse(cache.isEmpty())
        assertEquals(1, cache.count())
        assertEquals("foo", cache[0])
        assertThrows<IndexOutOfBoundsException> { cache[1] }
        assertFalse(cache.isFull())

        cache.add("bar")
        assertEquals(2, cache.capacity)
        assertEquals(2, cache.size)
        assertTrue("foo" in cache)
        assertTrue("bar" in cache)
        assertTrue(cache.containsAll(listOf("foo")))
        assertTrue(cache.containsAll(setOf("foo", "bar")))
        assertFalse(cache.isEmpty())
        assertEquals(2, cache.count())
        assertEquals("foo", cache[0])
        assertEquals("bar", cache[1])
        assertThrows<IndexOutOfBoundsException> { cache[2] }
        assertTrue(cache.isFull())

        cache.add("baz")
        assertEquals(2, cache.capacity)
        assertEquals(2, cache.size)
        assertFalse("foo" in cache)
        assertTrue("bar" in cache)
        assertTrue("baz" in cache)
        assertFalse(cache.containsAll(setOf("foo", "bar")))
        assertTrue(cache.containsAll(setOf("bar", "baz")))
        assertFalse(cache.isEmpty())
        assertEquals(2, cache.count())
        assertEquals("bar", cache[0])
        assertEquals("baz", cache[1])
        assertThrows<IndexOutOfBoundsException> { cache[2] }
        assertTrue(cache.isFull())
    }
}