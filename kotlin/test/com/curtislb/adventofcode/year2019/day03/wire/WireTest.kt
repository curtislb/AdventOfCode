package com.curtislb.adventofcode.year2019.day03.wire

import com.curtislb.adventofcode.common.grid.Point
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests [Wire].
 */
class WireTest {
    @Test fun testFindNearestIntersectionWithNonIntersectingWire() {
        var wireA = Wire("U1")
        var wireB = Wire("R1")
        assertEquals(Pair(null, Int.MAX_VALUE), wireA.findNearestIntersection(wireB))
        assertEquals(Pair(null, Int.MAX_VALUE), wireB.findNearestIntersection(wireA))

        wireA = Wire("R2")
        wireB = Wire("L2")
        assertEquals(Pair(null, Int.MAX_VALUE), wireA.findNearestIntersection(wireB))
        assertEquals(Pair(null, Int.MAX_VALUE), wireB.findNearestIntersection(wireA))

        wireA = Wire("R1")
        wireB = Wire("R1")
        assertEquals(Pair(null, Int.MAX_VALUE), wireA.findNearestIntersection(wireB))
        assertEquals(Pair(null, Int.MAX_VALUE), wireB.findNearestIntersection(wireA))

        wireA = Wire("L2")
        wireB = Wire("L3")
        assertEquals(Pair(null, Int.MAX_VALUE), wireA.findNearestIntersection(wireB))
        assertEquals(Pair(null, Int.MAX_VALUE), wireB.findNearestIntersection(wireA))

        wireA = Wire("U2,D4")
        wireB = Wire("D3")
        assertEquals(Pair(null, Int.MAX_VALUE), wireA.findNearestIntersection(wireB))
        assertEquals(Pair(null, Int.MAX_VALUE), wireB.findNearestIntersection(wireA))

        wireA = Wire("U4,R2,D3")
        wireB = Wire("R2,U2")
        assertEquals(Pair(null, Int.MAX_VALUE), wireA.findNearestIntersection(wireB))
        assertEquals(Pair(null, Int.MAX_VALUE), wireB.findNearestIntersection(wireA))


        wireA = Wire("R3,U3,L6,D5,R3")
        wireB = Wire("U2,L2,D3,R3,D2,L6,U2")
        assertEquals(Pair(null, Int.MAX_VALUE), wireA.findNearestIntersection(wireB))
        assertEquals(Pair(null, Int.MAX_VALUE), wireB.findNearestIntersection(wireA))
    }

    @Test fun testFindNearestIntersectionWithIntersectingWire() {
        var wireA = Wire("R1,U1")
        var wireB = Wire("U1,R1")
        assertEquals(Pair(Point(1, 1), 2), wireA.findNearestIntersection(wireB))
        assertEquals(Pair(Point(1, 1), 2), wireB.findNearestIntersection(wireA))

        wireA = Wire("D1,L1,D1")
        wireB = Wire("L1,D1,L1")
        assertEquals(Pair(Point(-1, -1), 2), wireA.findNearestIntersection(wireB))
        assertEquals(Pair(Point(-1, -1), 2), wireB.findNearestIntersection(wireA))

        wireA = Wire("U2,R3,U1")
        wireB = Wire("R1,U3,R2")
        assertEquals(Pair(Point(1, 2), 3), wireA.findNearestIntersection(wireB))
        assertEquals(Pair(Point(1, 2), 3), wireB.findNearestIntersection(wireA))

        wireA = Wire("R2,D2,L2,U2")
        wireB = Wire("U2,L2,D2,R2")
        assertEquals(Pair(Point.ORIGIN, 0), wireA.findNearestIntersection(wireB))
        assertEquals(Pair(Point.ORIGIN, 0), wireB.findNearestIntersection(wireA))

        wireA = Wire("U3,R10,D2,L7")
        wireB = Wire("R4,U5,R3")
        assertEquals(Pair(Point(4, 1), 5), wireA.findNearestIntersection(wireB))
        assertEquals(Pair(Point(4, 1), 5), wireB.findNearestIntersection(wireA))

        wireA = Wire("R4,U5,L6,D8,L4,U3,R2")
        wireB = Wire("U3,R6,D4,L11,U3")
        assertEquals(Pair(Point(-2, -1), 3), wireA.findNearestIntersection(wireB))
        assertEquals(Pair(Point(-2, -1), 3), wireB.findNearestIntersection(wireA))

        wireA = Wire("R8,U5,L5,D3")
        wireB = Wire("U7,R6,D4,L4")
        assertEquals(Pair(Point(3, 3), 6), wireA.findNearestIntersection(wireB))
        assertEquals(Pair(Point(3, 3), 6), wireB.findNearestIntersection(wireA))
    }

