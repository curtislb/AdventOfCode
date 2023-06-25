package com.curtislb.adventofcode.common.heap

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests the [MinimumHeap] class.
 */
class MinimumHeapTest {
    private lateinit var heap: MinimumHeap<String>

    @BeforeEach
    fun setUp() {
        heap = MinimumHeap()
    }

    @Test
    fun size_whenEmpty() {
        assertThat(heap.size).isEqualTo(0)
    }

    @Test
    fun size_withOneEntry() {
        heap.addOrDecreaseKey("foo", 0L)
        assertThat(heap.size).isEqualTo(1)
    }

    @Test
    fun size_withMultipleEntries() {
        with(heap) {
            addOrDecreaseKey("foo", 0L)
            addOrDecreaseKey("bar", 1L)
            addOrDecreaseKey("baz", 0L)
        }
        assertThat(heap.size).isEqualTo(3)
    }

    @Test
    fun contains_whenEmpty() {
        assertThat("foo" in heap).isFalse()
    }

    @Test
    fun contains_withElementInHeap() {
        heap.addOrDecreaseKey("foo", 0L)
        assertThat("foo" in heap).isTrue()
    }

    @Test
    fun contains_withElementNotInHeap() {
        heap.addOrDecreaseKey("foo", 0L)
        assertThat("bar" in heap).isFalse()
    }

    @Test
    fun get_whenEmpty() {
        assertThat(heap["foo"]).isNull()
    }

    @Test
    fun get_withElementInHeap() {
        heap.addOrDecreaseKey("foo", 2L)
        assertThat(heap["foo"]).isEqualTo(2L)
    }

    @Test
    fun get_withElementNotInHeap() {
        heap.addOrDecreaseKey("foo", 2L)
        assertThat(heap["bar"]).isNull()
    }

    @Test
    fun isEmpty_whenEmpty() {
        assertThat(heap.isEmpty()).isTrue()
    }

    @Test
    fun isEmpty_whenNotEmpty() {
        heap.addOrDecreaseKey("foo", 0L)
        assertThat(heap.isEmpty()).isFalse()
    }

    @Test
    fun addOrDecreaseKey_withElementInHeap_newKeyGreater() {
        heap.addOrDecreaseKey("foo", 2L)
        assertThrows<IllegalArgumentException> { heap.addOrDecreaseKey("foo", 3L) }
    }

    @Test
    fun addOrDecreaseKey_withElementInHeap_newKeyEqual() {
        heap.addOrDecreaseKey("foo", 2L)
        assertThrows<IllegalArgumentException> { heap.addOrDecreaseKey("foo", 2L) }
    }

    @Test
    fun addOrDecreaseKey_withElementInHeap_newKeyLess_oneEntry() {
        heap.addOrDecreaseKey("foo", 2L)
        heap.addOrDecreaseKey("foo", -1L)
        assertThat(heap["foo"]).isEqualTo(-1L)
    }

    @Test
    fun addOrDecreaseKey_withElementInHeap_newKeyLess_fewEntries() {
        with(heap) {
            addOrDecreaseKey("foo", 2L)
            addOrDecreaseKey("bar", 5L)
            addOrDecreaseKey("foo", -1L)
            addOrDecreaseKey("bar", -2L)
            addOrDecreaseKey("baz", 0L)
        }
        assertThat(heap["foo"]).isEqualTo(-1L)
    }

    @Test
    fun addOrDecreaseKey_withElementInHeap_newKeyLess_manyEntries() {
        with(heap) {
            addOrDecreaseKey("foo", 1L)
            addOrDecreaseKey("bar", 2L)
            addOrDecreaseKey("baz", 3L)
            addOrDecreaseKey("qux", 4L)
            addOrDecreaseKey("qox", 5L)
            addOrDecreaseKey("fred", 6L)
            addOrDecreaseKey("foo", -1L)
            addOrDecreaseKey("bar", -2L)
            addOrDecreaseKey("baz", -3L)
            addOrDecreaseKey("qux", -4L)
            addOrDecreaseKey("qox", -5L)
            addOrDecreaseKey("fred", -6L)
        }
        assertThat(heap["bar"]).isEqualTo(-2L)
    }

    @Test
    fun popMinimum_whenEmpty() {
        assertThrows<NoSuchElementException> { heap.popMinimum() }
    }

    @Test
    fun popMinimum_withOneEntry() {
        heap.addOrDecreaseKey("foo", 2L)
        assertThat(heap.popMinimum()).isEqualTo(HeapEntry("foo", 2L))
    }

    @Test
    fun popMinimum_withMultipleEntries() {
        heap.addOrDecreaseKey("foo", 2L)
        heap.addOrDecreaseKey("bar", -1L)
        heap.addOrDecreaseKey("baz", 0L)
        assertThat(heap.popMinimum()).isEqualTo(HeapEntry("bar", -1L))
    }
}
