package com.curtislb.adventofcode.year2021.day21.dice.dirac

import com.curtislb.adventofcode.common.collection.Counter
import com.curtislb.adventofcode.common.collection.forEachNested

/**
 * A set of dice, each of which can roll any integer from 1 to [sidesCount] with equal probability.
 *
 * @param diceCount The number of dice that will be rolled each time.
 * @param sidesCount The maximum possible result when rolling each die.
 */
class DiracDice(val diceCount: Int, val sidesCount: Int) {
    /**
     * A map from each possible total result to the number of outcomes that sum to it.
     */
    private val outcomeCounts: Map<Int, Long> = Counter<Int>()
        .apply {
            (1..sidesCount).toList().forEachNested(diceCount) { indexedRolls ->
                val rollResult = indexedRolls.sumOf { (_, roll) -> roll }
                this[rollResult]++
                false // Don't stop iterating
            }
        }
        .toMap()

    /**
     * All possible values given by rolling the dice and summing the results.
     */
    val possibleResults: IntRange = diceCount..(diceCount * sidesCount)

    /**
     * Returns the number of distinct dice roll outcomes that sum to the given [totalResult].
     */
    fun countOutcomes(totalResult: Int): Long = outcomeCounts[totalResult] ?: 0L
}