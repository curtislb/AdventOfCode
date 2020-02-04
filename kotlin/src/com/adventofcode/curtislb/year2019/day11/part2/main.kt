/*
--- Part Two ---

You're not sure what it's trying to paint, but it's definitely not a registration identifier. The Space Police are
getting impatient.

Checking your external ship cameras again, you notice a white panel marked "emergency hull painting robot starting
panel". The rest of the panels are still black, but it looks like the robot was expecting to start on a white panel,
not a black one.

Based on the Space Law Space Brochure that the Space Police attached to one of your windows, a valid registration
identifier is always eight capital letters. After starting the robot on a single white panel instead, what registration
identifier does it paint on your hull?
*/

package com.adventofcode.curtislb.year2019.day11.part2

import com.adventofcode.curtislb.common.intcode.Intcode
import com.adventofcode.curtislb.common.io.pathToInput
import com.adventofcode.curtislb.year2019.day11.painting.Color
import com.adventofcode.curtislb.year2019.day11.painting.Robot

private val INPUT_PATH = pathToInput(year = 2019, day = 11)

// Answer: ZCGRHKLB
fun main() {
    // Run the robot from a white starting panel.
    val robot = Robot()
    robot.paint(Color.WHITE)
    robot.executeProgram(Intcode(INPUT_PATH.toFile()))

    // Print the painted grid (with its y-axis reversed).
    robot.paintedGrid.forEach { row ->
        row.forEach { print(it.symbol) }
        println()
    }
}
