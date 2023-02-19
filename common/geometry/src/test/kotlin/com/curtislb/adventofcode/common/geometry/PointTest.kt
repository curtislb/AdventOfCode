package com.curtislb.adventofcode.common.geometry

import com.curtislb.adventofcode.common.testing.assertAlmostEquals
import com.curtislb.adventofcode.common.testing.assertContainsExactly
import kotlin.math.PI
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [Point].
 */
class PointTest {
    @Test
    fun testPlus() {
        assertEquals(Point.ORIGIN, Point.ORIGIN + Point.ORIGIN)
        assertEquals(Point(1, -2), Point.ORIGIN + Point(1, -2))
        assertEquals(Point(-3, 4), Point(-3, 4) + Point.ORIGIN)
        assertEquals(Point(-57, 3), Point(-44, 16) + Point(-13, -13))
        assertEquals(Point(-67, -73), Point(-22, -42) + Point(-45, -31))
        assertEquals(Point(5, -15), Point(23, 10) + Point(-18, -25))
        assertEquals(Point(-44, 17), Point(-10, -5) + Point(-34, 22))
        assertEquals(Point(65, 20), Point(24, 8) + Point(41, 12))
        assertEquals(Point(15, -31), Point(37, -6) + Point(-22, -25))
        assertEquals(Point(49, -7), Point(33, 23) + Point(16, -30))
    }

    @Test
    fun testMinus() {
        assertEquals(Point.ORIGIN, Point.ORIGIN - Point.ORIGIN)
        assertEquals(Point(-1, 2), Point.ORIGIN - Point(1, -2))
        assertEquals(Point(3, -4), Point(3, -4) - Point.ORIGIN)
        assertEquals(Point(53, 52), Point(15, 43) - Point(-38, -9))
        assertEquals(Point(0, -17), Point(-41, 4) - Point(-41, 21))
        assertEquals(Point(8, -12), Point(36, -9) - Point(28, 3))
        assertEquals(Point(-75, -15), Point(-28, 24) - Point(47, 39))
        assertEquals(Point(30, -77), Point(9, -31) - Point(-21, 46))
        assertEquals(Point(6, -15), Point(-30, 33) - Point(-36, 48))
        assertEquals(Point(-67, 23), Point(-35, -12) - Point(32, -35))
    }

    @Test
    fun testAllNeighbors() {
        assertContainsExactly(
            listOf(
                Point(0, 1),
                Point(1, 0),
                Point(0, -1),
                Point(-1, 0),
                Point(1, 1),
                Point(1, -1),
                Point(-1, 1),
                Point(-1, -1)
            ),
            Point(0, 0).allNeighbors()
        )
        assertContainsExactly(
            listOf(
                Point(10, 1),
                Point(11, 0),
                Point(10, -1),
                Point(9, 0),
                Point(11, 1),
                Point(11, -1),
                Point(9, 1),
                Point(9, -1)
            ),
            Point(10, 0).allNeighbors()
        )
        assertContainsExactly(
            listOf(
                Point(18, 10),
                Point(19, 9),
                Point(18, 8),
                Point(17, 9),
                Point(19, 10),
                Point(19, 8),
                Point(17, 10),
                Point(17, 8)
            ),
            Point(18, 9).allNeighbors()
        )
        assertContainsExactly(
            listOf(
                Point(-18, -18),
                Point(-17, -19),
                Point(-18, -20),
                Point(-19, -19),
                Point(-17, -18),
                Point(-17, -20),
                Point(-19, -18),
                Point(-19, -20)
            ),
            Point(-18, -19).allNeighbors()
        )
    }

    @Test
    fun testCardinalNeighbors() {
        assertContainsExactly(
            listOf(Point(0, 1), Point(1, 0), Point(0, -1), Point(-1, 0)),
            Point(0, 0).cardinalNeighbors()
        )
        assertContainsExactly(
            listOf(Point(10, 1), Point(11, 0), Point(10, -1), Point(9, 0)),
            Point(10, 0).cardinalNeighbors()
        )
        assertContainsExactly(
            listOf(Point(18, 10), Point(19, 9), Point(18, 8), Point(17, 9)),
            Point(18, 9).cardinalNeighbors()
        )
        assertContainsExactly(
            listOf(Point(-18, -18), Point(-17, -19), Point(-18, -20), Point(-19, -19)),
            Point(-18, -19).cardinalNeighbors()
        )
    }

    @Test
    fun testDiagonalNeighbors() {
        assertContainsExactly(
            listOf(Point(1, 1), Point(1, -1), Point(-1, 1), Point(-1, -1)),
            Point(0, 0).diagonalNeighbors()
        )
        assertContainsExactly(
            listOf(Point(11, 1), Point(11, -1), Point(9, 1), Point(9, -1)),
            Point(10, 0).diagonalNeighbors()
        )
        assertContainsExactly(
            listOf(Point(19, 10), Point(19, 8), Point(17, 10), Point(17, 8)),
            Point(18, 9).diagonalNeighbors()
        )
        assertContainsExactly(
            listOf(Point(-17, -18), Point(-17, -20), Point(-19, -18), Point(-19, -20)),
            Point(-18, -19).diagonalNeighbors()
        )
    }

