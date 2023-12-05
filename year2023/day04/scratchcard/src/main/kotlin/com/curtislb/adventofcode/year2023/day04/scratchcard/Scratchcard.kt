package com.curtislb.adventofcode.year2023.day04.scratchcard

import com.curtislb.adventofcode.common.parse.parseInts

/**
 * A scratchcard with two sets of numbers, representing the card's actual numbers and the winning
 * numbers for that card.
 *
 * @property numbers The actual numbers associated with the card.
 * @property winningNumbers The winning numbers associated with the card.
 *
 * @constructor Creates a new instance of [Scratchcard] with the given [numbers] and
 *  [winningNumbers].
 */
class Scratchcard(private val numbers: Set<Int>, private val winningNumbers: Set<Int>) {
    /**
     * Returns the count of numbers that match one of the winning numbers for the scratchcard.
     */
    fun countMatches(): Int = (numbers intersect winningNumbers).size

    companion object {
        /**
         * A regex used to parse information about a scratchcard.
         */
        private val CARD_REGEX = Regex("""Card\s+\d+:((?:\d|\s)+)\|((?:\d|\s)+)""")

        /**
         * Returns a [Scratchcard] with numbers read from the given [string].
         *
         * The [string] must have the format `"Card $id: $num1, $num2, ... | $win1, $win2, ..."`,
         * where each `numX` is one of the card's actual numbers and each `winY` is one of the
         * winning numbers for the scratchcard.
         *
         * @throws IllegalArgumentException If [string] is not formatted correctly.
         */
        fun fromString(string: String): Scratchcard {
            val matchResult = CARD_REGEX.matchEntire(string)
            require(matchResult != null) { "Malformed scratchcard string: $string" }

            val (numbersString, winningNumbersString) = matchResult.destructured
            val numbers = numbersString.parseInts().toSet()
            val winningNumbers = winningNumbersString.parseInts().toSet()
            return Scratchcard(numbers, winningNumbers)
        }
    }
}
