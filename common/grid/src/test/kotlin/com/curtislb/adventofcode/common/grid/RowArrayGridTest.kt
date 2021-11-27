package com.curtislb.adventofcode.common.grid

import com.curtislb.adventofcode.common.testing.assertContainsExactly
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [RowArrayGrid].
 */
class RowArrayGridTest {
    @Test
    fun testWhenEmpty() {
        val grid = RowArrayGrid<Nothing>()

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

        assertThrows<IndexOutOfBoundsException> { grid.volatileRow(0) }
        assertThrows<IndexOutOfBoundsException> { grid.volatileColumn(0) }

        assertEquals(emptyList(), grid.volatileRows())
        assertEquals(emptyList(), grid.volatileColumns())

        assertEquals("", grid.joinRowsToString { it.toString() })

        assertTrue(grid.flippedHorizontal().isEmpty())
        assertTrue(grid.rotatedLeft().isEmpty())
    }

    @Test
    fun testWithOneElement() {
        val rows = ArrayList<ArrayList<Int>>().apply {
            add(ArrayList<Int>(1).apply { add(42) })
        }
        val grid = RowArrayGrid(rows)

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

        assertEquals(listOf(42), grid.volatileRow(0))
        assertEquals(listOf(42), grid.volatileColumn(0))
        assertThrows<IndexOutOfBoundsException> { grid.volatileRow(1) }
        assertThrows<IndexOutOfBoundsException> { grid.volatileColumn(1) }
        assertThrows<IndexOutOfBoundsException> { grid.volatileRow(-1) }
        assertThrows<IndexOutOfBoundsException> { grid.volatileColumn(-1) }

        assertEquals(listOf(listOf(42)), grid.volatileRows())
        assertEquals(listOf(listOf(42)), grid.volatileColumns())

        assertEquals("<[42]>", grid.joinRowsToString { "<$it>" })

        assertEquals(gridOf(listOf(42)), grid.flippedHorizontal())
        assertEquals(gridOf(listOf(42)), grid.rotatedLeft())
    }

