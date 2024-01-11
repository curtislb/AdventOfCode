/*
--- Part Two ---

As you try to work out what might be wrong, the reindeer tugs on your shirt and leads you to a
nearby control panel. There, a collection of buttons lets you align the contraption so that the beam
enters from any edge tile and heading away from that edge. (You can choose either of two directions
for the beam if it starts on a corner; for instance, if the beam starts in the bottom-right corner,
it can start heading either left or upward.)

So, the beam could start on any tile in the top row (heading downward), any tile in the bottom row
(heading upward), any tile in the leftmost column (heading right), or any tile in the rightmost
column (heading left). To produce lava, you need to find the configuration that energizes as many
tiles as possible.

In the above example, this can be achieved by starting the beam in the fourth tile from the left in
the top row:

```
.|<2<\....
|v-v\^....
.v.v.|->>>
.v.v.v^.|.
.v.v.v^...
.v.v.v^..\
.v.v/2\\..
<-2-/vv|..
.|<<<2-|.\
.v//.|.v..
```

Using this configuration, 51 tiles are energized:

```
.#####....
.#.#.#....
.#.#.#####
.#.#.##...
.#.#.##...
.#.#.##...
.#.#####..
########..
.#######..
.#...#.#..
```

Find the initial beam configuration that energizes the largest number of tiles; how many tiles are
energized in that configuration?
*/

package com.curtislb.adventofcode.year2023.day16.part2

import com.curtislb.adventofcode.year2023.day16.beam.BeamContraption
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2023, day 16, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): Int {
    val contraption = BeamContraption.fromFile(inputPath.toFile())
    return contraption.findMaxBeamEnergy()
}

fun main() {
    println(solve())
}
