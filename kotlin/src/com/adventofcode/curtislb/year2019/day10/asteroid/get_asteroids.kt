package com.adventofcode.curtislb.year2019.day10.asteroid

import com.adventofcode.curtislb.common.grid.Point
import java.io.File

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
