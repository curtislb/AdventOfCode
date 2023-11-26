package com.curtislb.adventofcode.common.search

import com.curtislb.adventofcode.common.testing.containsExactlyInAnyOrderElementsOfAny
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests functions related to finding numbers that sum to a target value.
 */
class FindSumTest {
    @Test
    fun findNonNegativeSubarraySum_negativeTarget() {
        assertThrows<IllegalArgumentException> {
            listOf(1L, 2L, 3L).findNonNegativeSubarraySum(-1L)
        }
    }

    @Test
    fun findNonNegativeSubarraySum_negativeMinSize() {
        assertThrows<IllegalArgumentException> {
            listOf(1L, 2L, 3L).findNonNegativeSubarraySum(0L, minSize = -1)
        }
    }

    @Test
    fun findNonNegativeSubarraySum_withNegativeValue() {
        assertThrows<IllegalArgumentException> {
            listOf(-1L, 2L, 3L).findNonNegativeSubarraySum(1L)
        }
    }

    @Test
    fun findNonNegativeSubarraySum_whenEmpty_targetZero_defaultMinSize() {
        val numbers = emptyList<Long>()
        assertThat(numbers.findNonNegativeSubarraySum(0L)).isEmpty()
    }

    @Test
    fun findNonNegativeSubarraySum_whenEmpty_targetZero_positiveMinSize() {
        val numbers = emptyList<Long>()
        assertThat(numbers.findNonNegativeSubarraySum(0L, minSize = 1)).isNull()
    }

    @Test
    fun findNonNegativeSubarraySum_whenEmpty_positiveTarget_defaultMinSize() {
        val numbers = emptyList<Long>()
        assertThat(numbers.findNonNegativeSubarraySum(1L)).isNull()
    }

    @Test
    fun findNonNegativeSubarraySum_whenEmpty_positiveTarget_positiveMinSize() {
        val numbers = emptyList<Long>()
        assertThat(numbers.findNonNegativeSubarraySum(1L, minSize = 1)).isNull()
    }

    @Test
    fun findNonNegativeSubarraySum_withSingleValue_targetZero_defaultMinSize() {
        val numbers = listOf(42L)
        assertThat(numbers.findNonNegativeSubarraySum(0L)).isEmpty()
    }

    @Test
    fun findNonNegativeSubarraySum_withSingleValue_targetZero_positiveMinSize() {
        val numbers = listOf(42L)
        assertThat(numbers.findNonNegativeSubarraySum(0L, minSize = 1)).isNull()
    }

    @Test
    fun findNonNegativeSubarraySum_withSingleValue_possibleTarget_defaultMinSize() {
        val numbers = listOf(42L)
        assertThat(numbers.findNonNegativeSubarraySum(42L)).containsExactly(42L)
    }

    @Test
    fun findNonNegativeSubarraySum_withSingleValue_possibleTarget_minSizeOne() {
        val numbers = listOf(42L)
        assertThat(numbers.findNonNegativeSubarraySum(42L, minSize = 1)).containsExactly(42L)
    }

    @Test
    fun findNonNegativeSubarraySum_withSingleValue_possibleTarget_minSizeTwo() {
        val numbers = listOf(42L)
        assertThat(numbers.findNonNegativeSubarraySum(42L, minSize = 2)).isNull()
    }

    @Test
    fun findNonNegativeSubarraySum_withSingleValue_impossibleTarget_defaultMinSize() {
        val numbers = listOf(42L)
        assertThat(numbers.findNonNegativeSubarraySum(23L)).isNull()
    }

    @Test
    fun findNonNegativeSubarraySum_withSingleValue_impossibleTarget_positiveMinSize() {
        val numbers = listOf(42L)
        assertThat(numbers.findNonNegativeSubarraySum(23L, minSize = 1)).isNull()
    }

