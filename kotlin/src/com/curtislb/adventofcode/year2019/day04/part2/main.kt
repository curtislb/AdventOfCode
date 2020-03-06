/*
--- Part Two ---

An Elf just remembered one more important detail: the two adjacent matching digits are not part of a larger group of
matching digits.

Given this additional criterion, but still ignoring the range rule, the following are now true:

  - 112233 meets these criteria because the digits never decrease and all repeated digits are exactly two digits long.
  - 123444 no longer meets the criteria (the repeated 44 is part of a larger group of 444).
  - 111122 meets the criteria (even though 1 is repeated more than twice, it still contains a double 22).

How many different passwords within the range given in your puzzle input meet all of the criteria?
*/

package com.curtislb.adventofcode.year2019.day04.part2

import com.curtislb.adventofcode.common.io.pathToInput
import com.curtislb.adventofcode.common.io.readIntRange
import com.curtislb.adventofcode.year2019.day04.password.ExactLengthGenerator
import com.curtislb.adventofcode.year2019.day04.password.ExactRepeatCountDigitGenerator
import com.curtislb.adventofcode.year2019.day04.password.InRangeGenerator
import com.curtislb.adventofcode.year2019.day04.password.NonDecreasingGenerator
import com.curtislb.adventofcode.year2019.day04.password.SatisfiesAllGenerator
import java.nio.file.Path

/**
 * Returns the solution to the puzzle for day 4, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param passwordLength The number of digits in a valid password.
 * @param repeatCount The exact number of times a digit must be repeated in a valid password.
 */
fun solve(inputPath: Path = pathToInput(year = 2019, day = 4), passwordLength: Int = 6, repeatCount: Int = 2): Int {
    val passwordRange = inputPath.toFile().readIntRange()
    val generator = SatisfiesAllGenerator(
        ExactLengthGenerator(passwordLength),
        InRangeGenerator(passwordRange.first, passwordRange.last),
        NonDecreasingGenerator(),
        ExactRepeatCountDigitGenerator(repeatCount)
    )
    return generator.countPasswords()
}

fun main() {
    println(solve())
}
