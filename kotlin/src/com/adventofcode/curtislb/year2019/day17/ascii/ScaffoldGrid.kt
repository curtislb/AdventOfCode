package com.adventofcode.curtislb.year2019.day17.ascii

import com.adventofcode.curtislb.common.collection.removeLast
import com.adventofcode.curtislb.common.grid.Direction
import com.adventofcode.curtislb.common.grid.Orientation
import com.adventofcode.curtislb.common.grid.Point
import com.adventofcode.curtislb.year2019.day17.ascii.instruction.Instruction
import com.adventofcode.curtislb.year2019.day17.ascii.instruction.Move
import com.adventofcode.curtislb.year2019.day17.ascii.instruction.TurnLeft
import com.adventofcode.curtislb.year2019.day17.ascii.instruction.TurnRight

/**
 * A grid representing the ship's scaffold and the vacuum robot's position on it, as reported by the [ASCII].
 */
class ScaffoldGrid {
    /**
     * A 2D matrix representing the type of space at each position in this [ScaffoldGrid].
     */
    private var grid: MutableList<MutableList<Space>> = mutableListOf(mutableListOf())

    /**
     * Adds a new empty row to the bottom of this [ScaffoldGrid].
     */
    fun addRow() { grid.add(mutableListOf()) }

    /**
     * Appends a new space to the bottom row of this [ScaffoldGrid].
     * @param space The [Space] to be appended to the current last row.
     */
    fun addSpace(space: Space) { grid.last().add(space) }

    /**
     * Removes one row from the bottom of this [ScaffoldGrid].
     */
    fun removeRow() { grid.removeLast() }

    /**
     * Produces a given row in this [ScaffoldGrid].
     * @param index The index of the row to return.
     * @return An ordered [List] of [Space] types in the [ScaffoldGrid] row at [index].
     */
    operator fun get(index: Int): List<Space> = grid[index]

    /**
     * Finds all positions on this [ScaffoldGrid] where the scaffold crosses over itself.
     * @return A [List] of all [Point] positions on this [ScaffoldGrid] that contain a [Space.SCAFFOLD] intersection.
     */
    fun findIntersections(): List<Point> {
        val intersections = mutableListOf<Point>()
        grid.forEachIndexed { i, row ->
            row.forEachIndexed { j, space ->
                if (space == Space.SCAFFOLD) {
                    val point = Point.fromMatrixCoordinates(i, j)
                    val isIntersection = point.neighbors.all { neighbor ->
                        val (neighborRow, neighborCol) = neighbor.toMatrixCoordinates()
                        neighborRow in grid.indices && neighborCol in grid[neighborRow].indices
                                && grid[neighborRow][neighborCol] == Space.SCAFFOLD
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
     * Produces a list of instructions that will cause the vacuum robot to safely visit all scaffold spaces.
     * @return A [List] of [Instruction] values that can be given to the vacuum robot in order for it to visit all
     *  [Space.SCAFFOLD] positions on this [ScaffoldGrid] from its starting location at one end of the scaffold.
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
     * Checks if a given position in this [ScaffoldGrid] is safe for the vacuum robot to move onto.
     * @param position A [Point] position in this [ScaffoldGrid].
     * @return `true` if there is a [Space.SCAFFOLD] space at [position], or `false` otherwise.
     */
    private fun isSafeSpace(position: Point): Boolean {
        val (i, j) = position.toMatrixCoordinates()
        return i in grid.indices && j in grid[i].indices && grid[i][j] == Space.SCAFFOLD
    }

    override fun toString(): String {
        return grid.joinToString(separator = "\n") { row ->
            row.joinToString(separator = "") { space -> space.symbol.toString() }
        }
    }
}
