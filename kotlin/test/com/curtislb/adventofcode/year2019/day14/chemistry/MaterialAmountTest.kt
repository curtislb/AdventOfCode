package com.curtislb.adventofcode.year2019.day14.chemistry

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests [MaterialAmount].
 */
class MaterialAmountTest {
    @Test(expected = IllegalArgumentException::class)
    fun testConstructWithInvalidMaterial() {
        MaterialAmount("abc xyz", 1L)
    }

    @Test
    fun testToString() {
        assertEquals("0 foo", MaterialAmount("foo", 0L).toString())
        assertEquals("1 BAR", MaterialAmount("BAR", 1L).toString())
        assertEquals("-2 Baz", MaterialAmount("Baz", -2L).toString())
        assertEquals("5059 qu.X", MaterialAmount("qu.X", 5059L).toString())
    }

    @Test
    fun testFromString() {
        assertEquals(MaterialAmount("lorem", 0L), MaterialAmount.from("0 lorem"))
        assertEquals(MaterialAmount("Ipsum", 2L), MaterialAmount.from("2 Ipsum"))
        assertEquals(MaterialAmount("DOLOR", -3L), MaterialAmount.from("-3 DOLOR"))
        assertEquals(MaterialAmount("sit_Amet", 8904L), MaterialAmount.from("8904 sit_Amet"))
    }
}
