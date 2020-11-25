package com.curtislb.adventofcode.common.intcode.mode

import com.curtislb.adventofcode.common.intcode.Intcode
import org.junit.Before
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests [ImmediateMode].
 */
class ImmediateModeTest {
    private lateinit var intcode: Intcode

    @Before
    fun setUp() {
        intcode = Intcode("99")
    }

    @Test
    fun testGetValue() {
        assertEquals(BigInteger.ZERO, ImmediateMode.getValue(intcode, BigInteger.ZERO))
        assertEquals(BigInteger.ONE, ImmediateMode.getValue(intcode, BigInteger.ONE))
        assertEquals(BigInteger("37"), ImmediateMode.getValue(intcode, BigInteger("37")))
        assertEquals(BigInteger("-60"), ImmediateMode.getValue(intcode, BigInteger("-60")))
    }

    @Test(expected = UnsupportedOperationException::class)
    fun testSetValue() {
        ImmediateMode.setValue(intcode, BigInteger.ZERO, BigInteger.ONE)
    }
}
