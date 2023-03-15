package com.curtislb.adventofcode.common.range

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests the [overlapWith] extension function for ranges.
 */
class RangeOverlapTest {
    @Test
    fun overlapWith_intRange_bothEmpty() {
        val range = IntRange.EMPTY
        val other = IntRange.EMPTY
        assertThat(range overlapWith other).isEqualTo(IntRange.EMPTY)
    }

    @Test
    fun overlapWith_intRange_leftEmpty() {
        val range = IntRange.EMPTY
        val other = -2..2
        assertThat(range overlapWith other).isEqualTo(IntRange.EMPTY)
    }

    @Test
    fun overlapWith_intRange_rightEmpty() {
        val range = -2..2
        val other = IntRange.EMPTY
        assertThat(range overlapWith other).isEqualTo(IntRange.EMPTY)
    }

    @Test
    fun overlapWith_intRange_firstBeforeFirst_lastBeforeFirst() {
        val range = -2..2
        val other = -3..-3
        assertThat(range overlapWith other).isEqualTo(IntRange.EMPTY)
    }

    @Test
    fun overlapWith_intRange_firstBeforeFirst_lastEqualsFirst() {
        val range = -2..2
        val other = -3..-2
        assertThat(range overlapWith other).isEqualTo(-2..-2)
    }

    @Test
    fun overlapWith_intRange_firstBeforeFirst_lastInside() {
        val range = -2..2
        val other = -3..1
        assertThat(range overlapWith other).isEqualTo(-2..1)
    }

    @Test
    fun overlapWith_intRange_firstBeforeFirst_lastEqualsLast() {
        val range = -2..2
        val other = -3..2
        assertThat(range overlapWith other).isEqualTo(-2..2)
    }

    @Test
    fun overlapWith_intRange_firstBeforeFirst_lastAfterLast() {
        val range = -2..2
        val other = -3..3
        assertThat(range overlapWith other).isEqualTo(-2..2)
    }

    @Test
    fun overlapWith_intRange_firstEqualsFirst_lastEqualsFirst() {
        val range = -2..2
        val other = -2..-2
        assertThat(range overlapWith other).isEqualTo(-2..-2)
    }

    @Test
    fun overlapWith_intRange_firstEqualsFirst_lastInside() {
        val range = -2..2
        val other = -2..1
        assertThat(range overlapWith other).isEqualTo(-2..1)
    }

    @Test
    fun overlapWith_intRange_firstEqualsFirst_lastEqualsLast() {
        val range = -2..2
        val other = -2..2
        assertThat(range overlapWith other).isEqualTo(-2..2)
    }

    @Test
    fun overlapWith_intRange_firstEqualsFirst_lastAfterLast() {
        val range = -2..2
        val other = -2..3
        assertThat(range overlapWith other).isEqualTo(-2..2)
    }

    @Test
    fun overlapWith_intRange_firstInside_lastInside() {
        val range = -2..2
        val other = -1..1
        assertThat(range overlapWith other).isEqualTo(-1..1)
    }

    @Test
    fun overlapWith_intRange_firstInside_lastEqualsLast() {
        val range = -2..2
        val other = -1..2
        assertThat(range overlapWith other).isEqualTo(-1..2)
    }

    @Test
    fun overlapWith_intRange_firstInside_lastAfterLast() {
        val range = -2..2
        val other = -1..3
        assertThat(range overlapWith other).isEqualTo(-1..2)
    }

    @Test
    fun overlapWith_intRange_firstEqualsLast_lastEqualsLast() {
        val range = -2..2
        val other = 2..2
        assertThat(range overlapWith other).isEqualTo(2..2)
    }

    @Test
    fun overlapWith_intRange_firstEqualsLast_lastAfterLast() {
        val range = -2..2
        val other = 2..3
        assertThat(range overlapWith other).isEqualTo(2..2)
    }

    @Test
    fun overlapWith_intRange_firstAfterLast_lastAfterLast() {
        val range = -2..2
        val other = 3..3
        assertThat(range overlapWith other).isEqualTo(IntRange.EMPTY)
    }

    @Test
    fun overlapWith_longRange_bothEmpty() {
        val range = LongRange.EMPTY
        val other = LongRange.EMPTY
        assertThat(range overlapWith other).isEqualTo(LongRange.EMPTY)
    }

    @Test
    fun overlapWith_longRange_leftEmpty() {
        val range = LongRange.EMPTY
        val other = -2L..2L
        assertThat(range overlapWith other).isEqualTo(LongRange.EMPTY)
    }

