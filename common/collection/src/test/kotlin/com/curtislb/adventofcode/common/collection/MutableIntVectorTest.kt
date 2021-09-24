package com.curtislb.adventofcode.common.collection

import kotlin.test.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Test

/**
 * Tests [MutableIntVector].
 */
class MutableIntVectorTest {
    @Test
    fun testGetSetAndSize() {
        var vector = MutableIntVector()
        assertEquals(0, vector.size)

        vector = MutableIntVector(0)
        assertEquals(1, vector.size)
        assertEquals(0, vector[0])

        vector[0] = 30
        assertEquals(1, vector.size)
        assertEquals(30, vector[0])

        vector = MutableIntVector(1, 2)
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

        vector = MutableIntVector(66, -57, 55)
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

    @Test
    fun testUpdateWithNewComponents() {
        var vector = MutableIntVector()
        vector.update()
        assertEquals(MutableIntVector(), vector)

        vector = MutableIntVector(44, 8, -65, -54, 33)
        vector.update(7, -55, -78, -91, 35)
        assertEquals(MutableIntVector(7, -55, -78, -91, 35), vector)
    }

    @Test
    fun testUpdateWithWrongNumberOfNewComponents() {
        assertThrows<IllegalArgumentException> { MutableIntVector(-6, 7).update(8, -9, 10) }
    }

    @Test
    fun testUpdateWithComponentValues() {
        var vector = MutableIntVector()
        vector.update(mapOf())
        assertEquals(MutableIntVector(), vector)

        vector = MutableIntVector(-32, -92, -97, -68, 86)
        vector.update(mapOf(0 to 52, 3 to 4, 4 to -19))
        assertEquals(MutableIntVector(52, -92, -97, 4, -19), vector)
    }

    @Test
    fun testUpdateWithInvalidComponentValues() {
        assertThrows<IllegalArgumentException> {
            MutableIntVector(11, 12, -13).update(mapOf(0 to -14, 3 to 15))
        }
    }

    @Test
    fun testAdd() {
        var vectorA = MutableIntVector()
        var vectorB = MutableIntVector()
        vectorA.add(vectorB)
        assertEquals(MutableIntVector(), vectorA)
        assertEquals(MutableIntVector(), vectorB)

        vectorA = MutableIntVector(5)
        vectorB = MutableIntVector(-2)
        vectorA.add(vectorB)
        assertEquals(MutableIntVector(3), vectorA)
        assertEquals(MutableIntVector(-2), vectorB)

        vectorA = MutableIntVector(29, -44, 91)
        vectorB = MutableIntVector(74, 89, -98)
        vectorA.add(vectorB)
        assertEquals(MutableIntVector(103, 45, -7), vectorA)
        assertEquals(MutableIntVector(74, 89, -98), vectorB)
    }

    @Test
    fun testAddWithWrongSizeVector() {
        assertThrows<IllegalArgumentException> { MutableIntVector(3, -4).add(MutableIntVector(5)) }
    }

    @Test
    fun testPlus() {
        var vectorA = MutableIntVector()
        var vectorB = MutableIntVector()
        assertEquals(MutableIntVector(), vectorA + vectorB)

        vectorA = MutableIntVector(1)
        vectorB = MutableIntVector(-3)
        var vectorSum = vectorA + vectorB
        assertEquals(MutableIntVector(-2), vectorSum)

        vectorA = MutableIntVector(-39, -4, 19)
        vectorB = MutableIntVector(91, -67, -66)
        vectorSum = vectorA + vectorB
        assertEquals(MutableIntVector(52, -71, -47), vectorSum)

        vectorA[1] = 29
        vectorB[2] = -43
        assertEquals(MutableIntVector(52, -71, -47), vectorSum)
    }

    @Test
    fun testPlusWithWrongSizeVector() {
        assertThrows<IllegalArgumentException> { MutableIntVector(1) + MutableIntVector(2, 3) }
    }

    @Test
    fun testSumBy() {
        var vector = MutableIntVector()
        assertEquals(0, vector.sumBy { it })
        assertEquals(0, vector.sumBy { -it })
        assertEquals(0, vector.sumBy { (it - 1) * 2 })
        assertEquals(0, vector.sumBy { it * it })

        vector = MutableIntVector(-23)
        assertEquals(-23, vector.sumBy { it })
        assertEquals(23, vector.sumBy { -it })
        assertEquals(-48, vector.sumBy { (it - 1) * 2 })
        assertEquals(529, vector.sumBy { it * it })

        vector = MutableIntVector(10, 1, -4)
        assertEquals(7, vector.sumBy { it })
        assertEquals(-7, vector.sumBy { -it })
        assertEquals(8, vector.sumBy { (it - 1) * 2 })
        assertEquals(117, vector.sumBy { it * it })
    }

    @Test
    fun testCopy() {
        val vector = MutableIntVector(82, -30, -27)
        val vectorCopy = vector.copy()
        assertEquals(MutableIntVector(82, -30, -27), vectorCopy)

        vector[0] = -61
        vector[1] = 77
        vector[2] = 8
        assertEquals(MutableIntVector(82, -30, -27), vectorCopy)
    }

    @Test
    fun testToString() {
        assertEquals("<>", MutableIntVector().toString())
        assertEquals("<0>", MutableIntVector(0).toString())
        assertEquals("<-1>", MutableIntVector(-1).toString())
        assertEquals("<1, 2, 3>", MutableIntVector(1, 2, 3).toString())
        assertEquals("<-3, 5, -2>", MutableIntVector(-3, 5, -2).toString())
    }

    @Test
    fun testConstructMutableVectorOfZeros() {
        assertEquals(MutableIntVector(), MutableIntVector.ofZeros(0))
        assertEquals(MutableIntVector(0), MutableIntVector.ofZeros(1))
        assertEquals(MutableIntVector(0, 0), MutableIntVector.ofZeros(2))
        assertEquals(MutableIntVector(0, 0, 0), MutableIntVector.ofZeros(3))
        assertEquals(MutableIntVector(0, 0, 0, 0, 0), MutableIntVector.ofZeros(5))
        assertEquals(MutableIntVector(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), MutableIntVector.ofZeros(10))
    }
}
