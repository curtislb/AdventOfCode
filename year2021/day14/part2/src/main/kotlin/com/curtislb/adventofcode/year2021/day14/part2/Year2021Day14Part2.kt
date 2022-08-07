/*
--- Part Two ---

The resulting polymer isn't nearly strong enough to reinforce the submarine. You'll need to run more
steps of the pair insertion process; a total of 40 steps should do it.

In the above example, the most common element is `B` (occurring 2192039569602 times) and the least
common element is `H` (occurring 3849876073 times); subtracting these produces 2188189693529.

Apply 40 steps of pair insertion to the polymer template and find the most and least common elements
in the result. What do you get if you take the quantity of the most common element and subtract the
quantity of the least common element?
*/

package com.curtislb.adventofcode.year2021.day14.part2

import com.curtislb.adventofcode.year2021.day14.polymer.PolymerizationProcess
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2021, day 14, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param stepCount The number pair insertion steps to apply to the polymer template.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt"), stepCount: Int = 40): Long {
    val polymerization = PolymerizationProcess.fromFile(inputPath.toFile())
    polymerization.applyPairInsertions(stepCount)
    return polymerization.maxElementCountDifference()
}

fun main() {
    println(solve())
}
