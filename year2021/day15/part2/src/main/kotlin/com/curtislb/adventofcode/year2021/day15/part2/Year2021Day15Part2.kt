/*
TODO: Description goes here
*/

package com.curtislb.adventofcode.year2021.day15.part2

import com.curtislb.adventofcode.common.collection.getCyclic
import com.curtislb.adventofcode.common.graph.DirectedEdge
import com.curtislb.adventofcode.common.graph.dijkstraShortestDistance
import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.toGrid
import com.curtislb.adventofcode.common.math.toDigit
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2021, day 15, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "test_input.txt")): Long? {
    val grid = inputPath.toFile().readLines().map { it.toCharArray().map { it.toDigit() } }.toGrid()
    return dijkstraShortestDistance(
        Point.ORIGIN,
        isGoal = {
            val (rowIndex, colIndex) = it.toMatrixCoordinates()
            rowIndex == grid.height * 5 - 1 &&
                colIndex == grid.width * 5 - 1
        },
        getEdges = { point ->
            point.cardinalNeighbors().filter {
                val (rowIndex, colIndex) = it.toMatrixCoordinates()
                rowIndex in 0 until (grid.height * 5) &&
                    colIndex in 0 until (grid.width * 5)
            }.map {
                val (rowIndex, colIndex) = it.toMatrixCoordinates()
                val row = grid.shallowRows().getCyclic(rowIndex)
                val baseRisk = row.getCyclic(colIndex).toLong()
                val riskIncrease = rowIndex / grid.height + colIndex / grid.width
                val modRisk = ((baseRisk + riskIncrease - 1L) % 9L) + 1L
                DirectedEdge(it, modRisk)
            }.asSequence()
        }
    )
}

fun main() {
    println(solve())
}
