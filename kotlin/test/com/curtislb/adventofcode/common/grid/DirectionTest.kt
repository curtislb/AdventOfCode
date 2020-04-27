package com.curtislb.adventofcode.common.grid

import org.junit.Test
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals

class DirectionTest {
    @Test fun testReverse() {
        assertEquals(Direction.DOWN, Direction.UP.reverse())
        assertEquals(Direction.LEFT, Direction.RIGHT.reverse())
        assertEquals(Direction.UP, Direction.DOWN.reverse())
        assertEquals(Direction.RIGHT, Direction.LEFT.reverse())
    }

    @Test fun testTurnLeft() {
        assertEquals(Direction.LEFT, Direction.UP.turnLeft())
        assertEquals(Direction.UP, Direction.RIGHT.turnLeft())
        assertEquals(Direction.RIGHT, Direction.DOWN.turnLeft())
        assertEquals(Direction.DOWN, Direction.LEFT.turnLeft())
    }

    @Test fun testTurnRight() {
        assertEquals(Direction.RIGHT, Direction.UP.turnRight())
        assertEquals(Direction.DOWN, Direction.RIGHT.turnRight())
        assertEquals(Direction.LEFT, Direction.DOWN.turnRight())
        assertEquals(Direction.UP, Direction.LEFT.turnRight())
    }

    @Test fun testConstructFromValidChar() {
        assertEquals(Direction.UP, Direction.from('U'))
        assertEquals(Direction.UP, Direction.from('u'))
        assertEquals(Direction.DOWN, Direction.from('D'))
        assertEquals(Direction.DOWN, Direction.from('d'))
        assertEquals(Direction.RIGHT, Direction.from('R'))
        assertEquals(Direction.RIGHT, Direction.from('r'))
        assertEquals(Direction.LEFT, Direction.from('L'))
        assertEquals(Direction.LEFT, Direction.from('l'))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testConstructFromInvalidChar() {
        Direction.from('F')
    }
}
