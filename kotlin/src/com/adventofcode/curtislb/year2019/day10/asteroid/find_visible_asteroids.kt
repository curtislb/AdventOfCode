package com.adventofcode.curtislb.year2019.day10.asteroid

import com.adventofcode.curtislb.common.grid.Point
import com.adventofcode.curtislb.common.grid.Ray

/**
 * Finds all asteroids visible from a [Point] on a 2D grid.
 *
 * An asteroid is visible if there is a direct line of sight from [source] to that [Point] which is not obstructed by
 * other asteroids. Note that any asteroid located at [source] is not considered visible.
 *
 * @param source The [Point] from which other asteroids must be visible.
 * @param asteroids The [Point] locations of all asteroids (possibly including [source]), relative to the top-left
 *  corner of the grid.
 */
fun findVisibleAsteroids(source: Point, asteroids: Collection<Point>): List<Point> {
    val closestAsteroids = mutableMapOf<Ray, Pair<Point, Int>>()
    asteroids.forEach { asteroid ->
        if (asteroid != source) {
            val ray = Ray(source, asteroid)
            val squaredDistance = source.squaredDistanceTo(asteroid)
            if (ray !in closestAsteroids || closestAsteroids[ray]!!.second > squaredDistance) {
                closestAsteroids[ray] = Pair(asteroid, squaredDistance)
            }
        }
    }
    return closestAsteroids.values.map { it.first }
}
