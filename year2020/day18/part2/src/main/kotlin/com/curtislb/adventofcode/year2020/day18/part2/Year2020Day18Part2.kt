/*
--- Part Two ---

You manage to answer the child's questions and they finish part 1 of their homework, but get stuck
when they reach the next section: advanced math.

Now, addition and multiplication have different precedence levels, but they're not the ones you're
familiar with. Instead, addition is evaluated before multiplication.

For example, the steps to evaluate the expression 1 + 2 * 3 + 4 * 5 + 6 are now as follows:

1 + 2 * 3 + 4 * 5 + 6
  3   * 3 + 4 * 5 + 6
  3   *   7   * 5 + 6
  3   *   7   *  11
     21       *  11
         231

Here are the other examples from above:

- 1 + (2 * 3) + (4 * (5 + 6)) still becomes 51.
- 2 * 3 + (4 * 5) becomes 46.
- 5 + (8 * 3 + 9 + 3 * 4 * 3) becomes 1445.
- 5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4)) becomes 669060.
- ((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2 becomes 23340.

What do you get if you add up the results of evaluating the homework problems using these new rules?
*/

package com.curtislb.adventofcode.year2020.day18.part2

import com.curtislb.adventofcode.year2020.day18.expression.evaluateAdvanced
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2020, day 18, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): Long {
    val file = inputPath.toFile()
    var total = 0L
    file.forEachLine { total += evaluateAdvanced(it) }
    return total
}

fun main() {
    println(solve())
}
