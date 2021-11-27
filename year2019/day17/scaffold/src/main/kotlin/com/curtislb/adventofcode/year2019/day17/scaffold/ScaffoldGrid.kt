package com.curtislb.adventofcode.year2019.day17.scaffold

import com.curtislb.adventofcode.common.grid.Direction
import com.curtislb.adventofcode.common.grid.Grid
import com.curtislb.adventofcode.common.grid.Orientation
import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.forEachPointValue
import com.curtislb.adventofcode.common.grid.toGrid
import com.curtislb.adventofcode.year2019.day17.scaffold.instruction.Instruction
import com.curtislb.adventofcode.year2019.day17.scaffold.instruction.Move
import com.curtislb.adventofcode.year2019.day17.scaffold.instruction.TurnLeft
import com.curtislb.adventofcode.year2019.day17.scaffold.instruction.TurnRight

/**
 * A grid representing the ship's scaffold and the vacuum robot's position on it.
 */
class ScaffoldGrid private constructor(private val grid: Grid<Space>) {
    class Builder {
        private val rowList: MutableList<MutableList<Space>> = mutableListOf(mutableListOf())

        fun addRow() {
            rowList.add(mutableListOf())
        }

        fun addSpace(space: Space) {
            rowList.last().add(space)
        }

        fun removeRow() {
            rowList.removeLast()
        }

        fun build(): ScaffoldGrid = ScaffoldGrid(rowList.toGrid())
    }

    /**
     * Returns all grid positions where the scaffold crosses over itself.
     */
    fun findIntersections(): List<Point> {
        return mutableListOf<Point>().apply {
            grid.forEachPointValue { point, space ->
                if (space == Space.SCAFFOLD) {
                    val isIntersection = point.cardinalNeighbors().all { neighbor ->
                        grid.getOrNull(neighbor) == Space.SCAFFOLD
                    }
                    if (isIntersection) {
                        add(point)
                    }
                }
            }
        }
    }

    /**
     * Returns a list of instructions that lets the vacuum robot safely visit all scaffold spaces.
     */
    fun planRoute(): List<Instruction> {
        val instructions = mutableListOf<Instruction>()

        // Find the vacuum robot's starting orientation in the grid.
        var robotStart: Orientation? = null
        for (rowIndex in grid.rowIndices) {
            if (robotStart != null) {
                break
            }
            for (colIndex in grid.columnIndices) {
                if (robotStart != null) {
                    break
                }

                when (grid[rowIndex, colIndex]) {
                    Space.ROBOT_UP -> {
                        robotStart = Orientation(
                            Point.fromMatrixCoordinates(rowIndex, colIndex),
                            Direction.UP
                        )
                    }

                    Space.ROBOT_RIGHT -> {
                        robotStart = Orientation(
                            Point.fromMatrixCoordinates(rowIndex, colIndex),
                            Direction.RIGHT
                        )
                    }

                    Space.ROBOT_DOWN -> {
                        robotStart = Orientation(
                            Point.fromMatrixCoordinates(rowIndex, colIndex),
                            Direction.DOWN
                        )
                    }

                    Space.ROBOT_LEFT -> {
                        robotStart = Orientation(
                            Point.fromMatrixCoordinates(rowIndex, colIndex),
                            Direction.LEFT
                        )
                    }

                    else -> Unit
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
            val forwardOrientation = orientation.move()
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
                val rightOrientation = orientation.turnRight().move()
                val leftOrientation = orientation.turnLeft().move()
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
    private fun isSafeSpace(position: Point): Boolean = grid.getOrNull(position)?.isSafe == true

    override fun toString(): String {
        return grid.joinRowsToString(separator = "\n") { row ->
            row.joinToString(separator = "") { space -> space.symbol.toString() }
        }
    }
}
