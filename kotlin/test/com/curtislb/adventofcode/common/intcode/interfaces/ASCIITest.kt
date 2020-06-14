package com.curtislb.adventofcode.common.intcode.interfaces

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.OutputStream
import java.io.PrintStream
import java.math.BigInteger
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Tests [ASCII].
 */
class ASCIITest {
    private lateinit var asciiOutputs: MutableList<BigInteger>
    private lateinit var nonAsciiOutputs: MutableList<BigInteger>
    private lateinit var standardOutChars: MutableList<Int>

    @Before fun setUp() {
        asciiOutputs = mutableListOf()
        nonAsciiOutputs = mutableListOf()
        standardOutChars = mutableListOf()
        System.setOut(PrintStream(object : OutputStream() {
            override fun write(char: Int) {
                standardOutChars.add(char)
            }
        }))
    }

    @After fun tearDown() = System.setOut(System.out)

    @Test fun testRunWhileShowingAsciiOutput() {
        val ascii = ASCII("4,9,4,10,4,11,4,12,99,-19,72,128,105", showAsciiOutput = true) { output, isAscii ->
            if (isAscii) {
                asciiOutputs.add(output)
            } else {
                nonAsciiOutputs.add(output)
            }
        }
        assertTrue(asciiOutputs.isEmpty())
        assertTrue(nonAsciiOutputs.isEmpty())
        assertTrue(standardOutChars.isEmpty())
        assertFalse(ascii.isPaused)
        assertFalse(ascii.isDone)

        ascii.run()
        assertEquals(listOf(BigInteger("72"), BigInteger("105")), asciiOutputs.toList())
        assertEquals(listOf(BigInteger("-19"), BigInteger("128")), nonAsciiOutputs.toList())
        assertEquals(listOf(72, 105), standardOutChars)
        assertFalse(ascii.isPaused)
        assertTrue(ascii.isDone)
    }

    @Test fun testRunWhileNotShowingAsciiOutput() {
        val ascii = ASCII("4,9,4,10,4,11,4,12,99,-19,72,128,105", showAsciiOutput = false) { output, isAscii ->
            if (isAscii) {
                asciiOutputs.add(output)
            } else {
                nonAsciiOutputs.add(output)
            }
        }
        assertTrue(asciiOutputs.isEmpty())
        assertTrue(nonAsciiOutputs.isEmpty())
        assertTrue(standardOutChars.isEmpty())
        assertFalse(ascii.isPaused)
        assertFalse(ascii.isDone)

        ascii.run()
        assertEquals(listOf(BigInteger("72"), BigInteger("105")), asciiOutputs.toList())
        assertEquals(listOf(BigInteger("-19"), BigInteger("128")), nonAsciiOutputs.toList())
        assertTrue(standardOutChars.isEmpty())
        assertFalse(ascii.isPaused)
        assertTrue(ascii.isDone)
    }

    @Test fun testSendInput() {
        val ascii = ASCII("3,13,3,14,3,15,4,13,4,14,4,15,99") { output, isAscii ->
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
}
