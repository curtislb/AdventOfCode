package com.curtislb.adventofcode.year2020.day08.bootcode

/**
 * TODO
 */
class BootCode(programString: String) {
    /**
     * TODO
     */
    val instructions: List<Instruction> = programString.trim().lines().map { Instruction.from(it) }

    /**
     * TODO
     */
    private var state: State = INITIAL_STATE

    /**
     * TODO
     */
    val accumulator: Int get() = state.accumulator

    /**
     * TODO
     */
    private var isLoopDetected: Boolean = false

    /**
     * TODO
     */
    fun run(): Boolean {
        if (isLoopDetected) {
            return false
        }

        val executedInstructions = mutableSetOf<Int>()
        while (state.pointer in instructions.indices) {
            if (state.pointer in executedInstructions) {
                isLoopDetected = true
                break
            }

            executedInstructions.add(state.pointer)
            state = instructions[state.pointer].execute(state)
        }

        return !isLoopDetected
    }

    /**
     * TODO
     */
    fun resetState() {
        state = INITIAL_STATE
        isLoopDetected = false
    }

    companion object {
        /**
         * TODO
         */
        private val INITIAL_STATE = State(accumulator = 0, pointer = 0)
    }

    /**
     * TODO
     */
    class State(val accumulator: Int, val pointer: Int) {
        /**
         * TODO
         */
        fun with(accumulator: Int? = null, pointer: Int? = null) = State(
            accumulator ?: this.accumulator,
            pointer ?: this.pointer
        )
    }
}
