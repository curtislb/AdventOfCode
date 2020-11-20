package com.curtislb.adventofcode.common.range

import java.math.BigInteger
import java.util.Objects

/**
 * A range of values of type [BigInteger], from [start] up to and including [endInclusive].
 */
class BigIntegerRange(
    override val start: BigInteger,
    override val endInclusive: BigInteger
) : ClosedRange<BigInteger>, Iterable<BigInteger> {
    /**
     * The number of integer values that are within this range.
     */
    val size: BigInteger get() = if (isEmpty()) BigInteger.ZERO else endInclusive - start + BigInteger.ONE

    /**
     * A range of values of type [BigInteger], from [start] up to and including [endInclusive].
     *
     * @param intRange A range of values of type [Int] from which to create this range.
     */
    constructor(intRange: IntRange) : this(intRange.first.toBigInteger(), intRange.last.toBigInteger())

    /**
     * A range of values of type [BigInteger], from [start] up to and including [endInclusive].
     *
     * @param longRange A range of values of type [Long] from which to create this range.
     */
    constructor(longRange: LongRange) : this(longRange.first.toBigInteger(), longRange.last.toBigInteger())

    override fun iterator(): Iterator<BigInteger> = object : Iterator<BigInteger> {
        private var current: BigInteger = start

        override fun hasNext(): Boolean = current <= endInclusive

        override fun next(): BigInteger = current++
    }

    override fun equals(other: Any?): Boolean {
        return other is BigIntegerRange && ((isEmpty() && other.isEmpty())
            || (start == other.start && endInclusive == other.endInclusive))
    }

    override fun hashCode(): Int = if (isEmpty()) -1 else Objects.hash(start, endInclusive)

    override fun toString(): String = "$start..$endInclusive"

    companion object {
        /**
         * An empty range of values of type [BigInteger].
         */
        val EMPTY = BigIntegerRange(BigInteger.ONE, BigInteger.ZERO)
    }
}
