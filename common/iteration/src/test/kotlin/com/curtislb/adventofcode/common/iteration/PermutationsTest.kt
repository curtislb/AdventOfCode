package com.curtislb.adventofcode.common.iteration

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests the [permutations] extension function.
 */
class PermutationsTest {
    @Test
    fun permutations_emptyList() {
        val list = emptyList<Any>()
        assertThat(list.permutations().toList()).isEmpty()
    }

    @Test
    fun permutations_singletonList() {
        val list = listOf("foo")
        assertThat(list.permutations().toList()).containsExactly(listOf("foo"))
    }

    @Test
    fun permutations_twoElementList_noDuplicates() {
        val list = listOf("foo", "bar")
        assertThat(list.permutations().toList())
            .containsExactlyInAnyOrder(listOf("foo", "bar"), listOf("bar", "foo"))
    }

    @Test
    fun permutations_twoElementList_withDuplicates() {
        val list = listOf("foo", "foo")
        assertThat(list.permutations().toList())
            .containsExactlyInAnyOrder(listOf("foo", "foo"), listOf("foo", "foo"))
    }

    @Test
    fun permutations_threeElementList_noDuplicates() {
        val list = listOf("foo", "bar", "baz")
        assertThat(list.permutations().toList()).containsExactlyInAnyOrder(
            listOf("foo", "bar", "baz"),
            listOf("foo", "baz", "bar"),
            listOf("bar", "foo", "baz"),
            listOf("bar", "baz", "foo"),
            listOf("baz", "foo", "bar"),
            listOf("baz", "bar", "foo")
        )
    }

    @Test
    fun permutations_threeElementList_withDuplicates() {
        val list = listOf("foo", "bar", "foo")
        assertThat(list.permutations().toList()).containsExactlyInAnyOrder(
            listOf("foo", "bar", "foo"),
            listOf("foo", "foo", "bar"),
            listOf("bar", "foo", "foo"),
            listOf("bar", "foo", "foo"),
            listOf("foo", "foo", "bar"),
            listOf("foo", "bar", "foo")
        )
    }

    @Test
    fun permutations_fourElementList_noDuplicates() {
        val list = listOf("foo", "bar", "baz", "qux")
        assertThat(list.permutations().toList()).containsExactlyInAnyOrder(
            listOf("foo", "bar", "baz", "qux"),
            listOf("foo", "bar", "qux", "baz"),
            listOf("foo", "qux", "bar", "baz"),
            listOf("qux", "foo", "bar", "baz"),
            listOf("foo", "baz", "bar", "qux"),
            listOf("foo", "baz", "qux", "bar"),
            listOf("foo", "qux", "baz", "bar"),
            listOf("qux", "foo", "baz", "bar"),
            listOf("bar", "foo", "baz", "qux"),
            listOf("bar", "foo", "qux", "baz"),
            listOf("bar", "qux", "foo", "baz"),
            listOf("qux", "bar", "foo", "baz"),
            listOf("bar", "baz", "foo", "qux"),
            listOf("bar", "baz", "qux", "foo"),
            listOf("bar", "qux", "baz", "foo"),
            listOf("qux", "bar", "baz", "foo"),
            listOf("baz", "foo", "bar", "qux"),
            listOf("baz", "foo", "qux", "bar"),
            listOf("baz", "qux", "foo", "bar"),
            listOf("qux", "baz", "foo", "bar"),
            listOf("baz", "bar", "foo", "qux"),
            listOf("baz", "bar", "qux", "foo"),
            listOf("baz", "qux", "bar", "foo"),
            listOf("qux", "baz", "bar", "foo"),
        )
    }

    @Test
    fun permutations_fourElementList_withDuplicates() {
        val list = listOf("foo", "foo", "bar", "bar")
        assertThat(list.permutations().toList()).containsExactlyInAnyOrder(
            listOf("foo", "foo", "bar", "bar"),
            listOf("foo", "foo", "bar", "bar"),
            listOf("foo", "bar", "foo", "bar"),
            listOf("bar", "foo", "foo", "bar"),
            listOf("foo", "bar", "foo", "bar"),
            listOf("foo", "bar", "bar", "foo"),
            listOf("foo", "bar", "bar", "foo"),
            listOf("bar", "foo", "bar", "foo"),
            listOf("foo", "foo", "bar", "bar"),
            listOf("foo", "foo", "bar", "bar"),
            listOf("foo", "bar", "foo", "bar"),
            listOf("bar", "foo", "foo", "bar"),
            listOf("foo", "bar", "foo", "bar"),
            listOf("foo", "bar", "bar", "foo"),
            listOf("foo", "bar", "bar", "foo"),
            listOf("bar", "foo", "bar", "foo"),
            listOf("bar", "foo", "foo", "bar"),
            listOf("bar", "foo", "bar", "foo"),
            listOf("bar", "bar", "foo", "foo"),
            listOf("bar", "bar", "foo", "foo"),
            listOf("bar", "foo", "foo", "bar"),
            listOf("bar", "foo", "bar", "foo"),
            listOf("bar", "bar", "foo", "foo"),
            listOf("bar", "bar", "foo", "foo"),
        )
    }
}
