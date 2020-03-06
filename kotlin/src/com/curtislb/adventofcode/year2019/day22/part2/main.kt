/*
--- Part Two ---

After a while, you realize your shuffling skill won't improve much more with merely a single deck of cards. You ask
every 3D printer on the ship to make you some more cards while you check on the ship repairs. While reviewing the work
the droids have finished so far, you think you see Halley's Comet fly past!

When you get back, you discover that the 3D printers have combined their power to create for you a single, giant, brand
new, factory order deck of 119315717514047 space cards.

Finally, a deck of cards worthy of shuffling!

You decide to apply your complete shuffle process (your puzzle input) to the deck 101741582076661 times in a row.

You'll need to be careful, though - one wrong move with this many cards and you might overflow your entire ship!

After shuffling your new, giant, factory order deck that many times, what number is on the card that ends up in
position 2020?
*/

package com.curtislb.adventofcode.year2019.day22.part2

import com.curtislb.adventofcode.common.io.mapLines
import com.curtislb.adventofcode.common.io.pathToInput
import com.curtislb.adventofcode.year2019.day22.shuffle.Shuffle
import java.math.BigInteger
import java.nio.file.Path

/**
 * Returns the solution to the puzzle for day 22, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param deckSize The number of cards in the deck to be shuffled.
 * @param shuffleCount The number of times the full shuffle procedure should be applied.
 * @param targetPosition The final position in the deck that we're interested in.
 */
fun solve(
    inputPath: Path = pathToInput(year = 2019, day = 22),
    deckSize: BigInteger = BigInteger("119315717514047"),
    shuffleCount: BigInteger = BigInteger("101741582076661"),
    targetPosition: BigInteger = BigInteger("2020")
): BigInteger {
    // Solve a system of equations involving the two previous card positions.
    val shuffleSteps = inputPath.toFile().mapLines { Shuffle.from(it) }
    val y = Shuffle.reverse(shuffleSteps, targetPosition, deckSize)
    val z = Shuffle.reverse(shuffleSteps, y, deckSize)
    val a = ((y - z) * (targetPosition - y).modInverse(deckSize)) % deckSize
    val b = (y - a * targetPosition) % deckSize

    // Apply the geometric series formula to determine the result.
    val c = a.modPow(shuffleCount, deckSize)
    return (c * targetPosition + (c - BigInteger.ONE) * (a - BigInteger.ONE).modInverse(deckSize) * b) % deckSize
}

fun main() {
    println(solve())
}
