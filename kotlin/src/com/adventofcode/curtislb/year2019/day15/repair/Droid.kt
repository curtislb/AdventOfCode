package com.adventofcode.curtislb.year2019.day15.repair

import com.adventofcode.curtislb.common.grid.Direction
import com.adventofcode.curtislb.common.grid.Point
import com.adventofcode.curtislb.common.grid.constructGrid
import com.adventofcode.curtislb.common.grid.reverse
import com.adventofcode.curtislb.common.intcode.Intcode
import java.io.File
import java.math.BigInteger

/**
 * A repair droid that navigates and paints panels in an infinite 2D grid.
 */
class Droid(file: File) {
    /**
     * The [Intcode] computer used to operate the [Droid].
     */
    private val intcode: Intcode = Intcode(file)

    /**
     * The current [Point] position of the [Droid] in the grid.
     */
    var currentPosition: Point = Point.ORIGIN
        private set

    /**
     * The position of the oxygen system, if it has been located by the [Droid].
     */
    var goalPosition: Point? = null
        private set

    /**
     * The current [Direction] in which the [Droid] is facing, based on its last move.
     */
    private var currentDirection: Direction = Direction.UP

    /**
     * A [MutableMap] from each [Point] position that the [Droid] has identified to its corresponding [Space] type.
     */
    private val knownSpaces: MutableMap<Point, Space> = mutableMapOf(Pair(Point.ORIGIN, Space.EMPTY))

    /**
     * The most recent [Space] type identified by the [Droid].
     */
    private var lastSpace: Space = Space.EMPTY

    init {
        intcode.onOutput = { output ->
            lastSpace = output.toSpace()
            knownSpaces[currentPosition.move(currentDirection)] = lastSpace
        }
    }

    /**
     * Whether the [Droid]'s current position is the location of the oxygen system.
     */
    val isAtGoal: Boolean
        get() = currentPosition == goalPosition

    /**
     * A [List] of lists representing the portion of the grid that the [Droid] has explored.
     */
    val knownGrid: List<List<Space>>
        get() = constructGrid(knownSpaces.keys) { knownSpaces.getOrDefault(it, Space.UNKNOWN) }

    /**
     * A [List] of lists representing the portion of the grid that the [Droid] has explored and the current position of
     * the [Droid].
     */
    val knownGridWithDroid: List<List<Space>>
        get() = constructGrid(knownSpaces.keys) { point ->
            if (point == currentPosition) Space.DROID else knownSpaces.getOrDefault(point, Space.UNKNOWN)
        }

    /**
     * Gives the currently identified [Space] type of a given grid position.
     * @param position The [Point] position of a space on the grid.
     * @return The [Space] type of [position] if it has been explored by the [Droid], or [Space.UNKNOWN] otherwise.
     */
    fun spaceAt(position: Point): Space = knownSpaces.getOrDefault(position, Space.UNKNOWN)

    /**
     * Gets all known open spaces that are adjacent to a given grid space.
     * @param position The [Point] position of a space on the grid.
     * @return An [Iterable] of all [Point] positions adjacent to [position] (ignoring diagonals) that have been
     *  identified by the [Droid] as either an [Space.EMPTY] or an [Space.OXYGEN].
     */
    fun adjacentOpenSpaces(position: Point = currentPosition): List<Point> {
        return Direction.values().map { position.move(it) }.filter { adjacentPosition ->
            val space = knownSpaces[adjacentPosition]
            space == Space.EMPTY || space == Space.OXYGEN
        }
    }

    /**
     * Attempts to move the [Droid] one unit in a given direction on the grid.
     *
     * The [Droid] first updates its [currentDirection] and then identifies the [Space] one unit in that [Direction]
     * from its [currentPosition]. The type of [Space] then determines what happens to the [Droid]'s position:
     * - [Space.EMPTY] or [Space.OXYGEN]: The [Droid]'s position changes to the new space.
     * - [Space.WALL]: The [Droid] can't move into this space, so its position doesn't change.
     *
     * @param direction The [Direction] in which the [Droid] should move.
     */
    fun move(direction: Direction = currentDirection) {
        currentDirection = direction

        val input = when (direction) {
            Direction.UP -> BigInteger.ONE
            Direction.DOWN -> BigInteger.TWO
            Direction.LEFT -> BigInteger("3")
            Direction.RIGHT -> BigInteger("4")
        }
        intcode.sendInput(input)
        intcode.run()

        if (lastSpace != Space.WALL) {
            currentPosition = currentPosition.move(direction)
            if (lastSpace == Space.OXYGEN) {
                goalPosition = currentPosition
            }
        }
    }

    /**
     * Causes the [Droid] to exhaustively search all grid spaces reachable from its current position.
     */
    fun explore() = exploreInternal(visited = mutableSetOf())

    /**
     * Private recursive helper function for [explore].
     * @param visited A [MutableSet] of each [Point] position that the [Droid] has explored.
     */
    private fun exploreInternal(visited: MutableSet<Point>) {
        visited.add(currentPosition)
        for (direction in Direction.values()) {
            val newPosition = currentPosition.move(direction)
            if (newPosition !in knownSpaces) {
                move(direction)
                if (currentPosition == newPosition) {
                    exploreInternal(visited)
                    move(direction.reverse())
                }
            }
        }
    }
}
