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
 * Tests [MutableGrid].
 */
class MutableGridTest {
    @Test
    fun testWhenEmpty() {
        val mutableGrid = mutableGridOf<Int>()

        assertEquals(0, mutableGrid.width)
        assertEquals(0, mutableGrid.height)
        assertEquals(0, mutableGrid.size)

        assertEquals(-1, mutableGrid.lastRowIndex)
        assertEquals(-1, mutableGrid.lastColumnIndex)

        assertTrue(mutableGrid.rowIndices.isEmpty())
        assertTrue(mutableGrid.columnIndices.isEmpty())

        assertTrue(mutableGrid.isEmpty())
        assertEquals(emptyList(), mutableGrid.points().toList())
        assertFalse(Point.ORIGIN in mutableGrid)

        assertThrows<IndexOutOfBoundsException> { mutableGrid[0, 0] }
        assertThrows<IndexOutOfBoundsException> { mutableGrid[Point.ORIGIN] }

        assertNull(mutableGrid.getOrNull(0, 0))
        assertNull(mutableGrid.getOrNull(Point.ORIGIN))

        assertThrows<IndexOutOfBoundsException> { mutableGrid.row(0) }
        assertThrows<IndexOutOfBoundsException> { mutableGrid.column(0) }

        assertThrows<NoSuchElementException> { mutableGrid.firstRow() }
        assertThrows<NoSuchElementException> { mutableGrid.firstColumn() }
        assertThrows<NoSuchElementException> { mutableGrid.lastRow() }
        assertThrows<NoSuchElementException> { mutableGrid.lastColumn() }

        assertNull(mutableGrid.rowOrNull(0))
        assertNull(mutableGrid.columnOrNull(0))

        assertEquals(emptyList(), mutableGrid.rows())
        assertEquals(emptyList(), mutableGrid.columns())

        assertThrows<IndexOutOfBoundsException> { mutableGrid.shallowRow(0) }
        assertThrows<IndexOutOfBoundsException> { mutableGrid.shallowColumn(0) }

        assertEquals(emptyList(), mutableGrid.shallowRows())
        assertEquals(emptyList(), mutableGrid.shallowColumns())

        assertEquals("", mutableGrid.joinRowsToString { it.toString() })

        assertTrue(mutableGrid.flippedHorizontal().isEmpty())
        assertTrue(mutableGrid.rotatedLeft().isEmpty())
    }

