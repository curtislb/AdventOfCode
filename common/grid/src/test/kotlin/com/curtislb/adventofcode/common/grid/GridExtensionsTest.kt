package com.curtislb.adventofcode.common.grid

import com.curtislb.adventofcode.common.geometry.Point
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests various [Grid] extension functions.
 */
class GridExtensionsTest {
    @Test
    fun testAddRowWith() {
        var grid = mutableGridOf<Int>().apply { addRowWith { add(1) } }
        assertEquals(gridOf(listOf(1)), grid)

        grid = mutableGridOf<Int>().apply { addRowWith { addAll(listOf(1, 2, 3)) } }
        assertEquals(gridOf(listOf(1, 2, 3)), grid)

        grid = mutableGridOf(listOf(4, 5)).apply {
            addRowWith {
                addAll(listOf(6, 7))
                removeLast()
                add(8)
            }
        }
        assertEquals(gridOf(listOf(4, 5), listOf(6, 8)), grid)
    }

    @Test
    fun testForEachIndexed() {
        val map = mutableMapOf<Int, String>()
        val action: (Int, Int, String) -> Unit = { rowIndex, colIndex, value ->
            map[rowIndex * 10 + colIndex] = value
        }
        emptyGrid<String>().forEachIndexed(action)
        assertEquals(emptyMap(), map)

        map.clear()
        gridOf(listOf("foo")).forEachIndexed(action)
        assertEquals(mapOf(0 to "foo"), map)

        map.clear()
        gridOf(listOf("foo", "bar")).forEachIndexed(action)
        assertEquals(mapOf(0 to "foo", 1 to "bar"), map)

        map.clear()
        gridOf(listOf("foo", "bar"), listOf("baz", "qux")).forEachIndexed(action)
        assertEquals(mapOf(0 to "foo", 1 to "bar", 10 to "baz", 11 to "qux"), map)
    }

    @Test
    fun testForEachPointValue() {
        val map = mutableMapOf<Point, String>()
        val action: (Point, String) -> Unit = { point, value -> map[point] = value }
        emptyGrid<String>().forEachPointValue(action)
        assertEquals(emptyMap(), map)

        map.clear()
        gridOf(listOf("foo")).forEachPointValue(action)
        assertEquals(mapOf(Point.ORIGIN to "foo"), map)

        map.clear()
        gridOf(listOf("foo", "bar")).forEachPointValue(action)
        assertEquals(mapOf(Point.ORIGIN to "foo", Point(1, 0) to "bar"), map)

        map.clear()
        gridOf(listOf("foo", "bar"), listOf("baz", "qux")).forEachPointValue(action)
        assertEquals(
            mapOf(
                Point.ORIGIN to "foo",
                Point(1, 0) to "bar",
                Point(0, -1) to "baz",
                Point(1, -1) to "qux"
            ),
            map
        )
    }

    @Test
    fun testMapIndexed() {
        val transform: (Int, Int, String) -> Pair<Int, String> = { rowIndex, colIndex, value ->
            Pair(rowIndex * 10 + colIndex, value)
        }

        assertEquals(emptyGrid(), emptyGrid<String>().mapIndexed(transform))
        assertEquals(gridOf(listOf(0 to "foo")), gridOf(listOf("foo")).mapIndexed(transform))
        assertEquals(
            gridOf(listOf(0 to "foo", 1 to "bar")),
            gridOf(listOf("foo", "bar")).mapIndexed(transform)
        )
        assertEquals(
            gridOf(
                listOf(0 to "foo", 1 to "bar"),
                listOf(10 to "baz", 11 to "qux")
            ),
            gridOf(listOf("foo", "bar"), listOf("baz", "qux")).mapIndexed(transform)
        )
    }

    @Test
    fun testMapPointValues() {
        val transform: (Point, String) -> Pair<Point, String> = { point, value ->
            Pair(point, value)
        }

        assertEquals(emptyGrid(), emptyGrid<String>().mapPointValues(transform))
        assertEquals(
            gridOf(listOf(Point.ORIGIN to "foo")),
            gridOf(listOf("foo")).mapPointValues(transform)
        )
        assertEquals(
            gridOf(listOf(Point.ORIGIN to "foo", Point(1, 0) to "bar")),
            gridOf(listOf("foo", "bar")).mapPointValues(transform)
        )
        assertEquals(
            gridOf(
                listOf(Point.ORIGIN to "foo", Point(1, 0) to "bar"),
                listOf(Point(0, -1) to "baz", Point(1, -1) to "qux")
            ),
            gridOf(listOf("foo", "bar"), listOf("baz", "qux")).mapPointValues(transform)
        )
    }

    @Test
    fun testSumRowsBy() {
        assertEquals(0, emptyGrid<Int>().sumRowsBy { 1 })
        assertEquals(-42, gridOf(listOf(42)).sumRowsBy { -it.sum() })
        assertEquals(6, gridOf(listOf(1, 2, 3)).sumRowsBy { it.sum() })
        assertEquals(
            14,
            gridOf(listOf(1, 2, 3)).sumRowsBy { row -> row.sumOf { value -> value * value } }
        )
        assertEquals(
            -76,
            gridOf(
                listOf(-25, -1, -84, -83),
                listOf(44, 12, -2, -57),
                listOf(32, 98, -7, -3)
            ).sumRowsBy { it.sum() }
        )
        assertEquals(
            6,
            gridOf(
                listOf(-25, -1, -84, -83),
                listOf(44, 12, -2, -57),
                listOf(32, 98, -7, -3)
            ).sumRowsBy { row -> row.count { value -> value % 2 == 0 } }
        )
    }
}
