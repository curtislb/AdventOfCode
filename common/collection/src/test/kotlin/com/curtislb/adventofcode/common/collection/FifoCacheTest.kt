package com.curtislb.adventofcode.common.collection

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests the [FifoCache] class.
 */
class FifoCacheTest {
    @Test
    fun construct_zeroCapacity() {
        assertThrows<IllegalArgumentException> { FifoCache<Any>(capacity = 0) }
    }

    @Test
    fun construct_negativeCapacity() {
        assertThrows<IllegalArgumentException> { FifoCache<Any>(capacity = -1) }
    }

    @Test
    fun size_whenEmpty() {
        val cache = FifoCache<Any>(capacity = 1)
        assertThat(cache).hasSize(0)
    }

    @Test
    fun size_whenNotFull() {
        val cache = FifoCache<String>(capacity = 2).apply { add("foo") }
        assertThat(cache).hasSize(1)
    }

    @Test
    fun size_whenFull() {
        val cache = FifoCache<String>(capacity = 2).apply {
            add("foo")
            add("bar")
            add("baz")
        }
        assertThat(cache).hasSize(2)
    }

    @Test
    fun isEmpty_whenEmpty() {
        val cache = FifoCache<Any>(capacity = 1)
        assertThat(cache).isEmpty()
    }

    @Test
    fun isEmpty_whenNotFull() {
        val cache = FifoCache<String>(capacity = 2).apply { add("foo") }
        assertThat(cache).isNotEmpty
    }

    @Test
    fun isEmpty_whenFull() {
        val cache = FifoCache<String>(capacity = 2).apply {
            add("foo")
            add("bar")
        }
        assertThat(cache).isNotEmpty
    }

    @Test
    fun isEmpty_whenOverFull() {
        val cache = FifoCache<String>(capacity = 2).apply {
            add("foo")
            add("bar")
            add("baz")
        }
        assertThat(cache).isNotEmpty
    }

    @Test
    fun contains_whenEmpty() {
        val cache = FifoCache<String>(capacity = 1)
        assertThat("foo" in cache).isFalse
    }

    @Test
    fun contains_presentElement() {
        val cache = FifoCache<String>(capacity = 2).apply {
            add("foo")
            add("bar")
            add("baz")
        }
        assertThat("bar" in cache).isTrue
    }

    @Test
    fun contains_novelElement() {
        val cache = FifoCache<String>(capacity = 2).apply {
            add("foo")
            add("bar")
            add("baz")
        }
        assertThat("qux" in cache).isFalse
    }

    @Test
    fun contains_evictedElement() {
        val cache = FifoCache<String>(capacity = 2).apply {
            add("foo")
            add("bar")
            add("baz")
        }
        assertThat("foo" in cache).isFalse
    }

    @Test
    fun containsAll_whenEmpty() {
        val cache = FifoCache<Any>(capacity = 1)
        assertThat(cache.containsAll(listOf("foo"))).isFalse
    }

    @Test
    fun containsAll_presentElements() {
        val cache = FifoCache<String>(capacity = 2).apply {
            add("foo")
            add("bar")
            add("baz")
        }

        assertThat(cache.containsAll(listOf("bar", "baz"))).isTrue
    }

    @Test
    fun containsAll_withNovelElement() {
        val cache = FifoCache<String>(capacity = 2).apply {
            add("foo")
            add("bar")
            add("baz")
        }

        val elements = listOf("bar", "qux")
        assertThat(cache.containsAll(elements)).isFalse
    }

    @Test
    fun containsAll_withEvictedElement() {
        val cache = FifoCache<String>(capacity = 2).apply {
            add("foo")
            add("bar")
            add("baz")
        }

        val elements = listOf("foo", "bar", "baz")
        assertThat(cache.containsAll(elements)).isFalse
    }

    @Test
    fun iterator_whenEmpty() {
        val cache = FifoCache<Any>(capacity = 1)

        val elements = mutableListOf<Any>()
        for (element in cache) {
            elements.add(element)
        }

        assertThat(elements).isEmpty()
    }

    @Test
    fun iterator_whenNotFull() {
        val cache = FifoCache<String>(capacity = 2).apply { add("foo") }

        val elements = mutableListOf<String>()
        for (element in cache) {
            elements.add(element)
        }

        assertThat(elements).containsExactly("foo")
    }

    @Test
    fun iterator_whenFull() {
        val cache = FifoCache<String>(capacity = 2).apply {
            add("foo")
            add("bar")
            add("baz")
        }

        val elements = mutableListOf<String>()
        for (element in cache) {
            elements.add(element)
        }

        assertThat(elements).containsExactly("bar", "baz")
    }

    @Test
    fun get_whenEmpty() {
        val cache = FifoCache<Any>(capacity = 1)
        assertThrows<IndexOutOfBoundsException> { cache[0] }
    }

    @Test
    fun get_inBounds_whenNotFull() {
        val cache = FifoCache<String>(capacity = 2).apply { add("foo") }
        assertThat(cache[0]).isEqualTo("foo")
    }

    @Test
    fun get_outOfBounds_whenNotFull() {
        val cache = FifoCache<String>(capacity = 2).apply { add("foo") }
        assertThrows<IndexOutOfBoundsException> { cache[1] }
    }

    @Test
    fun get_inBounds_whenFull() {
        val cache = FifoCache<String>(capacity = 2).apply {
            add("foo")
            add("bar")
            add("baz")
        }

        assertThat(cache[0]).isEqualTo("bar")
        assertThat(cache[1]).isEqualTo("baz")
    }

    @Test
    fun get_outOfBounds_whenFull() {
        val cache = FifoCache<String>(capacity = 2).apply {
            add("foo")
            add("bar")
            add("baz")
        }
        assertThrows<IndexOutOfBoundsException> { cache[2] }
    }

    @Test
    fun isFull_whenEmpty() {
        val cache = FifoCache<Any>(capacity = 1)
        assertThat(cache.isFull()).isFalse
    }

    @Test
    fun isFull_whenNotFull() {
        val cache = FifoCache<String>(capacity = 2).apply { add("foo") }
        assertThat(cache.isFull()).isFalse
    }

    @Test
    fun isFull_whenFull() {
        val cache = FifoCache<String>(capacity = 2).apply {
            add("foo")
            add("bar")
        }
        assertThat(cache.isFull()).isTrue
    }

    @Test
    fun isFull_whenOverFull() {
        val cache = FifoCache<String>(capacity = 2).apply {
            add("foo")
            add("bar")
            add("baz")
        }
        assertThat(cache.isFull()).isTrue
    }

    @Test
    fun add_duplicateElement_whenNotFull() {
        val cache = FifoCache<String>(capacity = 2).apply {
            add("foo")
            add("foo")
        }
        assertThat(cache).containsExactly("foo", "foo")
    }

    @Test
    fun add_duplicateElement_whenFull() {
        val cache = FifoCache<String>(capacity = 2).apply {
            add("foo")
            add("bar")
            add("bar")
        }
        assertThat(cache).containsExactly("bar", "bar")
    }
}
