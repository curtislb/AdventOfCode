package com.curtislb.adventofcode.year2020.day08.bootcode.operation

import com.curtislb.adventofcode.year2020.day08.bootcode.BootCode

/**
 * TODO
 */
object NoOperation : Operation {
    override fun execute(argument: Int, state: BootCode.State): BootCode.State = state.with(pointer = state.pointer + 1)
}
