/*
--- Part Two ---

After reviewing the available paths, you realize you might have time to visit a single small cave
twice. Specifically, big caves can be visited any number of times, a single small cave can be
visited at most twice, and the remaining small caves can be visited at most once. However, the caves
named `start` and `end` can only be visited exactly once each: once you leave the `start` cave, you
may not return to it, and once you reach the `end` cave, the path must end immediately.

Now, the 36 possible paths through the first example above are:

```
start,A,b,A,b,A,c,A,end
start,A,b,A,b,A,end
start,A,b,A,b,end
start,A,b,A,c,A,b,A,end
start,A,b,A,c,A,b,end
start,A,b,A,c,A,c,A,end
start,A,b,A,c,A,end
start,A,b,A,end
start,A,b,d,b,A,c,A,end
start,A,b,d,b,A,end
start,A,b,d,b,end
start,A,b,end
start,A,c,A,b,A,b,A,end
start,A,c,A,b,A,b,end
start,A,c,A,b,A,c,A,end
start,A,c,A,b,A,end
start,A,c,A,b,d,b,A,end
start,A,c,A,b,d,b,end
start,A,c,A,b,end
start,A,c,A,c,A,b,A,end
start,A,c,A,c,A,b,end
start,A,c,A,c,A,end
start,A,c,A,end
start,A,end
start,b,A,b,A,c,A,end
start,b,A,b,A,end
start,b,A,b,end
start,b,A,c,A,b,A,end
start,b,A,c,A,b,end
start,b,A,c,A,c,A,end
start,b,A,c,A,end
start,b,A,end
start,b,d,b,A,c,A,end
start,b,d,b,A,end
start,b,d,b,end
start,b,end
```

The slightly larger example above now has 103 paths through it, and the even larger example now has
3509 paths through it.

Given these new rules, how many paths through this cave system are there?
*/

package com.curtislb.adventofcode.year2021.day12.part2

import com.curtislb.adventofcode.year2021.day12.caves.CaveSystem
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2021, day 12, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param startCave The name of the cave from which all paths should start.
 * @param endCave The name of the cave at which all paths should end.
 */
fun solve(
    inputPath: Path = Paths.get("..", "input", "input.txt"),
    startCave: String = "start",
    endCave: String = "end"
): Int {
    val caveSystem = CaveSystem(inputPath.toFile().readText())
    return caveSystem.countPaths(startCave, endCave, maxSmallRepeatCount = 1)
}

fun main() {
    println(solve())
}
