package com.curtislb.adventofcode.common.intcode.operation

import com.curtislb.adventofcode.common.intcode.Intcode
import com.curtislb.adventofcode.common.intcode.mode.ImmediateMode
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests [MoveRelativeBaseOperation].
 */
class MoveRelativeBaseOperationTest {
    @Test fun testProcess() {
        val intcode = Intcode("9,13,9,0,9,-19,99")
        assertEquals(0, intcode.relativeBase)

        assertEquals(
            2,
            MoveRelativeBaseOperation.process(
                intcode,
                pointer = 0,
                parameters = arrayOf(BigInteger("13")),
                modes = arrayOf(ImmediateMode)
            )
        )
        assertEquals(13, intcode.relativeBase)

        assertEquals(
            4,
            MoveRelativeBaseOperation.process(
                intcode,
                pointer = 2,
                parameters = arrayOf(BigInteger.ZERO),
                modes = arrayOf(ImmediateMode)
            )
        )
        assertEquals(13, intcode.relativeBase)

        assertEquals(
            6,
            MoveRelativeBaseOperation.process(
                intcode,
                pointer = 4,
                parameters = arrayOf(BigInteger("-19")),
                modes = arrayOf(ImmediateMode)
            )
        )
        assertEquals(-6, intcode.relativeBase)

        assertEquals(BigInteger("9"), intcode[0])
        assertEquals(BigInteger("13"), intcode[1])
        assertEquals(BigInteger("9"), intcode[2])
        assertEquals(BigInteger.ZERO, intcode[3])
        assertEquals(BigInteger("9"), intcode[4])
        assertEquals(BigInteger("-19"), intcode[5])
        assertEquals(BigInteger("99"), intcode[6])
        assertEquals(BigInteger.ZERO, intcode[7])
    }
}
