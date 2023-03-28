package com.curtislb.adventofcode.year2020.day12.navigation

import com.curtislb.adventofcode.common.geometry.Direction
import com.curtislb.adventofcode.common.geometry.Pose
import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.geometry.RotationAngle

/**
 * A ship in a 2D grid that can be directly moved and turned via navigation commands.
 *
 * @param pose The initial position and direction of the ship in the grid.
 */
class SimpleNavigationShip(
    pose: Pose = Pose(Point.ORIGIN, Direction.UP)
) : Ship {
    /**
     * The current position and direction of this ship in the grid
     */
    var pose: Pose = pose
        private set

    /**
     * Moves this ship [distance] grid units in the given [direction] without changing the direction
     * it's facing.
     */
    override fun move(direction: Direction, distance: Int) {
        pose = pose.move(direction, distance)
    }

    /**
     * Turns this ship counterclockwise by the given [angle] without changing its position.
     */
    override fun turnLeft(angle: RotationAngle) {
        pose = pose.turnLeft(angle)
    }

    /**
     * Moves this ship [count] grid units in the direction it's currently facing.
     */
    override fun goForward(count: Int) {
        pose = pose.move(distance = count)
    }
}
