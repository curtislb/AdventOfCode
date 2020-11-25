package com.curtislb.adventofcode.common.intcode.operation

import com.curtislb.adventofcode.common.intcode.Intcode
import com.curtislb.adventofcode.common.intcode.mode.ImmediateMode
import com.curtislb.adventofcode.common.intcode.mode.PositionMode
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests [EqualsOperation].
 */
class EqualsOperationTest {
    @Test
    fun testProcessWithIncreasingParams() {
        val intcode = Intcode("8,-47,46,5,99,65")
        assertEquals(
            4,
            EqualsOperation.process(
                intcode,
                pointer = 0,
                parameters = arrayOf(BigInteger("-47"), BigInteger("46"), BigInteger("5")),
                modes = arrayOf(ImmediateMode, ImmediateMode, PositionMode)
            )
        )
        assertEquals(BigInteger("8"), intcode[0])
        assertEquals(BigInteger("-47"), intcode[1])
        assertEquals(BigInteger("46"), intcode[2])
        assertEquals(BigInteger("5"), intcode[3])
        assertEquals(BigInteger("99"), intcode[4])
        assertEquals(BigInteger.ZERO, intcode[5])
        assertEquals(BigInteger.ZERO, intcode[6])
    }

    @Test
    fun testProcessWithDecreasingParams() {
        val intcode = Intcode("8,-47,-24,5,99,-48")
        assertEquals(
            4,
            EqualsOperation.process(
                intcode,
                pointer = 0,
                parameters = arrayOf(BigInteger("-47"), BigInteger("-24"), BigInteger("5")),
                modes = arrayOf(ImmediateMode, ImmediateMode, PositionMode)
            )
        )
        assertEquals(BigInteger("8"), intcode[0])
        assertEquals(BigInteger("-47"), intcode[1])
        assertEquals(BigInteger("-24"), intcode[2])
        assertEquals(BigInteger("5"), intcode[3])
        assertEquals(BigInteger("99"), intcode[4])
        assertEquals(BigInteger.ZERO, intcode[5])
        assertEquals(BigInteger.ZERO, intcode[6])
    }

    @Test
    fun testProcessWithEqualParams() {
        val intcode = Intcode("8,40,40,5,99,-48")
        assertEquals(
            4,
            EqualsOperation.process(
                intcode,
                pointer = 0,
                parameters = arrayOf(BigInteger("-6"), BigInteger("-6"), BigInteger("5")),
                modes = arrayOf(ImmediateMode, ImmediateMode, PositionMode)
            )
        )
        assertEquals(BigInteger("8"), intcode[0])
        assertEquals(BigInteger("40"), intcode[1])
        assertEquals(BigInteger("40"), intcode[2])
        assertEquals(BigInteger("5"), intcode[3])
        assertEquals(BigInteger("99"), intcode[4])
        assertEquals(BigInteger.ONE, intcode[5])
        assertEquals(BigInteger.ZERO, intcode[6])
    }
}
