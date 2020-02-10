package com.curtislb.adventofcode.common.grid

/**
 * A [position] and cardinal [direction] on a 2D grid.
 */
data class Orientation(val position: Point, val direction: Direction) {
    /**
     * Returns the orientation given by moving [distance] grid units forward from this one.
     */
    fun moveForward(distance: Int = 1): Orientation = Orientation(position.move(direction, distance), direction)

    /**
     * Returns the orientation given by turning 180 degrees from this one.
     */
    fun turnAround(): Orientation = turnToward(direction.reverse())

    /**
     * Returns the orientation given by turning 90 degrees counterclockwise from this one.
     */
    fun turnLeft(): Orientation = turnToward(direction.turnLeft())

    /**
     * Returns the orientation given by turning 90 degrees clockwise from this one.
     */
    fun turnRight(): Orientation = turnToward(direction.turnRight())

    /**
     * Returns the orientation given by turning from this one to face [direction].
     */
    fun turnToward(direction: Direction): Orientation = Orientation(position, direction)
}
