/*
--- Part Two ---

Finish folding the transparent paper according to the instructions. The manual says the code is
always eight capital letters.

What code do you use to activate the infrared thermal imaging camera system?
*/

package com.curtislb.adventofcode.year2021.day13.part2

import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.constructPointGrid
import com.curtislb.adventofcode.common.io.forEachSection
import com.curtislb.adventofcode.common.parse.toInts
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2021, day 13, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): String {
    var points = mutableSetOf<Point>()
    val instructions = mutableListOf<String>()
    inputPath.toFile().forEachSection { section ->
        if (points.isEmpty()) {
            section.forEach { line ->
                val (x, y) = line.toInts()
                points.add(Point(x, -y))
            }
        } else {
            section.forEach { line ->
                instructions.add(line.trim())
            }
        }
    }

    instructions.forEach { instruction ->
        val (axisName, axisValueString) = instruction.split(" ").last().split("=")
        val isXFold = axisName == "x"
        val axisValue = axisValueString.toInt()
        val foldedPoints = mutableSetOf<Point>()
        points.forEach { point ->
            val foldedPoint = if (isXFold) {
                point.foldLeft(axisValue)
            } else {
                point.foldUp(-axisValue)
            }
            foldedPoints.add(foldedPoint)
        }
        points = foldedPoints
    }

    return constructPointGrid(points) { if (it in points) '#' else ' ' }
        .joinRowsToString(separator = "\n") { it.joinToString(separator = "") }
}

fun Point.foldLeft(axis: Int): Point = if (x > axis) copy(x = axis - (x - axis)) else this

fun Point.foldUp(axis: Int): Point = if (y < axis) copy(y = axis - (y - axis)) else this

fun main() {
    println(solve())
}
