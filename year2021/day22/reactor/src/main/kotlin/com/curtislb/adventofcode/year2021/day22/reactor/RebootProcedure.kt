package com.curtislb.adventofcode.year2021.day22.reactor

import com.curtislb.adventofcode.common.collection.Counter
import com.curtislb.adventofcode.common.geometry.Cuboid

/**
 * A reactor reboot procedure that involves activating and deactivating cubes in a 3D grid.
 */
class RebootProcedure {
    /**
     * A counter that maps activated regions to the number of times their volumes should be added to
     * (or subtracted from) the total number of currently active cubes.
     */
    private val activeRegionsCounter: Counter<Cuboid> = Counter()

    /**
     * Returns the number of currently active cubes.
     */
    fun countActiveCubes(): Long =
        activeRegionsCounter.entries.sumOf { (region, count) -> region.volume * count }

    /**
     * Executes the given [rebootStep] by turning on or off all cubes in the specified region.
     */
    fun execute(rebootStep: RebootStep) {
        // Subtract volumes of overlapping regions that were previously activated
        val counterUpdates = Counter<Cuboid>()
        for ((region, count) in activeRegionsCounter.entries) {
            val overlap = region overlap rebootStep.region
            if (!overlap.isEmpty()) {
                counterUpdates[overlap] -= count
            }
        }
        activeRegionsCounter.addCounts(counterUpdates)

        // Add the current region's volume, if this step involves activating cubes
        if (rebootStep.isOn) {
            activeRegionsCounter[rebootStep.region]++
        }
    }
}
