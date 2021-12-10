package com.curtislb.adventofcode.year2020.day12.navigation

import com.curtislb.adventofcode.common.grid.Direction
import com.curtislb.adventofcode.common.grid.Orientation
import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.RotationAngle

/**
 * A ship in a 2D grid that can be directly moved and turned via navigation commands.
 *
 * @param orientation The initial orientation of the ship in the grid.
 */
class SimpleNavigationShip(
    orientation: Orientation = Orientation(Point.ORIGIN, Direction.UP)
) : Ship {
    /**
     * The current orientation of this ship in the grid.
     */
    var orientation: Orientation = orientation
        private set

    /**
     * Moves this ship [distance] grid units in the given [direction] without changing the direction
     * it's facing.
     */
    override fun move(direction: Direction, distance: Int) {
        orientation = orientation.move(direction, distance)
    }

    /**
     * Turns this ship counterclockwise by the given [angle] without changing its position.
     */
    override fun turnLeft(angle: RotationAngle) {
        orientation = orientation.turnLeft(angle)
    }

    /**
     * Moves this ship [count] grid units in the direction it's currently facing.
     */
    override fun goForward(count: Int) {
        orientation = orientation.move(distance = count)
    }
}
