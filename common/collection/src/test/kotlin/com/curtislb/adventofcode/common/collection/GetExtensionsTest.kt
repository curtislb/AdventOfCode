package com.curtislb.adventofcode.common.collection

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [getCyclic] and [getOrNull].
 */
class GetExtensionsTest {
    @Test
    fun getCyclic_emptyList() {
        assertThrows<NoSuchElementException> { emptyList<Any>().getCyclic(0) }
    }

    @Test
    fun getCyclic_singletonList_indexInBounds() {
        val list = listOf("foo")
        assertThat(list.getCyclic(0)).isEqualTo("foo")
    }

    @Test
    fun getCyclic_singletonList_indexJustTooLarge() {
        val list = listOf("foo")
        assertThat(list.getCyclic(1)).isEqualTo("foo")
    }

    @Test
    fun getCyclic_singletonList_indexJustTooSmall() {
        val list = listOf("foo")
        assertThat(list.getCyclic(-1)).isEqualTo("foo")
    }

    @Test
    fun getCyclic_singletonList_indexFarTooLarge() {
        val list = listOf("foo")
        assertThat(list.getCyclic(3)).isEqualTo("foo")
    }

    @Test
    fun getCyclic_singletonList_indexFarTooSmall() {
        val list = listOf("foo")
        assertThat(list.getCyclic(-3)).isEqualTo("foo")
    }

    @Test
    fun getCyclic_multiElementList_indexInBounds() {
        val list = listOf("foo", "bar", "baz")
        assertThat(list.getCyclic(1)).isEqualTo("bar")
    }

    @Test
    fun getCyclic_multiElementList_indexJustTooLarge() {
        val list = listOf("foo", "bar", "baz")
        assertThat(list.getCyclic(3)).isEqualTo("foo")
    }

    @Test
    fun getCyclic_multiElementList_indexJustTooSmall() {
        val list = listOf("foo", "bar", "baz")
        assertThat(list.getCyclic(-1)).isEqualTo("baz")
    }

    @Test
    fun getCyclic_multiElementList_indexFarTooLarge() {
        val list = listOf("foo", "bar", "baz")
        assertThat(list.getCyclic(8)).isEqualTo("baz")
    }

    @Test
    fun getCyclic_multiElementList_indexFarTooSmall() {
        val list = listOf("foo", "bar", "baz")
        assertThat(list.getCyclic(-5)).isEqualTo("bar")
    }

    @Test
    fun getOrNull_emptyList() {
        val rowList = emptyList<List<Any>>()
        assertThat(rowList.getOrNull(0, 0)).isNull()
    }

    @Test
    fun getOrNull_oneByOne_bothIndicesInBounds() {
        val rowList = listOf(listOf("foo"))
        assertThat(rowList.getOrNull(0, 0)).isEqualTo("foo")
    }

    @Test
    fun getOrNull_oneByOne_rowIndexTooLarge() {
        val rowList = listOf(listOf("foo"))
        assertThat(rowList.getOrNull(1, 0)).isNull()
    }

    @Test
    fun getOrNull_oneByOne_rowIndexTooSmall() {
        val rowList = listOf(listOf("foo"))
        assertThat(rowList.getOrNull(-1, 0)).isNull()
    }

    @Test
    fun getOrNull_oneByOne_colIndexTooLarge() {
        val rowList = listOf(listOf("foo"))
        assertThat(rowList.getOrNull(0, 1)).isNull()
    }

    @Test
    fun getOrNull_oneByOne_colIndexTooSmall() {
        val rowList = listOf(listOf("foo"))
        assertThat(rowList.getOrNull(0, -1)).isNull()
    }

    @Test
    fun getOrNull_oneByOne_bothIndicesOutOfBounds() {
        val rowList = listOf(listOf("foo"))
        assertThat(rowList.getOrNull(-1, 1)).isNull()
    }

    @Test
    fun getOrNull_rectangular_bothIndicesInBounds() {
        val rowList = listOf(
            listOf("foo", "bar", "baz"),
            listOf("qux", "quux", "fred")
        )
        assertThat(rowList.getOrNull(1, 2)).isEqualTo("fred")
    }

    @Test
    fun getOrNull_rectangular_rowIndexTooLarge() {
        val rowList = listOf(
            listOf("foo", "bar", "baz"),
            listOf("qux", "quux", "fred")
        )
        assertThat(rowList.getOrNull(2, 2)).isNull()
    }

    @Test
    fun getOrNull_rectangular_rowIndexTooSmall() {
        val rowList = listOf(
            listOf("foo", "bar", "baz"),
            listOf("qux", "quux", "fred")
        )
        assertThat(rowList.getOrNull(-1, 2)).isNull()
    }

    @Test
    fun getOrNull_rectangular_colIndexTooLarge() {
        val rowList = listOf(
            listOf("foo", "bar", "baz"),
            listOf("qux", "quux", "fred")
        )
        assertThat(rowList.getOrNull(1, 3)).isNull()
    }

    @Test
    fun getOrNull_rectangular_colIndexTooSmall() {
        val rowList = listOf(
            listOf("foo", "bar", "baz"),
            listOf("qux", "quux", "fred")
        )
        assertThat(rowList.getOrNull(1, -1)).isNull()
    }

    @Test
    fun getOrNull_rectangular_bothIndicesOutOfBounds() {
        val rowList = listOf(
            listOf("foo", "bar", "baz"),
            listOf("qux", "quux", "fred")
        )
        assertThat(rowList.getOrNull(2, -1)).isNull()
    }
    @Test
    fun getOrNull_irregular_bothIndicesInBounds() {
        val rowList = listOf(
            listOf("foo", "bar", "baz"),
            listOf("qux"),
            listOf("quux", "fred")
        )
        assertThat(rowList.getOrNull(2, 1)).isEqualTo("fred")
    }

    @Test
    fun getOrNull_irregular_rowIndexTooLarge() {
        val rowList = listOf(
            listOf("foo", "bar", "baz"),
            listOf("qux"),
            listOf("quux", "fred")
        )
        assertThat(rowList.getOrNull(3, 0)).isNull()
    }

    @Test
    fun getOrNull_irregular_rowIndexTooSmall() {
        val rowList = listOf(
            listOf("foo", "bar", "baz"),
            listOf("qux"),
            listOf("quux", "fred")
        )
        assertThat(rowList.getOrNull(-1, 0)).isNull()
    }

    @Test
    fun getOrNull_irregular_colIndexTooLarge() {
        val rowList = listOf(
            listOf("foo", "bar", "baz"),
            listOf("qux"),
            listOf("quux", "fred")
        )
        assertThat(rowList.getOrNull(1, 1)).isNull()
    }

    @Test
    fun getOrNull_irregular_colIndexTooSmall() {
        val rowList = listOf(
            listOf("foo", "bar", "baz"),
            listOf("qux"),
            listOf("quux", "fred")
        )
        assertThat(rowList.getOrNull(1, -1)).isNull()
    }

    @Test
    fun getOrNull_irregular_bothIndicesOutOfBounds() {
        val rowList = listOf(
            listOf("foo", "bar", "baz"),
            listOf("qux"),
            listOf("quux", "fred")
        )
        assertThat(rowList.getOrNull(3, -1)).isNull()
    }
}
