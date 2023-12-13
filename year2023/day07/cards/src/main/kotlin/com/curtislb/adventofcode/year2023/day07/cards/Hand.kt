package com.curtislb.adventofcode.year2023.day07.cards

import com.curtislb.adventofcode.common.collection.Counter
import com.curtislb.adventofcode.common.comparison.compareLists

/**
 * A hand of Camel Cards, consisting of an ordered list of cards and a bid amount.
 *
 * @property cards The cards contained in the hand, from left to right.
 * @property bid The bid amount for the hand.
 *
 * @constructor Creates a new instance of [Hand] with the given [cards] and [bid] amount.
 */
data class Hand(val cards: List<Card>, val bid: Long) : Comparable<Hand> {
    /**
     * Counts of all distinct card labels in the hand, in descending order.
     *
     * Used to compare hands by type (e.g. "full house" vs. "two of a kind").
     */
    private val countsDescending: List<Int>

    init {
        // Count each distinct card label
        val cardCounter = Counter.fromItems(cards)

        countsDescending = when (val jokerCount = cardCounter[Card.JOKER].toInt()) {
            // If no jokers, sort the card counts as-is
            0 -> cardCounter.counts.map { it.toInt() }.sortedDescending()

            // If all jokers, treat them as the same card
            cards.size -> listOf(jokerCount)

            // Treat all jokers as the most common card
            else -> cardCounter.entries
                .filter { it.key != Card.JOKER }
                .map { it.value.toInt() }
                .toMutableList()
                .also { counts ->
                    counts.sortDescending()
                    counts[0] += jokerCount
                }
        }
    }

    override fun compareTo(other: Hand): Int {
        // Compare hand types, which are ordered by descending counts
        val countsResult = compareLists(countsDescending, other.countsDescending)
        if (countsResult != 0) {
            return countsResult
        }

        // Compare cards by strength, from left to right
        val cardsResult = compareLists(cards, other.cards)
        if (cardsResult != 0) {
            return cardsResult
        }

        // Compare the bid amounts
        return bid.compareTo(other.bid)
    }

    companion object {
        /**
         * A regex used to parse the cards and bid for a hand.
         */
        private val HAND_REGEX = Regex("""(\w+)\s+(\d+)""")

        /**
         * Returns a [Hand] with cards and bid amount read from the given [string].
         *
         * The [string] must have the format `"$cards $bid"`, where `cards` is an ordered sequence
         * of [Card] labels and `bid` is a decimal integer representing the bid amount.
         *
         * If [useJokerRule] is `true`, the label `J` will be treated as [Card.JOKER]. Otherwise, it
         * will be treated as [Card.JACK].
         *
         * @throws IllegalArgumentException If [string] has the wrong format.
         */
        fun fromString(string: String, useJokerRule: Boolean): Hand {
            val matchResult = HAND_REGEX.matchEntire(string)
            require(matchResult != null) { "Malformed hand string: $string" }

            val (cardsString, bidString) = matchResult.destructured
            val cards = cardsString.map { Card.fromChar(it, useJokerRule) }
            val bid = bidString.toLong()
            return Hand(cards, bid)
        }
    }
}
