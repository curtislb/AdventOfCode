package com.curtislb.adventofcode.common.grid

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests [Orientation].
 */
class OrientationTest {
    @Test
    fun testMoveForwardZeroDistance() {
        assertEquals(
            Orientation(Point(-16, -15), Direction.UP),
            Orientation(Point(-16, -15), Direction.UP).moveForward(0)
        )
        assertEquals(
            Orientation(Point(16, -9), Direction.RIGHT),
            Orientation(Point(16, -9), Direction.RIGHT).moveForward(0)
        )
        assertEquals(
            Orientation(Point(0, -4), Direction.DOWN),
            Orientation(Point(0, -4), Direction.DOWN).moveForward(0)
        )
        assertEquals(
            Orientation(Point(14, -5), Direction.LEFT),
            Orientation(Point(14, -5), Direction.LEFT).moveForward(0)
        )
    }

    @Test
    fun testMoveForwardWhileFacingUp() {
        val orientation = Orientation(Point(-16, 2), Direction.UP)
        assertEquals(Orientation(Point(-16, 3), Direction.UP), orientation.moveForward())
        assertEquals(Orientation(Point(-16, 5), Direction.UP), orientation.moveForward(3))
        assertEquals(Orientation(Point(-16, -7), Direction.UP), orientation.moveForward(-9))
    }

    @Test
    fun testMoveForwardWhileFacingRight() {
        val orientation = Orientation(Point(-10, 7), Direction.RIGHT)
        assertEquals(Orientation(Point(-9, 7), Direction.RIGHT), orientation.moveForward())
        assertEquals(Orientation(Point(1, 7), Direction.RIGHT), orientation.moveForward(11))
        assertEquals(Orientation(Point(-24, 7), Direction.RIGHT), orientation.moveForward(-14))
    }

    @Test
    fun testMoveForwardWhileFacingDown() {
        val orientation = Orientation(Point(0, -1), Direction.DOWN)
        assertEquals(Orientation(Point(0, -2), Direction.DOWN), orientation.moveForward())
        assertEquals(Orientation(Point(0, -6), Direction.DOWN), orientation.moveForward(5))
        assertEquals(Orientation(Point(0, 2), Direction.DOWN), orientation.moveForward(-3))
    }

    @Test
    fun testMoveForwardWhileFacingLeft() {
        val orientation = Orientation(Point(14, -3), Direction.LEFT)
        assertEquals(Orientation(Point(13, -3), Direction.LEFT), orientation.moveForward())
        assertEquals(Orientation(Point(12, -3), Direction.LEFT), orientation.moveForward(2))
        assertEquals(Orientation(Point(26, -3), Direction.LEFT), orientation.moveForward(-12))
    }

    @Test
    fun testTurnAround() {
        assertEquals(
            Orientation(Point(4, 11), Direction.DOWN),
            Orientation(Point(4, 11), Direction.UP).turnAround()
        )
        assertEquals(
            Orientation(Point(-20, 0), Direction.LEFT),
            Orientation(Point(-20, 0), Direction.RIGHT).turnAround()
        )
        assertEquals(
            Orientation(Point(-17, -12), Direction.UP),
            Orientation(Point(-17, -12), Direction.DOWN).turnAround()
        )
        assertEquals(
            Orientation(Point(14, -3), Direction.RIGHT),
            Orientation(Point(14, -3), Direction.LEFT).turnAround()
        )
    }

    @Test
    fun testTurnLeft() {
        assertEquals(
            Orientation(Point(-8, 1), Direction.LEFT),
            Orientation(Point(-8, 1), Direction.UP).turnLeft()
        )
        assertEquals(
            Orientation(Point(16, 17), Direction.UP),
            Orientation(Point(16, 17), Direction.RIGHT).turnLeft()
        )
        assertEquals(
            Orientation(Point(0, 15), Direction.RIGHT),
            Orientation(Point(0, 15), Direction.DOWN).turnLeft()
        )
        assertEquals(
            Orientation(Point(-8, -19), Direction.DOWN),
            Orientation(Point(-8, -19), Direction.LEFT).turnLeft()
        )
    }

    @Test
    fun testTurnRight() {
        assertEquals(
            Orientation(Point(-17, 2), Direction.RIGHT),
            Orientation(Point(-17, 2), Direction.UP).turnRight()
        )
        assertEquals(
            Orientation(Point(-2, -4), Direction.DOWN),
            Orientation(Point(-2, -4), Direction.RIGHT).turnRight()
        )
        assertEquals(
            Orientation(Point(-17, 12), Direction.LEFT),
            Orientation(Point(-17, 12), Direction.DOWN).turnRight()
        )
        assertEquals(
            Orientation(Point(-4, 16), Direction.UP),
            Orientation(Point(-4, 16), Direction.LEFT).turnRight()
        )
    }

    @Test
    fun testTurnTowardSameDirection() {
        assertEquals(
            Orientation(Point(-12, -9), Direction.UP),
            Orientation(Point(-12, -9), Direction.UP).turnToward(Direction.UP)
        )
        assertEquals(
            Orientation(Point(-6, 0), Direction.RIGHT),
            Orientation(Point(-6, 0), Direction.RIGHT).turnToward(Direction.RIGHT)
        )
        assertEquals(
            Orientation(Point(15, 15), Direction.DOWN),
            Orientation(Point(15, 15), Direction.DOWN).turnToward(Direction.DOWN)
        )
        assertEquals(
            Orientation(Point(-18, 6), Direction.LEFT),
            Orientation(Point(-18, 6), Direction.LEFT).turnToward(Direction.LEFT)
        )
    }

    @Test
    fun testTurnTowardDifferentDirection() {
        assertEquals(
            Orientation(Point(1, 17), Direction.LEFT),
            Orientation(Point(1, 17), Direction.UP).turnToward(Direction.LEFT)
        )
        assertEquals(
            Orientation(Point(14, -14), Direction.DOWN),
            Orientation(Point(14, -14), Direction.RIGHT).turnToward(Direction.DOWN)
        )
        assertEquals(
            Orientation(Point(-17, 16), Direction.UP),
            Orientation(Point(-17, 16), Direction.DOWN).turnToward(Direction.UP)
        )
        assertEquals(
            Orientation(Point(0, -11), Direction.RIGHT),
            Orientation(Point(0, -11), Direction.LEFT).turnToward(Direction.RIGHT)
        )
    }
}
