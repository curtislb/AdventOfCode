/*
--- Part Two ---

You now have a complete Intcode computer.

Finally, you can lock on to the Ceres distress signal! You just need to boost your sensors using the BOOST program.

The program runs in sensor boost mode by providing the input instruction the value 2. Once run, it will boost the
sensors automatically, but it might take a few seconds to complete the operation on slower hardware. In sensor boost
mode, the program will output a single value: the coordinates of the distress signal.

Run the BOOST program in sensor boost mode. What are the coordinates of the distress signal?
*/

package com.curtislb.adventofcode.year2019.day09.part2

import com.curtislb.adventofcode.common.intcode.Intcode
import com.curtislb.adventofcode.common.io.pathToInput
import java.math.BigInteger

/**
 * The path to the input file for this puzzle.
 */
private val INPUT_PATH = pathToInput(year = 2019, day = 9)

/**
 * A code that causes the program to run in sensor boost mode when provided as input.
 */
private val SENSOR_BOOST_MODE = BigInteger.TWO

// Answer: 33343
fun main() {
    val intcode = Intcode(INPUT_PATH.toFile())
    intcode.sendInput(SENSOR_BOOST_MODE)
    intcode.run()
}
