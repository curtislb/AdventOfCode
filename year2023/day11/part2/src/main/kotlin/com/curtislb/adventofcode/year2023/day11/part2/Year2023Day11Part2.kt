/*
--- Part Two ---

The galaxies are much older (and thus much farther apart) than the researcher initially estimated.

Now, instead of the expansion you did before, make each empty row or column one million times
larger. That is, each empty row should be replaced with 1000000 empty rows, and each empty column
should be replaced with 1000000 empty columns.

(In the example above, if each empty row or column were merely 10 times larger, the sum of the
shortest paths between every pair of galaxies would be 1030. If each empty row or column were merely
100 times larger, the sum of the shortest paths between every pair of galaxies would be 8410.
However, your universe will need to expand far beyond these values.)

Starting with the same initial image, expand the universe according to these new rules, then find
the length of the shortest path between every pair of galaxies. What is the sum of these lengths?
*/

package com.curtislb.adventofcode.year2023.day11.part2

import com.curtislb.adventofcode.year2023.day11.expansion.ExpandedUniverse
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2023, day 11, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param expansionFactor The actual size, after expansion, of each row or column with no galaxies.
 */
fun solve(
    inputPath: Path = Paths.get("..", "input", "input.txt"),
    expansionFactor: Long = 1_000_000L
): Long {
    val universe = ExpandedUniverse.fromFile(inputPath.toFile(), expansionFactor)
    return universe.sumGalaxyDistances()
}

fun main() {
    println(solve())
}
