package com.curtislb.adventofcode.common.geometry

/**
 * A valid [position] and facing [direction] on a two-dimensional grid.
 */
data class SpatialInfo(val position: Point, val direction: Direction) {
    /**
     * Returns the spatial info given by moving [distance] grid units in the given [direction].
     *
     * If [distance] is negative, this is equivalent to moving `-distance` units opposite the given
     * [direction].
     */
    fun move(direction: Direction = this.direction, distance: Int = 1): SpatialInfo {
        return SpatialInfo(position.move(direction, distance), this.direction)
    }

    /**
     * Returns the spatial info given by turning 180 degrees.
     */
    fun turnAround(): SpatialInfo = turnToward(direction.reverse())

    /**
     * Returns the spatial info given by turning a given [angle] counterclockwise.
     */
    fun turnLeft(angle: RotationAngle = RotationAngle.DEGREES_90): SpatialInfo =
        turnToward(direction.turnLeft(angle))

    /**
     * Returns the spatial info given by turning a given [angle] clockwise.
     */
    fun turnRight(angle: RotationAngle = RotationAngle.DEGREES_90): SpatialInfo =
        turnToward(direction.turnRight(angle))

    /**
     * Returns the spatial info given by turning to face [direction].
     */
    fun turnToward(direction: Direction): SpatialInfo = SpatialInfo(position, direction)
}
