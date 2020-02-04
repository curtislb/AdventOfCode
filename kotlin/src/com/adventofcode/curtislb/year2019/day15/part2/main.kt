/*
--- Part Two ---

You quickly repair the oxygen system; oxygen gradually fills the area.

Oxygen starts in the location containing the repaired oxygen system. It takes one minute for oxygen to spread to all
open locations that are adjacent to a location that already contains oxygen. Diagonal locations are not adjacent.

In the example above, suppose you've used the droid to explore the area fully and have the following map (where
locations that currently contain oxygen are marked O):

 ##
#..##
#.#..#
#.O.#
 ###

Initially, the only location which contains oxygen is the location of the repaired oxygen system. However, after one
minute, the oxygen spreads to all open (.) locations that are adjacent to a location containing oxygen:

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

Use the repair droid to get a complete map of the area. How many minutes will it take to fill with oxygen?
*/

package com.adventofcode.curtislb.year2019.day15.part2

import com.adventofcode.curtislb.common.graph.bfsApply
import com.adventofcode.curtislb.common.io.pathToInput
import com.adventofcode.curtislb.year2019.day15.repair.Droid

private val INPUT_PATH = pathToInput(year = 2019, day = 15)

// Answer: 364
fun main() {
    // Have the droid fully explore the grid.
    val droid = Droid(INPUT_PATH.toFile())
    droid.explore()

    // Get the position of the oxygen system.
    val oxygenStartPosition = droid.goalPosition
    if (oxygenStartPosition == null) {
        println("Unable to find oxygen system.")
        return
    }

    // Use BFS to find the furthest open space from the oxygen system.
    var maxDistance = 0L
    bfsApply(
        start = oxygenStartPosition,
        getNeighbors = { droid.adjacentOpenSpaces(it).asSequence() }
    ) { _, distance ->
        maxDistance = maxDistance.coerceAtLeast(distance)
        false // Not done searching.
    }
    println(maxDistance)
}
