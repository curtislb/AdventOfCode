package com.curtislb.adventofcode.year2019.day17.scaffold

import com.curtislb.adventofcode.year2019.day17.scaffold.instruction.Instruction
import com.curtislb.adventofcode.year2019.day17.scaffold.instruction.Move

/**
 * A movement routine that can be given as input to the vacuum robot.
 *
 * @param callOrder A list of indices, representing the [functions] to be called in order.
 * @param functions A list of ordered groups of movement instructions to be called by this routine.
 *
 * @throws IllegalArgumentException If any index in [callOrder] doesn't match the given [functions].
 */
class Routine(private val callOrder: List<Int>, private val functions: List<List<Instruction>>) {
    init {
        callOrder.forEach { index ->
            require(index in functions.indices) { "No matching function for index: $index" }
        }
    }

    /**
     * Returns an equivalent copy of this routine with at least [functionCount] functions.
     */
    fun padFunctions(functionCount: Int): Routine {
        if (functionCount <= functions.size) {
            return this
        }

        val paddedFunctions = functions.toMutableList()
        val noOpFunction = listOf(Move(0))
        for (i in 1..(functionCount - functions.size)) {
            paddedFunctions.add(noOpFunction)
        }
        return Routine(callOrder, paddedFunctions)
    }

    /**
     * Returns a string representation of this routine that can be provided as input to an
     * ASCII-compatible Intcode program.
     */
    fun toAsciiInput(): String {
        val callOrderString =
            callOrder.joinToString(separator = ",") { ('A'.code + it).toChar().toString() }
        val functionStrings = functions.map { function -> function.joinToString(separator = ",") }
        return "$callOrderString\n${functionStrings.joinToString(separator = "\n")}"
    }

    companion object {
        /**
         * Returns a routine with the following properties:
         *
         * - Is equivalent to [instructions].
         * - Has at most [maxFunctionCount] functions.
         * - Has at most [maxCharCount] characters in the ASCII representation of [callOrder] and of
         *   each function.
         *
         * If no such routine exists, this function instead returns `null`.
         */
        fun compressInstructions(
            instructions: List<Instruction>,
            maxFunctionCount: Int,
            maxCharCount: Int
        ): Routine? {
            return compressInstructionsInternal(instructions, maxFunctionCount, maxCharCount)
        }

        /**
         * Recursive helper function for [compressInstructions].
         */
        private fun compressInstructionsInternal(
            instructions: List<Instruction>,
            maxFunctionCount: Int,
            maxCharCount: Int,
            startIndex: Int = 0,
            compressedPrefix: MutableList<Int> = mutableListOf(),
            functions: MutableList<MutableList<Instruction>> = mutableListOf()
        ): Routine? {
            // Greedily match each existing function to instructions, starting from startIndex.
            for (i in functions.indices) {
                val function = functions[i]
                val nextStart = startIndex + function.size
                if (
                    nextStart <= instructions.size &&
                    instructions.subList(startIndex, nextStart) == function
                ) {
                    compressedPrefix.add(i)
                    val result = compressInstructionsInternal(
                        instructions,
                        maxFunctionCount,
                        maxCharCount,
                        nextStart,
                        compressedPrefix,
                        functions
                    )
                    if (result != null) {
                        return result
                    }
                    compressedPrefix.removeLast()
                }
            }

            when {
                // Check if all instructions are captured and the final routine meets all criteria.
                startIndex == instructions.size -> {
                    val compressedCharCount = compressedPrefix.size * 2 - 1
                    return if (compressedCharCount <= maxCharCount) {
                        Routine(compressedPrefix, functions)
                    } else {
                        null
                    }
                }

                // Stop if the function limit has been reached without capturing all instructions.
                functions.size == maxFunctionCount -> return null

                else -> {
                    compressedPrefix.add(functions.size)
                    functions.add(mutableListOf())

                    // Try all possible functions below maxCharCount starting from startIndex.
                    var endIndex = startIndex
                    var charCount = instructions[endIndex].toString().length
                    while (charCount <= maxCharCount) {
                        functions.last().add(instructions[endIndex])
                        val result = compressInstructionsInternal(
                            instructions,
                            maxFunctionCount,
                            maxCharCount,
                            endIndex + 1,
                            compressedPrefix,
                            functions
                        )
                        if (result != null) {
                            return result
                        }
                        endIndex++
                        charCount += 1 + instructions[endIndex].toString().length
                    }

                    compressedPrefix.removeLast()
                    functions.removeLast()

                    // Failed to find a valid routine.
                    return null
                }
            }
        }
    }
}
