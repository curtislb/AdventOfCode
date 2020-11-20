package com.curtislb.adventofcode.common.collection

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests [MutableVector].
 */
class MutableVectorTest {
    @Test fun testGetSetAndSize() {
        var vector = MutableVector()
        assertEquals(0, vector.size)

        vector = MutableVector(0)
        assertEquals(1, vector.size)
        assertEquals(0, vector[0])

        vector[0] = 30
        assertEquals(1, vector.size)
        assertEquals(30, vector[0])

        vector = MutableVector(1, 2)
        assertEquals(2, vector.size)
        assertEquals(1, vector[0])
        assertEquals(2, vector[1])

        vector[0] = -46
        assertEquals(2, vector.size)
        assertEquals(-46, vector[0])
        assertEquals(2, vector[1])

        vector[1] = 36
        assertEquals(2, vector.size)
        assertEquals(-46, vector[0])
        assertEquals(36, vector[1])

        vector = MutableVector(66, -57, 55)
        assertEquals(3, vector.size)
        assertEquals(66, vector[0])
        assertEquals(-57, vector[1])
        assertEquals(55, vector[2])

        vector[0] = -6
        vector[1] = 89
        vector[2] = 31
        assertEquals(3, vector.size)
        assertEquals(-6, vector[0])
        assertEquals(89, vector[1])
        assertEquals(31, vector[2])
    }

    @Test fun testUpdateWithNewComponents() {
        var vector = MutableVector()
        vector.update()
        assertEquals(MutableVector(), vector)

        vector = MutableVector(44, 8, -65, -54, 33)
        vector.update(7, -55, -78, -91, 35)
        assertEquals(MutableVector(7, -55, -78, -91, 35), vector)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testUpdateWithWrongNumberOfNewComponents() {
        MutableVector(-6, 7).update(8, -9, 10)
    }

    @Test fun testUpdateWithComponentValues() {
        var vector = MutableVector()
        vector.update(mapOf())
        assertEquals(MutableVector(), vector)

        vector = MutableVector(-32, -92, -97, -68, 86)
        vector.update(mapOf(0 to 52, 3 to 4, 4 to -19))
        assertEquals(MutableVector(52, -92, -97, 4, -19), vector)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testUpdateWithInvalidComponentValues() {
        MutableVector(11, 12, -13).update(mapOf(0 to -14, 3 to 15))
    }

    @Test fun testAdd() {
        var vectorA = MutableVector()
        var vectorB = MutableVector()
        vectorA.add(vectorB)
        assertEquals(MutableVector(), vectorA)
        assertEquals(MutableVector(), vectorB)

        vectorA = MutableVector(5)
        vectorB = MutableVector(-2)
        vectorA.add(vectorB)
        assertEquals(MutableVector(3), vectorA)
        assertEquals(MutableVector(-2), vectorB)

        vectorA = MutableVector(29, -44, 91)
        vectorB = MutableVector(74, 89, -98)
        vectorA.add(vectorB)
        assertEquals(MutableVector(103, 45, -7), vectorA)
        assertEquals(MutableVector(74, 89, -98), vectorB)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testAddWithWrongSizeVector() {
        MutableVector(3, -4).add(MutableVector(5))
    }

    @Test fun testPlus() {
        var vectorA = MutableVector()
        var vectorB = MutableVector()
        assertEquals(MutableVector(), vectorA + vectorB)

        vectorA = MutableVector(1)
        vectorB = MutableVector(-3)
        var vectorSum = vectorA + vectorB
        assertEquals(MutableVector(-2), vectorSum)

        vectorA = MutableVector(-39, -4, 19)
        vectorB = MutableVector(91, -67, -66)
        vectorSum = vectorA + vectorB
        assertEquals(MutableVector(52, -71, -47), vectorSum)

        vectorA[1] = 29
        vectorB[2] = -43
        assertEquals(MutableVector(52, -71, -47), vectorSum)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testPlusWithWrongSizeVector() {
        MutableVector(1) + MutableVector(2, 3)
    }

    @Test fun testSumBy() {
        var vector = MutableVector()
        assertEquals(0, vector.sumBy { it })
        assertEquals(0, vector.sumBy { -it })
        assertEquals(0, vector.sumBy { (it - 1) * 2 })
        assertEquals(0, vector.sumBy { it * it })

        vector = MutableVector(-23)
        assertEquals(-23, vector.sumBy { it })
        assertEquals(23, vector.sumBy { -it })
        assertEquals(-48, vector.sumBy { (it - 1) * 2 })
        assertEquals(529, vector.sumBy { it * it })

        vector = MutableVector(10, 1, -4)
        assertEquals(7, vector.sumBy { it })
        assertEquals(-7, vector.sumBy { -it })
        assertEquals(8, vector.sumBy { (it - 1) * 2 })
        assertEquals(117, vector.sumBy { it * it })
    }

    @Test fun testCopy() {
        val vector = MutableVector(82, -30, -27)
        val vectorCopy = vector.copy()
        assertEquals(MutableVector(82, -30, -27), vectorCopy)

        vector[0] = -61
        vector[1] = 77
        vector[2] = 8
        assertEquals(MutableVector(82, -30, -27), vectorCopy)
    }

    @Test fun testToString() {
        assertEquals("<>", MutableVector().toString())
        assertEquals("<0>", MutableVector(0).toString())
        assertEquals("<-1>", MutableVector(-1).toString())
        assertEquals("<1, 2, 3>", MutableVector(1, 2, 3).toString())
        assertEquals("<-3, 5, -2>", MutableVector(-3, 5, -2).toString())
    }

    @Test fun testConstructMutableVectorOfZeros() {
        assertEquals(MutableVector(), MutableVector.ofZeros(0))
        assertEquals(MutableVector(0), MutableVector.ofZeros(1))
        assertEquals(MutableVector(0, 0), MutableVector.ofZeros(2))
        assertEquals(MutableVector(0, 0, 0), MutableVector.ofZeros(3))
        assertEquals(MutableVector(0, 0, 0, 0, 0), MutableVector.ofZeros(5))
        assertEquals(MutableVector(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), MutableVector.ofZeros(10))
    }
}
