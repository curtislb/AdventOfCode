package com.curtislb.adventofcode.common.intcode.operation

import com.curtislb.adventofcode.common.intcode.Intcode
import com.curtislb.adventofcode.common.intcode.mode.Mode
import java.math.BigInteger

/**
 * Represents the input operation (opcode 3) for an [Intcode] program.
 *
 * This operation takes 1 parameter: `A`. It first reads the next value from [Intcode.input] and then stores it at the
 * position given by `A`.
 *
 * If [Intcode.input] has no next value, this operation immediately returns without moving the cursor, so that the
 * program can be paused.
 */
object InputOperation : Operation {
    override val parameterCount: Int = 1

    override fun process(intcode: Intcode, cursor: Int, parameters: Array<BigInteger>, modes: Array<Mode>): Int {
        assert(parameters.size == parameterCount) { "Expected $parameterCount parameters, but got ${parameters.size}" }
        assert(modes.size == parameterCount) { "Expected $parameterCount modes, but got ${modes.size}" }

        val inputValue = intcode.nextInput() ?: return cursor
        modes[0].setValue(intcode, parameters[0], inputValue)

        return cursor + parameterCount + 1
    }
}
