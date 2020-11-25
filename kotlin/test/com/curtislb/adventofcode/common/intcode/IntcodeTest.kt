package com.curtislb.adventofcode.common.intcode

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.OutputStream
import java.io.PrintStream
import java.math.BigInteger
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Tests [Intcode].
 */
class IntcodeTest {
    @get:Rule val temporaryFolder = TemporaryFolder()

    private lateinit var printedChars: MutableList<Char>
    private lateinit var standardOutput: PrintStream

    @Before
    fun setUp() {
        printedChars = mutableListOf()
        standardOutput = System.out
        System.setOut(PrintStream(object : OutputStream() {
            override fun write(char: Int) {
                printedChars.add(char.toChar())
            }
        }))
    }

    @After
    fun tearDown() = System.setOut(standardOutput)

    @Test(expected = IllegalArgumentException::class)
    fun testWithEmptyProgram() {
        Intcode("")
    }

    @Test
    fun testConstructFromFile() {
        val file = temporaryFolder.newFile().apply { writeText("4,5,4,6,99,-2,11") }

        val intcode = Intcode(file)
        assertEquals(BigInteger("4"), intcode[0])
        assertEquals(BigInteger("5"), intcode[1])
        assertEquals(BigInteger("4"), intcode[2])
        assertEquals(BigInteger("6"), intcode[3])
        assertEquals(BigInteger("99"), intcode[4])
        assertEquals(BigInteger("-2"), intcode[5])
        assertEquals(BigInteger("11"), intcode[6])

        intcode.run()
        assertEquals("-2%n11%n".format(), printedChars.joinToString(separator = ""))
    }

    @Test
    fun testConstructFromFileWithCustomOutputFunction() {
        val file = temporaryFolder.newFile().apply { writeText("4,5,4,6,99,3,13") }

        val outputs = mutableListOf<BigInteger>()
        val intcode = Intcode(file) { outputs.add(it) }
        assertEquals(BigInteger("4"), intcode[0])
        assertEquals(BigInteger("5"), intcode[1])
        assertEquals(BigInteger("4"), intcode[2])
        assertEquals(BigInteger("6"), intcode[3])
        assertEquals(BigInteger("99"), intcode[4])
        assertEquals(BigInteger("3"), intcode[5])
        assertEquals(BigInteger("13"), intcode[6])

        intcode.run()
        assertEquals(listOf(BigInteger("3"), BigInteger("13")), outputs.toList())
    }

    @Test
    fun testWithNoOpProgram() {
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

        intcode.resetState()
        assertFalse(intcode.isDone)
        assertFalse(intcode.isPaused)
        assertEquals(BigInteger("99"), intcode[0])
        assertEquals(BigInteger.ZERO, intcode[1])
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun testGetWithInvalidPosition() {
        val intcode = Intcode("99")
        intcode[-1]
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun testSetWithInvalidPosition() {
        val intcode = Intcode("99")
        intcode[-1] = BigInteger.ONE
    }

    @Test
    fun testSetAndResetExtendedValue() {
        val intcode = Intcode("99")
        intcode[1] = BigInteger.ONE
        assertEquals(BigInteger.ONE, intcode[1])
        intcode[1] = BigInteger.ZERO
        assertEquals(BigInteger.ZERO, intcode[1])
    }

    @Test
    fun testHandlesParameterMode() {
        val intcode = Intcode("1001,5,-9,6,99,17,-12")
        intcode.run()
        assertEquals(BigInteger("1001"), intcode[0])
        assertEquals(BigInteger("5"), intcode[1])
        assertEquals(BigInteger("-9"), intcode[2])
        assertEquals(BigInteger("6"), intcode[3])
        assertEquals(BigInteger("99"), intcode[4])
        assertEquals(BigInteger("17"), intcode[5])
        assertEquals(BigInteger("8"), intcode[6])
    }

    @Test
    fun testHandlesInput() {
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

    @Test
    fun testHandlesOutput() {
        val intcode = Intcode("4,5,4,6,99,18,-19")
        assertTrue(printedChars.isEmpty())

        intcode.run()
        assertEquals("18%n-19%n".format(), printedChars.joinToString(separator = ""))

        intcode.resetState()
        assertEquals("18%n-19%n".format(), printedChars.joinToString(separator = ""))

        intcode.run()
        assertEquals("18%n-19%n18%n-19%n".format(), printedChars.joinToString(separator = ""))
    }

    @Test
    fun testHandlesOutputWithCustomFunction() {
        val outputs = mutableListOf<BigInteger>()
        val intcode = Intcode("4,5,4,6,99,-74,16") { outputs.add(it) }
        assertTrue(outputs.isEmpty())
        assertTrue(printedChars.isEmpty())

        intcode.run()
        assertEquals(listOf(BigInteger("-74"), BigInteger("16")), outputs.toList())
        assertTrue(printedChars.isEmpty())

        intcode.resetState()
        assertEquals(listOf(BigInteger("-74"), BigInteger("16")), outputs.toList())
        assertTrue(printedChars.isEmpty())

        intcode.run()
        assertEquals(listOf(BigInteger("-74"), BigInteger("16"), BigInteger("-74"), BigInteger("16")), outputs.toList())
        assertTrue(printedChars.isEmpty())

        intcode.resetOutput()
        assertEquals(listOf(BigInteger("-74"), BigInteger("16"), BigInteger("-74"), BigInteger("16")), outputs.toList())
        assertTrue(printedChars.isEmpty())

        intcode.resetState()
        intcode.run()
        assertEquals(listOf(BigInteger("-74"), BigInteger("16"), BigInteger("-74"), BigInteger("16")), outputs.toList())
        assertEquals("-74%n16%n".format(), printedChars.joinToString(separator = ""))
    }
}
