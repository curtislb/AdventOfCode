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

package com.adventofcode.curtislb.year2019.day04.part1

import com.adventofcode.curtislb.common.io.pathToInput
import com.adventofcode.curtislb.common.io.readIntRange
import com.adventofcode.curtislb.year2019.day04.password.ExactLengthGenerator
import com.adventofcode.curtislb.year2019.day04.password.HasRepeatedDigitGenerator
import com.adventofcode.curtislb.year2019.day04.password.InRangeGenerator
import com.adventofcode.curtislb.year2019.day04.password.NonDecreasingGenerator
import com.adventofcode.curtislb.year2019.day04.password.SatisfiesAllGenerator

private val INPUT_PATH = pathToInput(year = 2019, day = 4, fileName = "input.txt")

private const val PASSWORD_LENGTH = 6

// Answer: 460
fun main() {
    val passwordRange = INPUT_PATH.toFile().readIntRange()
    val generator = SatisfiesAllGenerator(
        ExactLengthGenerator(PASSWORD_LENGTH),
        InRangeGenerator(passwordRange.first, passwordRange.last),
        NonDecreasingGenerator(),
        HasRepeatedDigitGenerator()
    )
    println(generator.countPasswords())
}