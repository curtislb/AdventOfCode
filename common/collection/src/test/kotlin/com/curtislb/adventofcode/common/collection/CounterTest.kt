package com.curtislb.adventofcode.common.collection

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Tests [Counter].
 */
class CounterTest {
    private lateinit var counter: Counter<String>

    @BeforeEach
    fun setUp() {
        counter = Counter()
    }

    @Test
    fun entries_whenEmpty() {
        assertThat(counter.entries).isEmpty()
    }

    @Test
    fun entries_whenNotEmpty() {
        counter["foo"] = 1L
        counter["bar"] = 2L
        counter["baz"] = -2L
        counter["bar"] = 3L
        counter["qux"] = 0L

        assertThat(counter.entries)
            .map<Pair<String, Long>> { it.toPair() }
            .containsExactlyInAnyOrder("foo" to 1L, "bar" to 3L, "baz" to -2L)
    }

    @Test
    fun keys_whenEmpty() {
        assertThat(counter.keys).isEmpty()
    }

    @Test
    fun keys_whenNotEmpty() {
        counter["foo"] = 1L
        counter["bar"] = 2L
        counter["baz"] = -2L
        counter["bar"] = 3L
        counter["qux"] = 0L

        assertThat(counter.keys).containsExactlyInAnyOrder("foo", "bar", "baz")
    }

    @Test
    fun contains_keyWithNoCount() {
        counter["foo"] = 1L
        assertThat("bar" in counter).isFalse
    }

    @Test
    fun contains_keyWithPositiveCount() {
        counter["foo"] = 1L
        assertThat("foo" in counter).isTrue
    }

    @Test
    fun contains_keyWithNegativeCount() {
        counter["foo"] = -1L
        assertThat("foo" in counter).isTrue
    }

    @Test
    fun contains_keyWithZeroCount() {
        counter["foo"] = 0L
        assertThat("foo" in counter).isFalse
    }

    @Test
    fun contains_emptyCounter_whenEmpty() {
        val other = Counter<String>()
        assertThat(other in counter).isTrue
    }

    @Test
    fun contains_emptyCounter_withPositiveCounts() {
        counter["foo"] = 1L
        counter["bar"] = 2L

        val other = Counter<String>()
        assertThat(other in counter).isTrue
    }

    @Test
    fun contains_emptyCounter_withNegativeCount() {
        counter["foo"] = 1L
        counter["bar"] = -1L

        val other = Counter<String>()
        assertThat(other in counter).isFalse
    }

    @Test
    fun contains_self() {
        counter["foo"] = 1L
        counter["bar"] = -1L

        assertThat(counter in counter).isTrue
    }

    @Test
    fun contains_counterWithSameCounts() {
        counter["foo"] = 1L
        counter["bar"] = -1L

        val other = Counter<String>()
        other["foo"] = 1L
        other["bar"] = -1L

        assertThat(other in counter).isTrue
    }

    @Test
    fun contains_counterWithNoGreaterCounts() {
        counter["foo"] = 1L
        counter["bar"] = 2L
        counter["baz"] = 3L
        counter["qux"] = -1L

        val other = Counter<String>()
        other["foo"] = 1L
        other["bar"] = -1L
        other["baz"] = 2L
        other["qux"] = -2L
        other["quux"] = -3L

        assertThat(other in counter).isTrue
    }

    @Test
    fun contains_counterWithGreaterPositiveCount() {
        counter["foo"] = 1L
        counter["bar"] = 2L
        counter["baz"] = 3L
        counter["qux"] = -1L
        counter["quux"] = -2L

        val other = Counter<String>()
        other["foo"] = 1L
        other["bar"] = 3L
        other["baz"] = 3L
        other["qux"] = -1L
        other["quux"] = -2L

        assertThat(other in counter).isFalse
    }

    @Test
    fun contains_counterWithGreaterNegativeCount() {
        counter["foo"] = 1L
        counter["bar"] = 2L
        counter["baz"] = 3L
        counter["qux"] = -1L
        counter["quux"] = -2L

        val other = Counter<String>()
        other["foo"] = 1L
        other["bar"] = 2L
        other["baz"] = 3L
        other["qux"] = -1L
        other["quux"] = -1L

        assertThat(other in counter).isFalse
    }

    @Test
    fun contains_counterWithGreaterZeroCount() {
        counter["foo"] = 1L
        counter["bar"] = 2L
        counter["baz"] = 3L
        counter["qux"] = -1L
        counter["quux"] = -2L

        val other = Counter<String>()
        other["foo"] = 1L
        other["bar"] = 2L
        other["baz"] = 3L
        other["qux"] = -1L

        assertThat(other in counter).isFalse
    }

    @Test
    fun get_keyWithNoCount() {
        counter["foo"] = 1L
        assertThat(counter["bar"]).isEqualTo(0L)
    }

    @Test
    fun get_keyWithPositiveCount() {
        counter["foo"] = 1L
        assertThat(counter["foo"]).isEqualTo(1L)
    }

    @Test
    fun get_keyWithNegativeCount() {
        counter["foo"] = -1L
        assertThat(counter["foo"]).isEqualTo(-1L)
    }

