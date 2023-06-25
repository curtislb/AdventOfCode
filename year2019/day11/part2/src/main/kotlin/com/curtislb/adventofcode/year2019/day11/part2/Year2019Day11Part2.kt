/*
--- Part Two ---

You're not sure what it's trying to paint, but it's definitely not a registration identifier. The
Space Police are getting impatient.

Checking your external ship cameras again, you notice a white panel marked "emergency hull painting
robot starting panel". The rest of the panels are still black, but it looks like the robot was
expecting to start on a white panel, not a black one.

Based on the Space Law Space Brochure that the Space Police attached to one of your windows, a valid
registration identifier is always eight capital letters. After starting the robot on a single white
panel instead, what registration identifier does it paint on your hull?
*/

package com.curtislb.adventofcode.year2019.day11.part2

import com.curtislb.adventofcode.common.grid.joinRowsToString
import com.curtislb.adventofcode.common.intcode.Intcode
import com.curtislb.adventofcode.year2019.day11.painting.Color
import com.curtislb.adventofcode.year2019.day11.painting.Robot
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2019, day 11, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): String {
    // Run the robot from a white starting panel.
    val robot = Robot()
    robot.paint(Color.WHITE)
    robot.executeProgram(Intcode(inputPath.toFile()))

    // Convert the painted portion of the grid to a string.
    return robot.getPaintedGrid().joinRowsToString(separator = "\n") { row ->
        row.joinToString(separator = "") { color -> color.symbol.toString() }
    }
}

fun main() {
    println(solve())
}
