package com.adventofcode.curtislb.common.grid

import com.adventofcode.curtislb.common.math.pow
import kotlin.math.abs

/**
 * A point on a 2D grid with positive and negative integer coordinates.
 * @param x The x-coordinate of the point.
 * @param y The y-coordinate of the point.
 */
data class Point(val x: Int, val y: Int) {
    override fun toString() = "($x, $y)"

    /**
     * Finds the [Point] reached by moving a given direction and distance from this point.
     * @receiver The [Point] from which to begin moving.
     * @param direction The direction in which to move.
     * @param distance The number of grid units to move.
     * @return A new [Point] corresponding to the grid position [distance] units in [direction] from this point.
     */
    fun move(direction: Direction, distance: Int): Point {
        return when (direction) {
            Direction.UP -> Point(x, y + distance)
            Direction.RIGHT -> Point(x + distance, y)
            Direction.DOWN -> Point(x, y - distance)
            Direction.LEFT -> Point(x - distance, y)
        }
    }

    /**
     * Finds the Manhattan distance between this and another [Point].
     *
     * The Manhattan distance represents the shortest possible path (in grid units) between two points, while moving
     * only up, down, left, or right.
     *
     * @param other The [Point] to which the distance will be determined.
     * @return The Manhattan distance between this [Point] and [other].
     */
    fun manhattanDistanceTo(other: Point) = abs(x - other.x) + abs(y - other.y)

    /**
     * Finds the squared distance between this and another [Point].
     * @param other The [Point] to which the squared distance will be determined.
     * @return The squared value of the Euclidean distance between this [Point] and [other].
     */
    fun squaredDistanceTo(other: Point): Int = (x - other.x).pow(2) + (y - other.y).pow(2)
}
