package com.curtislb.adventofcode.common.number

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests functions related to multiplying numbers.
 */
class ProductTest {
    @Test
    fun product_withInts_emptyList() {
        assertThat(emptyList<Int>().product()).isEqualTo(1)
    }

    @Test
    fun product_withInts_zeroElement() {
        assertThat(listOf(0).product()).isEqualTo(0)
    }

    @Test
    fun product_withInts_onePositiveElement() {
        assertThat(listOf(54).product()).isEqualTo(54)
    }

    @Test
    fun product_withInts_oneNegativeElement() {
        assertThat(listOf(-27).product()).isEqualTo(-27)
    }

    @Test
    fun product_withInts_positiveElements() {
        assertThat(listOf(2, 3, 5).product()).isEqualTo(30)
    }

    @Test
    fun product_withInts_negativeElements() {
        assertThat(listOf(-2, -3, -5).product()).isEqualTo(-30)
    }

    @Test
    fun product_withInts_positiveAndNegativeElements() {
        assertThat(listOf(12, -3, 3, -5, -10, 8, 12, -15, 4, 1).product()).isEqualTo(31104000)
    }

    @Test
    fun product_withLongs_emptyList() {
        assertThat(emptyList<Long>().product()).isEqualTo(1L)
    }

    @Test
    fun product_withLongs_zeroElement() {
        assertThat(listOf(0L).product()).isEqualTo(0L)
    }

    @Test
    fun product_withLongs_onePositiveElement() {
        assertThat(listOf(54L).product()).isEqualTo(54L)
    }

    @Test
    fun product_withLongs_oneNegativeElement() {
        assertThat(listOf(-27L).product()).isEqualTo(-27L)
    }

    @Test
    fun product_withLongs_positiveElements() {
        assertThat(listOf(2L, 3L, 5L).product()).isEqualTo(30L)
    }

    @Test
    fun product_withLongs_negativeElements() {
        assertThat(listOf(-2L, -3L, -5L).product()).isEqualTo(-30L)
    }

    @Test
    fun product_withLongs_positiveAndNegativeElements() {
        assertThat(listOf(-49L, -31L, 3L, -2L, -32L, 16L, 32L, -35L, -45L, -9L).product())
            .isEqualTo(-2116664524800L)
    }
}
