/*
--- Part Two ---

As the race is about to start, you realize the piece of paper with race times and record distances
you got earlier actually just has very bad kerning. There's really only one race - ignore the spaces
between the numbers on each line.

So, the example from before:

```
Time:      7  15   30
Distance:  9  40  200
```

...now instead means this:

```
Time:      71530
Distance:  940200
```

Now, you have to figure out how many ways there are to win this single race. In this example, the
race lasts for 71530 milliseconds and the record distance you need to beat is 940200 millimeters.
You could hold the button anywhere from 14 to 71516 milliseconds and beat the record, a total of
71503 ways!

How many ways can you beat the record in this one much longer race?
*/

package com.curtislb.adventofcode.year2023.day06.part2

import com.curtislb.adventofcode.common.parse.parseInts
import com.curtislb.adventofcode.year2023.day06.boat.BoatRace
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2023, day 6, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): Long {
    val (timeLine, distanceLine) = inputPath.toFile().readLines()
    val time = timeLine.joinIntsToLong()
    val distance = distanceLine.joinIntsToLong()
    val race = BoatRace(time, distance)
    return race.countWaysToWin()
}

/**
 * Returns the [Long] integer value given by concatenating all decimal integers in the string.
 */
private fun String.joinIntsToLong(): Long = parseInts().joinToString(separator = "").toLong()

fun main() {
    println(solve())
}
