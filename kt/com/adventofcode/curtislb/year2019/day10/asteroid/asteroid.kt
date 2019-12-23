package com.adventofcode.curtislb.year2019.day10.asteroid

import com.adventofcode.curtislb.common.collection.uniquePairs
import com.adventofcode.curtislb.common.grid.Point
import com.adventofcode.curtislb.common.grid.Ray
import java.io.File
import kotlin.math.PI
import kotlin.math.atan2

/**
 * Reads all asteroid locations from an input file.
 *
 * The input [file] should consist of one or more lines, where each character is one of:
 * - '#', representing an asteroid
 * - '.', representing an empty grid space
 *
 * @param file The [File] from which asteroid locations will be read.
 * @return A [List] of points representing the grid locations of all asteroids, where [Point.x] is the horizontal
 *  distance and [Point.y] is the vertical distance from the top-left corner of the grid.
 */
fun getAsteroids(file: File): List<Point> {
    val asteroids = mutableListOf<Point>()
    var y = 0
    file.forEachLine { line ->
        for (x in line.indices) {
            if (line[x] == '#') {
                asteroids.add(Point(x, y))
            }
        }
        y++
    }
    return asteroids
}

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
    for (asteroidPair in asteroids.uniquePairs()) {
        val (asteroid1, asteroid2) = asteroidPair
        asteroidRays.getOrPut(asteroid1) { mutableSetOf() }.add(Ray(asteroid1, asteroid2))
        asteroidRays.getOrPut(asteroid2) { mutableSetOf() }.add(Ray(asteroid2, asteroid1))
    }

    var bestStation: Point? = null
    var mostAsteroids = 0
    for (asteroid in asteroids) {
        val asteroidCount = if (asteroid in asteroidRays) asteroidRays[asteroid]!!.size else 0
        if (asteroidCount > mostAsteroids) {
            bestStation = asteroid
            mostAsteroids = asteroidCount
        }
    }
    return Pair(bestStation, mostAsteroids)
}

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
    for (asteroid in asteroids) {
        if (source == asteroid) {
            continue
        }
        val ray = Ray(source, asteroid)
        val squaredDistance = source.squaredDistanceTo(asteroid)
        if (ray !in closestAsteroids || closestAsteroids[ray]!!.second > squaredDistance) {
            closestAsteroids[ray] = Pair(asteroid, squaredDistance)
        }
    }
    return closestAsteroids.values.map { it.first }
}

/**
 * Finds the relative angle of the segment formed by this and another [Point].
 * @param other A [Point] that forms a segment with this one.
 * @return The angle in radians (from 0 to `2 * PI`, exclusive) of the segment formed by this [Point] and [other],
 *  measured clockwise from the negative y-axis.
 */
fun Point.relativeAngleTo(other: Point) = PI - atan2((other.x - x).toDouble(), (other.y - y).toDouble())
