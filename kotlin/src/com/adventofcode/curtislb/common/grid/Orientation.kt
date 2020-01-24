package com.adventofcode.curtislb.common.grid

/**
 * A position and cardinal direction on a 2D grid.
 * @param position A [Point] representing the grid position corresponding to this [Orientation].
 * @param direction The [Direction] corresponding to this [Orientation].
 */
data class Orientation(val position: Point, val direction: Direction) {
    /**
     * Finds the [Orientation] that results from moving a given distance forward.
     * @receiver The starting [Orientation] from which to move.
     * @param distance The number of grid units forward to move.
     * @return The [Orientation] reached by moving [distance] units in [direction] from [position].
     */
    fun moveForward(distance: Int = 1): Orientation = Orientation(position.move(direction, distance), direction)

    /**
     * Finds the [Orientation] that results from turning around.
     * @receiver The starting [Orientation] from which to turn.
     * @return The [Orientation] given by turning 180 degrees from [direction] at [position].
     */
    fun turnAround(): Orientation = Orientation(position, direction.reverse())

    /**
     * Finds the [Orientation] that results from turning to the left.
     * @receiver The starting [Orientation] from which to turn.
     * @return The [Orientation] given by turning 90 degrees counterclockwise from [direction] at [position].
     */
    fun turnLeft(): Orientation = Orientation(position, direction.turnLeft())

    /**
     * Finds the [Orientation] that results from turning to the right.
     * @receiver The starting [Orientation] from which to turn.
     * @return The [Orientation] given by turning 90 degrees clockwise from [direction] at [position].
     */
    fun turnRight(): Orientation = Orientation(position, direction.turnRight())
}
