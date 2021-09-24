package com.curtislb.adventofcode.year2019.day08.image

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [Pixel].
 */
class PixelTest {
    @Test
    fun testFromValidInt() {
        assertEquals(Pixel.BLACK, Pixel.from(0))
        assertEquals(Pixel.WHITE, Pixel.from(1))
        assertEquals(Pixel.ALPHA, Pixel.from(2))
    }

    @Test
    fun testFromInvalidInt() {
        assertThrows<IllegalArgumentException> { Pixel.from(-1) }
    }
}
