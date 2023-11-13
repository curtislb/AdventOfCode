package com.curtislb.adventofcode.common.number

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests functions related to prime numbers.
 */
class PrimesTest {
    @Test
    fun primeFactorization_ofNegativeNumber() {
        assertThrows<IllegalArgumentException> { primeFactorization(-1L) }
    }

    @Test
    fun primeFactorization_ofZero() {
        assertThrows<IllegalArgumentException> { primeFactorization(0L) }
    }

    @Test
    fun primeFactorization_ofOne() {
        assertThat(primeFactorization(1L)).isEmpty()
    }

    @Test
    fun primeFactorization_ofTwo() {
        assertThat(primeFactorization(2L)).containsExactlyEntriesOf(mapOf(2L to 1))
    }

    @Test
    fun primeFactorization_ofThree() {
        assertThat(primeFactorization(3L)).containsExactlyEntriesOf(mapOf(3L to 1))
    }

    @Test
    fun primeFactorization_ofFour() {
        assertThat(primeFactorization(4L)).containsExactlyEntriesOf(mapOf(2L to 2))
    }

    @Test
    fun primeFactorization_ofFive() {
        assertThat(primeFactorization(5L)).containsExactlyEntriesOf(mapOf(5L to 1))
    }

    @Test
    fun primeFactorization_ofSix() {
        assertThat(primeFactorization(6L))
            .containsExactlyInAnyOrderEntriesOf(mapOf(2L to 1, 3L to 1))
    }

    @Test
    fun primeFactorization_ofLargeCompositeNumber() {
        assertThat(primeFactorization(74435892358158L)).containsExactlyInAnyOrderEntriesOf(
            mapOf(2L to 1, 3L to 3, 29L to 1, 3049L to 2, 5113L to 1)
        )
    }

    @Test
    fun primeFactorization_ofLargePrimeNumber() {
        assertThat(primeFactorization(74435892358141L))
            .containsExactlyEntriesOf(mapOf(74435892358141L to 1))
    }

    @Test
    fun primeFactorization_ofMaxCompositeNumber() {
        assertThat(primeFactorization(Long.MAX_VALUE)).containsExactlyInAnyOrderEntriesOf(
            mapOf(7L to 2, 73L to 1, 127L to 1, 337L to 1, 92737L to 1, 649657L to 1)
        )
    }

    @Test
    fun primeFactorization_ofMaxPrimeNumber() {
        assertThat(primeFactorization(9223372036854775783L))
            .containsExactlyEntriesOf(mapOf(9223372036854775783L to 1))
    }
}
