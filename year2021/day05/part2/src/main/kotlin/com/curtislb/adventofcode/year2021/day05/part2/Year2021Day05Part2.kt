/*
--- Part Two ---

Unfortunately, considering only horizontal and vertical lines doesn't give you the full picture; you
need to also consider diagonal lines.

Because of the limits of the hydrothermal vent mapping system, the lines in your list will only ever
be horizontal, vertical, or a diagonal line at exactly 45 degrees. In other words:

- An entry like `1,1 -> 3,3` covers points `1,1`, `2,2`, and `3,3`.
- An entry like `9,7 -> 7,9` covers points `9,7`, `8,8`, and `7,9`.

Considering all lines from the above example would now produce the following diagram:

```
1.1....11.
.111...2..
..2.1.111.
...1.2.2..
.112313211
...1.2....
..1...1...
.1.....1..
1.......1.
222111....
```

You still need to determine the number of points where at least two lines overlap. In the above
example, this is still anywhere in the diagram with a 2 or larger - now a total of 12 points.

Consider all of the lines. At how many points do at least two lines overlap?
*/

package com.curtislb.adventofcode.year2021.day05.part2

import com.curtislb.adventofcode.year2021.day05.vents.VentField
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2021, day 05, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): Int {
    val vents = VentField(inputPath.toFile().readLines())
    return vents.overlapPoints().size
}

fun main() {
    println(solve())
}
