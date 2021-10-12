package com.curtislb.adventofcode.year2020.day08.bootcode

/**
 * A boot code program, consisting of a list of instructions that are executed in order.
 *
 * @param programString A string representation of the program, consisting of one instruction per
 *  line.
 */
class BootCode(programString: String) {
    /**
     * A list of instructions that make up the program.
     */
    val instructions: Array<Instruction> =
        programString.trim().lines().map { Instruction.from(it) }.toTypedArray()

    /**
     * The current state of the program, which may be updated during execution.
     */
    private var state: State = INITIAL_STATE

    /**
     * An integer value (initially zero) that may be modified during execution.
     */
    val accumulator: Int get() = state.accumulator

    /**
     * Whether an infinite loop has been detected during program execution.
     */
    var isLoopDetected: Boolean = false
        private set

    /**
     * Runs the program by executing instructions in sequence until a stopping condition is reached.
     *
     * The program begins by executing the instruction at pointer position 0. Each executed
     * instruction may then modify the state of the program (see [Instruction]), including by
     * updating the pointer to the position of the next instruction to be executed. This process
     * will continue until one of the following occurs:
     *
     * - The pointer is moved to a position with no corresponding instruction.
     * - The pointer is moved to the position of a previously executed instruction, meaning a loop
     *   has been detected.
     *
     * In either case, this method will return. Any future calls to [run] will then immediately
     * return, until [resetState] is invoked.
     */
    fun run() {
        if (!isLoopDetected && state.pointer in instructions.indices) {
            val prevInstructionPointers = mutableSetOf<Int>()
            while (state.pointer in instructions.indices) {
                if (state.pointer in prevInstructionPointers) {
                    isLoopDetected = true
                    break
                }

                prevInstructionPointers.add(state.pointer)
                state = instructions[state.pointer].execute(state)
            }
        }
    }

    /**
     * Restores the [State] of the program to its starting value and clears the [isLoopDetected]
     * flag so that the program can be run again.
     *
     * Note that any modified [instructions] are *not* reset by this method.
     */
    fun resetState() {
        state = INITIAL_STATE
        isLoopDetected = false
    }

    companion object {
        /**
         * The initial state of the program, prior to execution.
         */
        private val INITIAL_STATE = State(accumulator = 0, pointer = 0)
    }
}
