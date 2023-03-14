package com.curtislb.adventofcode.year2020.day17.conway

import com.curtislb.adventofcode.common.collection.mapToMutableSet
import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.io.forEachLineIndexed
import com.curtislb.adventofcode.common.vector.IntVector
import java.io.File

/**
 * An energy source consisting of an infinite multidimensional grid of Conway cubes that become
 * active or inactive over time, according to a set boot process.
 *
 * @param initialActiveCubes The spatial coordinates of all active cubes prior to the boot process.
 */
class EnergySource(initialActiveCubes: Set<IntVector>) {
    /**
     * The spatial coordinates of all currently active cubes.
     */
    private var activeCubes = initialActiveCubes.mapToMutableSet { it.copy() }

    /**
     * The number of currently active cubes.
     */
    val activityLevel: Int
        get() = activeCubes.size

    /**
     * Runs the energy source boot process for a number of cycles equal to [cycleCount].
     *
     * During each cycle, each cube in the energy source may become active or inactive according to
     * the following rules, which are applied to all cubes simultaneously:
     *
     * - Each active cube that has fewer than 2 or more than 3 active neighbors becomes inactive.
     * - Each inactive cube that has exactly 3 active neighbors becomes active.
     */
    fun runBootProcess(cycleCount: Int) {
        activeCubes = BootProcess.runSimulation(activeCubes, cycleCount)
    }

    override fun toString(): String = activeCubes.toString()

    companion object {
        /**
         * Returns an [EnergySource], with its initial active cubes read from the given [file] and
         * a number of spatial dimensions equal to [dimensionCount].
         *
         * @throws IllegalArgumentException If [dimensionCount] is negative.
         */
        fun fromFile(file: File, dimensionCount: Int = 3): EnergySource {
            require(dimensionCount >= 0) {
                "Number of spatial dimensions must be non-negative: $dimensionCount"
            }

            val initialActiveCubes = mutableSetOf<IntVector>()
            file.forEachLineIndexed { rowIndex, line ->
                line.forEachIndexed { colIndex, char ->
                    if (char == '#') {
                        val point = Point.fromMatrixCoordinates(rowIndex, colIndex)
                        val cube = IntVector(dimensionCount) { index ->
                            when (index) {
                                0 -> point.x
                                1 -> point.y
                                else -> 0
                            }
                        }
                        initialActiveCubes.add(cube)
                    }
                }
            }

            return EnergySource(initialActiveCubes)
        }
    }
}
