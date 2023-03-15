package com.curtislb.adventofcode.common.collection

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests [mapToMap], [mapToSet], and related extension functions.
 */
class MapExtensionsTest {
    @Test
    fun mapToMap_emptyList() {
        val list = emptyList<Any>()
        assertThat(list.mapToMap { it to it }).isEmpty()
    }

    @Test
    fun mapToMap_listWithDistinctElements() {
        val list = listOf("foo", "bar", "baz")
        assertThat(list.mapToMap { it to it.last() })
            .containsExactlyInAnyOrderEntriesOf(mapOf("foo" to 'o', "bar" to 'r', "baz" to 'z'))
    }

    @Test
    fun mapToMap_listWithDuplicateElements() {
        val list = listOf("foo", "bar", "foo", "baz", "bar")
        assertThat(list.mapToMap { it to it.last() })
            .containsExactlyInAnyOrderEntriesOf(mapOf("foo" to 'o', "bar" to 'r', "baz" to 'z'))
    }

    @Test
    fun mapToMap_emptyMap() {
        val map = emptyMap<Any, Any>()
        assertThat(map.mapToMap { it.toPair() }).isEmpty()
    }

    @Test
    fun mapToMap_nonEmptyMap() {
        val map = mapOf("foo" to 2, "bar" to -3, "baz" to 5)
        assertThat(map.mapToMap { -it.value to it.key.uppercase() })
            .containsExactlyInAnyOrderEntriesOf(mapOf(-2 to "FOO", 3 to "BAR", -5 to "BAZ"))
    }

    @Test
    fun mapToMutableSet_emptyList() {
        val list = emptyList<Any>()
        assertThat(list.mapToMutableSet { it }).isEmpty()
    }

    @Test
    fun mapToMutableSet_listWithDistinctElements() {
        val list = listOf("foo", "bar", "baz")
        assertThat(list.mapToMutableSet { it.reversed() }).containsOnly("oof", "rab", "zab")
    }

    @Test
    fun mapToMutableSet_listWithDuplicateElements() {
        val list = listOf("foo", "bar", "foo", "baz", "bar")
        assertThat(list.mapToMutableSet { it.uppercase() }).containsOnly("FOO", "BAR", "BAZ")
    }

    @Test
    fun mapToSet_emptyList() {
        val list = emptyList<Any>()
        assertThat(list.mapToSet { it }).isEmpty()
    }

    @Test
    fun mapToSet_listWithDistinctElements() {
        val list = listOf("foo", "bar", "baz")
        assertThat(list.mapToSet { it.reversed() }).containsOnly("oof", "rab", "zab")
    }

    @Test
    fun mapToSet_listWithDuplicateElements() {
        val list = listOf("foo", "bar", "foo", "baz", "bar")
        assertThat(list.mapToSet { it.uppercase() }).containsOnly("FOO", "BAR", "BAZ")
    }
}
