package com.curtislb.adventofcode.year2020.day12.navigation

import com.curtislb.adventofcode.common.geometry.Direction
import com.curtislb.adventofcode.common.geometry.SpatialInfo
import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.geometry.RotationAngle

/**
 * A ship in a 2D grid that can be directly moved and turned via navigation commands.
 *
 * @param spatialInfo The initial spatial info of the ship in the grid.
 */
class SimpleNavigationShip(
    spatialInfo: SpatialInfo = SpatialInfo(Point.ORIGIN, Direction.UP)
) : Ship {
    /**
     * The current spatial info of this ship in the grid
     */
    var spatialInfo: SpatialInfo = spatialInfo
        private set

    /**
     * Moves this ship [distance] grid units in the given [direction] without changing the direction
     * it's facing.
     */
    override fun move(direction: Direction, distance: Int) {
        spatialInfo = spatialInfo.move(direction, distance)
    }

    /**
     * Turns this ship counterclockwise by the given [angle] without changing its position.
     */
    override fun turnLeft(angle: RotationAngle) {
        spatialInfo = spatialInfo.turnLeft(angle)
    }

    /**
     * Moves this ship [count] grid units in the direction it's currently facing.
     */
    override fun goForward(count: Int) {
        spatialInfo = spatialInfo.move(distance = count)
    }
}
