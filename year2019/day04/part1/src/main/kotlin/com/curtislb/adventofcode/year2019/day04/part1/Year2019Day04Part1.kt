/*
--- Day 4: Secure Container ---

You arrive at the Venus fuel depot only to discover it's protected by a password. The Elves had written the password on
a sticky note, but someone threw it out.

However, they do remember a few key facts about the password:

  - It is a six-digit number.
  - The value is within the range given in your puzzle input.
  - Two adjacent digits are the same (like 22 in 122345).
  - Going from left to right, the digits never decrease; they only ever increase or stay the same (like 111123 or
    135679).

Other than the range rule, the following are true:

  - 111111 meets these criteria (double 11, never decreases).
  - 223450 does not meet these criteria (decreasing pair of digits 50).
  - 123789 does not meet these criteria (no double).

How many different passwords within the range given in your puzzle input meet these criteria?
*/

package com.curtislb.adventofcode.year2019.day04.part1

import com.curtislb.adventofcode.common.io.pathToInput
import com.curtislb.adventofcode.common.io.readIntRange
import com.curtislb.adventofcode.year2019.day04.password.ExactLengthGenerator
import com.curtislb.adventofcode.year2019.day04.password.HasRepeatedDigitGenerator
import com.curtislb.adventofcode.year2019.day04.password.InRangeGenerator
import com.curtislb.adventofcode.year2019.day04.password.NonDecreasingGenerator
import com.curtislb.adventofcode.year2019.day04.password.SatisfiesAllGenerator
import java.nio.file.Path

/**
 * Returns the solution to the puzzle for 2019, day 4, part 1.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param passwordLength The number of digits in a valid password.
 */
fun solve(inputPath: Path = pathToInput(year = 2019, day = 4), passwordLength: Int = 6): Int {
    val passwordRange = inputPath.toFile().readIntRange()
    val generator = SatisfiesAllGenerator(
        ExactLengthGenerator(passwordLength),
        InRangeGenerator(passwordRange.first, passwordRange.last),
        NonDecreasingGenerator(),
        HasRepeatedDigitGenerator()
    )
    return generator.countPasswords()
}

fun main() {
    println(solve())
}
