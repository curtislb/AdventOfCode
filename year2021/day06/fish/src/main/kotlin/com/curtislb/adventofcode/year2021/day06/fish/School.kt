package com.curtislb.adventofcode.year2021.day06.fish

import com.curtislb.adventofcode.common.collection.Counter

/**
 * A school of lanternfish, which create more fish over time according to a population growth model.
 *
 * @param initialTimers The internal reproduction timer (in days) of each lanternfish.
 * @param cycleDays The number of days required for a mature lanternfish to create a new fish.
 * @param maturationDays The number of days required for a new lanternfish to become mature.
 *
 * @throws IllegalArgumentException If any timer in `initialTimers` is negative.
 */
class School(initialTimers: List<Int>, cycleDays: Int, maturationDays: Int) {
    /**
     * The value (in days) to which a fish's internal timer should be set after creating a new fish.
     */
    private val oldTimerStart: Int = cycleDays - 1

    /**
     * The value (in days) to which a newly created fish's internal timer should be set.
     */
    private val newTimerStart: Int = oldTimerStart + maturationDays

    /**
     * An upper bound on the current internal timer for any fish.
     */
    private var timerMax: Int = newTimerStart

    /**
     * A counter of the current number of fish for each internal timer value.
     */
    private val fishCounts: Counter<Int> = Counter()

    init {
        initialTimers.forEach { timer ->
            require(timer >= 0) { "Timer starting value must be non-negative: $timer" }
            timerMax = timerMax.coerceAtLeast(timer)
            fishCounts[timer]++
        }
    }

    /**
     * Returns the current number of fish with the given internal [timer] value.
     */
    operator fun get(timer: Int): Long = fishCounts[timer]

    /**
     * Returns the current total number of fish.
     */
    fun countFish(): Long = fishCounts.sumCounts()

    /**
     * Simulates population growth for [dayCount] days, starting from the current state.
     *
     * Each day, lanternfish population growth is simulated as follows:
     *
     * - A fish with a timer of 0 updates its timer to [oldTimerStart] and creates one new fish with
     *   a timer of [newTimerStart].
     * - A fish with a timer of any other value decreases its timer by 1.
     */
    fun simulate(dayCount: Int = 1) {
        repeat(dayCount) {
            // Decrement timer counts, keeping track of new fish
            val newFishCount = fishCounts[0]
            for (timer in 0..timerMax) {
                fishCounts[timer] = fishCounts[timer + 1]
            }

            // Check if the upper bound for timer values can be reduced
            if (timerMax > newTimerStart) {
                timerMax--
            }

            // Add new fish and update all parents' timers
            fishCounts[oldTimerStart] += newFishCount
            fishCounts[newTimerStart] += newFishCount
        }
    }
}
