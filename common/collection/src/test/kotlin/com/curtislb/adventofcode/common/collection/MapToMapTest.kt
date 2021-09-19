package com.curtislb.adventofcode.common.collection

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests [mapToMap].
 */
class MapToMapTest {
    @Test
    fun testWithEmptyList() {
        assertEquals(emptyMap(), emptyList<Any>().mapToMap { it to it })
    }

    @Test
    fun testWithEmptyMap() {
        assertEquals(emptyMap(), emptyMap<Any, Any>().mapToMap { (key, value) -> key to value })
    }

    @Test
    fun testWithNonEmptyList() {
        assertEquals(mapOf("1" to 1L, "2" to 2L, "3" to 3L), listOf(1, 2, 3).mapToMap { it.toString() to it.toLong() })
    }

    @Test
    fun testWithNonEmptyMap() {
        val actual = mapOf("x" to 2, "y" to 3, "z" to 5).mapToMap { (key, value) -> -value to key.toUpperCase() }
        assertEquals(mapOf(-2 to "X", -3 to "Y", -5 to "Z"), actual)
    }
}
