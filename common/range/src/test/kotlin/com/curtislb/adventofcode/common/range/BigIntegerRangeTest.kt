package com.curtislb.adventofcode.common.range

import java.math.BigInteger
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests the [BigIntegerRange] class and related functions.
 */
class BigIntegerRangeTest {
    @Test
    fun startAndEndInclusive_whenEmpty() {
        val range = BigIntegerRange.EMPTY
        assertThat(range.start).isGreaterThan(range.endInclusive)
    }

    @Test
    fun startAndEndInclusive_withSingleValue() {
        val range = BigIntegerRange(42, 42)
        assertThat(range.start).isEqualTo(BigInteger.valueOf(42))
        assertThat(range.endInclusive).isEqualTo(BigInteger.valueOf(42))
    }

    @Test
    fun startAndEndInclusive_withMultipleValues() {
        val range = BigIntegerRange(-5, 12)
        assertThat(range.start).isEqualTo(BigInteger.valueOf(-5))
        assertThat(range.endInclusive).isEqualTo(BigInteger.valueOf(12))
    }

    @Test
    fun constructor_withLongs() {
        val range = BigIntegerRange(-2147483649L, 2147483648L)
        assertThat(range.first).isEqualTo(BigInteger.valueOf(-2147483649L))
        assertThat(range.last).isEqualTo(BigInteger.valueOf(2147483648L))
    }

    @Test
    fun constructor_withBigIntegers() {
        val first = BigInteger("-9223372036854775809")
        val last = BigInteger("9223372036854775808")
        val range = BigIntegerRange(first, last)
        assertThat(range.first).isEqualTo(first)
        assertThat(range.last).isEqualTo(last)
    }

    @Test
    fun toString_whenEmpty() {
        val range = BigIntegerRange(2, -3)
        assertThat(range.toString()).isEqualTo("BigIntegerRange(2, -3)")
    }

    @Test
    fun toString_withSingleValue() {
        val range = BigIntegerRange(-23, -23)
        assertThat(range.toString()).isEqualTo("BigIntegerRange(-23, -23)")
    }

    @Test
    fun toString_withMultipleValues() {
        val range = BigIntegerRange(0, 101)
        assertThat(range.toString()).isEqualTo("BigIntegerRange(0, 101)")
    }

    @Test
    fun equals_nullRange_whenEmpty() {
        val range = BigIntegerRange.EMPTY
        val other: BigIntegerRange? = null
        assertThat(range).isNotEqualTo(other)
    }

    @Test
    fun equals_nullRange_whenNotEmpty() {
        val range = BigIntegerRange(0, 0)
        val other: BigIntegerRange? = null
        assertThat(range).isNotEqualTo(other)
    }

    @Test
    fun equals_emptyRange_whenEmpty() {
        val range = BigIntegerRange(1, 0)
        val other = BigIntegerRange(0, -1)
        assertThat(range).isEqualTo(other)
    }

    @Test
    fun equals_emptyRange_whenNotEmpty() {
        val range = BigIntegerRange(0, 1)
        val other = BigIntegerRange(1, 0)
        assertThat(range).isNotEqualTo(other)
    }

    @Test
    fun equals_nonEmptyRange_whenEmpty() {
        val range = BigIntegerRange(0, -1)
        val other = BigIntegerRange(-1, 0)
        assertThat(range).isNotEqualTo(other)
    }

    @Test
    fun equals_differentFirstValues() {
        val range = BigIntegerRange(1, 3)
        val other = BigIntegerRange(2, 3)
        assertThat(range).isNotEqualTo(other)
    }

    @Test
    fun equals_differentSecondValues() {
        val range = BigIntegerRange(1, 4)
        val other = BigIntegerRange(1, 3)
        assertThat(range).isNotEqualTo(other)
    }

    @Test
    fun equals_sameFirstAndSecondValues() {
        val range = BigIntegerRange(-2, 0)
        val other = BigIntegerRange(-2, 0)
        assertThat(range).isEqualTo(other)
    }

