package com.adventofcode.curtislb.common.intcode.operation

import com.adventofcode.curtislb.common.intcode.Intcode
import com.adventofcode.curtislb.common.intcode.mode.Mode
import java.math.BigInteger

/**
 * Represents the stop operation (opcode 99) for an [Intcode] program.
 *
 * This operation takes no parameters. It indicates that the currently running [Intcode] program should halt by moving
 * the program cursor to an invalid position.
 */
object StopOperation : Operation {
    override val parameterCount: Int = 0

    override fun process(intcode: Intcode, cursor: Int, parameters: Array<BigInteger>, modes: Array<Mode>): Int {
        assert(parameters.size == parameterCount) { "Expected $parameterCount parameters, but got ${parameters.size}" }
        assert(modes.size == parameterCount) { "Expected $parameterCount modes, but got ${modes.size}" }
        return -1
    }
}
