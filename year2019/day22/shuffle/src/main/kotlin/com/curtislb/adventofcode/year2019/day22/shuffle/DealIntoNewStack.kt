package com.curtislb.adventofcode.year2019.day22.shuffle

import java.math.BigInteger

/**
 * A shuffle that involves dealing all cards into a new stack and using that stack as the deck.
 */
object DealIntoNewStack : Shuffle {
    override fun apply(position: BigInteger, deckSize: BigInteger): BigInteger =
        deckSize - BigInteger.ONE - position

    override fun applyReverse(position: BigInteger, deckSize: BigInteger): BigInteger =
        apply(position, deckSize)
}
