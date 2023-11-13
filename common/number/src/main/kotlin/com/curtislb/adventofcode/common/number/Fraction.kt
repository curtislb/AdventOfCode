package com.curtislb.adventofcode.common.number

import java.util.Objects
import kotlin.math.abs

/**
 * A unique representation of a rational number, with reduced [numerator] and [denominator].
 *
 * @property numerator The numerator of the reduced-form fraction. May be negative or zero.
 * @property denominator The denominator of the reduced-form fraction. Must be a positive integer.
 */
class Fraction private constructor(val numerator: Long, val denominator: Long) {
    /**
     * Returns the sum of this fraction and [other].
     */
    operator fun plus(other: Fraction): Fraction = when {
        numerator == 0L -> other
        other.numerator == 0L -> this
        else -> {
            val lcm = leastCommonMultiple(denominator, other.denominator)
            val scaledNumerator = numerator * (lcm / denominator)
            val otherScaledNumerator = other.numerator * (lcm / other.denominator)
            valueOf(scaledNumerator + otherScaledNumerator, lcm)
        }
    }

    /**
     * Returns the difference between this fraction and [other].
     */
    operator fun minus(other: Fraction) = -other + this

    /**
     * Returns the negative complement of this fraction.
     */
    operator fun unaryMinus(): Fraction =
        if (numerator == 0L) ZERO else Fraction(-numerator, denominator)

    /**
     * Returns the product of this fraction and [other].
     */
    operator fun times(other: Fraction): Fraction =
        valueOf(numerator * other.numerator, denominator * other.denominator)

    /**
     * Returns the quotient of this fraction and [other].
     *
     * @throws ArithmeticException If [other] is [ZERO].
     */
    operator fun div(other: Fraction): Fraction {
        if (other.numerator == 0L) {
            throw ArithmeticException("Can't divide by a zero-value fraction")
        }
        return valueOf(numerator * other.denominator, denominator * other.numerator)
    }

    /**
     * Returns the least integer value that is greater than or equal to this fraction.
     */
    fun ceil(): Long = when {
        numerator == 0L -> 0L
        numerator > 0L -> denominator.nextMultipleAtLeast(numerator) / denominator
        else -> -(denominator.prevMultipleAtMost(-numerator)) / denominator
    }

    /**
     * Returns the greatest integer value that is less than or equal to this fraction.
     */
    fun floor(): Long = when {
        numerator == 0L -> 0L
        numerator > 0L -> denominator.prevMultipleAtMost(numerator) / denominator
        else -> -(denominator.nextMultipleAtLeast(-numerator)) / denominator
    }

    override fun toString(): String = "$numerator/$denominator"

    override fun equals(other: Any?): Boolean =
        other is Fraction && numerator == other.numerator && denominator == other.denominator

    override fun hashCode(): Int = Objects.hash(numerator, denominator)

    companion object {
        /**
         * A [Fraction] representing the value 0.
         */
        val ZERO: Fraction = Fraction(0L, 1L)

        /**
         * Returns a reduced-form fraction that represents the value of [numerator] / [denominator].
         *
         * @throws IllegalArgumentException If [denominator] is zero.
         */
        fun valueOf(numerator: Int, denominator: Int): Fraction =
            valueOf(numerator.toLong(), denominator.toLong())

        /**
         * Returns a reduced-form fraction that represents the value of [numerator] / [denominator].
         *
         * @throws IllegalArgumentException If [denominator] is zero.
         */
        fun valueOf(numerator: Long, denominator: Long = 1L): Fraction {
            require(denominator != 0L) { "Fraction can't have a denominator of 0" }

            // Fractions with a numerator of 0 are equivalent
            if (numerator == 0L) {
                return ZERO
            }

            // Apply sign to numerator and reduce terms
            val isNegative = (numerator < 0L) != (denominator < 0L)
            val absNumerator = abs(numerator)
            val absDenominator = abs(denominator)
            val gcd = greatestCommonDivisor(absNumerator, absDenominator)
            val reducedNumerator = (if (isNegative) -absNumerator else absNumerator) / gcd
            val reducedDenominator = absDenominator / gcd
            return Fraction(reducedNumerator, reducedDenominator)
        }
    }
}