    @Test
    fun testMoveZeroDistance() {
        assertEquals(Point(-2, -12), Point(-2, -12).move(Direction.UP, distance = 0))
        assertEquals(Point(0, 16), Point(0, 16).move(Direction.RIGHT, distance = 0))
        assertEquals(Point(-9, 13), Point(-9, 13).move(Direction.DOWN, distance = 0))
        assertEquals(Point(-1, -2), Point(-1, -2).move(Direction.LEFT, distance = 0))
    }

    @Test
    fun testMoveNonzeroDistance() {
        assertEquals(Point(0, 18), Point(0, 17).move(Direction.UP))
        assertEquals(Point(18, -2), Point(17, -2).move(Direction.RIGHT))
        assertEquals(Point(-14, 7), Point(-14, 8).move(Direction.DOWN))
        assertEquals(Point(1, 10), Point(2, 10).move(Direction.LEFT))
        assertEquals(Point(12, 16), Point(12, 13).move(Direction.UP, 3))
        assertEquals(Point(2, -10), Point(-8, -10).move(Direction.RIGHT, 10))
        assertEquals(Point(0, 10), Point(0, -8).move(Direction.DOWN, -18))
        assertEquals(Point(6, -6), Point(11, -6).move(Direction.LEFT, 5))
    }

    @Test
    fun testRotateClockwise() {
        assertEquals(Point.ORIGIN, Point.ORIGIN.rotateClockwise())
        assertEquals(Point(0, -1), Point(1, 0).rotateClockwise())
        assertEquals(Point(-1, 0), Point(0, -1).rotateClockwise())
        assertEquals(Point(0, 1), Point(-1, 0).rotateClockwise())
        assertEquals(Point(1, 0), Point(0, 1).rotateClockwise())
        assertEquals(Point(3, -47), Point(47, 3).rotateClockwise())
        assertEquals(Point(-41, 2), Point(-2, -41).rotateClockwise())
        assertEquals(Point(-30, -31), Point(31, -30).rotateClockwise())
        assertEquals(Point(10, 37), Point(-37, 10).rotateClockwise())
        assertEquals(Point(-35, 13), Point(-9, 49).rotateClockwise(Point(-40, 44)))
        assertEquals(Point(-7, -17), Point(1, 27).rotateClockwise(Point(-25, 9)))
        assertEquals(Point(-6, -43), Point(38, 11).rotateClockwise(Point(-11, 6)))
        assertEquals(Point(-28, 32), Point(17, 23).rotateClockwise(Point(-1, 50)))
        assertEquals(Point(55, -46), Point(-36, 37).rotateClockwise(Point(-32, -50)))
        assertEquals(Point(70, -13), Point(-47, 40).rotateClockwise(Point(-15, -45)))
        assertEquals(Point(67, -22), Point(-5, 40).rotateClockwise(Point(0, -27)))
        assertEquals(Point(51, 46), Point(2, 39).rotateClockwise(Point(30, 18)))
    }

    @Test
    fun testRotateCounterclockwise() {
        assertEquals(Point.ORIGIN, Point.ORIGIN.rotateCounterclockwise())
        assertEquals(Point(0, 1), Point(1, 0).rotateCounterclockwise())
        assertEquals(Point(-1, 0), Point(0, 1).rotateCounterclockwise())
        assertEquals(Point(0, -1), Point(-1, 0).rotateCounterclockwise())
        assertEquals(Point(1, 0), Point(0, -1).rotateCounterclockwise())
        assertEquals(Point(-3, 47), Point(47, 3).rotateCounterclockwise())
        assertEquals(Point(41, -2), Point(-2, -41).rotateCounterclockwise())
        assertEquals(Point(30, 31), Point(31, -30).rotateCounterclockwise())
        assertEquals(Point(-10, -37), Point(-37, 10).rotateCounterclockwise())
        assertEquals(Point(-45, 75), Point(-9, 49).rotateCounterclockwise(Point(-40, 44)))
        assertEquals(Point(-43, 35), Point(1, 27).rotateCounterclockwise(Point(-25, 9)))
        assertEquals(Point(-16, 55), Point(38, 11).rotateCounterclockwise(Point(-11, 6)))
        assertEquals(Point(26, 68), Point(17, 23).rotateCounterclockwise(Point(-1, 50)))
        assertEquals(Point(-119, -54), Point(-36, 37).rotateCounterclockwise(Point(-32, -50)))
        assertEquals(Point(-100, -77), Point(-47, 40).rotateCounterclockwise(Point(-15, -45)))
        assertEquals(Point(-67, -32), Point(-5, 40).rotateCounterclockwise(Point(0, -27)))
        assertEquals(Point(9, -10), Point(2, 39).rotateCounterclockwise(Point(30, 18)))
    }

