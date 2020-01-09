package com.adventofcode.curtislb.common.math

import java.util.Objects
import kotlin.math.abs

/**
 * A unique representation of a rational number, whose numerator and denominator have been reduced to lowest terms.
 * @param numer The numerator for this [Fraction]. May be negative and/or unreduced.
 * @param denom The denominator for this [Fraction]. May be negative and/or unreduced.
 * @throws IllegalArgumentException If [denom] is 0.
 */
class Fraction(numer: Long, denom: Long = 1L) {
    /**
     * The simplified numerator for this [Fraction]. May be negative or 0.
     */
    val numer: Long

    /**
     * The simplified denominator for this [Fraction]. Guaranteed to be a positive [Int].
     */
    val denom: Long

    init {
        when {
            denom == 0L -> throw IllegalArgumentException("Fraction can't have a denominator of 0.")
            numer == 0L -> {
                // All fractions with a numerator of 0 are equivalent
                this.numer = 0L
                this.denom = 1L
            }
            else -> {
                // Apply sign to numerator and reduce to lowest terms
                val isNegative = (numer < 0L) != (denom < 0L)
                val absNumer = abs(numer)
                val absDenom = abs(denom)
                val gcd = greatestCommonDivisor(absNumer, absDenom)
                this.numer = (if (isNegative) -absNumer else absNumer) / gcd
                this.denom = absDenom / gcd
            }
        }
    }

    /**
     * A unique representation of a rational number, whose numerator and denominator have been reduced to lowest terms.
     * @param numer The numerator for this [Fraction]. May be negative and/or unreduced.
     * @param denom The denominator for this [Fraction]. May be negative and/or unreduced.
     * @throws IllegalArgumentException If [denom] is 0.
     */
    constructor(numer: Int, denom: Int) : this(numer.toLong(), denom.toLong())

    /**
     * Computes the negative value of this [Fraction].
     * @return A new [Fraction] whose value is the negative complement of this [Fraction].
     */
    operator fun unaryMinus(): Fraction = Fraction(-numer, denom)

    /**
     * Computes the sum of this and another [Fraction].
     * @param other The [Fraction] to be added to this one.
     * @return A new [Fraction] whose value is the sum of this [Fraction] and [other].
     */
    operator fun plus(other: Fraction): Fraction {
        val lcm = leastCommonMultiple(denom, other.denom)
        return Fraction(numer * (lcm / denom) + other.numer * (lcm / other.denom), lcm)
    }

    /**
     * Computes the difference of this and another [Fraction].
     * @param other The [Fraction] to be subtracted from this one.
     * @return A new [Fraction] whose value is this [Fraction] minus [other].
     */
    operator fun minus(other: Fraction) = this + (-other)

    /**
     * Computes the product of this and another [Fraction].
     * @param other The [Fraction] to be multiplied with this one.
     * @return A new [Fraction] whose value is the product of this [Fraction] and [other].
     */
    operator fun times(other: Fraction): Fraction = Fraction(numer * other.numer, denom * other.denom)

    /**
     * Computes the quotient of this and another [Fraction].
     * @param other The [Fraction] that will divide this one.
     * @return A new [Fraction] whose value is the product of this [Fraction] divided by [other].
     */
    operator fun div(other: Fraction): Fraction = Fraction(numer * other.denom, denom * other.numer)

    /**
     * Produces the integer ceiling of this [Fraction].
     * @return The least [Long] value that is greater than or equal to the value of this fraction.
     */
    fun ceil(): Long {
        return when {
            numer == 0L -> 0L
            numer > 0L -> denom.nextMultipleAtLeast(numer) / denom
            else -> -denom.prevMultipleAtMost(abs(numer)) / denom
        }
    }

    /**
     * Produces the integer floor of this [Fraction].
     * @return The greatest [Long] value that is less than or equal to the value of this fraction.
     */
    fun floor(): Long {
        return when {
            numer == 0L -> 0L
            numer > 0L -> denom.prevMultipleAtMost(numer) / denom
            else -> -denom.nextMultipleAtLeast(abs(numer)) / denom
        }
    }

    override fun equals(other: Any?): Boolean {
        return other is Fraction && numer == other.numer && denom == other.denom
    }

    override fun hashCode(): Int = Objects.hash(numer, denom)

    override fun toString(): String = "$numer/$denom"
}
