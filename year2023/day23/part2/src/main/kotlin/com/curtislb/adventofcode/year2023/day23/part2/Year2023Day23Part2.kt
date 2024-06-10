/*
--- Part Two ---

As you reach the trailhead, you realize that the ground isn't as slippery as you expected; you'll
have no problem climbing up the steep slopes.

Now, treat all slopes as if they were normal paths (`.`). You still want to make sure you have the
most scenic hike possible, so continue to ensure that you never step onto the same tile twice. What
is the longest hike you can take?

In the example above, this increases the longest hike to 154 steps:

```
#S#####################
#OOOOOOO#########OOO###
#######O#########O#O###
###OOOOO#.>OOO###O#O###
###O#####.#O#O###O#O###
###O>...#.#O#OOOOO#OOO#
###O###.#.#O#########O#
###OOO#.#.#OOOOOOO#OOO#
#####O#.#.#######O#O###
#OOOOO#.#.#OOOOOOO#OOO#
#O#####.#.#O#########O#
#O#OOO#...#OOO###...>O#
#O#O#O#######O###.###O#
#OOO#O>.#...>O>.#.###O#
#####O#.#.###O#.#.###O#
#OOOOO#...#OOO#.#.#OOO#
#O#########O###.#.#O###
#OOO###OOO#OOO#...#O###
###O###O#O###O#####O###
#OOO#OOO#O#OOO>.#.>O###
#O###O###O#O###.#.#O###
#OOOOO###OOO###...#OOO#
#####################O#
```

Find the longest hike you can take through the surprisingly dry hiking trails listed on your map.
How many steps long is the longest hike?
*/

package com.curtislb.adventofcode.year2023.day23.part2

import com.curtislb.adventofcode.year2023.day23.hike.HikingTrailMap
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2023, day 23, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): Int {
    val hikingTrailMap = HikingTrailMap.fromFile(inputPath.toFile())
    return hikingTrailMap.findLongestPathDistance(isIcy = false)
}

fun main() {
    println(solve())
}
