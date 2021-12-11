package com.curtislb.adventofcode.common.collection

import kotlin.math.abs
import kotlin.test.assertEquals
import kotlin.test.assertNull
import org.junit.jupiter.api.Test

/**
 * Tests [minAndMaxByOrNull] and [minAndMaxOfOrNull].
 */
class MinAndMaxTest {
    @Test
    fun testMinAndMaxByOrNull() {
        assertNull(emptyList<Int>().minAndMaxByOrNull { it })
        assertEquals(MinMax(0, 0), listOf(0).minAndMaxByOrNull { it })
        assertEquals(MinMax(5, 5), listOf(5).minAndMaxByOrNull { it })
        assertEquals(MinMax(5, 5), listOf(5).minAndMaxByOrNull { -it })
        assertEquals(MinMax(-1, -1), listOf(-1).minAndMaxByOrNull { it })
        assertEquals(MinMax(-1, -1), listOf(-1).minAndMaxByOrNull { it * 2 })
        assertEquals(MinMax(1, 2), listOf(1, 2).minAndMaxByOrNull { it })
        assertEquals(MinMax(2, 1), listOf(1, 2).minAndMaxByOrNull { -it })
        assertEquals(
            MinMax(-76, 29),
            listOf(29, 7, -76, -58, -17).minAndMaxByOrNull { it }
        )
        assertEquals(
            MinMax(7, -76),
            listOf(29, 7, -76, -58, -17).minAndMaxByOrNull { abs(it) }
        )
        assertEquals(
            MinMax(-76, -17),
            listOf(29, 7, -76, -58, -17).minAndMaxByOrNull { element ->
                if (element > 0) element - 50 else element
            }
        )
    }

    @Test
    fun testMinAndMaxOfOrNull() {
        assertNull(emptyList<Int>().minAndMaxOfOrNull { it })
        assertEquals(MinMax(0, 0), listOf(0).minAndMaxOfOrNull { it })
        assertEquals(MinMax(5, 5), listOf(5).minAndMaxOfOrNull { it })
        assertEquals(MinMax(-5, -5), listOf(5).minAndMaxOfOrNull { -it })
        assertEquals(MinMax(-1, -1), listOf(-1).minAndMaxOfOrNull { it })
        assertEquals(MinMax(-2, -2), listOf(-1).minAndMaxOfOrNull { it * 2 })
        assertEquals(MinMax(1, 2), listOf(1, 2).minAndMaxOfOrNull { it })
        assertEquals(MinMax(-2, -1), listOf(1, 2).minAndMaxOfOrNull { -it })
        assertEquals(MinMax(-76, 29), listOf(29, 7, -76, -58, -17).minAndMaxOfOrNull { it })
        assertEquals(
            MinMax(7, 76),
            listOf(29, 7, -76, -58, -17).minAndMaxOfOrNull { abs(it) }
        )
        assertEquals(
            MinMax(-26, 33),
            listOf(29, 7, -76, -58, -17).minAndMaxOfOrNull { element ->
                if (element < 0) element + 50 else element
            }
        )
    }
}
