package com.adventofcode.curtislb.year2019.day10.part1.asteroid

import com.adventofcode.curtislb.common.grid.Point
import java.io.File

/**
 * TODO
 */
fun findAsteroids(file: File): List<Point> {
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
