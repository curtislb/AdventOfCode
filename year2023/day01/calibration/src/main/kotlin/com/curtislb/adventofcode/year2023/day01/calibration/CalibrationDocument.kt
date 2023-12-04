package com.curtislb.adventofcode.year2023.day01.calibration

import com.curtislb.adventofcode.common.number.DIGIT_WORDS
import com.curtislb.adventofcode.common.number.digitWordToInt
import com.curtislb.adventofcode.common.number.isDigit

/**
 * A document with lines of text that contain calibration values, possibly surrounded by other
 * characters.
 *
 * @property lines A list of all lines of text in the document.
 *
 * @constructor Creates a new instance of [CalibrationDocument] with the given [lines] of text.
 */
class CalibrationDocument(private val lines: List<String>) {
    /**
     * Returns the sum of all calibration values in the document, found by concatenating the first
     * and last decimal digit characters (`'0'..'9'`) on each line.
     */
    fun sumValuesFromDigitsOnly(): Int = lines.sumOf { line ->
        val firstDigit = line.first { it.isDigit() }.digitToInt()
        val lastDigit = line.last { it.isDigit() }.digitToInt()
        concatDigits(firstDigit, lastDigit)
    }

    /**
     * Returns the sum of all calibration values in the document, found by concatenating the first
     * and last decimal digits on each line, which may appear as characters (`'0'..'9'`) or as
     * English words ("zero", "one", "two", etc.).
     */
    fun sumValuesFromDigitsAndWords(): Int = lines.sumOf { line ->
        val firstDigit = FORWARD_REGEX.find(line)?.value?.toDigit() ?: 0
        val lastDigit = REVERSE_REGEX.find(line.reversed())?.value?.reversedToDigit() ?: 0
        concatDigits(firstDigit, lastDigit)
    }

    companion object {
        /**
         * The English names of all decimal digits, separated by the pipe character.
         */
        private val DIGIT_WORDS_PSV = DIGIT_WORDS.joinToString(separator = "|")

        /**
         * A regex used to search for a decimal digit character or word in a regular string.
         */
        private val FORWARD_REGEX = Regex("""\d|$DIGIT_WORDS_PSV""")

        /**
         * A regex used to search for a decimal digit character or word in a reversed string.
         */
        private val REVERSE_REGEX = Regex("""\d|${DIGIT_WORDS_PSV.reversed()}""")

        /**
         * Returns the decimal integer value that results from concatenating [tensDigit] and
         * [onesDigit], in that order.
         */
        private fun concatDigits(tensDigit: Int, onesDigit: Int) = tensDigit * 10 + onesDigit

        /**
         * Returns the integer value corresponding to the given decimal digit character or
         * corresponding English word ("zero" -> 0, "one" -> 1, "two" -> 2, etc.).
         */
        private fun String.toDigit(): Int =
            if (isDigit()) this[0].digitToInt() else digitWordToInt()

        /**
         * Returns the integer value corresponding to the given decimal digit character or
         * corresponding reversed English word ("orez" -> 0, "eno" -> 1, "owt" -> 2, etc.).
         */
        private fun String.reversedToDigit(): Int =
            if (isDigit()) this[0].digitToInt() else reversed().digitWordToInt()
    }
}
