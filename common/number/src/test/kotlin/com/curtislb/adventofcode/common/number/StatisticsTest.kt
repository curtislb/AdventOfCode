package com.curtislb.adventofcode.common.number

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests functions related to numerical statistics.
 */
class StatisticsTest {
    @Test
    fun medianOrNull_ofEmptyList() {
        assertThat(emptyList<Int>().medianOrNull()).isNull()
    }

    @Test
    fun medianOrNull_ofSingletonList() {
        assertThat(listOf(42).medianOrNull()).isEqualTo(42)
    }

    @Test
    fun medianOrNull_ofTwoElementList_inOrder() {
        assertThat(listOf(2, 3).medianOrNull()).isEqualTo(3)
    }

    @Test
    fun medianOrNull_ofTwoElementList_reverseOrder() {
        assertThat(listOf(3, 2).medianOrNull()).isEqualTo(3)
    }

    @Test
    fun medianOrNull_ofSortedList_withEvenLength_noDuplicates() {
        assertThat(listOf(-93, -90, -79, -28, -24, 6, 31, 43, 50, 52).medianOrNull()).isEqualTo(6)
    }

    @Test
    fun medianOrNull_ofSortedList_withEvenLength_withDuplicates() {
        assertThat(listOf(-93, -90, -90, -90, -28, -24, 6, 6, 31, 43).medianOrNull()).isEqualTo(-24)
    }

    @Test
    fun medianOrNull_ofSortedList_withOddLength_noDuplicates() {
        assertThat(listOf(-90, -79, -28, -24, 6, 31, 43, 50, 52).medianOrNull()).isEqualTo(6)
    }

    @Test
    fun medianOrNull_ofSortedList_withOddLength_withDuplicates() {
        assertThat(listOf(-90, -90, -90, -28, -24, 6, 6, 31, 43).medianOrNull()).isEqualTo(-24)
    }

    @Test
    fun medianOrNull_ofUnsortedList_withEvenLength_noDuplicates() {
        assertThat(listOf(-28, 52, 43, 31, -93, -24, 6, -90, 50, -79).medianOrNull()).isEqualTo(6)
    }

    @Test
    fun medianOrNull_ofUnsortedList_withEvenLength_withDuplicates() {
        assertThat(listOf(-90, 6, -90, 43, -28, 31, 6, -90, -24, -93).medianOrNull()).isEqualTo(-24)
    }

    @Test
    fun medianOrNull_ofUnsortedList_withOddLength_noDuplicates() {
        assertThat(listOf(-24, 43, 31, -90, -79, -28, 50, 52, 6).medianOrNull()).isEqualTo(6)
    }

    @Test
    fun medianOrNull_ofUnsortedList_withOddLength_withDuplicates() {
        assertThat(listOf(6, 31, -90, -24, -90, 43, 6, -90, -28).medianOrNull()).isEqualTo(-24)
    }
}
