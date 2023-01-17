package com.curtislb.adventofcode.common.range

import java.math.BigInteger
import java.util.Objects

/**
 * A contiguous range of [BigInteger] values.
 *
 * @param start The minimum value contained in this range.
 * @param endInclusive The maximum value contained in this range.
 */
class BigIntegerRange(
    override val start: BigInteger,
    override val endInclusive: BigInteger
) : ClosedRange<BigInteger>, Iterable<BigInteger> {
    /**
     * The minimum value contained in this range.
     */
    val first: BigInteger
        get() = start

    /**
     * The maximum value contained in this range.
     */
    val last: BigInteger
        get() = endInclusive

    /**
     * The number of distinct integer values in this range.
     */
    val size: BigInteger
        get() = if (isEmpty()) BigInteger.ZERO else endInclusive - start + BigInteger.ONE

    /**
     * A contiguous range of [BigInteger] values.
     *
     * @param intRange A range of [Int] values that will be used to create this range.
     */
    constructor(intRange: IntRange) : this(
        intRange.first.toBigInteger(),
        intRange.last.toBigInteger()
    )

    /**
     * A range of values of type [BigInteger], from [start] up to and including [endInclusive].
     *
     * @param longRange A range of [Long] values that will be used to create this range.
     */
    constructor(longRange: LongRange) : this(
        longRange.first.toBigInteger(),
        longRange.last.toBigInteger()
    )

    override fun equals(other: Any?): Boolean {
        return other is BigIntegerRange && ((isEmpty() && other.isEmpty()) ||
            (start == other.start && endInclusive == other.endInclusive))
    }

    override fun hashCode(): Int = if (isEmpty()) -1 else Objects.hash(start, endInclusive)

    override fun toString(): String = "$start..$endInclusive"

    override fun iterator(): Iterator<BigInteger> = object : Iterator<BigInteger> {
        private var current: BigInteger = start

        override fun hasNext(): Boolean = current <= endInclusive

        override fun next(): BigInteger = current++
    }

    /**
     * Returns if all the values in [other] are also in this range.
     */
    operator fun contains(other: BigIntegerRange) = other.all { it in this }

    companion object {
        /**
         * An empty range of values of type [BigInteger].
         */
        val EMPTY = BigIntegerRange(BigInteger.ONE, BigInteger.ZERO)
    }
}

/**
 * Returns a [BigIntegerRange] containing the values from this up to and including [other].
 */
operator fun BigInteger.rangeTo(other: BigInteger): BigIntegerRange = BigIntegerRange(this, other)

/**
 * Returns a [BigIntegerRange] containing the values from this up until (but not including) [other].
 */
infix fun BigInteger.until(other: BigInteger): BigIntegerRange {
    return BigIntegerRange(this, other - BigInteger.ONE)
}
