package com.curtislb.adventofcode.year2021.day18.snailfish

/**
 * A snailfish number, which is either a [NumberPair] or a [RegularNumber].
 */
sealed class SnailfishNumber {
    /**
     * Returns the magnitude of this number.
     */
    abstract fun magnitude(): Int

    /**
     * Returns the result of applying the "explode" reduction operation to this number, which is
     * nested at the given [depth] relative to the outermost snailfish number.
     */
    internal abstract fun explode(depth: Int): ExplodeResult

    /**
     * Returns the result of applying the "split" reduction operation to this number.
     */
    internal abstract fun split(): SplitResult

    /**
     * Returns a copy of this number with [value] added to the leftmost regular number.
     */
    internal abstract fun addToLeftmost(value: Int): SnailfishNumber

    /**
     * Returns a copy of this number with [value] added to the rightmost regular number.
     */
    internal abstract fun addToRightmost(value: Int): SnailfishNumber

    /**
     * Returns the reduced snailfish number sum of this number and [other] (in that order).
     */
    operator fun plus(other: SnailfishNumber): SnailfishNumber = NumberPair(this, other).reduce()

    /**
     * Returns the reduced form of this snailfish number.
     *
     * To reduce a snailfish number, the first applicable action in the following list must be
     * applied repeatedly, until neither action is applicable to the resulting number:
     *
     * - If any [NumberPair] is nested inside four pairs, the leftmost such pair explodes.
     * - If any [RegularNumber] is 10 or greater, the leftmost such number splits.
     */
    fun reduce(): SnailfishNumber {
        var number = this
        while (true) {
            val explodeResult = number.explode(depth = 0)
            if (explodeResult.isChanged) {
                number = explodeResult.number
                continue
            }

            val splitResult = number.split()
            if (splitResult.isChanged) {
                number = splitResult.number
            } else {
                break
            }
        }
        return number
    }

    companion object {
        /**
         * A regex that matches a snailfish number pair.
         */
        private val NUMBER_PAIR_REGEX = Regex("""\s*\[(.*)]\s*""")

        /**
         * A regex that matches a regular snailfish number.
         */
        private val REGULAR_NUMBER_REGEX = Regex("""\s*\d+\s*""")

        /**
         * Returns the snailfish number represented by the given [numberString].
         *
         * The format of [numberString] must be one of the following:
         *
         * - A decimal integer, representing a [RegularNumber]
         * - `"[$A,$B]"`, representing a [NumberPair] with left value `A` and right value `B`
         *
         * @throws IllegalArgumentException If [numberString] does not have the right format.
         */
        fun fromString(numberString: String): SnailfishNumber {
            // Check if the string represents a regular number
            if (REGULAR_NUMBER_REGEX.matches(numberString)) {
                return RegularNumber(numberString.trim().toInt())
            }

            // Otherwise, the string much represent a number pair
            val matchResult = NUMBER_PAIR_REGEX.matchEntire(numberString)
            require(matchResult != null) { "Malformed number string: $numberString" }

            // Find the separator for the outermost snailfish number by counting brackets
            val innerString = matchResult.groupValues[1]
            var bracketCount = 0
            var commaIndex = -1
            for ((index, char) in innerString.withIndex()) {
                when (char) {
                    '[' -> bracketCount++

                    ']' -> {
                        require(bracketCount > 0) { "Malformed number string: $numberString" }
                        bracketCount--
                    }

                    ',' -> if (bracketCount == 0) {
                        commaIndex = index
                        break
                    }

                    else -> Unit
                }
            }

            // Recursively parse the left and right snailfish numbers
            require(commaIndex in innerString.indices) { "Malformed number string: $numberString" }
            val leftString = innerString.substring(0, commaIndex)
            val rightString = innerString.substring(commaIndex + 1)
            return NumberPair(fromString(leftString), fromString(rightString))
        }
    }
}
