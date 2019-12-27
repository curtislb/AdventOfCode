package com.adventofcode.curtislb.year2019.day12.body

import kotlin.math.abs

/**
 * A celestial body, with a position and velocity in 3D space.
 * @param position A [Vector] representing the current position of this [Body].
 * @param velocity A [Vector] representing the current velocity of this [Body].
 */
class Body(val position: Vector = Vector(0, 0, 0), val velocity: Vector = Vector(0, 0, 0)) {
    /**
     * An orbiting body, with a position and velocity in 3D space.
     * @param bodyString A [String] representation of the position [Vector] for this [Body].
     */
    constructor(bodyString: String) : this(position = Vector(bodyString))

    /**
     * The current total energy (potential and kinetic) of this [Body].
     */
    val totalEnergy: Int
        get() = position.sumBy { abs(it) } * velocity.sumBy { abs(it) }

    /**
     * Updates the [velocity] of this [Body] due to gravity from another [Body].
     * @param other Another [Body] whose gravity will be applied to this one.
     */
    fun applyGravity(other: Body) {
        if (other.position.x != position.x) {
           velocity.x += if (other.position.x > position.x) 1 else -1
        }
        if (other.position.y != position.y) {
           velocity.y += if (other.position.y > position.y) 1 else -1
        }
        if (other.position.z != position.z) {
           velocity.z += if (other.position.z > position.z) 1 else -1
        }
    }

    /**
     * Updates the [position] of this [Body] according to its current velocity.
     */
    fun applyVelocity() { position += velocity }

    /**
     * Creates a deep copy of this [Body] with the same current [position] and [velocity].
     */
    fun copy(): Body = Body(position.copy(), velocity.copy())

    override fun toString(): String = "pos=$position, vel=$velocity"
}
