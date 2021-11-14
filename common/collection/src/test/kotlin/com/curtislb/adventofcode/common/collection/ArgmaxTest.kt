package com.curtislb.adventofcode.common.collection

import kotlin.test.assertEquals
import kotlin.test.assertNull
import org.junit.jupiter.api.Test

/**
 * Tests [argmaxByOrNull].
 */
class ArgmaxTest {
    @Test
    fun testArgmaxByOrNull() {
        assertNull(emptyList<Int>().argmaxByOrNull { it })
        assertEquals(0, listOf(23).argmaxByOrNull { it * 2 })
        assertEquals(1, listOf(40, 95, 19).argmaxByOrNull { it + 80 })
        assertEquals(2, listOf(40, 95, 19).argmaxByOrNull { -it })
        assertEquals(2, listOf("4", "-66", "103", "56", "32").argmaxByOrNull { it.toInt() })
        assertEquals(3, listOf(70, 12, 14, 22, 31).argmaxByOrNull { it % 23 })
        assertEquals(
            9,
            listOf(837, -396, 275, 976, 454, -950, -794, 194, -592, 994, 611).argmaxByOrNull { it }
        )
    }
}