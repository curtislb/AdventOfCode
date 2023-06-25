package com.curtislb.adventofcode.common.grid

import com.curtislb.adventofcode.common.geometry.Point
import java.util.Objects
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests the [MutableGrid] interface and related functions.
 */
class MutableGridTest {
    @Test
    fun set_point_whenEmpty() {
        val grid = MutableGridImpl<Any>(emptyList())
        val point = Point.ORIGIN
        assertThrows<IndexOutOfBoundsException> { grid[point] = "foo" }
    }

    @Test
    fun set_point_withSingleElement_pointInBounds() {
        val grid = MutableGridImpl(listOf(listOf("foo")))
        val point = Point.ORIGIN
        grid[point] = "bar"
        assertThat(grid).isEqualTo(MutableGridImpl(listOf(listOf("bar"))))
    }

    @Test
    fun set_point_withSingleElement_xTooSmall() {
        val grid = MutableGridImpl(listOf(listOf("foo")))
        val point = Point(-1, 0)
        assertThrows<IndexOutOfBoundsException> { grid[point] = "bar" }
    }

    @Test
    fun set_point_withSingleElement_xTooLarge() {
        val grid = MutableGridImpl(listOf(listOf("foo")))
        val point = Point(1, 0)
        assertThrows<IndexOutOfBoundsException> { grid[point] = "bar" }
    }

    @Test
    fun set_point_withSingleElement_yTooSmall() {
        val grid = MutableGridImpl(listOf(listOf("foo")))
        val point = Point(0, -1)
        assertThrows<IndexOutOfBoundsException> { grid[point] = "bar" }
    }

    @Test
    fun set_point_withSingleElement_yTooLarge() {
        val grid = MutableGridImpl(listOf(listOf("foo")))
        val point = Point(0, 1)
        assertThrows<IndexOutOfBoundsException> { grid[point] = "bar" }
    }

    @Test
    fun set_point_withSingleRow_pointInBounds() {
        val grid = MutableGridImpl(listOf(listOf("foo", "bar", "baz")))
        val point = Point(2, 0)
        grid[point] = "qux"
        assertThat(grid).isEqualTo(MutableGridImpl(listOf(listOf("foo", "bar", "qux"))))
    }

    @Test
    fun set_point_withSingleRow_xTooSmall() {
        val grid = MutableGridImpl(listOf(listOf("foo", "bar", "baz")))
        val point = Point(-1, 1)
        assertThrows<IndexOutOfBoundsException> { grid[point] = "qux" }
    }

    @Test
    fun set_point_withSingleRow_xTooLarge() {
        val grid = MutableGridImpl(listOf(listOf("foo", "bar", "baz")))
        val point = Point(3, 0)
        assertThrows<IndexOutOfBoundsException> { grid[point] = "qux" }
    }

    @Test
    fun set_point_withSingleRow_yTooSmall() {
        val grid = MutableGridImpl(listOf(listOf("foo", "bar", "baz")))
        val point = Point(1, -1)
        assertThrows<IndexOutOfBoundsException> { grid[point] = "qux" }
    }

    @Test
    fun set_point_withSingleRow_yTooLarge() {
        val grid = MutableGridImpl(listOf(listOf("foo", "bar", "baz")))
        val point = Point(1, 1)
        assertThrows<IndexOutOfBoundsException> { grid[point] = "qux" }
    }

    @Test
    fun set_point_withMultipleRows_pointInBounds() {
        val grid = MutableGridImpl(
            listOf(
                listOf("foo", "bar"),
                listOf("baz", "qux"),
                listOf("qox", "fred")
            )
        )
        val point = Point(1, -2)
        grid[point] = "echo"
        assertThat(grid).isEqualTo(
            MutableGridImpl(
                listOf(
                    listOf("foo", "bar"),
                    listOf("baz", "qux"),
                    listOf("qox", "echo")
                )
            )
        )
    }

    @Test
    fun set_point_withMultipleRows_xTooSmall() {
        val grid = MutableGridImpl(
            listOf(
                listOf("foo", "bar"),
                listOf("baz", "qux"),
                listOf("qox", "fred")
            )
        )
        val point = Point(-1, -1)
        assertThrows<IndexOutOfBoundsException> { grid[point] = "echo" }
    }

