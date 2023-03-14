package com.curtislb.adventofcode.common.comparison

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [argmax] and [argmaxBy].
 */
class ArgmaxTest {
    @Test
    fun argmax_emptyList() {
        val list = emptyList<Int>()
        assertThrows<NoSuchElementException> { list.argmax() }
    }

    @Test
    fun argmax_singletonList() {
        val list = listOf("foo")
        assertThat(list.argmax()).isEqualTo(0)
    }

    @Test
    fun argmax_smallList_noDuplicate() {
        val list = listOf('o', 'r', 'z', 'x')
        assertThat(list.argmax()).isEqualTo(2)
    }

    @Test
    fun argmax_smallList_withDuplicate() {
        val list = listOf("bar", "foo", "baz", "foo")
        assertThat(list.argmax()).isEqualTo(1)
    }

    @Test
    fun argmax_largeList() {
        val list = listOf(
            89484,
            -29734,
            34406,
            2922,
            -74977,
            -52574,
            -46920,
            -65119,
            30521,
            90135,
            -64431,
            50678,
            -20738,
            14070,
            -93447,
            17530,
            -28077,
            -79639,
            -92369,
            77830,
            99644,
            1988,
            -76860,
            -88691,
            78692,
        )
        assertThat(list.argmax()).isEqualTo(20)
    }

    @Test
    fun argmaxBy_emptyList() {
        val list = emptyList<Int>()
        assertThrows<NoSuchElementException> { list.argmaxBy { it } }
    }

    @Test
    fun argmaxBy_singletonList() {
        val list = listOf("foo")
        assertThat(list.argmaxBy { it.last() }).isEqualTo(0)
    }

    @Test
    fun argmaxBy_smallList_noDuplicate() {
        val list = listOf("foo", "barbaz", "qux", "quux")
        assertThat(list.argmaxBy { it.length }).isEqualTo(1)
    }

    @Test
    fun argmaxBy_smallList_withDuplicate() {
        val list = listOf("foo", "bar", "quux", "fred", "baz")
        assertThat(list.argmaxBy { it.length }).isEqualTo(2)
    }

    @Test
    fun argmaxBy_largeList() {
        val list = listOf(
            "18630",
            "14144",
            "-99090",
            "30128",
            "39961",
            "13623",
            "40370",
            "-65857",
            "66109",
            "30749",
            "-48162",
            "-53018",
            "76684",
            "-81290",
            "-81072",
            "64040",
            "30163",
            "-74778",
            "-19558",
            "42645",
            "-91301",
            "-5760",
            "24132",
            "34972",
            "4158"
        )
        assertThat(list.argmaxBy { it.toInt() }).isEqualTo(12)
    }
}
