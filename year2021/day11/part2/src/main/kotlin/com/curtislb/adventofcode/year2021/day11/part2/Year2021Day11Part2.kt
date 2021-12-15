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

import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.grid.forEachIndexed
import com.curtislb.adventofcode.common.grid.forEachPointValue
import com.curtislb.adventofcode.common.grid.toMutableGrid
import com.curtislb.adventofcode.common.math.toDigit
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2021, day 11, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): Int {
    val grid = inputPath.toFile().readLines().map { line ->
        line.map { it.toDigit() }
    }.toMutableGrid()

    var step = 1
    while (true) {
        grid.forEachIndexed { rowIndex, colIndex, _ -> grid[rowIndex, colIndex]++ }

        val flashed = mutableSetOf<Point>()
        while (
            grid.shallowRows().withIndex().any { (rowIndex, row) ->
                row.withIndex().any { (colIndex, value) ->
                    Point.fromMatrixCoordinates(rowIndex, colIndex) !in flashed && value > 9
                }
            }
        ) {
            grid.forEachPointValue { point, value ->
                if (point !in flashed && value > 9) {
                    flashed.add(point)
                    point.allNeighbors().forEach { neighbor ->
                        if (neighbor in grid) {
                            grid[neighbor]++
                        }
                    }
                }
            }
        }

        if (flashed.size == grid.size) {
            return step
        }

        flashed.forEach { grid[it] = 0 }
        step++
    }
}

fun main() {
    println(solve())
}
