package com.curtislb.adventofcode.year2019.day11.painting

import com.curtislb.adventofcode.common.geometry.Direction
import com.curtislb.adventofcode.common.geometry.SpatialInfo
import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.grid.emptyGrid
import com.curtislb.adventofcode.common.grid.gridOf
import com.curtislb.adventofcode.common.intcode.Intcode
import kotlin.test.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [Robot].
 */
class RobotTest {
    private lateinit var robot: Robot

    @BeforeEach
    fun setUp() {
        robot = Robot()
    }

    @Test
    fun testMoveAndTurn() {
        assertEquals(SpatialInfo(Point(0, 0), Direction.UP), robot.spatialInfo)

        robot.moveForward()
        assertEquals(SpatialInfo(Point(0, 1), Direction.UP), robot.spatialInfo)

        robot.turnRight()
        assertEquals(SpatialInfo(Point(0, 1), Direction.RIGHT), robot.spatialInfo)

        robot.turnRight()
        assertEquals(SpatialInfo(Point(0, 1), Direction.DOWN), robot.spatialInfo)

        robot.turnLeft()
        assertEquals(SpatialInfo(Point(0, 1), Direction.RIGHT), robot.spatialInfo)

        robot.moveForward()
        assertEquals(SpatialInfo(Point(1, 1), Direction.RIGHT), robot.spatialInfo)

        robot.moveForward()
        assertEquals(SpatialInfo(Point(2, 1), Direction.RIGHT), robot.spatialInfo)

        robot.turnLeft()
        assertEquals(SpatialInfo(Point(2, 1), Direction.UP), robot.spatialInfo)

        robot.turnLeft()
        assertEquals(SpatialInfo(Point(2, 1), Direction.LEFT), robot.spatialInfo)

        robot.turnLeft()
        assertEquals(SpatialInfo(Point(2, 1), Direction.DOWN), robot.spatialInfo)

        robot.moveForward()
        assertEquals(SpatialInfo(Point(2, 0), Direction.DOWN), robot.spatialInfo)

        robot.moveForward()
        assertEquals(SpatialInfo(Point(2, -1), Direction.DOWN), robot.spatialInfo)

        robot.turnRight()
        assertEquals(SpatialInfo(Point(2, -1), Direction.LEFT), robot.spatialInfo)

        robot.moveForward()
        assertEquals(SpatialInfo(Point(1, -1), Direction.LEFT), robot.spatialInfo)

        robot.moveForward()
        assertEquals(SpatialInfo(Point(0, -1), Direction.LEFT), robot.spatialInfo)

        robot.moveForward()
        assertEquals(SpatialInfo(Point(-1, -1), Direction.LEFT), robot.spatialInfo)
    }

    @Test
    fun testPaintAndConstructGrid() {
        assertEquals(0, robot.paintedArea)
        assertEquals(emptyGrid(), robot.getPaintedGrid())

        robot.paint(Color.BLACK)
        assertEquals(1, robot.paintedArea)
        assertEquals(gridOf(listOf(Color.BLACK)), robot.getPaintedGrid())

        robot.paint(Color.WHITE)
        assertEquals(1, robot.paintedArea)
        assertEquals(gridOf(listOf(Color.WHITE)), robot.getPaintedGrid())

        robot.moveForward()
        robot.paint(Color.WHITE)
        assertEquals(2, robot.paintedArea)
        assertEquals(
            gridOf(
                listOf(Color.WHITE),
                listOf(Color.WHITE)
            ),
            robot.getPaintedGrid()
        )

        robot.paint(Color.BLACK)
        assertEquals(2, robot.paintedArea)
        assertEquals(
            gridOf(
                listOf(Color.BLACK),
                listOf(Color.WHITE)
            ),
            robot.getPaintedGrid()
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
            robot.getPaintedGrid()
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
            robot.getPaintedGrid()
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
            robot.getPaintedGrid()
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
            robot.getPaintedGrid()
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
            robot.getPaintedGrid()
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
            robot.getPaintedGrid()
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
            robot.getPaintedGrid()
        )
    }

    @Test
    fun testExecuteProgramWithInvalidMoveOutput() {
        val intcode = Intcode("104,-1,99")
        assertThrows<IllegalArgumentException> { robot.executeProgram(intcode) }
    }

    @Test
    fun testExecuteProgramWithInvalidPaintOutput() {
        val intcode = Intcode("104,1,104,-1,99")
        assertThrows<IllegalArgumentException> { robot.executeProgram(intcode) }
    }

    @Test
    fun testExecuteProgram() {
        val intcode = Intcode(
            "3,100,4,100,108,0,100,100,4,100,101,1,101,101,1007,101,6,102,1005,102,2,99"
        )
        robot.executeProgram(intcode)
        assertEquals(6, robot.paintedArea)
        assertEquals(
            gridOf(
                listOf(Color.BLACK, Color.BLACK, Color.BLACK, Color.WHITE),
                listOf(Color.BLACK, Color.BLACK, Color.WHITE, Color.BLACK),
                listOf(Color.BLACK, Color.WHITE, Color.BLACK, Color.BLACK)
            ),
            robot.getPaintedGrid()
        )
    }
}
