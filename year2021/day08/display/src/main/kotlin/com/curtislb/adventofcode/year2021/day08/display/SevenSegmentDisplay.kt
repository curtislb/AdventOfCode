package com.curtislb.adventofcode.year2021.day08.display

import com.curtislb.adventofcode.common.math.digitsToInt

/**
 * A numerical seven-segment display whose signal wires have been mixed up.
 *
 * @param displayString A string representing the state and output of the display. This string must
 *  be of the form `"$digitA $digitB ... $digitG | $outputA ..."`, were each `digitX` represents the
 *  [SignalPattern] for a different decimal digit and each `outputX` represents the pattern for a
 *  single digit of the display's output.
 *
 * @throws IllegalArgumentException If the display string does not match the required format.
 */
class SevenSegmentDisplay(displayString: String) {
    /**
     * Signal patterns for each decimal digit from 0 to 9, in no particular order.
     */
    private val digitPatterns: List<SignalPattern>

    /**
     * Signal patterns for the decimal digits of the display output, in big-endian order.
     */
    val outputPatterns: List<SignalPattern>

    init {
        val matchResult = DISPLAY_REGEX.matchEntire(displayString)
        require(matchResult != null) { "Malformed display string: $displayString" }

        val (digitPatternsString, outputPatternsString) = matchResult.destructured
        digitPatterns = digitPatternsString.toSignalPatterns()
        outputPatterns = outputPatternsString.toSignalPatterns()
    }

    /**
     * A map from each possible signal pattern for this display to the digit that it represents.
     */
    private val digitMapping: Map<SignalPattern, Int> by lazy {
        val mapping = mutableMapOf<SignalPattern, Int>()
        val knownPatterns = MutableList(10) { SignalPattern.NONE }

        // Group patterns by their wire/segment counts
        val patternsByWireCount = digitPatterns.groupBy { it.countWires() }

        // Identify digits with unique segment counts (1, 4, 7, and 8)
        UNIQUE_COUNTS_MAP.forEach { (wireCount, digit) ->
            // Check that pattern exists and is unique
            val patterns = patternsByWireCount[wireCount]
                ?: throw IllegalStateException("No pattern for digit: $digit")
            check(patterns.size == 1) { "Multiple patterns for digit $digit: $patterns" }

            // Add to mapping and to the list of known digit patterns (for later deduction)
            val pattern = patterns.single()
            mapping[pattern] = digit
            knownPatterns[digit] = pattern
        }

        // Identify digits with five segments (2, 3, and 5)
        patternsByWireCount[5]?.let { patterns ->
            val fourOneDiff = knownPatterns[4] - knownPatterns[1]
            patterns.forEach { pattern ->
                val digit = when {
                    knownPatterns[1] in pattern -> 3
                    fourOneDiff in pattern -> 5
                    else -> 2
                }
                mapping[pattern] = digit
            }
        }

        // Identify digits with six segments (0, 6, and 9)
        patternsByWireCount[6]?.let { patterns ->
            patterns.forEach { pattern ->
                val digit = when {
                    knownPatterns[4] in pattern -> 9
                    knownPatterns[1] in pattern -> 0
                    else -> 6
                }
                mapping[pattern] = digit
            }
        }

        // Check that mapping contains exactly one pattern for each digit
        check(mapping.values.size == 10 && (0..9).all { it in mapping.values }) {
            "Invalid digit mapping: $mapping"
        }

        mapping
    }

    /**
     * Returns the decoded integer output of this display.
     *
     * @throws IllegalStateException If the display was initialized with incorrect digit or output
     *  patterns, meaning the output can't be decoded.
     */
    fun decodeOutput(): Int {
        val outputDigits = outputPatterns.map { pattern ->
            digitMapping[pattern] ?: throw IllegalStateException("Unrecognized pattern: $pattern")
        }
        return outputDigits.digitsToInt()
    }

    companion object {
        /**
         * Regex used to parse the digit and output substrings from a display string.
         */
        private val DISPLAY_REGEX = Regex("""\s*((?:[a-z]+\s*)+)\s+\|\s+((?:[a-z]+\s*)+)\s*""")

        /**
         * Regex for the separator between signal patterns in a display string.
         */
        private val PATTERN_SEPARATOR_REGEX = Regex("""\s+""")

        /**
         * A map from each segment count that uniquely identifies a digit to that digit.
         */
        private val UNIQUE_COUNTS_MAP = mapOf(2 to 1, 4 to 4, 3 to 7, 7 to 8)

        /**
         * Returns a list of space-separated signal patterns contained in this string.
         */
        private fun String.toSignalPatterns(): List<SignalPattern> =
            trim().split(PATTERN_SEPARATOR_REGEX).map { SignalPattern.fromString(it) }
    }
}