    @Test
    fun set_point_withMultipleRows_xTooLarge() {
        val grid = MutableGridImpl(
            listOf(
                listOf("foo", "bar"),
                listOf("baz", "qux"),
                listOf("qox", "fred")
            )
        )
        val point = Point(2, -1)
        assertThrows<IndexOutOfBoundsException> { grid[point] = "echo" }
    }

    @Test
    fun set_point_withMultipleRows_yTooSmall() {
        val grid = MutableGridImpl(
            listOf(
                listOf("foo", "bar"),
                listOf("baz", "qux"),
                listOf("qox", "fred")
            )
        )
        val point = Point(0, -3)
        assertThrows<IndexOutOfBoundsException> { grid[point] = "echo" }
    }

    @Test
    fun set_point_withMultipleRows_yTooLarge() {
        val grid = MutableGridImpl(
            listOf(
                listOf("foo", "bar"),
                listOf("baz", "qux"),
                listOf("qox", "fred")
            )
        )
        val point = Point(0, 1)
        assertThrows<IndexOutOfBoundsException> { grid[point] = "echo" }
    }

    @Test
    fun addShallowRow_whenEmpty_emptyRow() {
        val grid = MutableGridImpl<String>(emptyList())
        assertThrows<IllegalArgumentException> { grid.addShallowRow(emptyList()) }
    }

    @Test
    fun addShallowRow_whenEmpty_nonEmptyRow() {
        val grid = MutableGridImpl<String>(emptyList())
        grid.addShallowRow(listOf("foo", "bar", "baz"))
        assertThat(grid).isEqualTo(MutableGridImpl(listOf(listOf("foo", "bar", "baz"))))
    }

    @Test
    fun addShallowRow_whenNotEmpty_emptyRow() {
        val grid = MutableGridImpl(listOf(listOf("foo")))
        assertThrows<IllegalArgumentException> { grid.addShallowRow(emptyList()) }
    }

    @Test
    fun addShallowRow_whenNotEmpty_sameSizeRow() {
        val grid = MutableGridImpl(listOf(listOf("foo", "bar"), listOf("baz", "qux")))
        grid.addShallowRow(listOf("qox", "fred"))
        assertThat(grid).isEqualTo(
            MutableGridImpl(
                listOf(
                    listOf("foo", "bar"),
                    listOf("baz", "qux"),
                    listOf("qox", "fred")
                )
            )
        )
    }

    @Test
    fun addShallowRow_whenNotEmpty_differentSizeRow() {
        val grid = MutableGridImpl(listOf(listOf("foo", "bar", "baz")))
        assertThrows<IllegalArgumentException> { grid.addShallowRow(listOf("qox", "fred")) }
    }

    @Test
    fun addShallowColumn_whenEmpty_emptyColumn() {
        val grid = MutableGridImpl<String>(emptyList())
        assertThrows<IllegalArgumentException> { grid.addShallowColumn(emptyList()) }
    }

    @Test
    fun addShallowColumn_whenEmpty_nonEmptyColumn() {
        val grid = MutableGridImpl<String>(emptyList())
        grid.addShallowColumn(listOf("foo", "bar", "baz"))
        assertThat(grid).isEqualTo(
            MutableGridImpl(listOf(listOf("foo"), listOf("bar"), listOf("baz")))
        )
    }

    @Test
    fun addShallowColumn_whenNotEmpty_emptyColumn() {
        val grid = MutableGridImpl(listOf(listOf("foo")))
        assertThrows<IllegalArgumentException> { grid.addShallowColumn(emptyList()) }
    }

    @Test
    fun addShallowColumn_whenNotEmpty_sameSizeColumn() {
        val grid = MutableGridImpl(listOf(listOf("foo", "bar"), listOf("baz", "qux")))
        grid.addShallowColumn(listOf("qox", "fred"))
        assertThat(grid).isEqualTo(
            MutableGridImpl(listOf(listOf("foo", "bar", "qox"), listOf("baz", "qux", "fred")))
        )
    }

    @Test
    fun addShallowColumn_whenNotEmpty_differentSizeColumn() {
        val grid = MutableGridImpl(listOf(listOf("foo", "bar", "baz")))
        assertThrows<IllegalArgumentException> { grid.addShallowColumn(listOf("qox", "fred")) }
    }

    @Test
    fun constructor_zeroHeight_nonzeroWidth() {
        val height = 0
        val width = 1
        assertThrows<IllegalArgumentException> { MutableGrid(height, width) { _, _ -> 0 } }
    }

