package com.curtislb.adventofcode.common.intcode.mode

import com.curtislb.adventofcode.common.intcode.Intcode
import java.math.BigInteger
import kotlin.test.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Tests [ImmediateMode].
 */
class ImmediateModeTest {
    private lateinit var intcode: Intcode

    @BeforeEach
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

    @Test
    fun testSetValue() {
        assertThrows(UnsupportedOperationException::class.java) {
            ImmediateMode.setValue(intcode, BigInteger.ZERO, BigInteger.ONE)
        }
    }
}
