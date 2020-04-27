package com.curtislb.adventofcode.common.grid

import com.curtislb.adventofcode.common.testing.assertAlmostEquals
import com.curtislb.adventofcode.common.testing.assertContainsExactly
import org.junit.Test
import java.lang.IllegalArgumentException
import kotlin.math.PI
import kotlin.test.assertEquals

/**
 * Tests [Point].
 */
class PointTest {
    @Test fun testNeighbors() {
        assertContainsExactly(listOf(Point(0, 1), Point(1, 0), Point(0, -1), Point(-1, 0)), Point(0, 0).neighbors)
        assertContainsExactly(listOf(Point(10, 1), Point(11, 0), Point(10, -1), Point(9, 0)), Point(10, 0).neighbors)
        assertContainsExactly(listOf(Point(18, 10), Point(19, 9), Point(18, 8), Point(17, 9)), Point(18, 9).neighbors)
        assertContainsExactly(
            listOf(Point(-18, -18), Point(-17, -19), Point(-18, -20), Point(-19, -19)),
            Point(-18, -19).neighbors
        )
    }

    @Test fun testMoveZeroDistance() {
        assertEquals(Point(-2, -12), Point(-2, -12).move(Direction.UP, distance = 0))
        assertEquals(Point(0, 16), Point(0, 16).move(Direction.RIGHT, distance = 0))
        assertEquals(Point(-9, 13), Point(-9, 13).move(Direction.DOWN, distance = 0))
        assertEquals(Point(-1, -2), Point(-1, -2).move(Direction.LEFT, distance = 0))
    }

    @Test fun testMoveNonzeroDistance() {
        assertEquals(Point(0, 18), Point(0, 17).move(Direction.UP))
        assertEquals(Point(18, -2), Point(17, -2).move(Direction.RIGHT))
        assertEquals(Point(-14, 7), Point(-14, 8).move(Direction.DOWN))
        assertEquals(Point(1, 10), Point(2, 10).move(Direction.LEFT))
        assertEquals(Point(12, 16), Point(12, 13).move(Direction.UP, 3))
        assertEquals(Point(2, -10), Point(-8, -10).move(Direction.RIGHT, 10))
        assertEquals(Point(0, 10), Point(0, -8).move(Direction.DOWN, -18))
        assertEquals(Point(6, -6), Point(11, -6).move(Direction.LEFT, 5))
    }

    @Test fun testManhattanDistance() {
        assertEquals(0, Point(1, -17).manhattanDistance(Point(1, -17)))
        assertEquals(1, Point(-20, 0).manhattanDistance(Point(-21, 0)))
        assertEquals(21, Point(18, 3).manhattanDistance(Point(14, -14)))
        assertEquals(26, Point(-12, -16).manhattanDistance(Point(-19, 3)))
        assertEquals(32, Point(13, 3).manhattanDistance(Point(-2, 20)))
        assertEquals(38, Point(-10, 19).manhattanDistance(Point(12, 3)))
    }

    @Test fun testSquaredDistance() {
        assertEquals(0, Point(1, -18).squaredDistance(Point(1, -18)))
        assertEquals(1, Point(-2, 18).squaredDistance(Point(-2, 17)))
        assertEquals(10, Point(-15, 12).squaredDistance(Point(-14, 15)))
        assertEquals(50, Point(9, -4).squaredDistance(Point(16, -3)))
        assertEquals(169, Point(11, 6).squaredDistance(Point(16, -6)))
        assertEquals(289, Point(15, -20).squaredDistance(Point(7, -5)))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testAngleClockwiseFromPositiveYWithSamePoint() {
        Point(6, 3).angleClockwiseFromPositiveY(Point(6, 3))
    }

    @Test fun testAngleClockwiseFromPositiveYWithDifferentPoint() {
        assertAlmostEquals(0.0, Point(13, -20).angleClockwiseFromPositiveY(Point(13, -19)))
        assertAlmostEquals(PI / 2.0, Point(0, -9).angleClockwiseFromPositiveY(Point(12, -9)))
        assertAlmostEquals(PI, Point(-6, 17).angleClockwiseFromPositiveY(Point(-6, 12)))
        assertAlmostEquals(PI * 1.5,  Point(4, 16).angleClockwiseFromPositiveY(Point(-2, 16)))
        assertAlmostEquals(PI / 4.0, Point(4, 7).angleClockwiseFromPositiveY(Point(9, 12)))
        assertAlmostEquals(0.3217505543966, Point(-19, 3).angleClockwiseFromPositiveY(Point(-16, 12)))
        assertAlmostEquals(2.9562447035941, Point(-11, 5).angleClockwiseFromPositiveY(Point(-8, -11)))
        assertAlmostEquals(2.8555412118725, Point(-20, 13).angleClockwiseFromPositiveY(Point(-15, -4)))
        assertAlmostEquals(3.4400915851760, Point(16, 16).angleClockwiseFromPositiveY(Point(12, 3)))
        assertAlmostEquals(3.2522498747637, Point(9, 4).angleClockwiseFromPositiveY(Point(7, -14)))
        assertAlmostEquals(4.7891608716545, Point(12, -1).angleClockwiseFromPositiveY(Point(-1, 0)))
        assertAlmostEquals(5.6577002669404, Point(18, -19).angleClockwiseFromPositiveY(Point(5, -1)))
    }

    @Test fun testConvertToMatrixCoordinates() {
        assertEquals(Pair(0, 0), Point(0, 0).toMatrixCoordinates())
        assertEquals(Pair(7, 9), Point(9, -7).toMatrixCoordinates())
        assertEquals(Pair(12, 20), Point(20, -12).toMatrixCoordinates())
        assertEquals(Pair(15, 0), Point(0, -15).toMatrixCoordinates())
        assertEquals(Pair(17, 8), Point(8, -17).toMatrixCoordinates())
        assertEquals(Pair(5, -2), Point(-2, -5).toMatrixCoordinates())
        assertEquals(Pair(13, -6), Point(-6, -13).toMatrixCoordinates())
        assertEquals(Pair(-10, 2), Point(2, 10).toMatrixCoordinates())
        assertEquals(Pair(-9, 5), Point(5, 9).toMatrixCoordinates())
        assertEquals(Pair(-5, -19), Point(-19, 5).toMatrixCoordinates())
        assertEquals(Pair(-7, -3), Point(-3, 7).toMatrixCoordinates())
    }

    @Test fun testConstructFromMatrixCoordinates() {
        assertEquals(Point(0, 0), Point.fromMatrixCoordinates(0, 0))
        assertEquals(Point(5, -1), Point.fromMatrixCoordinates(1, 5))
        assertEquals(Point(0, -7), Point.fromMatrixCoordinates(7, 0))
        assertEquals(Point(3, -4), Point.fromMatrixCoordinates(4, 3))
        assertEquals(Point(19, -9), Point.fromMatrixCoordinates(9, 19))
        assertEquals(Point(11, -18), Point.fromMatrixCoordinates(18, 11))
        assertEquals(Point(9, -8), Point.fromMatrixCoordinates(8, 9))
        assertEquals(Point(-15, -11), Point.fromMatrixCoordinates(11, -15))
        assertEquals(Point(-6, -5), Point.fromMatrixCoordinates(5, -6))
        assertEquals(Point(15, 3), Point.fromMatrixCoordinates(-3, 15))
        assertEquals(Point(7, 16), Point.fromMatrixCoordinates(-16, 7))
        assertEquals(Point(-17, 13), Point.fromMatrixCoordinates(-13, -17))
        assertEquals(Point(-16, 17), Point.fromMatrixCoordinates(-17, -16))
    }
}
