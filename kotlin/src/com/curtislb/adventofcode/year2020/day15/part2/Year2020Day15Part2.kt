/*
--- Part Two ---

Impressed, the Elves issue you a challenge: determine the 30000000th number spoken. For example, given the same starting
numbers as above:

- Given 0,3,6, the 30000000th number spoken is 175594.
- Given 1,3,2, the 30000000th number spoken is 2578.
- Given 2,1,3, the 30000000th number spoken is 3544142.
- Given 1,2,3, the 30000000th number spoken is 261214.
- Given 2,3,1, the 30000000th number spoken is 6895259.
- Given 3,2,1, the 30000000th number spoken is 18.
- Given 3,1,2, the 30000000th number spoken is 362.

Given your starting numbers, what will be the 30000000th number spoken?
*/

package com.curtislb.adventofcode.year2020.day15.part2

import com.curtislb.adventofcode.common.io.pathToInput
import com.curtislb.adventofcode.year2020.day15.memory.playGame
import java.nio.file.Path

/**
 * Returns the solution to the puzzle for 2020, day 15, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = pathToInput(year = 2020, day = 15)): Int {
    val file = inputPath.toFile()
    val startingNums = file.readText().trim().split(',').map { it.toInt() }
    return playGame(startingNums, 30_000_000)
}

fun main() {
    println(solve())
}
