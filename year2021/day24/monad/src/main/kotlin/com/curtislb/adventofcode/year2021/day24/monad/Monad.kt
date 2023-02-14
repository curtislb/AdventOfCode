package com.curtislb.adventofcode.year2021.day24.monad

import com.curtislb.adventofcode.common.number.digitsToLong
import java.io.File

/**
 * Simplified representation of a MONAD program, which checks if a given submarine model number,
 * consisting of a set number of non-zero digits, is valid.
 *
 * @param rules The list of all digit rules used to determine if a model number is valid.
 * @param modelNumberLength The number of (non-zero) digits in a valid model number.
 */
class Monad(private val rules: List<DigitRule>, private val modelNumberLength: Int) {
    /**
     * Returns the largest valid model number, according to the MONAD program.
     */
    fun largestModelNumber(): Long {
        val digits = MutableList(modelNumberLength) { 9 }
        for (rule in rules) {
            digits[rule.fromIndex] = (9 - rule.increase).coerceAtMost(9)
            digits[rule.toIndex] = digits[rule.fromIndex] + rule.increase
        }
        return digits.digitsToLong()
    }

    /**
     * Returns the smallest valid model number, according to the MONAD program.
     */
    fun smallestModelNumber(): Long {
        val digits = MutableList(modelNumberLength) { 1 }
        for (rule in rules) {
            digits[rule.fromIndex] = (1 - rule.increase).coerceAtLeast(1)
            digits[rule.toIndex] = digits[rule.fromIndex] + rule.increase
        }
        return digits.digitsToLong()
    }

    companion object {
        /**
         * The number of ALU program lines required to parse a single input digit (including the
         * input instruction).
         */
        private const val LINES_PER_INPUT = 18

        /**
         * The line offset from the input instruction for the current digit to the instruction that
         * determines whether this digit is "pushed to" or "popped from" the output register `z`.
         */
        private const val POP_PUSH_OFFSET = 4

        /**
         * The line offset from the input instruction for the current digit to the instruction that
         * determines the relevant [DigitRule] value when "popping" this digit.
         */
        private const val POP_VAL_OFFSET = 5

        /**
         * The line offset from the input instruction for the current digit to the instruction that
         * determines the relevant [DigitRule] value when "pushing" this digit.
         */
        private const val PUSH_VAL_OFFSET = 15

        /**
         * Regex for parsing the instruction that determines whether the current digit is "pushed
         * to" or "popped from" the output register `z`.
         */
        private val POP_PUSH_REGEX = Regex("""div z (1|26)""")

        /**
         * Regex for parsing the instruction that determines the relevant [DigitRule] value when
         * "popping" the current digit.
         */
        private val POP_VAL_REGEX = Regex("""add x (-?\d+)""")

        /**
         * Regex for parsing the instruction that determines the relevant [DigitRule] value when
         * "pushing" the current digit.
         */
        private val PUSH_VAL_REGEX = Regex("""add y (-?\d+)""")

        /**
         * Returns a simplified representation of the MONAD program represented by the given ALU
         * program [file].
         *
         * @throws IllegalArgumentException If [file] does not represent a valid MONAD program.
         */
        fun fromFile(file: File): Monad {
            // Parse the ALU program into its instruction lines
            val lines = file.readLines()

            // Parse the digit rules for each pair of "push" and "pop" instruction groups
            val stack = ArrayDeque<Pair<Int, Int>>()
            val rules = mutableListOf<DigitRule>()
            var modelNumberLength = 0
            for (lineIndex in lines.indices step LINES_PER_INPUT) {
                modelNumberLength++

                // Check if the current digit is "popped" or "pushed"
                val popPushLine = lines[lineIndex + POP_PUSH_OFFSET]
                val popPushMatchResult = POP_PUSH_REGEX.matchEntire(popPushLine)
                require(popPushMatchResult != null) { "Invalid MONAD program" }
                val isPop = popPushMatchResult.groupValues[1] != "1"

                if (isPop) {
                    // Get the digit rule value for this pop instruction group
                    val popValueLine = lines[lineIndex + POP_VAL_OFFSET]
                    val popValueMatchResult = POP_VAL_REGEX.matchEntire(popValueLine)
                    require(popValueMatchResult != null) { "Invalid MONAD program" }

                    // Construct a new digit rule using the last "pushed" digit
                    val (pushIndex, pushValue) = stack.removeLast()
                    val popIndex = modelNumberLength - 1
                    val popValue = popValueMatchResult.groupValues[1].toInt()
                    val newRule = DigitRule(
                        fromIndex = pushIndex,
                        toIndex = popIndex,
                        increase = pushValue + popValue
                    )
                    rules.add(newRule)
                } else {
                    // Get the digit rule value for this push instruction group
                    val pushValueLine = lines[lineIndex + PUSH_VAL_OFFSET]
                    val pushValueMatchResult = PUSH_VAL_REGEX.matchEntire(pushValueLine)
                    require(pushValueMatchResult != null) { "Invalid MONAD program" }

                    // Push this value (and the index of the current digit) onto the stack
                    val pushIndex = modelNumberLength - 1
                    val pushValue = pushValueMatchResult.groupValues[1].toInt()
                    stack.addLast(Pair(pushIndex, pushValue))
                }
            }

            return Monad(rules, modelNumberLength)
        }
    }
}