    @Test
    fun overlapWith_longRange_rightEmpty() {
        val range = -2L..2L
        val other = LongRange.EMPTY
        assertThat(range overlapWith other).isEqualTo(LongRange.EMPTY)
    }

    @Test
    fun overlapWith_longRange_firstBeforeFirst_lastBeforeFirst() {
        val range = -2L..2L
        val other = -3L..-3L
        assertThat(range overlapWith other).isEqualTo(LongRange.EMPTY)
    }

    @Test
    fun overlapWith_longRange_firstBeforeFirst_lastEqualsFirst() {
        val range = -2L..2L
        val other = -3L..-2L
        assertThat(range overlapWith other).isEqualTo(-2L..-2L)
    }

    @Test
    fun overlapWith_longRange_firstBeforeFirst_lastInside() {
        val range = -2L..2L
        val other = -3L..1L
        assertThat(range overlapWith other).isEqualTo(-2L..1L)
    }

    @Test
    fun overlapWith_longRange_firstBeforeFirst_lastEqualsLast() {
        val range = -2L..2L
        val other = -3L..2L
        assertThat(range overlapWith other).isEqualTo(-2L..2L)
    }

    @Test
    fun overlapWith_longRange_firstBeforeFirst_lastAfterLast() {
        val range = -2L..2L
        val other = -3L..3L
        assertThat(range overlapWith other).isEqualTo(-2L..2L)
    }

    @Test
    fun overlapWith_longRange_firstEqualsFirst_lastEqualsFirst() {
        val range = -2L..2L
        val other = -2L..-2L
        assertThat(range overlapWith other).isEqualTo(-2L..-2L)
    }

    @Test
    fun overlapWith_longRange_firstEqualsFirst_lastInside() {
        val range = -2L..2L
        val other = -2L..1L
        assertThat(range overlapWith other).isEqualTo(-2L..1L)
    }

    @Test
    fun overlapWith_longRange_firstEqualsFirst_lastEqualsLast() {
        val range = -2L..2L
        val other = -2L..2L
        assertThat(range overlapWith other).isEqualTo(-2L..2L)
    }

    @Test
    fun overlapWith_longRange_firstEqualsFirst_lastAfterLast() {
        val range = -2L..2L
        val other = -2L..3L
        assertThat(range overlapWith other).isEqualTo(-2L..2L)
    }

    @Test
    fun overlapWith_longRange_firstInside_lastInside() {
        val range = -2L..2L
        val other = -1L..1L
        assertThat(range overlapWith other).isEqualTo(-1L..1L)
    }

    @Test
    fun overlapWith_longRange_firstInside_lastEqualsLast() {
        val range = -2L..2L
        val other = -1L..2L
        assertThat(range overlapWith other).isEqualTo(-1L..2L)
    }

    @Test
    fun overlapWith_longRange_firstInside_lastAfterLast() {
        val range = -2L..2L
        val other = -1L..3L
        assertThat(range overlapWith other).isEqualTo(-1L..2L)
    }

    @Test
    fun overlapWith_longRange_firstEqualsLast_lastEqualsLast() {
        val range = -2L..2L
        val other = 2L..2L
        assertThat(range overlapWith other).isEqualTo(2L..2L)
    }

    @Test
    fun overlapWith_longRange_firstEqualsLast_lastAfterLast() {
        val range = -2L..2L
        val other = 2L..3L
        assertThat(range overlapWith other).isEqualTo(2L..2L)
    }

    @Test
    fun overlapWith_longRange_firstAfterLast_lastAfterLast() {
        val range = -2L..2L
        val other = 3L..3L
        assertThat(range overlapWith other).isEqualTo(LongRange.EMPTY)
    }

    @Test
    fun overlapWith_bigIntegerRange_bothEmpty() {
        val range = BigIntegerRange.EMPTY
        val other = BigIntegerRange.EMPTY
        assertThat(range overlapWith other).isEqualTo(BigIntegerRange.EMPTY)
    }

    @Test
    fun overlapWith_bigIntegerRange_leftEmpty() {
        val range = BigIntegerRange.EMPTY
        val other = BigIntegerRange(-2, 2)
        assertThat(range overlapWith other).isEqualTo(BigIntegerRange.EMPTY)
    }

    @Test
    fun overlapWith_bigIntegerRange_rightEmpty() {
        val range = BigIntegerRange(-2, 2)
        val other = BigIntegerRange.EMPTY
        assertThat(range overlapWith other).isEqualTo(BigIntegerRange.EMPTY)
    }

