package com.curtislb.adventofcode.year2019.day11.painting

import com.curtislb.adventofcode.common.grid.Direction
import com.curtislb.adventofcode.common.grid.Orientation
import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.emptyGrid
import com.curtislb.adventofcode.common.grid.gridOf
import com.curtislb.adventofcode.common.intcode.Intcode
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * Tests [Robot].
 */
class RobotTest {
    private lateinit var robot: Robot

    @Before
    fun setUp () {
        robot = Robot()
    }

    @Test
    fun testMoveAndTurn() {
        assertEquals(Orientation(Point(0, 0), Direction.UP), robot.orientation)

        robot.moveForward()
        assertEquals(Orientation(Point(0, 1), Direction.UP), robot.orientation)

        robot.turnRight()
        assertEquals(Orientation(Point(0, 1), Direction.RIGHT), robot.orientation)

        robot.turnRight()
        assertEquals(Orientation(Point(0, 1), Direction.DOWN), robot.orientation)

        robot.turnLeft()
        assertEquals(Orientation(Point(0, 1), Direction.RIGHT), robot.orientation)

        robot.moveForward()
        assertEquals(Orientation(Point(1, 1), Direction.RIGHT), robot.orientation)

        robot.moveForward()
        assertEquals(Orientation(Point(2, 1), Direction.RIGHT), robot.orientation)

        robot.turnLeft()
        assertEquals(Orientation(Point(2, 1), Direction.UP), robot.orientation)

        robot.turnLeft()
        assertEquals(Orientation(Point(2, 1), Direction.LEFT), robot.orientation)

        robot.turnLeft()
        assertEquals(Orientation(Point(2, 1), Direction.DOWN), robot.orientation)

        robot.moveForward()
        assertEquals(Orientation(Point(2, 0), Direction.DOWN), robot.orientation)

        robot.moveForward()
        assertEquals(Orientation(Point(2, -1), Direction.DOWN), robot.orientation)

        robot.turnRight()
        assertEquals(Orientation(Point(2, -1), Direction.LEFT), robot.orientation)

        robot.moveForward()
        assertEquals(Orientation(Point(1, -1), Direction.LEFT), robot.orientation)

        robot.moveForward()
        assertEquals(Orientation(Point(0, -1), Direction.LEFT), robot.orientation)

        robot.moveForward()
        assertEquals(Orientation(Point(-1, -1), Direction.LEFT), robot.orientation)
    }

