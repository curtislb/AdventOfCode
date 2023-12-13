/*
--- Part Two ---

Everyone will starve if you only plant such a small number of seeds. Re-reading the almanac, it
looks like the seeds: line actually describes ranges of seed numbers.

The values on the initial seeds: line come in pairs. Within each pair, the first value is the start
of the range and the second value is the length of the range. So, in the first line of the example
above:

```
seeds: 79 14 55 13
```

This line describes two ranges of seed numbers to be planted in the garden. The first range starts
with seed number 79 and contains 14 values: 79, 80, ..., 91, 92. The second range starts with seed
number 55 and contains 13 values: 55, 56, ..., 66, 67.

Now, rather than considering four seed numbers, you need to consider a total of 27 seed numbers.

In the above example, the lowest location number can be obtained from seed number 82, which
corresponds to soil 84, fertilizer 84, water 84, light 77, temperature 45, humidity 46, and location
46. So, the lowest location number is 46.

Consider all of the initial seed numbers listed in the ranges on the first line of the almanac. What
is the lowest location number that corresponds to any of the initial seed numbers?
*/

package com.curtislb.adventofcode.year2023.day05.part2

import com.curtislb.adventofcode.year2023.day05.almanac.Almanac
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2023, day 5, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param sourceCategory The category of the initial values to be converted.
 * @param destCategory The target category to which the values should be converted.
 */
fun solve(
    inputPath: Path = Paths.get("..", "input", "input.txt"),
    sourceCategory: String = "seed",
    destCategory: String = "location"
): Long {
    val (values, almanac) = Almanac.parseFile(inputPath.toFile())
    val ranges = values.windowed(size = 2, step = 2) { it[0]..<(it[0] + it[1]) }.toSet()
    val destRanges = almanac.convertRanges(ranges, sourceCategory, destCategory)
    return destRanges.minOf { it.first }
}

fun main() {
    println(solve())
}
