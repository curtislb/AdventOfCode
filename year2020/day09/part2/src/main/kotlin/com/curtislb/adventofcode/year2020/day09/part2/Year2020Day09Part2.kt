/*
--- Part Two ---

The final step in breaking the XMAS encryption relies on the invalid number you just found: you must
find a contiguous set of at least two numbers in your list which sum to the invalid number from step
1.

Again consider the above example:

35
20
15
25
47
40
62
55
65
95
102
117
150
182
127
219
299
277
309
576

In this list, adding up all of the numbers from 15 through 40 produces the invalid number from step
1, 127. (Of course, the contiguous set of numbers in your actual list might be much longer.)

To find the encryption weakness, add together the smallest and largest number in this contiguous
range; in this example, these are 15 and 47, producing 62.

What is the encryption weakness in your XMAS-encrypted list of numbers?
*/

package com.curtislb.adventofcode.year2020.day09.part2

import com.curtislb.adventofcode.common.parse.parseLongs
import com.curtislb.adventofcode.year2020.day09.encryption.Xmas
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2020, day 9, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param preambleSize The count of numbers in the preamble.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt"), preambleSize: Int = 25): Long? {
    val file = inputPath.toFile()
    val xmas = Xmas(file.readText().parseLongs(), preambleSize)
    return xmas.findEncryptionWeakness()
}

fun main() {
    when (val solution = solve()) {
        null -> println("No solution")
        else -> println(solution)
    }
}
