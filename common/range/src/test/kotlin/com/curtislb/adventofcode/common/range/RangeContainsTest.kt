package com.curtislb.adventofcode.common.range

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests the [contains] extension function for ranges.
 */
class RangeContainsTest {
    @Test
    fun contains_intRange_emptyInEmpty() {
        val range = IntRange.EMPTY
        val other = IntRange.EMPTY
        assertThat(other in range).isTrue
    }

    @Test
    fun contains_intRange_emptyInNonEmpty() {
        val range = 0..0
        val other = IntRange.EMPTY
        assertThat(other in range).isTrue
    }

    @Test
    fun contains_intRange_nonEmptyInEmpty() {
        val range = IntRange.EMPTY
        val other = 0..0
        assertThat(other in range).isFalse
    }

    @Test
    fun contains_intRange_sameFirstAndLast() {
        val range = -2..2
        val other = -2..2
        assertThat(other in range).isTrue
    }

    @Test
    fun contains_intRange_smallerFirstValue() {
        val range = -2..2
        val other = -3..2
        assertThat(other in range).isFalse
    }

    @Test
    fun contains_intRange_largerLastValue() {
        val range = -2..2
        val other = -2..3
        assertThat(other in range).isFalse
    }

    @Test
    fun contains_intRange_strictSuperset() {
        val range = -2..2
        val other = -3..3
        assertThat(other in range).isFalse
    }

    @Test
    fun contains_intRange_strictSubset() {
        val range = -2..2
        val other = -1..1
        assertThat(other in range).isTrue
    }

    @Test
    fun contains_longRange_emptyInEmpty() {
        val range = LongRange.EMPTY
        val other = LongRange.EMPTY
        assertThat(other in range).isTrue
    }

    @Test
    fun contains_longRange_emptyInNonEmpty() {
        val range = 0L..0L
        val other = LongRange.EMPTY
        assertThat(other in range).isTrue
    }

    @Test
    fun contains_longRange_nonEmptyInEmpty() {
        val range = LongRange.EMPTY
        val other = 0L..0L
        assertThat(other in range).isFalse
    }

    @Test
    fun contains_longRange_sameFirstAndLast() {
        val range = -2L..2L
        val other = -2L..2L
        assertThat(other in range).isTrue
    }

    @Test
    fun contains_longRange_smallerFirstValue() {
        val range = -2L..2L
        val other = -3L..2L
        assertThat(other in range).isFalse
    }

    @Test
    fun contains_longRange_largerLastValue() {
        val range = -2L..2L
        val other = -2L..3L
        assertThat(other in range).isFalse
    }

    @Test
    fun contains_longRange_strictSuperset() {
        val range = -2L..2L
        val other = -3L..3L
        assertThat(other in range).isFalse
    }

    @Test
    fun contains_longRange_strictSubset() {
        val range = -2L..2L
        val other = -1L..1L
        assertThat(other in range).isTrue
    }

    @Test
    fun contains_bigIntegerRange_emptyInEmpty() {
        val range = BigIntegerRange.EMPTY
        val other = BigIntegerRange.EMPTY
        assertThat(other in range).isTrue
    }

    @Test
    fun contains_bigIntegerRange_emptyInNonEmpty() {
        val range = BigIntegerRange(0, 0)
        val other = BigIntegerRange.EMPTY
        assertThat(other in range).isTrue
    }

    @Test
    fun contains_bigIntegerRange_nonEmptyInEmpty() {
        val range = BigIntegerRange.EMPTY
        val other = BigIntegerRange(0, 0)
        assertThat(other in range).isFalse
    }

    @Test
    fun contains_bigIntegerRange_sameFirstAndLast() {
        val range = BigIntegerRange(-2, 2)
        val other = BigIntegerRange(-2, 2)
        assertThat(other in range).isTrue
    }

    @Test
    fun contains_bigIntegerRange_smallerFirstValue() {
        val range = BigIntegerRange(-2, 2)
        val other = BigIntegerRange(-3, 2)
        assertThat(other in range).isFalse
    }

    @Test
    fun contains_bigIntegerRange_largerLastValue() {
        val range = BigIntegerRange(-2, 2)
        val other = BigIntegerRange(-2, 3)
        assertThat(other in range).isFalse
    }

    @Test
    fun contains_bigIntegerRange_strictSuperset() {
        val range = BigIntegerRange(-2, 2)
        val other = BigIntegerRange(-3, 3)
        assertThat(other in range).isFalse
    }

    @Test
    fun contains_bigIntegerRange_strictSubset() {
        val range = BigIntegerRange(-2, 2)
        val other = BigIntegerRange(-1, 1)
        assertThat(other in range).isTrue
    }
}
