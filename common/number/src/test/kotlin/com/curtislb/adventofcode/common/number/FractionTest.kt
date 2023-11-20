package com.curtislb.adventofcode.common.number

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests the [Fraction] class.
 */
class FractionTest {
    @Test
    fun plus_thisZero_otherZero() {
        val fraction = Fraction.ZERO
        val other = Fraction.ZERO
        assertThat(fraction + other).isEqualTo(Fraction.ZERO)
    }

    @Test
    fun plus_thisZero_otherPositiveFraction() {
        val fraction = Fraction.ZERO
        val other = Fraction.valueOf(2, 3)
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(2, 3))
    }

    @Test
    fun plus_thisZero_otherPositiveWhole() {
        val fraction = Fraction.ZERO
        val other = Fraction.valueOf(2)
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(2))
    }

    @Test
    fun plus_thisZero_otherNegativeFraction() {
        val fraction = Fraction.ZERO
        val other = Fraction.valueOf(-2, 3)
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(-2, 3))
    }

    @Test
    fun plus_thisZero_otherNegativeWhole() {
        val fraction = Fraction.ZERO
        val other = Fraction.valueOf(-2)
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(-2))
    }

    @Test
    fun plus_thisPositiveFraction_otherZero() {
        val fraction = Fraction.valueOf(2, 3)
        val other = Fraction.ZERO
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(2, 3))
    }

    @Test
    fun plus_thisPositiveFraction_otherPositiveFraction() {
        val fraction = Fraction.valueOf(2, 3)
        val other = Fraction.valueOf(3, 5)
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(19, 15))
    }

    @Test
    fun plus_thisPositiveFraction_otherPositiveWhole() {
        val fraction = Fraction.valueOf(2, 3)
        val other = Fraction.valueOf(2)
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(8, 3))
    }

    @Test
    fun plus_thisPositiveFraction_otherNegativeFraction() {
        val fraction = Fraction.valueOf(2, 3)
        val other = Fraction.valueOf(-3, 5)
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(1, 15))
    }

    @Test
    fun plus_thisPositiveFraction_otherNegativeWhole() {
        val fraction = Fraction.valueOf(2, 3)
        val other = Fraction.valueOf(-2)
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(-4, 3))
    }

    @Test
    fun plus_thisPositiveWhole_otherZero() {
        val fraction = Fraction.valueOf(3)
        val other = Fraction.ZERO
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(3))
    }

    @Test
    fun plus_thisPositiveWhole_otherPositiveFraction() {
        val fraction = Fraction.valueOf(3)
        val other = Fraction.valueOf(3, 5)
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(18, 5))
    }

    @Test
    fun plus_thisPositiveWhole_otherPositiveWhole() {
        val fraction = Fraction.valueOf(3)
        val other = Fraction.valueOf(2)
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(5))
    }

    @Test
    fun plus_thisPositiveWhole_otherNegativeFraction() {
        val fraction = Fraction.valueOf(3)
        val other = Fraction.valueOf(-3, 5)
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(12, 5))
    }

    @Test
    fun plus_thisPositiveWhole_otherNegativeWhole() {
        val fraction = Fraction.valueOf(3)
        val other = Fraction.valueOf(-2)
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(1))
    }

    @Test
    fun plus_thisNegativeFraction_otherZero() {
        val fraction = Fraction.valueOf(-2, 3)
        val other = Fraction.ZERO
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(-2, 3))
    }

    @Test
    fun plus_thisNegativeFraction_otherPositiveFraction() {
        val fraction = Fraction.valueOf(-2, 3)
        val other = Fraction.valueOf(3, 5)
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(-1, 15))
    }

    @Test
    fun plus_thisNegativeFraction_otherPositiveWhole() {
        val fraction = Fraction.valueOf(-2, 3)
        val other = Fraction.valueOf(2)
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(4, 3))
    }

    @Test
    fun plus_thisNegativeFraction_otherNegativeFraction() {
        val fraction = Fraction.valueOf(-2, 3)
        val other = Fraction.valueOf(-3, 5)
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(-19, 15))
    }

    @Test
    fun plus_thisNegativeFraction_otherNegativeWhole() {
        val fraction = Fraction.valueOf(-2, 3)
        val other = Fraction.valueOf(-2)
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(-8, 3))
    }

    @Test
    fun plus_thisNegativeWhole_otherZero() {
        val fraction = Fraction.valueOf(-3)
        val other = Fraction.ZERO
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(-3))
    }

    @Test
    fun plus_thisNegativeWhole_otherPositiveFraction() {
        val fraction = Fraction.valueOf(-3)
        val other = Fraction.valueOf(3, 5)
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(-12, 5))
    }

    @Test
    fun plus_thisNegativeWhole_otherPositiveWhole() {
        val fraction = Fraction.valueOf(-3)
        val other = Fraction.valueOf(2)
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(-1))
    }

    @Test
    fun plus_thisNegativeWhole_otherNegativeFraction() {
        val fraction = Fraction.valueOf(-3)
        val other = Fraction.valueOf(-3, 5)
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(-18, 5))
    }

    @Test
    fun plus_thisNegativeWhole_otherNegativeWhole() {
        val fraction = Fraction.valueOf(-3)
        val other = Fraction.valueOf(-2)
        assertThat(fraction + other).isEqualTo(Fraction.valueOf(-5))
    }

    @Test
    fun minus_thisZero_otherZero() {
        val fraction = Fraction.ZERO
        val other = Fraction.ZERO
        assertThat(fraction - other).isEqualTo(Fraction.ZERO)
    }

    @Test
    fun minus_thisZero_otherPositiveFraction() {
        val fraction = Fraction.ZERO
        val other = Fraction.valueOf(2, 3)
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(-2, 3))
    }

    @Test
    fun minus_thisZero_otherPositiveWhole() {
        val fraction = Fraction.ZERO
        val other = Fraction.valueOf(2)
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(-2))
    }

    @Test
    fun minus_thisZero_otherNegativeFraction() {
        val fraction = Fraction.ZERO
        val other = Fraction.valueOf(-2, 3)
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(2, 3))
    }

    @Test
    fun minus_thisZero_otherNegativeWhole() {
        val fraction = Fraction.ZERO
        val other = Fraction.valueOf(-2)
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(2))
    }

    @Test
    fun minus_thisPositiveFraction_otherZero() {
        val fraction = Fraction.valueOf(2, 3)
        val other = Fraction.ZERO
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(2, 3))
    }

    @Test
    fun minus_thisPositiveFraction_otherPositiveFraction() {
        val fraction = Fraction.valueOf(2, 3)
        val other = Fraction.valueOf(3, 5)
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(1, 15))
    }

    @Test
    fun minus_thisPositiveFraction_otherPositiveWhole() {
        val fraction = Fraction.valueOf(2, 3)
        val other = Fraction.valueOf(2)
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(-4, 3))
    }

    @Test
    fun minus_thisPositiveFraction_otherNegativeFraction() {
        val fraction = Fraction.valueOf(2, 3)
        val other = Fraction.valueOf(-3, 5)
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(19, 15))
    }

    @Test
    fun minus_thisPositiveFraction_otherNegativeWhole() {
        val fraction = Fraction.valueOf(2, 3)
        val other = Fraction.valueOf(-2)
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(8, 3))
    }

    @Test
    fun minus_thisPositiveWhole_otherZero() {
        val fraction = Fraction.valueOf(3)
        val other = Fraction.ZERO
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(3))
    }

    @Test
    fun minus_thisPositiveWhole_otherPositiveFraction() {
        val fraction = Fraction.valueOf(3)
        val other = Fraction.valueOf(3, 5)
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(12, 5))
    }

    @Test
    fun minus_thisPositiveWhole_otherPositiveWhole() {
        val fraction = Fraction.valueOf(3)
        val other = Fraction.valueOf(2)
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(1))
    }

    @Test
    fun minus_thisPositiveWhole_otherNegativeFraction() {
        val fraction = Fraction.valueOf(3)
        val other = Fraction.valueOf(-3, 5)
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(18, 5))
    }

    @Test
    fun minus_thisPositiveWhole_otherNegativeWhole() {
        val fraction = Fraction.valueOf(3)
        val other = Fraction.valueOf(-2)
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(5))
    }

    @Test
    fun minus_thisNegativeFraction_otherZero() {
        val fraction = Fraction.valueOf(-2, 3)
        val other = Fraction.ZERO
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(-2, 3))
    }

    @Test
    fun minus_thisNegativeFraction_otherPositiveFraction() {
        val fraction = Fraction.valueOf(-2, 3)
        val other = Fraction.valueOf(3, 5)
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(-19, 15))
    }

    @Test
    fun minus_thisNegativeFraction_otherPositiveWhole() {
        val fraction = Fraction.valueOf(-2, 3)
        val other = Fraction.valueOf(2)
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(-8, 3))
    }

    @Test
    fun minus_thisNegativeFraction_otherNegativeFraction() {
        val fraction = Fraction.valueOf(-2, 3)
        val other = Fraction.valueOf(-3, 5)
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(-1, 15))
    }

    @Test
    fun minus_thisNegativeFraction_otherNegativeWhole() {
        val fraction = Fraction.valueOf(-2, 3)
        val other = Fraction.valueOf(-2)
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(4, 3))
    }

    @Test
    fun minus_thisNegativeWhole_otherZero() {
        val fraction = Fraction.valueOf(-3)
        val other = Fraction.ZERO
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(-3))
    }

    @Test
    fun minus_thisNegativeWhole_otherPositiveFraction() {
        val fraction = Fraction.valueOf(-3)
        val other = Fraction.valueOf(3, 5)
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(-18, 5))
    }

    @Test
    fun minus_thisNegativeWhole_otherPositiveWhole() {
        val fraction = Fraction.valueOf(-3)
        val other = Fraction.valueOf(2)
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(-5))
    }

    @Test
    fun minus_thisNegativeWhole_otherNegativeFraction() {
        val fraction = Fraction.valueOf(-3)
        val other = Fraction.valueOf(-3, 5)
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(-12, 5))
    }

    @Test
    fun minus_thisNegativeWhole_otherNegativeWhole() {
        val fraction = Fraction.valueOf(-3)
        val other = Fraction.valueOf(-2)
        assertThat(fraction - other).isEqualTo(Fraction.valueOf(-1))
    }

    @Test
    fun unaryMinus_whenZero() {
        val fraction = Fraction.ZERO
        assertThat(-fraction).isEqualTo(Fraction.ZERO)
    }

    @Test
    fun unaryMinus_whenPositiveFraction() {
        val fraction = Fraction.valueOf(2, 3)
        assertThat(-fraction).isEqualTo(Fraction.valueOf(-2, 3))
    }

    @Test
    fun unaryMinus_whenPositiveWhole() {
        val fraction = Fraction.valueOf(2)
        assertThat(-fraction).isEqualTo(Fraction.valueOf(-2))
    }

    @Test
    fun unaryMinus_whenNegativeFraction() {
        val fraction = Fraction.valueOf(-2, 3)
        assertThat(-fraction).isEqualTo(Fraction.valueOf(2, 3))
    }

    @Test
    fun unaryMinus_whenNegativeWhole() {
        val fraction = Fraction.valueOf(-2)
        assertThat(-fraction).isEqualTo(Fraction.valueOf(2))
    }

    @Test
    fun times_thisZero_otherZero() {
        val fraction = Fraction.ZERO
        val other = Fraction.ZERO
        assertThat(fraction * other).isEqualTo(Fraction.ZERO)
    }

    @Test
    fun times_thisZero_otherPositiveFraction() {
        val fraction = Fraction.ZERO
        val other = Fraction.valueOf(2, 3)
        assertThat(fraction * other).isEqualTo(Fraction.ZERO)
    }

    @Test
    fun times_thisZero_otherPositiveWhole() {
        val fraction = Fraction.ZERO
        val other = Fraction.valueOf(2)
        assertThat(fraction * other).isEqualTo(Fraction.ZERO)
    }

    @Test
    fun times_thisZero_otherNegativeFraction() {
        val fraction = Fraction.ZERO
        val other = Fraction.valueOf(-2, 3)
        assertThat(fraction * other).isEqualTo(Fraction.ZERO)
    }

    @Test
    fun times_thisZero_otherNegativeWhole() {
        val fraction = Fraction.ZERO
        val other = Fraction.valueOf(-2)
        assertThat(fraction * other).isEqualTo(Fraction.ZERO)
    }

    @Test
    fun times_thisPositiveFraction_otherZero() {
        val fraction = Fraction.valueOf(2, 3)
        val other = Fraction.ZERO
        assertThat(fraction * other).isEqualTo(Fraction.ZERO)
    }

    @Test
    fun times_thisPositiveFraction_otherPositiveFraction() {
        val fraction = Fraction.valueOf(2, 3)
        val other = Fraction.valueOf(3, 5)
        assertThat(fraction * other).isEqualTo(Fraction.valueOf(2, 5))
    }

    @Test
    fun times_thisPositiveFraction_otherPositiveWhole() {
        val fraction = Fraction.valueOf(2, 3)
        val other = Fraction.valueOf(2)
        assertThat(fraction * other).isEqualTo(Fraction.valueOf(4, 3))
    }

    @Test
    fun times_thisPositiveFraction_otherNegativeFraction() {
        val fraction = Fraction.valueOf(2, 3)
        val other = Fraction.valueOf(-3, 5)
        assertThat(fraction * other).isEqualTo(Fraction.valueOf(-2, 5))
    }

    @Test
    fun times_thisPositiveFraction_otherNegativeWhole() {
        val fraction = Fraction.valueOf(2, 3)
        val other = Fraction.valueOf(-2)
        assertThat(fraction * other).isEqualTo(Fraction.valueOf(-4, 3))
    }

    @Test
    fun times_thisPositiveWhole_otherZero() {
        val fraction = Fraction.valueOf(3)
        val other = Fraction.ZERO
        assertThat(fraction * other).isEqualTo(Fraction.ZERO)
    }

    @Test
    fun times_thisPositiveWhole_otherPositiveFraction() {
        val fraction = Fraction.valueOf(3)
        val other = Fraction.valueOf(3, 5)
        assertThat(fraction * other).isEqualTo(Fraction.valueOf(9, 5))
    }

    @Test
    fun times_thisPositiveWhole_otherPositiveWhole() {
        val fraction = Fraction.valueOf(3)
        val other = Fraction.valueOf(2)
        assertThat(fraction * other).isEqualTo(Fraction.valueOf(6))
    }

    @Test
    fun times_thisPositiveWhole_otherNegativeFraction() {
        val fraction = Fraction.valueOf(3)
        val other = Fraction.valueOf(-3, 5)
        assertThat(fraction * other).isEqualTo(Fraction.valueOf(-9, 5))
    }

    @Test
    fun times_thisPositiveWhole_otherNegativeWhole() {
        val fraction = Fraction.valueOf(3)
        val other = Fraction.valueOf(-2)
        assertThat(fraction * other).isEqualTo(Fraction.valueOf(-6))
    }

    @Test
    fun times_thisNegativeFraction_otherZero() {
        val fraction = Fraction.valueOf(-2, 3)
        val other = Fraction.ZERO
        assertThat(fraction * other).isEqualTo(Fraction.ZERO)
    }

    @Test
    fun times_thisNegativeFraction_otherPositiveFraction() {
        val fraction = Fraction.valueOf(-2, 3)
        val other = Fraction.valueOf(3, 5)
        assertThat(fraction * other).isEqualTo(Fraction.valueOf(-2, 5))
    }

    @Test
    fun times_thisNegativeFraction_otherPositiveWhole() {
        val fraction = Fraction.valueOf(-2, 3)
        val other = Fraction.valueOf(2)
        assertThat(fraction * other).isEqualTo(Fraction.valueOf(-4, 3))
    }

    @Test
    fun times_thisNegativeFraction_otherNegativeFraction() {
        val fraction = Fraction.valueOf(-2, 3)
        val other = Fraction.valueOf(-3, 5)
        assertThat(fraction * other).isEqualTo(Fraction.valueOf(2, 5))
    }

    @Test
    fun times_thisNegativeFraction_otherNegativeWhole() {
        val fraction = Fraction.valueOf(-2, 3)
        val other = Fraction.valueOf(-2)
        assertThat(fraction * other).isEqualTo(Fraction.valueOf(4, 3))
    }

    @Test
    fun times_thisNegativeWhole_otherZero() {
        val fraction = Fraction.valueOf(-3)
        val other = Fraction.ZERO
        assertThat(fraction * other).isEqualTo(Fraction.ZERO)
    }

    @Test
    fun times_thisNegativeWhole_otherPositiveFraction() {
        val fraction = Fraction.valueOf(-3)
        val other = Fraction.valueOf(3, 5)
        assertThat(fraction * other).isEqualTo(Fraction.valueOf(-9, 5))
    }

    @Test
    fun times_thisNegativeWhole_otherPositiveWhole() {
        val fraction = Fraction.valueOf(-3)
        val other = Fraction.valueOf(2)
        assertThat(fraction * other).isEqualTo(Fraction.valueOf(-6))
    }

    @Test
    fun times_thisNegativeWhole_otherNegativeFraction() {
        val fraction = Fraction.valueOf(-3)
        val other = Fraction.valueOf(-3, 5)
        assertThat(fraction * other).isEqualTo(Fraction.valueOf(9, 5))
    }

    @Test
    fun times_thisNegativeWhole_otherNegativeWhole() {
        val fraction = Fraction.valueOf(-3)
        val other = Fraction.valueOf(-2)
        assertThat(fraction * other).isEqualTo(Fraction.valueOf(6))
    }


    @Test
    fun div_thisZero_otherZero() {
        val fraction = Fraction.ZERO
        val other = Fraction.ZERO
        assertThrows<ArithmeticException> { fraction / other }
    }

    @Test
    fun div_thisZero_otherPositiveFraction() {
        val fraction = Fraction.ZERO
        val other = Fraction.valueOf(2, 3)
        assertThat(fraction / other).isEqualTo(Fraction.ZERO)
    }

    @Test
    fun div_thisZero_otherPositiveWhole() {
        val fraction = Fraction.ZERO
        val other = Fraction.valueOf(2)
        assertThat(fraction / other).isEqualTo(Fraction.ZERO)
    }

    @Test
    fun div_thisZero_otherNegativeFraction() {
        val fraction = Fraction.ZERO
        val other = Fraction.valueOf(-2, 3)
        assertThat(fraction / other).isEqualTo(Fraction.ZERO)
    }

    @Test
    fun div_thisZero_otherNegativeWhole() {
        val fraction = Fraction.ZERO
        val other = Fraction.valueOf(-2)
        assertThat(fraction / other).isEqualTo(Fraction.ZERO)
    }

    @Test
    fun div_thisPositiveFraction_otherZero() {
        val fraction = Fraction.valueOf(2, 3)
        val other = Fraction.ZERO
        assertThrows<ArithmeticException> { fraction / other }
    }

    @Test
    fun div_thisPositiveFraction_otherPositiveFraction() {
        val fraction = Fraction.valueOf(2, 3)
        val other = Fraction.valueOf(3, 5)
        assertThat(fraction * other).isEqualTo(Fraction.valueOf(2, 5))
    }

    @Test
    fun div_thisPositiveFraction_otherPositiveWhole() {
        val fraction = Fraction.valueOf(2, 3)
        val other = Fraction.valueOf(2)
        assertThat(fraction / other).isEqualTo(Fraction.valueOf(1, 3))
    }

    @Test
    fun div_thisPositiveFraction_otherNegativeFraction() {
        val fraction = Fraction.valueOf(2, 3)
        val other = Fraction.valueOf(-3, 5)
        assertThat(fraction / other).isEqualTo(Fraction.valueOf(-10, 9))
    }

    @Test
    fun div_thisPositiveFraction_otherNegativeWhole() {
        val fraction = Fraction.valueOf(2, 3)
        val other = Fraction.valueOf(-2)
        assertThat(fraction / other).isEqualTo(Fraction.valueOf(-1, 3))
    }

    @Test
    fun div_thisPositiveWhole_otherZero() {
        val fraction = Fraction.valueOf(3)
        val other = Fraction.ZERO
        assertThrows<ArithmeticException> { fraction / other }
    }

    @Test
    fun div_thisPositiveWhole_otherPositiveFraction() {
        val fraction = Fraction.valueOf(3)
        val other = Fraction.valueOf(3, 5)
        assertThat(fraction / other).isEqualTo(Fraction.valueOf(5))
    }

    @Test
    fun div_thisPositiveWhole_otherPositiveWhole() {
        val fraction = Fraction.valueOf(3)
        val other = Fraction.valueOf(2)
        assertThat(fraction / other).isEqualTo(Fraction.valueOf(3, 2))
    }

    @Test
    fun div_thisPositiveWhole_otherNegativeFraction() {
        val fraction = Fraction.valueOf(3)
        val other = Fraction.valueOf(-3, 5)
        assertThat(fraction / other).isEqualTo(Fraction.valueOf(-5))
    }

    @Test
    fun div_thisPositiveWhole_otherNegativeWhole() {
        val fraction = Fraction.valueOf(3)
        val other = Fraction.valueOf(-2)
        assertThat(fraction / other).isEqualTo(Fraction.valueOf(-3, 2))
    }

    @Test
    fun div_thisNegativeFraction_otherZero() {
        val fraction = Fraction.valueOf(-2, 3)
        val other = Fraction.ZERO
        assertThrows<ArithmeticException> { fraction / other }
    }

    @Test
    fun div_thisNegativeFraction_otherPositiveFraction() {
        val fraction = Fraction.valueOf(-2, 3)
        val other = Fraction.valueOf(3, 5)
        assertThat(fraction / other).isEqualTo(Fraction.valueOf(-10, 9))
    }

    @Test
    fun div_thisNegativeFraction_otherPositiveWhole() {
        val fraction = Fraction.valueOf(-2, 3)
        val other = Fraction.valueOf(2)
        assertThat(fraction / other).isEqualTo(Fraction.valueOf(-1, 3))
    }

    @Test
    fun div_thisNegativeFraction_otherNegativeFraction() {
        val fraction = Fraction.valueOf(-2, 3)
        val other = Fraction.valueOf(-3, 5)
        assertThat(fraction / other).isEqualTo(Fraction.valueOf(10, 9))
    }

    @Test
    fun div_thisNegativeFraction_otherNegativeWhole() {
        val fraction = Fraction.valueOf(-2, 3)
        val other = Fraction.valueOf(-2)
        assertThat(fraction / other).isEqualTo(Fraction.valueOf(1, 3))
    }

    @Test
    fun div_thisNegativeWhole_otherZero() {
        val fraction = Fraction.valueOf(-3)
        val other = Fraction.ZERO
        assertThrows<ArithmeticException> { fraction / other }
    }

    @Test
    fun div_thisNegativeWhole_otherPositiveFraction() {
        val fraction = Fraction.valueOf(-3)
        val other = Fraction.valueOf(3, 5)
        assertThat(fraction / other).isEqualTo(Fraction.valueOf(-5))
    }

    @Test
    fun div_thisNegativeWhole_otherPositiveWhole() {
        val fraction = Fraction.valueOf(-3)
        val other = Fraction.valueOf(2)
        assertThat(fraction / other).isEqualTo(Fraction.valueOf(-3, 2))
    }

    @Test
    fun div_thisNegativeWhole_otherNegativeFraction() {
        val fraction = Fraction.valueOf(-3)
        val other = Fraction.valueOf(-3, 5)
        assertThat(fraction / other).isEqualTo(Fraction.valueOf(5))
    }

    @Test
    fun div_thisNegativeWhole_otherNegativeWhole() {
        val fraction = Fraction.valueOf(-3)
        val other = Fraction.valueOf(-2)
        assertThat(fraction / other).isEqualTo(Fraction.valueOf(3, 2))
    }

    @Test
    fun ceil_whenZero() {
        val fraction = Fraction.ZERO
        assertThat(fraction.ceil()).isEqualTo(0L)
    }

    @Test
    fun ceil_whenPositiveWhole() {
        val fraction = Fraction.valueOf(2)
        assertThat(fraction.ceil()).isEqualTo(2L)
    }

    @Test
    fun ceil_whenPositiveProperFraction() {
        val fraction = Fraction.valueOf(2, 5)
        assertThat(fraction.ceil()).isEqualTo(1L)
    }

    @Test
    fun ceil_whenPositiveImproperFraction() {
        val fraction = Fraction.valueOf(9, 4)
        assertThat(fraction.ceil()).isEqualTo(3L)
    }

    @Test
    fun ceil_whenNegativeWhole() {
        val fraction = Fraction.valueOf(-3)
        assertThat(fraction.ceil()).isEqualTo(-3L)
    }

    @Test
    fun ceil_whenNegativeProperFraction() {
        val fraction = Fraction.valueOf(-2, 5)
        assertThat(fraction.ceil()).isEqualTo(0L)
    }

    @Test
    fun ceil_whenNegativeImproperFraction() {
        val fraction = Fraction.valueOf(-9, 4)
        assertThat(fraction.ceil()).isEqualTo(-2L)
    }

    @Test
    fun floor_whenZero() {
        val fraction = Fraction.ZERO
        assertThat(fraction.floor()).isEqualTo(0L)
    }

    @Test
    fun floor_whenPositiveWhole() {
        val fraction = Fraction.valueOf(2)
        assertThat(fraction.floor()).isEqualTo(2L)
    }

    @Test
    fun floor_whenPositiveProperFraction() {
        val fraction = Fraction.valueOf(2, 5)
        assertThat(fraction.floor()).isEqualTo(0L)
    }

    @Test
    fun floor_whenPositiveImproperFraction() {
        val fraction = Fraction.valueOf(9, 4)
        assertThat(fraction.floor()).isEqualTo(2L)
    }

    @Test
    fun floor_whenNegativeWhole() {
        val fraction = Fraction.valueOf(-3)
        assertThat(fraction.floor()).isEqualTo(-3L)
    }

    @Test
    fun floor_whenNegativeProperFraction() {
        val fraction = Fraction.valueOf(-2, 5)
        assertThat(fraction.floor()).isEqualTo(-1L)
    }

    @Test
    fun floor_whenNegativeImproperFraction() {
        val fraction = Fraction.valueOf(-9, 4)
        assertThat(fraction.floor()).isEqualTo(-3L)
    }

    @Test
    fun equals_nonFraction() {
        val fraction = Fraction.valueOf(1)
        assertThat(fraction).isNotEqualTo(1)
    }

    @Test
    fun equals_differentNumerator() {
        val fraction = Fraction.valueOf(1, 2)
        val other = Fraction.valueOf(1, 3)
        assertThat(fraction).isNotEqualTo(other)
    }

    @Test
    fun equals_differentDenominator() {
        val fraction = Fraction.valueOf(2, 3)
        val other = Fraction.valueOf(2, 5)
        assertThat(fraction).isNotEqualTo(other)
    }

    @Test
    fun equals_negativeComplement() {
        val fraction = Fraction.valueOf(1, 2)
        val other = -Fraction.valueOf(1, 2)
        assertThat(fraction).isNotEqualTo(other)
    }

    @Test
    fun equals_nonReducedForm() {
        val fraction = Fraction.valueOf(2, 3)
        val other = Fraction.valueOf(4, 6)
        assertThat(fraction).isEqualTo(other)
    }

    @Test
    fun toString_whenZero() {
        val fraction = Fraction.ZERO
        assertThat(fraction.toString()).isEqualTo("0/1")
    }

    @Test
    fun toString_whenPositiveWhole() {
        val fraction = Fraction.valueOf(2)
        assertThat(fraction.toString()).isEqualTo("2/1")
    }

    @Test
    fun toString_whenPositiveProperFraction() {
        val fraction = Fraction.valueOf(2, 5)
        assertThat(fraction.toString()).isEqualTo("2/5")
    }

    @Test
    fun toString_whenPositiveImproperFraction() {
        val fraction = Fraction.valueOf(9, 4)
        assertThat(fraction.toString()).isEqualTo("9/4")
    }

    @Test
    fun toString_whenNegativeWhole() {
        val fraction = Fraction.valueOf(-3)
        assertThat(fraction.toString()).isEqualTo("-3/1")
    }

    @Test
    fun toString_whenNegativeProperFraction() {
        val fraction = Fraction.valueOf(-2, 5)
        assertThat(fraction.toString()).isEqualTo("-2/5")
    }

    @Test
    fun toString_whenNegativeImproperFraction() {
        val fraction = Fraction.valueOf(-9, 4)
        assertThat(fraction.toString()).isEqualTo("-9/4")
    }

    @Test
    fun hashCode_whenZero() {
        val fraction = Fraction.ZERO
        assertThat(fraction.hashCode())
            .isEqualTo(Fraction.ZERO.hashCode())
            .isEqualTo(Fraction.valueOf(0).hashCode())
            .isEqualTo(Fraction.valueOf(0, 2).hashCode())
            .isEqualTo(Fraction.valueOf(0, -3).hashCode())
    }

    @Test
    fun hashCode_whenNonZero() {
        val fraction = Fraction.valueOf(2, 3)
        assertThat(fraction.hashCode())
            .isEqualTo(Fraction.valueOf(2, 3).hashCode())
            .isEqualTo(Fraction.valueOf(4, 6).hashCode())
            .isEqualTo(Fraction.valueOf(-2, -3).hashCode())
            .isEqualTo(Fraction.valueOf(-12, -18).hashCode())
    }

    @Test
    fun valueOf_zeroDenominator() {
        assertThrows<IllegalArgumentException> { Fraction.valueOf(1L, 0L) }
    }

    @Test
    fun valueOf_zeroNumerator_noDenominator() {
        val fraction = Fraction.valueOf(0L)
        assertThat(fraction).isEqualTo(Fraction.ZERO)
        assertThat(fraction.numerator).isEqualTo(0L)
        assertThat(fraction.denominator).isEqualTo(1L)
    }

    @Test
    fun valueOf_zeroNumerator_positiveDenominator() {
        val fraction = Fraction.valueOf(0L, 4L)
        assertThat(fraction).isEqualTo(Fraction.ZERO)
        assertThat(fraction.numerator).isEqualTo(0L)
        assertThat(fraction.denominator).isEqualTo(1L)
    }

    @Test
    fun valueOf_zeroNumerator_negativeDenominator() {
        val fraction = Fraction.valueOf(0L, -4L)
        assertThat(fraction).isEqualTo(Fraction.ZERO)
        assertThat(fraction.numerator).isEqualTo(0L)
        assertThat(fraction.denominator).isEqualTo(1L)
    }

    @Test
    fun valueOf_positiveNumerator_noDenominator() {
        val fraction = Fraction.valueOf(6L)
        assertThat(fraction.numerator).isEqualTo(6L)
        assertThat(fraction.denominator).isEqualTo(1L)
    }

    @Test
    fun valueOf_positiveNumerator_positiveDenominator() {
        val fraction = Fraction.valueOf(6L, 4L)
        assertThat(fraction.numerator).isEqualTo(3L)
        assertThat(fraction.denominator).isEqualTo(2L)
    }

    @Test
    fun valueOf_positiveNumerator_negativeDenominator() {
        val fraction = Fraction.valueOf(6L, -4L)
        assertThat(fraction.numerator).isEqualTo(-3L)
        assertThat(fraction.denominator).isEqualTo(2L)
    }

    @Test
    fun valueOf_negativeNumerator_noDenominator() {
        val fraction = Fraction.valueOf(-6L)
        assertThat(fraction.numerator).isEqualTo(-6L)
        assertThat(fraction.denominator).isEqualTo(1L)
    }

    @Test
    fun valueOf_negativeNumerator_positiveDenominator() {
        val fraction = Fraction.valueOf(-6L, 4L)
        assertThat(fraction.numerator).isEqualTo(-3L)
        assertThat(fraction.denominator).isEqualTo(2L)
    }

    @Test
    fun valueOf_negativeNumerator_negativeDenominator() {
        val fraction = Fraction.valueOf(-6L, -4L)
        assertThat(fraction.numerator).isEqualTo(3L)
        assertThat(fraction.denominator).isEqualTo(2L)
    }
}
