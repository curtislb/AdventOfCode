package com.curtislb.adventofcode.year2019.day22.shuffle

import java.math.BigInteger

/**
 * A shuffle that involves dealing cards into stacks a given [increment] apart, wrapping around as
 * necessary, and collecting them in order to form the new deck.
 */
class DealWithIncrement(private val increment: BigInteger) : Shuffle {
    override fun apply(position: BigInteger, deckSize: BigInteger): BigInteger =
        (position * increment) % deckSize

    override fun applyReverse(position: BigInteger, deckSize: BigInteger): BigInteger {
        return (position * increment.modInverse(deckSize)) % deckSize
    }
}
