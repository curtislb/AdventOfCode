/*
TODO: Description goes here
*/

package com.curtislb.adventofcode.year2021.day15.part1

import com.curtislb.adventofcode.common.graph.DirectedEdge
import com.curtislb.adventofcode.common.graph.dijkstraShortestDistance
import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.toGrid
import com.curtislb.adventofcode.common.math.toDigit
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2021, day 15, part 1.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "test_input.txt")): Long? {
    val grid = inputPath.toFile().readLines().map { it.toCharArray().map { it.toDigit() } }.toGrid()
    return dijkstraShortestDistance(
        Point.ORIGIN,
        isGoal = { it == Point.fromMatrixCoordinates(grid.lastRowIndex, grid.lastColumnIndex) },
        getEdges = { point ->
            point.cardinalNeighbors().filter { it in grid }.map {
                DirectedEdge(it, grid[it].toLong())
            }.asSequence()
        }
    )
}

fun main() {
    println(solve())
}
