package com.curtislb.adventofcode.common.grid

import com.curtislb.adventofcode.common.geometry.Point
import java.util.Objects
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests the [Grid] interface and related functions.
 */
class GridTest {
    @Test
    fun size_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        assertThat(grid.size).isEqualTo(0)
    }

    @Test
    fun size_withSingleElement() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.size).isEqualTo(1)
    }

    @Test
    fun size_withSingleRow() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.size).isEqualTo(3)
    }

    @Test
    fun size_withMultipleRows() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.size).isEqualTo(6)
    }

    @Test
    fun lastRowIndex_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        assertThat(grid.lastRowIndex).isEqualTo(-1)
    }

    @Test
    fun lastRowIndex_withSingleElement() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.lastRowIndex).isEqualTo(0)
    }

    @Test
    fun lastRowIndex_withSingleRow() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.lastRowIndex).isEqualTo(0)
    }

    @Test
    fun lastRowIndex_withMultipleRows() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.lastRowIndex).isEqualTo(2)
    }

    @Test
    fun lastColumnIndex_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        assertThat(grid.lastColumnIndex).isEqualTo(-1)
    }

    @Test
    fun lastColumnIndex_withSingleElement() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.lastColumnIndex).isEqualTo(0)
    }

    @Test
    fun lastColumnIndex_withSingleRow() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.lastColumnIndex).isEqualTo(2)
    }

    @Test
    fun lastColumnIndex_withMultipleRows() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.lastColumnIndex).isEqualTo(1)
    }

    @Test
    fun rowIndices_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        assertThat(grid.rowIndices).isEqualTo(IntRange.EMPTY)
    }

    @Test
    fun rowIndices_withSingleElement() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.rowIndices).isEqualTo(0..0)
    }

    @Test
    fun rowIndices_withSingleRow() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.rowIndices).isEqualTo(0..0)
    }

    @Test
    fun rowIndices_withMultipleRows() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.rowIndices).isEqualTo(0..2)
    }

    @Test
    fun columnIndices_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        assertThat(grid.columnIndices).isEqualTo(IntRange.EMPTY)
    }

    @Test
    fun columnIndices_withSingleElement() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.columnIndices).isEqualTo(0..0)
    }

    @Test
    fun columnIndices_withSingleRow() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.columnIndices).isEqualTo(0..2)
    }

    @Test
    fun columnIndices_withMultipleRows() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.columnIndices).isEqualTo(0..1)
    }

    @Test
    fun isEmpty_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        assertThat(grid.isEmpty()).isTrue()
    }

    @Test
    fun isEmpty_withSingleElement() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.isEmpty()).isFalse()
    }

    @Test
    fun isEmpty_withSingleRow() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.isEmpty()).isFalse()
    }

    @Test
    fun isEmpty_withMultipleRows() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.isEmpty()).isFalse()
    }

    @Test
    fun points_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        assertThat(grid.points().asIterable()).isEmpty()
    }

    @Test
    fun points_withSingleElement() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.points().asIterable()).containsExactly(Point.ORIGIN)
    }

    @Test
    fun points_withSingleRow() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.points().asIterable())
            .containsExactlyInAnyOrder(Point(0, 0), Point(1, 0), Point(2, 0))
    }

    @Test
    fun points_withMultipleRows() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.points().asIterable()).containsExactlyInAnyOrder(
            Point(0, 0),
            Point(1, 0),
            Point(0, -1),
            Point(1, -1),
            Point(0, -2),
            Point(1, -2)
        )
    }

    @Test
    fun values_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        assertThat(grid.values().asIterable()).isEmpty()
    }

    @Test
    fun values_withSingleElement() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.values().asIterable()).containsExactly("foo")
    }

    @Test
    fun values_withSingleRow() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.values().asIterable()).containsExactlyInAnyOrder("foo", "bar", "baz")
    }

    @Test
    fun values_withMultipleRows() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.values().asIterable())
            .containsExactlyInAnyOrder("foo", "bar", "baz", "qux", "qox", "fred")
    }

    @Test
    fun contains_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        val point = Point.ORIGIN
        assertThat(point in grid).isFalse()
    }

    @Test
    fun contains_withSingleElement_pointInBounds() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        val point = Point.ORIGIN
        assertThat(point in grid).isTrue()
    }

    @Test
    fun contains_withSingleElement_xTooSmall() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        val point = Point(-1, 0)
        assertThat(point in grid).isFalse()
    }

    @Test
    fun contains_withSingleElement_xTooLarge() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        val point = Point(1, 0)
        assertThat(point in grid).isFalse()
    }

    @Test
    fun contains_withSingleElement_yTooSmall() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        val point = Point(0, -1)
        assertThat(point in grid).isFalse()
    }

    @Test
    fun contains_withSingleElement_yTooLarge() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        val point = Point(0, 1)
        assertThat(point in grid).isFalse()
    }

    @Test
    fun contains_withSingleRow_pointInBounds() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        val point = Point(2, 0)
        assertThat(point in grid).isTrue()
    }

    @Test
    fun contains_withSingleRow_xTooSmall() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        val point = Point(-1, 0)
        assertThat(point in grid).isFalse()
    }

    @Test
    fun contains_withSingleRow_xTooLarge() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        val point = Point(3, 0)
        assertThat(point in grid).isFalse()
    }

    @Test
    fun contains_withSingleRow_yTooSmall() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        val point = Point(0, -1)
        assertThat(point in grid).isFalse()
    }

    @Test
    fun contains_withSingleRow_yTooLarge() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        val point = Point(0, 1)
        assertThat(point in grid).isFalse()
    }

    @Test
    fun contains_withMultipleRows_pointInBounds() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        val point = Point(1, -2)
        assertThat(point in grid).isTrue()
    }

    @Test
    fun contains_withMultipleRows_xTooSmall() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        val point = Point(-1, -1)
        assertThat(point in grid).isFalse()
    }

    @Test
    fun contains_withMultipleRows_xTooLarge() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        val point = Point(2, -1)
        assertThat(point in grid).isFalse()
    }

    @Test
    fun contains_withMultipleRows_yTooSmall() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        val point = Point(1, -3)
        assertThat(point in grid).isFalse()
    }

    @Test
    fun contains_withMultipleRows_yTooLarge() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        val point = Point(1, 1)
        assertThat(point in grid).isFalse()
    }

    @Test
    fun get_point_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        val point = Point.ORIGIN
        assertThrows<IndexOutOfBoundsException> { grid[point] }
    }

    @Test
    fun get_point_withSingleElement_pointInBounds() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        val point = Point.ORIGIN
        assertThat(grid[point]).isEqualTo("foo")
    }

    @Test
    fun get_point_withSingleElement_xTooSmall() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        val point = Point(-1, 0)
        assertThrows<IndexOutOfBoundsException> { grid[point] }
    }

    @Test
    fun get_point_withSingleElement_xTooLarge() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        val point = Point(1, 0)
        assertThrows<IndexOutOfBoundsException> { grid[point] }
    }

    @Test
    fun get_point_withSingleElement_yTooSmall() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        val point = Point(0, -1)
        assertThrows<IndexOutOfBoundsException> { grid[point] }
    }

    @Test
    fun get_point_withSingleElement_yTooLarge() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        val point = Point(0, 1)
        assertThrows<IndexOutOfBoundsException> { grid[point] }
    }

    @Test
    fun get_point_withSingleRow_pointInBounds() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        val point = Point(2, 0)
        assertThat(grid[point]).isEqualTo("baz")
    }

    @Test
    fun get_point_withSingleRow_xTooSmall() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        val point = Point(-1, 1)
        assertThrows<IndexOutOfBoundsException> { grid[point] }
    }

    @Test
    fun get_point_withSingleRow_xTooLarge() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        val point = Point(3, 0)
        assertThrows<IndexOutOfBoundsException> { grid[point] }
    }

    @Test
    fun get_point_withSingleRow_yTooSmall() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        val point = Point(1, -1)
        assertThrows<IndexOutOfBoundsException> { grid[point] }
    }

    @Test
    fun get_point_withSingleRow_yTooLarge() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        val point = Point(1, 1)
        assertThrows<IndexOutOfBoundsException> { grid[point] }
    }

    @Test
    fun get_point_withMultipleRows_pointInBounds() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        val point = Point(1, -2)
        assertThat(grid[point]).isEqualTo("fred")
    }

    @Test
    fun get_point_withMultipleRows_xTooSmall() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        val point = Point(-1, -1)
        assertThrows<IndexOutOfBoundsException> { grid[point] }
    }

    @Test
    fun get_point_withMultipleRows_xTooLarge() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        val point = Point(2, -1)
        assertThrows<IndexOutOfBoundsException> { grid[point] }
    }

    @Test
    fun get_point_withMultipleRows_yTooSmall() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        val point = Point(0, -3)
        assertThrows<IndexOutOfBoundsException> { grid[point] }
    }

    @Test
    fun get_point_withMultipleRows_yTooLarge() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        val point = Point(0, 1)
        assertThrows<IndexOutOfBoundsException> { grid[point] }
    }

    @Test
    fun getOrNull_indices_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        assertThat(grid.getOrNull(0, 0)).isNull()
    }

    @Test
    fun getOrNull_indices_withSingleElement_indicesInBounds() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.getOrNull(0, 0)).isEqualTo("foo")
    }

    @Test
    fun getOrNull_indices_withSingleElement_rowIndexTooSmall() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.getOrNull(-1, 0)).isNull()
    }

    @Test
    fun getOrNull_indices_withSingleElement_rowIndexTooLarge() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.getOrNull(1, 0)).isNull()
    }

    @Test
    fun getOrNull_indices_withSingleElement_colIndexTooSmall() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.getOrNull(0, -1)).isNull()
    }

    @Test
    fun getOrNull_indices_withSingleElement_colIndexTooLarge() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.getOrNull(0, 1)).isNull()
    }

    @Test
    fun getOrNull_indices_withSingleRow_indicesInBounds() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.getOrNull(0, 2)).isEqualTo("baz")
    }

    @Test
    fun getOrNull_indices_withSingleRow_rowIndexTooSmall() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.getOrNull(-1, 1)).isNull()
    }

    @Test
    fun getOrNull_indices_withSingleRow_rowIndexTooLarge() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.getOrNull(1, 1)).isNull()
    }

    @Test
    fun getOrNull_indices_withSingleRow_colIndexTooSmall() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.getOrNull(0, -1)).isNull()
    }

    @Test
    fun getOrNull_indices_withSingleRow_colIndexTooLarge() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.getOrNull(0, 3)).isNull()
    }

    @Test
    fun getOrNull_indices_withMultipleRows_indicesInBounds() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.getOrNull(2, 1)).isEqualTo("fred")
    }

    @Test
    fun getOrNull_indices_withMultipleRows_rowIndexTooSmall() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.getOrNull(-1, 0)).isNull()
    }

    @Test
    fun getOrNull_indices_withMultipleRows_rowIndexTooLarge() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.getOrNull(3, 0)).isNull()
    }

    @Test
    fun getOrNull_indices_withMultipleRows_colIndexTooSmall() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.getOrNull(1, -1)).isNull()
    }

    @Test
    fun getOrNull_indices_withMultipleRows_colIndexTooLarge() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.getOrNull(1, 2)).isNull()
    }

    @Test
    fun getOrNull_point_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        val point = Point.ORIGIN
        assertThat(grid.getOrNull(point)).isNull()
    }

    @Test
    fun getOrNull_point_withSingleElement_pointInBounds() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        val point = Point.ORIGIN
        assertThat(grid.getOrNull(point)).isEqualTo("foo")
    }

    @Test
    fun getOrNull_point_withSingleElement_xTooSmall() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        val point = Point(-1, 0)
        assertThat(grid.getOrNull(point)).isNull()
    }

    @Test
    fun getOrNull_point_withSingleElement_xTooLarge() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        val point = Point(1, 0)
        assertThat(grid.getOrNull(point)).isNull()
    }

    @Test
    fun getOrNull_point_withSingleElement_yTooSmall() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        val point = Point(0, -1)
        assertThat(grid.getOrNull(point)).isNull()
    }

    @Test
    fun getOrNull_points_withSingleElement_yTooLarge() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        val point = Point(0, 1)
        assertThat(grid.getOrNull(point)).isNull()
    }

    @Test
    fun getOrNull_point_withSingleRow_pointInBounds() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        val point = Point(2, 0)
        assertThat(grid.getOrNull(point)).isEqualTo("baz")
    }

    @Test
    fun getOrNull_point_withSingleRow_xTooSmall() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        val point = Point(-1, 1)
        assertThat(grid.getOrNull(point)).isNull()
    }

    @Test
    fun getOrNull_point_withSingleRow_xTooLarge() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        val point = Point(3, 0)
        assertThat(grid.getOrNull(point)).isNull()
    }

    @Test
    fun getOrNull_point_withSingleRow_yTooSmall() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        val point = Point(1, -1)
        assertThat(grid.getOrNull(point)).isNull()
    }

    @Test
    fun getOrNull_point_withSingleRow_yTooLarge() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        val point = Point(1, 1)
        assertThat(grid.getOrNull(point)).isNull()
    }

    @Test
    fun getOrNull_point_withMultipleRows_pointInBounds() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        val point = Point(1, -2)
        assertThat(grid.getOrNull(point)).isEqualTo("fred")
    }

    @Test
    fun getOrNull_point_withMultipleRows_xTooSmall() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        val point = Point(-1, -1)
        assertThat(grid.getOrNull(point)).isNull()
    }

    @Test
    fun getOrNull_point_withMultipleRows_xTooLarge() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        val point = Point(2, -1)
        assertThat(grid.getOrNull(point)).isNull()
    }

    @Test
    fun getOrNull_point_withMultipleRows_yTooSmall() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        val point = Point(0, -3)
        assertThat(grid.getOrNull(point)).isNull()
    }

    @Test
    fun getOrNull_point_withMultipleRows_yTooLarge() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        val point = Point(0, 1)
        assertThat(grid.getOrNull(point)).isNull()
    }

    @Test
    fun firstRow_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        assertThrows<NoSuchElementException> { grid.firstRow() }
    }

    @Test
    fun firstRow_withSingleElement() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.firstRow()).isEqualTo(listOf("foo"))
    }

    @Test
    fun firstRow_withSingleRow() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.firstRow()).isEqualTo(listOf("foo", "bar", "baz"))
    }

    @Test
    fun firstRow_withMultipleRows() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.firstRow()).isEqualTo(listOf("foo", "bar"))
    }

    @Test
    fun firstColumn_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        assertThrows<NoSuchElementException> { grid.firstColumn() }
    }

    @Test
    fun firstColumn_withSingleElement() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.firstColumn()).isEqualTo(listOf("foo"))
    }

    @Test
    fun firstColumn_withSingleRow() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.firstColumn()).isEqualTo(listOf("foo"))
    }

    @Test
    fun firstColumn_withMultipleRows() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.firstColumn()).isEqualTo(listOf("foo", "baz", "qox"))
    }

    @Test
    fun lastRow_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        assertThrows<NoSuchElementException> { grid.lastRow() }
    }

    @Test
    fun lastRow_withSingleElement() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.lastRow()).isEqualTo(listOf("foo"))
    }

    @Test
    fun lastRow_withSingleRow() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.lastRow()).isEqualTo(listOf("foo", "bar", "baz"))
    }

    @Test
    fun lastRow_withMultipleRows() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.lastRow()).isEqualTo(listOf("qox", "fred"))
    }

    @Test
    fun lastColumn_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        assertThrows<NoSuchElementException> { grid.lastColumn() }
    }

    @Test
    fun lastColumn_withSingleElement() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.lastColumn()).isEqualTo(listOf("foo"))
    }

    @Test
    fun lastColumn_withSingleRow() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.lastColumn()).isEqualTo(listOf("baz"))
    }

    @Test
    fun lastColumn_withMultipleRows() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.lastColumn()).isEqualTo(listOf("bar", "qux", "fred"))
    }

    @Test
    fun rowOrNull_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        assertThat(grid.rowOrNull(0)).isNull()
    }

    @Test
    fun rowOrNull_withSingleElement_indexInBounds() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.rowOrNull(0)).isEqualTo(listOf("foo"))
    }

    @Test
    fun rowOrNull_withSingleElement_indexTooSmall() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.rowOrNull(-1)).isNull()
    }

    @Test
    fun rowOrNull_withSingleElement_indexTooLarge() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.rowOrNull(1)).isNull()
    }

    @Test
    fun rowOrNull_withSingleRow_indexInBounds() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.rowOrNull(0)).isEqualTo(listOf("foo", "bar", "baz"))
    }

    @Test
    fun rowOrNull_withSingleRow_indexTooSmall() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.rowOrNull(-1)).isNull()
    }

    @Test
    fun rowOrNull_withSingleRow_indexTooLarge() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.rowOrNull(1)).isNull()
    }

    @Test
    fun rowOrNull_withMultipleRows_indexInBounds() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.rowOrNull(1)).isEqualTo(listOf("baz", "qux"))
    }

    @Test
    fun rowOrNull_withMultipleRows_indexTooSmall() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.rowOrNull(-1)).isNull()
    }

    @Test
    fun rowOrNull_withMultipleRows_indexTooLarge() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.rowOrNull(3)).isNull()
    }

    @Test
    fun columnOrNull_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        assertThat(grid.columnOrNull(0)).isNull()
    }

    @Test
    fun columnOrNull_withSingleElement_indexInBounds() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.columnOrNull(0)).isEqualTo(listOf("foo"))
    }

    @Test
    fun columnOrNull_withSingleElement_indexTooSmall() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.columnOrNull(-1)).isNull()
    }

    @Test
    fun columnOrNull_withSingleElement_indexTooLarge() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.columnOrNull(1)).isNull()
    }

    @Test
    fun columnOrNull_withSingleRow_indexInBounds() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.columnOrNull(1)).isEqualTo(listOf("bar"))
    }

    @Test
    fun columnOrNull_withSingleRow_indexTooSmall() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.columnOrNull(-1)).isNull()
    }

    @Test
    fun columnOrNull_withSingleRow_indexTooLarge() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.columnOrNull(3)).isNull()
    }

    @Test
    fun columnOrNull_withMultipleRows_indexInBounds() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.columnOrNull(1)).isEqualTo(listOf("bar", "qux", "fred"))
    }

    @Test
    fun columnOrNull_withMultipleRows_indexTooSmall() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.columnOrNull(-1)).isNull()
    }

    @Test
    fun columnOrNull_withMultipleRows_indexTooLarge() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.columnOrNull(3)).isNull()
    }

    @Test
    fun rows_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        assertThat(grid.rows()).isEmpty()
    }

    @Test
    fun rows_withSingleElement() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.rows()).containsExactly(listOf("foo"))
    }

    @Test
    fun rows_withSingleRow() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.rows()).containsExactly(listOf("foo", "bar", "baz"))
    }

    @Test
    fun rows_withMultipleRows() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.rows())
            .containsExactly(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
    }

    @Test
    fun columns_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        assertThat(grid.columns()).isEmpty()
    }

    @Test
    fun columns_withSingleElement() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.columns()).containsExactly(listOf("foo"))
    }

    @Test
    fun columns_withSingleRow() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.columns()).containsExactly(listOf("foo"), listOf("bar"), listOf("baz"))
    }

    @Test
    fun columns_withMultipleRows() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.columns())
            .containsExactly(listOf("foo", "baz", "qox"), listOf("bar", "qux", "fred"))
    }

    @Test
    fun shallowRow_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        assertThrows<IndexOutOfBoundsException> { grid.shallowRow(0) }
    }

    @Test
    fun shallowRow_withSingleElement_indexInBounds() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.shallowRow(0)).isEqualTo(listOf("foo"))
    }

    @Test
    fun shallowRow_withSingleElement_indexTooSmall() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThrows<IndexOutOfBoundsException> { grid.shallowRow(-1) }
    }

    @Test
    fun shallowRow_withSingleElement_indexTooLarge() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThrows<IndexOutOfBoundsException> { grid.shallowRow(1) }
    }

    @Test
    fun shallowRow_withSingleRow_indexInBounds() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.shallowRow(0)).isEqualTo(listOf("foo", "bar", "baz"))
    }

    @Test
    fun shallowRow_withSingleRow_indexTooSmall() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThrows<IndexOutOfBoundsException> { grid.shallowRow(-1) }
    }

    @Test
    fun shallowRow_withSingleRow_indexTooLarge() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThrows<IndexOutOfBoundsException> { grid.shallowRow(1) }
    }

    @Test
    fun shallowRow_withMultipleRows_indexInBounds() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.shallowRow(1)).isEqualTo(listOf("baz", "qux"))
    }

    @Test
    fun shallowRow_withMultipleRows_indexTooSmall() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThrows<IndexOutOfBoundsException> { grid.shallowRow(-1) }
    }

    @Test
    fun shallowRow_withMultipleRows_indexTooLarge() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThrows<IndexOutOfBoundsException> { grid.shallowRow(3) }
    }

    @Test
    fun shallowColumn_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        assertThrows<IndexOutOfBoundsException> { grid.shallowColumn(0) }
    }

    @Test
    fun shallowColumn_withSingleElement_indexInBounds() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.shallowColumn(0)).isEqualTo(listOf("foo"))
    }

    @Test
    fun shallowColumn_withSingleElement_indexTooSmall() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThrows<IndexOutOfBoundsException> { grid.shallowColumn(-1) }
    }

    @Test
    fun shallowColumn_withSingleElement_indexTooLarge() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThrows<IndexOutOfBoundsException> { grid.shallowColumn(1) }
    }

    @Test
    fun shallowColumn_withSingleRow_indexInBounds() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.shallowColumn(1)).isEqualTo(listOf("bar"))
    }

    @Test
    fun shallowColumn_withSingleRow_indexTooSmall() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThrows<IndexOutOfBoundsException> { grid.shallowColumn(-1) }
    }

    @Test
    fun shallowColumn_withSingleRow_indexTooLarge() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThrows<IndexOutOfBoundsException> { grid.shallowColumn(3) }
    }

    @Test
    fun shallowColumn_withMultipleRows_indexInBounds() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.shallowColumn(1)).isEqualTo(listOf("bar", "qux", "fred"))
    }

    @Test
    fun shallowColumn_withMultipleRows_indexTooSmall() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThrows<IndexOutOfBoundsException> { grid.shallowColumn(-1) }
    }

    @Test
    fun shallowColumn_withMultipleRows_indexTooLarge() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThrows<IndexOutOfBoundsException> { grid.shallowColumn(3) }
    }

    @Test
    fun shallowRows_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        assertThat(grid.shallowRows()).isEmpty()
    }

    @Test
    fun shallowRows_withSingleElement() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.shallowRows()).containsExactly(listOf("foo"))
    }

    @Test
    fun shallowRows_withSingleRow() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.shallowRows()).containsExactly(listOf("foo", "bar", "baz"))
    }

    @Test
    fun shallowRows_withMultipleRows() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.shallowRows())
            .containsExactly(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
    }

    @Test
    fun shallowColumns_whenEmpty() {
        val grid = GridImpl<Any>(emptyList())
        assertThat(grid.shallowColumns()).isEmpty()
    }

    @Test
    fun shallowColumns_withSingleElement() {
        val rowList = listOf(listOf("foo"))
        val grid = GridImpl(rowList)
        assertThat(grid.shallowColumns()).containsExactly(listOf("foo"))
    }

    @Test
    fun shallowColumns_withSingleRow() {
        val rowList = listOf(listOf("foo", "bar", "baz"))
        val grid = GridImpl(rowList)
        assertThat(grid.shallowColumns()).containsExactly(listOf("foo"), listOf("bar"), listOf("baz"))
    }

    @Test
    fun shallowColumns_withMultipleRows() {
        val rowList = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = GridImpl(rowList)
        assertThat(grid.shallowColumns())
            .containsExactly(listOf("foo", "baz", "qox"), listOf("bar", "qux", "fred"))
    }

    @Test
    fun constructor_zeroHeight_nonzeroWidth() {
        val height = 0
        val width = 1
        assertThrows<IllegalArgumentException> { Grid(height, width) { _, _ -> 0 } }
    }

    @Test
    fun constructor_nonzeroHeight_zeroWidth() {
        val height = 1
        val width = 0
        assertThrows<IllegalArgumentException> { Grid(height, width) { _, _ -> 0 } }
    }

    @Test
    fun constructor_negativeHeight() {
        val height = -1
        val width = 1
        assertThrows<IllegalArgumentException> { Grid(height, width) { _, _ -> 0 } }
    }

    @Test
    fun constructor_negativeWidth() {
        val height = 1
        val width = -1
        assertThrows<IllegalArgumentException> { Grid(height, width) { _, _ -> 0 } }
    }

    @Test
    fun constructor_noRows_noColumns() {
        val height = 0
        val width = 0
        val grid = Grid(height, width) { _, _ -> 0 }
        assertThat(grid).isEqualTo(emptyGrid<Int>())
    }

    @Test
    fun constructor_singleRow_singleColumn() {
        val height = 1
        val width = 1
        val grid = Grid(height, width) { _, _ -> 42 }
        assertThat(grid).isEqualTo(GridImpl(listOf(listOf(42))))
    }

    @Test
    fun constructor_singleRow_multipleColumns() {
        val height = 1
        val width = 3
        val row = listOf("foo", "bar", "baz")
        val grid = Grid(height, width) { _, colIndex -> row[colIndex] }
        assertThat(grid).isEqualTo(GridImpl(listOf(row)))
    }

    @Test
    fun constructor_multipleRows_singleColumn() {
        val height = 3
        val width = 1
        val rows = listOf(listOf("foo"), listOf("bar"), listOf("baz"))
        val grid = Grid(height, width) { rowIndex, _ -> rows[rowIndex][0] }
        assertThat(grid).isEqualTo(GridImpl(rows))
    }

    @Test
    fun constructor_multipleRows_multipleColumns() {
        val height = 3
        val width = 2
        val rows = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = Grid(height, width) { rowIndex, colIndex -> rows[rowIndex][colIndex] }
        assertThat(grid).isEqualTo(GridImpl(rows))
    }

    @Test
    fun gridOf_noRows() {
        val grid = gridOf<Any>()
        assertThat(grid).isEqualTo(emptyGrid<Any>())
    }

    @Test
    fun gridOf_singleRow() {
        val grid = gridOf(listOf("foo", "bar", "baz"))
        assertThat(grid).isEqualTo(GridImpl(listOf(listOf("foo", "bar", "baz"))))
    }

    @Test
    fun gridOf_multipleRows() {
        val grid = gridOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        assertThat(grid).isEqualTo(
            GridImpl(listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred")))
        )
    }

    @Test
    fun gridOf_differentSizeRows() {
        assertThrows<IllegalArgumentException> { gridOf(listOf("foo", "bar"), listOf("baz")) }
    }

    @Test
    fun toGrid_list_noRows() {
        val rows = mutableListOf<List<Any>>()
        val grid = rows.toGrid()
        rows.add(listOf())
        assertThat(grid).isEqualTo(emptyGrid<Any>())
    }

    @Test
    fun toGrid_list_singleRow() {
        val rows = listOf(mutableListOf("foo", "bar", "baz"))
        val grid = rows.toGrid()
        rows[0][0] = "qux"
        assertThat(grid).isEqualTo(GridImpl(listOf(listOf("foo", "bar", "baz"))))
    }

    @Test
    fun toGrid_list_multipleRows() {
        val rows = mutableListOf(
            mutableListOf("foo", "bar"),
            mutableListOf("baz", "qux"),
            mutableListOf("qox", "fred")
        )
        val grid = rows.toGrid()
        rows[0][0] = "oof"
        rows.removeLast()
        assertThat(grid).isEqualTo(
            GridImpl(listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred")))
        )
    }

    @Test
    fun toGrid_list_differentSizeRows() {
        val rows = listOf(listOf("foo", "bar"), listOf("baz"))
        assertThrows<IllegalArgumentException> { rows.toGrid() }
    }

    @Test
    fun toGrid_mutableGrid_noRows() {
        val mutableGrid = mutableGridOf<Any>()
        val grid = mutableGrid.toGrid()
        mutableGrid.addRow(listOf("foo"))
        assertThat(grid).isEqualTo(emptyGrid<Any>())
    }

    @Test
    fun toGrid_mutableGrid_singleRow() {
        val mutableGrid = mutableGridOf(listOf("foo", "bar", "baz"))
        val grid = mutableGrid.toGrid()
        mutableGrid[0, 0] = "qux"
        assertThat(grid).isEqualTo(GridImpl(listOf(listOf("foo", "bar", "baz"))))
    }

    @Test
    fun toGrid_mutableGrid_multipleRows() {
        val mutableGrid = mutableGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        val grid = mutableGrid.toGrid()
        mutableGrid[0, 0] = "oof"
        mutableGrid.removeLastRow()
        assertThat(grid).isEqualTo(
            GridImpl(listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred")))
        )
    }
}

/**
 * Sample implementation of an immutable grid with arbitrary values.
 *
 * @param E The type of values stored in the grid.
 * @property rowList The list of rows that make up the grid.
 *
 * @constructor Creates a new instance of [GridImpl] with the given [rowList].
 */
private class GridImpl<E>(private val rowList: List<List<E>>) : Grid<E> {
    override val height: Int = rowList.size

    override val width: Int = if (rowList.isEmpty()) 0 else rowList[0].size

    override fun get(rowIndex: Int, colIndex: Int): E = rowList[rowIndex][colIndex]

    override fun row(rowIndex: Int): List<E> = rowList[rowIndex]

    override fun column(colIndex: Int): List<E> {
        Objects.checkIndex(colIndex, width)
        return rowList.map { it[colIndex] }
    }

    override fun equals(other: Any?): Boolean = other is Grid<*> && rowList == other.rows()

    override fun hashCode(): Int =
        if (rowList.isEmpty()) emptyGrid<E>().hashCode() else rowList.hashCode()
}
