package com.curtislb.adventofcode.common.collection

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.fail

/**
 * Tests [ValueSequencer].
 */
class ValueSequencerTest {
    @Test fun testWhenEmpty() {
        val sequencer = ValueSequencer<Any>()
        assertFalse(sequencer.hasNext())
        assertNextThrows(sequencer)
        sequencer.clear()
        assertFalse(sequencer.hasNext())
    }

    @Test fun testQueueing() {
        val sequencer = ValueSequencer<Int>()
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
        assertNextThrows(sequencer)
    }

    @Test fun testClear() {
        val sequencer = ValueSequencer<Int>()

        sequencer.queue(sequenceOf(-30, 71))
        assertTrue(sequencer.hasNext())
        sequencer.clear()
        assertFalse(sequencer.hasNext())
        assertNextThrows(sequencer)

        sequencer.queue(sequenceOf(64))
        sequencer.queue(sequenceOf(-6, 91))
        assertTrue(sequencer.hasNext())
        sequencer.clear()
        assertFalse(sequencer.hasNext())
        assertNextThrows(sequencer)
    }

    private companion object {
        /**
         * Checks that calling [ValueSequencer.next] on [sequencer] throws a [NoSuchElementException].
         */
        fun assertNextThrows(sequencer: ValueSequencer<*>) {
            try {
                sequencer.next()
                fail("Expected NoSuchElementException.")
            } catch (e: NoSuchElementException) {
                // This is expected.
            }
        }
    }
}
