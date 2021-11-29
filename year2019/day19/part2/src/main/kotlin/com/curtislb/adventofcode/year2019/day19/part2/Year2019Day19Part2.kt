/*
--- Part Two ---

You aren't sure how large Santa's ship is. You aren't even sure if you'll need to use this thing on
Santa's ship, but it doesn't hurt to be prepared. You figure Santa's ship might fit in a 100x100
square.

The beam gets wider as it travels away from the emitter; you'll need to be a minimum distance away
to fit a square of that size into the beam fully. (Don't rotate the square; it should be aligned to
the same axes as the drone grid.)

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

In this example, the 10x10 square closest to the emitter that fits entirely within the tractor beam
has been marked O. Within it, the point closest to the emitter (the only highlighted O) is at X=25,
Y=20.

Find the 100x100 square closest to the emitter that fits entirely within the tractor beam; within
that square, find the point closest to the emitter. What value do you get if you take that point's X
coordinate, multiply it by 10000, then add the point's Y coordinate? (In the example above, this
would be 250020.)
*/

package com.curtislb.adventofcode.year2019.day19.part2

import com.curtislb.adventofcode.common.search.bisectIndex
import com.curtislb.adventofcode.year2019.day19.drone.DroneSystem
import java.math.BigInteger
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.system.measureTimeMillis

/**
 * Returns the solution to the puzzle for 2019, day 19, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param shipSize The width and height of the square ship to fit within the beam.
 * @param positionXFactor The number by which to multiply the x-coordinate of the final position.
 */
fun solve(
    inputPath: Path = Paths.get("..", "input", "input.txt"),
    shipSize: BigInteger = BigInteger("100"),
    positionXFactor: BigInteger = BigInteger("10000")
): BigInteger? {
    // Find the first row that allows the ship to fit in the beam.
    val droneSystem = DroneSystem(inputPath.toFile())
    val shipRowDelta = shipSize - BigInteger.ONE
    val targetIndex = bisectIndex { index ->
        val overlap = droneSystem.findBeamOverlap(index.toBigInteger(), shipRowDelta)
        overlap.size >= shipSize
    }

    return if (targetIndex != null) {
        // Find the leftmost valid position for the ship in the row.
        val topRowIndex = targetIndex.toBigInteger()
        val overlap = droneSystem.findBeamOverlap(topRowIndex, shipRowDelta)
        overlap.start * positionXFactor + topRowIndex
    } else {
        null
    }
}

fun main() = when (val solution = solve()) {
    null -> println("Unable to find a large enough area.")
    else -> println(solution)
}
