package com.adventofcode.curtislb.common.intcode.operation

import com.adventofcode.curtislb.common.intcode.Intcode
import com.adventofcode.curtislb.common.intcode.mode.Mode
import java.math.BigInteger

/**
 * Represents the output operation (opcode 4) for an [Intcode] program.
 *
 * This operation takes 1 parameter: `A`. It first gets the value of `A` and then produces it as output by invoking
 * [Intcode.onOutput].
 */
object OutputOperation : Operation {
    override val parameterCount: Int = 1

    override fun process(intcode: Intcode, cursor: Int, parameters: Array<BigInteger>, modes: Array<Mode>): Int {
        assert(parameters.size == parameterCount) { "Expected $parameterCount parameters, but got ${parameters.size}" }
        assert(modes.size == parameterCount) { "Expected $parameterCount modes, but got ${modes.size}" }

        val value = modes[0].getValue(intcode, parameters[0])
        intcode.onOutput(value)

        return cursor + parameterCount + 1
    }
}
