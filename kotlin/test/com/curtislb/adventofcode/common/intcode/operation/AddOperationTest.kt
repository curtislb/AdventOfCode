package com.curtislb.adventofcode.common.intcode.operation

import com.curtislb.adventofcode.common.intcode.Intcode
import com.curtislb.adventofcode.common.intcode.mode.ImmediateMode
import com.curtislb.adventofcode.common.intcode.mode.PositionMode
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests [AddOperation].
 */
class AddOperationTest {
    @Test
    fun testProcessWithImmediateParams() {
        val intcode = Intcode("1,98,-35,5,99,65")
        assertEquals(
            4,
            AddOperation.process(
                intcode,
                pointer = 0,
                parameters = arrayOf(BigInteger("98"), BigInteger("-35"), BigInteger("5")),
                modes = arrayOf(ImmediateMode, ImmediateMode, PositionMode)
            )
        )
        assertEquals(BigInteger.ONE, intcode[0])
        assertEquals(BigInteger("98"), intcode[1])
        assertEquals(BigInteger("-35"), intcode[2])
        assertEquals(BigInteger("5"), intcode[3])
        assertEquals(BigInteger("99"), intcode[4])
        assertEquals(BigInteger("63"), intcode[5])
        assertEquals(BigInteger.ZERO, intcode[6])
    }

    @Test
    fun testProcessWithPositionParams() {
        val intcode = Intcode("4,-60,1,0,7,5,99,42")
        assertEquals(
            6,
            AddOperation.process(
                intcode,
                pointer = 2,
                parameters = arrayOf(BigInteger.ZERO, BigInteger("7"), BigInteger("5")),
                modes = Array(3) { PositionMode }
            )
        )
        assertEquals(BigInteger("4"), intcode[0])
        assertEquals(BigInteger("-60"), intcode[1])
        assertEquals(BigInteger.ONE, intcode[2])
        assertEquals(BigInteger.ZERO, intcode[3])
        assertEquals(BigInteger("7"), intcode[4])
        assertEquals(BigInteger("46"), intcode[5])
        assertEquals(BigInteger("99"), intcode[6])
        assertEquals(BigInteger("42"), intcode[7])
        assertEquals(BigInteger.ZERO, intcode[8])
    }
}
