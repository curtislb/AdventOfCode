package com.curtislb.adventofcode.common.intcode.mode

import com.curtislb.adventofcode.common.intcode.Intcode
import java.math.BigInteger

/**
 * A mode in which a parameter is interpreted as its own value.
 *
 * Due to the nature of this mode, [ImmediateMode.setValue] is not supported.
 */
object ImmediateMode : Mode {
    override fun getValue(intcode: Intcode, parameter: BigInteger): BigInteger = parameter

    override fun setValue(intcode: Intcode, parameter: BigInteger, value: BigInteger) {
        throw UnsupportedOperationException("Can't set the value of a parameter in immediate mode.")
    }
}