    @Test fun testFindShortestPathIntersectionWithNonIntersectingWire() {
        var wireA = Wire("U1")
        var wireB = Wire("R1")
        assertEquals(Pair(null, Int.MAX_VALUE), wireA.findShortestPathIntersection(wireB))
        assertEquals(Pair(null, Int.MAX_VALUE), wireB.findShortestPathIntersection(wireA))

        wireA = Wire("R2")
        wireB = Wire("L2")
        assertEquals(Pair(null, Int.MAX_VALUE), wireA.findShortestPathIntersection(wireB))
        assertEquals(Pair(null, Int.MAX_VALUE), wireB.findShortestPathIntersection(wireA))

        wireA = Wire("R1")
        wireB = Wire("R1")
        assertEquals(Pair(null, Int.MAX_VALUE), wireA.findShortestPathIntersection(wireB))
        assertEquals(Pair(null, Int.MAX_VALUE), wireB.findShortestPathIntersection(wireA))

        wireA = Wire("L2")
        wireB = Wire("L3")
        assertEquals(Pair(null, Int.MAX_VALUE), wireA.findShortestPathIntersection(wireB))
        assertEquals(Pair(null, Int.MAX_VALUE), wireB.findShortestPathIntersection(wireA))

        wireA = Wire("U2,D4")
        wireB = Wire("D3")
        assertEquals(Pair(null, Int.MAX_VALUE), wireA.findShortestPathIntersection(wireB))
        assertEquals(Pair(null, Int.MAX_VALUE), wireB.findShortestPathIntersection(wireA))

        wireA = Wire("U4,R2,D3")
        wireB = Wire("R2,U2")
        assertEquals(Pair(null, Int.MAX_VALUE), wireA.findShortestPathIntersection(wireB))
        assertEquals(Pair(null, Int.MAX_VALUE), wireB.findShortestPathIntersection(wireA))

        wireA = Wire("R3,U3,L6,D5,R3")
        wireB = Wire("U2,L2,D3,R3,D2,L6,U2")
        assertEquals(Pair(null, Int.MAX_VALUE), wireA.findShortestPathIntersection(wireB))
        assertEquals(Pair(null, Int.MAX_VALUE), wireB.findShortestPathIntersection(wireA))
    }

    @Test fun testFindShortestPathIntersectionWithIntersectingWire() {
        var wireA = Wire("R1,U1")
        var wireB = Wire("U1,R1")
        assertEquals(Pair(Point(1, 1), 4), wireA.findShortestPathIntersection(wireB))
        assertEquals(Pair(Point(1, 1), 4), wireB.findShortestPathIntersection(wireA))

        wireA = Wire("D1,L1,D1")
        wireB = Wire("L1,D1,L1")
        assertEquals(Pair(Point(-1, -1), 4), wireA.findShortestPathIntersection(wireB))
        assertEquals(Pair(Point(-1, -1), 4), wireB.findShortestPathIntersection(wireA))

        wireA = Wire("U2,R3,U1")
        wireB = Wire("R1,U3,R2")
        assertEquals(Pair(Point(1, 2), 6), wireA.findShortestPathIntersection(wireB))
        assertEquals(Pair(Point(1, 2), 6), wireB.findShortestPathIntersection(wireA))

        wireA = Wire("R2,D2,L2,U2")
        wireB = Wire("U2,L2,D2,R2")
        assertEquals(Pair(Point.ORIGIN, 16), wireA.findShortestPathIntersection(wireB))
        assertEquals(Pair(Point.ORIGIN, 16), wireB.findShortestPathIntersection(wireA))

        wireA = Wire("U3,R10,D2,L7")
        wireB = Wire("R4,U5,R3")
        assertEquals(Pair(Point(4, 3), 14), wireA.findShortestPathIntersection(wireB))
        assertEquals(Pair(Point(4, 3), 14), wireB.findShortestPathIntersection(wireA))

        wireA = Wire("R4,U5,L6,D8,L4,U3,R2")
        wireB = Wire("U3,R6,D4,L11,U3")
        assertEquals(Pair(Point(4, 3), 14), wireA.findShortestPathIntersection(wireB))
        assertEquals(Pair(Point(4, 3), 14), wireB.findShortestPathIntersection(wireA))

        wireA = Wire("R8,U5,L5,D3")
        wireB = Wire("U7,R6,D4,L4")
        assertEquals(Pair(Point(6, 5), 30), wireA.findShortestPathIntersection(wireB))
        assertEquals(Pair(Point(6, 5), 30), wireB.findShortestPathIntersection(wireA))
    }
}
