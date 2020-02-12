/*
--- Part Two ---

You aren't sure how large Santa's ship is. You aren't even sure if you'll need to use this thing on Santa's ship, but
it doesn't hurt to be prepared. You figure Santa's ship might fit in a 100x100 square.

The beam gets wider as it travels away from the emitter; you'll need to be a minimum distance away to fit a square of
that size into the beam fully. (Don't rotate the square; it should be aligned to the same axes as the drone grid.)

For example, suppose you have the following tractor beam readings:

#.......................................
.#......................................
..##....................................
...###..................................
....###.................................
.....####...............................
......#####.............................
......######............................
.......#######..........................
........########........................
.........#########......................
..........#########.....................
...........##########...................
...........############.................
............############................
.............#############..............
..............##############............
...............###############..........
................###############.........
................#################.......
.................########OOOOOOOOOO.....
..................#######OOOOOOOOOO#....
...................######OOOOOOOOOO###..
....................#####OOOOOOOOOO#####
.....................####OOOOOOOOOO#####
.....................####OOOOOOOOOO#####
......................###OOOOOOOOOO#####
.......................##OOOOOOOOOO#####
........................#OOOOOOOOOO#####
.........................OOOOOOOOOO#####
..........................##############
..........................##############
...........................#############
............................############
.............................###########

In this example, the 10x10 square closest to the emitter that fits entirely within the tractor beam has been marked O.
Within it, the point closest to the emitter (the only highlighted O) is at X=25, Y=20.

Find the 100x100 square closest to the emitter that fits entirely within the tractor beam; within that square, find the
point closest to the emitter. What value do you get if you take that point's X coordinate, multiply it by 10000, then
add the point's Y coordinate? (In the example above, this would be 250020.)
*/

package com.curtislb.adventofcode.year2019.day19.part2

import com.curtislb.adventofcode.common.io.pathToInput
import com.curtislb.adventofcode.common.search.bisectIndex
import com.curtislb.adventofcode.year2019.day19.drone.DroneSystem
import java.math.BigInteger

/**
 * The path to the input file for this puzzle.
 */
private val INPUT_PATH = pathToInput(year = 2019, day = 19)

/**
 * The width and height of the square ship to fit within the beam.
 */
private val SHIP_SIZE = BigInteger("100")

/**
 * The number by which the x-coordinate of the final position should be multiplied.
 */
private val POSITION_X_FACTOR = BigInteger("10000")

// Answer: 13530764
fun main() {
    // Find the first row that allows the ship to fit in the beam.
    val droneSystem = DroneSystem(INPUT_PATH.toFile())
    val shipRowDelta = SHIP_SIZE - BigInteger.ONE
    val targetIndex = bisectIndex { index ->
        val overlap = droneSystem.findBeamOverlap(index.toBigInteger(), shipRowDelta)
        overlap.size >= SHIP_SIZE
    }

    if (targetIndex != null) {
        // Find the leftmost valid position for the ship in the row.
        val topRowIndex = targetIndex.toBigInteger()
        val overlap = droneSystem.findBeamOverlap(topRowIndex, shipRowDelta)
        println(overlap.start * POSITION_X_FACTOR + topRowIndex)
    } else {
        println("Failed to find large enough area.")
    }
}
