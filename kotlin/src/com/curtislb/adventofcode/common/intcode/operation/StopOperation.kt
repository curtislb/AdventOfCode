package com.curtislb.adventofcode.common.intcode.operation

import com.curtislb.adventofcode.common.intcode.Intcode
import com.curtislb.adventofcode.common.intcode.mode.Mode
import java.math.BigInteger

/**
 * Represents the stop operation (opcode 99) for an [Intcode] program.
 *
 * This operation takes no parameters. It indicates that the currently running program should halt by moving its
 * instruction pointer to an invalid position.
 */
object StopOperation : Operation {
    override val parameterCount: Int = 0

    override fun process(intcode: Intcode, pointer: Int, parameters: Array<BigInteger>, modes: Array<Mode>): Int {
        require(parameters.size == parameterCount) { "Wanted $parameterCount parameters, but got ${parameters.size}." }
        require(modes.size == parameterCount) { "Wanted $parameterCount modes, but got ${modes.size}." }
        return -1
    }
}
