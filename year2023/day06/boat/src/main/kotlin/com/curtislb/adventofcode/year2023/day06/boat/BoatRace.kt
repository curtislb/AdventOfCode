package com.curtislb.adventofcode.year2023.day06.boat

import com.curtislb.adventofcode.common.number.quadraticRoots
import kotlin.math.ceil

/**
 * A toy boat race that lasts for a fixed amount of time and has an associated record distance,
 * which must be exceeded in order to win the race.
 *
 * @property time The total time (in milliseconds) that the race lasts.
 * @property distance The current best distance ever recorded in the race.
 *
 * @constructor Creates a new instance of [BoatRace] with the given total [time] and record
 *  [distance].
 */
class BoatRace(private val time: Long, private val distance: Long) {
    /**
     * Returns the number of different ways the race can be won, by holding the button for a
     * distinct whole number of milliseconds.
     */
    fun countWaysToWin(): Long {
        // Solve the quadratic equation: x*(t - x) = d => -x^2 + t*x - d = 0
        val roots = quadraticRoots(a = -1.0, b = time.toDouble(), c = -distance.toDouble())

        // Find the minimum winning hold time
        val floorTime = roots.first.toLong()
        val minHoldTime = if (isWinningHoldTime(floorTime)) floorTime else floorTime + 1

        // Find the maximum winning hold time
        val ceilTime = ceil(roots.second).toLong()
        val maxHoldTime = if (isWinningHoldTime(ceilTime)) ceilTime else ceilTime - 1

        println("BoatRace(time = $time, distance = $distance)")
        println("minHoldTime = $minHoldTime, maxHoldTime = $maxHoldTime")

        return maxHoldTime - minHoldTime + 1L
    }

    /**
     * Returns `true` if it's possible to win the race by holding the button for exactly [holdTime]
     * milliseconds.
     */
    private fun isWinningHoldTime(holdTime: Long): Boolean = holdTime * (time - holdTime) > distance
}
