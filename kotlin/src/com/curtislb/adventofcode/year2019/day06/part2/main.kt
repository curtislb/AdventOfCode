/*
--- Part Two ---

Now, you just need to figure out how many orbital transfers you (YOU) need to take to get to Santa (SAN).

You start at the object YOU are orbiting; your destination is the object SAN is orbiting. An orbital transfer lets you
move from any object to an object orbiting or orbited by that object.

For example, suppose you have the following map:

COM)B
B)C
C)D
D)E
E)F
B)G
G)H
D)I
E)J
J)K
K)L
K)YOU
I)SAN

Visually, the above map of orbits looks like this:

                          YOU
                         /
        G - H       J - K - L
       /           /
COM - B - C - D - E - F
               \
                I - SAN

In this example, YOU are in orbit around K, and SAN is in orbit around I. To move from K to I, a minimum of 4 orbital
transfers are required:

  - K to J
  - J to E
  - E to D
  - D to I

Afterward, the map of orbits looks like this:

        G - H       J - K - L
       /           /
COM - B - C - D - E - F
               \
                I - SAN
                 \
                  YOU

What is the minimum number of orbital transfers required to move from the object YOU are orbiting to the object SAN is
orbiting? (Between the objects they are orbiting - not between YOU and SAN.)
*/

package com.curtislb.adventofcode.year2019.day06.part2

import com.curtislb.adventofcode.common.io.pathToInput
import com.curtislb.adventofcode.year2019.day06.orbits.Universe
import java.nio.file.Path

/**
 * Returns the solution to the puzzle for day 6, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param start The name of the node representing our starting location.
 * @param target The name of the node representing the location of our target.
 */
fun solve(
    inputPath: Path = pathToInput(year = 2019, day = 6),
    start: String = "YOU",
    target: String = "SAN"
): Int? {
    val universe = Universe(inputPath.toFile())
    return universe.findOrbitalTransferDistance(start, target)
}

// Answer: 499
fun main() = when (val solution = solve()) {
    null -> println("No orbital transfer path found.")
    else -> println(solution)
}
