package com.adventofcode.curtislb.common.math

import java.util.Objects
import kotlin.math.abs

/**
 * TODO
 */
class Fraction(numer: Int, denom: Int = 1) {
    /**
     * TODO
     */
    val numer: Int

    /**
     * TODO
     */
    val denom: Int

    init {
        if (denom == 0) {
            throw IllegalArgumentException("Fraction can't have a denominator of 0")
        }
        val isNegative = (numer < 0) != (denom < 0)
        val absNumer = abs(numer)
        val absDenom = abs(denom)
        val commonDivisor = greatestCommonDivisor(absNumer, absDenom)
        this.numer = (if (isNegative) -absNumer else absNumer) / commonDivisor
        this.denom = absDenom / commonDivisor
    }

    operator fun unaryPlus(): Fraction = this

    operator fun unaryMinus(): Fraction = Fraction(-numer, denom)

    operator fun plus(other: Fraction): Fraction {
        val newDenom = leastCommonMultiple(denom, other.denom)
        return Fraction(numer * (newDenom / denom) + other.numer * (newDenom / other.denom), newDenom)
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
