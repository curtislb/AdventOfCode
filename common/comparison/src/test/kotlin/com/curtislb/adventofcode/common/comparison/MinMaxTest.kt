package com.curtislb.adventofcode.common.comparison

import kotlin.math.abs
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests [minMaxOrNull] and [minMaxByOrNull].
 */
class MinMaxTest {
    @Test
    fun minMaxOrNull_emptyList() {
        val list = emptyList<Int>()
        assertThat(list.minMaxOrNull()).isNull()
    }

    @Test
    fun minMaxOrNull_singletonList() {
        val list = listOf("foo")
        assertThat(list.minMaxOrNull()).isEqualTo(MinMax(min = "foo", max = "foo"))
    }

    @Test
    fun minMaxOrNull_smallList_noDuplicates() {
        val list = listOf('n', 'i', 'c', 'e')
        assertThat(list.minMaxOrNull()).isEqualTo(MinMax(min = 'c', max = 'n'))
    }

    @Test
    fun minMaxOrNull_smallList_withDuplicates() {
        val list = listOf(2, 0, 2, 1, 0)
        assertThat(list.minMaxOrNull()).isEqualTo(MinMax(min = 0, max = 2))
    }

    @Test
    fun minMaxOrNull_largeList() {
        val list = listOf(
            -31691,
            -25313,
            -32063,
            27519,
            20747,
            -39670,
            -41078,
            46503,
            66826,
            4441,
            38663,
            27425,
            -91029,
            51711,
            -72345,
            -36140,
            83422,
            -17293,
            33274,
            79954,
            -24672,
            -61102,
            -63318,
            64814,
            69788,
        )
        assertThat(list.minMaxOrNull()).isEqualTo(MinMax(min = -91029, max = 83422))
    }

    @Test
    fun minMaxByOrNull_emptyList() {
        val list = emptyList<Int>()
        assertThat(list.minMaxByOrNull { it }).isNull()
    }

    @Test
    fun minMaxByOrNull_singletonList() {
        val list = listOf("foo")
        assertThat(list.minMaxByOrNull { it.length }).isEqualTo(MinMax(min = "foo", max = "foo"))
    }

    @Test
    fun minMaxByOrNull_smallList_noDuplicates() {
        val list = listOf("foo", "bar", "baz", "qux")
        assertThat(list.minMaxByOrNull { it.last() }).isEqualTo(MinMax(min = "foo", max = "baz"))
    }

    @Test
    fun minMaxByOrNull_smallList_withDuplicates() {
        val list = listOf("foo", "bar", "baz", "qux", "quux")
        assertThat(list.minMaxByOrNull { it.first() }).isEqualTo(MinMax(min = "bar", max = "qux"))
    }

    @Test
    fun minMaxByOrNull_largeList() {
        val list = listOf(
            -81906,
            -73383,
            -15708,
            17801,
            -6774,
            97453,
            30285,
            -83479,
            -81800,
            -17258,
            24010,
            -91807,
            88703,
            -24245,
            -98124,
            31840,
            -73826,
            29215,
            -74901,
            -34130,
            33429,
            71619,
            38295,
            -92733,
            39493
        )
        assertThat(list.minMaxByOrNull { abs(it) }).isEqualTo(MinMax(min = -6774, max = -98124))
    }
}
