package com.curtislb.adventofcode.common.intcode.mode

import java.math.BigInteger
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [Mode].
 */
class ModeTest {
    @Test
    fun testFromIntValue() {
        assertEquals(PositionMode, Mode.from(0))
        assertEquals(ImmediateMode, Mode.from(1))
        assertEquals(RelativeMode, Mode.from(2))
    }

    @Test
    fun testFromInvalidIntValue() {
        assertThrows<IllegalArgumentException> { Mode.from(-1) }
    }

    @Test
    fun testFromBigIntegerValue() {
        assertEquals(PositionMode, Mode.from(BigInteger.ZERO))
        assertEquals(ImmediateMode, Mode.from(BigInteger.ONE))
        assertEquals(RelativeMode, Mode.from(BigInteger.TWO))
    }

    @Test
    fun testFromInvalidBigIntegerValue() {
        assertThrows<IllegalArgumentException> { Mode.from(BigInteger("-1")) }
    }
}
