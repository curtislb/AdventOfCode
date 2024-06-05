/*
--- Part Two ---

Disintegrating bricks one at a time isn't going to be fast enough. While it might sound dangerous,
what you really need is a chain reaction.

You'll need to figure out the best brick to disintegrate. For each brick, determine how many other
bricks would fall if that brick were disintegrated.

Using the same example as above:

- Disintegrating brick `A` would cause all 6 other bricks to fall.
- Disintegrating brick `F` would cause only 1 other brick, `G`, to fall.

Disintegrating any other brick would cause no other bricks to fall. So, in this example, the sum of
the number of other bricks that would fall as a result of disintegrating each brick is 7.

For each brick, determine how many other bricks would fall if that brick were disintegrated. What is
the sum of the number of other bricks that would fall?
*/

package com.curtislb.adventofcode.year2023.day22.part2

import com.curtislb.adventofcode.year2023.day22.bricks.FallingBricks
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2023, day 22, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): Int {
    val fallingBricks = FallingBricks.fromFile(inputPath.toFile())
    return fallingBricks.sumSupportedBricks()
}

fun main() {
    println(solve())
}
