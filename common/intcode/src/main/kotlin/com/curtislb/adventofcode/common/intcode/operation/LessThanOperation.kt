package com.curtislb.adventofcode.common.intcode.operation

import com.curtislb.adventofcode.common.intcode.Intcode
import com.curtislb.adventofcode.common.intcode.mode.Mode
import java.math.BigInteger

/**
 * Represents the less-than operation (opcode 7) for an [Intcode] program.
 *
 * This operation takes 3 parameters: `A`, `B`, and `C`. It first checks the condition `A < B`. If
 * the condition is `true`, this operation then stores the value 1 at the position given by `C`.
 * Otherwise, it stores the value 0 at that position.
 */
object LessThanOperation : Operation {
    override val parameterCount: Int = 3

    override fun process(
        intcode: Intcode,
        pointer: Int,
        parameters: Array<BigInteger>,
        modes: Array<Mode>
    ): Int {
        checkParameters(parameters)
        checkModes(modes)

        val (operand1, operand2) = (0..1).map { modes[it].getValue(intcode, parameters[it]) }
        modes[2].setValue(
            intcode,
            parameters[2],
            if (operand1 < operand2) BigInteger.ONE else BigInteger.ZERO
        )

        return pointer + parameterCount + 1
    }
}
