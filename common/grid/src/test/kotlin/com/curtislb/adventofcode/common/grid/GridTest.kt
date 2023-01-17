package com.curtislb.adventofcode.common.grid

import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.testing.assertContainsExactly
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotSame
import kotlin.test.assertNull
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [Grid].
 */
class GridTest {
    @Test
    fun testWhenEmpty() {
        val grid = gridOf<Int>()

        assertEquals(0, grid.width)
        assertEquals(0, grid.height)
        assertEquals(0, grid.size)

        assertEquals(-1, grid.lastRowIndex)
        assertEquals(-1, grid.lastColumnIndex)

        assertTrue(grid.rowIndices.isEmpty())
        assertTrue(grid.columnIndices.isEmpty())

        assertTrue(grid.isEmpty())
        assertEquals(emptyList(), grid.points().toList())
        assertFalse(Point.ORIGIN in grid)

        assertThrows<IndexOutOfBoundsException> { grid[0, 0] }
        assertThrows<IndexOutOfBoundsException> { grid[Point.ORIGIN] }

        assertNull(grid.getOrNull(0, 0))
        assertNull(grid.getOrNull(Point.ORIGIN))

        assertThrows<IndexOutOfBoundsException> { grid.row(0) }
        assertThrows<IndexOutOfBoundsException> { grid.column(0) }

        assertThrows<NoSuchElementException> { grid.firstRow() }
        assertThrows<NoSuchElementException> { grid.firstColumn() }
        assertThrows<NoSuchElementException> { grid.lastRow() }
        assertThrows<NoSuchElementException> { grid.lastColumn() }

        assertNull(grid.rowOrNull(0))
        assertNull(grid.columnOrNull(0))

        assertEquals(emptyList(), grid.rows())
        assertEquals(emptyList(), grid.columns())

        assertThrows<IndexOutOfBoundsException> { grid.shallowRow(0) }
        assertThrows<IndexOutOfBoundsException> { grid.shallowColumn(0) }

        assertEquals(emptyList(), grid.shallowRows())
        assertEquals(emptyList(), grid.shallowColumns())

        assertEquals("", grid.joinRowsToString { it.toString() })

        assertTrue(grid.flippedHorizontal().isEmpty())
        assertTrue(grid.rotatedLeft().isEmpty())
    }

    @Test
    fun testWithOneElement() {
        val grid = gridOf(listOf(42))

        assertEquals(1, grid.width)
        assertEquals(1, grid.height)
        assertEquals(1, grid.size)

        assertEquals(0, grid.lastRowIndex)
        assertEquals(0, grid.lastColumnIndex)

        assertEquals(0..0, grid.rowIndices)
        assertEquals(0..0, grid.columnIndices)

        assertFalse(grid.isEmpty())
        assertEquals(listOf(Point.ORIGIN), grid.points().toList())
        assertTrue(Point.ORIGIN in grid)
        assertFalse(Point(1, 0) in grid)
        assertFalse(Point(0, -1) in grid)

        assertEquals(42, grid[0, 0])
        assertEquals(42, grid[Point.ORIGIN])
        assertThrows<IndexOutOfBoundsException> { grid[1, 0] }
        assertThrows<IndexOutOfBoundsException> { grid[Point(0, -1)] }

        assertEquals(42, grid.getOrNull(0, 0))
        assertEquals(42, grid.getOrNull(Point.ORIGIN))
        assertNull(grid.getOrNull(-1, 0))
        assertNull(grid.getOrNull(Point(1, 0)))

        assertEquals(listOf(42), grid.row(0))
        assertEquals(listOf(42), grid.column(0))
        assertThrows<IndexOutOfBoundsException> { grid.row(1) }
        assertThrows<IndexOutOfBoundsException> { grid.column(1) }
        assertThrows<IndexOutOfBoundsException> { grid.row(-1) }
        assertThrows<IndexOutOfBoundsException> { grid.column(-1) }

        assertEquals(listOf(42), grid.firstRow())
        assertEquals(listOf(42), grid.firstColumn())
        assertEquals(listOf(42), grid.lastRow())
        assertEquals(listOf(42), grid.lastColumn())

        assertEquals(listOf(42), grid.rowOrNull(0))
        assertEquals(listOf(42), grid.columnOrNull(0))
        assertNull(grid.rowOrNull(1))
        assertNull(grid.columnOrNull(1))
        assertNull(grid.rowOrNull(-1))
        assertNull(grid.columnOrNull(-1))

        assertEquals(listOf(listOf(42)), grid.rows())
        assertEquals(listOf(listOf(42)), grid.columns())

        assertEquals(listOf(42), grid.shallowRow(0))
        assertEquals(listOf(42), grid.shallowColumn(0))
        assertThrows<IndexOutOfBoundsException> { grid.shallowRow(1) }
        assertThrows<IndexOutOfBoundsException> { grid.shallowColumn(1) }
        assertThrows<IndexOutOfBoundsException> { grid.shallowRow(-1) }
        assertThrows<IndexOutOfBoundsException> { grid.shallowColumn(-1) }

        assertEquals(listOf(listOf(42)), grid.shallowRows())
        assertEquals(listOf(listOf(42)), grid.shallowColumns())

        assertEquals("<[42]>", grid.joinRowsToString { "<$it>" })

        assertEquals(gridOf(listOf(42)), grid.flippedHorizontal())
        assertEquals(gridOf(listOf(42)), grid.rotatedLeft())
    }

