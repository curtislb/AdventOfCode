package com.adventofcode.curtislb.year2019.day10.asteroid

import com.adventofcode.curtislb.common.collection.uniquePairs
import com.adventofcode.curtislb.common.grid.Point
import com.adventofcode.curtislb.common.grid.Ray

/**
 * Finds the best location for a monitoring station, given the positions of all asteroids in a grid.
 *
 * The station must be located at one of the positions contained in [asteroids], and the best location is the one from
 * which the maximum number of other asteroids are visible.
 *
 * @param asteroids The [Point] locations of all asteroids, relative to the top-left corner of the grid.
 * @return If there are at least two asteroids, returns a [Pair] containing the best [Point] for a station to be located
 *  and the number of other asteroids visible from that location. Otherwise, returns the [Pair] `(null, 0)`.
 */
fun findBestStation(asteroids: List<Point>): Pair<Point?, Int> {
    val asteroidRays = mutableMapOf<Point, MutableSet<Ray>>()
    asteroids.uniquePairs().forEach { (asteroid1, asteroid2) ->
        asteroidRays.getOrPut(asteroid1) { mutableSetOf() }.add(Ray(asteroid1, asteroid2))
        asteroidRays.getOrPut(asteroid2) { mutableSetOf() }.add(Ray(asteroid2, asteroid1))
    }

    var bestStation: Point? = null
    var mostAsteroids = 0
    asteroids.forEach { point ->
        val asteroidCount = asteroidRays[point]?.size ?: 0
        if (asteroidCount > mostAsteroids) {
            bestStation = point
            mostAsteroids = asteroidCount
        }
    }
    return Pair(bestStation, mostAsteroids)
}
