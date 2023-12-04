/*
--- Part Two ---

Your calculation isn't quite right. It looks like some of the digits are actually spelled out with
letters: one, two, three, four, five, six, seven, eight, and nine also count as valid "digits".

Equipped with this new information, you now need to find the real first and last digit on each line.
For example:

```
two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen
```

In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76. Adding these together
produces 281.

What is the sum of all of the calibration values?
*/

package com.curtislb.adventofcode.year2023.day01.part2

import com.curtislb.adventofcode.year2023.day01.calibration.CalibrationDocument
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2023, day 1, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): Int {
    val calibration = CalibrationDocument(inputPath.toFile().readLines())
    return calibration.sumValuesFromDigitsAndWords()
}

fun main() {
    println(solve())
}
