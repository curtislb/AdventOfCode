/*
--- Part Two ---

Finish folding the transparent paper according to the instructions. The manual says the code is
always eight capital letters.

What code do you use to activate the infrared thermal imaging camera system?
*/

package com.curtislb.adventofcode.year2021.day13.part2

import com.curtislb.adventofcode.common.grid.gridOfPoints
import com.curtislb.adventofcode.common.grid.joinRowsToString
import com.curtislb.adventofcode.common.io.readSections
import com.curtislb.adventofcode.year2021.day13.origami.OrigamiSheet
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2021, day 13, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): String {
    val (pointStrings, instructionStrings) = inputPath.toFile().readSections()
    val sheet = OrigamiSheet(pointStrings, instructionStrings).apply { fold() }
    val pointGrid = gridOfPoints(sheet.points) { if (it in sheet.points) '#' else ' ' }
    return pointGrid.joinRowsToString(separator = "\n") { it.joinToString(separator = "") }
}

fun main() {
    println(solve())
}
