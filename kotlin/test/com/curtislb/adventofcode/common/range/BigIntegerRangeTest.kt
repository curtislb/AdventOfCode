package com.curtislb.adventofcode.common.range

import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

/**
 * Tests [BigIntegerRange].
 */
class BigIntegerRangeTest {
    @Test
    fun testSize() {
        assertEquals(BigInteger.ZERO, BigIntegerRange.EMPTY.size)
        assertEquals(BigInteger.ZERO, BigIntegerRange(BigInteger.ONE, BigInteger.ZERO).size)
        assertEquals(BigInteger.ZERO, BigIntegerRange(BigInteger.TWO, BigInteger.ZERO).size)
        assertEquals(BigInteger.ONE, BigIntegerRange(BigInteger.ZERO, BigInteger.ZERO).size)
        assertEquals(BigInteger.ONE, BigIntegerRange(BigInteger.ONE, BigInteger.ONE).size)
        assertEquals(BigInteger.TWO, BigIntegerRange(BigInteger.ZERO, BigInteger.ONE).size)
        assertEquals(BigInteger.TWO, BigIntegerRange(BigInteger.ONE, BigInteger.TWO).size)
        assertEquals(BigInteger("9"), BigIntegerRange(BigInteger.TWO, BigInteger.TEN).size)
        assertEquals(BigInteger("18"), BigIntegerRange(BigInteger("-5"), BigInteger("12")).size)
        assertEquals(BigInteger("1420198"), BigIntegerRange(BigInteger("-475962"), BigInteger("944235")).size)
    }

    @Test
    fun testConstructFromIntRange() {
        var range = BigIntegerRange(0..0)
        assertEquals(BigInteger.ZERO, range.start)
        assertEquals(BigInteger.ZERO, range.endInclusive)

        range = BigIntegerRange(0..1)
        assertEquals(BigInteger.ZERO, range.start)
        assertEquals(BigInteger.ONE, range.endInclusive)

        range = BigIntegerRange(-1..1)
        assertEquals(BigInteger("-1"), range.start)
        assertEquals(BigInteger.ONE, range.endInclusive)

        range = BigIntegerRange(1..100)
        assertEquals(BigInteger.ONE, range.start)
        assertEquals(BigInteger("100"), range.endInclusive)
    }

    @Test
    fun testConstructFromLongRange() {
        var range = BigIntegerRange(0L..0L)
        assertEquals(BigInteger.ZERO, range.start)
        assertEquals(BigInteger.ZERO, range.endInclusive)

        range = BigIntegerRange(0L..1L)
        assertEquals(BigInteger.ZERO, range.start)
        assertEquals(BigInteger.ONE, range.endInclusive)

        range = BigIntegerRange(-1L..1L)
        assertEquals(BigInteger("-1"), range.start)
        assertEquals(BigInteger.ONE, range.endInclusive)

        range = BigIntegerRange(1L..100L)
        assertEquals(BigInteger.ONE, range.start)
        assertEquals(BigInteger("100"), range.endInclusive)
    }

    @Test
    fun testIterator() {
        val items = mutableListOf<BigInteger>()
        for (item in BigIntegerRange(BigInteger("-4"), BigInteger("3"))) {
            items.add(item)
        }
        assertEquals(
            listOf(
                BigInteger("-4"),
                BigInteger("-3"),
                BigInteger("-2"),
                BigInteger("-1"),
                BigInteger.ZERO,
                BigInteger.ONE,
                BigInteger.TWO,
                BigInteger("3")
            ),
            items.toList()
        )
    }

