/*
--- Part Two ---

Suppose the lanternfish live forever and have unlimited food and space. Would they take over the
entire ocean?

After 256 days in the example above, there would be a total of 26984457539 lanternfish!

How many lanternfish would there be after 256 days?
*/

package com.curtislb.adventofcode.year2021.day06.part2

import com.curtislb.adventofcode.common.parse.parseInts
import com.curtislb.adventofcode.year2021.day06.lanternfish.School
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2021, day 6, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param cycleDays The number of days required for a mature lanternfish to create a new fish.
 * @param maturationDays The number of days required for a new lanternfish to become mature.
 * @param simulationDays The number of days for which the simulation should be run.
 */
fun solve(
    inputPath: Path = Paths.get("..", "input", "input.txt"),
    cycleDays: Int = 7,
    maturationDays: Int = 2,
    simulationDays: Int = 256
): Long {
    val initialTimers = inputPath.toFile().readText().parseInts()
    val school = School(initialTimers, cycleDays, maturationDays).apply { update(simulationDays) }
    return school.countFish()
}

fun main() {
    println(solve())
}
