package com.curtislb.adventofcode.common.collection

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests [MutableVector].
 */
class MutableVectorTest {
    @Test fun testConstructFromString() {
        assertEquals(MutableVector(3, -15, 44), MutableVector("<x=3,y=-15,z=44>"))
    }

    @Test fun testAddition() {
        val vectorA = MutableVector(-39, -4, 19)
        val vectorB = MutableVector(91, -67, -66)
        assertEquals(MutableVector(52, -71, -47), vectorA + vectorB)

        vectorB.add(vectorA)
        assertEquals(MutableVector(52, -71, -47), vectorB)
        assertEquals(MutableVector(13, -75, -28), vectorB + vectorA)
    }

    @Test fun testSumBy() {
        val vector = MutableVector(10, 1, -4)
        assertEquals(7, vector.sumBy { it })
        assertEquals(-7, vector.sumBy { -it })
        assertEquals(8, vector.sumBy { (it - 1) * 2 })
        assertEquals(117, vector.sumBy { it * it })
    }
}
