package com.curtislb.adventofcode.year2023.day17.crucible

import com.curtislb.adventofcode.common.geometry.Direction
import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.geometry.Pose
import com.curtislb.adventofcode.common.graph.WeightedGraph
import com.curtislb.adventofcode.common.grid.Grid
import com.curtislb.adventofcode.common.grid.mutableGridOf
import java.io.File

/**
 * A specification for how a heated crucible can move through a grid of city blocks, while losing a
 * specified amount of heat for each block that it enters.
 *
 * @property heatLossGrid A grid of city blocks, where the value at each position represents the
 *  amount of heat lost by the crucible each time it moves into the corresponding block.
 * @property maxStraightDistance The maximum number of blocks through which the crucible can travel
 *  in a straight line before it must turn left or right.
 * @property minStoppingDistance The minimum number of blocks through which the crucible must travel
 *  in a straight line (once it has begun moving) before it can stop, turn left, or turn right.
 *
 * @constructor Creates a new instance of [CruciblePath] with the given [heatLossGrid],
 *  [maxStraightDistance], and [minStoppingDistance].
 */
class CruciblePath(
    private val heatLossGrid: Grid<Long>,
    private val maxStraightDistance: Int,
    private val minStoppingDistance: Int
) {
    /**
     * A graph with edges from each possible [CrucibleState] to other states that are reachable by
     * moving the crucible into an adjacent city block, weighted by the heat loss incurred for
     * moving into that block.
     */
    private val heatLossGraph = object : WeightedGraph<CrucibleState>() {
        override fun getEdges(node: CrucibleState): Iterable<Edge<CrucibleState>> {
            val edges = mutableListOf<Edge<CrucibleState>>()

            // Try moving the crucible in a straight line
            if (canGoStraight(node.straightDistance)) {
                val straight = node.pose.move()
                edges.addEdgeIfValid(straight, node.straightDistance + 1)
            }

            // Try moving the crucible left and right
            if (canTurnOrStop(node.straightDistance)) {
                val left = node.pose.turnAndMove(node.pose.direction.turnLeft())
                val right = node.pose.turnAndMove(node.pose.direction.turnRight())
                edges.addEdgeIfValid(left, straightDistance = 1)
                edges.addEdgeIfValid(right, straightDistance = 1)
            }

            return edges
        }

        /**
         * Adds a [WeightedGraph.Edge] corresponding to a [CrucibleState] with the given [pose] and
         * [straightDistance], if the [pose] represents a valid position in the [heatLossGrid].
         */
        private fun MutableList<Edge<CrucibleState>>.addEdgeIfValid(
            pose: Pose,
            straightDistance: Int
        ) {
            val heatLoss = heatLossGrid.getOrNull(pose.position)
            if (heatLoss != null) {
                val state = CrucibleState(pose, straightDistance)
                add(Edge(state, heatLoss))
            }
        }
    }

    /**
     * Returns the minimum amount of total heat lost by the crucible while following any valid path
     * from the top-left position of the [heatLossGrid] to the bottom-right position.
     */
    fun findMinimumHeatLoss(): Long? {
        val goalPosition = Point.fromMatrixCoordinates(
            rowIndex = heatLossGrid.lastRowIndex,
            colIndex = heatLossGrid.lastColumnIndex
        )

        // Use A* search with a Manhattan distance heuristic
        return heatLossGraph.aStarDistance(
            source = CrucibleState(Pose(Point.ORIGIN, Direction.RIGHT), straightDistance = 0),
            heuristic = { state -> (state.pose.position manhattanDistance goalPosition).toLong() },
            isGoal = { state ->
                state.pose.position == goalPosition && canTurnOrStop(state.straightDistance)
            }
        )
    }

    /**
     * Returns `true` if the crucible can continue moving in a straight line after moving straight
     * for a number of city blocks equal to [straightDistance].
     */
    private fun canGoStraight(straightDistance: Int): Boolean =
        straightDistance < maxStraightDistance

    /**
     * Returns `true` if the crucible can turn left/right or stop at a goal position after moving
     * straight for a number of city blocks equal to [straightDistance].
     */
    private fun canTurnOrStop(straightDistance: Int): Boolean =
        straightDistance == 0 || straightDistance >= minStoppingDistance

    companion object {
        /**
         * Returns a [CruciblePath] with a [heatLossGrid] read from the given [file] and the
         * specified [maxStraightDistance] and [minStoppingDistance] parameters.
         *
         * The [file] must contain lines of equal length, where each character is a decimal digit
         * representing the heat lost by the crucible when entering the city block at that position.
         *
         * @throws IllegalArgumentException If [file] is formatted incorrectly.
         */
        fun fromFile(
            file: File,
            maxStraightDistance: Int = 1,
            minStoppingDistance: Int = 1
        ): CruciblePath {
            val heatLossGrid = mutableGridOf<Long>()
            file.forEachLine { line ->
                val row = line.map { it.digitToInt().toLong() }
                heatLossGrid.addShallowRow(row)
            }
            return CruciblePath(heatLossGrid, maxStraightDistance, minStoppingDistance)
        }
    }

    /**
     * A possible state of the crucible while traveling through the city block grid.
     *
     * @property pose The current position and direction of the crucible.
     * @property straightDistance The most recent number of city blocks through which the crucible
     *  has moved in a straight line.
     */
    private data class CrucibleState(val pose: Pose, val straightDistance: Int)
}