    @Test
    fun testWithOneElement() {
        val mutableGrid = mutableGridOf(listOf(42))

        assertEquals(1, mutableGrid.width)
        assertEquals(1, mutableGrid.height)
        assertEquals(1, mutableGrid.size)

        assertEquals(0, mutableGrid.lastRowIndex)
        assertEquals(0, mutableGrid.lastColumnIndex)

        assertEquals(0..0, mutableGrid.rowIndices)
        assertEquals(0..0, mutableGrid.columnIndices)

        assertFalse(mutableGrid.isEmpty())
        assertEquals(listOf(Point.ORIGIN), mutableGrid.points().toList())
        assertTrue(Point.ORIGIN in mutableGrid)
        assertFalse(Point(1, 0) in mutableGrid)
        assertFalse(Point(0, -1) in mutableGrid)

        assertEquals(42, mutableGrid[0, 0])
        assertEquals(42, mutableGrid[Point.ORIGIN])
        assertThrows<IndexOutOfBoundsException> { mutableGrid[1, 0] }
        assertThrows<IndexOutOfBoundsException> { mutableGrid[Point(0, -1)] }

        assertEquals(42, mutableGrid.getOrNull(0, 0))
        assertEquals(42, mutableGrid.getOrNull(Point.ORIGIN))
        assertNull(mutableGrid.getOrNull(-1, 0))
        assertNull(mutableGrid.getOrNull(Point(1, 0)))

        assertEquals(listOf(42), mutableGrid.row(0))
        assertEquals(listOf(42), mutableGrid.column(0))
        assertThrows<IndexOutOfBoundsException> { mutableGrid.row(1) }
        assertThrows<IndexOutOfBoundsException> { mutableGrid.column(1) }
        assertThrows<IndexOutOfBoundsException> { mutableGrid.row(-1) }
        assertThrows<IndexOutOfBoundsException> { mutableGrid.column(-1) }

        assertEquals(listOf(42), mutableGrid.firstRow())
        assertEquals(listOf(42), mutableGrid.firstColumn())
        assertEquals(listOf(42), mutableGrid.lastRow())
        assertEquals(listOf(42), mutableGrid.lastColumn())

        assertEquals(listOf(42), mutableGrid.rowOrNull(0))
        assertEquals(listOf(42), mutableGrid.columnOrNull(0))
        assertNull(mutableGrid.rowOrNull(1))
        assertNull(mutableGrid.columnOrNull(1))
        assertNull(mutableGrid.rowOrNull(-1))
        assertNull(mutableGrid.columnOrNull(-1))

        assertEquals(listOf(listOf(42)), mutableGrid.rows())
        assertEquals(listOf(listOf(42)), mutableGrid.columns())

        assertEquals(listOf(42), mutableGrid.shallowRow(0))
        assertEquals(listOf(42), mutableGrid.shallowColumn(0))
        assertThrows<IndexOutOfBoundsException> { mutableGrid.shallowRow(1) }
        assertThrows<IndexOutOfBoundsException> { mutableGrid.shallowColumn(1) }
        assertThrows<IndexOutOfBoundsException> { mutableGrid.shallowRow(-1) }
        assertThrows<IndexOutOfBoundsException> { mutableGrid.shallowColumn(-1) }

        assertEquals(listOf(listOf(42)), mutableGrid.shallowRows())
        assertEquals(listOf(listOf(42)), mutableGrid.shallowColumns())

        assertEquals("<[42]>", mutableGrid.joinRowsToString { "<$it>" })

        assertEquals(gridOf(listOf(42)), mutableGrid.flippedHorizontal())
        assertEquals(gridOf(listOf(42)), mutableGrid.rotatedLeft())
    }

