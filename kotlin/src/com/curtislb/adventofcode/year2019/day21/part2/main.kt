/*
--- Part Two ---

There are many areas the springdroid can't reach. You flip through the manual and discover a way to increase its sensor
range.

Instead of ending your springscript program with WALK, use RUN. Doing this will enable extended sensor mode, capable of
sensing ground up to nine tiles away. This data is available in five new read-only registers:

  - Register E indicates whether there is ground five tiles away.
  - Register F indicates whether there is ground six tiles away.
  - Register G indicates whether there is ground seven tiles away.
  - Register H indicates whether there is ground eight tiles away.
  - Register I indicates whether there is ground nine tiles away.

All other functions remain the same.

Successfully survey the rest of the hull by ending your program with RUN. What amount of hull damage does the
springdroid now report?
*/

package com.curtislb.adventofcode.year2019.day21.part2

import com.curtislb.adventofcode.common.io.pathToInput
import com.curtislb.adventofcode.year2019.day21.spring.Register.A
import com.curtislb.adventofcode.year2019.day21.spring.Register.B
import com.curtislb.adventofcode.year2019.day21.spring.Register.C
import com.curtislb.adventofcode.year2019.day21.spring.Register.D
import com.curtislb.adventofcode.year2019.day21.spring.Register.E
import com.curtislb.adventofcode.year2019.day21.spring.Register.F
import com.curtislb.adventofcode.year2019.day21.spring.Register.H
import com.curtislb.adventofcode.year2019.day21.spring.Register.J
import com.curtislb.adventofcode.year2019.day21.spring.Register.T
import com.curtislb.adventofcode.year2019.day21.spring.SpringDroid
import com.curtislb.adventofcode.year2019.day21.spring.SpringScript

/**
 * The path to the input file for this puzzle.
 */
private val INPUT_PATH = pathToInput(year = 2019, day = 21)

/**
 * The maximum number of springscript instructions that the springdroid can remember.
 */
private const val MAX_INSTRUCTIONS = 15

// Answer: 1142830249
fun main() {
    val droid = SpringDroid(INPUT_PATH.toFile(), MAX_INSTRUCTIONS)
    droid.runProgram(
        SpringScript.builder(enableExtendedMode = true)
            .not(A, T)
            .or(T, J)
            .not(B, T)
            .or(T, J)
            .not(C, T)
            .or(T, J)
            .and(D, J)
            .not(F, T)
            .and(F, T)
            .or(E, T)
            .or(H, T)
            .and(T, J)
            .build()
    )
}
