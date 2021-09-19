package com.curtislb.adventofcode.year2019.day08.image

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

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

    @Test(expected = IllegalArgumentException::class)
    fun testFromInvalidInt() {
        Pixel.from(-1)
    }
}
