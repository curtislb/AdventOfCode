package com.curtislb.adventofcode.common.range

import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests [toIterableRange].
 */
class ToIterableRangeTest {
    @Test fun testToIterableRange() {
        assertEquals(
            38..62,
            object : ClosedRange<Int> {
                override val start: Int = 38
                override val endInclusive: Int = 62
            }.toIterableRange()
        )
        assertEquals(
            -98L..3L,
            object : ClosedRange<Long> {
                override val start: Long = -98L
                override val endInclusive: Long = 3L
            }.toIterableRange()
        )
        assertEquals(
            BigIntegerRange(BigInteger("8"), BigInteger("91")),
            object : ClosedRange<BigInteger> {
                override val start: BigInteger = BigInteger("8")
                override val endInclusive: BigInteger = BigInteger("91")
            }.toIterableRange()
        )
    }
}
