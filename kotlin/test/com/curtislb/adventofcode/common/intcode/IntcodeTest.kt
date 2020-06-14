package com.curtislb.adventofcode.common.intcode

import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Tests [Intcode].
 */
class IntcodeTest {
    @Test(expected = IllegalArgumentException::class)
    fun testWithEmptyProgram() {
        Intcode("")
    }

    @Test fun testWithNoOpProgram() {
        val intcode = Intcode("99")
        assertFalse(intcode.isDone)
        assertFalse(intcode.isPaused)
        assertEquals(BigInteger("99"), intcode[0])
        assertEquals(BigInteger.ZERO, intcode[1])

        intcode.run()
        assertTrue(intcode.isDone)
        assertFalse(intcode.isPaused)
        assertEquals(BigInteger("99"), intcode[0])
        assertEquals(BigInteger.ZERO, intcode[1])

        intcode.reset()
        assertFalse(intcode.isDone)
        assertFalse(intcode.isPaused)
        assertEquals(BigInteger("99"), intcode[0])
        assertEquals(BigInteger.ZERO, intcode[1])
    }

    @Test fun testHandlesInput() {
        val intcode = Intcode("3,15,3,22,3,32,99")
        assertFalse(intcode.isDone)
        assertFalse(intcode.isPaused)
        assertEquals(BigInteger.ZERO, intcode[15])
        assertEquals(BigInteger.ZERO, intcode[22])
        assertEquals(BigInteger.ZERO, intcode[32])

        intcode.run()
        assertFalse(intcode.isDone)
        assertTrue(intcode.isPaused)
        assertEquals(BigInteger.ZERO, intcode[15])
        assertEquals(BigInteger.ZERO, intcode[22])
        assertEquals(BigInteger.ZERO, intcode[32])

        intcode.sendInput(BigInteger("48"))
        assertFalse(intcode.isDone)
        assertTrue(intcode.isPaused)
        assertEquals(BigInteger.ZERO, intcode[15])
        assertEquals(BigInteger.ZERO, intcode[22])
        assertEquals(BigInteger.ZERO, intcode[32])

        intcode.run()
        assertFalse(intcode.isDone)
        assertTrue(intcode.isPaused)
        assertEquals(BigInteger("48"), intcode[15])
        assertEquals(BigInteger.ZERO, intcode[22])
        assertEquals(BigInteger.ZERO, intcode[32])

        intcode.sendInput(BigInteger("-2"), BigInteger("11"), BigInteger("-19"))
        intcode.run()
        assertTrue(intcode.isDone)
        assertFalse(intcode.isPaused)
        assertEquals(BigInteger("48"), intcode[15])
        assertEquals(BigInteger("-2"), intcode[22])
        assertEquals(BigInteger("11"), intcode[32])
    }

    @Test fun testHandlesOutput() {
        val outputs = mutableListOf<BigInteger>()
        val intcode = Intcode("4,5,4,6,99,-74,16") { outputs.add(it) }
        assertTrue(outputs.isEmpty())

        intcode.run()
        assertEquals(listOf(BigInteger("-74"), BigInteger("16")), outputs.toList())

        intcode.reset()
        assertEquals(listOf(BigInteger("-74"), BigInteger("16")), outputs.toList())

        intcode.run()
        assertEquals(listOf(BigInteger("-74"), BigInteger("16"), BigInteger("-74"), BigInteger("16")), outputs.toList())
    }
}
