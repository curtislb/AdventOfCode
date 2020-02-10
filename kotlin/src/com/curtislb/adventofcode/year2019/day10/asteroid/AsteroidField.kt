package com.curtislb.adventofcode.year2019.day10.asteroid

import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.Ray
import java.io.File

/**
 * A collection of asteroids, arranged in a 2D grid.
 *
 * @param file A file containing the grid layout for this asteroid field. Each line should contain a row of characters,
 *  with each representing an empty grid space (`'.'`) or an asteroid (`'#'`).
 */
class AsteroidField(file: File) {
    /**
     * A list of all grid locations that contain asteroids.
     */
    private val asteroids: MutableList<Point>

    init {
        val asteroidsBuilder = mutableListOf<Point>()
        var rowIndex = 0
        file.forEachLine { line ->
            line.forEachIndexed { colIndex, char ->
                if (char == '#') {
                    asteroidsBuilder.add(Point.fromMatrixCoordinates(rowIndex, colIndex))
                }
            }
            rowIndex++
        }
        asteroids = asteroidsBuilder
    }

    /**
     * The number of asteroids currently in the grid.
     */
    val size: Int
        get() = asteroids.size

    /**
     * Finds the best location for a monitoring station, given the positions of all asteroids in the grid.
     *
     * A station must be located at the same position as an asteroid. Among these, the best location is the one from
     * which the maximum number of other asteroids are visible.
     *
     * @return A [Pair] containing the best [Point] for a station to be located and the number of other asteroids
     *  visible from that location, or the [Pair] `(null, 0)` if there are no possible locations.
     */
    fun findBestStation(): Pair<Point?, Int> {
        if (asteroids.size == 1) {
            return Pair(asteroids[0], 0)
        }

        return asteroids.fold(Pair(null as Point?, 0)) { bestPair, asteroid ->
            val (_, mostVisible) = bestPair
            val visibleCount = countVisibleAsteroids(asteroid)
            if (visibleCount > mostVisible) Pair(asteroid, visibleCount) else bestPair
        }
    }

    /**
     * Vaporizes as many asteroids as possible up to [count] from a given [station], removing them from the grid.
     *
     * Asteroids are vaporized from [station] by a laser that begins pointing up and proceeds clockwise, removing at
     * most one asteroid (the one closest to [station]) per angle, per rotation. This continues until either [count]
     * asteroids have been vaporized or no more can be vaporized from [station].
     *
     * @return The position of the last vaporized asteroid if exactly [count] are vaporized, or `null` otherwise.
     */
    fun vaporizeAsteroids(station: Point, count: Int): Point? {
        // Check if the number of asteroids that can be vaporized is less than count.
        if (count >= asteroids.size) {
            asteroids.removeAll { it != station }
            return null
        }

        // Keep removing all visible asteroids until target is among them.
        var vaporizedCount = 0
        var visibleAsteroids = findVisibleAsteroids(station)
        while (count - vaporizedCount > visibleAsteroids.size) {
            vaporizedCount += visibleAsteroids.size
            asteroids.removeAll(visibleAsteroids)
            visibleAsteroids = findVisibleAsteroids(station)
        }

        // Sort visible asteroids in the order they would be vaporized.
        val sortedAsteroids = visibleAsteroids.sortedBy { asteroid ->
            if (asteroid == station) {
                Double.POSITIVE_INFINITY
            } else {
                station.angleClockwiseFromPositiveY(asteroid)
            }
        }

        // Remove all asteroids up to target and return it.
        val targetIndex = count - vaporizedCount - 1
        asteroids.removeAll(sortedAsteroids.subList(0, targetIndex + 1))
        return sortedAsteroids[targetIndex]
    }

    /**
     * Returns the number of asteroids visible from [source].
     *
     * An asteroid is visible if and only if there is a direct line of sight from [source] to its position that is not
     * obstructed by other asteroids. Asteroids located at [source] are not considered to be visible.
     */
    private fun countVisibleAsteroids(source: Point): Int {
        val asteroidRays = mutableSetOf<Ray>()
        asteroids.forEach { asteroid ->
            if (asteroid != source) {
                asteroidRays.add(Ray(source, asteroid))
            }
        }
        return asteroidRays.size
    }

    /**
     * Returns the positions of all asteroids visible from [source].
     *
     * An asteroid is visible if and only if there is a direct line of sight from [source] to its position that is not
     * obstructed by other asteroids. Asteroids located at [source] are not considered to be visible.
     */
    private fun findVisibleAsteroids(source: Point): List<Point> {
        val closestAsteroids = mutableMapOf<Ray, Pair<Point, Int>>()
        asteroids.forEach { asteroid ->
            if (asteroid != source) {
                val ray = Ray(source, asteroid)
                val oldSquaredDistance = closestAsteroids[ray]?.second ?: Int.MAX_VALUE
                val newSquaredDistance = source.squaredDistance(asteroid)
                if (newSquaredDistance < oldSquaredDistance) {
                    closestAsteroids[ray] = Pair(asteroid, newSquaredDistance)
                }
            }
        }
        return closestAsteroids.values.map { it.first }
    }
}
