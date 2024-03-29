package com.curtislb.adventofcode.year2019.day15.repair

import com.curtislb.adventofcode.common.geometry.Direction
import com.curtislb.adventofcode.common.geometry.Pose
import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.graph.UnweightedGraph
import com.curtislb.adventofcode.common.intcode.Intcode
import java.io.File
import java.math.BigInteger

/**
 * A repair droid that navigates and paints panels in an infinite 2D grid.
 *
 * @param file A file containing the [Intcode] program that controls the repair droid.
 */
class Droid(file: File) {
    /**
     * The current position and direction of the repair droid in the grid.
     */
    var pose: Pose = Pose(Point.ORIGIN, Direction.UP)
        private set

    /**
     * The position of the oxygen system, if it has been located by the repair droid.
     */
    var goalPosition: Point? = null
        private set

    /**
     * A graph with edges from each grid position to adjacent occupiable spaces seen by the droid.
     */
    val exploredGraph = object : UnweightedGraph<Point>() {
        override fun getNeighbors(node: Point): Iterable<Point> = adjacentOccupiableSpaces(node)
    }

    /**
     * The most recent space identified by the repair droid.
     */
    private var lastSpace: Space = Space.OPEN

    /**
     * A map from each position that the repair droid has identified to the space at that position.
     */
    private val knownSpaces: MutableMap<Point, Space> = mutableMapOf(Point.ORIGIN to Space.OPEN)

    /**
     * The [Intcode] program that controls the repair droid.
     */
    private val intcode: Intcode = Intcode(file) { output ->
        lastSpace = Space.from(output)
        knownSpaces[pose.move().position] = lastSpace
    }

    /**
     * Returns the space at [position] on the grid if it has been identified by the repair droid, or
     * [Space.UNKNOWN] otherwise.
     */
    fun spaceAt(position: Point): Space = knownSpaces.getOrDefault(position, Space.UNKNOWN)

    /**
     * Returns all identified spaces adjacent to [position] that the repair droid can occupy.
     */
    fun adjacentOccupiableSpaces(position: Point = pose.position): List<Point> {
        return position.cardinalNeighbors()
            .filter { neighbor -> spaceAt(neighbor).isOccupiable == true }
    }

    /**
     * Attempts to move the repair droid one unit in [direction] from its current grid position.
     *
     * The repair droid first turns to face [direction] and then identifies the space one unit
     * forward from its current position. If that space is one that the droid can occupy, it then
     * moves forward into that position.
     */
    fun move(direction: Direction = pose.direction) {
        // Update the direction the droid is facing.
        pose = pose.turnToFace(direction)

        // Send move instruction as input to the program.
        val input = when (direction) {
            Direction.UP -> BigInteger.ONE
            Direction.DOWN -> BigInteger.TWO
            Direction.LEFT -> BigInteger("3")
            Direction.RIGHT -> BigInteger("4")
            else -> throw IllegalArgumentException("No move instruction for direction: $direction")
        }
        intcode.sendInput(input)
        intcode.run()

        if (lastSpace.isOccupiable == true) {
            // Update the droid's position to the new space.
            pose = pose.move()

            // Check if we've found the location of the oxygen system.
            if (lastSpace == Space.OXYGEN) {
                goalPosition = pose.position
            }
        }
    }

    /**
     * Makes the repair droid search all grid spaces reachable from its current position.
     */
    fun explore() {
        exploreRecursive(visited = mutableSetOf())
    }

    /**
     * Recursive helper function for [explore].
     */
    private fun exploreRecursive(visited: MutableSet<Point>) {
        visited.add(pose.position)
        for (direction in Direction.cardinalValues()) {
            val newPosition = pose.position.move(direction)
            if (newPosition !in visited && spaceAt(newPosition).isOccupiable != false) {
                move(direction)
                if (pose.position == newPosition) {
                    exploreRecursive(visited)
                    move(direction.reverse())
                }
            }
        }
    }
}