    @Test
    fun get_keyWithZeroCount() {
        counter["foo"] = 0L
        assertThat(counter["foo"]).isEqualTo(0L)
    }

    @Test
    fun addAll_emptyList() {
        counter["foo"] = 1L
        counter["bar"] = -1L

        counter.addAll(emptyList())

        assertThat(counter.entries)
            .map<Pair<String, Long>> { it.toPair() }
            .containsExactlyInAnyOrder("foo" to 1L, "bar" to -1L)
    }

    @Test
    fun addAll_nonEmptyList() {
        counter["foo"] = 1L
        counter["bar"] = -1L
        counter["baz"] = -2L

        val keys = listOf("foo", "bar", "foo", "baz")
        counter.addAll(keys)

        assertThat(counter.entries)
            .map<Pair<String, Long>> { it.toPair() }
            .containsExactlyInAnyOrder("foo" to 3L, "baz" to -1L)
    }

    @Test
    fun addCounts_emptyCounter() {
        counter["foo"] = 1L
        counter["bar"] = -1L
        counter["baz"] = 2L

        val other = Counter<String>()
        counter.addCounts(other)

        assertThat(counter.entries)
            .map<Pair<String, Long>> { it.toPair() }
            .containsExactlyInAnyOrder("foo" to 1L, "bar" to -1L, "baz" to 2L)
    }

    @Test
    fun addCounts_whenEmpty() {
        val other = Counter<String>()
        other["foo"] = 1L
        other["bar"] = -1L
        other["baz"] = 2L

        counter.addCounts(other)

        assertThat(counter.entries)
            .map<Pair<String, Long>> { it.toPair() }
            .containsExactlyInAnyOrder("foo" to 1L, "bar" to -1L, "baz" to 2L)
    }

    @Test
    fun addCounts_whenNotEmpty() {
        counter["foo"] = 1L
        counter["bar"] = -1L
        counter["baz"] = 2L
        counter["qux"] = 3L

        val other = Counter<String>()
        other["foo"] = 1L
        other["bar"] = -3L
        other["baz"] = -2L
        other["quux"] = 5L

        counter.addCounts(other)

        assertThat(counter.entries)
            .map<Pair<String, Long>> { it.toPair() }
            .containsExactlyInAnyOrder("foo" to 2L, "bar" to -4L, "qux" to 3L, "quux" to 5L)
    }

    @Test
    fun clear_whenEmpty() {
        counter.clear()
        assertThat(counter.entries).isEmpty()
    }

    @Test
    fun clear_whenNotEmpty() {
        counter["foo"] = 1L
        counter["bar"] = -1L
        counter["baz"] = 3L
        counter["qux"] = -2L

        counter.clear()

        assertThat(counter.entries).isEmpty()
    }

    @Test
    fun clearNegative_whenEmpty() {
        counter.clearNegative()
        assertThat(counter.entries).isEmpty()
    }

    @Test
    fun clearNegative_whenNotEmpty() {
        counter["foo"] = 1L
        counter["bar"] = -1L
        counter["baz"] = 3L
        counter["qux"] = -2L

        counter.clearNegative()

        assertThat(counter.entries)
            .map<Pair<String, Long>> { it.toPair() }
            .containsExactlyInAnyOrder("foo" to 1L, "baz" to 3L)
    }

    @Test
    fun getPositiveEntries_whenEmpty() {
        assertThat(counter.getPositiveEntries()).isEmpty()
    }

    @Test
    fun getPositiveEntries_whenNotEmpty() {
        counter["foo"] = 1L
        counter["bar"] = -1L
        counter["baz"] = 3L
        counter["qux"] = -2L

        assertThat(counter.getPositiveEntries())
            .map<Pair<String, Long>> { it.toPair() }
            .containsExactlyInAnyOrder("foo" to 1L, "baz" to 3L)
    }

    @Test
    fun getPositiveKeys_whenEmpty() {
        assertThat(counter.getPositiveKeys()).isEmpty()
    }

    @Test
    fun getPositiveKeys_whenNotEmpty() {
        counter["foo"] = 1L
        counter["bar"] = -1L
        counter["baz"] = 3L
        counter["qux"] = -2L

        assertThat(counter.getPositiveKeys()).containsExactlyInAnyOrder("foo", "baz")
    }

    @Test
    fun toMap_whenEmpty() {
        assertThat(counter.toMap()).isEmpty()
    }

    @Test
    fun toMap_whenNotEmpty() {
        counter["foo"] = 1L
        counter["bar"] = 2L
        counter["baz"] = -2L
        counter["bar"] = 3L
        counter["qux"] = 0L

        assertThat(counter.toMap())
            .containsExactlyInAnyOrderEntriesOf(mapOf("foo" to 1L, "bar" to 3L, "baz" to -2L))
    }

    @Test
    fun toString_whenEmpty() {
        assertThat(counter.toString()).isEqualTo("{}")
    }

    @Test
    fun toString_whenNotEmpty() {
        counter["foo"] = 1L
        counter["bar"] = 2L
        counter["baz"] = -2L
        counter["bar"] = 3L
        counter["qux"] = 0L

        assertThat(counter.toString())
            .contains("foo=1")
            .contains("bar=3")
            .contains("baz=-2")
            .doesNotContain("qux")
    }
}
