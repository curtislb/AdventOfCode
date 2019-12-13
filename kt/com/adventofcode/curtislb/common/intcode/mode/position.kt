package com.adventofcode.curtislb.common.intcode.mode

import com.adventofcode.curtislb.common.intcode.Intcode

/**
 * Gives the value of a parameter in position mode ([Mode.POSITION]).
 * @param intcode The currently running Intcode program.
 * @param param The parameter whose value will be given.
 * @return The value at the position in [intcode] given by [param].
 */
fun applyPosition(intcode: Intcode, param: Int): Int = intcode[param]
