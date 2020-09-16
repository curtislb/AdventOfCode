package com.curtislb.adventofcode.common.math

import java.util.Objects
import kotlin.math.abs

/**
 * A unique representation of a rational number, whose [numerator] and [denominator] have been reduced to lowest terms.
 *
 * @throws IllegalArgumentException If [denominator] is 0.
 */
class Fraction(numerator: Long, denominator: Long = 1L) {
    /**
     * The simplified numerator for this fraction. May be negative or 0.
     */
    val numerator: Long

    /**
     * The simplified denominator for this fraction. Must be a positive integer.
     */
    val denominator: Long

    init {
        require(denominator != 0L) { "Fraction can't have a denominator of 0." }
        if (numerator == 0L) {
            // All fractions with a numerator of 0 are equivalent.
            this.numerator = 0L
            this.denominator = 1L
        } else {
            // Apply sign to numerator and reduce to lowest terms.
            val isNegative = (numerator < 0L) != (denominator < 0L)
            val absNumer = abs(numerator)
            val absDenom = abs(denominator)
            val gcd = greatestCommonDivisor(absNumer, absDenom)
            this.numerator = (if (isNegative) -absNumer else absNumer) / gcd
            this.denominator = absDenom / gcd
        }
    }

    /**
     * A unique representation of a rational number, whose [numerator] and [denominator] have been reduced to lowest
     * terms.
     *
     * @throws IllegalArgumentException If [denominator] is 0.
     */
    constructor(numerator: Int, denominator: Int = 1) : this(numerator.toLong(), denominator.toLong())

    /**
     * Returns the fraction that is the negative complement of this one.
     */
    operator fun unaryMinus(): Fraction = Fraction(-numerator, denominator)

    /**
     * Returns the sum of this and [other] as a fraction.
     */
    operator fun plus(other: Fraction): Fraction {
        val lcm = leastCommonMultiple(denominator, other.denominator)
        return Fraction(numerator * (lcm / denominator) + other.numerator * (lcm / other.denominator), lcm)
    }

    /**
     * Returns the difference between this and [other] as a fraction.
     */
    operator fun minus(other: Fraction) = this + (-other)

    /**
     * Returns the product of this and [other] as a fraction.
     */
    operator fun times(other: Fraction): Fraction {
        return Fraction(numerator * other.numerator, denominator * other.denominator)
    }

    /**
     * Returns the quotient of this and [other] as a fraction.
     */
    operator fun div(other: Fraction): Fraction = Fraction(numerator * other.denominator, denominator * other.numerator)

    /**
     * Returns the least integer value that is greater than or equal to the value of this fraction.
     */
    fun ceil(): Long = when {
        numerator == 0L -> 0L
        numerator > 0L -> denominator.nextMultipleAtLeast(numerator) / denominator
        else -> -denominator.prevMultipleAtMost(abs(numerator)) / denominator
    }

    /**
     * Returns the greatest integer value that is less than or equal to the value of this fraction.
     */
    fun floor(): Long = when {
        numerator == 0L -> 0L
        numerator > 0L -> denominator.prevMultipleAtMost(numerator) / denominator
        else -> -denominator.nextMultipleAtLeast(abs(numerator)) / denominator
    }

    override fun equals(other: Any?): Boolean {
        return other is Fraction && numerator == other.numerator && denominator == other.denominator
    }

    override fun hashCode(): Int = Objects.hash(numerator, denominator)

    override fun toString(): String = "$numerator/$denominator"
}
