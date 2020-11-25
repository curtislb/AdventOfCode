package com.curtislb.adventofcode.common.intcode.mode

import com.curtislb.adventofcode.common.intcode.Intcode
import org.junit.Before
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests [RelativeMode].
 */
class RelativeModeTest {
    private lateinit var intcode: Intcode

    @Before
    fun setUp() {
        intcode = Intcode("2,-59,98,29,99")
    }

    @Test
    fun testGetValue() {
        assertEquals(BigInteger("98"), RelativeMode.getValue(intcode, BigInteger.TWO))
        assertEquals(BigInteger.ZERO, RelativeMode.getValue(intcode, BigInteger("5")))
        assertEquals(BigInteger("-59"), RelativeMode.getValue(intcode, BigInteger.ONE))
        assertEquals(BigInteger.TWO, RelativeMode.getValue(intcode, BigInteger.ZERO))
        assertEquals(BigInteger("29"), RelativeMode.getValue(intcode, BigInteger("3")))
        assertEquals(BigInteger("99"), RelativeMode.getValue(intcode, BigInteger("4")))

        intcode.relativeBase = 3
        assertEquals(BigInteger.ZERO, RelativeMode.getValue(intcode, BigInteger("3")))
        assertEquals(BigInteger("-59"), RelativeMode.getValue(intcode, BigInteger("-2")))
        assertEquals(BigInteger("29"), RelativeMode.getValue(intcode, BigInteger.ZERO))
        assertEquals(BigInteger.TWO, RelativeMode.getValue(intcode, BigInteger("-3")))
        assertEquals(BigInteger("99"), RelativeMode.getValue(intcode, BigInteger.ONE))
        assertEquals(BigInteger.ZERO, RelativeMode.getValue(intcode, BigInteger.TWO))
        assertEquals(BigInteger("98"), RelativeMode.getValue(intcode, BigInteger("-1")))
    }

    @Test
    fun testSetValue() {
        RelativeMode.setValue(intcode, BigInteger.ZERO, BigInteger("6"))
        RelativeMode.setValue(intcode, BigInteger("3"), BigInteger.TEN)

        intcode.relativeBase = 2
        RelativeMode.setValue(intcode, BigInteger("-1"), BigInteger("-11"))
        RelativeMode.setValue(intcode, BigInteger.ZERO, BigInteger("-95"))
        RelativeMode.setValue(intcode, BigInteger("3"), BigInteger("29"))

        assertEquals(BigInteger("6"), intcode[0])
        assertEquals(BigInteger("-11"), intcode[1])
        assertEquals(BigInteger("-95"), intcode[2])
        assertEquals(BigInteger.TEN, intcode[3])
        assertEquals(BigInteger("99"), intcode[4])
        assertEquals(BigInteger("29"), intcode[5])
        assertEquals(BigInteger.ZERO, intcode[6])
    }
}
