/*
--- Part Two ---

You quickly repair the oxygen system; oxygen gradually fills the area.

Oxygen starts in the location containing the repaired oxygen system. It takes one minute for oxygen
to spread to all open locations that are adjacent to a location that already contains oxygen.
Diagonal locations are not adjacent.

In the example above, suppose you've used the droid to explore the area fully and have the following
map (where locations that currently contain oxygen are marked O):

 ##
#..##
#.#..#
#.O.#
 ###

Initially, the only location which contains oxygen is the location of the repaired oxygen system.
However, after one minute, the oxygen spreads to all open (.) locations that are adjacent to a
location containing oxygen:

 ##
#..##
#.#..#
#OOO#
 ###

After a total of two minutes, the map looks like this:

 ##
#..##
#O#O.#
#OOO#
 ###

After a total of three minutes:

 ##
#O.##
#O#OO#
#OOO#
 ###

And finally, the whole region is full of oxygen after a total of four minutes:

 ##
#OO##
#O#OO#
#OOO#
 ###

So, in this example, all locations contain oxygen after 4 minutes.

Use the repair droid to get a complete map of the area. How many minutes will it take to fill with
oxygen?
*/

package com.curtislb.adventofcode.year2019.day15.part2

import com.curtislb.adventofcode.year2019.day15.repair.Droid
import com.curtislb.adventofcode.year2019.day15.repair.DroidSearch
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.math.max

/**
 * Returns the solution to the puzzle for 2019, day 15, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): Long? {
    // Have the droid fully explore the grid
    val droid = Droid(inputPath.toFile()).apply { explore() }

    // Get the position of the oxygen system
    val oxygenStartPosition = droid.goalPosition ?: return null

    // Use BFS to find the furthest open space from the oxygen system
    var maxDistance = 0L
    val search = DroidSearch(droid)
    search.forEachNode(source = oxygenStartPosition) { _, distance ->
        maxDistance = max(maxDistance, distance)
        false // Not done searching
    }

    return maxDistance
}

fun main() = when (val solution = solve()) {
    null -> println("Unable to find the oxygen system.")
    else -> println(solution)
}
