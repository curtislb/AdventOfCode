package com.curtislb.adventofcode.year2020.day08.bootcode.operation

import com.curtislb.adventofcode.year2020.day08.bootcode.BootCode

/**
 * TODO
 */
interface Operation {
    /**
     * TODO
     */
    fun execute(argument: Int, state: BootCode.State): BootCode.State

    companion object {
        fun from(string: String): Operation = when (string) {
            "acc" -> AccumulateOperation
            "jmp" -> JumpOperation
            "nop" -> NoOperation
            else -> throw IllegalArgumentException("Invalid operation: $string")
        }
    }
}
