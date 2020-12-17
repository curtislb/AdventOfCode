package com.curtislb.adventofcode.year2020.day17.conway

import com.curtislb.adventofcode.common.collection.MutableVector
import com.curtislb.adventofcode.common.collection.Vector
import com.curtislb.adventofcode.common.grid.Point

/**
 * TODO
 */
class ConwayCubes(initialState: String, dimensionCount: Int = 3) {
    /**
     * TODO
     */
    private var activeCubes: Set<Vector> = mutableSetOf<Vector>().apply {
        initialState.trim().lines().forEachIndexed { rowIndex, line ->
            line.forEachIndexed { colIndex, char ->
                if (char == '#') {
                    val point = Point.fromMatrixCoordinates(rowIndex, colIndex)
                    val vector = MutableVector.ofZeros(dimensionCount).run {
                        this[0] = point.x
                        this[1] = point.y
                        toVector()
                    }
                    add(vector)
                }
            }
        }
    }

    /**
     * TODO
     */
    val activityLevel: Int get() = activeCubes.size

    fun update(cycleCount: Int = 1) {
        for (cycle in 1..cycleCount) {
            val newActiveCubes = mutableSetOf<Vector>()

            val inactiveCubes = mutableSetOf<Vector>()
            activeCubes.forEach { cube ->
                var activeNeighborCount = 0
                cube.neighbors.forEach { neighbor ->
                    if (neighbor in activeCubes) {
                        activeNeighborCount++
                    } else {
                        inactiveCubes.add(neighbor)
                    }
                }
                if (activeNeighborCount in 2..3) {
                    newActiveCubes.add(cube)
                }
            }

            inactiveCubes.forEach { cube ->
                if (cube.neighbors.count { it in activeCubes } == 3) {
                    newActiveCubes.add(cube)
                }
            }

            activeCubes = newActiveCubes
        }
    }

    override fun toString(): String = activeCubes.toString()
}
