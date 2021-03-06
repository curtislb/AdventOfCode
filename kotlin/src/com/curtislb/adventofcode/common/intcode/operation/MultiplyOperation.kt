package com.curtislb.adventofcode.common.intcode.operation

import com.curtislb.adventofcode.common.intcode.Intcode
import com.curtislb.adventofcode.common.intcode.mode.Mode
import java.math.BigInteger

/**
 * Represents the multiply operation (opcode 2) for an [Intcode] program.
 *
 * This operation takes 3 parameters: `A`, `B`, and `C`. It first finds the product `A * B` and then stores the result
 * at the position given by `C`.
 */
object MultiplyOperation : Operation {
    override val parameterCount: Int = 3

    override fun process(intcode: Intcode, pointer: Int, parameters: Array<BigInteger>, modes: Array<Mode>): Int {
        require(parameters.size == parameterCount) { "Wanted $parameterCount parameters, but got ${parameters.size}." }
        require(modes.size == parameterCount) { "Wanted $parameterCount modes, but got ${modes.size}." }

        val (operand1, operand2) = (0..1).map { modes[it].getValue(intcode, parameters[it]) }
        modes[2].setValue(intcode, parameters[2], operand1 * operand2)

        return pointer + parameterCount + 1
    }
}