    @Test
    fun testEquals() {
        assertEquals(BigIntegerRange.EMPTY, BigIntegerRange.EMPTY)
        assertEquals(BigIntegerRange(BigInteger.ONE, BigInteger.ZERO), BigIntegerRange.EMPTY)
        assertEquals(BigIntegerRange(BigInteger.ONE, BigInteger.ZERO), BigIntegerRange(BigInteger.TWO, BigInteger.ZERO))
        assertEquals(BigIntegerRange(BigInteger.TEN, BigInteger.TWO), BigIntegerRange(BigInteger.TWO, BigInteger.ZERO))
        assertEquals(BigIntegerRange(BigInteger.ONE, BigInteger.ONE), BigIntegerRange(BigInteger.ONE, BigInteger.ONE))
        assertEquals(BigIntegerRange(BigInteger.ONE, BigInteger.TWO), BigIntegerRange(BigInteger.ONE, BigInteger.TWO))
        assertEquals(BigIntegerRange(BigInteger.TWO, BigInteger.TEN), BigIntegerRange(BigInteger.TWO, BigInteger.TEN))
        assertEquals(
            BigIntegerRange(BigInteger("-3"), BigInteger("4")),
            BigIntegerRange(BigInteger("-3"), BigInteger("4"))
        )

        assertNotEquals(BigIntegerRange(BigInteger.ZERO, BigInteger.ZERO), BigIntegerRange.EMPTY)
        assertNotEquals(
            BigIntegerRange(BigInteger.ZERO, BigInteger.ZERO),
            BigIntegerRange(BigInteger.ONE, BigInteger.ONE)
        )
        assertNotEquals(
            BigIntegerRange(BigInteger.ZERO, BigInteger.ZERO),
            BigIntegerRange(BigInteger.ZERO, BigInteger.ONE)
        )
        assertNotEquals(
            BigIntegerRange(BigInteger.ZERO, BigInteger.ONE),
            BigIntegerRange(BigInteger.ONE, BigInteger.ONE)
        )
        assertNotEquals(
            BigIntegerRange(BigInteger.ZERO, BigInteger.TWO),
            BigIntegerRange(BigInteger.ONE, BigInteger.TEN)
        )
        assertNotEquals(
            BigIntegerRange(BigInteger("-3"), BigInteger("4")),
            BigIntegerRange(BigInteger("-4"), BigInteger("3"))
        )
    }

    @Test
    fun testHashCode() {
        val ranges = listOf(
            BigIntegerRange(BigInteger("90"), BigInteger("-4")),
            BigIntegerRange(BigInteger("-100"),  BigInteger("-27")),
            BigIntegerRange(BigInteger("8"), BigInteger("58")),
            BigIntegerRange(BigInteger("72"), BigInteger("94")),
            BigIntegerRange(BigInteger("-89"),  BigInteger("-53")),
            BigIntegerRange(BigInteger("-16"), BigInteger("83")),
            BigIntegerRange(BigInteger("-43"), BigInteger("-5")),
            BigIntegerRange(BigInteger("-85"),  BigInteger("-51")),
            BigIntegerRange(BigInteger("-29"), BigInteger("90")),
            BigIntegerRange(BigInteger("-63"), BigInteger("10"))
        )
        val hashMap = HashMap<BigIntegerRange, Int>()
        ranges.forEachIndexed { index, range -> hashMap[range] = index }
        ranges.forEachIndexed { index, range -> assertEquals(index, hashMap[range]) }
    }

    @Test
    fun testToString() {
        assertEquals("1..0", BigIntegerRange(BigInteger.ONE, BigInteger.ZERO).toString())
        assertEquals("2..0", BigIntegerRange(BigInteger.TWO, BigInteger.ZERO).toString())
        assertEquals("0..0", BigIntegerRange(BigInteger.ZERO, BigInteger.ZERO).toString())
        assertEquals("0..-1", BigIntegerRange(BigInteger.ZERO, BigInteger("-1")).toString())
        assertEquals("1..1", BigIntegerRange(BigInteger.ONE, BigInteger.ONE).toString())
        assertEquals("0..1", BigIntegerRange(BigInteger.ZERO, BigInteger.ONE).toString())
        assertEquals("1..2", BigIntegerRange(BigInteger.ONE, BigInteger.TWO).toString())
        assertEquals("2..10", BigIntegerRange(BigInteger.TWO, BigInteger.TEN).toString())
        assertEquals("-5..-2", BigIntegerRange(BigInteger("-5"), BigInteger("-2")).toString())
        assertEquals("-861..987", BigIntegerRange(BigInteger("-861"), BigInteger("987")).toString())
    }
}
