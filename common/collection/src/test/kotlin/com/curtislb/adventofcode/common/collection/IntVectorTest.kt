package com.curtislb.adventofcode.common.collection

import com.curtislb.adventofcode.common.testing.assertContainsExactly
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNotEquals
import kotlin.test.assertNotSame
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [IntVector].
 */
class IntVectorTest {
    @Test
    fun testGetAndSize() {
        var vector = IntVector()
        assertEquals(0, vector.size)

        vector = IntVector(0)
        assertEquals(1, vector.size)
        assertEquals(0, vector[0])

        vector = IntVector(1, 2)
        assertEquals(2, vector.size)
        assertEquals(1, vector[0])
        assertEquals(2, vector[1])

        vector = IntVector(66, -57, 55)
        assertEquals(3, vector.size)
        assertEquals(66, vector[0])
        assertEquals(-57, vector[1])
        assertEquals(55, vector[2])
    }

    @Test
    fun testPlus() {
        var vectorA = IntVector()
        var vectorB = IntVector()
        assertEquals(IntVector(), vectorA + vectorB)

        vectorA = IntVector(1)
        vectorB = IntVector(-3)
        assertEquals(IntVector(-2), vectorA + vectorB)

        vectorA = IntVector(-39, -4, 19)
        vectorB = IntVector(91, -67, -66)
        assertEquals(IntVector(52, -71, -47), vectorA + vectorB)
    }

    @Test
    fun testPlusWithWrongSizeVector() {
        assertThrows<IllegalArgumentException> { IntVector(0) + IntVector() }
        assertThrows<IllegalArgumentException> { IntVector(1) + IntVector(2, 3) }
    }

    @Test
    fun testSumOf() {
        var vector = IntVector()
        assertEquals(0, vector.sumOf { it })
        assertEquals(0, vector.sumOf { -it })
        assertEquals(0, vector.sumOf { (it - 1) * 2 })
        assertEquals(0, vector.sumOf { it * it })

        vector = IntVector(-23)
        assertEquals(-23, vector.sumOf { it })
        assertEquals(23, vector.sumOf { -it })
        assertEquals(-48, vector.sumOf { (it - 1) * 2 })
        assertEquals(529, vector.sumOf { it * it })

        vector = IntVector(10, 1, -4)
        assertEquals(7, vector.sumOf { it })
        assertEquals(-7, vector.sumOf { -it })
        assertEquals(8, vector.sumOf { (it - 1) * 2 })
        assertEquals(117, vector.sumOf { it * it })
    }

    @Test
    fun testNeighbors() {
        assertEquals(emptyList(), IntVector().neighbors())
        assertContainsExactly(listOf(IntVector(-1), IntVector(1)), IntVector(0).neighbors())
        assertContainsExactly(listOf(IntVector(69), IntVector(71)), IntVector(70).neighbors())
        assertContainsExactly(listOf(IntVector(-7), IntVector(-5)), IntVector(-6).neighbors())
        assertContainsExactly(
            listOf(
                IntVector(-33, 59),
                IntVector(-33, 60),
                IntVector(-33, 61),
                IntVector(-32, 59),
                IntVector(-32, 61),
                IntVector(-31, 59),
                IntVector(-31, 60),
                IntVector(-31, 61),
            ),
            IntVector(-32, 60).neighbors()
        )
        assertContainsExactly(
            listOf(
                IntVector(41, -88, -27),
                IntVector(41, -88, -26),
                IntVector(41, -88, -25),
                IntVector(41, -87, -27),
                IntVector(41, -87, -26),
                IntVector(41, -87, -25),
                IntVector(41, -86, -27),
                IntVector(41, -86, -26),
                IntVector(41, -86, -25),
                IntVector(42, -88, -27),
                IntVector(42, -88, -26),
                IntVector(42, -88, -25),
                IntVector(42, -87, -27),
                IntVector(42, -87, -25),
                IntVector(42, -86, -27),
                IntVector(42, -86, -26),
                IntVector(42, -86, -25),
                IntVector(43, -88, -27),
                IntVector(43, -88, -26),
                IntVector(43, -88, -25),
                IntVector(43, -87, -27),
                IntVector(43, -87, -26),
                IntVector(43, -87, -25),
                IntVector(43, -86, -27),
                IntVector(43, -86, -26),
                IntVector(43, -86, -25),
            ),
            IntVector(42, -87, -26).neighbors(),
        )
    }

    @Test
    fun testCopy() {
        val vector = IntVector(82, -30, -27)
        val vectorCopy = vector.copy()
        assertEquals(vector, vectorCopy)
        assertNotSame(vector, vectorCopy)
    }

    @Test
    fun testEquals() {
        assertEquals(IntVector(), IntVector())
        assertEquals(IntVector(28), IntVector(28))
        assertNotEquals(IntVector(84), IntVector(-84))
        assertEquals(IntVector(63, 34), IntVector(63, 34))
        assertNotEquals(IntVector(63, 34), IntVector(34, 63))
        assertNotEquals(IntVector(63, 34), IntVector(63, -34))
        assertNotEquals(IntVector(63, 34), IntVector(-63, 34))
        assertNotEquals(IntVector(63, 34), IntVector(-63, -34))
        assertEquals(IntVector(52, -58, 76), IntVector(52, -58, 76))
        assertNotEquals(IntVector(52, -58, 76), IntVector(-58, 76, 52))
        assertEquals(IntVector(-17, -30, 11, 99, -27), IntVector(-17, -30, 11, 99, -27))
        assertNotEquals(IntVector(-17, -30, 11, 99, -27), IntVector(-27, 99, 11, -30, -17))
    }

    @Test
    fun testHashCode() {
        val vectors = listOf(
            IntVector(),
            IntVector(-4),
            IntVector(12),
            IntVector(48),
            IntVector(71, 77),
            IntVector(-12, -84),
            IntVector(43, -29),
            IntVector(-29, 43),
            IntVector(89, -1, -96),
            IntVector(-1, -96, 89),
            IntVector(12, -40, 31, -84),
            IntVector(12, 31, -40, -84),
            IntVector(-23, 71, -76, -56, 10),
            IntVector(-76, 71, -56, -23, 10)
        )
        val hashMap = HashMap<IntVector, Int>()
        vectors.forEachIndexed { index, range -> hashMap[range] = index }
        vectors.forEachIndexed { index, range -> assertEquals(index, hashMap[range]) }
    }

    @Test
    fun testToString() {
        assertEquals("<>", IntVector().toString())
        assertEquals("<0>", IntVector(0).toString())
        assertEquals("<-1>", IntVector(-1).toString())
        assertEquals("<1, 2, 3>", IntVector(1, 2, 3).toString())
        assertEquals("<-3, 5, -2>", IntVector(-3, 5, -2).toString())
    }

    @Test
    fun testToMutableIntVector() {
        val vector = IntVector(-14, -21, 8, -78, 6)
        val mutableVector = vector.toMutableIntVector()
        assertIs<MutableIntVector>(mutableVector)
        assertEquals(vector, mutableVector)
        assertNotSame(vector, mutableVector)
    }

    @Test
    fun testConstructVectorOfZeros() {
        assertEquals(IntVector(), IntVector.ofZeros(0))
        assertEquals(IntVector(0), IntVector.ofZeros(1))
        assertEquals(IntVector(0, 0), IntVector.ofZeros(2))
        assertEquals(IntVector(0, 0, 0), IntVector.ofZeros(3))
        assertEquals(IntVector(0, 0, 0, 0, 0), IntVector.ofZeros(5))
        assertEquals(IntVector(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), IntVector.ofZeros(10))
    }
}