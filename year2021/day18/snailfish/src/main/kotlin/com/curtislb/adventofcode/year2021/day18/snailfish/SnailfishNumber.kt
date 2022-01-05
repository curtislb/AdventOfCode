package com.curtislb.adventofcode.year2021.day18.snailfish

/**
 * TODO
 */
sealed class SnailfishNumber {
    abstract fun magnitude(): Int

    operator fun plus(other: SnailfishNumber): SnailfishNumber = NumberPair(this, other).reduce()

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

    internal abstract fun explode(depth: Int): ExplodeResult

    internal abstract fun split(): SplitResult

    internal abstract fun addToLeftmost(value: Int): SnailfishNumber

    internal abstract fun addToRightmost(value: Int): SnailfishNumber

    companion object {
        private val NUMBER_PAIR_REGEX = Regex("""\s*\[(.*)]\s*""")

        private val REGULAR_NUMBER_REGEX = Regex("""\s*\d+\s*""")

        fun fromString(numberString: String): SnailfishNumber {
            if (REGULAR_NUMBER_REGEX.matches(numberString)) {
                return RegularNumber(numberString.trim().toInt())
            }

            val matchResult = NUMBER_PAIR_REGEX.matchEntire(numberString)
            require(matchResult != null) { "Malformed number string: $numberString" }

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

            require(commaIndex in innerString.indices) { "Malformed number string: $numberString" }
            val leftString = innerString.substring(0, commaIndex)
            val rightString = innerString.substring(commaIndex + 1)
            return NumberPair(fromString(leftString), fromString(rightString))
        }
    }
}
