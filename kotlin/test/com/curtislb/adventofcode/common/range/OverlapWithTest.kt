package com.curtislb.adventofcode.common.range

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Tests [overlapWith].
 */
class OverlapWithTest {
    @Test
    fun testWithEmptyRange() {
        assertTrue(IntRange.EMPTY.overlapWith(IntRange.EMPTY).isEmpty())
        assertTrue((0..1).overlapWith(IntRange.EMPTY).isEmpty())
        assertTrue(IntRange.EMPTY.overlapWith(0..1).isEmpty())
    }

    @Test
    fun testWithNonOverlappingRanges() {
        assertTrue((0..0).overlapWith(1..1).isEmpty())
        assertTrue((1..1).overlapWith(0..0).isEmpty())
        assertTrue((0..1).overlapWith(2..2).isEmpty())
        assertTrue((2..2).overlapWith(0..1).isEmpty())
        assertTrue((0..1).overlapWith(-2..-1).isEmpty())
        assertTrue((-2..-1).overlapWith(0..1).isEmpty())
        assertTrue((-16..2).overlapWith(9..19).isEmpty())
        assertTrue((9..19).overlapWith(-16..2).isEmpty())
    }

    @Test
    fun testWithOverlappingRanges() {
        assertEquals(0..0, (0..0).overlapWith(0..0).toIterableRange())
        assertEquals(1..1, (1..1).overlapWith(1..1).toIterableRange())
        assertEquals(0..1, (0..1).overlapWith(0..1).toIterableRange())
        assertEquals(1..2, (1..2).overlapWith(1..2).toIterableRange())
        assertEquals(-1..2, (-1..2).overlapWith(-1..2).toIterableRange())
        assertEquals(0..0, (0..0).overlapWith(-1..1).toIterableRange())
        assertEquals(0..0, (-1..1).overlapWith(0..0).toIterableRange())
        assertEquals(1..2, (1..2).overlapWith(0..3).toIterableRange())
        assertEquals(1..2, (0..3).overlapWith(1..2).toIterableRange())
        assertEquals(-1..2, (-1..2).overlapWith(-3..5).toIterableRange())
        assertEquals(-1..2, (-3..5).overlapWith(-1..2).toIterableRange())
        assertEquals(0..0, (0..0).overlapWith(0..1).toIterableRange())
        assertEquals(1..1, (0..1).overlapWith(1..1).toIterableRange())
        assertEquals(1..2, (1..2).overlapWith(0..2).toIterableRange())
        assertEquals(1..2, (0..2).overlapWith(1..2).toIterableRange())
        assertEquals(1..2, (0..2).overlapWith(1..3).toIterableRange())
        assertEquals(1..2, (1..3).overlapWith(0..2).toIterableRange())
        assertEquals(3..3, (0..3).overlapWith(3..5).toIterableRange())
        assertEquals(3..3, (3..5).overlapWith(0..3).toIterableRange())
        assertEquals(1..3, (-1..3).overlapWith(1..5).toIterableRange())
        assertEquals(1..3, (1..5).overlapWith(-1..3).toIterableRange())
    }
}
