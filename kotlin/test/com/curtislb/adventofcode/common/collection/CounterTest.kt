package com.curtislb.adventofcode.common.collection

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CounterTest {
    @Test fun testWhenEmpty() {
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
    }

    @Test fun testWithInitialCounts() {
        val counts = mapOf(Pair("lorem", 2L), Pair("ipsum", 0L), Pair("dolor", -3L), Pair("sit", 2147483648L))
        val counter = Counter(counts)
        assertEquals(mapOf(Pair("lorem", 2L), Pair("dolor", -3L), Pair("sit", 2147483648L)), counter.toMap())
        assertEquals(
            mapOf(Pair("lorem", 2L), Pair("dolor", -3L), Pair("sit", 2147483648L)).entries,
            counter.entriesWithNonzeroCount
        )
        assertEquals(mapOf(Pair("lorem", 2L), Pair("sit", 2147483648L)).entries, counter.entriesWithPositiveCount)
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

    @Test fun testUpdatingCounts() {
        val counter = Counter<String>()

        counter["badger"] += 12L
        counter["mushroom"] += 2L
        assertEquals(mapOf(Pair("badger", 12L), Pair("mushroom", 2L)), counter.toMap())
        assertEquals(mapOf(Pair("badger", 12L), Pair("mushroom", 2L)).entries, counter.entriesWithNonzeroCount)
        assertEquals(mapOf(Pair("badger", 12L), Pair("mushroom", 2L)).entries, counter.entriesWithPositiveCount)
        assertTrue("badger" in counter)
        assertTrue("mushroom" in counter)
        assertFalse("snake" in counter)
        assertEquals(12L, counter["badger"])
        assertEquals(2L, counter["mushroom"])
        assertEquals(0L, counter["snake"])

        counter["badger"] += 11L
        counter["mushroom"] -= 2L
        counter["snake"] -= 5L
        assertEquals(mapOf(Pair("badger", 23L), Pair("snake", -5L)), counter.toMap())
        assertEquals(mapOf(Pair("badger", 23L), Pair("snake", -5L)).entries, counter.entriesWithNonzeroCount)
        assertEquals(mapOf(Pair("badger", 23L)).entries, counter.entriesWithPositiveCount)
        assertTrue("badger" in counter)
        assertFalse("mushroom" in counter)
        assertTrue("snake" in counter)
        assertEquals(23L, counter["badger"])
        assertEquals(0L, counter["mushroom"])
        assertEquals(-5L, counter["snake"])
    }

    @Test fun testClearNegativeCounts() {
        val counter = Counter(mapOf(
            Pair("alpha", 560209110L),
            Pair("bravo", -693733719L),
            Pair("charlie", -466329041L),
            Pair("delta", 927075951L),
            Pair("echo", -13083113L),
            Pair("foxtrot", 272479767L)
        ))
        counter.clearNegativeCounts()
        assertEquals(
            mapOf(Pair("alpha", 560209110L), Pair("delta", 927075951L), Pair("foxtrot", 272479767L)),
            counter.toMap()
        )
        assertEquals(
            mapOf(Pair("alpha", 560209110L), Pair("delta", 927075951L), Pair("foxtrot", 272479767L)).entries,
            counter.entriesWithNonzeroCount
        )
        assertEquals(
            mapOf(Pair("alpha", 560209110L), Pair("delta", 927075951L), Pair("foxtrot", 272479767L)).entries,
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
