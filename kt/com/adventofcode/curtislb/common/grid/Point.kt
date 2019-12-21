package com.adventofcode.curtislb.common.grid

import kotlin.math.abs

/**
 * A point on a 2D grid with positive and negative integer coordinates.
 * @param x The x-coordinate of the point.
 * @param y The y-coordinate of the point.
 */
data class Point(val x: Int, val y: Int) {
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
     * Finds the Manhattan distance between this [Point] and another one.
     *
     * The Manhattan distance represents the shortest possible path (in grid units) between two points, while moving
     * only up, down, left, or right.
     *
     * @receiver The [Point] to be compared with [other].
     * @param other The [Point] with which this one will be compared.
     * @return The Manhattan distance between this [Point] and [other].
     */
    fun manhattanDistanceTo(other: Point) = abs(x - other.x) + abs(y - other.y)
}
