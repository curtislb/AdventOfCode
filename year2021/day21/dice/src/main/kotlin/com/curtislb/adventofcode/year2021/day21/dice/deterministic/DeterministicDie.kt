package com.curtislb.adventofcode.year2021.day21.dice.deterministic

/**
 * A die that repeatedly produces integers in order from 1 to [sidesCount] (inclusive) when rolled.
 *
 * @param sidesCount The maximum possible result when rolling the die.
 */
class DeterministicDie(val sidesCount: Int) {
    /**
     * Returns the last result after the die has been rolled a number of times equal to [rollCount].
     *
     * @throws IllegalArgumentException If [rollCount] is negative or zero.
     */
    fun getResult(rollCount: Int): Int {
        require(rollCount > 0) { "Roll count must be positive: $rollCount" }
        return when (val modRoll = rollCount % sidesCount) {
            0 -> sidesCount
            else -> modRoll
        }
    }
}