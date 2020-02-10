package com.curtislb.adventofcode.year2019.day12.body

import com.curtislb.adventofcode.common.collection.MutableVector
import kotlin.math.abs

/**
 * A celestial body, with a [position] and [velocity] in 3D space.
 */
class Body(val position: MutableVector = MutableVector(), val velocity: MutableVector = MutableVector()) {
    /**
     * An celestial body, with a [position] and [velocity] in 3D space.
     *
     * @param bodyString A string representation of the [MutableVector] position for this body.
     */
    constructor(bodyString: String) : this(position = MutableVector(bodyString))

    /**
     * The current total energy (potential and kinetic) of this body.
     */
    val totalEnergy: Int
        get() = position.sumBy { abs(it) } * velocity.sumBy { abs(it) }

    /**
     * Updates the velocity of this body due to gravity from [other].
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
     * Updates the [position] of this body based on its current velocity.
     */
    fun applyVelocity() { position += velocity }

    /**
     * Returns a deep copy of this body, with the same current position and velocity.
     */
    fun copy(): Body = Body(position.copy(), velocity.copy())

    override fun toString(): String = "pos=$position, vel=$velocity"
}
