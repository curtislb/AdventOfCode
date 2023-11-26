package com.curtislb.adventofcode.common.search

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests the [bisect] function.
 */
class BisectTest {
    @Test
    fun bisect_defaultKnownFalse_defaultKnownTrue_positivePredicate() {
        assertThat(bisect { it > 0 }).isEqualTo(1L)
    }

    @Test
    fun bisect_defaultKnownFalse_knownTrueOne_positivePredicate() {
        assertThat(bisect(knownTrue = 1L) { it > 0L }).isEqualTo(1L)
    }

    @Test
    fun bisect_defaultKnownFalse_knownTrueTwo_positivePredicate() {
        assertThat(bisect(knownTrue = 2L) { it > 0L }).isEqualTo(1L)
    }

    @Test
    fun bisect_defaultKnownFalse_knownTrueOneHundred_positivePredicate() {
        assertThat(bisect(knownTrue = 100L) { it > 0L }).isEqualTo(1L)
    }

    @Test
    fun bisect_knownFalseZero_knownTrueOne_positivePredicate() {
        assertThat(bisect(knownFalse = 0L, knownTrue = 1L) { it > 0L }).isEqualTo(1L)
    }

    @Test
    fun bisect_knownFalseZero_knownTrueTwo_positivePredicate() {
        assertThat(bisect(knownFalse = 0L, knownTrue = 2L) { it > 0L }).isEqualTo(1L)
    }

    @Test
    fun bisect_knownFalseZero_knownTrueOneHundred_positivePredicate() {
        assertThat(bisect(knownFalse = 0L, knownTrue = 100L) { it > 0L }).isEqualTo(1L)
    }

    @Test
    fun bisect_defaultKnownFalse_defaultKnownTrue_predicateAboveNumber() {
        assertThat(bisect { it > 5L }).isEqualTo(6L)
    }

    @Test
    fun bisect_knownFalsePositive_defaultKnownTrue_predicateAboveNumber() {
        assertThat(bisect(knownFalse = 2L) { it > 5L }).isEqualTo(6L)
    }

    @Test
    fun bisect_knownFalsePositive_knownTruePositive_predicateAboveNumber() {
        assertThat(bisect(knownFalse = 3L, knownTrue = 7L) { it > 5L }).isEqualTo(6L)
    }

    @Test
    fun bisect_defaultKnownFalse_defaultKnownTrue_predicateFalse() {
        assertThat(bisect { false }).isEqualTo(-1L)
    }

    @Test
    fun bisect_knownFalseMaxValue_defaultKnownTrue_predicateFalse() {
        assertThat(bisect(knownFalse = Long.MAX_VALUE) { false }).isEqualTo(-1L)
    }

    @Test
    fun bisect_defaultKnownFalse_knownTrueMaxValue_predicateMaxValue() {
        assertThat(bisect(knownTrue = Long.MAX_VALUE) { it == Long.MAX_VALUE })
            .isEqualTo(Long.MAX_VALUE)
    }

    @Test
    fun bisect_knownFalseNegative() {
        assertThrows<IllegalArgumentException> { bisect(knownFalse = -1L) { it > 0L } }
    }

    @Test
    fun bisect_knownFalseEqualsKnownTrue() {
        assertThrows<IllegalArgumentException> {
            bisect(knownFalse = 0L, knownTrue = 0L) { it > 0L }
        }
    }

    @Test
    fun bisect_knownFalseGreaterThanKnownTrue() {
        assertThrows<IllegalArgumentException> {
            bisect(knownFalse = 1L, knownTrue = 0L) { it > 0L }
        }
    }
}
