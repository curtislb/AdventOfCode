package com.curtislb.adventofcode.year2019.day12.body

import com.curtislb.adventofcode.common.collection.MutableVector
import com.curtislb.adventofcode.common.collection.deepCopy
import com.curtislb.adventofcode.common.collection.uniquePairs
import com.curtislb.adventofcode.common.io.mapLines
import java.io.File

/**
 * A system of multiple celestial bodies, which move and exert gravity on each other in 3D space.
 *
 * @param file The file from which the initial [MutableVector] positions for all bodies will be read, one per line.
 */
class NBodySystem(file: File) {
    /**
     * The current states (positions and velocities) of all bodies in this system.
     */
    var bodies: List<Body>
        private set

    /**
     * The initial states (positions and velocities) of all bodies in this system.
     */
    private val initialStates: List<Body>

    init {
        bodies = file.mapLines { Body(it) }
        initialStates = bodies.deepCopy { it.copy() }
    }

    /**
     * The current total energy (potential and kinetic) of this system.
     */
    val totalEnergy: Int get() = bodies.sumBy { it.totalEnergy }

    /**
     * Updates the positions and velocities of all bodies in this system for a given number of time [steps].
     */
    fun simulate(steps: Int = 1) {
        for (step in 1..steps) {
            bodies.uniquePairs().forEach { (body1, body2) ->
                body1.applyGravity(body2)
                body2.applyGravity(body1)
            }
            bodies.forEach { it.applyVelocity() }
        }
    }

    /**
     * Resets the positions and velocities of all bodies in this system to their initial values.
     */
    fun reset() {
        bodies = initialStates.deepCopy { it.copy() }
    }

    /**
     * Returns a vector representing the periodicity of this system along each 3D axis.
     *
     * The period of an axis is the number of iterations required for the position and velocity of all bodies along that
     * axis to return to their initial values.
     *
     * Calling this method will modify the positions and velocities of bodies in this system. In order to restore the
     * system to its initial configuration following this method, use [reset].
     */
    fun findAxialPeriodicity(): MutableVector {
        // Store the initial body configurations for each axis.
        val initialValues: List<List<Int>> = (0 until Body.DIMENSION_COUNT).map { index ->
            bodies.flatMap { listOf(it.position[index], it.velocity[index]) }
        }

        // Run simulation until the periods of all axes are found.
        val periods = Array<Int?>(Body.DIMENSION_COUNT) { null }
        var steps = 0
        do {
            // Update the positions and velocities of all bodies.
            simulate()
            steps++

            // Check if any axes have returned to their initial configurations.
            for (i in periods.indices) {
                if (periods[i] == null) {
                    val values = bodies.flatMap { listOf(it.position[i], it.velocity[i]) }
                    if (values == initialValues[i]) {
                        periods[i] = steps
                    }
                }
            }
        } while (periods.any { it == null })

        return MutableVector(*IntArray(periods.size) { index -> periods[index]!! })
    }

    override fun toString(): String = bodies.joinToString(separator = "\n")
}