    @Test
    fun findNonNegativeSubarraySum_withMultipleValues_targetFirstElement_defaultMinSize() {
        val numbers = listOf(23L, 6L, 2L, 4L, 7L)
        assertThat(numbers.findNonNegativeSubarraySum(23L)).containsExactly(23L)
    }

    @Test
    fun findNonNegativeSubarraySum_withMultipleValues_targetLastElement_defaultMinSize() {
        val numbers = listOf(23L, 6L, 2L, 4L, 7L)
        assertThat(numbers.findNonNegativeSubarraySum(7L)).containsExactly(7L)
    }

    @Test
    fun findNonNegativeSubarraySum_withMultipleValues_targetMiddleElement_defaultMinSize() {
        val numbers = listOf(23L, 6L, 2L, 4L, 7L)
        assertThat(numbers.findNonNegativeSubarraySum(2L)).containsExactly(2L)
    }

    @Test
    fun findNonNegativeSubarraySum_withMultipleValues_possibleTarget_minSizeOne() {
        val numbers = listOf(23L, 6L, 2L, 4L, 7L)
        assertThat(numbers.findNonNegativeSubarraySum(6L, minSize = 1))
            .containsExactlyInAnyOrderElementsOfAny(listOf(6L), listOf(2L, 4L))
    }

    @Test
    fun findNonNegativeSubarraySum_withMultipleValues_possibleTarget_minSizeTwo() {
        val numbers = listOf(23L, 6L, 2L, 4L, 7L)
        assertThat(numbers.findNonNegativeSubarraySum(6L, minSize = 2))
            .containsExactlyInAnyOrder(2L, 4L)
    }

    @Test
    fun findNonNegativeSubarraySum_withMultipleValues_impossibleTarget_defaultMinSize() {
        val numbers = listOf(23L, 6L, 2L, 4L, 7L)
        assertThat(numbers.findNonNegativeSubarraySum(24L)).isNull()
    }

    @Test
    fun findNonNegativeSubarraySum_withMultipleValues_impossibleTarget_minSizeOne() {
        val numbers = listOf(23L, 6L, 2L, 4L, 7L)
        assertThat(numbers.findNonNegativeSubarraySum(24L, minSize = 1)).isNull()
    }

    @Test
    fun findNonNegativeSubarraySum_withMultipleValues_impossibleTarget_minSizeTwo() {
        val numbers = listOf(23L, 6L, 2L, 4L, 7L)
        assertThat(numbers.findNonNegativeSubarraySum(7L, minSize = 2)).isNull()
    }

    @Test
    fun findPairSum_ofInts_whenEmpty() {
        val numbers = emptyList<Int>()
        assertThat(numbers.findPairSum(0)).isNull()
    }

    @Test
    fun findPairSum_ofInts_singleValue() {
        val numbers = listOf(1)
        assertThat(numbers.findPairSum(1)).isNull()
    }

    @Test
    fun findPairSum_ofInts_twoValues_possibleTarget() {
        val numbers = listOf(-1, 2)
        assertThat(numbers.findPairSum(1)?.toList()).containsExactlyInAnyOrder(-1, 2)
    }

    @Test
    fun findPairSum_ofInts_twoValues_impossibleTarget() {
        val numbers = listOf(-1, 2)
        assertThat(numbers.findPairSum(2)).isNull()
    }

    @Test
    fun findPairSum_ofInts_severalValues_possibleTarget_distinctAddends() {
        val numbers = listOf(64, -36, 6, 39, -57, -80, -49, 30, -49, -53)
        assertThat(numbers.findPairSum(69)?.toList()).containsExactlyInAnyOrder(39, 30)
    }

    @Test
    fun findPairSum_ofInts_severalValues_possibleTarget_duplicateAddends() {
        val numbers = listOf(64, -36, 6, 39, -57, -80, -49, 30, -49, -53)
        assertThat(numbers.findPairSum(-98)?.toList()).containsExactly(-49, -49)
    }

