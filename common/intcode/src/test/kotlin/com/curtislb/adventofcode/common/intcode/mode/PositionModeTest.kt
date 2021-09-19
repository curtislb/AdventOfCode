package com.curtislb.adventofcode.common.intcode.mode

import com.curtislb.adventofcode.common.intcode.Intcode
import java.math.BigInteger
import kotlin.test.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Tests [PositionMode].
 */
class PositionModeTest {
    private lateinit var intcode: Intcode

    @BeforeEach
    fun setUp() {
        intcode = Intcode("1,36,-42,3,99")
    }

    @Test
    fun testGetValue() {
        assertEquals(BigInteger("-42"), PositionMode.getValue(intcode, BigInteger.TWO))
        assertEquals(BigInteger("36"), PositionMode.getValue(intcode, BigInteger.ONE))
        assertEquals(BigInteger.ONE, PositionMode.getValue(intcode, BigInteger.ZERO))
        assertEquals(BigInteger.ZERO, PositionMode.getValue(intcode, BigInteger("5")))
        assertEquals(BigInteger("3"), PositionMode.getValue(intcode, BigInteger("3")))
        assertEquals(BigInteger("99"), PositionMode.getValue(intcode, BigInteger("4")))
    }

    @Test
    fun testSetValue() {
        PositionMode.setValue(intcode, BigInteger.ONE, BigInteger("32"))
        PositionMode.setValue(intcode, BigInteger.ZERO, BigInteger("2"))
        PositionMode.setValue(intcode, BigInteger("3"), BigInteger("6"))
        PositionMode.setValue(intcode, BigInteger("6"), BigInteger("-77"))
        assertEquals(BigInteger("2"), intcode[0])
        assertEquals(BigInteger("32"), intcode[1])
        assertEquals(BigInteger("-42"), intcode[2])
        assertEquals(BigInteger("6"), intcode[3])
        assertEquals(BigInteger("99"), intcode[4])
        assertEquals(BigInteger.ZERO, intcode[5])
        assertEquals(BigInteger("-77"), intcode[6])
    }
}
