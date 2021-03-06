/*
--- Part Two ---

All this drifting around in space makes you wonder about the nature of the universe. Does history really repeat itself?
You're curious whether the moons will ever return to a previous state.

Determine the number of steps that must occur before all of the moons' positions and velocities exactly match a
previous point in time.

For example, the first example above takes 2772 steps before they exactly match a previous point in time; it eventually
returns to the initial state:

After 0 steps:
pos=<x= -1, y=  0, z=  2>, vel=<x=  0, y=  0, z=  0>
pos=<x=  2, y=-10, z= -7>, vel=<x=  0, y=  0, z=  0>
pos=<x=  4, y= -8, z=  8>, vel=<x=  0, y=  0, z=  0>
pos=<x=  3, y=  5, z= -1>, vel=<x=  0, y=  0, z=  0>

After 2770 steps:
pos=<x=  2, y= -1, z=  1>, vel=<x= -3, y=  2, z=  2>
pos=<x=  3, y= -7, z= -4>, vel=<x=  2, y= -5, z= -6>
pos=<x=  1, y= -7, z=  5>, vel=<x=  0, y= -3, z=  6>
pos=<x=  2, y=  2, z=  0>, vel=<x=  1, y=  6, z= -2>

After 2771 steps:
pos=<x= -1, y=  0, z=  2>, vel=<x= -3, y=  1, z=  1>
pos=<x=  2, y=-10, z= -7>, vel=<x= -1, y= -3, z= -3>
pos=<x=  4, y= -8, z=  8>, vel=<x=  3, y= -1, z=  3>
pos=<x=  3, y=  5, z= -1>, vel=<x=  1, y=  3, z= -1>

After 2772 steps:
pos=<x= -1, y=  0, z=  2>, vel=<x=  0, y=  0, z=  0>
pos=<x=  2, y=-10, z= -7>, vel=<x=  0, y=  0, z=  0>
pos=<x=  4, y= -8, z=  8>, vel=<x=  0, y=  0, z=  0>
pos=<x=  3, y=  5, z= -1>, vel=<x=  0, y=  0, z=  0>

Of course, the universe might last for a very long time before repeating. Here's a copy of the second example from
above:

<x=-8, y=-10, z=0>
<x=5, y=5, z=10>
<x=2, y=-7, z=3>
<x=9, y=-8, z=-3>

This set of initial positions takes 4686774924 steps before it repeats a previous state! Clearly, you might need to
find a more efficient way to simulate the universe.

How many steps does it take to reach the first state that exactly matches a previous state?
*/

package com.curtislb.adventofcode.year2019.day12.part2

import com.curtislb.adventofcode.common.io.pathToInput
import com.curtislb.adventofcode.common.math.leastCommonMultiple
import com.curtislb.adventofcode.year2019.day12.body.NBodySystem
import java.nio.file.Path

/**
 * Returns the solution to the puzzle for 2019, day 12, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = pathToInput(year = 2019, day = 12)): Long {
    val system = NBodySystem(inputPath.toFile())
    val periodicity = system.findAxialPeriodicity()
    return leastCommonMultiple(periodicity[0].toLong(), periodicity[1].toLong(), periodicity[2].toLong())
}

fun main() {
    println(solve())
}
