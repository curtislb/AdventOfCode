package com.curtislb.adventofcode.year2020.day08.bootcode.operation

import com.curtislb.adventofcode.year2020.day08.bootcode.State

/**
 * Represents the "no" operation (`nop`) for a boot code program.
 *
 * This operation simply advances the pointer to the next instruction.
 */
object NoOperation : Operation {
    override fun execute(argument: Int, state: State): State = state.copy(pointer = state.pointer + 1)
}
