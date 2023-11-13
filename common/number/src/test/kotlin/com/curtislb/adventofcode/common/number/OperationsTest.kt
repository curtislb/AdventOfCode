package com.curtislb.adventofcode.common.number

import java.math.BigInteger
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests functions related to mathematical operations.
 */
class OperationsTest {
    @Test
    fun modMultiply_withLongs_zeroBase_zeroMultiplicand() {
        assertThat(0L.modMultiply(0L, modulus = 95L)).isEqualTo(0L)
    }

    @Test
    fun modMultiply_withLongs_zeroBase_positiveMultiplicand() {
        assertThat(0L.modMultiply(706L, modulus = 387L)).isEqualTo(0L)
    }

    @Test
    fun modMultiply_withLongs_zeroBase_negativeMultiplicand() {
        assertThat(0L.modMultiply(-182L, modulus = 273L)).isEqualTo(0L)
    }

    @Test
    fun modMultiply_withLongs_positiveBase_zeroMultiplicand() {
        assertThat(873L.modMultiply(0L, modulus = 933L)).isEqualTo(0L)
    }

    @Test
    fun modMultiply_withLongs_positiveBase_positiveMultiplicand() {
        assertThat(340L.modMultiply(887L, modulus = 97L)).isEqualTo(7L)
    }

    @Test
    fun modMultiply_withLongs_positiveBase_negativeMultiplicand() {
        assertThat(445L.modMultiply(-372L, modulus = 16L)).isEqualTo(12L)
    }

    @Test
    fun modMultiply_withLongs_negativeBase_zeroMultiplicand() {
        assertThat((-922L).modMultiply(0L, modulus = 233L)).isEqualTo(0L)
    }

    @Test
    fun modMultiply_withLongs_negativeBase_positiveMultiplicand() {
        assertThat((-414L).modMultiply(741L, modulus = 656L)).isEqualTo(234L)
    }

