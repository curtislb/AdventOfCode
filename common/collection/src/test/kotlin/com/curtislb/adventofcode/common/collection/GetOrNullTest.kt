package com.curtislb.adventofcode.common.collection

import kotlin.test.assertEquals
import kotlin.test.assertNull
import org.junit.jupiter.api.Test

/**
 * Tests [getOrNull] with row and column indices.
 */
class GetOrNullTest {
    @Test
    fun testWithEmptyList() {
        assertNull(emptyList<List<Any>>().getOrNull(0, 0))
    }

    @Test
    fun testWithOneByOneArray() {
        val array = listOf(listOf(71))
        assertEquals(71, array.getOrNull(0, 0))
        assertNull(array.getOrNull(0, 1))
        assertNull(array.getOrNull(1, 0))
        assertNull(array.getOrNull(1, 1))
        assertNull(array.getOrNull(0, -1))
        assertNull(array.getOrNull(-1, 0))
        assertNull(array.getOrNull(-1, -1))
    }

    @Test
    fun testWithRectangularArray() {
        val array = listOf(
            listOf(33, 82, -60, 85),
            listOf(11, 54, 10, 64),
            listOf(-2, -8, 69, -23)
        )
        assertEquals(33, array.getOrNull(0, 0))
        assertEquals(82, array.getOrNull(0, 1))
        assertEquals(-60, array.getOrNull(0, 2))
        assertEquals(85, array.getOrNull(0, 3))
        assertEquals(11, array.getOrNull(1, 0))
        assertEquals(54, array.getOrNull(1, 1))
        assertEquals(10, array.getOrNull(1, 2))
        assertEquals(64, array.getOrNull(1, 3))
        assertEquals(-2, array.getOrNull(2, 0))
        assertEquals(-8, array.getOrNull(2, 1))
        assertEquals(69, array.getOrNull(2, 2))
        assertEquals(-23, array.getOrNull(2, 3))
        assertNull(array.getOrNull(0, 4))
        assertNull(array.getOrNull(0, -1))
        assertNull(array.getOrNull(3, 1))
        assertNull(array.getOrNull(-1, 1))
        assertNull(array.getOrNull(-1, 4))
        assertNull(array.getOrNull(3, -1))
        assertNull(array.getOrNull(-1, -1))
    }

    @Test
    fun testWithIrregularArray() {
        val array = listOf(
            listOf(47, 88, 45, 25, 14),
            listOf(77, -56),
            listOf(),
            listOf(21, 62, -93)
        )
        assertEquals(47, array.getOrNull(0, 0))
        assertEquals(88, array.getOrNull(0, 1))
        assertEquals(45, array.getOrNull(0, 2))
        assertEquals(25, array.getOrNull(0, 3))
        assertEquals(14, array.getOrNull(0, 4))
        assertEquals(77, array.getOrNull(1, 0))
        assertEquals(-56, array.getOrNull(1, 1))
        assertEquals(21, array.getOrNull(3, 0))
        assertEquals(62, array.getOrNull(3, 1))
        assertEquals(-93, array.getOrNull(3, 2))
        assertNull(array.getOrNull(-1, 0))
        assertNull(array.getOrNull(0, 5))
        assertNull(array.getOrNull(0, -1))
        assertNull(array.getOrNull(1, 2))
        assertNull(array.getOrNull(1, -1))
        assertNull(array.getOrNull(2, 0))
        assertNull(array.getOrNull(2, -1))
        assertNull(array.getOrNull(3, 3))
        assertNull(array.getOrNull(3, -1))
        assertNull(array.getOrNull(4, 1))
    }
}