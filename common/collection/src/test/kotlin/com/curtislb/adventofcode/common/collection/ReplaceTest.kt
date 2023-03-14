package com.curtislb.adventofcode.common.collection

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests [replaceAt].
 */
class ReplaceTest {
    @Test
    fun replaceAt_emptyList() {
        val list = emptyList<Any>()
        assertThat(list.replaceAt(0) { it }).isEmpty()
    }

    @Test
    fun replaceAt_singletonList_indexInBounds() {
        val list = listOf("foo")
        assertThat(list.replaceAt(0) { it.reversed() }).containsExactly("oof")
    }

    @Test
    fun replaceAt_singletonList_indexTooSmall() {
        val list = listOf("foo")
        assertThat(list.replaceAt(-1) { it.reversed() }).containsExactly("foo")
    }

    @Test
    fun replaceAt_singletonList_indexTooLarge() {
        val list = listOf("foo")
        assertThat(list.replaceAt(1) { it.reversed() }).containsExactly("foo")
    }

    @Test
    fun replaceAt_multiElementList_indexInBounds() {
        val list = listOf("foo", "bar", "baz")
        assertThat(list.replaceAt(1) { it.uppercase() }).containsExactly("foo", "BAR", "baz")
    }

    @Test
    fun replaceAt_multiElementList_indexTooSmall() {
        val list = listOf("foo", "bar", "baz")
        assertThat(list.replaceAt(-1) { it.uppercase() }).containsExactly("foo", "bar", "baz")
    }

    @Test
    fun replaceAt_multiElementList_indexTooLarge() {
        val list = listOf("foo", "bar", "baz")
        assertThat(list.replaceAt(3) { it.uppercase() }).containsExactly("foo", "bar", "baz")
    }
}