    @Test
    fun testWithMultipleElements() {
        val mutableGrid = mutableGridOf(
            listOf(10, 20, 28, -48),
            listOf(73, 34, -63, -67),
            listOf(-79, -60, 13, -55)
        )

        assertEquals(4, mutableGrid.width)
        assertEquals(3, mutableGrid.height)
        assertEquals(12, mutableGrid.size)

        assertEquals(2, mutableGrid.lastRowIndex)
        assertEquals(3, mutableGrid.lastColumnIndex)

        assertEquals(0..2, mutableGrid.rowIndices)
        assertEquals(0..3, mutableGrid.columnIndices)

        assertFalse(mutableGrid.isEmpty())
        assertContainsExactly(
            listOf(
                Point(0,  0), Point(1,  0), Point(2,  0), Point(3,  0),
                Point(0, -1), Point(1, -1), Point(2, -1), Point(3, -1),
                Point(0, -2), Point(1, -2), Point(2, -2), Point(3, -2)
            ),
            mutableGrid.points().toList()
        )
        assertTrue(Point.ORIGIN in mutableGrid)
        assertTrue(Point(3, 0) in mutableGrid)
        assertTrue(Point(0, -2) in mutableGrid)
        assertFalse(Point(4, 0) in mutableGrid)
        assertFalse(Point(0, -3) in mutableGrid)

        assertEquals(10, mutableGrid[0, 0])
        assertEquals(20, mutableGrid[0, 1])
        assertEquals(28, mutableGrid[0, 2])
        assertEquals(-48, mutableGrid[0, 3])
        assertEquals(73, mutableGrid[1, 0])
        assertEquals(34, mutableGrid[1, 1])
        assertEquals(-63, mutableGrid[1, 2])
        assertEquals(-67, mutableGrid[1, 3])
        assertEquals(-79, mutableGrid[2, 0])
        assertEquals(-60, mutableGrid[2, 1])
        assertEquals(13, mutableGrid[2, 2])
        assertEquals(-55, mutableGrid[2, 3])
        assertEquals(10, mutableGrid[Point.ORIGIN])
        assertEquals(20, mutableGrid[Point(1, 0)])
        assertEquals(28, mutableGrid[Point(2, 0)])
        assertEquals(-48, mutableGrid[Point(3, 0)])
        assertEquals(73, mutableGrid[Point(0, -1)])
        assertEquals(34, mutableGrid[Point(1, -1)])
        assertEquals(-63, mutableGrid[Point(2, -1)])
        assertEquals(-67, mutableGrid[Point(3, -1)])
        assertEquals(-79, mutableGrid[Point(0, -2)])
        assertEquals(-60, mutableGrid[Point(1, -2)])
        assertEquals(13, mutableGrid[Point(2, -2)])
        assertEquals(-55, mutableGrid[Point(3, -2)])
        assertThrows<IndexOutOfBoundsException> { mutableGrid[4, 0] }
        assertThrows<IndexOutOfBoundsException> { mutableGrid[Point(0, -3)] }

        assertEquals(10, mutableGrid.getOrNull(0, 0))
        assertEquals(-63, mutableGrid.getOrNull(1, 2))
        assertEquals(28, mutableGrid.getOrNull(Point(2, 0)))
        assertEquals(-60, mutableGrid.getOrNull(Point(1, -2)))
        assertNull(mutableGrid.getOrNull(-1, 0))
        assertNull(mutableGrid.getOrNull(Point(4, 0)))

        assertEquals(listOf(10, 20, 28, -48), mutableGrid.row(0))
        assertEquals(listOf(73, 34, -63, -67), mutableGrid.row(1))
        assertEquals(listOf(-79, -60, 13, -55), mutableGrid.row(2))
        assertEquals(listOf(10, 73, -79), mutableGrid.column(0))
        assertEquals(listOf(20, 34, -60), mutableGrid.column(1))
        assertEquals(listOf(28, -63, 13), mutableGrid.column(2))
        assertEquals(listOf(-48, -67, -55), mutableGrid.column(3))
        assertThrows<IndexOutOfBoundsException> { mutableGrid.row(3) }
        assertThrows<IndexOutOfBoundsException> { mutableGrid.column(4) }
        assertThrows<IndexOutOfBoundsException> { mutableGrid.row(-1) }
        assertThrows<IndexOutOfBoundsException> { mutableGrid.column(-1) }

        assertEquals(listOf(10, 20, 28, -48), mutableGrid.firstRow())
        assertEquals(listOf(10, 73, -79), mutableGrid.firstColumn())
        assertEquals(listOf(-79, -60, 13, -55), mutableGrid.lastRow())
        assertEquals(listOf(-48, -67, -55), mutableGrid.lastColumn())

        assertEquals(listOf(10, 20, 28, -48), mutableGrid.rowOrNull(0))
        assertEquals(listOf(73, 34, -63, -67), mutableGrid.rowOrNull(1))
        assertEquals(listOf(-79, -60, 13, -55), mutableGrid.rowOrNull(2))
        assertEquals(listOf(10, 73, -79), mutableGrid.columnOrNull(0))
        assertEquals(listOf(20, 34, -60), mutableGrid.columnOrNull(1))
        assertEquals(listOf(28, -63, 13), mutableGrid.columnOrNull(2))
        assertEquals(listOf(-48, -67, -55), mutableGrid.columnOrNull(3))
        assertNull(mutableGrid.rowOrNull(3))
        assertNull(mutableGrid.columnOrNull(4))
        assertNull(mutableGrid.rowOrNull(-1))
        assertNull(mutableGrid.columnOrNull(-1))

        assertEquals(
            listOf(
                listOf(10, 20, 28, -48),
                listOf(73, 34, -63, -67),
                listOf(-79, -60, 13, -55)
            ),
            mutableGrid.rows()
        )
        assertEquals(
            listOf(
                listOf(10, 73, -79),
                listOf(20, 34, -60),
                listOf(28, -63, 13),
                listOf(-48, -67, -55)
            ),
            mutableGrid.columns()
        )

        assertEquals(listOf(10, 20, 28, -48), mutableGrid.shallowRow(0))
        assertEquals(listOf(73, 34, -63, -67), mutableGrid.shallowRow(1))
        assertEquals(listOf(-79, -60, 13, -55), mutableGrid.shallowRow(2))
        assertEquals(listOf(10, 73, -79), mutableGrid.shallowColumn(0))
        assertEquals(listOf(20, 34, -60), mutableGrid.shallowColumn(1))
        assertEquals(listOf(28, -63, 13), mutableGrid.shallowColumn(2))
        assertEquals(listOf(-48, -67, -55), mutableGrid.shallowColumn(3))
        assertThrows<IndexOutOfBoundsException> { mutableGrid.shallowRow(3) }
        assertThrows<IndexOutOfBoundsException> { mutableGrid.shallowColumn(4) }
        assertThrows<IndexOutOfBoundsException> { mutableGrid.shallowRow(-1) }
        assertThrows<IndexOutOfBoundsException> { mutableGrid.shallowColumn(-1) }

        assertEquals(
            listOf(
                listOf(10, 20, 28, -48),
                listOf(73, 34, -63, -67),
                listOf(-79, -60, 13, -55)
            ),
            mutableGrid.shallowRows()
        )
        assertEquals(
            listOf(
                listOf(10, 73, -79),
                listOf(20, 34, -60),
                listOf(28, -63, 13),
                listOf(-48, -67, -55)
            ),
            mutableGrid.shallowColumns()
        )

        assertEquals(
            "[10, 20, 28, -48] + [73, 34, -63, -67] + [-79, -60, 13, -55]",
            mutableGrid.joinRowsToString(separator = " + ") { it.toString() }
        )

        assertEquals(
            gridOf(
                listOf(-48, 28, 20, 10),
                listOf(-67, -63, 34, 73),
                listOf(-55, 13, -60, -79)
            ),
            mutableGrid.flippedHorizontal()
        )
        assertEquals(
            gridOf(
                listOf(-48, -67, -55),
                listOf(28, -63, 13),
                listOf(20, 34, -60),
                listOf(10, 73, -79)
            ),
            mutableGrid.rotatedLeft()
        )
    }

