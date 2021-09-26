/*
--- Part Two ---

As you finish the last group's customs declaration, you notice that you misread one word in the instructions:

You don't need to identify the questions to which anyone answered "yes"; you need to identify the questions to which
everyone answered "yes"!

Using the same example as above:

abc

a
b
c

ab
ac

a
a
a
a

b

This list represents answers from five groups:

- In the first group, everyone (all 1 person) answered "yes" to 3 questions: a, b, and c.
- In the second group, there is no question to which everyone answered "yes".
- In the third group, everyone answered yes to only 1 question, a. Since some people did not answer "yes" to b or c,
  they don't count.
- In the fourth group, everyone answered yes to only 1 question, a.
- In the fifth group, everyone (all 1 person) answered "yes" to 1 question, b.

In this example, the sum of these counts is 3 + 0 + 1 + 1 + 1 = 6.

For each group, count the number of questions to which everyone answered "yes". What is the sum of those counts?
*/

package com.curtislb.adventofcode.year2020.day06.part2

import com.curtislb.adventofcode.common.io.forEachSection
import com.curtislb.adventofcode.common.io.pathToInput
import java.nio.file.Path

/**
 * Returns the solution to the puzzle for 2020, day 6, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = pathToInput(year = 2020, day = 6)): Int {
    val file = inputPath.toFile()
    var total = 0
    val lowerLetters = ('a'..'z').toSet()
    file.forEachSection { lines ->
        total += lines.fold(lowerLetters) { answerSet, line -> answerSet intersect line.toSet() }.size
    }
    return total
}

fun main() {
    println(solve())
}
