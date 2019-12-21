package com.adventofcode.curtislb.common.intcode.mode

import com.adventofcode.curtislb.common.intcode.Intcode
import com.adventofcode.curtislb.common.intcode.mode.PositionMode.getValue
import com.adventofcode.curtislb.common.intcode.mode.PositionMode.setValue
import java.math.BigInteger

/**
 * An [Intcode] parameter mode for getting the relative positional value of a parameter.
 *
 * In this [Mode], a parameter is interpreted as a position in an [Intcode] program relative to the current value of
 * [Intcode.relativeBase]. Hence, [getValue] returns the value at that position, and [setValue] sets it.
 */
object RelativeMode : Mode {
    override fun getValue(intcode: Intcode, parameter: BigInteger): BigInteger {
        return intcode[intcode.relativeBase + parameter.toInt()]
    }

    override fun setValue(intcode: Intcode, parameter: BigInteger, value: BigInteger) {
        intcode[intcode.relativeBase + parameter.toInt()] = value
    }
}