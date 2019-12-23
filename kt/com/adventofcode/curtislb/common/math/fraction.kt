package com.adventofcode.curtislb.common.math

import java.util.Objects
import kotlin.math.abs

/**
 * A unique representation of a rational number, whose numerator and denominator have been reduced to lowest terms.
 * @param numer The numerator for this [Fraction]. May be negative and/or unreduced.
 * @param denom The denominator for this [Fraction]. May be negative and/or unreduced.
 * @throws IllegalArgumentException If [denom] is 0.
 */
class Fraction(numer: Int, denom: Int = 1) {
    /**
     * The simplified numerator for this [Fraction]. May be negative or 0.
     */
    val numer: Int

    /**
     * The simplified denominator for this [Fraction]. Guaranteed to be a positive [Int].
     */
    val denom: Int

    init {
        when {
            denom == 0 -> throw IllegalArgumentException("Fraction can't have a denominator of 0")
            numer == 0 -> {
                // All fractions with a numerator of 0 are equivalent
                this.numer = 0
                this.denom = 1
            }
            else -> {
                // Apply sign to numerator and reduce to lowest terms
                val isNegative = (numer < 0) != (denom < 0)
                val absNumer = abs(numer)
                val absDenom = abs(denom)
                val gcd = greatestCommonDivisor(absNumer, absDenom)
                this.numer = (if (isNegative) -absNumer else absNumer) / gcd
                this.denom = absDenom / gcd
            }
        }
    }

    operator fun unaryPlus(): Fraction = this

    operator fun unaryMinus(): Fraction = Fraction(-numer, denom)

    operator fun plus(other: Fraction): Fraction {
        val lcm = leastCommonMultiple(denom, other.denom)
        return Fraction(numer * (lcm / denom) + other.numer * (lcm / other.denom), lcm)
    }

    operator fun minus(other: Fraction) = this + (-other)

    operator fun times(other: Fraction): Fraction = Fraction(numer * other.numer, denom * other.denom)

    operator fun div(other: Fraction): Fraction = Fraction(numer * other.denom, denom * other.numer)

    override fun equals(other: Any?): Boolean {
        return other is Fraction && numer == other.numer && denom == other.denom
    }

    override fun hashCode(): Int = Objects.hash(numer, denom)

    override fun toString(): String = "$numer/$denom"
}
