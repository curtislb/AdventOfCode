package com.curtislb.adventofcode.common.intcode.mode

import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests [Mode].
 */
class ModeTest {
    @Test
    fun testConstructFromIntValue() {
        assertEquals(PositionMode, Mode.from(0))
        assertEquals(ImmediateMode, Mode.from(1))
        assertEquals(RelativeMode, Mode.from(2))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testConstructFromInvalidIntValue() {
        Mode.from(-1)
    }

    @Test
    fun testConstructFromBigIntegerValue() {
        assertEquals(PositionMode, Mode.from(BigInteger.ZERO))
        assertEquals(ImmediateMode, Mode.from(BigInteger.ONE))
        assertEquals(RelativeMode, Mode.from(BigInteger.TWO))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testConstructFromInvalidBigIntegerValue() {
        Mode.from(BigInteger("-1"))
    }
}
