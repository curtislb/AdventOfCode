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

package com.adventofcode.curtislb.year2019.day04.part2

import com.adventofcode.curtislb.common.io.pathToInput
import com.adventofcode.curtislb.common.io.readIntRange
import com.adventofcode.curtislb.year2019.day04.password.ExactLengthGenerator
import com.adventofcode.curtislb.year2019.day04.password.ExactRepCountDigitGenerator
import com.adventofcode.curtislb.year2019.day04.password.InRangeGenerator
import com.adventofcode.curtislb.year2019.day04.password.NonDecreasingGenerator
import com.adventofcode.curtislb.year2019.day04.password.SatisfiesAllGenerator

private val INPUT_PATH = pathToInput(year = 2019, day = 4, fileName = "input.txt")

private const val PASSWORD_LENGTH = 6
private const val REPEAT_COUNT = 2

fun main() {
    val (minPassword, maxPassword) = INPUT_PATH.toFile().readIntRange()
    val generator = SatisfiesAllGenerator(
        ExactLengthGenerator(PASSWORD_LENGTH),
        InRangeGenerator(minPassword, maxPassword),
        NonDecreasingGenerator(),
        ExactRepCountDigitGenerator(REPEAT_COUNT)
    )
    println(generator.countPasswords())
}
