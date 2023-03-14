package com.curtislb.adventofcode.common.iteration

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [nestedLoop].
 */
class NestedLoopTest {
    @Test
    fun nestedLoop_emptyList_negativeLevels_overlapping() {
        assertThrows<IllegalArgumentException> {
            nestedLoop(items = emptyList<Any>(), levelCount = -1) { false }
        }
    }

    @Test
    fun nestedLoop_emptyList_negativeLevels_nonOverlapping() {
        assertThrows<IllegalArgumentException> {
            nestedLoop(items = emptyList<Any>(), levelCount = -1, overlapIndices = false) { false }
        }
    }

    @Test
    fun nestedLoop_emptyList_zeroLevels_overlapping() {
        val processedItems = mutableListOf<List<Any>>()

        nestedLoop(items = emptyList(), levelCount = 0) {
            processedItems.add(it)
            false // Keep iterating
        }

        assertThat(processedItems).isEmpty()
    }

    @Test
    fun nestedLoop_emptyList_zeroLevels_nonOverlapping() {
        val processedItems = mutableListOf<List<Any>>()

        nestedLoop(items = emptyList(), levelCount = 0, overlapIndices = false) {
            processedItems.add(it)
            false // Keep iterating
        }

        assertThat(processedItems).isEmpty()
    }

    @Test
    fun nestedLoop_emptyList_oneLevel_overlapping() {
        val processedItems = mutableListOf<List<Any>>()

        nestedLoop(items = emptyList(), levelCount = 1) {
            processedItems.add(it)
            false // Keep iterating
        }

        assertThat(processedItems).isEmpty()
    }

    @Test
    fun nestedLoop_emptyList_oneLevel_nonOverlapping() {
        assertThrows<IllegalArgumentException> {
            nestedLoop(items = emptyList<Any>(), levelCount = 1, overlapIndices = false) { false }
        }
    }

    @Test
    fun nestedLoop_emptyList_twoLevels_overlapping() {
        val processedItems = mutableListOf<List<Any>>()

        nestedLoop(items = emptyList(), levelCount = 2) {
            processedItems.add(it)
            false // Keep iterating
        }

        assertThat(processedItems).isEmpty()
    }

    @Test
    fun nestedLoop_emptyList_twoLevels_nonOverlapping() {
        assertThrows<IllegalArgumentException> {
            nestedLoop(items = emptyList<Any>(), levelCount = 2, overlapIndices = false) { false }
        }
    }

    @Test
    fun nestedLoop_singletonList_negativeLevels_overlapping() {
        assertThrows<IllegalArgumentException> {
            nestedLoop(items = listOf("foo"), levelCount = -1) { false }
        }
    }

    @Test
    fun nestedLoop_singletonList_negativeLevels_nonOverlapping() {
        assertThrows<IllegalArgumentException> {
            nestedLoop(items = listOf("foo"), levelCount = -1, overlapIndices = false) { false }
        }
    }

    @Test
    fun nestedLoop_singletonList_zeroLevels_overlapping() {
        val processedItems = mutableListOf<List<String>>()

        nestedLoop(items = listOf("foo"), levelCount = 0) {
            processedItems.add(it)
            false // Keep iterating
        }

        assertThat(processedItems).isEmpty()
    }

    @Test
    fun nestedLoop_singletonList_zeroLevels_nonOverlapping() {
        val processedItems = mutableListOf<List<String>>()

        nestedLoop(items = listOf("foo"), levelCount = 0, overlapIndices = false) {
            processedItems.add(it)
            false // Keep iterating
        }

        assertThat(processedItems).isEmpty()
    }

    @Test
    fun nestedLoop_singletonList_oneLevel_overlapping() {
        val processedItems = mutableListOf<List<String>>()

        nestedLoop(items = listOf("foo"), levelCount = 1) {
            processedItems.add(it)
            false // Keep iterating
        }

        assertThat(processedItems).containsExactly(listOf("foo"))
    }

