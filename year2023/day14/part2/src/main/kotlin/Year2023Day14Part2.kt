/*
--- Part Two ---

The parabolic reflector dish deforms, but not in a way that focuses the beam. To do that, you'll
need to move the rocks to the edges of the platform. Fortunately, a button on the side of the
control panel labeled "spin cycle" attempts to do just that!

Each cycle tilts the platform four times so that the rounded rocks roll north, then west, then
south, then east. After each tilt, the rounded rocks roll as far as they can before the platform
tilts in the next direction. After one cycle, the platform will have finished rolling the rounded
rocks in those four directions in that order.

Here's what happens in the example above after each of the first few cycles:

```
After 1 cycle:
.....#....
....#...O#
...OO##...
.OO#......
.....OOO#.
.O#...O#.#
....O#....
......OOOO
#...O###..
#..OO#....

After 2 cycles:
.....#....
....#...O#
.....##...
..O#......
.....OOO#.
.O#...O#.#
....O#...O
.......OOO
#..OO###..
#.OOO#...O

After 3 cycles:
.....#....
....#...O#
.....##...
..O#......
.....OOO#.
.O#...O#.#
....O#...O
.......OOO
#...O###.O
#.OOO#...O
```

This process should work if you leave it running long enough, but you're still worried about the
north support beams. To make sure they'll survive for a while, you need to calculate the total load
on the north support beams after 1000000000 cycles.

In the above example, after 1000000000 cycles, the total load on the north support beams is 64.

Run the spin cycle for 1000000000 cycles. Afterward, what is the total load on the north support
beams?
*/

package com.curtislb.adventofcode.year2023.day14.part2

import com.curtislb.adventofcode.year2023.day14.dish.DishPlatform
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2023, day 14, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param iterations The number of "spin cycle" iterations to run for the platform.
 */
fun solve(
    inputPath: Path = Paths.get("..", "input", "input.txt"),
    iterations: Long = 1_000_000_000L
): Int {
    val platform = DishPlatform.fromFile(inputPath.toFile()).apply { runSpinCycle(iterations) }
    return platform.findNorthBeamLoad()
}

fun main() {
    println(solve())
}