    @Test
    fun testWithMultipleElements() {
        val grid = gridOf(
            listOf(10, 20, 28, -48),
            listOf(73, 34, -63, -67),
            listOf(-79, -60, 13, -55)
        )

        assertEquals(4, grid.width)
        assertEquals(3, grid.height)
        assertEquals(12, grid.size)

        assertEquals(2, grid.lastRowIndex)
        assertEquals(3, grid.lastColumnIndex)

        assertEquals(0..2, grid.rowIndices)
        assertEquals(0..3, grid.columnIndices)

        assertFalse(grid.isEmpty())
        assertContainsExactly(
            listOf(
                Point(0,  0), Point(1,  0), Point(2,  0), Point(3,  0),
                Point(0, -1), Point(1, -1), Point(2, -1), Point(3, -1),
                Point(0, -2), Point(1, -2), Point(2, -2), Point(3, -2)
            ),
            grid.points().toList()
        )
        assertTrue(Point.ORIGIN in grid)
        assertTrue(Point(3, 0) in grid)
        assertTrue(Point(0, -2) in grid)
        assertFalse(Point(4, 0) in grid)
        assertFalse(Point(0, -3) in grid)

        assertEquals(10, grid[0, 0])
        assertEquals(20, grid[0, 1])
        assertEquals(28, grid[0, 2])
        assertEquals(-48, grid[0, 3])
        assertEquals(73, grid[1, 0])
        assertEquals(34, grid[1, 1])
        assertEquals(-63, grid[1, 2])
        assertEquals(-67, grid[1, 3])
        assertEquals(-79, grid[2, 0])
        assertEquals(-60, grid[2, 1])
        assertEquals(13, grid[2, 2])
        assertEquals(-55, grid[2, 3])
        assertEquals(10, grid[Point.ORIGIN])
        assertEquals(20, grid[Point(1, 0)])
        assertEquals(28, grid[Point(2, 0)])
        assertEquals(-48, grid[Point(3, 0)])
        assertEquals(73, grid[Point(0, -1)])
        assertEquals(34, grid[Point(1, -1)])
        assertEquals(-63, grid[Point(2, -1)])
        assertEquals(-67, grid[Point(3, -1)])
        assertEquals(-79, grid[Point(0, -2)])
        assertEquals(-60, grid[Point(1, -2)])
        assertEquals(13, grid[Point(2, -2)])
        assertEquals(-55, grid[Point(3, -2)])
        assertThrows<IndexOutOfBoundsException> { grid[4, 0] }
        assertThrows<IndexOutOfBoundsException> { grid[Point(0, -3)] }

        assertEquals(10, grid.getOrNull(0, 0))
        assertEquals(-63, grid.getOrNull(1, 2))
        assertEquals(28, grid.getOrNull(Point(2, 0)))
        assertEquals(-60, grid.getOrNull(Point(1, -2)))
        assertNull(grid.getOrNull(-1, 0))
        assertNull(grid.getOrNull(Point(4, 0)))

        assertEquals(listOf(10, 20, 28, -48), grid.row(0))
        assertEquals(listOf(73, 34, -63, -67), grid.row(1))
        assertEquals(listOf(-79, -60, 13, -55), grid.row(2))
        assertEquals(listOf(10, 73, -79), grid.column(0))
        assertEquals(listOf(20, 34, -60), grid.column(1))
        assertEquals(listOf(28, -63, 13), grid.column(2))
        assertEquals(listOf(-48, -67, -55), grid.column(3))
        assertThrows<IndexOutOfBoundsException> { grid.row(3) }
        assertThrows<IndexOutOfBoundsException> { grid.column(4) }
        assertThrows<IndexOutOfBoundsException> { grid.row(-1) }
        assertThrows<IndexOutOfBoundsException> { grid.column(-1) }

        assertEquals(listOf(10, 20, 28, -48), grid.firstRow())
        assertEquals(listOf(10, 73, -79), grid.firstColumn())
        assertEquals(listOf(-79, -60, 13, -55), grid.lastRow())
        assertEquals(listOf(-48, -67, -55), grid.lastColumn())

        assertEquals(listOf(10, 20, 28, -48), grid.rowOrNull(0))
        assertEquals(listOf(73, 34, -63, -67), grid.rowOrNull(1))
        assertEquals(listOf(-79, -60, 13, -55), grid.rowOrNull(2))
        assertEquals(listOf(10, 73, -79), grid.columnOrNull(0))
        assertEquals(listOf(20, 34, -60), grid.columnOrNull(1))
        assertEquals(listOf(28, -63, 13), grid.columnOrNull(2))
        assertEquals(listOf(-48, -67, -55), grid.columnOrNull(3))
        assertNull(grid.rowOrNull(3))
        assertNull(grid.columnOrNull(4))
        assertNull(grid.rowOrNull(-1))
        assertNull(grid.columnOrNull(-1))

        assertEquals(
            listOf(
                listOf(10, 20, 28, -48),
                listOf(73, 34, -63, -67),
                listOf(-79, -60, 13, -55)
            ),
            grid.rows()
        )
        assertEquals(
            listOf(
                listOf(10, 73, -79),
                listOf(20, 34, -60),
                listOf(28, -63, 13),
                listOf(-48, -67, -55)
            ),
            grid.columns()
        )

        assertEquals(listOf(10, 20, 28, -48), grid.shallowRow(0))
        assertEquals(listOf(73, 34, -63, -67), grid.shallowRow(1))
        assertEquals(listOf(-79, -60, 13, -55), grid.shallowRow(2))
        assertEquals(listOf(10, 73, -79), grid.shallowColumn(0))
        assertEquals(listOf(20, 34, -60), grid.shallowColumn(1))
        assertEquals(listOf(28, -63, 13), grid.shallowColumn(2))
        assertEquals(listOf(-48, -67, -55), grid.shallowColumn(3))
        assertThrows<IndexOutOfBoundsException> { grid.shallowRow(3) }
        assertThrows<IndexOutOfBoundsException> { grid.shallowColumn(4) }
        assertThrows<IndexOutOfBoundsException> { grid.shallowRow(-1) }
        assertThrows<IndexOutOfBoundsException> { grid.shallowColumn(-1) }

        assertEquals(
            listOf(
                listOf(10, 20, 28, -48),
                listOf(73, 34, -63, -67),
                listOf(-79, -60, 13, -55)
            ),
            grid.shallowRows()
        )
        assertEquals(
            listOf(
                listOf(10, 73, -79),
                listOf(20, 34, -60),
                listOf(28, -63, 13),
                listOf(-48, -67, -55)
            ),
            grid.shallowColumns()
        )

        assertEquals(
            "[10, 20, 28, -48] + [73, 34, -63, -67] + [-79, -60, 13, -55]",
            grid.joinRowsToString(separator = " + ") { it.toString() }
        )

        assertEquals(
            gridOf(
                listOf(-48, 28, 20, 10),
                listOf(-67, -63, 34, 73),
                listOf(-55, 13, -60, -79)
            ),
            grid.flippedHorizontal()
        )
        assertEquals(
            gridOf(
                listOf(-48, -67, -55),
                listOf(28, -63, 13),
                listOf(20, 34, -60),
                listOf(10, 73, -79)
            ),
            grid.rotatedLeft()
        )
    }

