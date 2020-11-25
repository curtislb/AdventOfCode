package com.curtislb.adventofcode.common.intcode.operation

import com.curtislb.adventofcode.common.intcode.Intcode
import com.curtislb.adventofcode.common.intcode.mode.ImmediateMode
import com.curtislb.adventofcode.common.intcode.mode.PositionMode
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests [LessThanOperation].
 */
class LessThanOperationTest {
    @Test
    fun testProcessWithIncreasingParams() {
        val intcode = Intcode("7,-50,10,5,99,-48")
        assertEquals(
            4,
            LessThanOperation.process(
                intcode,
                pointer = 0,
                parameters = arrayOf(BigInteger("-50"), BigInteger("10"), BigInteger("5")),
                modes = arrayOf(ImmediateMode, ImmediateMode, PositionMode)
            )
        )
        assertEquals(BigInteger("7"), intcode[0])
        assertEquals(BigInteger("-50"), intcode[1])
        assertEquals(BigInteger.TEN, intcode[2])
        assertEquals(BigInteger("5"), intcode[3])
        assertEquals(BigInteger("99"), intcode[4])
        assertEquals(BigInteger.ONE, intcode[5])
        assertEquals(BigInteger.ZERO, intcode[6])
    }

    @Test
    fun testProcessWithDecreasingParams() {
        val intcode = Intcode("7,49,37,5,99,-48")
        assertEquals(
            4,
            LessThanOperation.process(
                intcode,
                pointer = 0,
                parameters = arrayOf(BigInteger("49"), BigInteger("37"), BigInteger("5")),
                modes = arrayOf(ImmediateMode, ImmediateMode, PositionMode)
            )
        )
        assertEquals(BigInteger("7"), intcode[0])
        assertEquals(BigInteger("49"), intcode[1])
        assertEquals(BigInteger("37"), intcode[2])
        assertEquals(BigInteger("5"), intcode[3])
        assertEquals(BigInteger("99"), intcode[4])
        assertEquals(BigInteger.ZERO, intcode[5])
        assertEquals(BigInteger.ZERO, intcode[6])
    }

    @Test
    fun testProcessWithEqualParams() {
        val intcode = Intcode("7,-6,-6,5,99,-48")
        assertEquals(
            4,
            LessThanOperation.process(
                intcode,
                pointer = 0,
                parameters = arrayOf(BigInteger("-6"), BigInteger("-6"), BigInteger("5")),
                modes = arrayOf(ImmediateMode, ImmediateMode, PositionMode)
            )
        )
        assertEquals(BigInteger("7"), intcode[0])
        assertEquals(BigInteger("-6"), intcode[1])
        assertEquals(BigInteger("-6"), intcode[2])
        assertEquals(BigInteger("5"), intcode[3])
        assertEquals(BigInteger("99"), intcode[4])
        assertEquals(BigInteger.ZERO, intcode[5])
        assertEquals(BigInteger.ZERO, intcode[6])
    }
}
