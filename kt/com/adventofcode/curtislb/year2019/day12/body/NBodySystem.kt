package com.adventofcode.curtislb.year2019.day12.body

import com.adventofcode.curtislb.common.collection.deepCopy
import com.adventofcode.curtislb.common.collection.uniquePairs
import java.io.File

/**
 * A system of multiple celestial bodies, which move and exert gravity on each other in 3D space.
 * @param file The file from which the initial [Body] configurations will be read, one per line.
 */
class NBodySystem(file: File) {
    /**
     * The current states (positions and velocities) of all bodies in this [NBodySystem].
     */
    var bodies: List<Body>
        private set

    /**
     * The initial states (positions and velocities) of all bodies in this [NBodySystem].
     */
    private val initialState: List<Body>

    init {
        val listBuilder = mutableListOf<Body>()
        file.forEachLine { listBuilder.add(Body(it)) }
        initialState = listBuilder.toList()
        bodies = initialState.deepCopy { it.copy() }
    }

    /**
     * The current total energy (potential and kinetic) of this [NBodySystem].
     */
    val totalEnergy: Int
        get() = bodies.sumBy { it.totalEnergy }

    /**
     * Simulates the change in positions and velocities of all bodies in this [NBodySystem] over time.
     *
     * When this function is called, the positions and velocities of all elements in [bodies] will be updated to their
     * corresponding values after [steps] iterations.
     *
     * @param steps The number of time steps that should be simulated.
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
     * Resets the positions and velocities of all bodies in this [NBodySystem] to their initial values.
     */
    fun reset() {
        bodies = initialState.deepCopy { it.copy() }
    }

    /**
     * Finds the periodicity of this [NBodySystem] along each 3D axis.
     *
     * The period of an axis is given by the number of iterations required for the position and velocity of all bodies
     * along that axis to return to their initial values.
     *
     * Note that calling this method will modify the positions and velocities of [bodies] in this [NBodySystem]. In
     * order to restore the system to its initial configuration following this method, use [reset].
     *
     * @return A [Vector] with each component representing the period of this [NBodySystem] along that axis.
     */
    fun findAxialPeriodicity(): Vector {
        val initialXValues: List<Int> = bodies.flatMap { listOf(it.position.x, it.velocity.x) }
        val initialYValues: List<Int> = bodies.flatMap { listOf(it.position.y, it.velocity.y) }
        val initialZValues: List<Int> = bodies.flatMap { listOf(it.position.z, it.velocity.z) }
        var steps = 1
        simulate()

        var periodX: Int? = null
        var periodY: Int? = null
        var periodZ: Int? = null
        while (periodX == null || periodY == null || periodZ == null) {
            if (periodX == null) {
                val values = bodies.flatMap { listOf(it.position.x, it.velocity.x) }
                if (values == initialXValues) {
                    periodX = steps
                }
            }
            if (periodY == null) {
                val values = bodies.flatMap { listOf(it.position.y, it.velocity.y) }
                if (values == initialYValues) {
                    periodY = steps
                }
            }
            if (periodZ == null) {
                val values = bodies.flatMap { listOf(it.position.z, it.velocity.z) }
                if (values == initialZValues) {
                    periodZ = steps
                }
            }

            simulate()
            steps++
        }

        return Vector(periodX, periodY, periodZ)
    }

    override fun toString(): String = bodies.joinToString(separator = "\n") { it.toString() }
}
