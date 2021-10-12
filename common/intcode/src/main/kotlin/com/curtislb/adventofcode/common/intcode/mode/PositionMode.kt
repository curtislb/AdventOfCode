package com.curtislb.adventofcode.common.intcode.mode

import com.curtislb.adventofcode.common.intcode.Intcode
import java.math.BigInteger

/**
 * A mode in which a parameter is interpreted as an absolute position in an [Intcode] program.
 */
object PositionMode : Mode {
    override fun getValue(intcode: Intcode, parameter: BigInteger): BigInteger {
        return intcode[parameter.toInt()]
    }

    override fun setValue(intcode: Intcode, parameter: BigInteger, value: BigInteger) {
        intcode[parameter.toInt()] = value
    }
}
