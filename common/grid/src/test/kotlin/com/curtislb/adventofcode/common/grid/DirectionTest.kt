package com.curtislb.adventofcode.common.grid

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
    fun testCountRightTurns() {
        assertEquals(0, Direction.UP.countRightTurns(Direction.UP))
        assertEquals(1, Direction.UP.countRightTurns(Direction.RIGHT))
        assertEquals(2, Direction.UP.countRightTurns(Direction.DOWN))
        assertEquals(3, Direction.UP.countRightTurns(Direction.LEFT))
        assertEquals(3, Direction.RIGHT.countRightTurns(Direction.UP))
        assertEquals(0, Direction.RIGHT.countRightTurns(Direction.RIGHT))
        assertEquals(1, Direction.RIGHT.countRightTurns(Direction.DOWN))
        assertEquals(2, Direction.RIGHT.countRightTurns(Direction.LEFT))
        assertEquals(2, Direction.DOWN.countRightTurns(Direction.UP))
        assertEquals(3, Direction.DOWN.countRightTurns(Direction.RIGHT))
        assertEquals(0, Direction.DOWN.countRightTurns(Direction.DOWN))
        assertEquals(1, Direction.DOWN.countRightTurns(Direction.LEFT))
        assertEquals(1, Direction.LEFT.countRightTurns(Direction.UP))
        assertEquals(2, Direction.LEFT.countRightTurns(Direction.RIGHT))
        assertEquals(3, Direction.LEFT.countRightTurns(Direction.DOWN))
        assertEquals(0, Direction.LEFT.countRightTurns(Direction.LEFT))
    }

    @Test
    fun testFromValidChar() {
        assertEquals(Direction.UP, Direction.from('U'))
        assertEquals(Direction.UP, Direction.from('u'))
        assertEquals(Direction.DOWN, Direction.from('D'))
        assertEquals(Direction.DOWN, Direction.from('d'))
        assertEquals(Direction.RIGHT, Direction.from('R'))
        assertEquals(Direction.RIGHT, Direction.from('r'))
        assertEquals(Direction.LEFT, Direction.from('L'))
        assertEquals(Direction.LEFT, Direction.from('l'))
    }

    @Test
    fun testFromInvalidChar() {
        assertThrows<IllegalArgumentException> { Direction.from('F') }
    }
}