    @Test
    fun testSet() {
        val mutableGrid = mutableGridOf(
            listOf(-36, -20, -66),
            listOf(67, -61, 13),
            listOf(80, -32, -37),
            listOf(-48, -85, 4)
        )

        mutableGrid[0, 0] = 17
        mutableGrid[1, 2] = -51
        mutableGrid[2, 1] = 87
        mutableGrid[3, 0] = -62

        assertThrows<IndexOutOfBoundsException> { mutableGrid[0, -1] = -44 }
        assertThrows<IndexOutOfBoundsException> { mutableGrid[-1, 2] = 26 }
        assertThrows<IndexOutOfBoundsException> { mutableGrid[1, 3] = 99 }
        assertThrows<IndexOutOfBoundsException> { mutableGrid[4, 2] = 71 }

        mutableGrid[Point(2, 0)] = 22
        mutableGrid[Point(0, -1)] = -57
        mutableGrid[Point(1, -2)] = 12
        mutableGrid[Point(2, -3)] = 95

        assertThrows<IndexOutOfBoundsException> { mutableGrid[Point(-1, 0)] = 25 }
        assertThrows<IndexOutOfBoundsException> { mutableGrid[Point(2, 1)] = 57 }
        assertThrows<IndexOutOfBoundsException> { mutableGrid[Point(3, -1)] = -94 }
        assertThrows<IndexOutOfBoundsException> { mutableGrid[Point(2, -4)] = -89 }

        assertEquals(
            gridOf(
                listOf(17, -20, 22),
                listOf(-57, -61, -51),
                listOf(80, 12, -37),
                listOf(-62, -85, 95)
            ),
            mutableGrid
        )
    }

    @Test
    fun testAddRowsAndColumns() {
        val mutableGrid = mutableGridOf<Int>()
        assertThrows<IllegalArgumentException> { mutableGrid.addRow(emptyList()) }
        assertThrows<IllegalArgumentException> { mutableGrid.addColumn(emptyList()) }

        mutableGrid.addRow(listOf(1, 2))
        assertEquals(gridOf(listOf(1, 2)), mutableGrid)

        assertThrows<IllegalArgumentException> { mutableGrid.addRow(listOf(3)) }
        assertThrows<IllegalArgumentException> { mutableGrid.addColumn(listOf(3, 4)) }

        mutableGrid.addColumn(listOf(3))
        assertEquals(gridOf(listOf(1, 2, 3)), mutableGrid)

        mutableGrid.addRow(listOf(4, 5, 6))
        assertEquals(gridOf(listOf(1, 2, 3), listOf(4, 5, 6)), mutableGrid)

        assertThrows<IllegalArgumentException> { mutableGrid.addRow(listOf(7, 8, 9, 0)) }
        assertThrows<IllegalArgumentException> { mutableGrid.addColumn(listOf(7)) }

        mutableGrid.addColumn(listOf(7, 8))
        assertEquals(gridOf(listOf(1, 2, 3, 7), listOf(4, 5, 6, 8)), mutableGrid)
    }

