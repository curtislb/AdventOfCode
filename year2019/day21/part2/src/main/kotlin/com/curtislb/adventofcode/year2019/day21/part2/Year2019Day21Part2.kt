/*
--- Part Two ---

There are many areas the springdroid can't reach. You flip through the manual and discover a way to
increase its sensor range.

Instead of ending your springscript program with WALK, use RUN. Doing this will enable extended
sensor mode, capable of sensing ground up to nine tiles away. This data is available in five new
read-only registers:

- Register E indicates whether there is ground five tiles away.
- Register F indicates whether there is ground six tiles away.
- Register G indicates whether there is ground seven tiles away.
- Register H indicates whether there is ground eight tiles away.
- Register I indicates whether there is ground nine tiles away.

All other functions remain the same.

Successfully survey the rest of the hull by ending your program with RUN. What amount of hull damage
does the springdroid now report?
*/

package com.curtislb.adventofcode.year2019.day21.part2

import com.curtislb.adventofcode.year2019.day21.springdroid.Register.A
import com.curtislb.adventofcode.year2019.day21.springdroid.Register.B
import com.curtislb.adventofcode.year2019.day21.springdroid.Register.C
import com.curtislb.adventofcode.year2019.day21.springdroid.Register.D
import com.curtislb.adventofcode.year2019.day21.springdroid.Register.E
import com.curtislb.adventofcode.year2019.day21.springdroid.Register.F
import com.curtislb.adventofcode.year2019.day21.springdroid.Register.H
import com.curtislb.adventofcode.year2019.day21.springdroid.Register.J
import com.curtislb.adventofcode.year2019.day21.springdroid.Register.T
import com.curtislb.adventofcode.year2019.day21.springdroid.SpringDroid
import com.curtislb.adventofcode.year2019.day21.springdroid.SpringScript
import java.math.BigInteger
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2019, day 21, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param maxInstructions The maximum number of springscript instructions for the springdroid.
 */
fun solve(
    inputPath: Path = Paths.get("..", "input", "input.txt"),
    maxInstructions: Int = 15
): BigInteger? {
    val droid = SpringDroid(inputPath.toFile(), maxInstructions)
    val script = SpringScript.create(extendedMode = true) {
        not(A, T)
        or(T, J)
        not(B, T)
        or(T, J)
        not(C, T)
        or(T, J)
        and(D, J)
        not(F, T)
        and(F, T)
        or(E, T)
        or(H, T)
        and(T, J)
    }
    return droid.runProgram(script)
}

fun main() = when (val solution = solve()) {
    null -> println("No output produced by the springdroid.")
    else -> println(solution)
}
