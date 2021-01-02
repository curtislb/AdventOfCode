/*
After some careful analysis, you believe that exactly one instruction is corrupted.

Somewhere in the program, either a jmp is supposed to be a nop, or a nop is supposed to be a jmp. (No acc instructions
were harmed in the corruption of this boot code.)

The program is supposed to terminate by attempting to execute an instruction immediately after the last instruction in
the file. By changing exactly one jmp or nop, you can repair the boot code and make it terminate correctly.

For example, consider the same program from above:

nop +0
acc +1
jmp +4
acc +3
jmp -3
acc -99
acc +1
jmp -4
acc +6

If you change the first instruction from nop +0 to jmp +0, it would create a single-instruction infinite loop, never
leaving that instruction. If you change almost any of the jmp instructions, the program will still eventually find
another jmp instruction and loop forever.

However, if you change the second-to-last instruction (from jmp -4 to nop -4), the program terminates! The instructions
are visited in this order:

nop +0  | 1
acc +1  | 2
jmp +4  | 3
acc +3  |
jmp -3  |
acc -99 |
acc +1  | 4
nop -4  | 5
acc +6  | 6

After the last instruction (acc +6), the program terminates by attempting to run the instruction below the last
instruction in the file. With this change, after the program terminates, the accumulator contains the value 8 (acc +1,
acc +1, acc +6).

Fix the program so that it terminates normally by changing exactly one jmp (to nop) or nop (to jmp). What is the value
of the accumulator after the program terminates?
*/

package com.curtislb.adventofcode.year2020.day08.part2

import com.curtislb.adventofcode.common.io.pathToInput
import com.curtislb.adventofcode.year2020.day08.bootcode.BootCode
import com.curtislb.adventofcode.year2020.day08.bootcode.Instruction
import com.curtislb.adventofcode.year2020.day08.bootcode.operation.JumpOperation
import com.curtislb.adventofcode.year2020.day08.bootcode.operation.NoOperation
import java.nio.file.Path

/**
 * Returns the solution to the puzzle for 2020, day 8, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = pathToInput(year = 2020, day = 8)): Int? {
    val file = inputPath.toFile()
    val bootCode = BootCode(file.readText())

    // Try replacing each jmp/nop instruction until the program terminates.
    bootCode.instructions.forEachIndexed { index, instruction ->
        // Determine the new operation to try.
        val newOperation = when(instruction.operation) {
            JumpOperation -> NoOperation
            NoOperation -> JumpOperation
            else -> instruction.operation
        }

        if (newOperation != instruction.operation) {
            // Run the program with the substituted operation and check if it terminates.
            bootCode.instructions[index] = Instruction(newOperation, instruction.argument)
            bootCode.run()
            if (!bootCode.isLoopDetected) {
                return bootCode.accumulator
            }

            // Restore the original operation and reset the program before trying again.
            bootCode.instructions[index] = instruction
            bootCode.resetState()
        }
    }

    // Failed to find a substitution that causes the program to terminate.
    return null
}

fun main() = when (val solution = solve()) {
    null -> println("No solution found.")
    else -> println(solution)
}
