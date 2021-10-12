/*
--- Part Two ---

Ding! The "fasten seat belt" signs have turned on. Time to find your seat.

It's a completely full flight, so your seat should be the only missing boarding pass in your list.
However, there's a catch: some of the seats at the very front and back of the plane don't exist on
this aircraft, so they'll be missing from your list as well.

Your seat wasn't at the very front or back, though; the seats with IDs +1 and -1 from yours will be
in your list.

What is the ID of your seat?
*/

package com.curtislb.adventofcode.year2020.day05.part2

import com.curtislb.adventofcode.year2020.day05.boarding.Seat
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2020, day 5, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): Int? {
    val file = inputPath.toFile()
    val seatIDs = mutableListOf<Int>()
    file.forEachLine { line ->
        val seat = Seat.from(line)
        seatIDs.add(seat.id)
    }

    // Search through sorted seat IDs to find the first gap.
    seatIDs.sort()
    seatIDs.forEachIndexed { index, seatId ->
        val nextId = seatId + 1
        if (nextId < seatIDs[index + 1]) {
            return nextId
        }
    }

    // No missing seat ID found.
    return null
}

fun main() = when (val solution = solve()) {
    null -> println("No solution found.")
    else -> println(solution)
}
