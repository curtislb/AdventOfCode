package com.curtislb.adventofcode.year2020.day17.conway

import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.io.forEachLineIndexed
import com.curtislb.adventofcode.common.simulation.GameOfLife
import java.io.File

/**
 * An energy source consisting of an infinite multidimensional grid of [ConwayCube]s that become
 * active or inactive over time, according to a set boot process.
 *
 * @param initialActiveCubes A set of all active cubes prior to the boot process.
 */
class EnergySource(initialActiveCubes: Set<ConwayCube>) {
    /**
     * All currently active cubes in the energy source.
     */
    private var activeCubes = initialActiveCubes.toMutableSet()

    /**
     * The number of currently active cubes in the energy source.
     */
    val activityLevel: Int
        get() = activeCubes.size

    /**
     * Runs the energy source boot process for a number of cycles equal to [cycleCount].
     *
     * During each cycle, each [ConwayCube] in the energy source may become active or inactive
     * according to the following rules, which are applied to all cubes simultaneously:
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
         */
        fun fromFile(file: File, dimensionCount: Int = 3): EnergySource {
            val initialActiveCubes = mutableSetOf<ConwayCube>()
            file.forEachLineIndexed { rowIndex, line ->
                line.trim().forEachIndexed { colIndex, char ->
                    if (char == '#') {
                        val point = Point.fromMatrixCoordinates(rowIndex, colIndex)
                        val cubeCoordinates = IntArray(dimensionCount) { index ->
                            when (index) {
                                0 -> point.x
                                1 -> point.y
                                else -> 0
                            }
                        }
                        initialActiveCubes.add(ConwayCube(*cubeCoordinates))
                    }
                }
            }
            return EnergySource(initialActiveCubes)
        }
    }

    /**
     * A Game of Life simulation representing the energy source boot process.
     */
    private object BootProcess : GameOfLife<MutableSet<ConwayCube>, ConwayCube, Boolean>() {
        override fun getValue(state: MutableSet<ConwayCube>, key: ConwayCube): Boolean =
            key in state

        override fun setValue(
            state: MutableSet<ConwayCube>,
            key: ConwayCube,
            value: Boolean
        ): MutableSet<ConwayCube> {
            return if (getValue(state, key) == value) {
                state
            } else {
                state.apply { if (value) add(key) else remove(key) }
            }
        }

        override fun getUpdatableKeys(state: MutableSet<ConwayCube>): Sequence<ConwayCube> {
            val updatableKeys = state.toMutableSet()
            for (cube in state) {
                updatableKeys.addAll(cube.neighbors())
            }
            return updatableKeys.asSequence()
        }

        override fun getNeighboringKeys(
            state: MutableSet<ConwayCube>,
            key: ConwayCube
        ): Sequence<ConwayCube> {
            return key.neighbors().asSequence()
        }

        override fun applyUpdateRules(value: Boolean, neighbors: Sequence<Boolean>): Boolean {
            val activeCount = neighbors.count { it }
            return activeCount == 3 || (value && activeCount == 2)
        }

        override fun copyState(state: MutableSet<ConwayCube>): MutableSet<ConwayCube> =
            state.toMutableSet()
    }
}
