package com.curtislb.adventofcode.year2021.day22.reactor

import com.curtislb.adventofcode.common.range.size
import com.curtislb.adventofcode.common.geometry.Cuboid

/**
 * A reactor initialization procedure that involves activating and deactivating cubes in a fixed
 * 3D region.
 *
 * @param region A region containing all cubes that are tracked during the initialization procedure.
 */
class InitializationProcedure(private val region: Cuboid) {
    /**
     * The number of currently active cubes in the initialization procedure region.
     */
    var activeCubesCount: Int = 0
        private set

    /**
     * The active states of all cubes in the initialization procedure region.
     */
    private val cubes: List<List<BooleanArray>> =
        List(region.xRange.size) {
            List(region.yRange.size) {
                BooleanArray(region.zRange.size)
            }
        }

    /**
     * Returns if the cube at position ([x], [y], [z]) in the initialization procedure region is
     * currently active.
     */
    private fun isCubeActive(x: Int, y: Int, z: Int): Boolean =
        cubes[x - region.xRange.first][y - region.yRange.first][z - region.zRange.first]

    /**
     * Sets the activity state of the cube at position ([x], [y], [z]) in the initialization
     * procedure region to [isActive].
     */
    private fun setCubeState(x: Int, y: Int, z: Int, isActive: Boolean) {
        cubes[x - region.xRange.first][y - region.yRange.first][z - region.zRange.first] = isActive
    }

    /**
     * Executes the given [rebootStep] by turning on or off all cubes in the specified region that
     * overlap with the initialization procedure region.
     */
    fun execute(rebootStep: RebootStep) {
        val overlap = region.overlapWith(rebootStep.region)
        overlap.forEachVoxel { x, y, z ->
            if (rebootStep.isOn != isCubeActive(x, y, z)) {
                activeCubesCount += if (rebootStep.isOn) 1 else -1
                setCubeState(x, y, z, rebootStep.isOn)
            }
        }
    }
}