    @Test
    fun testAddRowCopiesRow() {
        val mutableGrid = mutableGridOf<Int>()
        val row = mutableListOf(1, 2, 3)
        mutableGrid.addRow(row)

        row[0] = 4
        row[1] = 5
        row[2] = 6
        row.add(7)

        assertEquals(gridOf(listOf(1, 2, 3)), mutableGrid)
        assertEquals(listOf(1, 2, 3), mutableGrid.firstRow())
    }

    @Test
    fun testAddColumnCopiesColumn() {
        val mutableGrid = mutableGridOf<Int>()
        val column = mutableListOf(1, 2, 3)
        mutableGrid.addColumn(column)

        column[0] = 4
        column[1] = 5
        column[2] = 6
        column.add(7)

        assertEquals(gridOf(listOf(1), listOf(2), listOf(3)), mutableGrid)
        assertEquals(listOf(1, 2, 3), mutableGrid.firstColumn())
    }

    @Test
    fun testAddShallowRowsAndColumns() {
        val mutableGrid = mutableGridOf<Int>()
        assertThrows<IllegalArgumentException> { mutableGrid.addShallowRow(emptyList()) }
        assertThrows<IllegalArgumentException> { mutableGrid.addShallowColumn(emptyList()) }

        mutableGrid.addShallowRow(listOf(1, 2))
        assertEquals(gridOf(listOf(1, 2)), mutableGrid)

        assertThrows<IllegalArgumentException> { mutableGrid.addShallowRow(listOf(3)) }
        assertThrows<IllegalArgumentException> { mutableGrid.addShallowColumn(listOf(3, 4)) }

        mutableGrid.addShallowColumn(listOf(3))
        assertEquals(gridOf(listOf(1, 2, 3)), mutableGrid)

        mutableGrid.addShallowRow(listOf(4, 5, 6))
        assertEquals(gridOf(listOf(1, 2, 3), listOf(4, 5, 6)), mutableGrid)

        assertThrows<IllegalArgumentException> { mutableGrid.addShallowRow(listOf(7, 8, 9, 0)) }
        assertThrows<IllegalArgumentException> { mutableGrid.addShallowColumn(listOf(7)) }

        mutableGrid.addShallowColumn(listOf(7, 8))
        assertEquals(gridOf(listOf(1, 2, 3, 7), listOf(4, 5, 6, 8)), mutableGrid)
    }

    @Test
    fun testRemoveLastRowAndColumn() {
        val mutableGrid = mutableGridOf(
            listOf(17, -8, -55, -24, 10),
            listOf(16, -17, -67, -26, -73),
            listOf(12, 53, -7, -83, -75),
            listOf(55, 34, -69, -16, 58)
        )

        mutableGrid.removeLastRow()
        assertEquals(
            gridOf(
                listOf(17, -8, -55, -24, 10),
                listOf(16, -17, -67, -26, -73),
                listOf(12, 53, -7, -83, -75)
            ),
            mutableGrid
        )

        mutableGrid.removeLastColumn()
        assertEquals(
            gridOf(
                listOf(17, -8, -55, -24),
                listOf(16, -17, -67, -26),
                listOf(12, 53, -7, -83)
            ),
            mutableGrid
        )

        mutableGrid.removeLastColumn()
        assertEquals(
            gridOf(
                listOf(17, -8, -55),
                listOf(16, -17, -67),
                listOf(12, 53, -7)
            ),
            mutableGrid
        )

        mutableGrid.removeLastRow()
        assertEquals(
            gridOf(
                listOf(17, -8, -55),
                listOf(16, -17, -67)
            ),
            mutableGrid
        )

        mutableGrid.removeLastRow()
        assertEquals(gridOf(listOf(17, -8, -55)), mutableGrid)

        mutableGrid.removeLastColumn()
        assertEquals(gridOf(listOf(17, -8)), mutableGrid)

        mutableGrid.removeLastRow()
        assertEquals(emptyGrid(), mutableGrid)

        assertThrows<NoSuchElementException> { mutableGrid.removeLastRow() }
        assertThrows<NoSuchElementException> { mutableGrid.removeLastColumn() }
    }

