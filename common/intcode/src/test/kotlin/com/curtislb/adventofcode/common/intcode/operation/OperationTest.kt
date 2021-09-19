package com.curtislb.adventofcode.common.intcode.operation

import java.math.BigInteger
import kotlin.test.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

/**
 * Tests [Operation].
 */
class OperationTest {
    @Test
    fun testFromIntValue() {
        assertEquals(AddOperation, Operation.from(1))
        assertEquals(MultiplyOperation, Operation.from(2))
        assertEquals(InputOperation, Operation.from(3))
        assertEquals(OutputOperation, Operation.from(4))
        assertEquals(JumpIfTrueOperation, Operation.from(5))
        assertEquals(JumpIfFalseOperation, Operation.from(6))
        assertEquals(LessThanOperation, Operation.from(7))
        assertEquals(EqualsOperation, Operation.from(8))
        assertEquals(MoveRelativeBaseOperation, Operation.from(9))
        assertEquals(StopOperation, Operation.from(99))
    }

    @Test
    fun testFromInvalidIntValue() {
        assertThrows(IllegalArgumentException::class.java) { Operation.from(0) }
    }

    @Test
    fun testFromBigIntegerValue() {
        assertEquals(AddOperation, Operation.from(BigInteger.ONE))
        assertEquals(MultiplyOperation, Operation.from(BigInteger.TWO))
        assertEquals(InputOperation, Operation.from(BigInteger("3")))
        assertEquals(OutputOperation, Operation.from(BigInteger("4")))
        assertEquals(JumpIfTrueOperation, Operation.from(BigInteger("5")))
        assertEquals(JumpIfFalseOperation, Operation.from(BigInteger("6")))
        assertEquals(LessThanOperation, Operation.from(BigInteger("7")))
        assertEquals(EqualsOperation, Operation.from(BigInteger("8")))
        assertEquals(MoveRelativeBaseOperation, Operation.from(BigInteger("9")))
        assertEquals(StopOperation, Operation.from(BigInteger("99")))
    }

    @Test
    fun testFromInvalidBigIntegerValue() {
        assertThrows(IllegalArgumentException::class.java) { Operation.from(BigInteger.ZERO) }
    }
}