    @Test
    fun findPairSum_ofInts_severalValues_possibleTarget_multipleOptions() {
        val numbers = listOf(64, -36, 6, 39, -56, -80, -49, 30, -49, -53)
        assertThat(numbers.findPairSum(-50)?.toList())
            .containsExactlyInAnyOrderElementsOfAny(listOf(6, -56), listOf(-80, 30))
    }

    @Test
    fun findPairSum_ofInts_severalValues_impossibleTarget() {
        val numbers = listOf(64, -36, 6, 39, -57, -80, -49, 30, -49, -53)
        assertThat(numbers.findPairSum(23)).isNull()
    }

    @Test
    fun findPairSum_ofLongs_whenEmpty() {
        val numbers = emptyList<Long>()
        assertThat(numbers.findPairSum(0L)).isNull()
    }

    @Test
    fun findPairSum_ofLongs_singleValue() {
        val numbers = listOf(1L)
        assertThat(numbers.findPairSum(1L)).isNull()
    }

    @Test
    fun findPairSum_ofLongs_twoValues_possibleTarget() {
        val numbers = listOf(-1L, 2L)
        assertThat(numbers.findPairSum(1L)?.toList()).containsExactlyInAnyOrder(-1L, 2L)
    }

    @Test
    fun findPairSum_ofLongs_twoValues_impossibleTarget() {
        val numbers = listOf(-1L, 2L)
        assertThat(numbers.findPairSum(2L)).isNull()
    }

    @Test
    fun findPairSum_ofLongs_severalValues_possibleTarget_bothDistinct() {
        val numbers = listOf(64L, -36L, 6L, 39L, -57L, -80L, -49L, 30L, -49L, -53L)
        assertThat(numbers.findPairSum(69L)?.toList()).containsExactlyInAnyOrder(39L, 30L)
    }

    @Test
    fun findPairSum_ofLongs_severalValues_possibleTarget_withDuplicates() {
        val numbers = listOf(64L, -36L, 6L, 39L, -57L, -80L, -49L, 30L, -49L, -53L)
        assertThat(numbers.findPairSum(-98L)?.toList()).containsExactly(-49L, -49L)
    }

    @Test
    fun findPairSum_ofLongs_severalValues_possibleTarget_withMultipleOptions() {
        val numbers = listOf(64L, -36L, 6L, 39L, -56L, -80L, -49L, 30L, -49L, -53L)
        assertThat(numbers.findPairSum(-50L)?.toList())
            .containsExactlyInAnyOrderElementsOfAny(listOf(6L, -56L), listOf(-80L, 30L))
    }

    @Test
    fun findPairSum_ofLongs_severalValues_impossibleTarget() {
        val numbers = listOf(64L, -36L, 6L, 39L, -57L, -80L, -49L, 30L, -49L, -53L)
        assertThat(numbers.findPairSum(23L)).isNull()
    }

    @Test
    fun findTripleSum_ofInts_whenEmpty() {
        val numbers = emptyList<Int>()
        assertThat(numbers.findTripleSum(0)).isNull()
    }

    @Test
    fun findTripleSum_ofInts_singleValue() {
        val numbers = listOf(1)
        assertThat(numbers.findTripleSum(1)).isNull()
    }

    @Test
    fun findTripleSum_ofInts_twoValues() {
        val numbers = listOf(-1, 2)
        assertThat(numbers.findTripleSum(1)).isNull()
    }

    @Test
    fun findTripleSum_ofInts_threeValues_possibleTarget() {
        val numbers = listOf(1, -2, 3)
        assertThat(numbers.findTripleSum(2)?.toList()).containsExactlyInAnyOrder(1, -2, 3)
    }

    @Test
    fun findTripleSum_ofInts_threeValues_impossibleTarget() {
        val numbers = listOf(1, -2, 3)
        assertThat(numbers.findTripleSum(3)).isNull()
    }

