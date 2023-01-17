/*
--- Part Two ---

Time to check the rest of the slopes - you need to minimize the probability of a sudden arboreal
stop, after all.

Determine the number of trees you would encounter if, for each of the following slopes, you start at
the top-left corner and traverse the map all the way to the bottom:

- Right 1, down 1.
- Right 3, down 1. (This is the slope you already checked.)
- Right 5, down 1.
- Right 7, down 1.
- Right 1, down 2.

In the above example, these slopes would find 2, 7, 3, 4, and 2 tree(s) respectively; multiplied
together, these produce the answer 336.
*/

package com.curtislb.adventofcode.year2020.day03.part2

import com.curtislb.adventofcode.common.number.Fraction
import com.curtislb.adventofcode.common.number.product
import com.curtislb.adventofcode.year2020.day03.trees.TreeField
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2020, day 3, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): Long {
    val file = inputPath.toFile()
    val field = TreeField(file.readText())
    val slopes = listOf(
        Fraction(-1, 1),
        Fraction(-1, 3),
        Fraction(-1, 5),
        Fraction(-1, 7),
        Fraction(-2, 1)
    )
    return slopes.map { field.countTreesAlongSlope(it).toLong() }.product()
}

fun main() {
    println(solve())
}
