package com.curtislb.adventofcode.common.geometry

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DirectionTest {
    @Test
    fun testReverse() {
        assertEquals(Direction.DOWN, Direction.UP.reverse())
        assertEquals(Direction.LEFT, Direction.RIGHT.reverse())
        assertEquals(Direction.UP, Direction.DOWN.reverse())
        assertEquals(Direction.RIGHT, Direction.LEFT.reverse())
    }

    @Test
    fun testTurnLeft() {
        assertEquals(Direction.LEFT, Direction.UP.turnLeft())
        assertEquals(Direction.UP, Direction.RIGHT.turnLeft())
        assertEquals(Direction.RIGHT, Direction.DOWN.turnLeft())
        assertEquals(Direction.DOWN, Direction.LEFT.turnLeft())
    }

    @Test
    fun testTurnRight() {
        assertEquals(Direction.RIGHT, Direction.UP.turnRight())
        assertEquals(Direction.DOWN, Direction.RIGHT.turnRight())
        assertEquals(Direction.LEFT, Direction.DOWN.turnRight())
        assertEquals(Direction.UP, Direction.LEFT.turnRight())
    }

    @Test
    fun testFromValidChar() {
        assertEquals(Direction.UP, Direction.fromChar('U'))
        assertEquals(Direction.UP, Direction.fromChar('u'))
        assertEquals(Direction.DOWN, Direction.fromChar('D'))
        assertEquals(Direction.DOWN, Direction.fromChar('d'))
        assertEquals(Direction.RIGHT, Direction.fromChar('R'))
        assertEquals(Direction.RIGHT, Direction.fromChar('r'))
        assertEquals(Direction.LEFT, Direction.fromChar('L'))
        assertEquals(Direction.LEFT, Direction.fromChar('l'))
    }

    @Test
    fun testFromInvalidChar() {
        assertThrows<IllegalArgumentException> { Direction.fromChar('F') }
    }
}
