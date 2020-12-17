package com.curtislb.adventofcode.year2020.day08.bootcode.operation

import com.curtislb.adventofcode.year2020.day08.bootcode.BootCode

/**
 * TODO
 */
object AccumulateOperation : Operation {
    override fun execute(argument: Int, state: BootCode.State): BootCode.State {
        return state.with(accumulator = state.accumulator + argument, pointer = state.pointer + 1)
    }
}