    @Test
    fun nestedLoop_singletonList_oneLevel_nonOverlapping() {
        val processedItems = mutableListOf<List<String>>()

        nestedLoop(items = listOf("foo"), levelCount = 1, overlapIndices = false) {
            processedItems.add(it)
            false // Keep iterating
        }

        assertThat(processedItems).containsExactly(listOf("foo"))
    }

    @Test
    fun nestedLoop_singletonList_twoLevels_overlapping() {
        val processedItems = mutableListOf<List<String>>()

        nestedLoop(items = listOf("foo"), levelCount = 2) {
            processedItems.add(it)
            false // Keep iterating
        }

        assertThat(processedItems).containsExactly(listOf("foo", "foo"))
    }

    @Test
    fun nestedLoop_singletonList_twoLevels_nonOverlapping() {
        assertThrows<IllegalArgumentException> {
            nestedLoop(items = listOf("foo"), levelCount = 2, overlapIndices = false) { false }
        }
    }

    @Test
    fun nestedLoop_multiElementList_negativeLevels_overlapping() {
        val items = listOf("foo", "bar", "baz")
        assertThrows<IllegalArgumentException> {
            nestedLoop(items = items, levelCount = -1) { false }
        }
    }

    @Test
    fun nestedLoop_multiElementList_negativeLevels_nonOverlapping() {
        val items = listOf("foo", "bar", "baz")
        assertThrows<IllegalArgumentException> {
            nestedLoop(items = items, levelCount = -1, overlapIndices = false) { false }
        }
    }

    @Test
    fun nestedLoop_multiElementList_zeroLevels_overlapping() {
        val items = listOf("foo", "bar", "baz")
        val processedItems = mutableListOf<List<String>>()

        nestedLoop(items = items, levelCount = 0) {
            processedItems.add(it)
            false // Keep iterating
        }

        assertThat(processedItems).isEmpty()
    }

    @Test
    fun nestedLoop_multiElementList_zeroLevels_nonOverlapping() {
        val items = listOf("foo", "bar", "baz")
        val processedItems = mutableListOf<List<String>>()

        nestedLoop(items = items, levelCount = 0, overlapIndices = false) {
            processedItems.add(it)
            false // Keep iterating
        }

        assertThat(processedItems).isEmpty()
    }

    @Test
    fun nestedLoop_multiElementList_oneLevel_overlapping() {
        val items = listOf("foo", "bar", "baz")
        val processedItems = mutableListOf<List<String>>()

        nestedLoop(items = items, levelCount = 1) {
            processedItems.add(it)
            false // Keep iterating
        }

        assertThat(processedItems).containsExactly(listOf("foo"), listOf("bar"), listOf("baz"))
    }

    @Test
    fun nestedLoop_multiElementList_oneLevel_nonOverlapping() {
        val items = listOf("foo", "bar", "baz")
        val processedItems = mutableListOf<List<String>>()

        nestedLoop(items = items, levelCount = 1, overlapIndices = false) {
            processedItems.add(it)
            false // Keep iterating
        }

        assertThat(processedItems).containsExactly(listOf("foo"), listOf("bar"), listOf("baz"))
    }

    @Test
    fun nestedLoop_multiElementList_twoLevels_overlapping() {
        val items = listOf("foo", "bar", "baz")
        val processedItems = mutableListOf<List<String>>()

        nestedLoop(items = items, levelCount = 2) {
            processedItems.add(it)
            false // Keep iterating
        }

        assertThat(processedItems).containsExactly(
            listOf("foo", "foo"),
            listOf("foo", "bar"),
            listOf("foo", "baz"),
            listOf("bar", "foo"),
            listOf("bar", "bar"),
            listOf("bar", "baz"),
            listOf("baz", "foo"),
            listOf("baz", "bar"),
            listOf("baz", "baz"),
        )
    }

