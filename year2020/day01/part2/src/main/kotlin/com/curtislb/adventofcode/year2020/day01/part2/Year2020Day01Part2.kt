/*
--- Part Two ---

The Elves in accounting are thankful for your help; one of them even offers you a starfish coin they
had left over from a past vacation. They offer you a second one if you can find three numbers in
your expense report that meet the same criteria.

Using the above example again, the three entries that sum to 2020 are 979, 366, and 675. Multiplying
them together produces the answer, 241861950.

In your expense report, what is the product of the three entries that sum to 2020?
*/

package com.curtislb.adventofcode.year2020.day01.part2

import com.curtislb.adventofcode.common.math.product
import com.curtislb.adventofcode.common.search.findTupleSum
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2020, day 1, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): Int? {
    val file = inputPath.toFile()
    val entries = file.readLines().map { it.toInt() }
    val sumEntries = entries.findTupleSum(count = 3, targetSum = 2020)
    return sumEntries?.product()
}

fun main() = when (val solution = solve()) {
    null -> println("No solution found.")
    else -> println(solution)
}
