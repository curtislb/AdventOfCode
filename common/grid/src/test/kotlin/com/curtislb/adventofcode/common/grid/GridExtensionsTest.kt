package com.curtislb.adventofcode.common.grid

import com.curtislb.adventofcode.common.geometry.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests various [Grid] extension functions.
 */
class GridExtensionsTest {
    @Test
    fun forEachIndexed_whenEmpty() {
        val grid = emptyGrid<Any>()

        val map = mutableMapOf<Pair<Int, Int>, Any>()
        grid.forEachIndexed { rowIndex, colIndex, value -> map[Pair(rowIndex, colIndex)] = value }

        assertThat(map).isEmpty()
    }

    @Test
    fun forEachIndexed_withSingleElement() {
        val grid = gridOf(listOf("foo"))

        val map = mutableMapOf<Pair<Int, Int>, String>()
        grid.forEachIndexed { rowIndex, colIndex, value -> map[Pair(rowIndex, colIndex)] = value }

        assertThat(map).containsExactlyEntriesOf(mapOf(Pair(0, 0) to "foo"))
    }

    @Test
    fun forEachIndexed_withSingleRow() {
        val grid = gridOf(listOf("foo", "bar", "baz"))

        val map = mutableMapOf<Pair<Int, Int>, String>()
        grid.forEachIndexed { rowIndex, colIndex, value -> map[Pair(rowIndex, colIndex)] = value }

        assertThat(map).containsExactlyInAnyOrderEntriesOf(
            mapOf(Pair(0, 0) to "foo", Pair(0, 1) to "bar", Pair(0, 2) to "baz")
        )
    }

    @Test
    fun forEachIndexed_withMultipleRows() {
        val grid = gridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )

        val map = mutableMapOf<Pair<Int, Int>, String>()
        grid.forEachIndexed { rowIndex, colIndex, value -> map[Pair(rowIndex, colIndex)] = value }

