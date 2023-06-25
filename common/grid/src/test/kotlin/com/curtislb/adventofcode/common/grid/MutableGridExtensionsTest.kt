package com.curtislb.adventofcode.common.grid

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests the [addRow] and [updateAll] extension functions.
 */
class MutableGridExtensionsTest {
    @Test
    fun addRow_toEmptyGrid_singleElement() {
        val grid = mutableGridOf<Int>()
        grid.addRow { add(1) }
        Assertions.assertThat(grid).isEqualTo(gridOf(listOf(1)))
    }

    @Test
    fun addRow_toEmptyGrid_multipleElements() {
        val grid = mutableGridOf<Int>()
        grid.addRow { addAll(listOf(1, 2, 3)) }
        Assertions.assertThat(grid).isEqualTo(gridOf(listOf(1, 2, 3)))
    }

    @Test
    fun addRow_toNonEmptyGrid_sameRowSize() {
        val grid = mutableGridOf(listOf(1, 2))
        grid.addRow { addAll(listOf(3, 4)) }
        Assertions.assertThat(grid).isEqualTo(gridOf(listOf(1, 2), listOf(3, 4)))
    }

    @Test
    fun addRow_toNonEmptyGrid_differentRowSize() {
        val grid = mutableGridOf(listOf(1, 2, 3))
        assertThrows<IllegalArgumentException> {
            grid.addRow { addAll(listOf(4, 5)) }
        }
    }

    @Test
    fun updateAll_whenEmpty() {
        val grid = mutableGridOf<String>()
        grid.updateAll { it.reversed() }
        Assertions.assertThat(grid).isEqualTo(emptyGrid<String>())
    }

    @Test
    fun updateAll_withSingleElement() {
        val grid = mutableGridOf(listOf("foo"))
        grid.updateAll { it.reversed() }
        Assertions.assertThat(grid).isEqualTo(gridOf(listOf("oof")))
    }

    @Test
    fun updateAll_withSingleRow() {
        val grid = mutableGridOf(listOf("foo", "bar", "baz"))
        grid.updateAll { it.reversed() }
        Assertions.assertThat(grid).isEqualTo(gridOf(listOf("oof", "rab", "zab")))
    }

    @Test
    fun updateAll_withMultipleRows() {
        val grid = mutableGridOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        grid.updateAll { it.reversed() }
        Assertions.assertThat(grid).isEqualTo(
            gridOf(listOf("oof", "rab"), listOf("zab", "xuq"), listOf("xoq", "derf"))
        )
    }
}
