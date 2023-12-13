package com.curtislb.adventofcode.year2023.day07.cards

/**
 * A card used to play Camel Cards. Indistinguishable from other cards with the same [label].
 *
 * @property label A character that uniquely identifies the card.
 */
enum class Card(val label: Char) {
    /**
     * "Wildcard" with the label `J`. Weaker than any other card, but can pretend to be whatever
     * card is best for the purpose of determining hand type.
     */
    JOKER('J'),

    /**
     * Card with the label `2`. Stronger than [JOKER], but weaker than [THREE].
     */
    TWO('2'),

    /**
     * Card with the label `3`. Stronger than [TWO], but weaker than [FOUR].
     */
    THREE('3'),

    /**
     * Card with the label `4`. Stronger than [THREE], but weaker than [FIVE].
     */
    FOUR('4'),

    /**
     * Card with the label `5`. Stronger than [FOUR], but weaker than [SIX].
     */
    FIVE('5'),

    /**
     * Card with the label `6`. Stronger than [FIVE], but weaker than [SEVEN].
     */
    SIX('6'),

    /**
     * Card with the label `7`. Stronger than [SIX], but weaker than [EIGHT].
     */
    SEVEN('7'),

    /**
     * Card with the label `8`. Stronger than [SEVEN], but weaker than [NINE].
     */
    EIGHT('8'),

    /**
     * Card with the label `9`. Stronger than [EIGHT], but weaker than [TEN].
     */
    NINE('9'),

    /**
     * Card with the label `T`. Stronger than [NINE], but weaker than [JACK].
     */
    TEN('T'),

    /**
     * Card with the label `J`. Stronger than [TEN], but weaker than [QUEEN].
     */
    JACK('J'),

    /**
     * Card with the label `Q`. Stronger than [JACK], but weaker than [KING].
     */
    QUEEN('Q'),

    /**
     * Card with the label `K`. Stronger than [QUEEN], but weaker than [ACE].
     */
    KING('K'),

    /**
     * Card with the label `A`. Stronger than any other card.
     */
    ACE('A');

    companion object {
        /**
         * Returns a [Card] corresponding to the given label [char].
         *
         * If [useJokerRule] is `true`, the label `J` will be treated as [Card.JOKER]. Otherwise, it
         * will be treated as [Card.JACK].
         *
         * @throws IllegalArgumentException If [char] has no corresponding [Card].
         */
        fun fromChar(char: Char, useJokerRule: Boolean): Card {
            // Interpret J based on whether the joker rule is used
            if (char == 'J') {
                return if (useJokerRule) JOKER else JACK
            }

            return entries.find { it.label == char }
                ?: throw IllegalArgumentException("Invalid label character: $char")
        }
    }
}
