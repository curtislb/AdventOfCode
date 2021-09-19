package com.curtislb.adventofcode.common.intcode.operation

import com.curtislb.adventofcode.common.intcode.Intcode
import com.curtislb.adventofcode.common.intcode.mode.ImmediateMode
import java.math.BigInteger
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests [JumpIfFalseOperation].
 */
class JumpIfFalseOperationTest {
    @Test
    fun testProcessWithZeroParam() {
        val intcode = Intcode("5,0,4,99,3,17")
        assertEquals(
            4,
            JumpIfFalseOperation.process(
                intcode,
                pointer = 0,
                parameters = arrayOf(BigInteger.ZERO, BigInteger("4")),
                modes = Array(2) { ImmediateMode }
            )
        )
        assertEquals(BigInteger("5"), intcode[0])
        assertEquals(BigInteger.ZERO, intcode[1])
        assertEquals(BigInteger("4"), intcode[2])
        assertEquals(BigInteger("99"), intcode[3])
        assertEquals(BigInteger("3"), intcode[4])
        assertEquals(BigInteger("17"), intcode[5])
        assertEquals(BigInteger.ZERO, intcode[6])
    }

    @Test
    fun testProcessWithPositiveParam() {
        val intcode = Intcode("5,25,4,99,3,17")
        assertEquals(
            3,
            JumpIfFalseOperation.process(
                intcode,
                pointer = 0,
                parameters = arrayOf(BigInteger("25"), BigInteger("4")),
                modes = Array(2) { ImmediateMode }
            )
        )
        assertEquals(BigInteger("5"), intcode[0])
        assertEquals(BigInteger("25"), intcode[1])
        assertEquals(BigInteger("4"), intcode[2])
        assertEquals(BigInteger("99"), intcode[3])
        assertEquals(BigInteger("3"), intcode[4])
        assertEquals(BigInteger("17"), intcode[5])
        assertEquals(BigInteger.ZERO, intcode[6])
    }

    @Test
    fun testProcessWithNegativeParam() {
        val intcode = Intcode("5,-94,4,99,3,17")
        assertEquals(
            3,
            JumpIfFalseOperation.process(
                intcode,
                pointer = 0,
                parameters = arrayOf(BigInteger("-94"), BigInteger("4")),
                modes = Array(2) { ImmediateMode }
            )
        )
        assertEquals(BigInteger("5"), intcode[0])
        assertEquals(BigInteger("-94"), intcode[1])
        assertEquals(BigInteger("4"), intcode[2])
        assertEquals(BigInteger("99"), intcode[3])
        assertEquals(BigInteger("3"), intcode[4])
        assertEquals(BigInteger("17"), intcode[5])
        assertEquals(BigInteger.ZERO, intcode[6])
    }
}

