package com.adventofcode.curtislb.year2019.day17.ascii

import com.adventofcode.curtislb.common.collection.removeLast
import com.adventofcode.curtislb.year2019.day17.ascii.instruction.Instruction
import com.adventofcode.curtislb.year2019.day17.ascii.instruction.Move

/**
 * A movement routine that can be given as input to the vacuum robot via the [ASCII].
 * @param callOrder A [List] of indices, representing the [functions] to be called in order by this [Routine].
 * @param functions A [List] of ordered groups of movement instructions that can be called by this [Routine].
 */
class Routine(private val callOrder: List<Int>, private val functions: List<List<Instruction>>) {
    init {
        callOrder.forEach { functionIndex ->
            if (functionIndex !in functions.indices) {
                throw IllegalArgumentException("No matching function for index: $functionIndex")
            }
        }
    }

    /**
     * Produces an equivalent copy of this [Routine] with at least a given number of functions.
     * @param functionCount The minimum size to which [functions] should be padded.
     * @return If [functions] contains fewer than [functionCount] items, returns a new [Routine] with the same
     *  [callOrder] and `functionCount - functions.size` no-op functions added to [functionCount]. Otherwise, returns
     *  this [Routine].
     */
    fun padFunctions(functionCount: Int): Routine {
        if (functionCount <= functions.size) {
            return this
        }

        val paddedFunctions = functions.toMutableList()
        for (i in 1..(functionCount - functions.size)) {
            paddedFunctions.add(listOf(Move(0)))
        }
        return Routine(callOrder, paddedFunctions)
    }

    override fun toString(): String {
        val callOrderString = callOrder.joinToString(separator = ",") { ('A'.toInt() + it).toChar().toString() }
        val functionStrings = functions.map { function -> function.joinToString(separator = ",") }
        return "$callOrderString\n${functionStrings.joinToString(separator = "\n")}"
    }

    companion object {
        /**
         * Tries to convert a list of instructions to a [Routine] that satisfies certain criteria.
         * @param instructions An ordered [List] of instructions to be converted to a [Routine].
         * @param maxFunctionCount The maximum number of functions that the [Routine] may contain.
         * @param maxCharCount The maximum number of characters that the ASCII representation of the call order and each
         *  function of the [Routine] may contain.
         * @return A [Routine] that meets the given criteria and is equivalent to [instructions] if one exists, or
         *  `null` otherwise.
         */
        fun compressInstructions(instructions: List<Instruction>, maxFunctionCount: Int, maxCharCount: Int): Routine? {
            return compressInstructionsInternal(instructions, maxFunctionCount, maxCharCount)
        }

        /**
         * Recursive helper function for [compressInstructions].
         * @param instructions An ordered [List] of instructions to be converted to a [Routine].
         * @param maxFunctionCount The maximum number of functions that the [Routine] may contain.
         * @param maxCharCount The maximum number of characters that the ASCII representation of the call order and each
         *  function of the [Routine] may contain.
         * @param startIndex An index to the first item in [instructions] that still needs to be compressed.
         * @param compressedPrefix A [List] of indices to [functions] that capture all instructions before [startIndex].
         * @param functions A partial [List] of functions that the final [Routine] will contain.
         * @return A [Routine] that meets the given criteria and is equivalent to [instructions] if one exists, or
         *  `null` otherwise.
         */
        private fun compressInstructionsInternal(
            instructions: List<Instruction>,
            maxFunctionCount: Int,
            maxCharCount: Int,
            startIndex: Int = 0,
            compressedPrefix: MutableList<Int> = mutableListOf(),
            functions: MutableList<MutableList<Instruction>> = mutableListOf()
        ): Routine? {
            // Try to greedily match each existing function to instructions starting from startIndex.
            for (i in functions.indices) {
                val function = functions[i]
                val nextStart = startIndex + function.size
                if (nextStart <= instructions.size && instructions.subList(startIndex, nextStart) == function) {
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
                // Check if we've captured all instructions and if the final routine meets all criteria.
                startIndex == instructions.size -> {
                    val compressedCharCount = compressedPrefix.size * 2 - 1
                    return if (compressedCharCount <= maxCharCount) Routine(compressedPrefix, functions) else null
                }

                // Stop if we haven't captured all instructions and have run out of functions.
                functions.size == maxFunctionCount -> return null

                else -> {
                    compressedPrefix.add(functions.size)
                    functions.add(mutableListOf())

                    // Try adding all possible functions below maxCharCount starting from startIndex.
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
