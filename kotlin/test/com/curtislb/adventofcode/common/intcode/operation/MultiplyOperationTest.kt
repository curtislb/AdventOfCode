package com.curtislb.adventofcode.common.intcode.operation

import com.curtislb.adventofcode.common.intcode.Intcode
import com.curtislb.adventofcode.common.intcode.mode.ImmediateMode
import com.curtislb.adventofcode.common.intcode.mode.PositionMode
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests [MultiplyOperation].
 */
class MultiplyOperationTest {
    @Test
    fun testProcessWithImmediateParams() {
        val intcode = Intcode("2,51,-22,5,99,28")
        assertEquals(
            4,
            MultiplyOperation.process(
                intcode,
                pointer = 0,
                parameters = arrayOf(BigInteger("51"), BigInteger("-22"), BigInteger("5")),
                modes = arrayOf(ImmediateMode, ImmediateMode, PositionMode)
            )
        )
        assertEquals(BigInteger.TWO, intcode[0])
        assertEquals(BigInteger("51"), intcode[1])
        assertEquals(BigInteger("-22"), intcode[2])
        assertEquals(BigInteger("5"), intcode[3])
        assertEquals(BigInteger("99"), intcode[4])
        assertEquals(BigInteger("-1122"), intcode[5])
        assertEquals(BigInteger.ZERO, intcode[6])
    }

    @Test
    fun testProcessWithPositionParams() {
        val intcode = Intcode("4,65,2,1,7,5,99,6")
        assertEquals(
            6,
            MultiplyOperation.process(
                intcode,
                pointer = 2,
                parameters = arrayOf(BigInteger.ONE, BigInteger("7"), BigInteger("5")),
                modes = Array(3) { PositionMode }
            )
        )
        assertEquals(BigInteger("4"), intcode[0])
        assertEquals(BigInteger("65"), intcode[1])
        assertEquals(BigInteger.TWO, intcode[2])
        assertEquals(BigInteger.ONE, intcode[3])
        assertEquals(BigInteger("7"), intcode[4])
        assertEquals(BigInteger("390"), intcode[5])
        assertEquals(BigInteger("99"), intcode[6])
        assertEquals(BigInteger("6"), intcode[7])
        assertEquals(BigInteger.ZERO, intcode[8])
    }
}
