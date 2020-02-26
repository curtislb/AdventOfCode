package com.curtislb.adventofcode.year2019.day22.shuffle

import java.math.BigInteger

/**
 * A shuffle that involves moving a chunk of [cutSize] cards from the top of the deck to the bottom.
 *
 * If [cutSize] is negative, instead moves a chunk of `-cutSize` cards from the bottom of the deck to the top.
 */
class Cut(private val cutSize: BigInteger) : Shuffle {
    override fun apply(position: BigInteger, deckSize: BigInteger): BigInteger {
        return (position - cutSize + deckSize) % deckSize
    }

    override fun applyReverse(position: BigInteger, deckSize: BigInteger): BigInteger {
        return (position + cutSize + deckSize) % deckSize
    }
}