    @Test
    fun hashCode_whenEmpty() {
        val hashMap = hashMapOf(BigIntegerRange.EMPTY to "foo")
        assertThat(hashMap[BigIntegerRange.EMPTY]).isEqualTo("foo")
        assertThat(hashMap[BigIntegerRange(2, 1)]).isEqualTo("foo")
        assertThat(hashMap[BigIntegerRange(-1, -2)]).isEqualTo("foo")
        assertThat(hashMap[BigIntegerRange(0, 0)]).isNull()
    }

    @Test
    fun hashCode_whenNotEmpty() {
        val hashMap = hashMapOf(BigIntegerRange(1, 3) to "bar")
        assertThat(hashMap[BigIntegerRange(1, 3)]).isEqualTo("bar")
        assertThat(hashMap[BigIntegerRange.EMPTY]).isNull()
        assertThat(hashMap[BigIntegerRange(3, 1)]).isNull()
        assertThat(hashMap[BigIntegerRange(2, 3)]).isNull()
    }

    @Test
    fun iterator_whenEmpty() {
        val range = BigIntegerRange.EMPTY

        val values = mutableListOf<BigInteger>()
        for (value in range) {
            values.add(value)
        }

        assertThat(values).isEmpty()
    }

    @Test
    fun iterator_withSingleValue() {
        val range = BigIntegerRange(99, 99)

        val values = mutableListOf<BigInteger>()
        for (value in range) {
            values.add(value)
        }

        assertThat(values).containsExactly(BigInteger.valueOf(99))
    }

    @Test
    fun iterator_withMultipleValues() {
        val range = BigIntegerRange(-2, 3)

        val values = mutableListOf<BigInteger>()
        for (value in range) {
            values.add(value)
        }

        assertThat(values).containsExactly(
            BigInteger.valueOf(-2),
            BigInteger.valueOf(-1),
            BigInteger.ZERO,
            BigInteger.ONE,
            BigInteger.TWO,
            BigInteger.valueOf(3)
        )
    }

    @Test
    fun toBigIntegerRange_intRange_empty() {
        val intRange = IntRange.EMPTY
        val bigIntRange = intRange.toBigIntegerRange()
        assertThat(bigIntRange.isEmpty()).isTrue
    }

    @Test
    fun toBigIntegerRange_intRange_singleValue() {
        val intRange = 2..2
        val bigIntRange = intRange.toBigIntegerRange()
        assertThat(bigIntRange.first).isEqualTo(BigInteger.TWO)
        assertThat(bigIntRange.last).isEqualTo(BigInteger.TWO)
    }

    @Test
    fun toBigIntegerRange_intRange_multipleValues() {
        val intRange = -76..13
        val bigIntRange = intRange.toBigIntegerRange()
        assertThat(bigIntRange.first).isEqualTo(BigInteger.valueOf(-76))
        assertThat(bigIntRange.last).isEqualTo(BigInteger.valueOf(13))
    }

    @Test
    fun toBigIntegerRange_longRange_empty() {
        val longRange = LongRange.EMPTY
        val bigIntRange = longRange.toBigIntegerRange()
        assertThat(bigIntRange.isEmpty()).isTrue
    }

    @Test
    fun toBigIntegerRange_longRange_singleValue() {
        val longRange = 2L..2L
        val bigIntRange = longRange.toBigIntegerRange()
        assertThat(bigIntRange.first).isEqualTo(BigInteger.TWO)
        assertThat(bigIntRange.last).isEqualTo(BigInteger.TWO)
    }

    @Test
    fun toBigIntegerRange_longRange_multipleValues() {
        val longRange = -76L..13L
        val bigIntRange = longRange.toBigIntegerRange()
        assertThat(bigIntRange.first).isEqualTo(BigInteger.valueOf(-76))
        assertThat(bigIntRange.last).isEqualTo(BigInteger.valueOf(13))
    }
}
