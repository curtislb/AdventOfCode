package com.curtislb.adventofcode.year2019.day12.body

import com.curtislb.adventofcode.common.collection.deepCopy
import com.curtislb.adventofcode.common.collection.uniquePairs
import com.curtislb.adventofcode.common.io.mapLines
import java.io.File

/**
 * A system of multiple celestial bodies, which move and exert gravity on each other in 3D space.
 *
 * @param file The file from which the initial positions for all bodies will be read, one per line.
 */
class NBodySystem(file: File) {
    /**
     * The current states (positions and velocities) of all bodies in this system.
     */
    var bodies: List<Body> = file.mapLines { Body(it) }
        private set

    /**
     * The initial states (positions and velocities) of all bodies in this system.
     */
    private val initialStates: List<Body> = bodies.deepCopy { it.copy() }

    /**
     * The current total energy (potential and kinetic) of this system.
     */
    val totalEnergy: Int get() = bodies.sumOf { it.totalEnergy }

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
     * Resets the positions and velocities of all bodies to their initial values.
     */
    fun reset() {
        bodies = initialStates.deepCopy { it.copy() }
    }

    /**
     * Returns a vector representing the periodicity of this system along each 3D axis.
     *
     * The period of an axis is the number of iterations required for the position and velocity of
     * all bodies along that axis to return to their initial values.
     *
     * Calling this method will modify the positions and velocities of bodies in this system. In
     * order to restore the system to its initial configuration following this method, use [reset].
     */
    fun findAxialPeriodicity(): Triple<Int, Int, Int> {
        // Store the initial body configurations for each axis.
        val initialValues = Triple(
            bodies.flatMap { listOf(it.position.first, it.velocity.first) },
            bodies.flatMap { listOf(it.position.second, it.velocity.second) },
            bodies.flatMap { listOf(it.position.third, it.velocity.third) }
        )

        // Run simulation until the periods of all axes are found.
        val periods = Array<Int?>(3) { null }
        var steps = 0
        while (true) {
            // Update the positions and velocities of all bodies.
            simulate()
            steps++

            // Check if any axes have returned to their initial configurations.
            if (periods[0] == null) {
                val xValues = bodies.flatMap { listOf(it.position.first, it.velocity.first) }
                if (xValues == initialValues.first) {
                    periods[0] = steps
                }
            }
            if (periods[1] == null) {
                val yValues = bodies.flatMap { listOf(it.position.second, it.velocity.second) }
                if (yValues == initialValues.second) {
                    periods[1] = steps
                }
            }
            if (periods[2] == null) {
                val zValues = bodies.flatMap { listOf(it.position.third, it.velocity.third) }
                if (zValues == initialValues.third) {
                    periods[2] = steps
                }
            }

            // Return x-, y-, and z-axis periods when all 3 have been found
            val xPeriod = periods[0]
            val yPeriod = periods[1]
            val zPeriod = periods[2]
            if (xPeriod != null && yPeriod != null && zPeriod != null) {
                return Triple(xPeriod, yPeriod, zPeriod)
            }
        }
    }

    override fun toString(): String = bodies.joinToString(separator = "\n")
}