    @Test
    fun testWithMultipleElements() {
        val rows = ArrayList<ArrayList<Int>>().apply {
            add(ArrayList<Int>(4).apply { addAll(listOf(10, 20, 28, -48)) })
            add(ArrayList<Int>(4).apply { addAll(listOf(73, 34, -63, -67)) })
            add(ArrayList<Int>(4).apply { addAll(listOf(-79, -60, 13, -55)) })
        }
        val grid = RowArrayGrid(rows)

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

        assertEquals(listOf(10, 20, 28, -48), grid.volatileRow(0))
        assertEquals(listOf(73, 34, -63, -67), grid.volatileRow(1))
        assertEquals(listOf(-79, -60, 13, -55), grid.volatileRow(2))
        assertEquals(listOf(10, 73, -79), grid.volatileColumn(0))
        assertEquals(listOf(20, 34, -60), grid.volatileColumn(1))
        assertEquals(listOf(28, -63, 13), grid.volatileColumn(2))
        assertEquals(listOf(-48, -67, -55), grid.volatileColumn(3))
        assertThrows<IndexOutOfBoundsException> { grid.volatileRow(3) }
        assertThrows<IndexOutOfBoundsException> { grid.volatileColumn(4) }
        assertThrows<IndexOutOfBoundsException> { grid.volatileRow(-1) }
        assertThrows<IndexOutOfBoundsException> { grid.volatileColumn(-1) }

        assertEquals(
            listOf(
                listOf(10, 20, 28, -48),
                listOf(73, 34, -63, -67),
                listOf(-79, -60, 13, -55)
            ),
            grid.volatileRows()
        )
        assertEquals(
            listOf(
                listOf(10, 73, -79),
                listOf(20, 34, -60),
                listOf(28, -63, 13),
                listOf(-48, -67, -55)
            ),
            grid.volatileColumns()
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
    fun testSet() {
        val rows = ArrayList<ArrayList<Int>>().apply {
            add(ArrayList<Int>(3).apply { addAll(listOf(-36, -20, -66)) })
            add(ArrayList<Int>(3).apply { addAll(listOf(67, -61, 13)) })
            add(ArrayList<Int>(3).apply { addAll(listOf(80, -32, -37)) })
            add(ArrayList<Int>(3).apply { addAll(listOf(-48, -85, 4)) })
        }
        val grid = RowArrayGrid(rows)

        grid[0, 0] = 17
        grid[1, 2] = -51
        grid[2, 1] = 87
        grid[3, 0] = -62

        assertThrows<IndexOutOfBoundsException> { grid[0, -1] = -44 }
        assertThrows<IndexOutOfBoundsException> { grid[-1, 2] = 26 }
        assertThrows<IndexOutOfBoundsException> { grid[1, 3] = 99 }
        assertThrows<IndexOutOfBoundsException> { grid[4, 2] = 71 }

        grid[Point(2, 0)] = 22
        grid[Point(0, -1)] = -57
        grid[Point(1, -2)] = 12
        grid[Point(2, -3)] = 95

        assertThrows<IndexOutOfBoundsException> { grid[Point(-1, 0)] = 25 }
        assertThrows<IndexOutOfBoundsException> { grid[Point(2, 1)] = 57 }
        assertThrows<IndexOutOfBoundsException> { grid[Point(3, -1)] = -94 }
        assertThrows<IndexOutOfBoundsException> { grid[Point(2, -4)] = -89 }

        assertEquals(
            gridOf(
                listOf(17, -20, 22),
                listOf(-57, -61, -51),
                listOf(80, 12, -37),
                listOf(-62, -85, 95)
            ),
            grid
        )
    }

    @Test
    fun testAddRowsAndColumns() {
        val grid = RowArrayGrid<Int>()
        assertThrows<IllegalArgumentException> { grid.addRow(emptyList()) }
        assertThrows<IllegalArgumentException> { grid.addColumn(emptyList()) }

        grid.addRow(listOf(1, 2))
        assertEquals(gridOf(listOf(1, 2)), grid)

        assertThrows<IllegalArgumentException> { grid.addRow(listOf(3)) }
        assertThrows<IllegalArgumentException> { grid.addColumn(listOf(3, 4)) }

        grid.addColumn(listOf(3))
        assertEquals(gridOf(listOf(1, 2, 3)), grid)

        grid.addRow(listOf(4, 5, 6))
        assertEquals(gridOf(listOf(1, 2, 3), listOf(4, 5, 6)), grid)

        assertThrows<IllegalArgumentException> { grid.addRow(listOf(7, 8, 9, 0)) }
        assertThrows<IllegalArgumentException> { grid.addColumn(listOf(7)) }

        grid.addColumn(listOf(7, 8))
        assertEquals(gridOf(listOf(1, 2, 3, 7), listOf(4, 5, 6, 8)), grid)
    }

    @Test
    fun testAddRowCopiesRow() {
        val grid = RowArrayGrid<Int>()
        val row = mutableListOf(1, 2, 3)
        grid.addRow(row)

        row[0] = 4
        row[1] = 5
        row[2] = 6
        row.add(7)

        assertEquals(gridOf(listOf(1, 2, 3)), grid)
        assertEquals(listOf(1, 2, 3), grid.firstRow())
    }

    @Test
    fun testAddColumnCopiesColumn() {
        val grid = RowArrayGrid<Int>()
        val column = mutableListOf(1, 2, 3)
        grid.addColumn(column)

        column[0] = 4
        column[1] = 5
        column[2] = 6
        column.add(7)

        assertEquals(gridOf(listOf(1), listOf(2), listOf(3)), grid)
        assertEquals(listOf(1, 2, 3), grid.firstColumn())
    }

    @Test
    fun testAddShallowRowsAndColumns() {
        val grid = RowArrayGrid<Int>()
        assertThrows<IllegalArgumentException> { grid.addShallowRow(emptyList()) }
        assertThrows<IllegalArgumentException> { grid.addShallowColumn(emptyList()) }

        grid.addShallowRow(listOf(1, 2))
        assertEquals(gridOf(listOf(1, 2)), grid)

        assertThrows<IllegalArgumentException> { grid.addShallowRow(listOf(3)) }
        assertThrows<IllegalArgumentException> { grid.addShallowColumn(listOf(3, 4)) }

        grid.addShallowColumn(listOf(3))
        assertEquals(gridOf(listOf(1, 2, 3)), grid)

        grid.addShallowRow(listOf(4, 5, 6))
        assertEquals(gridOf(listOf(1, 2, 3), listOf(4, 5, 6)), grid)

        assertThrows<IllegalArgumentException> { grid.addShallowRow(listOf(7, 8, 9, 0)) }
        assertThrows<IllegalArgumentException> { grid.addShallowColumn(listOf(7)) }

        grid.addShallowColumn(listOf(7, 8))
        assertEquals(gridOf(listOf(1, 2, 3, 7), listOf(4, 5, 6, 8)), grid)
    }

    @Test
    fun testRemoveLastRowAndColumn() {
        val rows = ArrayList<ArrayList<Int>>().apply {
            add(ArrayList<Int>(5).apply { addAll(listOf(17, -8, -55, -24, 10)) })
            add(ArrayList<Int>(5).apply { addAll(listOf(16, -17, -67, -26, -73)) })
            add(ArrayList<Int>(5).apply { addAll(listOf(12, 53, -7, -83, -75)) })
            add(ArrayList<Int>(5).apply { addAll(listOf(55, 34, -69, -16, 58)) })
        }
        val grid = RowArrayGrid(rows)

        grid.removeLastRow()
        assertEquals(
            gridOf(
                listOf(17, -8, -55, -24, 10),
                listOf(16, -17, -67, -26, -73),
                listOf(12, 53, -7, -83, -75)
            ),
            grid
        )

        grid.removeLastColumn()
        assertEquals(
            gridOf(
                listOf(17, -8, -55, -24),
                listOf(16, -17, -67, -26),
                listOf(12, 53, -7, -83)
            ),
            grid
        )

        grid.removeLastColumn()
        assertEquals(
            gridOf(
                listOf(17, -8, -55),
                listOf(16, -17, -67),
                listOf(12, 53, -7)
            ),
            grid
        )

        grid.removeLastRow()
        assertEquals(
            gridOf(
                listOf(17, -8, -55),
                listOf(16, -17, -67)
            ),
            grid
        )

        grid.removeLastRow()
        assertEquals(gridOf(listOf(17, -8, -55)), grid)

        grid.removeLastColumn()
        assertEquals(gridOf(listOf(17, -8)), grid)

        grid.removeLastRow()
        assertEquals(emptyGrid(), grid)

        assertThrows<NoSuchElementException> { grid.removeLastRow() }
        assertThrows<NoSuchElementException> { grid.removeLastColumn() }
    }

    @Test
    fun testEquals() {
        assertEquals(EmptyGrid, RowArrayGrid<Nothing>() as Grid<Nothing>)
        assertEquals(emptyGrid(), RowArrayGrid<Nothing>())
        assertEquals(emptyGrid(), RowArrayGrid<Int>())
        assertEquals(emptyGrid(), RowArrayGrid<String>())
        assertEquals(gridOf(), RowArrayGrid<Nothing>())
        assertEquals(mutableGridOf(), RowArrayGrid<Nothing>())

        val nullGrid: RowArrayGrid<Int>? = null
        assertNotEquals(nullGrid, RowArrayGrid())

        val rowArrayGrids = listOf(
            RowArrayGrid(),
            RowArrayGrid(
                ArrayList<ArrayList<Int>>().apply {
                    add(ArrayList<Int>().apply { add(1) })
                }
            ),
            RowArrayGrid(
                ArrayList<ArrayList<Int>>().apply {
                    add(ArrayList<Int>().apply { add(2) })
                    add(ArrayList<Int>().apply { add(3) })
                }
            ),
            RowArrayGrid(
                ArrayList<ArrayList<Int>>().apply {
                    add(ArrayList<Int>().apply { addAll(listOf(4, 5)) })
                    add(ArrayList<Int>().apply { addAll(listOf(6, 7)) })
                }
            ),
            RowArrayGrid(
                ArrayList<ArrayList<Int>>().apply {
                    add(ArrayList<Int>().apply { addAll(listOf(8, 9, 0)) })
                    add(ArrayList<Int>().apply { addAll(listOf(-1, -2, -3)) })
                }
            )
        )
        val rowArrayGridsCopy = listOf(
            RowArrayGrid(),
            RowArrayGrid(
                ArrayList<ArrayList<Int>>().apply {
                    add(ArrayList<Int>().apply { add(1) })
                }
            ),
            RowArrayGrid(
                ArrayList<ArrayList<Int>>().apply {
                    add(ArrayList<Int>().apply { add(2) })
                    add(ArrayList<Int>().apply { add(3) })
                }
            ),
            RowArrayGrid(
                ArrayList<ArrayList<Int>>().apply {
                    add(ArrayList<Int>().apply { addAll(listOf(4, 5)) })
                    add(ArrayList<Int>().apply { addAll(listOf(6, 7)) })
                }
            ),
            RowArrayGrid(
                ArrayList<ArrayList<Int>>().apply {
                    add(ArrayList<Int>().apply { addAll(listOf(8, 9, 0)) })
                    add(ArrayList<Int>().apply { addAll(listOf(-1, -2, -3)) })
                }
            )
        )

        rowArrayGrids.forEachIndexed { origIndex, origGrid ->
            rowArrayGridsCopy.forEachIndexed { copyIndex, copyGrid ->
                if (origIndex == copyIndex) {
                    assertEquals(origGrid, copyGrid)
                } else {
                    assertNotEquals(origGrid, copyGrid)
                }
            }
        }
    }

    @Test
    fun testHashCode() {
        assertEquals(EmptyGrid.hashCode(), RowArrayGrid<Nothing>().hashCode())
        assertEquals(EmptyGrid.hashCode(), RowArrayGrid<Int>().hashCode())

        val rowArrayGrids = listOf(
            RowArrayGrid(),
            RowArrayGrid(
                ArrayList<ArrayList<Int>>().apply {
                    add(ArrayList<Int>().apply { add(1) })
                }
            ),
            RowArrayGrid(
                ArrayList<ArrayList<Int>>().apply {
                    add(ArrayList<Int>().apply { add(2) })
                    add(ArrayList<Int>().apply { add(3) })
                }
            ),
            RowArrayGrid(
                ArrayList<ArrayList<Int>>().apply {
                    add(ArrayList<Int>().apply { addAll(listOf(4, 5)) })
                    add(ArrayList<Int>().apply { addAll(listOf(6, 7)) })
                }
            ),
            RowArrayGrid(
                ArrayList<ArrayList<Int>>().apply {
                    add(ArrayList<Int>().apply { addAll(listOf(8, 9, 0)) })
                    add(ArrayList<Int>().apply { addAll(listOf(-1, -2, -3)) })
                }
            )
        )
        val rowArrayGridsCopy = listOf(
            RowArrayGrid(),
            RowArrayGrid(
                ArrayList<ArrayList<Int>>().apply {
                    add(ArrayList<Int>().apply { add(1) })
                }
            ),
            RowArrayGrid(
                ArrayList<ArrayList<Int>>().apply {
                    add(ArrayList<Int>().apply { add(2) })
                    add(ArrayList<Int>().apply { add(3) })
                }
            ),
            RowArrayGrid(
                ArrayList<ArrayList<Int>>().apply {
                    add(ArrayList<Int>().apply { addAll(listOf(4, 5)) })
                    add(ArrayList<Int>().apply { addAll(listOf(6, 7)) })
                }
            ),
            RowArrayGrid(
                ArrayList<ArrayList<Int>>().apply {
                    add(ArrayList<Int>().apply { addAll(listOf(8, 9, 0)) })
                    add(ArrayList<Int>().apply { addAll(listOf(-1, -2, -3)) })
                }
            )
        )

        val hashMap = HashMap<RowArrayGrid<Int>, Int>()
        rowArrayGrids.forEachIndexed { index, rowArrayGrid ->
            hashMap[rowArrayGrid] = index
        }
        rowArrayGridsCopy.forEachIndexed { index, rowArrayGrid ->
            assertEquals(index, hashMap[rowArrayGrid])
        }
    }

    @Test
    fun testToString() {
        assertEquals("[]", RowArrayGrid<Nothing>().toString())

        var rows = ArrayList<ArrayList<Int>>().apply {
            add(ArrayList<Int>().apply { add(42) })
        }
        assertEquals("[[42]]", RowArrayGrid(rows).toString())

        rows = ArrayList<ArrayList<Int>>().apply {
            add(ArrayList<Int>().apply { addAll(listOf(1, -2, 3)) })
        }
        assertEquals("[[1, -2, 3]]", RowArrayGrid(rows).toString())

        rows = ArrayList<ArrayList<Int>>().apply {
            add(ArrayList<Int>().apply { add(-1) })
            add(ArrayList<Int>().apply { add(2) })
            add(ArrayList<Int>().apply { add(3) })
        }
        assertEquals(
            """
                [[-1],
                 [2],
                 [3]]
            """.trimIndent(),
            RowArrayGrid(rows).toString()
        )

        rows = ArrayList<ArrayList<Int>>().apply {
            add(ArrayList<Int>().apply { addAll(listOf(1, -2)) })
            add(ArrayList<Int>().apply { addAll(listOf(-3, 4)) })
        }
        assertEquals(
            """
                [[1, -2],
                 [-3, 4]]
            """.trimIndent(),
            RowArrayGrid(rows).toString()
        )

        rows = ArrayList<ArrayList<Int>>().apply {
            add(ArrayList<Int>().apply { addAll(listOf(-10, -27, 89, -46)) })
            add(ArrayList<Int>().apply { addAll(listOf(-23, -63, -37, 44)) })
            add(ArrayList<Int>().apply { addAll(listOf(76, 19, -88, -32)) })
            add(ArrayList<Int>().apply { addAll(listOf(73, 94, 56, 19)) })
            add(ArrayList<Int>().apply { addAll(listOf(-64, -1, -47, -42)) })
        }
        assertEquals(
            """
                [[-10, -27, 89, -46],
                 [-23, -63, -37, 44],
                 [76, 19, -88, -32],
                 [73, 94, 56, 19],
                 [-64, -1, -47, -42]]
            """.trimIndent(),
            RowArrayGrid(rows).toString()
        )
    }

    @Test
    fun testConstructWithInvalidRows() {
        assertThrows<IllegalArgumentException> {
            val rows = ArrayList<ArrayList<Nothing>>().apply { add(ArrayList()) }
            RowArrayGrid(rows)
        }
        assertThrows<IllegalArgumentException> { 
            val rows = ArrayList<ArrayList<String>>().apply {
                add(ArrayList<String>().apply { add("a") })
                add(ArrayList())
            }
            RowArrayGrid(rows)
        }
        assertThrows<IllegalArgumentException> {
            val rows = ArrayList<ArrayList<Int>>().apply {
                add(ArrayList<Int>().apply { add(1) })
                add(ArrayList<Int>().apply { addAll(listOf(2, 3)) })
            }
            RowArrayGrid(rows)
        }
        assertThrows<IllegalArgumentException> {
            val rows = ArrayList<ArrayList<Int>>().apply {
                add(ArrayList<Int>().apply { addAll(listOf(-37, 94, -76)) })
                add(ArrayList<Int>().apply { addAll(listOf(3, 21, -80)) })
                add(ArrayList<Int>().apply { addAll(listOf(22, 10)) })
            }
            RowArrayGrid(rows)
        }
        assertThrows<IllegalArgumentException> {
            val rows = ArrayList<ArrayList<Int>>().apply {
                add(ArrayList<Int>().apply { addAll(listOf(-89, -76, 26, 36)) })
                add(ArrayList<Int>().apply { addAll(listOf(48, 82, -16, 5)) })
                add(ArrayList<Int>().apply { addAll(listOf(40, 44, -42, 2, -44, 99)) })
                add(ArrayList<Int>().apply { addAll(listOf(-9, 66, 43, 29)) })
            }
            RowArrayGrid(rows)
        }
    }

    @Test
    fun testFakeInitConstructor() {
        assertEquals(emptyGrid(), RowArrayGrid(0, 0) { _, _ -> 42 })
        assertThrows<IllegalArgumentException> { RowArrayGrid(0, 1) { _, _ -> 42 } }
        assertThrows<IllegalArgumentException> { RowArrayGrid(2, 0) { _, _ -> 42 } }
        assertThrows<IllegalArgumentException> { RowArrayGrid(-1, 3) { _, _ -> 42 } }
        assertThrows<IllegalArgumentException> { RowArrayGrid(0, -1) { _, _ -> 42 } }
        assertEquals(gridOf(listOf(42)), RowArrayGrid(1, 1) { _, _ -> 42 })
        assertEquals(
            gridOf(
                listOf(0, 1, 2, 3, 4),
                listOf(10, 11, 12, 13, 14),
                listOf(20, 21, 22, 23, 24),
                listOf(30, 31, 32, 33, 34),
                listOf(40, 41, 42, 43, 44),
                listOf(50, 51, 52, 53, 54)
            ),
            RowArrayGrid(6, 5) { rowIndex, colIndex -> rowIndex * 10 + colIndex }
        )
    }

    @Test
    fun testFakeGridConstructor() {
        var mutableGrid = mutableGridOf<Int>()
        assertEquals(emptyGrid(), RowArrayGrid(mutableGrid))

        mutableGrid = mutableGridOf(listOf(42))
        assertEquals(gridOf(listOf(42)), RowArrayGrid(mutableGrid))

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
            RowArrayGrid(mutableGrid)
        )

        mutableGrid = mutableGridOf(mutableListOf(1, 2))
        val rowArrayGrid = RowArrayGrid(mutableGrid)

        mutableGrid[0, 0] = 3
        mutableGrid[0, 1] = 4
        mutableGrid.addRow(mutableListOf(5, 6))

        assertEquals(gridOf(listOf(1, 2)), rowArrayGrid)
    }

    @Test
    fun testFakeRowListConstructor() {
        assertEquals(emptyGrid(), RowArrayGrid<Nothing>(emptyList()))
        assertEquals(gridOf(listOf(42)), RowArrayGrid(listOf(listOf(42))))
        assertThrows<IllegalArgumentException> { RowArrayGrid(listOf(listOf(5), listOf(6, 7))) }
        assertEquals(
            gridOf(
                listOf(-89, 17, 9, -6, -9),
                listOf(-60, -11, 47, 65, -86),
                listOf(-77, -12, 45, -12, 2),
                listOf(-3, 74, -43, 2, -46)
            ),
            RowArrayGrid(
                listOf(
                    listOf(-89, 17, 9, -6, -9),
                    listOf(-60, -11, 47, 65, -86),
                    listOf(-77, -12, 45, -12, 2),
                    listOf(-3, 74, -43, 2, -46)
                )
            )
        )

        val rows = mutableListOf(mutableListOf(1, 2))
        val rowArrayGrid = RowArrayGrid(rows)

        rows[0][0] = 3
        rows[0][1] = 4
        rows.add(mutableListOf(5, 6))

        assertEquals(gridOf(listOf(1, 2)), rowArrayGrid)
    }

    @Test
    fun testFakeVarargRowsConstructor() {
        assertEquals(emptyGrid(), RowArrayGrid<Nothing>(emptyList()))
        assertEquals(gridOf(listOf(42)), RowArrayGrid(listOf(42)))
        assertThrows<IllegalArgumentException> { RowArrayGrid(listOf(5), listOf(6, 7)) }
        assertEquals(
            gridOf(
                listOf(-89, 17, 9, -6, -9),
                listOf(-60, -11, 47, 65, -86),
                listOf(-77, -12, 45, -12, 2),
                listOf(-3, 74, -43, 2, -46)
            ),
            RowArrayGrid(
                listOf(-89, 17, 9, -6, -9),
                listOf(-60, -11, 47, 65, -86),
                listOf(-77, -12, 45, -12, 2),
                listOf(-3, 74, -43, 2, -46)
            )
        )

        val row = mutableListOf(1, 2)
        val rowArrayGrid = RowArrayGrid(row)

        row[0] = 3
        row[1] = 4
        row.add(5)

        assertEquals(gridOf(listOf(1, 2)), rowArrayGrid)
    }
}