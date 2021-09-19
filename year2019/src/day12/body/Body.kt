package com.curtislb.adventofcode.year2019.day12.body

import com.curtislb.adventofcode.common.collection.MutableIntVector
import java.util.Objects
import kotlin.math.abs

/**
 * A celestial body, with a [position] and [velocity] in 3D space.
 *
 * @param position The 3D position vector of this body.
 * @param velocity The 3D velocity vector of this body.
 *
 * @throws IllegalArgumentException If either [position] or [velocity] has the wrong number of components.
 */
class Body(
    val position: MutableIntVector = MutableIntVector.ofZeros(DIMENSION_COUNT),
    val velocity: MutableIntVector = MutableIntVector.ofZeros(DIMENSION_COUNT)
) {
    init {
        require(position.size == DIMENSION_COUNT) { "Position must have $DIMENSION_COUNT components: ${position.size}" }
        require(velocity.size == DIMENSION_COUNT) { "Velocity must have $DIMENSION_COUNT components: ${velocity.size}" }
    }

    /**
     * The current total energy (potential and kinetic) of this body.
     */
    val totalEnergy: Int get() = position.sumBy { abs(it) } * velocity.sumBy { abs(it) }

    /**
     * A celestial body, with a [position] and [velocity] in 3D space.
     *
     * @param positionString A string representation of the position for this body, in the form `<x=$x, y=$y, z=$z>`.
     */
    constructor(positionString: String) : this() {
        val fieldStrings = positionString.trim(' ', '\t', '\r', '\n', '<', '>').split(',')
        fieldStrings.forEach { fieldString ->
            val (field, value) = fieldString.trim().split('=')
            when (field) {
                "x" -> position[0] = value.toInt()
                "y" -> position[1] = value.toInt()
                "z" -> position[2] = value.toInt()
                else -> throw IllegalArgumentException("Unknown field: $field")
            }
        }
    }

    /**
     * Updates the velocity of this body due to gravity from [other].
     */
    fun applyGravity(other: Body) {
        for (i in 0 until DIMENSION_COUNT) {
            if (position[i] != other.position[i]) {
                velocity[i] += if (position[i] < other.position[i]) 1 else -1
            }
        }
    }

    /**
     * Updates the [position] of this body based on its current velocity.
     */
    fun applyVelocity() {
        position.add(velocity)
    }

    /**
     * Returns a deep copy of this body, with the same current position and velocity.
     */
    fun copy(): Body = Body(position.copy(), velocity.copy())

    override fun equals(other: Any?): Boolean {
        return other is Body && position == other.position && velocity == other.velocity
    }

    override fun hashCode(): Int = Objects.hash(position, velocity)

    override fun toString(): String = "pos=${position.toXyzString()}, vel=${velocity.toXyzString()}"

    companion object {
        /**
         * The number of spatial dimensions represented by the position and velocity vectors for this body.
         */
        internal const val DIMENSION_COUNT = 3

        /**
         * Returns a string representation of this 3D vector, in the form `<x=$x, y=$y, z=$z>`.
         *
         * @throws IllegalArgumentException If this vector has the wrong number of components.
         */
        fun MutableIntVector.toXyzString(): String {
            require(size == DIMENSION_COUNT) { "Vector must have $DIMENSION_COUNT components: $size" }
            return "<x=${this[0]}, y=${this[1]}, z=${this[2]}>"
        }
    }
}
