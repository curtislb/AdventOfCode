package com.curtislb.adventofcode.year2019.day12.body

import com.curtislb.adventofcode.common.iteration.uniquePairs
import com.curtislb.adventofcode.common.io.mapLines
import com.curtislb.adventofcode.common.vector.IntVector
import java.io.File

/**
 * A system of multiple celestial bodies, which move and exert gravity on each other in 3D space.
 *
 * @param file The file from which the initial positions for all bodies will be read, one per line.
 */
class NBodySystem(file: File) {
    /**
     * The current states (positions and velocities) of all bodies in the system.
     */
    var bodies: List<Body> = file.mapLines { Body.fromPositionString(it) }
        private set

    /**
     * Returns the current total energy (potential and kinetic) of the system.
     */
    fun totalEnergy(): Int = bodies.sumOf { it.totalEnergy() }

    /**
     * Updates the positions and velocities of all bodies for a given number of time [steps].
     */
    fun simulate(steps: Int = 1) {
        for (step in 1..steps) {
            for ((bodyA, bodyB) in bodies.uniquePairs()) {
                bodyA.applyGravity(bodyB)
                bodyB.applyGravity(bodyA)
            }

            for (body in bodies) {
                body.applyVelocity()
            }
        }
    }

    /**
     * Returns a vector representing the periodicity of the system along each 3D axis.
     *
     * The period of an axis is the number of iterations required for the position and velocity of
     * all bodies along that axis to return to their initial values.
     *
     * Note that calling this function may cause the positions and velocities of bodies in the
     * system to change.
     */
    fun findPerAxisPeriods(): IntVector {
        // Store the initial body configurations for each axis
        val axisFilter = BooleanArray(Body.DIMENSION_COUNT) { true }
        val initialValues = perAxisComponentValues(axisFilter)

        // Run simulation until the periods of all axes are found
        val periods = IntVector(Body.DIMENSION_COUNT) { -1 }
        var steps = 0
        while (axisFilter.any { it }) {
            // Update the positions and velocities of all bodies
            simulate()
            steps++

            // Check if any axes have returned to their initial configurations
            val axisValues = perAxisComponentValues(axisFilter)
            for (index in initialValues.indices) {
                if (axisFilter[index] && axisValues[index] == initialValues[index]) {
                    periods[index] = steps
                }
            }

            for (index in axisFilter.indices) {
                axisFilter[index] = periods[index] == -1
            }
        }

        return periods
    }

    private fun perAxisComponentValues(axisFilter: BooleanArray): List<List<Int>> {
        val values = List(axisFilter.size) { index ->
            if (axisFilter[index]) mutableListOf<Int>() else emptyList()
        }

        for (body in bodies) {
            val position = body.position()
            val velocity = body.velocity()
            for (index in values.indices) {
                if (axisFilter[index]) {
                    with(values[index] as MutableList<Int>) {
                        add(position[index])
                        add(velocity[index])
                    }
                }
            }
        }

        return values
    }

    override fun toString(): String = bodies.joinToString(separator = "\n")
}
