package com.curtislb.adventofcode.common.intcode.interfaces

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
 * Tests [Ascii].
 */
class AsciiTest {
    @get:Rule val temporaryFolder = TemporaryFolder()

    private lateinit var asciiOutputs: MutableList<BigInteger>
    private lateinit var nonAsciiOutputs: MutableList<BigInteger>
    private lateinit var printedCodes: MutableList<Int>
    private lateinit var standardOutput: PrintStream

    @Before
    fun setUp() {
        asciiOutputs = mutableListOf()
        nonAsciiOutputs = mutableListOf()
        printedCodes = mutableListOf()
        standardOutput = System.out
        System.setOut(PrintStream(object : OutputStream() {
            override fun write(code: Int) {
                printedCodes.add(code)
            }
        }))
    }

    @After
    fun tearDown() = System.setOut(standardOutput)

    @Test
    fun testConstructFromFile() {
        val file = temporaryFolder.newFile().apply { writeText("4,3,99,92") }
        val ascii = Ascii(file, showAsciiOutput = true)
        ascii.run()
        assertEquals(listOf(92), printedCodes.toList())
    }

    @Test
    fun testConstructFromFileWithoutShowingAsciiOutput() {
        val file = temporaryFolder.newFile().apply { writeText("4,3,99,92") }
        val ascii = Ascii(file)
        ascii.run()
        assertTrue(printedCodes.isEmpty())
    }

    @Test
    fun testSetValue() {
        val ascii = Ascii("4,5,4,63,99,21", showAsciiOutput = true)
        ascii[5] = BigInteger("19")
        ascii[63] = BigInteger("112")
        ascii.run()
        assertEquals(listOf(19, 112), printedCodes.toList())
    }

    @Test
    fun testSendInput() {
        val ascii = Ascii("3,13,3,14,3,15,4,13,4,14,4,15,99") { output, isAscii ->
            if (isAscii) {
                asciiOutputs.add(output)
            } else {
                nonAsciiOutputs.add(output)
            }
        }
        assertTrue(asciiOutputs.isEmpty())
        assertTrue(nonAsciiOutputs.isEmpty())

        ascii.sendInput("Hi")
        ascii.run()
        assertEquals(listOf(BigInteger("72"), BigInteger("105"), BigInteger.TEN), asciiOutputs.toList())
        assertTrue(nonAsciiOutputs.isEmpty())
    }

    @Test
    fun testRunWithoutShowingAsciiOutput() {
        val ascii = Ascii("4,9,4,10,4,11,4,12,99,-19,72,128,105") { output, isAscii ->
            if (isAscii) {
                asciiOutputs.add(output)
            } else {
                nonAsciiOutputs.add(output)
            }
        }
        assertTrue(asciiOutputs.isEmpty())
        assertTrue(nonAsciiOutputs.isEmpty())
        assertTrue(printedCodes.isEmpty())
        assertFalse(ascii.isPaused)
        assertFalse(ascii.isDone)

        ascii.run()
        assertEquals(listOf(BigInteger("72"), BigInteger("105")), asciiOutputs.toList())
        assertEquals(listOf(BigInteger("-19"), BigInteger("128")), nonAsciiOutputs.toList())
        assertTrue(printedCodes.isEmpty())
        assertFalse(ascii.isPaused)
        assertTrue(ascii.isDone)

        ascii.resetState()
        assertFalse(ascii.isPaused)
        assertFalse(ascii.isDone)

        ascii.run()
        assertEquals(
            listOf(BigInteger("72"), BigInteger("105"), BigInteger("72"), BigInteger("105")),
            asciiOutputs.toList()
        )
        assertEquals(
            listOf(BigInteger("-19"), BigInteger("128"), BigInteger("-19"), BigInteger("128")),
            nonAsciiOutputs.toList()
        )
        assertTrue(printedCodes.isEmpty())
        assertFalse(ascii.isPaused)
        assertTrue(ascii.isDone)
    }

    @Test
    fun testRunWhileShowingAsciiOutput() {
        val ascii = Ascii("4,9,4,10,4,11,4,12,99,-19,72,128,105", showAsciiOutput = true) { output, isAscii ->
            if (isAscii) {
                asciiOutputs.add(output)
            } else {
                nonAsciiOutputs.add(output)
            }
        }
        assertTrue(asciiOutputs.isEmpty())
        assertTrue(nonAsciiOutputs.isEmpty())
        assertTrue(printedCodes.isEmpty())
        assertFalse(ascii.isPaused)
        assertFalse(ascii.isDone)

        ascii.run()
        assertEquals(listOf(BigInteger("72"), BigInteger("105")), asciiOutputs.toList())
        assertEquals(listOf(BigInteger("-19"), BigInteger("128")), nonAsciiOutputs.toList())
        assertEquals(listOf(72, 105), printedCodes)
        assertFalse(ascii.isPaused)
        assertTrue(ascii.isDone)

        ascii.resetState()
        assertFalse(ascii.isPaused)
        assertFalse(ascii.isDone)

        ascii.run()
        assertEquals(
            listOf(BigInteger("72"), BigInteger("105"), BigInteger("72"), BigInteger("105")),
            asciiOutputs.toList()
        )
        assertEquals(
            listOf(BigInteger("-19"), BigInteger("128"), BigInteger("-19"), BigInteger("128")),
            nonAsciiOutputs.toList()
        )
        assertEquals(listOf(72, 105, 72, 105), printedCodes)
        assertFalse(ascii.isPaused)
        assertTrue(ascii.isDone)
    }

    @Test
    fun testRunWithoutCustomOutputFunction() {
        val ascii = Ascii("4,9,4,10,4,11,4,12,99,-19,72,128,105", showAsciiOutput = true)
        assertTrue(asciiOutputs.isEmpty())
        assertTrue(nonAsciiOutputs.isEmpty())
        assertTrue(printedCodes.isEmpty())
        assertFalse(ascii.isPaused)
        assertFalse(ascii.isDone)

        ascii.run()
        assertTrue(asciiOutputs.isEmpty())
        assertTrue(nonAsciiOutputs.isEmpty())
        assertEquals(listOf(72, 105), printedCodes)
        assertFalse(ascii.isPaused)
        assertTrue(ascii.isDone)

        ascii.resetState()
        assertFalse(ascii.isPaused)
        assertFalse(ascii.isDone)

        ascii.run()
        assertTrue(asciiOutputs.isEmpty())
        assertTrue(nonAsciiOutputs.isEmpty())
        assertEquals(listOf(72, 105, 72, 105), printedCodes)
        assertFalse(ascii.isPaused)
        assertTrue(ascii.isDone)
    }

    @Test
    fun testRunWithoutAnyOutput() {
        val ascii = Ascii("4,9,4,10,4,11,4,12,99,-19,72,128,105")
        assertTrue(asciiOutputs.isEmpty())
        assertTrue(nonAsciiOutputs.isEmpty())
        assertTrue(printedCodes.isEmpty())
        assertFalse(ascii.isPaused)
        assertFalse(ascii.isDone)

        ascii.run()
        assertTrue(asciiOutputs.isEmpty())
        assertTrue(nonAsciiOutputs.isEmpty())
        assertTrue(printedCodes.isEmpty())
        assertFalse(ascii.isPaused)
        assertTrue(ascii.isDone)
    }

    @Test
    fun testNewlineCode() {
        assertEquals('\n'.toInt().toBigInteger(), Ascii.NEWLINE_CODE)
    }
}
