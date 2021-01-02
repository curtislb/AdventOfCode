package com.curtislb.adventofcode.year2020.day08.bootcode.operation

import com.curtislb.adventofcode.year2020.day08.bootcode.State

/**
 * Represents the jump operation (`jmp`) for a boot code program.
 *
 * This operation adds the given argument (which may be negative) to the current instruction pointer.
 */
object JumpOperation : Operation {
    override fun execute(argument: Int, state: State): State {
        return state.copy(pointer = state.pointer + argument)
    }
}
