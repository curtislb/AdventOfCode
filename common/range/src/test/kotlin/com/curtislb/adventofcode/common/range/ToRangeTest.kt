package com.curtislb.adventofcode.common.range

import java.math.BigInteger
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests the [toIntRange], [toLongRange], and [toBigIntegerRange] extension functions for
 * [ClosedRange].
 */
class ToRangeTest {
    @Test
    fun toIntRange_empty() {
        val closedRange = createClosedRange(0, -1)
        assertThat(closedRange.toIntRange()).isEqualTo(IntRange.EMPTY)
    }

    @Test
    fun toIntRange_singleValue() {
        val closedRange = createClosedRange(42, 42)
        assertThat(closedRange.toIntRange()).isEqualTo(42..42)
    }

    @Test
    fun toIntRange_multipleValues() {
        val closedRange = createClosedRange(-1, 5)
        assertThat(closedRange.toIntRange()).isEqualTo(-1..5)
    }

    @Test
    fun toLongRange_empty() {
        val closedRange = createClosedRange(0L, -1L)
        assertThat(closedRange.toLongRange()).isEqualTo(LongRange.EMPTY)
    }

    @Test
    fun toLongRange_singleValue() {
        val closedRange = createClosedRange(42L, 42L)
        assertThat(closedRange.toLongRange()).isEqualTo(42L..42L)
    }

    @Test
    fun toLongRange_multipleValues() {
        val first = -2147483649L
        val last = 2147483648L
        val closedRange = createClosedRange(first, last)
        assertThat(closedRange.toLongRange()).isEqualTo(first..last)
    }

    @Test
    fun toBigIntegerRange_empty() {
        val closedRange = createClosedRange(BigInteger.ZERO, BigInteger.valueOf(-1))
        assertThat(closedRange.toBigIntegerRange()).isEqualTo(BigIntegerRange.EMPTY)
    }

    @Test
    fun toBigIntegerRange_singleValue() {
        val value = BigInteger.valueOf(42)
        val closedRange = createClosedRange(value, value)
        assertThat(closedRange.toBigIntegerRange()).isEqualTo(BigIntegerRange(value, value))
    }

    @Test
    fun toBigIntegerRange_multipleValues() {
        val first = BigInteger("-9223372036854775809")
        val last = BigInteger("9223372036854775808")
        val closedRange = createClosedRange(first, last)
        assertThat(closedRange.toBigIntegerRange()).isEqualTo(BigIntegerRange(first, last))
    }

    /**
     * Returns an instance of [ClosedRange] with the given [first] and [last] values.
     */
    private fun <T : Comparable<T>> createClosedRange(first: T, last: T): ClosedRange<T> =
        object : ClosedRange<T> {
            override val start: T = first
            override val endInclusive: T = last
        }
}