    @Test
    fun modMultiply_withLongs_negativeBase_negativeMultiplicand() {
        assertThat((-662L).modMultiply(-241L, modulus = 401L)).isEqualTo(345L)
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
        assertThat(
            BigInteger.ZERO.modMultiply(BigInteger.ZERO, modulus = BigInteger.valueOf(95))
        ).isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun modMultiply_withBigIntegers_zeroBase_positiveMultiplicand() {
        assertThat(
            BigInteger.ZERO.modMultiply(BigInteger.valueOf(706), modulus = BigInteger.valueOf(387))
        ).isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun modMultiply_withBigIntegers_zeroBase_negativeMultiplicand() {
        assertThat(
            BigInteger.ZERO.modMultiply(BigInteger.valueOf(-182), modulus = BigInteger.valueOf(273))
        ).isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun modMultiply_withBigIntegers_positiveBase_zeroMultiplicand() {
        assertThat(
            BigInteger.valueOf(873).modMultiply(BigInteger.ZERO, modulus = BigInteger.valueOf(933))
        ).isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun modMultiply_withBigIntegers_positiveBase_positiveMultiplicand() {
        assertThat(
            BigInteger.valueOf(340).modMultiply(
                BigInteger.valueOf(887),
                modulus = BigInteger.valueOf(97)
            )
        ).isEqualTo(BigInteger.valueOf(7))
    }

    @Test
    fun modMultiply_withBigIntegers_positiveBase_negativeMultiplicand() {
        assertThat(
            BigInteger.valueOf(445).modMultiply(
                BigInteger.valueOf(-372),
                modulus = BigInteger.valueOf(16)
            )
        ).isEqualTo(BigInteger.valueOf(12))
    }

    @Test
    fun modMultiply_withBigIntegers_negativeBase_zeroMultiplicand() {
        assertThat(
            BigInteger.valueOf(-922).modMultiply(BigInteger.ZERO, modulus = BigInteger.valueOf(233))
        ).isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun modMultiply_withBigIntegers_negativeBase_positiveMultiplicand() {
        assertThat(
            BigInteger.valueOf(-414).modMultiply(
                BigInteger.valueOf(741),
                modulus = BigInteger.valueOf(656)
            )
        ).isEqualTo(BigInteger.valueOf(234))
    }

    @Test
    fun modMultiply_withBigIntegers_negativeBase_negativeMultiplicand() {
        assertThat(
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

    @Test
    fun pow_ofInt_zeroBase_zeroExponent() {
        assertThat(0.pow(0)).isEqualTo(1)
    }

    @Test
    fun pow_ofInt_zeroBase_oneExponent() {
        assertThat(0.pow(1)).isEqualTo(0)
    }

    @Test
    fun pow_ofInt_zeroBase_positiveExponent() {
        assertThat(0.pow(44)).isEqualTo(0)
    }

    @Test
    fun pow_ofInt_oneBase_zeroExponent() {
        assertThat(1.pow(0)).isEqualTo(1)
    }

    @Test
    fun pow_ofInt_oneBase_oneExponent() {
        assertThat(1.pow(1)).isEqualTo(1)
    }

    @Test
    fun pow_ofInt_oneBase_positiveExponent() {
        assertThat(1.pow(53)).isEqualTo(1)
    }

    @Test
    fun pow_ofInt_positiveBase_zeroExponent() {
        assertThat(536.pow(0)).isEqualTo(1)
    }

    @Test
    fun pow_ofInt_positiveBase_oneExponent() {
        assertThat(909.pow(1)).isEqualTo(909)
    }

    @Test
    fun pow_ofInt_positiveBase_positiveExponent() {
        assertThat(340.pow(3)).isEqualTo(39304000)
    }

    @Test
    fun pow_ofInt_negativeOneBase_zeroExponent() {
        assertThat((-1).pow(0)).isEqualTo(1)
    }

    @Test
    fun pow_ofInt_negativeOneBase_oneExponent() {
        assertThat((-1).pow(1)).isEqualTo(-1)
    }

    @Test
    fun pow_ofInt_negativeOneBase_oddExponent() {
        assertThat((-1).pow(3)).isEqualTo(-1)
    }

    @Test
    fun pow_ofInt_negativeOneBase_evenExponent() {
        assertThat((-1).pow(4)).isEqualTo(1)
    }

    @Test
    fun pow_ofInt_negativeBase_zeroExponent() {
        assertThat((-472).pow(0)).isEqualTo(1)
    }

    @Test
    fun pow_ofInt_negativeBase_oneExponent() {
        assertThat((-776).pow(1)).isEqualTo(-776)
    }

    @Test
    fun pow_ofInt_negativeBase_oddExponent() {
        assertThat((-176).pow(3)).isEqualTo(-5451776)
    }

    @Test
    fun pow_ofInt_negativeBase_evenExponent() {
        assertThat((-102).pow(4)).isEqualTo(108243216)
    }

    @Test
    fun pow_ofInt_negativeExponent() {
        assertThrows<IllegalArgumentException> { 1.pow(-1) }
    }

    @Test
    fun pow_ofLong_zeroBase_zeroExponent() {
        assertThat(0L.pow(0)).isEqualTo(1L)
    }

    @Test
    fun pow_ofLong_zeroBase_oneExponent() {
        assertThat(0L.pow(1)).isEqualTo(0L)
    }

    @Test
    fun pow_ofLong_zeroBase_positiveExponent() {
        assertThat(0L.pow(91)).isEqualTo(0L)
    }

    @Test
    fun pow_ofLong_oneBase_zeroExponent() {
        assertThat(1L.pow(0)).isEqualTo(1L)
    }

    @Test
    fun pow_ofLong_oneBase_oneExponent() {
        assertThat(1L.pow(1)).isEqualTo(1L)
    }

    @Test
    fun pow_ofLong_oneBase_positiveExponent() {
        assertThat(1L.pow(65)).isEqualTo(1L)
    }

    @Test
    fun pow_ofLong_positiveBase_zeroExponent() {
        assertThat(387L.pow(0)).isEqualTo(1L)
    }

    @Test
    fun pow_ofLong_positiveBase_oneExponent() {
        assertThat(281L.pow(1)).isEqualTo(281L)
    }

    @Test
    fun pow_ofLong_positiveBase_positiveExponent() {
        assertThat(31L.pow(10)).isEqualTo(819628286980801L)
    }

    @Test
    fun pow_ofLong_negativeOneBase_zeroExponent() {
        assertThat((-1L).pow(0)).isEqualTo(1L)
    }

    @Test
    fun pow_ofLong_negativeOneBase_oneExponent() {
        assertThat((-1L).pow(1)).isEqualTo(-1L)
    }

    @Test
    fun pow_ofLong_negativeOneBase_oddExponent() {
        assertThat((-1L).pow(3)).isEqualTo(-1L)
    }

    @Test
    fun pow_ofLong_negativeOneBase_evenExponent() {
        assertThat((-1L).pow(4)).isEqualTo(1L)
    }

    @Test
    fun pow_ofLong_negativeBase_zeroExponent() {
        assertThat((-850L).pow(0)).isEqualTo(1L)
    }

    @Test
    fun pow_ofLong_negativeBase_oneExponent() {
        assertThat((-726L).pow(1)).isEqualTo(-726L)
    }

    @Test
    fun pow_ofLong_negativeBase_oddExponent() {
        assertThat((-249L).pow(5)).isEqualTo(-957186876249L)
    }

    @Test
    fun pow_ofLong_negativeBase_evenExponent() {
        assertThat((-234L).pow(6)).isEqualTo(164170508913216L)
    }

    @Test
    fun pow_ofLong_negativeExponent() {
        assertThrows<IllegalArgumentException> { 1L.pow(-1) }
    }

    @Test
    fun product_ofInts_emptyList() {
        assertThat(emptyList<Int>().product()).isEqualTo(1)
    }

    @Test
    fun product_ofInts_zeroElement() {
        assertThat(listOf(0).product()).isEqualTo(0)
    }

    @Test
    fun product_ofInts_onePositiveElement() {
        assertThat(listOf(54).product()).isEqualTo(54)
    }

    @Test
    fun product_ofInts_oneNegativeElement() {
        assertThat(listOf(-27).product()).isEqualTo(-27)
    }

    @Test
    fun product_ofInts_positiveElements() {
        assertThat(listOf(2, 3, 5).product()).isEqualTo(30)
    }

    @Test
    fun product_ofInts_negativeElements() {
        assertThat(listOf(-2, -3, -5).product()).isEqualTo(-30)
    }

    @Test
    fun product_ofInts_positiveAndNegativeElements() {
        assertThat(listOf(12, -3, 3, -5, -10, 8, 12, -15, 4, 1).product()).isEqualTo(31104000)
    }

    @Test
    fun product_ofLongs_emptyList() {
        assertThat(emptyList<Long>().product()).isEqualTo(1L)
    }

    @Test
    fun product_ofLongs_zeroElement() {
        assertThat(listOf(0L).product()).isEqualTo(0L)
    }

    @Test
    fun product_ofLongs_onePositiveElement() {
        assertThat(listOf(54L).product()).isEqualTo(54L)
    }

    @Test
    fun product_ofLongs_oneNegativeElement() {
        assertThat(listOf(-27L).product()).isEqualTo(-27L)
    }

    @Test
    fun product_ofLongs_positiveElements() {
        assertThat(listOf(2L, 3L, 5L).product()).isEqualTo(30L)
    }

    @Test
    fun product_ofLongs_negativeElements() {
        assertThat(listOf(-2L, -3L, -5L).product()).isEqualTo(-30L)
    }

    @Test
    fun product_ofLongs_positiveAndNegativeElements() {
        assertThat(listOf(-49L, -31L, 3L, -2L, -32L, 16L, 32L, -35L, -45L, -9L).product())
            .isEqualTo(-2116664524800L)
    }

    @Test
    fun product_ofBigIntegers_emptyList() {
        assertThat(emptyList<BigInteger>().product()).isEqualTo(BigInteger.ONE)
    }

    @Test
    fun product_ofBigIntegers_zeroElement() {
        assertThat(listOf(BigInteger.ZERO).product()).isEqualTo(BigInteger.ZERO)
    }

    @Test
    fun product_ofBigIntegers_onePositiveElement() {
        assertThat(listOf(BigInteger.valueOf(54)).product()).isEqualTo(BigInteger.valueOf(54))
    }

    @Test
    fun product_ofBigIntegers_oneNegativeElement() {
        assertThat(listOf(BigInteger.valueOf(-27)).product()).isEqualTo(BigInteger.valueOf(-27))
    }

    @Test
    fun product_ofBigIntegers_positiveElements() {
        assertThat(
            listOf(BigInteger.valueOf(2), BigInteger.valueOf(3), BigInteger.valueOf(5)).product()
        ).isEqualTo(BigInteger.valueOf(30))
    }

    @Test
    fun product_ofBigIntegers_negativeElements() {
        assertThat(
            listOf(BigInteger.valueOf(-2), BigInteger.valueOf(-3), BigInteger.valueOf(-5)).product()
        ).isEqualTo(BigInteger.valueOf(-30))
    }

    @Test
    fun product_ofBigIntegers_positiveAndNegativeElements() {
        assertThat(
            listOf(
                BigInteger.valueOf(42),
                BigInteger.valueOf(-34),
                BigInteger.valueOf(-111),
                BigInteger.valueOf(191),
                BigInteger.valueOf(-156),
                BigInteger.valueOf(-48),
                BigInteger.valueOf(-77),
                BigInteger.valueOf(143),
                BigInteger.valueOf(148),
                BigInteger.valueOf(-146)
            ).product()
        ).isEqualTo(BigInteger("53937613013501048832"))
    }
}
