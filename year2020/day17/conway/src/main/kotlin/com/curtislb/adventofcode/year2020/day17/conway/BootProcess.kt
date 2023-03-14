package com.curtislb.adventofcode.year2020.day17.conway

import com.curtislb.adventofcode.common.simulation.GameOfLife
import com.curtislb.adventofcode.common.vector.IntVector

/**
 * A Game of Life simulation representing the energy source boot process.
 */
internal object BootProcess : GameOfLife<MutableSet<IntVector>, IntVector, Boolean>() {
    override fun getValue(state: MutableSet<IntVector>, key: IntVector): Boolean = key in state

    override fun setValue(
        state: MutableSet<IntVector>,
        key: IntVector,
        value: Boolean
    ): MutableSet<IntVector> {
        return if (getValue(state, key) == value) {
            state
        } else {
            state.apply { if (value) add(key) else remove(key) }
        }
    }

    override fun getUpdatableKeys(state: MutableSet<IntVector>): Sequence<IntVector> {
        val updatableKeys = state.toMutableSet()
        for (cube in state) {
            updatableKeys.addAll(cube.neighbors())
        }
        return updatableKeys.asSequence()
    }

    override fun getNeighboringKeys(
        state: MutableSet<IntVector>,
        key: IntVector
    ): Sequence<IntVector> {
        return key.neighbors().asSequence()
    }

    override fun applyUpdateRules(value: Boolean, neighbors: Sequence<Boolean>): Boolean {
        val activeCount = neighbors.count { it }
        return activeCount == 3 || (value && activeCount == 2)
    }

    override fun copyState(state: MutableSet<IntVector>): MutableSet<IntVector> =
        state.toMutableSet()
}
