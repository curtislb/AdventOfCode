package com.curtislb.adventofcode.common.collection

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests [mapToMap].
 */
class MapToMapTest {
    @Test fun testWithEmptyList() {
        assertEquals(emptyMap(), emptyList<Any>().mapToMap { Pair(it, it) })
    }

    @Test fun testWithEmptyMap() {
        assertEquals(emptyMap(), emptyMap<Any, Any>().mapToMap { (key, value) -> Pair(key, value) })
    }

    @Test fun testWithNonEmptyList() {
        assertEquals(
            mapOf(Pair("1", 1L), Pair("2", 2L), Pair("3", 3L)),
            listOf(1, 2, 3).mapToMap { Pair(it.toString(), it.toLong()) }
        )
    }

    @Test fun testWithNonEmptyMap() {
        val actual = mapOf(Pair("x", 2), Pair("y", 3), Pair("z", 5)).mapToMap { (key, value) ->
            Pair(-value, key.toUpperCase())
        }
        assertEquals(mapOf(Pair(-2, "X"), Pair(-3, "Y"), Pair(-5, "Z")), actual)
    }
}
