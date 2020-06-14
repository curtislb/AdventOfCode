package com.curtislb.adventofcode.year2019.day17.scaffold

import com.curtislb.adventofcode.common.collection.removeLast
import com.curtislb.adventofcode.common.grid.Direction
import com.curtislb.adventofcode.common.grid.Orientation
import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.getCellOrNull
import com.curtislb.adventofcode.year2019.day17.scaffold.instruction.Instruction
import com.curtislb.adventofcode.year2019.day17.scaffold.instruction.Move
import com.curtislb.adventofcode.year2019.day17.scaffold.instruction.TurnLeft
import com.curtislb.adventofcode.year2019.day17.scaffold.instruction.TurnRight

/**
 * A grid representing the ship's scaffold and the vacuum robot's position on it.
 */
class ScaffoldGrid {
    /**
     * A matrix representing the space at each position in the grid.
     */
    private var grid: MutableList<MutableList<Space>> = mutableListOf(mutableListOf())

    /**
     * Adds a new empty row to the bottom of the grid.
     */
    fun addRow() {
        grid.add(mutableListOf())
    }

    /**
     * Appends a [space] to the bottom row of the grid.
     */
    fun addSpace(space: Space) {
        grid.last().add(space)
    }

    /**
     * Removes one row from the bottom of the grid.
     */
    fun removeRow() {
        grid.removeLast()
    }

    /**
     * Returns the row at [index] in this grid.
     */
    operator fun get(index: Int): List<Space> = grid[index]

    /**
     * Returns all grid positions where the scaffold crosses over itself.
     */
    fun findIntersections(): List<Point> {
        val intersections = mutableListOf<Point>()
        grid.forEachIndexed { i, row ->
            row.forEachIndexed { j, space ->
                if (space == Space.SCAFFOLD) {
                    val point = Point.fromMatrixCoordinates(i, j)
                    val isIntersection = point.neighbors.all { neighbor ->
                        grid.getCellOrNull(neighbor) == Space.SCAFFOLD
                    }
                    if (isIntersection) {
                        intersections.add(point)
                    }
                }
            }
        }
        return intersections
    }

    /**
     * Returns a list of instructions that will cause the vacuum robot to safely visit all scaffold spaces.
     */
    fun planRoute(): List<Instruction> {
        val instructions = mutableListOf<Instruction>()

        // Find the vacuum robot's starting orientation in the grid.
        var robotStart: Orientation? = null
        for (i in grid.indices) {
            if (robotStart != null) {
                break
            }
            for (j in grid[i].indices) {
                if (robotStart != null) {
                    break
                }
                when (grid[i][j]) {
                    Space.ROBOT_UP -> robotStart = Orientation(Point.fromMatrixCoordinates(i, j), Direction.UP)
                    Space.ROBOT_RIGHT -> robotStart = Orientation(Point.fromMatrixCoordinates(i, j), Direction.RIGHT)
                    Space.ROBOT_DOWN -> robotStart = Orientation(Point.fromMatrixCoordinates(i, j), Direction.DOWN)
                    Space.ROBOT_LEFT -> robotStart = Orientation(Point.fromMatrixCoordinates(i, j), Direction.LEFT)
                    else -> {}
                }
            }
        }

        // If no movable robot is found, provide empty instructions.
        if (robotStart == null) {
            return instructions
        }

        // Simulate moving the robot from its current orientation (without actually doing so).
        var orientation: Orientation = robotStart
        var moveCount = 0
        while (true) {
            // Check if the robot can safely move forward.
            val forwardOrientation = orientation.moveForward()
            if (isSafeSpace(forwardOrientation.position)) {
                orientation = forwardOrientation
                moveCount++
            } else {
                // If the robot can no longer move forward, add the move instruction to get here.
                if (moveCount != 0) {
                    instructions.add(Move(moveCount))
                    moveCount = 0
                }

                // Check if the robot can safely move forward after turning.
                val rightOrientation = orientation.turnRight().moveForward()
                val leftOrientation = orientation.turnLeft().moveForward()
                when {
                    isSafeSpace(rightOrientation.position) -> {
                        orientation = rightOrientation
                        instructions.add(TurnRight)
                        moveCount++
                    }
                    isSafeSpace(leftOrientation.position) -> {
                        orientation = leftOrientation
                        instructions.add(TurnLeft)
                        moveCount++
                    }

                    // If the robot has to turn around, assume it has reached the end.
                    else -> return instructions
                }
            }
        }
    }

    /**
     * Returns `true` if the vacuum robot can safely occupy [position], or `false` otherwise.
     */
    private fun isSafeSpace(position: Point): Boolean = grid.getCellOrNull(position)?.isSafe == true

    override fun toString(): String {
        return grid.joinToString(separator = "\n") { row ->
            row.joinToString(separator = "") { space -> space.symbol.toString() }
        }
    }
}
