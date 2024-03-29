/*
--- Part Two ---

Due to what you can only assume is a mistranslation (you're not exactly fluent in Crab), you are
quite surprised when the crab starts arranging many cups in a circle on your raft - one million
(1000000) in total.

Your labeling is still correct for the first few cups; after that, the remaining cups are just
numbered in an increasing fashion starting from the number after the highest number in your list and
proceeding one by one until one million is reached. (For example, if your labeling were 54321, the
cups would be numbered 5, 4, 3, 2, 1, and then start counting up from 6 until one million is
reached.) In this way, every number from one through one million is used exactly once.

After discovering where you made the mistake in translating Crab Numbers, you realize the small crab
isn't going to do merely 100 moves; the crab is going to do ten million (10000000) moves!

The crab is going to hide your stars - one each - under the two cups that will end up immediately
clockwise of cup 1. You can have them if you predict what the labels on those cups will be when the
crab is finished.

In the above example (389125467), this would be 934001 and then 159792; multiplying these together
produces 149245887792.

Determine which two cups will end up immediately clockwise of cup 1. What do you get if you multiply
their labels together?
*/

package com.curtislb.adventofcode.year2020.day23.part2

import com.curtislb.adventofcode.common.number.product
import com.curtislb.adventofcode.year2020.day23.cups.CupCircle
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2020, day 23, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): Long {
    val file = inputPath.toFile()
    val cupCircle = CupCircle(file.readText(), fillUpTo = 1_000_000)
    repeat(10_000_000) { cupCircle.performMove() }
    return cupCircle.findLabelsAfter(1, count = 2).map { it.toLong() }.product()
}

fun main() {
    println(solve())
}
