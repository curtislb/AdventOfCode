package com.curtislb.adventofcode.common.geometry

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests [SpatialInfo].
 */
class SpatialInfoTest {
    @Test
    fun testMoveForwardZeroDistance() {
        assertEquals(
            SpatialInfo(Point(-16, -15), Direction.UP),
            SpatialInfo(Point(-16, -15), Direction.UP).move(distance = 0)
        )
        assertEquals(
            SpatialInfo(Point(16, -9), Direction.RIGHT),
            SpatialInfo(Point(16, -9), Direction.RIGHT).move(distance = 0)
        )
        assertEquals(
            SpatialInfo(Point(0, -4), Direction.DOWN),
            SpatialInfo(Point(0, -4), Direction.DOWN).move(distance = 0)
        )
        assertEquals(
            SpatialInfo(Point(14, -5), Direction.LEFT),
            SpatialInfo(Point(14, -5), Direction.LEFT).move(distance = 0)
        )
    }

    @Test
    fun testMoveForwardWhileFacingUp() {
        val spatialInfo = SpatialInfo(Point(-16, 2), Direction.UP)
        assertEquals(SpatialInfo(Point(-16, 3), Direction.UP), spatialInfo.move())
        assertEquals(SpatialInfo(Point(-16, 5), Direction.UP), spatialInfo.move(distance = 3))
        assertEquals(SpatialInfo(Point(-16, -7), Direction.UP), spatialInfo.move(distance = -9))
    }

    @Test
    fun testMoveForwardWhileFacingRight() {
        val spatialInfo = SpatialInfo(Point(-10, 7), Direction.RIGHT)
        assertEquals(SpatialInfo(Point(-9, 7), Direction.RIGHT), spatialInfo.move())
        assertEquals(SpatialInfo(Point(1, 7), Direction.RIGHT), spatialInfo.move(distance = 11))
        assertEquals(SpatialInfo(Point(-24, 7), Direction.RIGHT), spatialInfo.move(distance = -14))
    }

    @Test
    fun testMoveForwardWhileFacingDown() {
        val spatialInfo = SpatialInfo(Point(0, -1), Direction.DOWN)
        assertEquals(SpatialInfo(Point(0, -2), Direction.DOWN), spatialInfo.move())
        assertEquals(SpatialInfo(Point(0, -6), Direction.DOWN), spatialInfo.move(distance = 5))
        assertEquals(SpatialInfo(Point(0, 2), Direction.DOWN), spatialInfo.move(distance = -3))
    }

    @Test
    fun testMoveForwardWhileFacingLeft() {
        val spatialInfo = SpatialInfo(Point(14, -3), Direction.LEFT)
        assertEquals(SpatialInfo(Point(13, -3), Direction.LEFT), spatialInfo.move())
        assertEquals(SpatialInfo(Point(12, -3), Direction.LEFT), spatialInfo.move(distance = 2))
        assertEquals(SpatialInfo(Point(26, -3), Direction.LEFT), spatialInfo.move(distance = -12))
    }

    @Test
    fun testTurnAround() {
        assertEquals(
            SpatialInfo(Point(4, 11), Direction.DOWN),
            SpatialInfo(Point(4, 11), Direction.UP).turnAround()
        )
        assertEquals(
            SpatialInfo(Point(-20, 0), Direction.LEFT),
            SpatialInfo(Point(-20, 0), Direction.RIGHT).turnAround()
        )
        assertEquals(
            SpatialInfo(Point(-17, -12), Direction.UP),
            SpatialInfo(Point(-17, -12), Direction.DOWN).turnAround()
        )
        assertEquals(
            SpatialInfo(Point(14, -3), Direction.RIGHT),
            SpatialInfo(Point(14, -3), Direction.LEFT).turnAround()
        )
    }

    @Test
    fun testTurnLeft() {
        assertEquals(
            SpatialInfo(Point(-8, 1), Direction.LEFT),
            SpatialInfo(Point(-8, 1), Direction.UP).turnLeft()
        )
        assertEquals(
            SpatialInfo(Point(16, 17), Direction.UP),
            SpatialInfo(Point(16, 17), Direction.RIGHT).turnLeft()
        )
        assertEquals(
            SpatialInfo(Point(0, 15), Direction.RIGHT),
            SpatialInfo(Point(0, 15), Direction.DOWN).turnLeft()
        )
        assertEquals(
            SpatialInfo(Point(-8, -19), Direction.DOWN),
            SpatialInfo(Point(-8, -19), Direction.LEFT).turnLeft()
        )
    }

    @Test
    fun testTurnRight() {
        assertEquals(
            SpatialInfo(Point(-17, 2), Direction.RIGHT),
            SpatialInfo(Point(-17, 2), Direction.UP).turnRight()
        )
        assertEquals(
            SpatialInfo(Point(-2, -4), Direction.DOWN),
            SpatialInfo(Point(-2, -4), Direction.RIGHT).turnRight()
        )
        assertEquals(
            SpatialInfo(Point(-17, 12), Direction.LEFT),
            SpatialInfo(Point(-17, 12), Direction.DOWN).turnRight()
        )
        assertEquals(
            SpatialInfo(Point(-4, 16), Direction.UP),
            SpatialInfo(Point(-4, 16), Direction.LEFT).turnRight()
        )
    }

    @Test
    fun testTurnTowardSameDirection() {
        assertEquals(
            SpatialInfo(Point(-12, -9), Direction.UP),
            SpatialInfo(Point(-12, -9), Direction.UP).turnToward(Direction.UP)
        )
        assertEquals(
            SpatialInfo(Point(-6, 0), Direction.RIGHT),
            SpatialInfo(Point(-6, 0), Direction.RIGHT).turnToward(Direction.RIGHT)
        )
        assertEquals(
            SpatialInfo(Point(15, 15), Direction.DOWN),
            SpatialInfo(Point(15, 15), Direction.DOWN).turnToward(Direction.DOWN)
        )
        assertEquals(
            SpatialInfo(Point(-18, 6), Direction.LEFT),
            SpatialInfo(Point(-18, 6), Direction.LEFT).turnToward(Direction.LEFT)
        )
    }

    @Test
    fun testTurnTowardDifferentDirection() {
        assertEquals(
            SpatialInfo(Point(1, 17), Direction.LEFT),
            SpatialInfo(Point(1, 17), Direction.UP).turnToward(Direction.LEFT)
        )
        assertEquals(
            SpatialInfo(Point(14, -14), Direction.DOWN),
            SpatialInfo(Point(14, -14), Direction.RIGHT).turnToward(Direction.DOWN)
        )
        assertEquals(
            SpatialInfo(Point(-17, 16), Direction.UP),
            SpatialInfo(Point(-17, 16), Direction.DOWN).turnToward(Direction.UP)
        )
        assertEquals(
            SpatialInfo(Point(0, -11), Direction.RIGHT),
            SpatialInfo(Point(0, -11), Direction.LEFT).turnToward(Direction.RIGHT)
        )
    }
}
