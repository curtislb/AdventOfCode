package com.adventofcode.curtislb.common.intcode.mode

import com.adventofcode.curtislb.common.intcode.Intcode
import java.math.BigInteger

/**
 * An [Intcode] parameter mode for getting the immediate value of a parameter.
 *
 * In this [Mode], a parameter is interpreted as being its own value. Thus, [ImmediateMode.getValue] returns the
 * parameter directly. Due to the nature of this [Mode], [ImmediateMode.setValue] is not supported.
 */
object ImmediateMode : Mode {
    override fun getValue(intcode: Intcode, parameter: BigInteger): BigInteger = parameter

    override fun setValue(intcode: Intcode, parameter: BigInteger, value: BigInteger) {
        throw UnsupportedOperationException("Can't set the value of a parameter in immediate mode.")
    }
}
