package com.curtislb.adventofcode.year2019.day12.body

import com.curtislb.adventofcode.common.vector.IntVector
import java.util.Objects
import kotlin.math.abs

/**
 * A celestial body, with a [position] and [velocity] in 3D space.
 *
 * @param initialPosition The initial 3D position vector of the body.
 * @param initialVelocity The initial 3D velocity vector of the body.
 */
class Body(
    initialPosition: IntVector = IntVector(DIMENSION_COUNT),
    initialVelocity: IntVector = IntVector(DIMENSION_COUNT)
) {
    /**
     * The current 3D position vector of the body.
     */
    private val _position: IntVector = initialPosition.copy()

    /**
     * The current 3D velocity vector of the body.
     */
    private val _velocity: IntVector = initialVelocity.copy()

    /**
     * Returns a copy of the current 3D position vector of the body.
     */
    fun position(): IntVector = _position.copy()

    /**
     * Returns a copy of the current 3D velocity vector of the body
     */
    fun velocity(): IntVector = _velocity.copy()

    /**
     * The current total energy (potential and kinetic) of the body.
     */
    fun totalEnergy(): Int {
        val potentialEnergy = _position.componentSumOf { abs(it) }
        val kineticEnergy = _velocity.componentSumOf { abs(it) }
        return potentialEnergy * kineticEnergy
    }

    /**
     * Updates the velocity of this body due to gravity from [other].
     */
    fun applyGravity(other: Body) {
        for (index in 0 until DIMENSION_COUNT) {
            when {
                _position[index] < other._position[index] -> _velocity[index]++
                _position[index] > other._position[index] -> _velocity[index]--
                else -> {}
            }
        }
    }

    /**
     * Updates the [position] of this body based on its current velocity.
     */
    fun applyVelocity() {
        _position.add(_velocity)
    }

    override fun toString(): String =
        "pos=${_position.toXyzString()}, vel=${_velocity.toXyzString()}"

    override fun equals(other: Any?): Boolean {
        return other is Body && _position == other._position && _velocity == other._velocity
    }

    override fun hashCode(): Int = Objects.hash(_position, _velocity)

    companion object {
        /**
         * The number of spatial dimensions for the position and velocity of a body.
         */
        const val DIMENSION_COUNT = 3

        /**
         * Returns a [Body] with an initial position read from the given [positionString].
         *
         * @param positionString A string representing a position, in the form `<x=$x, y=$y, z=$z>`.
         *
         * @throws IllegalArgumentException If [positionString] has the wrong format.
         */
        fun fromPositionString(positionString: String): Body {
            val componentStrings = positionString.trim(' ', '\t', '\r', '\n', '<', '>').split(',')
            require(componentStrings.size == DIMENSION_COUNT) {
                "Incorrect number of components: ${componentStrings.size} != $DIMENSION_COUNT"
            }

            val position = IntVector(DIMENSION_COUNT)
            for (componentString in componentStrings) {
                val (name, value) = componentString.trim().split('=')
                when (name) {
                    "x" -> position.x = value.toInt()
                    "y" -> position.y = value.toInt()
                    "z" -> position.z = value.toInt()
                    else -> throw IllegalArgumentException("Unknown component name: $name")
                }
            }

            return Body(initialPosition = position)
        }

        /**
         * Returns a string representation of this 3D vector, in the form `<x=$x, y=$y, z=$z>`.
         *
         * @throws IndexOutOfBoundsException If the dimensionality of the vector is less than 3.
         */
        private fun IntVector.toXyzString(): String = "<x=$x, y=$y, z=$z>"
    }
}
