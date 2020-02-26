package com.curtislb.adventofcode.year2019.day22.shuffle

import java.math.BigInteger

/**
 * A reversible method for shuffling the cards in a deck.
 */
interface Shuffle {
    /**
     * Returns the new position of the card currently at [position] after shuffling with the given [deckSize].
     */
    fun apply(position: BigInteger, deckSize: BigInteger): BigInteger

    /**
     * Returns the previous position of the card currently at [position], prior to shuffling with the given [deckSize].
     *
     * This method must be defined such that `applyReverse(apply(position, deckSize), deckSize) == position`.
     */
    fun applyReverse(position: BigInteger, deckSize: BigInteger): BigInteger

    companion object {
        /**
         * Reverses a series of [shuffleSteps] and returns the original position of the card currently at [position],
         * given a [deckSize].
         */
        fun reverse(shuffleSteps: List<Shuffle>, position: BigInteger, deckSize: BigInteger): BigInteger {
            var prevPosition = position
            for (i in shuffleSteps.indices.reversed()) {
                prevPosition = shuffleSteps[i].applyReverse(prevPosition, deckSize)
            }
            return prevPosition
        }

        /**
         * Returns the shuffle corresponding to [shuffleString].
         *
         * @throws IllegalArgumentException If [shuffleString] has no corresponding shuffle.
         */
        fun from(shuffleString: String): Shuffle {
            val trimmedString = shuffleString.trim()
            return when {
                trimmedString == "deal into new stack" -> DealIntoNewStack
                trimmedString.startsWith("cut") -> Cut(extractParameter(trimmedString))
                trimmedString.startsWith("deal with increment") -> DealWithIncrement(extractParameter(trimmedString))
                else -> throw IllegalArgumentException("Unknown shuffle string: $trimmedString")
            }
        }

        /**
         * Returns the integer parameter of the shuffle corresponding to [shuffleString].
         */
        private fun extractParameter(shuffleString: String): BigInteger = shuffleString.split(' ').last().toBigInteger()
    }
}
