package com.curtislb.adventofcode.year2020.day08.bootcode

/**
 * The current state of a boot code program.
 *
 * @param accumulator An integer value (initially zero) that may be modified during execution.
 * @param pointer The zero-indexed position of the next instruction to be executed.
 */
data class State(val accumulator: Int, val pointer: Int)