    @Test
    fun testRotate180Degrees() {
        assertEquals(Point.ORIGIN, Point.ORIGIN.rotate180Degrees())
        assertEquals(Point(-1, 0), Point(1, 0).rotate180Degrees())
        assertEquals(Point(1, 0), Point(-1, 0).rotate180Degrees())
        assertEquals(Point(0, -1), Point(0, 1).rotate180Degrees())
        assertEquals(Point(0, 1), Point(0, -1).rotate180Degrees())
        assertEquals(Point(-47, -3), Point(47, 3).rotate180Degrees())
        assertEquals(Point(2, 41), Point(-2, -41).rotate180Degrees())
        assertEquals(Point(-31, 30), Point(31, -30).rotate180Degrees())
        assertEquals(Point(37, -10), Point(-37, 10).rotate180Degrees())
        assertEquals(Point(-71, 39), Point(-9, 49).rotate180Degrees(Point(-40, 44)))
        assertEquals(Point(-51, -9), Point(1, 27).rotate180Degrees(Point(-25, 9)))
        assertEquals(Point(-60, 1), Point(38, 11).rotate180Degrees(Point(-11, 6)))
        assertEquals(Point(-19, 77), Point(17, 23).rotate180Degrees(Point(-1, 50)))
        assertEquals(Point(-28, -137), Point(-36, 37).rotate180Degrees(Point(-32, -50)))
        assertEquals(Point(17, -130), Point(-47, 40).rotate180Degrees(Point(-15, -45)))
        assertEquals(Point(5, -94), Point(-5, 40).rotate180Degrees(Point(0, -27)))
        assertEquals(Point(58, -3), Point(2, 39).rotate180Degrees(Point(30, 18)))
    }

    @Test
    fun testManhattanDistance() {
        assertEquals(0, Point(1, -17).manhattanDistance(Point(1, -17)))
        assertEquals(1, Point(-20, 0).manhattanDistance(Point(-21, 0)))
        assertEquals(21, Point(18, 3).manhattanDistance(Point(14, -14)))
        assertEquals(26, Point(-12, -16).manhattanDistance(Point(-19, 3)))
        assertEquals(32, Point(13, 3).manhattanDistance(Point(-2, 20)))
        assertEquals(38, Point(-10, 19).manhattanDistance(Point(12, 3)))
    }

    @Test
    fun testSquaredDistance() {
        assertEquals(0, Point(1, -18).squaredDistance(Point(1, -18)))
        assertEquals(1, Point(-2, 18).squaredDistance(Point(-2, 17)))
        assertEquals(10, Point(-15, 12).squaredDistance(Point(-14, 15)))
        assertEquals(50, Point(9, -4).squaredDistance(Point(16, -3)))
        assertEquals(169, Point(11, 6).squaredDistance(Point(16, -6)))
        assertEquals(289, Point(15, -20).squaredDistance(Point(7, -5)))
    }

    @Test
    fun testAngleClockwiseFromPositiveYWithSamePoint() {
        assertThrows<IllegalArgumentException> {
            Point(6, 3).angleClockwiseFromPositiveY(Point(6, 3))
        }
    }

    @Test
    fun testAngleClockwiseFromPositiveYWithDifferentPoint() {
        assertAlmostEquals(0.0, Point(13, -20).angleClockwiseFromPositiveY(Point(13, -19)))
        assertAlmostEquals(PI / 2.0, Point(0, -9).angleClockwiseFromPositiveY(Point(12, -9)))
        assertAlmostEquals(PI, Point(-6, 17).angleClockwiseFromPositiveY(Point(-6, 12)))
        assertAlmostEquals(PI * 1.5, Point(4, 16).angleClockwiseFromPositiveY(Point(-2, 16)))
        assertAlmostEquals(PI / 4.0, Point(4, 7).angleClockwiseFromPositiveY(Point(9, 12)))
        assertAlmostEquals(
            0.3217505543966,
            Point(-19, 3).angleClockwiseFromPositiveY(Point(-16, 12))
        )
        assertAlmostEquals(
            2.9562447035941,
            Point(-11, 5).angleClockwiseFromPositiveY(Point(-8, -11))
        )
        assertAlmostEquals(
            2.8555412118725,
            Point(-20, 13).angleClockwiseFromPositiveY(Point(-15, -4))
        )
        assertAlmostEquals(3.4400915851760, Point(16, 16).angleClockwiseFromPositiveY(Point(12, 3)))
        assertAlmostEquals(3.2522498747637, Point(9, 4).angleClockwiseFromPositiveY(Point(7, -14)))
        assertAlmostEquals(4.7891608716545, Point(12, -1).angleClockwiseFromPositiveY(Point(-1, 0)))
        assertAlmostEquals(
            5.6577002669404,
            Point(18, -19).angleClockwiseFromPositiveY(Point(5, -1))
        )
    }

    @Test
    fun testToString() {
        assertEquals("(0, 0)", Point.ORIGIN.toString())
        assertEquals("(4, 20)", Point(4, 20).toString())
        assertEquals("(14, -12)", Point(14, -12).toString())
        assertEquals("(-17, 20)", Point(-17, 20).toString())
        assertEquals("(-18, -15)", Point(-18, -15).toString())
    }

    @Test
    fun testFromMatrixCoordinates() {
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
