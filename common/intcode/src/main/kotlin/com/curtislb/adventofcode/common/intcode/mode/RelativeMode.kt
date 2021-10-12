package com.curtislb.adventofcode.common.intcode.mode

import com.curtislb.adventofcode.common.intcode.Intcode
import java.math.BigInteger

/**
 * A mode in which a parameter is interpreted as a position in an [Intcode] program relative to the
 * current value of [Intcode.relativeBase].
 */
object RelativeMode : Mode {
    override fun getValue(intcode: Intcode, parameter: BigInteger): BigInteger {
        return intcode[intcode.relativeBase + parameter.toInt()]
    }

    override fun setValue(intcode: Intcode, parameter: BigInteger, value: BigInteger) {
        intcode[intcode.relativeBase + parameter.toInt()] = value
    }
}
