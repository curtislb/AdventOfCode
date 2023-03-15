package com.curtislb.adventofcode.common.range

import java.math.BigInteger
import java.util.Objects

/**
 * An ordered, contiguous range of [BigInteger] values.
 *
 * @property first The minimum value contained in the range.
 * @property last The maximum value contained in the range.
 *
 * @constructor Creates a new instance of [BigIntegerRange] with the given [first] and [last]
 *  (inclusive) values.
 */
class BigIntegerRange(
    val first: BigInteger,
    val last: BigInteger
) : ClosedRange<BigInteger>, Iterable<BigInteger> {
    /**
     * The minimum value contained in the range.
     */
    override val start: BigInteger
        get() = first

    /**
     * The maximum value contained in the range.
     */
    override val endInclusive: BigInteger
        get() = last

    /**
     * Creates a new instance of [BigIntegerRange] with the given [first] and [last]
     * (inclusive) values.
     */
    constructor(first: Int, last: Int) : this(first.toBigInteger(), last.toBigInteger())

    /**
     * Creates a new instance of [BigIntegerRange] with the given [first] and [last]
     * (inclusive) values.
     */
    constructor(first: Long, last: Long) : this(first.toBigInteger(), last.toBigInteger())

    override fun toString(): String = "BigIntegerRange($first, $last)"

    override fun equals(other: Any?): Boolean = when {
        other !is BigIntegerRange -> false
        isEmpty() && other.isEmpty() -> true
        else -> first == other.first && last == other.last
    }

    override fun hashCode(): Int = if (isEmpty()) -1 else Objects.hash(first, last)

    override fun iterator(): Iterator<BigInteger> = object : Iterator<BigInteger> {
        private var current: BigInteger = first

        override fun hasNext(): Boolean = current <= last

        override fun next(): BigInteger = current++
    }

    companion object {
        /**
         * An empty range of values of type [BigInteger].
         */
        val EMPTY = BigIntegerRange(BigInteger.ONE, BigInteger.ZERO)
    }
}

/**
 * Returns a [BigIntegerRange] with the same integer values as this range.
 */
fun IntRange.toBigIntegerRange(): BigIntegerRange = BigIntegerRange(first, last)

/**
 * Returns a [BigIntegerRange] with the same integer values as this range.
 */
fun LongRange.toBigIntegerRange(): BigIntegerRange = BigIntegerRange(first, last)
