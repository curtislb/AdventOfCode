package com.curtislb.adventofcode.common.collection

import kotlin.math.abs
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests [minAndMaxByOrNull] and [minAndMaxOfOrNull].
 */
class MinAndMaxTest {
    @Test
    fun testMinAndMaxByOrNull() {
        assertEquals(Pair(null, null), emptyList<Int>().minAndMaxByOrNull { it })
        assertEquals(Pair(0, 0), listOf(0).minAndMaxByOrNull { it })
        assertEquals(Pair(5, 5), listOf(5).minAndMaxByOrNull { it })
        assertEquals(Pair(5, 5), listOf(5).minAndMaxByOrNull { -it })
        assertEquals(Pair(-1, -1), listOf(-1).minAndMaxByOrNull { it })
        assertEquals(Pair(-1, -1), listOf(-1).minAndMaxByOrNull { it * 2 })
        assertEquals(Pair(1, 2), listOf(1, 2).minAndMaxByOrNull { it })
        assertEquals(Pair(2, 1), listOf(1, 2).minAndMaxByOrNull { -it })
        assertEquals(Pair(-76, 29), listOf(29, 7, -76, -58, -17).minAndMaxByOrNull { it })
        assertEquals(Pair(7, -76), listOf(29, 7, -76, -58, -17).minAndMaxByOrNull { abs(it) })
        assertEquals(Pair(-76, -17), listOf(29, 7, -76, -58, -17).minAndMaxByOrNull { element ->
            if (element > 0) element - 50 else element
        })
    }

    @Test
    fun testMinAndMaxOfOrNull() {
        assertEquals(Pair(null, null), emptyList<Int>().minAndMaxOfOrNull { it })
        assertEquals(Pair(0, 0), listOf(0).minAndMaxOfOrNull { it })
        assertEquals(Pair(5, 5), listOf(5).minAndMaxOfOrNull { it })
        assertEquals(Pair(-5, -5), listOf(5).minAndMaxOfOrNull { -it })
        assertEquals(Pair(-1, -1), listOf(-1).minAndMaxOfOrNull { it })
        assertEquals(Pair(-2, -2), listOf(-1).minAndMaxOfOrNull { it * 2 })
        assertEquals(Pair(1, 2), listOf(1, 2).minAndMaxOfOrNull { it })
        assertEquals(Pair(-2, -1), listOf(1, 2).minAndMaxOfOrNull { -it })
        assertEquals(Pair(-76, 29), listOf(29, 7, -76, -58, -17).minAndMaxOfOrNull { it })
        assertEquals(Pair(7, 76), listOf(29, 7, -76, -58, -17).minAndMaxOfOrNull { abs(it) })
        assertEquals(Pair(-26, 33), listOf(29, 7, -76, -58, -17).minAndMaxOfOrNull { element ->
            if (element < 0) element + 50 else element
        })
    }
}