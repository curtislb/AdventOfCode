package com.curtislb.adventofcode.common.range

import java.math.BigInteger
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests the [size] extension function for ranges.
 */
class RangeSizeTest {
    @Test
    fun size_intRange_empty() {
        val range = IntRange.EMPTY
        assertThat(range.size()).isEqualTo(0)
    }

    @Test
    fun size_intRange_singleValue() {
        val range = 42..42
        assertThat(range.size()).isEqualTo(1)
    }

    @Test
    fun size_intRange_multipleValues() {
        val range = -1..5
        assertThat(range.size()).isEqualTo(7)
    }

    @Test
    fun size_longRange_empty() {
        val range = LongRange.EMPTY
        assertThat(range.size()).isEqualTo(0L)
    }

    @Test
    fun size_longRange_singleValue() {
        val range = 42L..42L
        assertThat(range.size()).isEqualTo(1L)
    }

    @Test
    fun size_longRange_multipleValues() {
        val range = -2147483649L..2147483648L
        assertThat(range.size()).isEqualTo(4294967298L)
    }

    @Test
    fun size_bigIntegerRange_empty() {
        val range = BigIntegerRange.EMPTY
        assertThat(range.size()).isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun size_bigIntegerRange_singleValue() {
        val range = BigIntegerRange(42, 42)
        assertThat(range.size()).isEqualTo(BigInteger.ONE)
    }

    @Test
    fun size_bigIntegerRange_multipleValues() {
        val first = BigInteger("-9223372036854775809")
        val last = BigInteger("9223372036854775808")
        val range = BigIntegerRange(first, last)
        assertThat(range.size()).isEqualTo(BigInteger("18446744073709551618"))
    }
}
