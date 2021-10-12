package com.curtislb.adventofcode.common.collection

import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [ValueSequencer].
 */
class ValueSequencerTest {
    private lateinit var sequencer: ValueSequencer<Int>

    @BeforeEach
    fun setUp() {
        sequencer = ValueSequencer()
    }

    @Test
    fun testWhenEmpty() {
        assertFalse(sequencer.hasNext())
        assertThrows<NoSuchElementException> { sequencer.next() }
        sequencer.clear()
        assertFalse(sequencer.hasNext())
    }

    @Test
    fun testQueueing() {
        sequencer.queue(sequenceOf(11))
        assertTrue(sequencer.hasNext())
        sequencer.queue(sequenceOf(52, -73))
        assertTrue(sequencer.hasNext())
        assertEquals(11, sequencer.next())
        assertTrue(sequencer.hasNext())
        sequencer.queue(sequenceOf(11, 23, 33))
        assertTrue(sequencer.hasNext())
        assertEquals(52, sequencer.next())
        assertTrue(sequencer.hasNext())
        assertEquals(-73, sequencer.next())
        assertTrue(sequencer.hasNext())
        assertEquals(11, sequencer.next())
        assertTrue(sequencer.hasNext())
        assertEquals(23, sequencer.next())
        assertTrue(sequencer.hasNext())
        assertEquals(33, sequencer.next())
        assertFalse(sequencer.hasNext())
        assertThrows<NoSuchElementException> { sequencer.next() }
    }

    @Test
    fun testClear() {
        sequencer.queue(sequenceOf(-30, 71))
        assertTrue(sequencer.hasNext())
        sequencer.clear()
        assertFalse(sequencer.hasNext())
        assertThrows<NoSuchElementException> { sequencer.next() }

        sequencer.queue(sequenceOf(64))
        sequencer.queue(sequenceOf(-6, 91))
        assertTrue(sequencer.hasNext())
        sequencer.clear()
        assertFalse(sequencer.hasNext())
        assertThrows<NoSuchElementException> { sequencer.next() }
    }
}