    @Test
    fun testGridOfInvalidRows() {
        assertThrows<IllegalArgumentException> { gridOf(emptyList<Nothing>()) }
        assertThrows<IllegalArgumentException> { gridOf(listOf("a"), emptyList()) }
        assertThrows<IllegalArgumentException> { gridOf(listOf(1), listOf(2, 3)) }
        assertThrows<IllegalArgumentException> {
            gridOf(listOf(-37, 94, -76), listOf(3, 21, -80), listOf(22, 10))
        }
        assertThrows<IllegalArgumentException> {
            gridOf(
                listOf(-89, -76, 26, 36),
                listOf(48, 82, -16, 5),
                listOf(40, 44, -42, 2, -44, 99),
                listOf(-9, 66, 43, 29)
            )
        }
    }

    @Test
    fun testGridOfCopiesList() {
        val list = mutableListOf(1, 2)
        val grid = gridOf(list)

        list[0] = 3
        list[1] = 4
        list.add(5)

        assertEquals(gridOf(listOf(1, 2)), grid)
    }

    @Test
    fun testFakeConstructor() {
        assertEquals(emptyGrid(), Grid(0, 0) { _, _ -> 42 })
        assertThrows<IllegalArgumentException> { Grid(0, 1) { _, _ -> 42 } }
        assertThrows<IllegalArgumentException> { Grid(2, 0) { _, _ -> 42 } }
        assertThrows<IllegalArgumentException> { Grid(-1, 3) { _, _ -> 42 } }
        assertThrows<IllegalArgumentException> { Grid(0, -1) { _, _ -> 42 } }
        assertEquals(gridOf(listOf(42)), Grid(1, 1) { _, _ -> 42 })
        assertEquals(
            gridOf(
                listOf(0, 1, 2, 3, 4),
                listOf(10, 11, 12, 13, 14),
                listOf(20, 21, 22, 23, 24),
                listOf(30, 31, 32, 33, 34),
                listOf(40, 41, 42, 43, 44),
                listOf(50, 51, 52, 53, 54)
            ),
            Grid(6, 5) { rowIndex, colIndex -> rowIndex * 10 + colIndex }
        )
    }

