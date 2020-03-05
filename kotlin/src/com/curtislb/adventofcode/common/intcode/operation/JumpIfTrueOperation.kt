package com.curtislb.adventofcode.common.intcode.operation

import com.curtislb.adventofcode.common.intcode.Intcode
import com.curtislb.adventofcode.common.intcode.mode.Mode
import java.math.BigInteger

/**
 * Represents the jump-if-true operation (opcode 5) for an [Intcode] program.
 *
 * This operation takes 2 parameters: `A` and `B`. It first checks if the value of `A` is non-zero. If it is, then this
 * operation moves the instruction pointer to the position given by `B`. Otherwise, it advances the pointer as normal.
 */
object JumpIfTrueOperation : Operation {
    override val parameterCount: Int = 2

    override fun process(intcode: Intcode, pointer: Int, parameters: Array<BigInteger>, modes: Array<Mode>): Int {
        assert(parameters.size == parameterCount) { "Expected $parameterCount parameters, but got ${parameters.size}" }
        assert(modes.size == parameterCount) { "Expected $parameterCount modes, but got ${modes.size}" }

        val (condition, position) = (0..1).map { modes[it].getValue(intcode, parameters[it]) }
        return if (condition != BigInteger.ZERO) position.toInt() else pointer + parameterCount + 1
    }
}
