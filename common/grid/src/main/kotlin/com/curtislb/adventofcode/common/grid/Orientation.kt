package com.curtislb.adventofcode.common.grid

/**
 * A [position] and cardinal [direction] on a 2D grid.
 */
data class Orientation(val position: Point, val direction: Direction) {
    /**
     * Returns the orientation given by moving [distance] grid units in [direction] from this one.
     *
     * If [distance] is negative, this is equivalent to `-distance` units opposite [direction].
     */
    fun move(direction: Direction = this.direction, distance: Int = 1): Orientation {
        return Orientation(position.move(direction, distance), this.direction)
    }

    /**
     * Returns the orientation given by turning 180 degrees from this one.
     */
    fun turnAround(): Orientation = turnToward(direction.reverse())

    /**
     * Returns the orientation given by turning a given [angle] counterclockwise from this one.
     */
    fun turnLeft(angle: RotationAngle = RotationAngle.DEGREES_90): Orientation =
        turnToward(direction.turnLeft(angle))

    /**
     * Returns the orientation given by turning a given [angle] clockwise from this one.
     */
    fun turnRight(angle: RotationAngle = RotationAngle.DEGREES_90): Orientation =
        turnToward(direction.turnRight(angle))

    /**
     * Returns the orientation given by turning from this one to face [direction].
     */
    fun turnToward(direction: Direction): Orientation = Orientation(position, direction)
}
