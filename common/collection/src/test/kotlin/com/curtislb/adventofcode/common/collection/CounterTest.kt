package com.curtislb.adventofcode.common.collection

import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test

/**
 * Tests [Counter].
 */
class CounterTest {
    @Test
    fun testWhenEmpty() {
        val counter = Counter<String>()
        assertEquals(emptyMap(), counter.toMap())
        assertEquals(emptySet(), counter.entriesWithNonzeroCount)
        assertEquals(emptySet(), counter.entriesWithPositiveCount)
        assertEquals(emptySet(), counter.keysWithNonzeroCount)
        assertEquals(emptySet(), counter.keysWithPositiveCount)
        assertFalse("foo" in counter)
        assertFalse("bar" in counter)
        assertEquals(0L, counter["bar"])
        assertEquals(0L, counter["baz"])
        assertEquals("{}", counter.toString())
    }

    @Test
    fun testWithInitialCounts() {
        val counts = mapOf("lorem" to 2L, "ipsum" to 0L, "dolor" to -3L, "sit" to 2147483648L)
        val counter = Counter(counts)
        assertEquals(mapOf("lorem" to 2L, "dolor" to -3L, "sit" to 2147483648L), counter.toMap())
        assertEquals(
            mapOf("lorem" to 2L, "dolor" to -3L, "sit" to 2147483648L).entries,
            counter.entriesWithNonzeroCount
        )
        assertEquals(
            mapOf("lorem" to 2L, "sit" to 2147483648L).entries,
            counter.entriesWithPositiveCount
        )
        assertEquals(setOf("lorem", "dolor", "sit"), counter.keysWithNonzeroCount)
        assertEquals(setOf("lorem", "sit"), counter.keysWithPositiveCount)
        assertTrue("lorem" in counter)
        assertFalse("ipsum" in counter)
        assertTrue("dolor" in counter)
        assertTrue("sit" in counter)
        assertFalse("amet" in counter)
        assertEquals(2L, counter["lorem"])
        assertEquals(0L, counter["ipsum"])
        assertEquals(-3L, counter["dolor"])
        assertEquals(2147483648L, counter["sit"])
        assertEquals(0L, counter["amet"])
    }

    @Test
    fun testUpdatingCounts() {
        val counter = Counter<String>()

        counter["badger"] += 12L
        counter["mushroom"] += 2L
        assertEquals(mapOf("badger" to 12L, "mushroom" to 2L), counter.toMap())
        assertEquals(
            mapOf("badger" to 12L, "mushroom" to 2L).entries,
            counter.entriesWithNonzeroCount
        )
        assertEquals(
            mapOf("badger" to 12L, "mushroom" to 2L).entries,
            counter.entriesWithPositiveCount
        )
        assertTrue("badger" in counter)
        assertTrue("mushroom" in counter)
        assertFalse("snake" in counter)
        assertEquals(12L, counter["badger"])
        assertEquals(2L, counter["mushroom"])
        assertEquals(0L, counter["snake"])

        counter["badger"] += 11L
        counter["mushroom"] -= 2L
        counter["snake"] -= 5L
        assertEquals(mapOf("badger" to 23L, "snake" to -5L), counter.toMap())
        assertEquals(
            mapOf("badger" to 23L, "snake" to -5L).entries,
            counter.entriesWithNonzeroCount
        )
        assertEquals(mapOf("badger" to 23L).entries, counter.entriesWithPositiveCount)
        assertTrue("badger" in counter)
        assertFalse("mushroom" in counter)
        assertTrue("snake" in counter)
        assertEquals(23L, counter["badger"])
        assertEquals(0L, counter["mushroom"])
        assertEquals(-5L, counter["snake"])
    }

    @Test
    fun testConstructFromItems() {
        val counter = Counter("the spark that will light the fire that will burn the".split(' '))
        assertEquals(
            mapOf(
                "the" to 3L,
                "spark" to 1L,
                "that" to 2L,
                "will" to 2L,
                "light" to 1L,
                "fire" to 1L,
                "burn" to 1L
            ).entries,
            counter.entriesWithNonzeroCount
        )
    }

    @Test
    fun testContainsCounter() {
        val counterA = Counter<String>()
        val counterB = Counter<String>()
        assertTrue(counterA in counterB)
        assertTrue(counterB in counterA)

        counterA["red"] = 1L
        assertFalse(counterA in counterB)
        assertTrue(counterB in counterA)

        counterB["red"] = 1L
        assertTrue(counterA in counterB)
        assertTrue(counterB in counterA)

        counterB["red"] = 2L
        assertTrue(counterA in counterB)
        assertFalse(counterB in counterA)

        counterA["blue"] = 3L
        assertFalse(counterA in counterB)
        assertFalse(counterB in counterA)

        counterA["red"] = 2L
        assertFalse(counterA in counterB)
        assertTrue(counterB in counterA)

        counterB["blue"] = 4L
        assertTrue(counterA in counterB)
        assertFalse(counterB in counterA)

        counterA["green"] = -1L
        assertTrue(counterA in counterB)
        assertFalse(counterB in counterA)

        counterB["green"] = -2L
        assertFalse(counterA in counterB)
        assertFalse(counterB in counterA)

        counterA["green"] = -3L
        assertTrue(counterA in counterB)
        assertFalse(counterB in counterA)
    }

    @Test
    fun testAddAll() {
        val counter = Counter<String>()
        counter.addAll(listOf("banana", "apple", "banana", "orange", "orange", "banana"))
        assertEquals(1L, counter["apple"])
        assertEquals(3L, counter["banana"])
        assertEquals(2L, counter["orange"])
        assertEquals(0L, counter["kiwi"])
        assertEquals(0L, counter["kumquat"])

        counter["kiwi"] = -1L
        counter.addAll(listOf("orange", "banana", "kiwi", "orange", "orange", "kiwi", "apple"))
        assertEquals(2L, counter["apple"])
        assertEquals(4L, counter["banana"])
        assertEquals(5L, counter["orange"])
        assertEquals(1L, counter["kiwi"])
        assertEquals(0L, counter["kumquat"])
    }

    @Test
    fun testClearNegativeCounts() {
        val counter = Counter(
            mapOf(
                "alpha" to 560209110L,
                "bravo" to -693733719L,
                "charlie" to -466329041L,
                "delta" to 927075951L,
                "echo" to -13083113L,
                "foxtrot" to 272479767L
            )
        )
        counter.clearNegativeCounts()
        assertEquals(
            mapOf("alpha" to 560209110L, "delta" to 927075951L, "foxtrot" to 272479767L),
            counter.toMap()
        )
        assertEquals(
            mapOf("alpha" to 560209110L, "delta" to 927075951L, "foxtrot" to 272479767L).entries,
            counter.entriesWithNonzeroCount
        )
        assertEquals(
            mapOf("alpha" to 560209110L, "delta" to 927075951L, "foxtrot" to 272479767L).entries,
            counter.entriesWithPositiveCount
        )
        assertTrue("alpha" in counter)
        assertFalse("bravo" in counter)
        assertFalse("charlie" in counter)
        assertTrue("delta" in counter)
        assertFalse("echo" in counter)
        assertTrue("foxtrot" in counter)
        assertEquals(560209110L, counter["alpha"])
        assertEquals(0L, counter["bravo"])
        assertEquals(0L, counter["charlie"])
        assertEquals(927075951L, counter["delta"])
        assertEquals(0L, counter["echo"])
        assertEquals(272479767L, counter["foxtrot"])
    }
}