        assertThat(map).containsExactlyInAnyOrderEntriesOf(
            mapOf(
                Pair(0, 0) to "foo",
                Pair(0, 1) to "bar",
                Pair(1, 0) to "baz",
                Pair(1, 1) to "qux",
                Pair(2, 0) to "qox",
                Pair(2, 1) to "fred"
            )
        )
    }

    @Test
    fun forEachPointValue_whenEmpty() {
        val grid = emptyGrid<Any>()

        val map = mutableMapOf<Point, Any>()
        grid.forEachPointValue { point, value -> map[point] = value }

        assertThat(map).isEmpty()
    }

    @Test
    fun forEachPointValue_withSingleElement() {
        val grid = gridOf(listOf("foo"))
        val map = mutableMapOf<Point, String>()
        grid.forEachPointValue { point, value -> map[point] = value }
        assertThat(map).containsExactlyEntriesOf(mapOf(Point(0, 0) to "foo"))
    }

    @Test
    fun forEachPointValue_withSingleRow() {
        val grid = gridOf(listOf("foo", "bar", "baz"))
        val map = mutableMapOf<Point, String>()
        grid.forEachPointValue { point, value -> map[point] = value }
        assertThat(map).containsExactlyInAnyOrderEntriesOf(
            mapOf(Point(0, 0) to "foo", Point(1, 0) to "bar", Point(2, 0) to "baz")
        )
    }

    @Test
    fun forEachPointValue_withMultipleRows() {
        val grid = gridOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val map = mutableMapOf<Point, String>()
        grid.forEachPointValue { point, value -> map[point] = value }
        assertThat(map).containsExactlyInAnyOrderEntriesOf(
            mapOf(
                Point(0, 0) to "foo",
                Point(1, 0) to "bar",
                Point(0, -1) to "baz",
                Point(1, -1) to "qux",
                Point(0, -2) to "qox",
                Point(1, -2) to "fred"
            )
        )
    }

    @Test
    fun mapIndexed_whenEmpty() {
        val grid = emptyGrid<Any>()
        val mapped = grid.mapIndexed { _, _, value -> value }
        assertThat(mapped).isEqualTo(emptyGrid<Any>())
    }

    @Test
    fun mapIndexed_withSingleElement() {
        val grid = gridOf(listOf("foo"))
        val mapped = grid.mapIndexed { rowIndex, colIndex, value -> "$rowIndex,$colIndex:$value" }
        assertThat(mapped).isEqualTo(gridOf(listOf("0,0:foo")))
    }

    @Test
    fun mapIndexed_withSingleRow() {
        val grid = gridOf(listOf("foo", "bar", "baz"))
        val mapped = grid.mapIndexed { rowIndex, colIndex, value -> "$rowIndex,$colIndex:$value" }
        assertThat(mapped).isEqualTo(gridOf(listOf("0,0:foo", "0,1:bar", "0,2:baz")))
    }

    @Test
    fun mapIndexed_withMultipleRows() {
        val grid = gridOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val mapped = grid.mapIndexed { rowIndex, colIndex, value -> "$rowIndex,$colIndex:$value" }
        assertThat(mapped).isEqualTo(
            gridOf(
                listOf("0,0:foo", "0,1:bar"),
                listOf("1,0:baz", "1,1:qux"),
                listOf("2,0:qox", "2,1:fred")
            )
        )
    }

    @Test
    fun mapPointValues_whenEmpty() {
        val grid = emptyGrid<Any>()
        val mapped = grid.mapPointValues { point, value -> Pair(point, value) }
        assertThat(mapped).isEqualTo(emptyGrid<Pair<Point, Any>>())
    }

    @Test
    fun mapPointValues_withSingleElement() {
        val grid = gridOf(listOf("foo"))
        val mapped = grid.mapPointValues { point, value -> Pair(point, value) }
        assertThat(mapped).isEqualTo(gridOf(listOf(Point(0, 0) to "foo")))
    }

    @Test
    fun mapPointValues_withSingleRow() {
        val grid = gridOf(listOf("foo", "bar", "baz"))
        val mapped = grid.mapPointValues { point, value -> Pair(point, value) }
        assertThat(mapped).isEqualTo(
            gridOf(listOf(Point(0, 0) to "foo", Point(1, 0) to "bar", Point(2, 0) to "baz"))
        )
    }

    @Test
    fun mapPointValues_withMultipleRows() {
        val grid = gridOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val mapped = grid.mapPointValues { point, value -> Pair(point, value) }
        assertThat(mapped).isEqualTo(
            gridOf(
                listOf(Point(0, 0) to "foo", Point(1, 0) to "bar"),
                listOf(Point(0, -1) to "baz", Point(1, -1) to "qux"),
                listOf(Point(0, -2) to "qox", Point(1, -2) to "fred")
            )
        )
    }

    @Test
    fun mapRow_whenEmpty() {
        val grid = emptyGrid<Any>()
        assertThrows<IndexOutOfBoundsException> { grid.mapRow(0) { it } }
    }

    @Test
    fun mapRow_withSingleElement() {
        val grid = gridOf(listOf("foo"))
        assertThat(grid.mapRow(0) { it.reversed() }).containsExactly("oof")
    }

    @Test
    fun mapRow_withSingleRow() {
        val grid = gridOf(listOf("foo", "bar", "baz"))
        assertThat(grid.mapRow(0) { it.reversed() }).containsExactly("oof", "rab", "zab")
    }

    @Test
    fun mapRow_withMultipleRows_rowIndexInBounds() {
        val grid = gridOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        assertThat(grid.mapRow(1) { it.reversed() }).containsExactly("zab", "xuq")
    }

    @Test
    fun mapRow_withMultipleRows_rowIndexTooSmall() {
        val grid = gridOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        assertThrows<IndexOutOfBoundsException> { grid.mapRow(-1) { it.reversed() } }
    }

    @Test
    fun mapRow_withMultipleRows_rowIndexTooLarge() {
        val grid = gridOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        assertThrows<IndexOutOfBoundsException> { grid.mapRow(3) { it.reversed() } }
    }

    @Test
    fun sumRowsBy_whenEmpty() {
        val grid = emptyGrid<Any>()
        assertThat(grid.sumRowsBy { 1 }).isEqualTo(0)
    }

    @Test
    fun sumRowsBy_withSingleElement() {
        val grid = gridOf(listOf(42))
        assertThat(grid.sumRowsBy { it.sum() }).isEqualTo(42)
    }

    @Test
    fun sumRowsBy_withSingleRow() {
        val grid = gridOf(listOf(1, 2, 3))
        assertThat(grid.sumRowsBy { -it.sum() }).isEqualTo(-6)
    }

    @Test
    fun sumRowsBy_withMultipleRows() {
        val grid = gridOf(
            listOf(-25, -1, 84, 83),
            listOf(44, 12, -2, -57),
            listOf(32, 98, -7, -3)
        )
        assertThat(grid.sumRowsBy { it.sum() }).isEqualTo(258)
    }

    @Test
    fun joinRowsToString_whenEmpty_noSeparator_noTransform() {
        val grid = emptyGrid<Any>()
        assertThat(grid.joinRowsToString()).isEmpty()
    }

    @Test
    fun joinRowsToString_whenEmpty_noSeparator_withTransform() {
        val grid = emptyGrid<Any>()
        assertThat(grid.joinRowsToString { it.reversed().toString() }).isEmpty()
    }

    @Test
    fun joinRowsToString_whenEmpty_withSeparator_noTransform() {
        val grid = emptyGrid<Any>()
        assertThat(grid.joinRowsToString(separator = " | ")).isEmpty()
    }

    @Test
    fun joinRowsToString_whenEmpty_withSeparator_withTransform() {
        val grid = emptyGrid<Any>()
        assertThat(grid.joinRowsToString(separator = " | ") { it.reversed().toString() }).isEmpty()
    }

    @Test
    fun joinRowsToString_withSingleRow_noSeparator_noTransform() {
        val grid = gridOf(listOf(1, 2, 3))
        assertThat(grid.joinRowsToString()).isEqualTo("[1, 2, 3]")
    }

    @Test
    fun joinRowsToString_withSingleRow_noSeparator_withTransform() {
        val grid = gridOf(listOf(1, 2, 3))
        assertThat(grid.joinRowsToString { it.joinToString() }).isEqualTo("1, 2, 3")
    }

    @Test
    fun joinRowsToString_withSingleRow_withSeparator_noTransform() {
        val grid = gridOf(listOf(1, 2, 3))
        assertThat(grid.joinRowsToString(separator = " | ")).isEqualTo("[1, 2, 3]")
    }

    @Test
    fun joinRowsToString_withSingleRow_withSeparator_withTransform() {
        val grid = gridOf(listOf(1, 2, 3))
        assertThat(grid.joinRowsToString(separator = " | ") { it.joinToString() })
            .isEqualTo("1, 2, 3")
    }

    @Test
    fun joinRowsToString_withMultipleRows_noSeparator_noTransform() {
        val grid = gridOf(listOf(1, 2), listOf(3, 4), listOf(5, 6))
        assertThat(grid.joinRowsToString()).isEqualTo("[1, 2], [3, 4], [5, 6]")
    }

    @Test
    fun joinRowsToString_withMultipleRows_noSeparator_withTransform() {
        val grid = gridOf(listOf(1, 2), listOf(3, 4), listOf(5, 6))
        assertThat(grid.joinRowsToString { it.joinToString() }).isEqualTo("1, 2, 3, 4, 5, 6")
    }

    @Test
    fun joinRowsToString_withMultipleRows_withSeparator_noTransform() {
        val grid = gridOf(listOf(1, 2), listOf(3, 4), listOf(5, 6))
        assertThat(grid.joinRowsToString(separator = " | ")).isEqualTo("[1, 2] | [3, 4] | [5, 6]")
    }

    @Test
    fun joinRowsToString_withMultipleRows_withSeparator_withTransform() {
        val grid = gridOf(listOf(1, 2), listOf(3, 4), listOf(5, 6))
        assertThat(grid.joinRowsToString(separator = " | ") { it.joinToString() })
            .isEqualTo("1, 2 | 3, 4 | 5, 6")
    }

    @Test
    fun horizontalReflection_whenEmpty() {
        val grid = mutableGridOf<Any>()
        assertThat(grid.horizontalReflection()).isEqualTo(emptyGrid<Any>()).isNotSameAs(grid)
    }

    @Test
    fun horizontalReflection_withSingleElement() {
        val grid = mutableGridOf(listOf("foo"))
        assertThat(grid.horizontalReflection()).isEqualTo(gridOf(listOf("foo"))).isNotSameAs(grid)
    }

    @Test
    fun horizontalReflection_withSingleRow() {
        val grid = mutableGridOf(listOf("foo", "bar", "baz"))
        assertThat(grid.horizontalReflection())
            .isEqualTo(gridOf(listOf("baz", "bar", "foo")))
            .isNotSameAs(grid)
    }

    @Test
    fun horizontalReflection_withMultipleRows() {
        val grid = mutableGridOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        assertThat(grid.horizontalReflection())
            .isEqualTo(gridOf(listOf("bar", "foo"), listOf("qux", "baz"), listOf("fred", "qox")))
            .isNotSameAs(grid)
    }

    @Test
    fun leftRotation_whenEmpty() {
        val grid = mutableGridOf<Any>()
        assertThat(grid.leftRotation()).isEqualTo(emptyGrid<Any>()).isNotSameAs(grid)
    }

    @Test
    fun leftRotation_withSingleElement() {
        val grid = mutableGridOf(listOf("foo"))
        assertThat(grid.leftRotation()).isEqualTo(gridOf(listOf("foo"))).isNotSameAs(grid)
    }

    @Test
    fun leftRotation_withSingleRow() {
        val grid = mutableGridOf(listOf("foo", "bar", "baz"))
        assertThat(grid.leftRotation())
            .isEqualTo(gridOf(listOf("baz"), listOf("bar"), listOf("foo")))
            .isNotSameAs(grid)
    }

    @Test
    fun leftRotation_withMultipleRows() {
        val grid = mutableGridOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        assertThat(grid.leftRotation())
            .isEqualTo(gridOf(listOf("bar", "qux", "fred"), listOf("foo", "baz", "qox")))
            .isNotSameAs(grid)
    }
}
