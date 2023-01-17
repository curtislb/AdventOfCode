package com.curtislb.adventofcode.year2020.day12.navigation

import com.curtislb.adventofcode.common.geometry.Direction
import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.geometry.RotationAngle

/**
 * A ship in a 2D grid that can be navigated by manipulating a waypoint.
 *
 * @param position The initial position of the ship in the grid.
 * @param waypoint The initial position of the waypoint relative to the ship.
 */
class WaypointNavigationShip(
    position: Point = Point.ORIGIN,
    waypoint: Point = Point.ORIGIN
) : Ship {
    /**
     * The current position of the ship in the grid.
     */
    var position: Point = position
        private set

    /**
     * The current position of the waypoint relative to the ship.
     */
    var waypoint: Point = waypoint
        private set

    /**
     * Moves the waypoint [distance] grid units in the given [direction] relative to the ship.
     */
    override fun move(direction: Direction, distance: Int) {
        waypoint = waypoint.move(direction, distance)
    }

    /**
     * Rotates the waypoint counterclockwise around the ship by the given [angle].
     */
    override fun turnLeft(angle: RotationAngle) = when (angle) {
        RotationAngle.DEGREES_0 -> Unit
        RotationAngle.DEGREES_90 -> waypoint = waypoint.rotateCounterclockwise()
        RotationAngle.DEGREES_180 -> waypoint = waypoint.rotate180Degrees()
        RotationAngle.DEGREES_270 -> waypoint = waypoint.rotateClockwise()
        else -> throw IllegalArgumentException("Rotation angle not supported: $angle")
    }

    /**
     * Moves the ship to the position of the waypoint [count] times (without moving the waypoint
     * relative to the ship).
     */
    override fun goForward(count: Int) {
        repeat(count) { position += waypoint }
    }
}
