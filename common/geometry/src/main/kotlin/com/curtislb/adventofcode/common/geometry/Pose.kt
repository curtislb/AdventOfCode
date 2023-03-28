package com.curtislb.adventofcode.common.geometry

/**
 * A valid position and direction for an actor in a two-dimensional grid.
 *
 * @property position The position of the actor in the grid.
 * @property direction The direction the actor is facing.
 *
 * @constructor Creates a new instance of [Pose] with the given [position] and [direction].
 */
data class Pose(val position: Point, val direction: Direction) {
    /**
     * Returns the pose given by moving the actor [distance] grid units in a specified
     * [moveDirection] without changing the [direction] the actor is facing.
     *
     * If [distance] is negative, this function instead returns the pose given by moving
     * the actor -[distance] grid spaces opposite the specified [moveDirection].
     *
     * Note that [distance] is *not* the same as the Euclidean distance, as diagonally adjacent
     * positions are considered to have a [distance] of 1.
     */
    fun move(moveDirection: Direction = direction, distance: Int = 1): Pose {
        return Pose(position.move(moveDirection, distance), direction)
    }

    /**
     * Returns the pose given by turning the actor 180 degrees.
     */
    fun turnAround(): Pose = turnToFace(direction.reverse())

    /**
     * Returns the pose given by turning the actor a specified [angle] counterclockwise.
     */
    fun turnLeft(angle: RotationAngle = RotationAngle.DEGREES_90): Pose =
        turnToFace(direction.turnLeft(angle))

    /**
     * Returns the pose given by turning the actor a specified [angle] clockwise.
     */
    fun turnRight(angle: RotationAngle = RotationAngle.DEGREES_90): Pose =
        turnToFace(direction.turnRight(angle))

    /**
     * Returns the pose given by turning the actor to face a specified [newDirection].
     */
    fun turnToFace(newDirection: Direction): Pose = Pose(position, newDirection)
}
