/*
--- Part Two ---

It seems like the individual flashes aren't bright enough to navigate. However, you might have a
better option: the flashes seem to be synchronizing!

In the example above, the first time all octopuses flash simultaneously is step 195:

```
After step 193:
5877777777
8877777777
7777777777
7777777777
7777777777
7777777777
7777777777
7777777777
7777777777
7777777777

After step 194:
6988888888
9988888888
8888888888
8888888888
8888888888
8888888888
8888888888
8888888888
8888888888
8888888888

After step 195:
0000000000
0000000000
0000000000
0000000000
0000000000
0000000000
0000000000
0000000000
0000000000
0000000000
```

If you can calculate the exact moments when the octopuses will all flash simultaneously, you should
be able to navigate through the cavern. What is the first step during which all octopuses flash?
*/

package com.curtislb.adventofcode.year2021.day11.part2

import com.curtislb.adventofcode.year2021.day11.octopus.OctopusGrid
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2021, day 11, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): Int {
    val octopusGrid = OctopusGrid(inputPath.toFile().readText())
    octopusGrid.updateUntil {
        values().all { it == 0 }
    }
    return octopusGrid.totalSteps
}

fun main() {
    println(solve())
}