    @Test
    fun testMutableGridOfInvalidRows() {
        assertThrows<IllegalArgumentException> { mutableGridOf(emptyList<Nothing>()) }
        assertThrows<IllegalArgumentException> { mutableGridOf(listOf("a"), emptyList()) }
        assertThrows<IllegalArgumentException> { mutableGridOf(listOf(1), listOf(2, 3)) }
        assertThrows<IllegalArgumentException> {
            mutableGridOf(listOf(-37, 94, -76), listOf(3, 21, -80), listOf(22, 10))
        }
        assertThrows<IllegalArgumentException> {
            mutableGridOf(
                listOf(-89, -76, 26, 36),
                listOf(48, 82, -16, 5),
                listOf(40, 44, -42, 2, -44, 99),
                listOf(-9, 66, 43, 29)
            )
        }
    }

    @Test
    fun testMutableGridOfCopiesList() {
        val list = mutableListOf(1, 2)
        val mutableGrid = mutableGridOf(list)

        list[0] = 3
        list[1] = 4
        list.add(5)

        assertEquals(gridOf(listOf(1, 2)), mutableGrid)
    }

    @Test
    fun testFakeConstructor() {
        assertEquals(emptyGrid(), MutableGrid(0, 0) { _, _ -> 42 })
        assertThrows<IllegalArgumentException> { MutableGrid(0, 1) { _, _ -> 42 } }
        assertThrows<IllegalArgumentException> { MutableGrid(2, 0) { _, _ -> 42 } }
        assertThrows<IllegalArgumentException> { MutableGrid(-1, 3) { _, _ -> 42 } }
        assertThrows<IllegalArgumentException> { MutableGrid(0, -1) { _, _ -> 42 } }
        assertEquals(gridOf(listOf(42)), MutableGrid(1, 1) { _, _ -> 42 })
        assertEquals(
            gridOf(
                listOf(0, 1, 2, 3, 4),
                listOf(10, 11, 12, 13, 14),
                listOf(20, 21, 22, 23, 24),
                listOf(30, 31, 32, 33, 34),
                listOf(40, 41, 42, 43, 44),
                listOf(50, 51, 52, 53, 54)
            ),
            MutableGrid(6, 5) { rowIndex, colIndex -> rowIndex * 10 + colIndex }
        )
    }

    @Test
    fun testListToMutableGrid() {
        assertEquals(emptyGrid<Nothing>(), emptyList<Nothing>().toMutableGrid())
        assertEquals(gridOf(listOf(42)), listOf(listOf(42)).toMutableGrid())
        assertThrows<IllegalArgumentException> { listOf(listOf(5), listOf(6, 7)).toMutableGrid() }
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
            ).toMutableGrid()
        )
    }

    @Test
    fun testToMutableGridCopiesList() {
        val list = mutableListOf(mutableListOf(1, 2))
        val mutableGrid = list.toMutableGrid()

        list[0][0] = 3
        list[0][1] = 4
        list.add(mutableListOf(5, 6))

        assertEquals(gridOf(listOf(1, 2)), mutableGrid)
    }

    @Test
    fun testGridToMutableGrid() {
        var grid = gridOf<Int>()
        assertEquals(emptyGrid(), grid.toMutableGrid())
        assertNotSame(grid, grid.toMutableGrid())

        grid = gridOf(listOf(42))
        assertEquals(gridOf(listOf(42)), grid.toMutableGrid())
        assertNotSame(grid, grid.toMutableGrid())

        grid = gridOf(
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
            grid.toMutableGrid()
        )
        assertNotSame(grid, grid.toMutableGrid())
    }
}
