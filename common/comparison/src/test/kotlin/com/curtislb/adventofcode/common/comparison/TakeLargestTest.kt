package com.curtislb.adventofcode.common.comparison

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [takeLargest].
 */
class TakeLargestTest {
    @Test
    fun takeLargest_emptyList_negativeCount() {
        val list = emptyList<Int>()
        assertThrows<IllegalArgumentException> { list.takeLargest(-1) }
    }

    @Test
    fun takeLargest_emptyList_zeroCount() {
        val list = emptyList<Int>()
        assertThat(list.takeLargest(0)).isEmpty()
    }

    @Test
    fun takeLargest_emptyList_positiveCount() {
        val list = emptyList<Int>()
        assertThat(list.takeLargest(1)).isEmpty()
    }
    @Test
    fun takeLargest_singletonList_negativeCount() {
        val list = listOf(42)
        assertThrows<IllegalArgumentException> { list.takeLargest(-1) }
    }

    @Test
    fun takeLargest_singletonList_zeroCount() {
        val list = listOf(42)
        assertThat(list.takeLargest(0)).isEmpty()
    }

    @Test
    fun takeLargest_singletonList_oneCount() {
        val list = listOf(42)
        assertThat(list.takeLargest(1)).containsExactly(42)
    }

    @Test
    fun takeLargest_singletonList_twoCount() {
        val list = listOf(42)
        assertThat(list.takeLargest(2)).containsExactly(42)
    }

    @Test
    fun takeLargest_multiElementList_negativeCount() {
        val list = listOf("lorem", "ipsum", "dolor", "sit", "amet")
        assertThrows<IllegalArgumentException> { list.takeLargest(-1) }
    }

    @Test
    fun takeLargest_multiElementList_zeroCount() {
        val list = listOf("lorem", "ipsum", "dolor", "sit", "amet")
        assertThat(list.takeLargest(0)).isEmpty()
    }

    @Test
    fun takeLargest_multiElementList_oneCount() {
        val list = listOf("lorem", "ipsum", "dolor", "sit", "amet")
        assertThat(list.takeLargest(1)).containsExactly("sit")
    }

    @Test
    fun takeLargest_multiElementList_countLessThanSize() {
        val list = listOf("lorem", "ipsum", "dolor", "sit", "amet")
        assertThat(list.takeLargest(3)).containsExactlyInAnyOrder("lorem", "ipsum", "sit")
    }

    @Test
    fun takeLargest_multiElementList_countEqualToSize() {
        val list = listOf("lorem", "ipsum", "dolor", "sit", "amet")
        assertThat(list.takeLargest(5))
            .containsExactlyInAnyOrder("lorem", "ipsum", "dolor", "sit", "amet")
    }

    @Test
    fun takeLargest_multiElementList_countGreaterThanSize() {
        val list = listOf("lorem", "ipsum", "dolor", "sit", "amet")
        assertThat(list.takeLargest(6))
            .containsExactlyInAnyOrder("lorem", "ipsum", "dolor", "sit", "amet")
    }
}