    @Test
    fun testListToGrid() {
        assertEquals(emptyGrid<Nothing>(), emptyList<Nothing>().toGrid())
        assertEquals(gridOf(listOf(42)), listOf(listOf(42)).toGrid())
        assertThrows<IllegalArgumentException> { listOf(listOf(5), listOf(6, 7)).toGrid() }
        assertEquals(
            gridOf(
                listOf(-89, 17, 9, -6, -9),
                listOf(-60, -11, 47, 65, -86),
                listOf(-77, -12, 45, -12, 2),
                listOf(-3, 74, -43, 2, -46)
            ),
            listOf(
                listOf(-89, 17, 9, -6, -9),
                listOf(-60, -11, 47, 65, -86),
                listOf(-77, -12, 45, -12, 2),
                listOf(-3, 74, -43, 2, -46)
            ).toGrid()
        )
    }

    @Test
    fun testToGridCopiesList() {
        val list = mutableListOf(mutableListOf(1, 2))
        val grid = list.toGrid()

        list[0][0] = 3
        list[0][1] = 4
        list.add(mutableListOf(5, 6))

        assertEquals(gridOf(listOf(1, 2)), grid)
    }

    @Test
    fun testMutableGridToGrid() {
        var mutableGrid = mutableGridOf<Int>()
        assertEquals(emptyGrid(), mutableGrid.toGrid())
        assertNotSame(mutableGrid, mutableGrid.toGrid())

        mutableGrid = mutableGridOf(listOf(42))
        assertEquals(gridOf(listOf(42)), mutableGrid.toGrid())
        assertNotSame(mutableGrid, mutableGrid.toGrid())

        mutableGrid = mutableGridOf(
            listOf(20, 37, 63, -69, -2),
            listOf(6, -10, -71, 58, 94),
            listOf(-19, -73, 10, -30, 37),
            listOf(95, 84, -28, -14, 5)
        )
        assertEquals(
            gridOf(
                listOf(20, 37, 63, -69, -2),
                listOf(6, -10, -71, 58, 94),
                listOf(-19, -73, 10, -30, 37),
                listOf(95, 84, -28, -14, 5)
            ),
            mutableGrid.toGrid()
        )
        assertNotSame(mutableGrid, mutableGrid.toGrid())
    }

    @Test
    fun testToGridCopiesMutableGrid() {
        val mutableGrid = mutableGridOf(mutableListOf(1, 2))
        val grid = mutableGrid.toGrid()

        mutableGrid[0, 0] = 3
        mutableGrid[0, 1] = 4
        mutableGrid.addRow(mutableListOf(5, 6))

        assertEquals(gridOf(listOf(1, 2)), grid)
    }
}
