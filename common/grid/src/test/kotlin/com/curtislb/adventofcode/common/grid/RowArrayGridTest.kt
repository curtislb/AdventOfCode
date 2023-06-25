package com.curtislb.adventofcode.common.grid

import java.util.LinkedList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [RowArrayGrid].
 */
class RowArrayGridTest {
    @Test
    fun height_whenEmpty() {
        val grid = rowArrayGridOf<Any>()
        assertThat(grid.height).isEqualTo(0)
    }

    @Test
    fun height_withSingleElement() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThat(grid.height).isEqualTo(1)
    }

    @Test
    fun height_withSingleRow() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThat(grid.height).isEqualTo(1)
    }

    @Test
    fun height_withMultipleRows() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThat(grid.height).isEqualTo(3)
    }

    @Test
    fun width_whenEmpty() {
        val grid = rowArrayGridOf<Any>()
        assertThat(grid.width).isEqualTo(0)
    }

    @Test
    fun width_withSingleElement() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThat(grid.width).isEqualTo(1)
    }

    @Test
    fun width_withSingleRow() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThat(grid.width).isEqualTo(3)
    }

    @Test
    fun width_withMultipleRows() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThat(grid.width).isEqualTo(2)
    }

    @Test
    fun get_indices_whenEmpty() {
        val grid = rowArrayGridOf<Any>()
        assertThrows<IndexOutOfBoundsException> { grid[0, 0] }
    }

    @Test
    fun get_indices_withSingleElement_indicesInBounds() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThat(grid[0, 0]).isEqualTo("foo")
    }

    @Test
    fun get_indices_withSingleElement_rowIndexTooSmall() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThrows<IndexOutOfBoundsException> { grid[-1, 0] }
    }

    @Test
    fun get_indices_withSingleElement_rowIndexTooLarge() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThrows<IndexOutOfBoundsException> { grid[1, 0] }
    }

    @Test
    fun get_indices_withSingleElement_colIndexTooSmall() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThrows<IndexOutOfBoundsException> { grid[0, -1] }
    }

    @Test
    fun get_indices_withSingleElement_colIndexTooLarge() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThrows<IndexOutOfBoundsException> { grid[0, 1] }
    }

    @Test
    fun get_indices_withSingleRow_pointInBounds() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThat(grid[0, 2]).isEqualTo("baz")
    }

    @Test
    fun get_indices_withSingleRow_rowIndexTooSmall() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThrows<IndexOutOfBoundsException> { grid[-1, 0] }
    }

    @Test
    fun get_indices_withSingleRow_rowIndexTooLarge() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThrows<IndexOutOfBoundsException> { grid[1, 0] }
    }

    @Test
    fun get_indices_withSingleRow_colIndexTooSmall() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThrows<IndexOutOfBoundsException> { grid[0, -1] }
    }

    @Test
    fun get_indices_withSingleRow_colIndexTooLarge() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThrows<IndexOutOfBoundsException> { grid[0, 3] }
    }

    @Test
    fun get_indices_withMultipleRows_indicesInBounds() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThat(grid[2, 1]).isEqualTo("fred")
    }

    @Test
    fun get_indices_withMultipleRows_rowIndexTooSmall() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThrows<IndexOutOfBoundsException> { grid[-1, 0] }
    }

    @Test
    fun get_indices_withMultipleRows_rowIndexTooLarge() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThrows<IndexOutOfBoundsException> { grid[3, 0] }
    }

    @Test
    fun get_indices_withMultipleRows_colIndexTooSmall() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThrows<IndexOutOfBoundsException> { grid[0, -1] }
    }

    @Test
    fun get_indices_withMultipleRows_colIndexTooLarge() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThrows<IndexOutOfBoundsException> { grid[0, 2] }
    }

    @Test
    fun row_whenEmpty() {
        val grid = rowArrayGridOf<Any>()
        assertThrows<IndexOutOfBoundsException> { grid.row(0) }
    }

    @Test
    fun row_withSingleElement_indexInBounds() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThat(grid.row(0)).containsExactly("foo")
    }

    @Test
    fun row_withSingleElement_indexTooSmall() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThrows<IndexOutOfBoundsException> { grid.row(-1) }
    }

    @Test
    fun row_withSingleElement_indexTooLarge() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThrows<IndexOutOfBoundsException> { grid.row(1) }
    }

    @Test
    fun row_withSingleRow_indexInBounds() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThat(grid.row(0)).containsExactly("foo", "bar", "baz")
    }

    @Test
    fun row_withSingleRow_indexTooSmall() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThrows<IndexOutOfBoundsException> { grid.row(-1) }
    }

    @Test
    fun row_withSingleRow_indexTooLarge() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThrows<IndexOutOfBoundsException> { grid.row(1) }
    }

    @Test
    fun row_withMultipleRows_indexInBounds() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThat(grid.row(1)).containsExactly("baz", "qux")
    }

    @Test
    fun row_withMultipleRows_indexTooSmall() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThrows<IndexOutOfBoundsException> { grid.row(-1) }
    }

    @Test
    fun row_withMultipleRows_indexTooLarge() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThrows<IndexOutOfBoundsException> { grid.row(3) }
    }

    @Test
    fun column_whenEmpty() {
        val grid = rowArrayGridOf<Any>()
        assertThrows<IndexOutOfBoundsException> { grid.column(0) }
    }

    @Test
    fun column_withSingleElement_indexInBounds() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThat(grid.column(0)).containsExactly("foo")
    }

    @Test
    fun column_withSingleElement_indexTooSmall() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThrows<IndexOutOfBoundsException> { grid.column(-1) }
    }

    @Test
    fun column_withSingleElement_indexTooLarge() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThrows<IndexOutOfBoundsException> { grid.column(1) }
    }

    @Test
    fun column_withSingleRow_indexInBounds() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThat(grid.column(1)).containsExactly("bar")
    }

    @Test
    fun column_withSingleRow_indexTooSmall() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThrows<IndexOutOfBoundsException> { grid.column(-1) }
    }

    @Test
    fun column_withSingleRow_indexTooLarge() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThrows<IndexOutOfBoundsException> { grid.column(3) }
    }

    @Test
    fun column_withMultipleRows_indexInBounds() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThat(grid.column(1)).containsExactly("bar", "qux", "fred")
    }

    @Test
    fun column_withMultipleRows_indexTooSmall() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThrows<IndexOutOfBoundsException> { grid.column(-1) }
    }

    @Test
    fun column_withMultipleRows_indexTooLarge() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThrows<IndexOutOfBoundsException> { grid.column(3) }
    }

    @Test
    fun shallowRow_whenEmpty() {
        val grid = rowArrayGridOf<Any>()
        assertThrows<IndexOutOfBoundsException> { grid.shallowRow(0) }
    }

    @Test
    fun shallowRow_withSingleElement_indexInBounds() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThat(grid.shallowRow(0))
            .isSameAs(grid.shallowRow(0))
            .containsExactly("foo")
    }

    @Test
    fun shallowRow_withSingleElement_indexTooSmall() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThrows<IndexOutOfBoundsException> { grid.shallowRow(-1) }
    }

    @Test
    fun shallowRow_withSingleElement_indexTooLarge() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThrows<IndexOutOfBoundsException> { grid.shallowRow(1) }
    }

    @Test
    fun shallowRow_withSingleRow_indexInBounds() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThat(grid.shallowRow(0))
            .isSameAs(grid.shallowRow(0))
            .containsExactly("foo", "bar", "baz")
    }

    @Test
    fun shallowRow_withSingleRow_indexTooSmall() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThrows<IndexOutOfBoundsException> { grid.shallowRow(-1) }
    }

    @Test
    fun shallowRow_withSingleRow_indexTooLarge() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThrows<IndexOutOfBoundsException> { grid.shallowRow(1) }
    }

    @Test
    fun shallowRow_withMultipleRows_indexInBounds() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThat(grid.shallowRow(1))
            .isSameAs(grid.shallowRow(1))
            .containsExactly("baz", "qux")
    }

    @Test
    fun shallowRow_withMultipleRows_indexTooSmall() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThrows<IndexOutOfBoundsException> { grid.shallowRow(-1) }
    }

    @Test
    fun shallowRow_withMultipleRows_indexTooLarge() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThrows<IndexOutOfBoundsException> { grid.shallowRow(3) }
    }

    @Test
    fun shallowRows_whenEmpty() {
        val grid = rowArrayGridOf<Any>()
        assertThat(grid.shallowRows())
            .isSameAs(grid.shallowRows())
            .isEmpty()
    }

    @Test
    fun shallowRows_withSingleElement() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThat(grid.shallowRows())
            .isSameAs(grid.shallowRows())
            .containsExactly(listOf("foo"))
    }

    @Test
    fun shallowRows_withSingleRow() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThat(grid.shallowRows())
            .isSameAs(grid.shallowRows())
            .containsExactly(listOf("foo", "bar", "baz"))
    }

    @Test
    fun shallowRows_withMultipleRows() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThat(grid.shallowRows())
            .isSameAs(grid.shallowRows())
            .containsExactly(listOf("foo", "bar"), listOf("baz", "qux"), listOf("qox", "fred"))
    }

    @Test
    fun set_indices_whenEmpty() {
        val grid = rowArrayGridOf<Any>()
        assertThrows<IndexOutOfBoundsException> { grid[0, 0] = "foo" }
    }

    @Test
    fun set_indices_withSingleElement_indicesInBounds() {
        val grid = rowArrayGridOf(listOf("foo"))
        grid[0, 0] = "bar"
        assertThat(grid).isEqualTo(rowArrayGridOf(listOf("bar")))
    }

    @Test
    fun set_indices_withSingleElement_rowIndexTooSmall() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThrows<IndexOutOfBoundsException> { grid[-1, 0] = "bar" }
    }

    @Test
    fun set_indices_withSingleElement_rowIndexTooLarge() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThrows<IndexOutOfBoundsException> { grid[1, 0] = "bar" }
    }

    @Test
    fun set_indices_withSingleElement_colIndexTooSmall() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThrows<IndexOutOfBoundsException> { grid[0, -1] = "bar" }
    }

    @Test
    fun set_indices_withSingleElement_colIndexTooLarge() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThrows<IndexOutOfBoundsException> { grid[0, 1] = "bar" }
    }

    @Test
    fun set_indices_withSingleRow_indicesInBounds() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        grid[0, 2] = "qux"
        assertThat(grid).isEqualTo(rowArrayGridOf(listOf("foo", "bar", "qux")))
    }

    @Test
    fun set_indices_withSingleRow_rowIndexTooSmall() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThrows<IndexOutOfBoundsException> { grid[-1, 0] = "qux" }
    }

    @Test
    fun set_indices_withSingleRow_rowIndexTooLarge() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThrows<IndexOutOfBoundsException> { grid[1, 0] = "qux" }
    }

    @Test
    fun set_indices_withSingleRow_colIndexTooSmall() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThrows<IndexOutOfBoundsException> { grid[0, -1] = "qux" }
    }

    @Test
    fun set_indices_withSingleRow_colIndexTooLarge() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThrows<IndexOutOfBoundsException> { grid[0, 3] = "qux" }
    }

    @Test
    fun set_indices_withMultipleRows_indicesInBounds() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        grid[2, 1] = "echo"
        assertThat(grid).isEqualTo(
            rowArrayGridOf(
                listOf("foo", "bar"),
                listOf("baz", "qux"),
                listOf("qox", "echo")
            )
        )
    }

    @Test
    fun set_indices_withMultipleRows_rowIndexTooSmall() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThrows<IndexOutOfBoundsException> { grid[-1, 0] = "echo" }
    }

    @Test
    fun set_indices_withMultipleRows_rowIndexTooLarge() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThrows<IndexOutOfBoundsException> { grid[3, 0] = "echo" }
    }

    @Test
    fun set_indices_withMultipleRows_colIndexTooSmall() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThrows<IndexOutOfBoundsException> { grid[0, -1] = "echo" }
    }

    @Test
    fun set_point_withMultipleRows_yTooLarge() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThrows<IndexOutOfBoundsException> { grid[0, 2] = "echo" }
    }

    @Test
    fun addRow_whenEmpty_emptyRow() {
        val grid = rowArrayGridOf<String>()
        assertThrows<IllegalArgumentException> { grid.addRow(emptyList()) }
    }

    @Test
    fun addRow_whenEmpty_nonEmptyRow() {
        val grid = rowArrayGridOf<String>()
        val row = mutableListOf("foo", "bar", "baz")
        grid.addRow(row)
        row.clear()
        assertThat(grid).isEqualTo(rowArrayGridOf(listOf("foo", "bar", "baz")))
    }

    @Test
    fun addRow_whenNotEmpty_emptyRow() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThrows<IllegalArgumentException> { grid.addRow(emptyList()) }
    }

    @Test
    fun addRow_whenNotEmpty_sameSizeRow() {
        val grid = rowArrayGridOf(listOf("foo", "bar"), listOf("baz", "qux"))
        val row = mutableListOf("qox", "fred")
        grid.addRow(row)
        row.clear()
        assertThat(grid).isEqualTo(
            rowArrayGridOf(
                listOf("foo", "bar"),
                listOf("baz", "qux"),
                listOf("qox", "fred")
            )
        )
    }

    @Test
    fun addRow_whenNotEmpty_differentSizeRow() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThrows<IllegalArgumentException> { grid.addRow(listOf("qox", "fred")) }
    }

    @Test
    fun addColumn_whenEmpty_emptyColumn() {
        val grid = rowArrayGridOf<String>()
        assertThrows<IllegalArgumentException> { grid.addColumn(emptyList()) }
    }

    @Test
    fun addColumn_whenEmpty_nonEmptyColumn() {
        val grid = rowArrayGridOf<String>()
        grid.addColumn(listOf("foo", "bar", "baz"))
        assertThat(grid).isEqualTo(rowArrayGridOf(listOf("foo"), listOf("bar"), listOf("baz")))
    }

    @Test
    fun addColumn_whenNotEmpty_emptyColumn() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThrows<IllegalArgumentException> { grid.addColumn(emptyList()) }
    }

    @Test
    fun addColumn_whenNotEmpty_sameSizeColumn() {
        val grid = rowArrayGridOf(listOf("foo", "bar"), listOf("baz", "qux"))
        grid.addColumn(listOf("qox", "fred"))
        assertThat(grid).isEqualTo(
            rowArrayGridOf(listOf("foo", "bar", "qox"), listOf("baz", "qux", "fred"))
        )
    }

    @Test
    fun addColumn_whenNotEmpty_differentSizeColumn() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThrows<IllegalArgumentException> { grid.addColumn(listOf("qox", "fred")) }
    }

    @Test
    fun addShallowRow_whenEmpty_emptyRow_arrayList() {
        val grid = rowArrayGridOf<String>()
        val row = ArrayList<String>()
        assertThrows<IllegalArgumentException> { grid.addShallowRow(row) }
    }

    @Test
    fun addShallowRow_whenEmpty_emptyRow_linkedList() {
        val grid = rowArrayGridOf<String>()
        val row = LinkedList<String>()
        assertThrows<IllegalArgumentException> { grid.addShallowRow(row) }
    }

    @Test
    fun addShallowRow_whenEmpty_nonEmptyRow_arrayList() {
        val grid = rowArrayGridOf<String>()
        val row = arrayListOf("foo", "bar", "baz")
        grid.addShallowRow(row)
        assertThat(grid).isEqualTo(rowArrayGridOf(listOf("foo", "bar", "baz")))
    }

    @Test
    fun addShallowRow_whenEmpty_nonEmptyRow_linkedList() {
        val grid = rowArrayGridOf<String>()
        val row = LinkedList(listOf("foo", "bar", "baz"))
        grid.addShallowRow(row)
        row.clear()
        assertThat(grid).isEqualTo(rowArrayGridOf(listOf("foo", "bar", "baz")))
    }

    @Test
    fun addShallowRow_whenNotEmpty_emptyRow_arrayList() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThrows<IllegalArgumentException> { grid.addShallowRow(ArrayList()) }
    }

    @Test
    fun addShallowRow_whenNotEmpty_emptyRow_linkedList() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThrows<IllegalArgumentException> { grid.addShallowRow(LinkedList()) }
    }

    @Test
    fun addShallowRow_whenNotEmpty_sameSizeRow_arrayList() {
        val grid = rowArrayGridOf(listOf("foo", "bar"), listOf("baz", "qux"))
        val row = arrayListOf("qox", "fred")
        grid.addShallowRow(row)
        assertThat(grid).isEqualTo(
            rowArrayGridOf(
                listOf("foo", "bar"),
                listOf("baz", "qux"),
                listOf("qox", "fred")
            )
        )
    }

    @Test
    fun addShallowRow_whenNotEmpty_sameSizeRow_linkedList() {
        val grid = rowArrayGridOf(listOf("foo", "bar"), listOf("baz", "qux"))
        val row = LinkedList(listOf("qox", "fred"))
        grid.addShallowRow(row)
        assertThat(grid).isEqualTo(
            rowArrayGridOf(
                listOf("foo", "bar"),
                listOf("baz", "qux"),
                listOf("qox", "fred")
            )
        )
    }

    @Test
    fun addShallowRow_whenNotEmpty_differentSizeRow_arrayList() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        val row = arrayListOf("qox", "fred")
        assertThrows<IllegalArgumentException> { grid.addShallowRow(row) }
    }

    @Test
    fun addShallowRow_whenNotEmpty_differentSizeRow_linkedList() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        val row = LinkedList(listOf("qox", "fred"))
        assertThrows<IllegalArgumentException> { grid.addShallowRow(row) }
    }

    @Test
    fun removeLastRow_whenEmpty() {
        val grid = rowArrayGridOf<Any>()
        assertThrows<NoSuchElementException> { grid.removeLastRow() }
    }

    @Test
    fun removeLastRow_withSingleElement() {
        val grid = rowArrayGridOf(listOf("foo"))
        grid.removeLastRow()
        assertThat(grid).isEqualTo(emptyGrid<String>())
    }

    @Test
    fun removeLastRow_withSingleRow() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        grid.removeLastRow()
        assertThat(grid).isEqualTo(emptyGrid<String>())
    }

    @Test
    fun removeLastRow_withMultipleRows() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        grid.removeLastRow()
        assertThat(grid).isEqualTo(rowArrayGridOf(listOf("foo", "bar"), listOf("baz", "qux")))
    }

    @Test
    fun removeLastColumn_whenEmpty() {
        val grid = rowArrayGridOf<Any>()
        assertThrows<NoSuchElementException> { grid.removeLastColumn() }
    }

    @Test
    fun removeLastColumn_withSingleElement() {
        val grid = rowArrayGridOf(listOf("foo"))
        grid.removeLastColumn()
        assertThat(grid).isEqualTo(emptyGrid<String>())
    }

    @Test
    fun removeLastColumn_withSingleRow() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        grid.removeLastColumn()
        assertThat(grid).isEqualTo(rowArrayGridOf(listOf("foo", "bar")))
    }

    @Test
    fun removeLastColumn_withSingleColumn() {
        val grid = rowArrayGridOf(listOf("foo"), listOf("bar"), listOf("baz"))
        grid.removeLastColumn()
        assertThat(grid).isEqualTo(emptyGrid<String>())
    }

    @Test
    fun removeLastColumn_withMultipleRows() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        grid.removeLastColumn()
        assertThat(grid).isEqualTo(rowArrayGridOf(listOf("foo"), listOf("baz"), listOf("qox")))
    }

    @Test
    fun toString_whenEmpty() {
        val grid = rowArrayGridOf<Any>()
        assertThat(grid.toString()).isEqualTo("[]")
    }

    @Test
    fun toString_withSingleElement() {
        val grid = rowArrayGridOf(listOf("foo"))
        assertThat(grid.toString()).isEqualTo("[[foo]]")
    }

    @Test
    fun toString_withSingleRow() {
        val grid = rowArrayGridOf(listOf("foo", "bar", "baz"))
        assertThat(grid.toString()).isEqualTo("[[foo, bar, baz]]")
    }

    @Test
    fun toString_withMultipleRows() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThat(grid.toString()).isEqualTo(
            """
            [[foo, bar],
             [baz, qux],
             [qox, fred]]
            """.trimIndent()
        )
    }

    @Test
    fun equals_nullGrid() {
        val grid = rowArrayGridOf<Any>()
        val other: RowArrayGrid<Any>? = null
        assertThat(grid).isNotEqualTo(other)
    }

    @Test
    fun equals_emptyGrid_whenEmpty() {
        val grid = rowArrayGridOf<Any>()
        val other = emptyGrid<Any>()
        assertThat(grid).isEqualTo(other)
    }

    @Test
    fun equals_emptyGrid_whenNotEmpty() {
        val grid = rowArrayGridOf(listOf(0))
        val other = emptyGrid<Int>()
        assertThat(grid).isNotEqualTo(other)
    }

    @Test
    fun equals_nonEmptyGrid_whenEmpty() {
        val grid = rowArrayGridOf<Int>()
        val other = gridOf(listOf(0))
        assertThat(grid).isNotEqualTo(other)
    }

    @Test
    fun equals_nonEmptyGrid_withSameElements() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        val other = gridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThat(grid).isEqualTo(other)
    }

    @Test
    fun equals_nonEmptyGrid_withDifferentElements() {
        val grid = rowArrayGridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        val other = gridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("fred", "qox")
        )
        assertThat(grid).isNotEqualTo(other)
    }

    @Test
    fun hashCode_whenEmpty() {
        val grid = rowArrayGridOf<Any>()
        assertThat(grid.hashCode())
            .isEqualTo(grid.hashCode())
            .isEqualTo(emptyGrid<Any>().hashCode())
            .isEqualTo(gridOf<Any>().hashCode())
            .isEqualTo(rowArrayGridOf<Any>().hashCode())
            .isNotEqualTo(rowArrayGridOf(listOf(0)).hashCode())
    }

    @Test
    fun hashCode_whenNotEmpty() {
        val grid = rowArrayGridOf(listOf("foo", "bar"), listOf("baz", "qux"))
        assertThat(grid.hashCode())
            .isEqualTo(grid.hashCode())
            .isNotEqualTo(emptyGrid<Any>().hashCode())
            .isEqualTo(gridOf(listOf("foo", "bar"), listOf("baz", "qux")).hashCode())
            .isEqualTo(rowArrayGridOf(listOf("foo", "bar"), listOf("baz", "qux")).hashCode())
            .isNotEqualTo(rowArrayGridOf(listOf("foo", "bar"), listOf("qux", "baz")).hashCode())
    }

    @Test
    fun constructor_withInit_negativeHeight_negativeWidth() {
        assertThrows<IllegalArgumentException> {
            RowArrayGrid(height = -1, width = -1) { _, _ -> "foo" }
        }
    }

    @Test
    fun constructor_withInit_negativeHeight_zeroWidth() {
        assertThrows<IllegalArgumentException> {
            RowArrayGrid(height = -1, width = 0) { _, _ -> "foo" }
        }
    }

    @Test
    fun constructor_withInit_negativeHeight_positiveWidth() {
        assertThrows<IllegalArgumentException> {
            RowArrayGrid(height = -1, width = 1) { _, _ -> "foo" }
        }
    }

    @Test
    fun constructor_withInit_zeroHeight_negativeWidth() {
        assertThrows<IllegalArgumentException> {
            RowArrayGrid(height = 0, width = -1) { _, _ -> "foo" }
        }
    }

    @Test
    fun constructor_withInit_zeroHeight_zeroWidth() {
        val grid = RowArrayGrid(height = 0, width = 0) { _, _ -> "foo" }
        assertThat(grid).isEqualTo(emptyGrid<String>())
    }

    @Test
    fun constructor_withInit_zeroHeight_positiveWidth() {
        assertThrows<IllegalArgumentException> {
            RowArrayGrid(height = 0, width = 1) { _, _ -> "foo" }
        }
    }

    @Test
    fun constructor_withInit_positiveHeight_negativeWidth() {
        assertThrows<IllegalArgumentException> {
            RowArrayGrid(height = 1, width = -1) { _, _ -> "foo" }
        }
    }

    @Test
    fun constructor_withInit_positiveHeight_zeroWidth() {
        assertThrows<IllegalArgumentException> {
            RowArrayGrid(height = 1, width = 0) { _, _ -> "foo" }
        }
    }

    @Test
    fun constructor_withInit_singleElement() {
        val grid = RowArrayGrid(height = 1, width = 1) { _, _ -> "foo" }
        assertThat(grid).isEqualTo(rowArrayGridOf(listOf("foo")))
    }

    @Test
    fun constructor_withInit_singleRow() {
        val row = listOf("foo", "bar", "baz")
        val grid = RowArrayGrid(height = 1, width = 3) { _, colIndex -> row[colIndex] }
        assertThat(grid).isEqualTo(rowArrayGridOf(row))
    }

    @Test
    fun constructor_withInit_multipleRows() {
        val rows = listOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        val grid = RowArrayGrid(height = 3, width = 2) { rowIndex, colIndex ->
            rows[rowIndex][colIndex]
        }
        assertThat(grid).isEqualTo(
            rowArrayGridOf(
                listOf("foo", "bar"),
                listOf("baz", "qux"),
                listOf("qox", "fred")
            )
        )
    }

    @Test
    fun constructor_withGrid_noElements() {
        val grid = emptyGrid<Any>()
        assertThat(RowArrayGrid(grid)).isEqualTo(grid).isNotSameAs(grid)
    }

    @Test
    fun constructor_withGrid_singleElement() {
        val grid = gridOf(listOf("foo"))
        assertThat(RowArrayGrid(grid)).isEqualTo(grid).isNotSameAs(grid)
    }

    @Test
    fun constructor_withGrid_singleRow() {
        val grid = gridOf(listOf("foo", "bar", "baz"))
        assertThat(RowArrayGrid(grid)).isEqualTo(grid).isNotSameAs(grid)
    }

    @Test
    fun constructor_withGrid_multipleRows() {
        val grid = gridOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        assertThat(RowArrayGrid(grid)).isEqualTo(grid).isNotSameAs(grid)
    }

    @Test
    fun constructor_withRows_emptyList() {
        val rows = emptyList<List<Any>>()
        val grid = RowArrayGrid(rows)
        assertThat(grid).isEqualTo(emptyGrid<Any>())
    }

    @Test
    fun constructor_withRows_singleRow_emptyList() {
        val rows = listOf(emptyList<Any>())
        assertThrows<IllegalArgumentException> { RowArrayGrid(rows) }
    }

    @Test
    fun constructor_withRows_singleRow_singleElement() {
        val rows = listOf(listOf("foo"))
        val grid = RowArrayGrid(rows)
        assertThat(grid).isEqualTo(rowArrayGridOf(listOf("foo")))
    }

    @Test
    fun constructor_withRows_singleRow_multipleElements() {
        val rows = listOf(listOf("foo", "bar", "baz"))
        val grid = RowArrayGrid(rows)
        assertThat(grid).isEqualTo(rowArrayGridOf(listOf("foo", "bar", "baz")))
    }

    @Test
    fun constructor_withRows_multipleRows_differentLengths() {
        val rows = listOf(listOf("foo"), listOf("bar", "baz"))
        assertThrows<IllegalArgumentException> { RowArrayGrid(rows) }
    }

    @Test
    fun constructor_withRows_multipleRows_equalLengths() {
        val rows = listOf(
            listOf("foo", "bar"),
            listOf("baz", "qux"),
            listOf("qox", "fred")
        )
        val grid = RowArrayGrid(rows)
        assertThat(grid).isEqualTo(
            rowArrayGridOf(
                listOf("foo", "bar"),
                listOf("baz", "qux"),
                listOf("qox", "fred")
            )
        )
    }
}
