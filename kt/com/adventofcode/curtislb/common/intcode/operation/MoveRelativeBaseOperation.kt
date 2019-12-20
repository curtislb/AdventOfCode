package com.adventofcode.curtislb.common.intcode.operation

import com.adventofcode.curtislb.common.intcode.Intcode
import com.adventofcode.curtislb.common.intcode.mode.Mode
import java.math.BigInteger

/**
 * Represents the move-relative-base operation (opcode 9) for an [Intcode] program.
 *
 * This operation takes 1 parameter: `A`. It first gets the value of `A` and then increases the current value of
 * [Intcode.relativeBase] by that amount (or decreases it if the value of `A` is negative).
 */
object MoveRelativeBaseOperation : Operation {
    override val parameterCount: Int = 1

    override fun process(intcode: Intcode, cursor: Int, parameters: Array<BigInteger>, modes: Array<Mode>): Int {
        assert(parameters.size == parameterCount) { "Expected $parameterCount parameters, but got ${parameters.size}" }
        assert(modes.size == parameterCount) { "Expected $parameterCount modes, but got ${modes.size}" }

        val value = modes[0].getValue(intcode, parameters[0])
        intcode.relativeBase += value.toInt()

        return cursor + parameterCount + 1
    }
}