    @Test
    fun constructor_nonzeroHeight_zeroWidth() {
        val height = 1
        val width = 0
        assertThrows<IllegalArgumentException> { MutableGrid(height, width) { _, _ -> 0 } }
    }

    @Test
    fun constructor_negativeHeight() {
        val height = -1
        val width = 1
        assertThrows<IllegalArgumentException> { MutableGrid(height, width) { _, _ -> 0 } }
    }

    @Test
    fun constructor_negativeWidth() {
        val height = 1
        val width = -1
        assertThrows<IllegalArgumentException> { MutableGrid(height, width) { _, _ -> 0 } }
    }

    @Test
    fun constructor_noRows_noColumns() {
        val height = 0
        val width = 0
        val grid = MutableGrid(height, width) { _, _ -> 0 }
        assertThat(grid).isEqualTo(emptyGrid<Int>())
    }

    @Test
    fun constructor_singleRow_singleColumn() {
        val height = 1
        val width = 1
        val grid = MutableGrid(height, width) { _, _ -> 42 }
        assertThat(grid).isEqualTo(MutableGridImpl(listOf(listOf(42))))
    }

    @Test
    fun constructor_singleRow_multipleColumns() {
        val height = 1
        val width = 3
        val row = listOf("foo", "bar", "baz")
        val grid = Grid(height, width) { _, colIndex -> row[colIndex] }
        assertThat(grid).isEqualTo(MutableGridImpl(listOf(row)))
    }

    @Test
    fun constructor_multipleRows_singleColumn() {
        val height = 3
        val width = 1
        val rows = listOf(listOf("foo"), listOf("bar"), listOf("baz"))
        val grid = Grid(height, width) { rowIndex, _ -> rows[rowIndex][0] }
        assertThat(grid).isEqualTo(MutableGridImpl(rows))
    }

    @Test
    fun constructor_multipleRows_multipleColumns() {
        val height = 3
        val width = 2
        val rows = listOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val grid = Grid(height, width) { rowIndex, colIndex -> rows[rowIndex][colIndex] }
        assertThat(grid).isEqualTo(MutableGridImpl(rows))
    }

    @Test
    fun mutableGridOf_noRows() {
        val grid = mutableGridOf<Any>()
        assertThat(grid).isEqualTo(emptyGrid<Any>())
    }

    @Test
    fun mutableGridOf_singleRow() {
        val row = mutableListOf("foo", "bar", "baz")
        val grid = mutableGridOf(row)
        row.clear()
        assertThat(grid).isEqualTo(MutableGridImpl(listOf(listOf("foo", "bar", "baz"))))
    }

    @Test
    fun mutableGridOf_multipleRows() {
        val rows = arrayOf(
            mutableListOf("foo", "bar"),
            mutableListOf("baz", "qux"),
            mutableListOf("qox", "fred")
        )
        val grid = mutableGridOf(*rows)
        rows.forEach { it.clear() }
        assertThat(grid).isEqualTo(
            MutableGridImpl(
                listOf(
                    listOf("foo", "bar"),
                    listOf("baz", "qux"),
                    listOf("qox", "fred")
                )
            )
        )
    }

    @Test
    fun mutableGridOf_differentSizeRows() {
        assertThrows<IllegalArgumentException> {
            mutableGridOf(listOf("foo", "bar"), listOf("baz"))
        }
    }

    @Test
    fun toMutableGrid_list_noRows() {
        val rows = mutableListOf<List<Any>>()
        val grid = rows.toMutableGrid()
        rows.add(listOf())
        assertThat(grid).isEqualTo(emptyGrid<Any>())
    }

    @Test
    fun toMutableGrid_list_singleRow() {
        val rows = listOf(mutableListOf("foo", "bar", "baz"))
        val grid = rows.toMutableGrid()
        rows[0][0] = "qux"
        assertThat(grid).isEqualTo(MutableGridImpl(listOf(listOf("foo", "bar", "baz"))))
    }

    @Test
    fun toMutableGrid_list_multipleRows() {
        val rows = mutableListOf(
            mutableListOf("foo", "bar"),
            mutableListOf("baz", "qux"),
            mutableListOf("qox", "fred")
        )
        val grid = rows.toMutableGrid()
        rows[0][0] = "oof"
        rows.removeLast()
        assertThat(grid).isEqualTo(
            MutableGridImpl(
                listOf(
                    listOf("foo", "bar"),
                    listOf("baz", "qux"),
                    listOf("qox", "fred")
                )
            )
        )
    }

