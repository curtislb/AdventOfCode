package com.curtislb.adventofcode.common.intcode.operation

import com.curtislb.adventofcode.common.intcode.Intcode
import com.curtislb.adventofcode.common.intcode.mode.PositionMode
import org.junit.Before
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests [InputOperation].
 */
class InputOperationTest {
    private lateinit var intcode: Intcode

    @Before fun setUp() {
        intcode = Intcode("4,80,3,1,99")
    }

    @Test fun testProcessWithNoNextInput() {
        assertEquals(
            2,
            InputOperation.process(
                intcode,
                pointer = 2,
                parameters = arrayOf(BigInteger.ONE),
                modes = arrayOf(PositionMode)
            )
        )
        assertEquals(BigInteger("4"), intcode[0])
        assertEquals(BigInteger("80"), intcode[1])
        assertEquals(BigInteger("3"), intcode[2])
        assertEquals(BigInteger.ONE, intcode[3])
        assertEquals(BigInteger("99"), intcode[4])
        assertEquals(BigInteger.ZERO, intcode[5])
    }

    @Test fun testProcessWithNextInput() {
        intcode.sendInput(BigInteger("-75"))
        assertEquals(
            4,
            InputOperation.process(
                intcode,
                pointer = 2,
                parameters = arrayOf(BigInteger.ONE),
                modes = arrayOf(PositionMode)
            )
        )
        assertEquals(BigInteger("4"), intcode[0])
        assertEquals(BigInteger("-75"), intcode[1])
        assertEquals(BigInteger("3"), intcode[2])
        assertEquals(BigInteger.ONE, intcode[3])
        assertEquals(BigInteger("99"), intcode[4])
        assertEquals(BigInteger.ZERO, intcode[5])
    }
}
