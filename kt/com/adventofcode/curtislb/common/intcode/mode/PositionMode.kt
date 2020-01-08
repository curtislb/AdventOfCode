package com.adventofcode.curtislb.common.intcode.mode

import com.adventofcode.curtislb.common.intcode.Intcode
import java.math.BigInteger

/**
 * An [Intcode] parameter mode for getting the positional value of a parameter.
 *
 * In this [Mode], a parameter is interpreted as an absolute position in an [Intcode] program. Hence,
 * [PositionMode.getValue] returns the value at that position, and [PositionMode.setValue] sets it.
 */
object PositionMode : Mode {
    override fun getValue(intcode: Intcode, parameter: BigInteger): BigInteger = intcode[parameter.toInt()]

    override fun setValue(intcode: Intcode, parameter: BigInteger, value: BigInteger) {
        intcode[parameter.toInt()] = value
    }
}