    @Test
    fun toMutableGrid_list_differentSizeRows() {
        val rows = listOf(listOf("foo", "bar"), listOf("baz"))
        assertThrows<IllegalArgumentException> { rows.toMutableGrid() }
    }

    @Test
    fun toMutableGrid_grid_noRows() {
        val grid = gridOf<Any>()
        assertThat(grid.toMutableGrid()).isEqualTo(emptyGrid<Any>())
    }

    @Test
    fun toMutableGrid_grid_noRows_isSafeCopy() {
        val grid = gridOf<Any>()
        val mutableGrid = grid.toMutableGrid()
        mutableGrid.addRow(listOf("foo"))
        assertThat(grid).isEqualTo(emptyGrid<Any>())
    }

    @Test
    fun toMutableGrid_grid_singleRow() {
        val grid = gridOf(listOf("foo", "bar", "baz"))
        assertThat(grid.toMutableGrid())
            .isEqualTo(MutableGridImpl(listOf(listOf("foo", "bar", "baz"))))
    }

    @Test
    fun toMutableGrid_grid_singleRow_isSafeCopy() {
        val grid = gridOf(listOf("foo", "bar", "baz"))
        val mutableGrid = grid.toMutableGrid()
        mutableGrid[0, 0] = "qux"
        assertThat(grid).isEqualTo(gridOf(listOf("foo", "bar", "baz")))
    }

    @Test
    fun toMutableGrid_grid_multipleRows() {
        val grid = gridOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        assertThat(grid.toMutableGrid()).isEqualTo(
            MutableGridImpl(
                listOf(
                    listOf("foo", "bar"),
                    listOf("baz", "qux"),
                    listOf("qox", "fred")
                )
            )
        )
    }

    @Test
    fun toMutableGrid_grid_multipleRows_isSafeCopy() {
        val grid = gridOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
        val mutableGrid = grid.toMutableGrid()
        mutableGrid[0, 0] = "oof"
        mutableGrid.removeLastRow()
        assertThat(grid)
            .isEqualTo(gridOf(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred")))
    }
}

/**
 * Sample implementation of a mutable grid with arbitrary values.
 *
 * @param E The type of values stored in the grid.
 * @param initialRows A list of rows representing the initial values stored in the grid.
 *
 * @constructor Creates a new instance of [GridImpl] with the given `initialRows`.
 */
private class MutableGridImpl<E>(initialRows: List<List<E>>) : MutableGrid<E> {
    init {
        require(initialRows.all { it.size == initialRows[0].size })
    }

    /**
     * All rows currently in the grid.
     */
    private val rowList: MutableList<MutableList<E>> = MutableList(initialRows.size) { rowIndex ->
        MutableList(initialRows[0].size) { colIndex -> initialRows[rowIndex][colIndex] }
    }

    override val height: Int = rowList.size

    override val width: Int = if (rowList.isEmpty()) 0 else rowList[0].size

    override fun get(rowIndex: Int, colIndex: Int): E = rowList[rowIndex][colIndex]

    override fun set(rowIndex: Int, colIndex: Int, value: E) {
        rowList[rowIndex][colIndex] = value
    }

    override fun row(rowIndex: Int): List<E> = rowList[rowIndex]

    override fun column(colIndex: Int): List<E> {
        Objects.checkIndex(colIndex, width)
        return rowList.map { it[colIndex] }
    }

    override fun addRow(row: List<E>) {
        require(row.isNotEmpty())
        require(rowList.isEmpty() || row.size == rowList[0].size)
        rowList.add(row.toMutableList())
    }

    override fun addColumn(column: List<E>) {
        require(column.isNotEmpty())
        require(rowList.isEmpty() || column.size == rowList.size)
        if (rowList.isEmpty()) {
            column.forEach { rowList.add(mutableListOf(it)) }
        } else {
            column.forEachIndexed { index, value -> rowList[index].add(value) }
        }
    }

    override fun removeLastRow() {
        rowList.removeLast()
    }

    override fun removeLastColumn() {
        when (width) {
            0 -> throw NoSuchElementException()
            1 -> rowList.clear()
            else -> rowList.forEach { it.removeLast() }
        }
    }

    override fun equals(other: Any?): Boolean = other is Grid<*> && rowList == other.rows()

    override fun hashCode(): Int =
        if (rowList.isEmpty()) emptyGrid<E>().hashCode() else rowList.hashCode()
}