    @Test
    fun testPaintAndConstructGrid() {
        assertEquals(0, robot.paintedArea)
        assertEquals(emptyGrid(), robot.constructPaintedGrid())

        robot.paint(Color.BLACK)
        assertEquals(1, robot.paintedArea)
        assertEquals(gridOf(listOf(Color.BLACK)), robot.constructPaintedGrid())

        robot.paint(Color.WHITE)
        assertEquals(1, robot.paintedArea)
        assertEquals(gridOf(listOf(Color.WHITE)), robot.constructPaintedGrid())

        robot.moveForward()
        robot.paint(Color.WHITE)
        assertEquals(2, robot.paintedArea)
        assertEquals(
            gridOf(
                listOf(Color.WHITE),
                listOf(Color.WHITE)
            ),
            robot.constructPaintedGrid()
        )

        robot.paint(Color.BLACK)
        assertEquals(2, robot.paintedArea)
        assertEquals(
            gridOf(
                listOf(Color.BLACK),
                listOf(Color.WHITE)
            ),
            robot.constructPaintedGrid()
        )

        robot.moveForward()
        robot.moveForward()
        robot.paint(Color.WHITE)
        assertEquals(3, robot.paintedArea)
        assertEquals(
            gridOf(
                listOf(Color.WHITE),
                listOf(Color.BLACK),
                listOf(Color.BLACK),
                listOf(Color.WHITE)
            ),
            robot.constructPaintedGrid()
        )

        robot.turnLeft()
        robot.moveForward()
        robot.paint(Color.WHITE)
        assertEquals(4, robot.paintedArea)
        assertEquals(
            gridOf(
                listOf(Color.WHITE, Color.WHITE),
                listOf(Color.BLACK, Color.BLACK),
                listOf(Color.BLACK, Color.BLACK),
                listOf(Color.BLACK, Color.WHITE)
            ),
            robot.constructPaintedGrid()
        )

        robot.turnLeft()
        robot.moveForward()
        robot.moveForward()
        robot.paint(Color.BLACK)
        assertEquals(5, robot.paintedArea)
        assertEquals(
            gridOf(
                listOf(Color.WHITE, Color.WHITE),
                listOf(Color.BLACK, Color.BLACK),
                listOf(Color.BLACK, Color.BLACK),
                listOf(Color.BLACK, Color.WHITE)
            ),
            robot.constructPaintedGrid()
        )

        robot.turnLeft()
        robot.moveForward()
        robot.moveForward()
        robot.paint(Color.BLACK)
        assertEquals(6, robot.paintedArea)
        assertEquals(
            gridOf(
                listOf(Color.WHITE, Color.WHITE, Color.BLACK),
                listOf(Color.BLACK, Color.BLACK, Color.BLACK),
                listOf(Color.BLACK, Color.BLACK, Color.BLACK),
                listOf(Color.BLACK, Color.WHITE, Color.BLACK)
            ),
            robot.constructPaintedGrid()
        )

        robot.paint(Color.WHITE)
        assertEquals(6, robot.paintedArea)
        assertEquals(
            gridOf(
                listOf(Color.WHITE, Color.WHITE, Color.BLACK),
                listOf(Color.BLACK, Color.BLACK, Color.BLACK),
                listOf(Color.BLACK, Color.BLACK, Color.WHITE),
                listOf(Color.BLACK, Color.WHITE, Color.BLACK)
            ),
            robot.constructPaintedGrid()
        )

        robot.turnRight()
        robot.moveForward()
        robot.moveForward()
        robot.paint(Color.WHITE)
        assertEquals(7, robot.paintedArea)
        assertEquals(
            gridOf(
                listOf(Color.WHITE, Color.WHITE, Color.BLACK),
                listOf(Color.BLACK, Color.BLACK, Color.BLACK),
                listOf(Color.BLACK, Color.BLACK, Color.WHITE),
                listOf(Color.BLACK, Color.WHITE, Color.BLACK),
                listOf(Color.BLACK, Color.BLACK, Color.WHITE)
            ),
            robot.constructPaintedGrid()
        )

        robot.paint(Color.BLACK)
        assertEquals(7, robot.paintedArea)
        assertEquals(
            gridOf(
                listOf(Color.WHITE, Color.WHITE, Color.BLACK),
                listOf(Color.BLACK, Color.BLACK, Color.BLACK),
                listOf(Color.BLACK, Color.BLACK, Color.WHITE),
                listOf(Color.BLACK, Color.WHITE, Color.BLACK),
                listOf(Color.BLACK, Color.BLACK, Color.BLACK)
            ),
            robot.constructPaintedGrid()
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun testExecuteProgramWithInvalidMoveOutput() {
        val intcode = Intcode("104,-1,99")
        robot.executeProgram(intcode)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testExecuteProgramWithInvalidPaintOutput() {
        val intcode = Intcode("104,1,104,-1,99")
        robot.executeProgram(intcode)
    }

    @Test
    fun testExecuteProgram() {
        val intcode = Intcode("3,100,4,100,108,0,100,100,4,100,101,1,101,101,1007,101,6,102,1005,102,2,99")
        robot.executeProgram(intcode)
        assertEquals(6, robot.paintedArea)
        assertEquals(
            gridOf(
                listOf(Color.BLACK, Color.BLACK, Color.BLACK, Color.WHITE),
                listOf(Color.BLACK, Color.BLACK, Color.WHITE, Color.BLACK),
                listOf(Color.BLACK, Color.WHITE, Color.BLACK, Color.BLACK)
            ),
            robot.constructPaintedGrid()
        )
    }
}
