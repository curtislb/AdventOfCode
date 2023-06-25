package com.curtislb.adventofcode.common.grid

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests the [EmptyGrid] object.
 */
class EmptyGridTest {
    private lateinit var grid: Grid<Any>

    @BeforeEach
    fun setUp() {
        grid = emptyGrid()
    }

    @Test
    fun height_isZero() {
        assertThat(grid.height).isEqualTo(0)
    }

    @Test
    fun width_isZero() {
        assertThat(grid.width).isEqualTo(0)
    }

    @Test
    fun get_indices_bothZero() {
        assertThrows<IndexOutOfBoundsException> { grid[0, 0] }
    }

    @Test
    fun get_indices_rowIndexZero() {
        assertThrows<IndexOutOfBoundsException> { grid[0, 1] }
    }

    @Test
    fun get_indices_colIndexZero() {
        assertThrows<IndexOutOfBoundsException> { grid[1, 0] }
    }

    @Test
    fun get_indices_bothNonzero() {
        assertThrows<IndexOutOfBoundsException> { grid[1, 2] }
    }

    @Test
    fun row_indexZero() {
        assertThrows<IndexOutOfBoundsException> { grid.row(0) }
    }

    @Test
    fun row_indexNonzero() {
        assertThrows<IndexOutOfBoundsException> { grid.row(1) }
    }

    @Test
    fun column_indexZero() {
        assertThrows<IndexOutOfBoundsException> { grid.column(0) }
    }

    @Test
    fun column_indexNonzero() {
        assertThrows<IndexOutOfBoundsException> { grid.column(1) }
    }

    @Test
    fun rows_isEmpty() {
        assertThat(grid.rows()).isEmpty()
    }

    @Test
    fun columns_isEmpty() {
        assertThat(grid.columns()).isEmpty()
    }

    @Test
    fun equals_self() {
        assertThat(grid).isEqualTo(grid)
    }

    @Test
    fun equals_emptyGrid() {
        val other = emptyGrid<Any>()
        assertThat(grid).isEqualTo(other)
    }

    @Test
    fun equals_emptyImmutableGrid() {
        val other = gridOf<Any>()
        assertThat(grid).isEqualTo(other)
    }

    @Test
    fun equals_emptyMutableGrid() {
        val other = mutableGridOf<Any>()
        assertThat(grid).isEqualTo(other)
    }

    @Test
    fun equals_nullGrid() {
        val other: Grid<Any>? = null
        assertThat(grid).isNotEqualTo(other)
    }

    @Test
    fun equals_nonEmptyGrid() {
        val other = gridOf(listOf(0))
        assertThat(grid).isNotEqualTo(other)
    }

    @Test
    fun hashCode_isConsistent() {
        val grid = emptyGrid<Any>()
        assertThat(grid.hashCode())
            .isEqualTo(grid.hashCode())
            .isEqualTo(emptyGrid<Any>().hashCode())
    }

    @Test
    fun toString_isCorrect() {
        assertThat(grid.toString()).isEqualTo("[]")
    }
}
