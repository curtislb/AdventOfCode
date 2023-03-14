package com.curtislb.adventofcode.common.iteration

import com.curtislb.adventofcode.common.testing.containsExactlyInAnyOrderUnorderedPairs
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests [uniquePairs].
 */
class UniquePairsTest {
    @Test
    fun uniquePairs_emptyList() {
        val list = emptyList<Any>()
        assertThat(list.uniquePairs().toList()).isEmpty()
    }

    @Test
    fun uniquePairs_singletonList() {
        val list = listOf("foo")
        assertThat(list.uniquePairs().toList()).isEmpty()
    }

    @Test
    fun uniquePairs_twoElementList() {
        val list = listOf("foo", "bar")
        assertThat(list.uniquePairs().toList())
            .containsExactlyInAnyOrderUnorderedPairs(Pair("foo", "bar"))
    }

    @Test
    fun uniquePairs_threeElementList_noDuplicates() {
        val list = listOf("foo", "bar", "baz")
        assertThat(list.uniquePairs().toList()).containsExactlyInAnyOrderUnorderedPairs(
            Pair("foo", "bar"),
            Pair("foo", "baz"),
            Pair("bar", "baz")
        )
    }

    @Test
    fun uniquePairs_threeElementList_withDuplicates() {
        val list = listOf("foo", "bar", "foo")
        assertThat(list.uniquePairs().toList()).containsExactlyInAnyOrderUnorderedPairs(
            Pair("foo", "bar"),
            Pair("foo", "foo"),
            Pair("bar", "foo")
        )
    }

    @Test
    fun uniquePairs_fourElementList_noDuplicates() {
        val list = listOf("foo", "bar", "baz", "qux")
        assertThat(list.uniquePairs().toList()).containsExactlyInAnyOrderUnorderedPairs(
            Pair("foo", "bar"),
            Pair("foo", "baz"),
            Pair("foo", "qux"),
            Pair("bar", "baz"),
            Pair("bar", "qux"),
            Pair("baz", "qux")
        )
    }

    @Test
    fun uniquePairs_fourElementList_withDuplicates() {
        val list = listOf("foo", "foo", "bar", "bar")
        assertThat(list.uniquePairs().toList()).containsExactlyInAnyOrderUnorderedPairs(
            Pair("foo", "foo"),
            Pair("foo", "bar"),
            Pair("foo", "bar"),
            Pair("foo", "bar"),
            Pair("foo", "bar"),
            Pair("bar", "bar")
        )
    }
}
