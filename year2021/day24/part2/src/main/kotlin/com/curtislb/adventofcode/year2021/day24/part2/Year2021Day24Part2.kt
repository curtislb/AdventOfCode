/*
--- Part Two ---

As the submarine starts booting up things like the Retro Encabulator, you realize that maybe you
don't need all these submarine features after all.

What is the smallest model number accepted by MONAD?
*/

package com.curtislb.adventofcode.year2021.day24.part2

import com.curtislb.adventofcode.year2021.day24.monad.Monad
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2021, day 24, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): Long {
    val monad = Monad.fromFile(inputPath.toFile())
    return monad.smallestModelNumber()
}

fun main() {
    println(solve())
}