    @Test
    fun overlapWith_bigIntegerRange_firstBeforeFirst_lastBeforeFirst() {
        val range = BigIntegerRange(-2, 2)
        val other = BigIntegerRange(-3, -3)
        assertThat(range overlapWith other).isEqualTo(BigIntegerRange.EMPTY)
    }

    @Test
    fun overlapWith_bigIntegerRange_firstBeforeFirst_lastEqualsFirst() {
        val range = BigIntegerRange(-2, 2)
        val other = BigIntegerRange(-3, -2)
        assertThat(range overlapWith other).isEqualTo(BigIntegerRange(-2, -2))
    }

    @Test
    fun overlapWith_bigIntegerRange_firstBeforeFirst_lastInside() {
        val range = BigIntegerRange(-2, 2)
        val other = BigIntegerRange(-3, 1)
        assertThat(range overlapWith other).isEqualTo(BigIntegerRange(-2, 1))
    }

    @Test
    fun overlapWith_bigIntegerRange_firstBeforeFirst_lastEqualsLast() {
        val range = BigIntegerRange(-2, 2)
        val other = BigIntegerRange(-3, 2)
        assertThat(range overlapWith other).isEqualTo(BigIntegerRange(-2, 2))
    }

    @Test
    fun overlapWith_bigIntegerRange_firstBeforeFirst_lastAfterLast() {
        val range = BigIntegerRange(-2, 2)
        val other = BigIntegerRange(-3, 3)
        assertThat(range overlapWith other).isEqualTo(BigIntegerRange(-2, 2))
    }

    @Test
    fun overlapWith_bigIntegerRange_firstEqualsFirst_lastEqualsFirst() {
        val range = BigIntegerRange(-2, 2)
        val other = BigIntegerRange(-2, -2)
        assertThat(range overlapWith other).isEqualTo(BigIntegerRange(-2, -2))
    }

    @Test
    fun overlapWith_bigIntegerRange_firstEqualsFirst_lastInside() {
        val range = BigIntegerRange(-2, 2)
        val other = BigIntegerRange(-2, 1)
        assertThat(range overlapWith other).isEqualTo(BigIntegerRange(-2, 1))
    }

    @Test
    fun overlapWith_bigIntegerRange_firstEqualsFirst_lastEqualsLast() {
        val range = BigIntegerRange(-2, 2)
        val other = BigIntegerRange(-2, 2)
        assertThat(range overlapWith other).isEqualTo(BigIntegerRange(-2, 2))
    }

    @Test
    fun overlapWith_bigIntegerRange_firstEqualsFirst_lastAfterLast() {
        val range = BigIntegerRange(-2, 2)
        val other = BigIntegerRange(-2, 3)
        assertThat(range overlapWith other).isEqualTo(BigIntegerRange(-2, 2))
    }

    @Test
    fun overlapWith_bigIntegerRange_firstInside_lastInside() {
        val range = BigIntegerRange(-2, 2)
        val other = BigIntegerRange(-1, 1)
        assertThat(range overlapWith other).isEqualTo(BigIntegerRange(-1, 1))
    }

    @Test
    fun overlapWith_bigIntegerRange_firstInside_lastEqualsLast() {
        val range = BigIntegerRange(-2, 2)
        val other = BigIntegerRange(-1, 2)
        assertThat(range overlapWith other).isEqualTo(BigIntegerRange(-1, 2))
    }

    @Test
    fun overlapWith_bigIntegerRange_firstInside_lastAfterLast() {
        val range = BigIntegerRange(-2, 2)
        val other = BigIntegerRange(-1, 3)
        assertThat(range overlapWith other).isEqualTo(BigIntegerRange(-1, 2))
    }

    @Test
    fun overlapWith_bigIntegerRange_firstEqualsLast_lastEqualsLast() {
        val range = BigIntegerRange(-2, 2)
        val other = BigIntegerRange(2, 2)
        assertThat(range overlapWith other).isEqualTo(BigIntegerRange(2, 2))
    }

    @Test
    fun overlapWith_bigIntegerRange_firstEqualsLast_lastAfterLast() {
        val range = BigIntegerRange(-2, 2)
        val other = BigIntegerRange(2, 3)
        assertThat(range overlapWith other).isEqualTo(BigIntegerRange(2, 2))
    }

    @Test
    fun overlapWith_bigIntegerRange_firstAfterLast_lastAfterLast() {
        val range = BigIntegerRange(-2, 2)
        val other = BigIntegerRange(3, 3)
        assertThat(range overlapWith other).isEqualTo(BigIntegerRange.EMPTY)
    }
}
