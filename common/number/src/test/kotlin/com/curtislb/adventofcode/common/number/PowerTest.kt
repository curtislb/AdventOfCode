package com.curtislb.adventofcode.common.number

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests function related to exponentiation.
 */
class PowerTest {
    @Test
    fun pow_ofInt_zeroBase_zeroExponent() {
        Assertions.assertThat(0.pow(0)).isEqualTo(1)
    }

    @Test
    fun pow_ofInt_zeroBase_oneExponent() {
        Assertions.assertThat(0.pow(1)).isEqualTo(0)
    }

    @Test
    fun pow_ofInt_zeroBase_positiveExponent() {
        Assertions.assertThat(0.pow(44)).isEqualTo(0)
    }

    @Test
    fun pow_ofInt_oneBase_zeroExponent() {
        Assertions.assertThat(1.pow(0)).isEqualTo(1)
    }

    @Test
    fun pow_ofInt_oneBase_oneExponent() {
        Assertions.assertThat(1.pow(1)).isEqualTo(1)
    }

    @Test
    fun pow_ofInt_oneBase_positiveExponent() {
        Assertions.assertThat(1.pow(53)).isEqualTo(1)
    }

    @Test
    fun pow_ofInt_positiveBase_zeroExponent() {
        Assertions.assertThat(536.pow(0)).isEqualTo(1)
    }

    @Test
    fun pow_ofInt_positiveBase_oneExponent() {
        Assertions.assertThat(909.pow(1)).isEqualTo(909)
    }

    @Test
    fun pow_ofInt_positiveBase_positiveExponent() {
        Assertions.assertThat(340.pow(3)).isEqualTo(39304000)
    }

    @Test
    fun pow_ofInt_negativeOneBase_zeroExponent() {
        Assertions.assertThat((-1).pow(0)).isEqualTo(1)
    }

    @Test
    fun pow_ofInt_negativeOneBase_oneExponent() {
        Assertions.assertThat((-1).pow(1)).isEqualTo(-1)
    }

    @Test
    fun pow_ofInt_negativeOneBase_oddExponent() {
        Assertions.assertThat((-1).pow(3)).isEqualTo(-1)
    }

    @Test
    fun pow_ofInt_negativeOneBase_evenExponent() {
        Assertions.assertThat((-1).pow(4)).isEqualTo(1)
    }

    @Test
    fun pow_ofInt_negativeBase_zeroExponent() {
        Assertions.assertThat((-472).pow(0)).isEqualTo(1)
    }

    @Test
    fun pow_ofInt_negativeBase_oneExponent() {
        Assertions.assertThat((-776).pow(1)).isEqualTo(-776)
    }

    @Test
    fun pow_ofInt_negativeBase_oddExponent() {
        Assertions.assertThat((-176).pow(3)).isEqualTo(-5451776)
    }

    @Test
    fun pow_ofInt_negativeBase_evenExponent() {
        Assertions.assertThat((-102).pow(4)).isEqualTo(108243216)
    }

    @Test
    fun pow_ofInt_negativeExponent() {
        assertThrows<IllegalArgumentException> { 1.pow(-1) }
    }

    @Test
    fun pow_ofLong_zeroBase_zeroExponent() {
        Assertions.assertThat(0L.pow(0)).isEqualTo(1L)
    }

    @Test
    fun pow_ofLong_zeroBase_oneExponent() {
        Assertions.assertThat(0L.pow(1)).isEqualTo(0L)
    }

    @Test
    fun pow_ofLong_zeroBase_positiveExponent() {
        Assertions.assertThat(0L.pow(91)).isEqualTo(0L)
    }

    @Test
    fun pow_ofLong_oneBase_zeroExponent() {
        Assertions.assertThat(1L.pow(0)).isEqualTo(1L)
    }

    @Test
    fun pow_ofLong_oneBase_oneExponent() {
        Assertions.assertThat(1L.pow(1)).isEqualTo(1L)
    }

    @Test
    fun pow_ofLong_oneBase_positiveExponent() {
        Assertions.assertThat(1L.pow(65)).isEqualTo(1L)
    }

    @Test
    fun pow_ofLong_positiveBase_zeroExponent() {
        Assertions.assertThat(387L.pow(0)).isEqualTo(1L)
    }

    @Test
    fun pow_ofLong_positiveBase_oneExponent() {
        Assertions.assertThat(281L.pow(1)).isEqualTo(281L)
    }

    @Test
    fun pow_ofLong_positiveBase_positiveExponent() {
        Assertions.assertThat(31L.pow(10)).isEqualTo(819628286980801L)
    }

    @Test
    fun pow_ofLong_negativeOneBase_zeroExponent() {
        Assertions.assertThat((-1L).pow(0)).isEqualTo(1L)
    }

    @Test
    fun pow_ofLong_negativeOneBase_oneExponent() {
        Assertions.assertThat((-1L).pow(1)).isEqualTo(-1L)
    }

    @Test
    fun pow_ofLong_negativeOneBase_oddExponent() {
        Assertions.assertThat((-1L).pow(3)).isEqualTo(-1L)
    }

    @Test
    fun pow_ofLong_negativeOneBase_evenExponent() {
        Assertions.assertThat((-1L).pow(4)).isEqualTo(1L)
    }

    @Test
    fun pow_ofLong_negativeBase_zeroExponent() {
        Assertions.assertThat((-850L).pow(0)).isEqualTo(1L)
    }

    @Test
    fun pow_ofLong_negativeBase_oneExponent() {
        Assertions.assertThat((-726L).pow(1)).isEqualTo(-726L)
    }

    @Test
    fun pow_ofLong_negativeBase_oddExponent() {
        Assertions.assertThat((-249L).pow(5)).isEqualTo(-957186876249L)
    }

    @Test
    fun pow_ofLong_negativeBase_evenExponent() {
        Assertions.assertThat((-234L).pow(6)).isEqualTo(164170508913216L)
    }

    @Test
    fun pow_ofLong_negativeExponent() {
        assertThrows<IllegalArgumentException> { 1L.pow(-1) }
    }
}
