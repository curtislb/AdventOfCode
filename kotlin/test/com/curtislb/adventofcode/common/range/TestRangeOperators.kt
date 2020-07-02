package com.curtislb.adventofcode.common.range

import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests range operator extensions in  [com.curtislb.adventofcode.common.range].
 */
class TestRangeOperators {
    @Test fun testRangeTo() {
        assertEquals(BigIntegerRange.EMPTY, BigInteger.ONE..BigInteger.ZERO)
        assertEquals(BigIntegerRange.EMPTY, BigInteger.TWO..BigInteger.ZERO)
        assertEquals(BigIntegerRange(BigInteger.ZERO, BigInteger.ZERO), BigInteger.ZERO..BigInteger.ZERO)
        assertEquals(BigIntegerRange(BigInteger.ONE, BigInteger.ONE), BigInteger.ONE..BigInteger.ONE)
        assertEquals(BigIntegerRange(BigInteger.ZERO, BigInteger.ONE), BigInteger.ZERO..BigInteger.ONE)
        assertEquals(BigIntegerRange(BigInteger.ONE, BigInteger.TWO), BigInteger.ONE..BigInteger.TWO)
        assertEquals(BigIntegerRange(BigInteger.TWO, BigInteger.TEN), BigInteger.TWO..BigInteger.TEN)
        assertEquals(BigIntegerRange(BigInteger("-5"), BigInteger("-2")), BigInteger("-5")..BigInteger("-2"))
        assertEquals(BigIntegerRange(BigInteger("-861"), BigInteger("987")), BigInteger("-861")..BigInteger("987"))
    }

    @Test fun testUntil() {
        assertEquals(BigIntegerRange.EMPTY, BigInteger.ONE until BigInteger.ZERO)
        assertEquals(BigIntegerRange.EMPTY, BigInteger.TWO until BigInteger.ZERO)
        assertEquals(BigIntegerRange.EMPTY, BigInteger.ONE until BigInteger.ONE)
        assertEquals(BigIntegerRange(BigInteger.ZERO, BigInteger.ZERO), BigInteger.ZERO until BigInteger.ONE)
        assertEquals(BigIntegerRange(BigInteger.ONE, BigInteger.ONE), BigInteger.ONE until BigInteger.TWO)
        assertEquals(BigIntegerRange(BigInteger.ZERO, BigInteger.ONE), BigInteger.ZERO until BigInteger.TWO)
        assertEquals(BigIntegerRange(BigInteger.TWO, BigInteger("9")), BigInteger.TWO until BigInteger.TEN)
        assertEquals(BigIntegerRange(BigInteger("-5"), BigInteger("-3")), BigInteger("-5") until BigInteger("-2"))
        assertEquals(BigIntegerRange(BigInteger("-861"), BigInteger("986")), BigInteger("-861") until BigInteger("987"))
    }
}
