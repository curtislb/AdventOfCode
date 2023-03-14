package com.curtislb.adventofcode.common.collection

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [ValueSequencer].
 */
class ValueSequencerTest {
    private lateinit var sequencer: ValueSequencer<String>

    @BeforeEach
    fun setUp() {
        sequencer = ValueSequencer()
    }

    @Test
    fun isEmpty_afterInit() {
        assertThat(sequencer.isEmpty()).isTrue
    }

    @Test
    fun isEmpty_withEmptySequences() {
        repeat(3) { sequencer.offer(emptySequence()) }
        assertThat(sequencer.isEmpty()).isTrue
    }

    @Test
    fun poll_afterInit() {
        assertThrows<NoSuchElementException> { sequencer.poll() }
    }

    @Test
    fun poll_withEmptySequence() {
        sequencer.offer(emptySequence())
        assertThrows<NoSuchElementException> { sequencer.poll() }
    }

    @Test
    fun poll_withSingleValue() {
        sequencer.offer(sequenceOf("foo"))

        assertThat(sequencer.poll()).isEqualTo("foo")
        assertThrows<NoSuchElementException> { sequencer.poll() }
    }

    @Test
    fun poll_withSingleValue_afterEmptySequence() {
        with(sequencer) {
            offer(emptySequence())
            offer(sequenceOf("foo"))
        }

        assertThat(sequencer.poll()).isEqualTo("foo")
        assertThrows<NoSuchElementException> { sequencer.poll() }
    }

    @Test
    fun poll_withSingleSequence() {
        sequencer.offer(sequenceOf("foo", "bar", "baz"))

        assertThat(sequencer.poll()).isEqualTo("foo")
        assertThat(sequencer.poll()).isEqualTo("bar")
        assertThat(sequencer.poll()).isEqualTo("baz")
        assertThrows<NoSuchElementException> { sequencer.poll() }
    }

    @Test
    fun poll_withSingleSequence_afterEmptySequence() {
        with(sequencer) {
            offer(emptySequence())
            offer(sequenceOf("foo", "bar", "baz"))
        }

        assertThat(sequencer.poll()).isEqualTo("foo")
        assertThat(sequencer.poll()).isEqualTo("bar")
        assertThat(sequencer.poll()).isEqualTo("baz")
        assertThrows<NoSuchElementException> { sequencer.poll() }
    }

    @Test
    fun poll_withMultipleSequences_noneEmpty() {
        with(sequencer) {
            offer(sequenceOf("foo", "bar", "baz"))
            offer(sequenceOf("qux"))
            offer(sequenceOf("quux", "fred"))
        }

        assertThat(sequencer.poll()).isEqualTo("foo")
        assertThat(sequencer.poll()).isEqualTo("bar")
        assertThat(sequencer.poll()).isEqualTo("baz")
        assertThat(sequencer.poll()).isEqualTo("qux")
        assertThat(sequencer.poll()).isEqualTo("quux")
        assertThat(sequencer.poll()).isEqualTo("fred")
        assertThrows<NoSuchElementException> { sequencer.poll() }
    }

    @Test
    fun poll_withMultipleSequences_someEmpty() {
        with(sequencer) {
            offer(emptySequence())
            offer(sequenceOf("foo", "bar", "baz"))
            offer(sequenceOf("qux"))
            offer(emptySequence())
            offer(emptySequence())
            offer(sequenceOf("quux", "fred"))
            offer(emptySequence())
        }

        assertThat(sequencer.poll()).isEqualTo("foo")
        assertThat(sequencer.poll()).isEqualTo("bar")
        assertThat(sequencer.poll()).isEqualTo("baz")
        assertThat(sequencer.poll()).isEqualTo("qux")
        assertThat(sequencer.poll()).isEqualTo("quux")
        assertThat(sequencer.poll()).isEqualTo("fred")
        assertThrows<NoSuchElementException> { sequencer.poll() }
    }

    @Test
    fun clear_afterInit() {
        sequencer.clear()

        assertThat(sequencer.isEmpty()).isTrue
        assertThrows<NoSuchElementException> { sequencer.poll() }
    }

    @Test
    fun clear_withEmptySequence() {
        with(sequencer) {
            offer(emptySequence())
            clear()
        }

        assertThat(sequencer.isEmpty()).isTrue
        assertThrows<NoSuchElementException> { sequencer.poll() }
    }

    @Test
    fun clear_beforePolling() {
        with(sequencer) {
            offer(sequenceOf("foo", "bar", "baz"))
            clear()
        }

        assertThat(sequencer.isEmpty()).isTrue
        assertThrows<NoSuchElementException> { sequencer.poll() }
    }

    @Test
    fun clear_midSequence() {
        with(sequencer) {
            offer(sequenceOf("foo", "bar", "baz"))
            poll()
            clear()
        }

        assertThat(sequencer.isEmpty()).isTrue
        assertThrows<NoSuchElementException> { sequencer.poll() }
    }

    @Test
    fun clear_betweenSequences() {
        with(sequencer) {
            offer(sequenceOf("foo"))
            offer(sequenceOf("bar", "baz"))
            poll()
            clear()
        }

        assertThat(sequencer.isEmpty()).isTrue
        assertThrows<NoSuchElementException> { sequencer.poll() }
    }

    @Test
    fun clear_offerAndPollAfterward() {
        with(sequencer) {
            offer(sequenceOf("foo"))
            clear()
            offer(sequenceOf("bar", "baz"))
        }

        assertThat(sequencer.isEmpty()).isFalse
        assertThat(sequencer.poll()).isEqualTo("bar")
        assertThat(sequencer.poll()).isEqualTo("baz")
        assertThrows<NoSuchElementException> { sequencer.poll() }
    }
}
