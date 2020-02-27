package com.curtislb.adventofcode.year2019.day15.repair

import com.curtislb.adventofcode.common.grid.Direction
import com.curtislb.adventofcode.common.grid.Orientation
import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.constructGrid
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
     * The current orientation of the repair droid in the grid.
     */
    var orientation: Orientation = Orientation(Point.ORIGIN, Direction.UP)
        private set

    /**
     * The position of the oxygen system, if it has been located by the repair droid.
     */
    var goalPosition: Point? = null
        private set

    /**
     * The [Intcode] program that controls the repair droid.
     */
    private val intcode: Intcode

    /**
     * A map from each position that the repair droid has identified to the space at that position.
     */
    private val knownSpaces: MutableMap<Point, Space> = mutableMapOf(Pair(Point.ORIGIN, Space.OPEN))

    /**
     * The most recent space identified by the repair droid.
     */
    private var lastSpace: Space = Space.OPEN

    init {
        intcode = Intcode(file) { output ->
            lastSpace = Space.from(output)
            knownSpaces[orientation.moveForward().position] = lastSpace
        }
    }

    /**
     * Whether the repair droid's current position is the location of the oxygen system.
     */
    val isAtGoal: Boolean
        get() = orientation.position == goalPosition

    /**
     * Returns a matrix representing the portion of the grid that the repair droid has explored.
     */
    fun constructKnownGrid(): List<List<Space>> = constructGrid(knownSpaces.keys) { spaceAt(it) }

    /**
     * Returns a matrix representing the portion of the grid that the repair droid has explored, including the droid's
     * current position, represented by [Space.DROID].
     */
    fun constructKnownGridWithDroid(): List<List<Space>> {
        return constructGrid(knownSpaces.keys) { point ->
            if (point == orientation.position) Space.DROID else spaceAt(point)
        }
    }

    /**
     * Returns the space at [position] on the grid if it has been identified by the repair droid, or [Space.UNKNOWN]
     * otherwise.
     */
    fun spaceAt(position: Point): Space = knownSpaces.getOrDefault(position, Space.UNKNOWN)

    /**
     * Returns all identified spaces adjacent to [position] that the repair droid can occupy.
     */
    fun adjacentOccupiableSpaces(position: Point = orientation.position): List<Point> {
        return position.neighbors.filter { neighbor -> spaceAt(neighbor).isOccupiable == true }
    }

    /**
     * Attempts to move the repair droid one unit in [direction] from its current position on the grid.
     *
     * The repair droid first turns to face [direction] and then identifies the space one unit forward from its current
     * position. If that space is one that the droid can occupy, it then moves forward into that position.
     */
    fun move(direction: Direction = orientation.direction) {
        // Update the direction the droid is facing.
        orientation = orientation.turnToward(direction)

        // Send move instruction as input to the program.
        val input = when (direction) {
            Direction.UP -> BigInteger.ONE
            Direction.DOWN -> BigInteger.TWO
            Direction.LEFT -> BigInteger("3")
            Direction.RIGHT -> BigInteger("4")
        }
        intcode.sendInput(input)
        intcode.run()

        if (lastSpace.isOccupiable == true) {
            // Update the droid's position to the new space.
            orientation = orientation.moveForward()

            // Check if we've found the location of the oxygen system.
            if (lastSpace == Space.OXYGEN) {
                goalPosition = orientation.position
            }
        }
    }

    /**
     * Causes the repair droid to exhaustively search all grid spaces reachable from its current position.
     */
    fun explore() = exploreInternal(visited = mutableSetOf())

    /**
     * Recursive helper method for [explore].
     */
    private fun exploreInternal(visited: MutableSet<Point>) {
        visited.add(orientation.position)
        for (direction in Direction.values()) {
            val newPosition = orientation.position.move(direction)
            if (newPosition !in visited && spaceAt(newPosition).isOccupiable != false) {
                move(direction)
                if (orientation.position == newPosition) {
                    exploreInternal(visited)
                    move(direction.reverse())
                }
            }
        }
    }
}
