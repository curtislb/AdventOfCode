package com.curtislb.adventofcode.common.number

import java.math.BigInteger
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests functions related to modular arithmetic.
 */
class ModulusTest {
    @Test
    fun modMultiply_withLongs_zeroBase_zeroMultiplicand() {
        Assertions.assertThat(0L.modMultiply(0L, modulus = 95L)).isEqualTo(0L)
    }

    @Test
    fun modMultiply_withLongs_zeroBase_positiveMultiplicand() {
        Assertions.assertThat(0L.modMultiply(706L, modulus = 387L)).isEqualTo(0L)
    }

    @Test
    fun modMultiply_withLongs_zeroBase_negativeMultiplicand() {
        Assertions.assertThat(0L.modMultiply(-182L, modulus = 273L)).isEqualTo(0L)
    }

    @Test
    fun modMultiply_withLongs_positiveBase_zeroMultiplicand() {
        Assertions.assertThat(873L.modMultiply(0L, modulus = 933L)).isEqualTo(0L)
    }

    @Test
    fun modMultiply_withLongs_positiveBase_positiveMultiplicand() {
        Assertions.assertThat(340L.modMultiply(887L, modulus = 97L)).isEqualTo(7L)
    }

    @Test
    fun modMultiply_withLongs_positiveBase_negativeMultiplicand() {
        Assertions.assertThat(445L.modMultiply(-372L, modulus = 16L)).isEqualTo(12L)
    }

    @Test
    fun modMultiply_withLongs_negativeBase_zeroMultiplicand() {
        Assertions.assertThat((-922L).modMultiply(0L, modulus = 233L)).isEqualTo(0L)
    }

    @Test
    fun modMultiply_withLongs_negativeBase_positiveMultiplicand() {
        Assertions.assertThat((-414L).modMultiply(741L, modulus = 656L)).isEqualTo(234L)
    }

    @Test
    fun modMultiply_withLongs_negativeBase_negativeMultiplicand() {
        Assertions.assertThat((-662L).modMultiply(-241L, modulus = 401L)).isEqualTo(345L)
    }

    @Test
    fun modMultiply_withLongs_zeroModulus() {
        assertThrows<IllegalArgumentException> { 1L.modMultiply(1L, modulus = 0L) }
    }

    @Test
    fun modMultiply_withLongs_negativeModulus() {
        assertThrows<IllegalArgumentException> { 1L.modMultiply(1L, modulus = -1L) }
    }

    @Test
    fun modMultiply_withBigIntegers_zeroBase_zeroMultiplicand() {
        Assertions.assertThat(
            BigInteger.ZERO.modMultiply(BigInteger.ZERO, modulus = BigInteger.valueOf(95))
        ).isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun modMultiply_withBigIntegers_zeroBase_positiveMultiplicand() {
        Assertions.assertThat(
            BigInteger.ZERO.modMultiply(BigInteger.valueOf(706), modulus = BigInteger.valueOf(387))
        ).isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun modMultiply_withBigIntegers_zeroBase_negativeMultiplicand() {
        Assertions.assertThat(
            BigInteger.ZERO.modMultiply(BigInteger.valueOf(-182), modulus = BigInteger.valueOf(273))
        ).isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun modMultiply_withBigIntegers_positiveBase_zeroMultiplicand() {
        Assertions.assertThat(
            BigInteger.valueOf(873).modMultiply(BigInteger.ZERO, modulus = BigInteger.valueOf(933))
        ).isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun modMultiply_withBigIntegers_positiveBase_positiveMultiplicand() {
        Assertions.assertThat(
            BigInteger.valueOf(340).modMultiply(
                BigInteger.valueOf(887),
                modulus = BigInteger.valueOf(97)
            )
        ).isEqualTo(BigInteger.valueOf(7))
    }

    @Test
    fun modMultiply_withBigIntegers_positiveBase_negativeMultiplicand() {
        Assertions.assertThat(
            BigInteger.valueOf(445).modMultiply(
                BigInteger.valueOf(-372),
                modulus = BigInteger.valueOf(16)
            )
        ).isEqualTo(BigInteger.valueOf(12))
    }

    @Test
    fun modMultiply_withBigIntegers_negativeBase_zeroMultiplicand() {
        Assertions.assertThat(
            BigInteger.valueOf(-922).modMultiply(BigInteger.ZERO, modulus = BigInteger.valueOf(233))
        ).isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun modMultiply_withBigIntegers_negativeBase_positiveMultiplicand() {
        Assertions.assertThat(
            BigInteger.valueOf(-414).modMultiply(
                BigInteger.valueOf(741),
                modulus = BigInteger.valueOf(656)
            )
        ).isEqualTo(BigInteger.valueOf(234))
    }

    @Test
    fun modMultiply_withBigIntegers_negativeBase_negativeMultiplicand() {
        Assertions.assertThat(
            BigInteger.valueOf(-662).modMultiply(
                BigInteger.valueOf(-241),
                modulus = BigInteger.valueOf(401)
            )
        ).isEqualTo(BigInteger.valueOf(345))
    }

    @Test
    fun modMultiply_withBigIntegers_zeroModulus() {
        assertThrows<IllegalArgumentException> {
            BigInteger.ONE.modMultiply(BigInteger.ONE, modulus = BigInteger.ZERO)
        }
    }

    @Test
    fun modMultiply_withBigIntegers_negativeModulus() {
        assertThrows<IllegalArgumentException> {
            BigInteger.ONE.modMultiply(BigInteger.ONE, modulus = BigInteger.valueOf(-1))
        }
    }
}
