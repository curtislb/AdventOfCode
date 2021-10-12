package com.curtislb.adventofcode.year2020.day08.bootcode.operation

import com.curtislb.adventofcode.year2020.day08.bootcode.State

/**
 * Represents the accumulate operation (`acc`) for a boot code program.
 *
 * This operation adds the given argument (which may be negative) to the program accumulator and
 * advances the pointer to the next instruction.
 */
object AccumulateOperation : Operation {
    override fun execute(argument: Int, state: State): State {
        return state.copy(accumulator = state.accumulator + argument, pointer = state.pointer + 1)
    }
}