    @Test
    fun findTripleSum_ofInts_severalValues_possibleTarget_allDistinct() {
        val numbers = listOf(24, 66, 38, 26, -30, 54, -30, -60, 39, 31)
        assertThat(numbers.findTripleSum(60)?.toList()).containsExactlyInAnyOrder(66, 54, -60)
    }

    @Test
    fun findTripleSum_ofInts_severalValues_possibleTarget_withDuplicates() {
        val numbers = listOf(24, 66, 38, 26, -30, 54, -30, -60, 39, 31)
        assertThat(numbers.findTripleSum(-29)?.toList()).containsExactlyInAnyOrder(-30, -30, 31)
    }

    @Test
    fun findTripleSum_ofInts_severalValues_possibleTarget_withMultipleOptions() {
        val numbers = listOf(24, 66, 38, 28, -30, 54, -30, -60, 39, 31)
        assertThat(numbers.findTripleSum(6)?.toList()).containsExactlyInAnyOrderElementsOfAny(
            listOf(66, -30, -30),
            listOf(38, 28, -60)
        )
    }

    @Test
    fun findTripleSum_ofInts_severalValues_impossibleTarget() {
        val numbers = listOf(24, 66, 38, 26, -30, 54, -30, -60, 39, 31)
        assertThat(numbers.findTripleSum(42)).isNull()
    }

    @Test
    fun findTripleSum_ofLongs_whenEmpty() {
        val numbers = emptyList<Long>()
        assertThat(numbers.findTripleSum(0L)).isNull()
    }

    @Test
    fun findTripleSum_ofLongs_singleValue() {
        val numbers = listOf(1L)
        assertThat(numbers.findTripleSum(1L)).isNull()
    }

    @Test
    fun findTripleSum_ofLongs_twoValues() {
        val numbers = listOf(-1L, 2L)
        assertThat(numbers.findTripleSum(1L)).isNull()
    }

    @Test
    fun findTripleSum_ofLongs_threeValues_possibleTarget() {
        val numbers = listOf(1L, -2L, 3L)
        assertThat(numbers.findTripleSum(2L)?.toList()).containsExactlyInAnyOrder(1L, -2L, 3L)
    }

    @Test
    fun findTripleSum_ofLongs_threeValues_impossibleTarget() {
        val numbers = listOf(1L, -2L, 3L)
        assertThat(numbers.findTripleSum(3L)).isNull()
    }

    @Test
    fun findTripleSum_ofLongs_severalValues_possibleTarget_allDistinct() {
        val numbers = listOf(24L, 66L, 38L, 26L, -30L, 54L, -30L, -60L, 39L, 31L)
        assertThat(numbers.findTripleSum(60L)?.toList()).containsExactlyInAnyOrder(66L, 54L, -60L)
    }

    @Test
    fun findTripleSum_ofLongs_severalValues_possibleTarget_withDuplicates() {
        val numbers = listOf(24L, 66L, 38L, 26L, -30L, 54L, -30L, -60L, 39L, 31L)
        assertThat(numbers.findTripleSum(-29L)?.toList()).containsExactlyInAnyOrder(-30L, -30L, 31L)
    }

    @Test
    fun findTripleSum_ofLongs_severalValues_possibleTarget_withMultipleOptions() {
        val numbers = listOf(24L, 66L, 38L, 26L, -30L, 54L, -30L, -60L, 39L, 31L)
        assertThat(numbers.findTripleSum(6)?.toList()).containsExactlyInAnyOrderElementsOfAny(
            listOf(66L, -30L, -30L),
            listOf(38L, 28L, -60L)
        )
    }

    @Test
    fun findTripleSum_ofLongs_severalValues_impossibleTarget() {
        val numbers = listOf(24L, 66L, 38L, 26L, -30L, 54L, -30L, -60L, 39L, 31L)
        assertThat(numbers.findTripleSum(42L)).isNull()
    }
}