    @Test
    fun nestedLoop_multiElementList_twoLevels_overlapping_returnEarly() {
        val items = listOf("foo", "bar", "baz")
        val processedItems = mutableListOf<List<String>>()

        nestedLoop(items = items, levelCount = 2) {
            processedItems.add(it)
            processedItems.size >= 5
        }

        assertThat(processedItems).containsExactly(
            listOf("foo", "foo"),
            listOf("foo", "bar"),
            listOf("foo", "baz"),
            listOf("bar", "foo"),
            listOf("bar", "bar"),
        )
    }

    @Test
    fun nestedLoop_multiElementList_twoLevels_nonOverlapping() {
        val items = listOf("foo", "bar", "baz")
        val processedItems = mutableListOf<List<String>>()

        nestedLoop(items = items, levelCount = 2, overlapIndices = false) {
            processedItems.add(it)
            false // Keep iterating
        }

        assertThat(processedItems)
            .containsExactly(listOf("foo", "bar"), listOf("foo", "baz"), listOf("bar", "baz"))
    }

    @Test
    fun nestedLoop_multiElementList_twoLevels_nonOverlapping_returnEarly() {
        val items = listOf("foo", "bar", "baz")
        val processedItems = mutableListOf<List<String>>()

        nestedLoop(items = items, levelCount = 2, overlapIndices = false) {
            processedItems.add(it)
            processedItems.size >= 2 // Keep iterating
        }

        assertThat(processedItems).containsExactly(listOf("foo", "bar"), listOf("foo", "baz"))
    }

    @Test
    fun nestedLoop_multiElementList_threeLevels_overlapping() {
        val items = listOf("foo", "bar", "baz")
        val processedItems = mutableListOf<List<String>>()

        nestedLoop(items = items, levelCount = 3) {
            processedItems.add(it)
            false // Keep iterating
        }

        assertThat(processedItems).containsExactly(
            listOf("foo", "foo", "foo"),
            listOf("foo", "foo", "bar"),
            listOf("foo", "foo", "baz"),
            listOf("foo", "bar", "foo"),
            listOf("foo", "bar", "bar"),
            listOf("foo", "bar", "baz"),
            listOf("foo", "baz", "foo"),
            listOf("foo", "baz", "bar"),
            listOf("foo", "baz", "baz"),
            listOf("bar", "foo", "foo"),
            listOf("bar", "foo", "bar"),
            listOf("bar", "foo", "baz"),
            listOf("bar", "bar", "foo"),
            listOf("bar", "bar", "bar"),
            listOf("bar", "bar", "baz"),
            listOf("bar", "baz", "foo"),
            listOf("bar", "baz", "bar"),
            listOf("bar", "baz", "baz"),
            listOf("baz", "foo", "foo"),
            listOf("baz", "foo", "bar"),
            listOf("baz", "foo", "baz"),
            listOf("baz", "bar", "foo"),
            listOf("baz", "bar", "bar"),
            listOf("baz", "bar", "baz"),
            listOf("baz", "baz", "foo"),
            listOf("baz", "baz", "bar"),
            listOf("baz", "baz", "baz"),
        )
    }

    @Test
    fun nestedLoop_multiElementList_threeLevels_overlapping_returnEarly() {
        val items = listOf("foo", "bar", "baz")
        val processedItems = mutableListOf<List<String>>()

        nestedLoop(items = items, levelCount = 3) {
            processedItems.add(it)
            processedItems.size >= 7
        }

        assertThat(processedItems).containsExactly(
            listOf("foo", "foo", "foo"),
            listOf("foo", "foo", "bar"),
            listOf("foo", "foo", "baz"),
            listOf("foo", "bar", "foo"),
            listOf("foo", "bar", "bar"),
            listOf("foo", "bar", "baz"),
            listOf("foo", "baz", "foo"),
        )
    }

    @Test
    fun nestedLoop_multiElementList_threeLevels_nonOverlapping() {
        val items = listOf("foo", "bar", "baz")
        val processedItems = mutableListOf<List<String>>()

        nestedLoop(items = items, levelCount = 3, overlapIndices = false) {
            processedItems.add(it)
            false // Keep iterating
        }

        assertThat(processedItems).containsExactly(listOf("foo", "bar", "baz"))
    }
}
