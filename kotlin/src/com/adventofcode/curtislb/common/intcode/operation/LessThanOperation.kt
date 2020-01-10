package com.adventofcode.curtislb.common.intcode.operation

import com.adventofcode.curtislb.common.intcode.Intcode
import com.adventofcode.curtislb.common.intcode.mode.Mode
import java.math.BigInteger

/**
 * Represents the less-than operation (opcode 7) for an [Intcode] program.
 *
 * This operation takes 3 parameters: `A`, `B`, and `C`. It first checks the condition `A < B`. If the condition is
 * `true`, this [Operation] then stores the value 1 at the position given by `C`. Otherwise, it stores the value 0 at
 * that position.
 */
object LessThanOperation : Operation {
    override val parameterCount: Int = 3

    override fun process(intcode: Intcode, cursor: Int, parameters: Array<BigInteger>, modes: Array<Mode>): Int {
        assert(parameters.size == parameterCount) { "Expected $parameterCount parameters, but got ${parameters.size}" }
        assert(modes.size == parameterCount) { "Expected $parameterCount modes, but got ${modes.size}" }

        val (operand1, operand2) = (0..1).map { modes[it].getValue(intcode, parameters[it]) }
        modes[2].setValue(intcode, parameters[2], if (operand1 < operand2) BigInteger.ONE else BigInteger.ZERO)

        return cursor + parameterCount + 1
    }
}
