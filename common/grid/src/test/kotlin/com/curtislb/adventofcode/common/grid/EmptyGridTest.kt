package com.curtislb.adventofcode.common.grid

import com.curtislb.adventofcode.common.geometry.Point
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [EmptyGrid].
 */
class EmptyGridTest {
    @Test
    fun testHeightAndWidth() {
        assertEquals(0, EmptyGrid.height)
        assertEquals(0, EmptyGrid.width)
    }

    @Test
    fun testGetWithIndices() {
        assertThrows<IndexOutOfBoundsException> { EmptyGrid[0, 0] }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid[0, 1] }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid[1, 0] }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid[1, 2] }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid[-1, 0] }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid[0, -1] }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid[-1, -2] }
    }

    @Test
    fun testGetWithPoint() {
        assertThrows<IndexOutOfBoundsException> { EmptyGrid[Point.ORIGIN] }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid[Point(1, 0)] }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid[Point(0, -1)] }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid[Point(2, -1)] }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid[Point(0, 1)] }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid[Point(-1, 0)] }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid[Point(-2, 1)] }
    }

    @Test
    fun testRow() {
        assertThrows<IndexOutOfBoundsException> { EmptyGrid.row(0) }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid.row(1) }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid.row(-1) }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid.row(2) }
    }

    @Test
    fun testColumn() {
        assertThrows<IndexOutOfBoundsException> { EmptyGrid.column(0) }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid.column(1) }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid.column(-1) }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid.column(2) }
    }

    @Test
    fun testRowsAndColumns() {
        assertEquals(emptyList(), EmptyGrid.rows())
        assertEquals(emptyList(), EmptyGrid.columns())
    }

    @Test
    fun testShallowRow() {
        assertThrows<IndexOutOfBoundsException> { EmptyGrid.shallowRow(0) }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid.shallowRow(1) }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid.shallowRow(-1) }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid.shallowRow(2) }
    }

    @Test
    fun testShallowColumn() {
        assertThrows<IndexOutOfBoundsException> { EmptyGrid.shallowColumn(0) }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid.shallowColumn(1) }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid.shallowColumn(-1) }
        assertThrows<IndexOutOfBoundsException> { EmptyGrid.shallowColumn(2) }
    }

    @Test
    fun testShallowRowsAndColumns() {
        assertEquals(emptyList(), EmptyGrid.shallowRows())
        assertEquals(emptyList(), EmptyGrid.shallowColumns())
    }

    @Test
    fun testEquals() {
        assertEquals(emptyGrid(), EmptyGrid)
        assertEquals(gridOf(), EmptyGrid)
        assertEquals(mutableGridOf<Nothing>() as Grid<Nothing>, EmptyGrid)

        val nullGrid: Grid<Nothing>? = null
        assertNotEquals(nullGrid, EmptyGrid)

        assertEquals(gridOf<Nothing>(), emptyGrid())
        assertEquals(gridOf<Int>(), emptyGrid())
        assertEquals(gridOf<String>(), emptyGrid())
        assertNotEquals(gridOf(listOf(0)), emptyGrid())
    }

    @Test
    fun testHashCode() {
        val hashMap = HashMap<Grid<Nothing>, Int>()
        hashMap[emptyGrid()] = 42
        assertEquals(42, hashMap[emptyGrid()])
    }

    @Test
    fun testToString() {
        assertEquals("[]", EmptyGrid.toString())
        assertEquals("[]", emptyGrid<Int>().toString())
    }
}
